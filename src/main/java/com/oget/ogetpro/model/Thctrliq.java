package com.oget.ogetpro.model;

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
@Table ( name="thctrliq", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thctrliq implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="thctrliq_id", unique=true, nullable=false)
		@NotNull
		private Integer thctrliqId;
		
	
			@ManyToOne(fetch=FetchType.LAZY)
   		@JoinColumn(name="thpertli_id"  	 )
		@NotNull
		private Thpertli thpertli;	
    	//@ManyToOne(fetch=FetchType.LAZY)
   		//@JoinColumn(name="thtippro_id"  	 )
		//@NotNull
		private Thtippro thtipproByThtipproId;	
    	//@ManyToOne(fetch=FetchType.LAZY)
   		//@JoinColumn(name="thtippro_id"  	 )
		//@NotNull
		private Thtippro thtipproByThtipproSecId;	
        
					@Column(name="thcargos_id"   )
		private Integer thcargosId;	
    				@Column(name="thcencos_id"   )
		private Integer thcencosId;	
    				@Column(name="thcriter_id"   )
		private Integer thcriterId;	
    				@Column(name="thctrliq_estado"   )
		private String thctrliqEstado;	
    				@Column(name="thctrliq_fecha_fin_promedio"   )
		private Date thctrliqFechaFinPromedio;	
    				@Column(name="thctrliq_fecha_liq_prestacion"   )
		private Date thctrliqFechaLiqPrestacion;	
    				@Column(name="thctrliq_observa"   )
		private String thctrliqObserva;	
    				@Column(name="thctrliq_tiano"   )
		private Integer thctrliqTiano;	
    				@Column(name="thgrunom_id"   )
		private Integer thgrunomId;	
    				@Column(name="thlocope_id"   )
		private Integer thlocopeId;	
    				@Column(name="thsocied_id"   )
		private Integer thsociedId;	
    				@Column(name="thtipliq_id"   )
		private Integer thtipliqId;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="thctrliq")
		private List<Thmovliq> thmovliqs = new ArrayList<>();	
        
}	