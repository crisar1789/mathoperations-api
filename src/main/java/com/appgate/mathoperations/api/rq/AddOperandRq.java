package com.appgate.mathoperations.api.rq;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Object Add Operand request")
public class AddOperandRq extends SessionRq{

	@NotNull
	@ApiModelProperty(value = "Client session id", required = true)
	private String sessionId;
	@NotNull
	@ApiModelProperty(value = "Operando for calculating", required = true)
	private String operand;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getOperand() {
		return operand;
	}

	public void setOperand(String operand) {
		this.operand = operand;
	}
}
