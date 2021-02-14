package com.appgate.mathoperations.api.business;

import com.appgate.mathoperations.api.rq.AddOperandRq;
import com.appgate.mathoperations.api.rq.CalculateRq;
import com.appgate.mathoperations.api.rq.SessionRq;
import com.appgate.mathoperations.api.rs.AddOperandRs;
import com.appgate.mathoperations.api.rs.CalculateRs;
import com.appgate.mathoperations.api.rs.Response;
import com.appgate.mathoperations.api.rs.SessionRs;

public interface MathOperationsInt {

	public Response<SessionRs> generateSessionId(SessionRq sessionrq);
	
	public Response<AddOperandRs> addOperand(AddOperandRq addRq);
	
	public Response<CalculateRs> calculate(CalculateRq calculateRq);
}
