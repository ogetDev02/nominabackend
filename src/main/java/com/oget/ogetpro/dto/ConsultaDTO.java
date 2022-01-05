package com.oget.ogetpro.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ConsultaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -111886513498654238L;

	
	@JsonProperty("ip")
	private String ip;
	
	@JsonProperty("idTipoIdent")
	private Integer idTipoIdent;

	@JsonProperty("identificacion")
	private String identificacion;

	@JsonProperty("fechaExpDoc")
	private String fechaExpDoc;

	@JsonProperty("nombres")
	private String nombres;

	@JsonProperty("apellido1")
	private String apellido1;

	@JsonProperty("apellido2")
	private String apellido2;

		
	@JsonProperty("token")
	private String token;

		
	@JsonProperty("idValidacion")
	private Long idValidacion;
	
		
	public Long getIdValidacion() {
		return idValidacion;
	}

	public void setIdValidacion(Long idValidacion) {
		this.idValidacion = idValidacion;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFechaExpDoc() {
		return fechaExpDoc;
	}

	public void setFechaExpDoc(String fechaExpDoc) {
		this.fechaExpDoc = fechaExpDoc;
	}

	public Integer getIdTipoIdent() {
		return idTipoIdent;
	}

	public void setIdTipoIdent(Integer idTipoIdent) {
		this.idTipoIdent = idTipoIdent;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
}