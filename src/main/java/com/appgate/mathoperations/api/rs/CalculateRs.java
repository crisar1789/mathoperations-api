package com.appgate.mathoperations.api.rs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Object response for calculating operation")
public class CalculateRs {

	@ApiModelProperty(value = "Message with the calculation or error message", required = true)
	private String message;
	
	public CalculateRs(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
