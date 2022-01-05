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
@Table ( name="thvida2", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thvida2 implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="thvida2_id", unique=true, nullable=false)
		@NotNull
		private Integer thvida2Id;
		
	
			@ManyToOne(fetch=FetchType.LAZY)
   		@JoinColumn(name="thvida1_id"  	 )
		@NotNull
		private Thvida1 thvida1;	
        
						@NotNull
							@Column(name="thcargos_id"  , nullable=false  )
		private Integer thcargosId;	
    					@NotNull
							@Column(name="thcencos_id"  , nullable=false  )
		private Integer thcencosId;	
    					@NotNull
							@Column(name="thgrunom_id"  , nullable=false  )
		private Integer thgrunomId;	
    				@Column(name="thgrupres_id"   )
		private Integer thgrupresId;	
    					@NotNull
							@Column(name="thlocope_id"  , nullable=false  )
		private Integer thlocopeId;	
    					@NotNull
							@Column(name="thsocied_id"  , nullable=false  )
		private Integer thsociedId;	
    					@NotNull
							@Column(name="thtipliq_id"  , nullable=false  )
		private Integer thtipliqId;	
    				@Column(name="thvida2_estado"   )
		private String thvida2Estado;	
    				@Column(name="thvida2_fecha_hasta"   )
		private Date thvida2FechaHasta;	
    				@Column(name="thvida2_fecha_inicio"   )
		private Date thvida2FechaInicio;	
    				@Column(name="thvida2_fecha_retiro"   )
		private Date thvida2FechaRetiro;	
    				@Column(name="thvida2_fecha_ultaum"   )
		private Date thvida2FechaUltaum;	
    				@Column(name="thvida2_fecha_ulting"   )
		private Date thvida2FechaUlting;	
    				@Column(name="thvida2_fecult_retiro"   )
		private Date thvida2FecultRetiro;	
    				@Column(name="thvida2_hrs_remun"   )
		private Double thvida2HrsRemun;	
    				@Column(name="thvida2_ind_pago"   )
		private Integer thvida2IndPago;	
    				@Column(name="thvida2_porc_tiempo"   )
		private Double thvida2PorcTiempo;	
    				@Column(name="thvida2_vlr_remuneracion"   )
		private Double thvida2VlrRemuneracion;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="thvida2")
		private List<Thmovliq> thmovliqs = new ArrayList<>();	
        
}	