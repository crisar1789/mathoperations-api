package com.appgate.mathoperations.api.rs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Object response for Adding Operand")
public class AddOperandRs {

	@ApiModelProperty(value = "message for client", required = true)
	private String message;
	
	public AddOperandRs(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
