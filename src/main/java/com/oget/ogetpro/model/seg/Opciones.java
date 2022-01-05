package com.oget.ogetpro.model.seg;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org/
* www.zathuracode.org
* 
*/
@Entity
@Table ( name="opciones", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Opciones implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="opcidn", unique=true, nullable=false)
		@NotNull
		private Integer opcidn;
		
	
			@ManyToOne(fetch=FetchType.LAZY)
   		@JoinColumn(name="menuidn"  	 )
		@NotNull
		private Menus menus;	
        
					@Column(name="estado"   )
		private Integer estado;	
    				@Column(name="opcdescorv"   )
		private String opcdescorv;	
    				@Column(name="opcdescripv"   )
		private String opcdescripv;	
    				@Column(name="opcenlacev"   )
		private String opcenlacev;	
    				@Column(name="posicion"   )
		private Integer posicion;	
    				@Column(name="usuariomodificacion"   )
		private String usuariomodificacion;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="opciones")
		private List<Opcrole> opcroles = new ArrayList<>();	
    	@OneToMany(fetch=FetchType.LAZY, mappedBy="opciones")
		private List<Opcuser> opcusers = new ArrayList<>();	
        
}	