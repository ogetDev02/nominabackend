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
@Table ( name="thpertli", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thpertli implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="thpertli_id", unique=true, nullable=false)
		@NotNull
		private Integer thpertliId;
		
	
	    
					@Column(name="thpertli_anio"   )
		private Integer thpertliAnio;	
    				@Column(name="thpertli_anio_auto"   )
		private Integer thpertliAnioAuto;	
    				@Column(name="thpertli_anio_cont"   )
		private Integer thpertliAnioCont;	
    				@Column(name="thpertli_fecha_final"   )
		private Date thpertliFechaFinal;	
    				@Column(name="thpertli_fecha_inicial"   )
		private Date thpertliFechaInicial;	
    				@Column(name="thpertli_fecha_pago"   )
		private Date thpertliFechaPago;	
    				@Column(name="thpertli_k1_ano"   )
		private Integer thpertliK1Ano;	
    				@Column(name="thpertli_k1_periodo"   )
		private Integer thpertliK1Periodo;	
    				@Column(name="thpertli_k1_tipliq"   )
		private Integer thpertliK1Tipliq;	
    				@Column(name="thpertli_mes"   )
		private Integer thpertliMes;	
    				@Column(name="thpertli_mes_auto"   )
		private Integer thpertliMesAuto;	
    				@Column(name="thpertli_mes_cont"   )
		private Integer thpertliMesCont;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="thpertli")
		private List<Thctrliq> thctrliqs = new ArrayList<>();	
        
}	