package com.appgate.mathoperations.api.rq;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Object Session request")
public class SessionRq {

	@NotNull
	@ApiModelProperty(value = "Client type identification", required = true)
	private String tipoDoc;
	@NotNull
	@ApiModelProperty(value = "Client number identification", required = true)
	private String nroDoc;
	
	public String getTipoDoc() {
		return tipoDoc;
	}
	
	public String getNroDoc() {
		return nroDoc;
	}
}
