package com.appgate.mathoperations.api.business;

import com.appgate.mathoperations.api.rq.AddOperandRq;
import com.appgate.mathoperations.api.rq.CalculateRq;
import com.appgate.mathoperations.api.rq.SessionRq;
import com.appgate.mathoperations.api.rs.AddOperandRs;
import com.appgate.mathoperations.api.rs.CalculateRs;
import com.appgate.mathoperations.api.rs.Response;
import com.appgate.mathoperations.api.rs.SessionRs;

public interface MathOperationsInt {

	/**
	 * Método encargado de generar un id de sessión para un usuario
	 * y registrar sus datos en base de datos
	 * @param sessionRq Objeto con los datos del usuario
	 * @return Objeto con los datos de respuesta del servicio
	 */
	public Response<SessionRs> generateSessionId(SessionRq sessionRq);
	
	/**
	 * Método encargado de adicionar un operando para el usuario
	 * @param addRq Objeto con los datos de la petición
	 * @return Objeto con los datos de respuesta del servicio
	 */
	public Response<AddOperandRs> addOperand(AddOperandRq addRq);
	
	/**
	 * Método encargado de realizar un cálculo de una operación
	 * @param calculateRq Objeto con los datos de la petición
	 * @return Objeto con los datos de respuesta del servicio
	 */
	public Response<CalculateRs> calculate(CalculateRq calculateRq);
}
