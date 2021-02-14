package com.appgate.mathoperations.api.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
	
	@Value("${security.key}")
    private String key;
	
	@Value("${security.uniq-id}")
    private String uniqId;
	
	public MathOperationsImpl() {
	}
	
	@Override
	public Response<SessionRs> generateSessionId(SessionRq sessionRq) {
		
		Response<SessionRs> response = null;  
		try {
			
			String encryptSessionId = sessionRq.getTipoDoc()+"."
					+sessionRq.getNroDoc()+"."+this.uniqId;
			
			String sessionId = Security.encriptar(encryptSessionId, this.key);
			SessionRs sessionRs = new SessionRs(sessionId);
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
		AddOperandRs addRs = new AddOperandRs();
		try {
			
			String decryptSessionId = Security.encriptar(addRq.getSessionId(), this.key);
			
			String[] datos = decryptSessionId.split(".");
			
			if (!addRq.getTipoDoc().equals(datos[0])
					|| !addRq.getNroDoc().equals(datos[1])
						|| !this.key.equals(datos[2])) {
				addRs.setMessage("Invalid Session");
				return response = new Response<>(400, ConstantsUtil.BAD_REQUEST, addRs);
			}
			
			
			
		} catch(Exception e) {
			log.error("Business error", e);
			response = new Response<>(500, ConstantsUtil.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Override
	public Response<CalculateRs> calculate(CalculateRq calculateRq) {
		// TODO Auto-generated method stub
		return null;
	}

}
