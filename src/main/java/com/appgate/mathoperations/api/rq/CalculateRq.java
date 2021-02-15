package com.appgate.mathoperations.api.rq;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Object Calculate request")
public class CalculateRq {

	@NotNull
	@ApiModelProperty(value = "Client session id", required = true)
	private String sessionId;
	@NotNull
	@ApiModelProperty(value = "Operartion for calculating", required = true)
	private String operation;
	
	public String getSessionId() {
		return sessionId;
	}
	
	public String getOperation() {
		return operation;
	}
}
