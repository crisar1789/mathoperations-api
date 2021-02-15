package com.appgate.mathoperations.api.rs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Object response for User Session Id")
public class SessionRs {

	@ApiModelProperty(value = "User Session Id", required = true)
	private String sessionId;
	
	public SessionRs(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return sessionId;
	}
}
