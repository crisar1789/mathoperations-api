package com.appgate.mathoperations.api.business;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.appgate.mathoperations.api.model.User;
import com.appgate.mathoperations.api.model.UserDao;
import com.appgate.mathoperations.api.rq.AddOperandRq;
import com.appgate.mathoperations.api.rq.CalculateRq;
import com.appgate.mathoperations.api.rq.SessionRq;
import com.appgate.mathoperations.api.rs.AddOperandRs;
import com.appgate.mathoperations.api.rs.CalculateRs;
import com.appgate.mathoperations.api.rs.Response;
import com.appgate.mathoperations.api.rs.SessionRs;
import com.appgate.mathoperations.api.security.Security;
import com.appgate.mathoperations.api.util.ConstantsUtil;

@Service
public class MathOperationsImpl implements MathOperationsInt {

	private static final Logger log = LoggerFactory.getLogger(MathOperationsImpl.class);
	
	private String key;
	
	@Autowired
    private UserDao userDao;
	
	public MathOperationsImpl() {
		
		try {
			InputStream is = new ClassPathResource("/key.txt").getInputStream();
		    String contents = new String(FileCopyUtils.copyToByteArray(is), StandardCharsets.UTF_8);
		    this.key = contents.toString();
		    System.out.println(contents);
		} catch (IOException e) {
		    e.printStackTrace();
		} 
	}
	
	@Override
	public Response<SessionRs> generateSessionId(SessionRq sessionRq) {
		
		Response<SessionRs> response = null;  
		try {
			
			String encryptSessionId = sessionRq.getTipoDoc()+"."
					+sessionRq.getNroDoc()+"."+UUID.randomUUID().toString();
			
			String sessionId = Security.encriptar(encryptSessionId, this.key);
			SessionRs sessionRs = new SessionRs(sessionId);
			
			User user = new User(sessionRq.getTipoDoc(), 
					Long.valueOf(sessionRq.getNroDoc()), sessionId);
			
			// Se guarda el usuario
			userDao.save(user);
			
			response = new Response<>(200, ConstantsUtil.SUCCESS_TRANSACTION, sessionRs);
			
		} catch(Exception e) {
			log.error("Business error", e);
			response = new Response<>(500, ConstantsUtil.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Override
	public Response<AddOperandRs> addOperand(AddOperandRq addRq) {
		Response<AddOperandRs> response = null;
		try {
			
			String decryptSessionId = Security.desencriptar(addRq.getSessionId(), this.key);
			String[] datos = decryptSessionId.split("\\.");
			
			Optional<User> optional = userDao.findById(Long.valueOf(datos[1]));
			User user = optional.get();
			
			// Se valida la sesión
			if (!addRq.getSessionId().equals(user.getSessionId())) {
				return new Response<AddOperandRs>(400, ConstantsUtil.BAD_REQUEST, new AddOperandRs("Invalid Session Id"));
			}
			
			String valores = "";
			if (user.getValores() != null) {
				valores = user.getValores() + "," + addRq.getOperand();
			} else {
				valores = addRq.getOperand();
			}
			
			User userUpdate = new User(user.getTipoDoc(), user.getNroDoc(), user.getSessionId(), valores);
			
			userDao.save(userUpdate);
			
			response = new Response<>(200, ConstantsUtil.SUCCESS_TRANSACTION, new AddOperandRs("Operand Added"));
			
		} catch(NoSuchElementException e) {
			log.error("Business error", e);
			response = new Response<>(400, ConstantsUtil.BAD_REQUEST, new AddOperandRs("User session nonexistent"));
		} catch(IllegalArgumentException e) {
			log.error("Business error", e);
			response = new Response<>(500, ConstantsUtil.INTERNAL_SERVER_ERROR, new AddOperandRs("Invalid Session Id"));
		} catch(Exception e) {
			log.error("Business error", e);
			response = new Response<>(500, ConstantsUtil.INTERNAL_SERVER_ERROR, new AddOperandRs("Error Aplication. Contact to Administrator"));
		}
		return response;
	}

	@Override
	public Response<CalculateRs> calculate(CalculateRq calculateRq) {
		Response<CalculateRs> response = null;
		try {
			
			String decryptSessionId = Security.desencriptar(calculateRq.getSessionId(), this.key);
			String[] datos = decryptSessionId.split("\\.");
			
			Optional<User> optional = userDao.findById(Long.valueOf(datos[1]));
			User user = optional.get();
			
			// Se valida la sesión
			if (!calculateRq.getSessionId().equals(user.getSessionId())) {
				return new Response<CalculateRs>(400, ConstantsUtil.BAD_REQUEST, new CalculateRs("Invalid Session"));
			}
			
			// Se valida si el usuario ingresóm operandos
			if (user.getValores() == null) {
				return new Response<CalculateRs>(400, ConstantsUtil.BAD_REQUEST, new CalculateRs("Operands no entered"));
			}
			
			String[] values = user.getValores().split(",");
			List<String> operands = Arrays.asList(values);
			
			if (operands.size() < 2) {
				return new Response<CalculateRs>(400, ConstantsUtil.BAD_REQUEST, new CalculateRs("Must enter at least two operands"));
			}
			
			// Se eliminan tildes, si las hay
			String operacion = calculateRq.getOperation().toUpperCase();
			operacion = Normalizer.normalize(operacion, Normalizer.Form.NFD);
			operacion = operacion.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
			
			String responseMessage = "The calculation is: ";
			BigDecimal calculo = null;
			switch (operacion) {
			case "SUMA":
				calculo = BigDecimal.ZERO;
				for (String valor : operands) {
					calculo = calculo.add(new BigDecimal(valor));
				}
				responseMessage = responseMessage + calculo.toString();
				break;
			case "RESTA":
				for (String valor : operands) {
					if (operands.indexOf(valor) == 0) {
						calculo = new BigDecimal(valor);
					} else {
						calculo = calculo.subtract(new BigDecimal(valor));
					}
				}
				responseMessage = responseMessage + calculo.toString();
				break;
			case "MULTIPLICACION":
				for (String valor : operands) {
					if (operands.indexOf(valor) == 0) {
						calculo = new BigDecimal(valor);
					} else {
						calculo = calculo.subtract(new BigDecimal(valor));
					}
				}
				responseMessage = responseMessage + calculo.toString();
				break;
			case "DIVISION":
				List<String> subList = operands.subList(1, operands.size());
				if (!subList.contains("0")) {
					Float division = null;
					for (String valor : operands) {
						if (operands.indexOf(valor) == 0) {
							division = Float.valueOf(valor);
						} else {
							division = division / Float.valueOf(valor);
						}
					}
					responseMessage = responseMessage + division.toString();
				} else {
					responseMessage = "Invalid Operation";
				}
				break;
			case "POTENCIOACION":
				Double potencia = null;
				for (String valor : operands) {
					if (operands.indexOf(valor) == 0) {
						potencia = Double.valueOf(valor);
					} else {
						potencia = Math.pow(potencia.doubleValue(), Double.valueOf(valor));
					}
				}
				responseMessage = responseMessage + potencia.toString();
				break;

			default:
				responseMessage = "Invalid Operation";
				break;
			}
			
			// Se elimina el usuario y se finaliza el proceso
			userDao.delete(user);
			
			response = new Response<>(200, ConstantsUtil.SUCCESS_TRANSACTION, new CalculateRs(responseMessage));
			
		} catch(NoSuchElementException e) {
			log.error("Business error", e);
			response = new Response<>(400, ConstantsUtil.BAD_REQUEST, new CalculateRs("User session nonexistent"));
		} catch(IllegalArgumentException e) {
			log.error("Business error", e);
			response = new Response<>(500, ConstantsUtil.INTERNAL_SERVER_ERROR, new CalculateRs("Invalid Session"));
		} catch(Exception e) {
			log.error("Business error", e);
			response = new Response<>(500, ConstantsUtil.INTERNAL_SERVER_ERROR, new CalculateRs("Error Aplication. Contact to Administrator"));
		}
		
		return response;
	}

}
