package com.appgate.mathoperations.api.rs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Object for response services")
public class Response<T> {

	@ApiModelProperty(value = "Http stautus response", required = true)
	private int status;
	
	@ApiModelProperty(value = "Message response", required = true)
	private String message;
	
	@ApiModelProperty(value = "Data content response", required = true)
	private T body;
	
	public Response(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public Response(int status, String message, T body) {
		this.status = status;
		this.message = message;
		this.body = body;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
}
