package com.appgate.mathoperations.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import io.swagger.annotations.ApiModelProperty;

@EntityScan
@Entity
@Table(name = "USUARIO")
public class User {

	
	/*@Id
    @Column(name="ID", unique=true, updatable=false, nullable=false)
	@GeneratedValue
	private long id;*/
	@Column(name="TIPO_DOC", unique=true, nullable=false)
	private String tipoDoc;
	@Id
	@Column(name="NRO_DOC", unique=true, nullable=false)
	private Long nroDoc;
	@Column(name="SESSION_ID", unique=true, nullable=false)
	private String sessionId;
	@Column(name="VALORES")
	private String valores;
	
	public User() {
		super();
	}
	
	public User(String tipoDoc, Long nroDoc, String sessionId, String valores) {
		super();
		this.tipoDoc = tipoDoc;
		this.nroDoc = nroDoc;
		this.sessionId = sessionId;
		this.valores = valores;
	}

	public User(String tipoDoc, Long nroDoc, String sessionId) {
		super();
		this.tipoDoc = tipoDoc;
		this.nroDoc = nroDoc;
		this.sessionId = sessionId;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public Long getNroDoc() {
		return nroDoc;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getValores() {
		return valores;
	}
}
