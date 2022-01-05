package com.oget.ogetpro.dto;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MensajeDTO<T> implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4487496077948531241L;

	private static final Logger log = LoggerFactory.getLogger(MensajeDTO.class);

	private Integer TIPOALERTA;
	private Integer CODERROR;
	private String MSJERROR;
	private T object;
	private Long idValidacion;

	public MensajeDTO(Integer tIPOALERTA, Integer cODERROR, String mSJERROR, T object) {
		super();
		this.TIPOALERTA = tIPOALERTA;
		this.CODERROR = cODERROR;
		this.MSJERROR = mSJERROR;
		this.object = object;
	}

	public MensajeDTO(Integer tIPOALERTA, Integer cODERROR, String mSJERROR) {
		super();
		this.TIPOALERTA = tIPOALERTA;
		this.CODERROR = cODERROR;
		this.MSJERROR = mSJERROR;
	}

	public MensajeDTO(Integer tIPOALERTA, Integer cODERROR, String mSJERROR, T object, Long idValidacion) {
		super();
		TIPOALERTA = tIPOALERTA;
		CODERROR = cODERROR;
		MSJERROR = mSJERROR;
		this.object = object;
		this.idValidacion = idValidacion;
	}

	public MensajeDTO() {
	}
	
	public Long getIdValidacion() {
		return idValidacion;
	}

	public void setIdValidacion(Long idValidacion) {
		this.idValidacion = idValidacion;
	}

	public Integer getTIPOALERTA() {
		return TIPOALERTA;
	}

	public void setTIPOALERTA(Integer tIPOALERTA) {
		TIPOALERTA = tIPOALERTA;
	}

	public Integer getCODERROR() {
		return CODERROR;
	}

	public void setCODERROR(Integer cODERROR) {
		CODERROR = cODERROR;
	}

	public String getMSJERROR() {
		return MSJERROR;
	}

	public void setMSJERROR(String mSJERROR) {
		MSJERROR = mSJERROR;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

}