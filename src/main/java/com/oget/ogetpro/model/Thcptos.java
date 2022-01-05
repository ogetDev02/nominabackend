package com.oget.ogetpro.model;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;

import javax.persistence.*;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table ( name="thcptos", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thcptos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="thcptos_id", unique=true, nullable=false)
		@NotNull
		private Integer thcptosId;
		
	
	    
					@Column(name="thalgori_id"   )
		private Integer thalgoriId;	
    				@Column(name="thcptos_abrev"   )
		private String thcptosAbrev;	
    				@Column(name="thcptos_agru_base"   )
		private String thcptosAgruBase;	
    				@Column(name="thcptos_caract_liq"   )
		private Integer thcptosCaractLiq;	
    				@Column(name="thcptos_dias_factor"   )
		private Integer thcptosDiasFactor;	
    				@Column(name="thcptos_dias_pres"   )
		private Integer thcptosDiasPres;	
    				@Column(name="thcptos_ind_basico"   )
		private Integer thcptosIndBasico;	
    				@Column(name="thcptos_k1_cod"   )
		private Integer thcptosK1Cod;	
    				@Column(name="thcptos_k2_desc"   )
		private String thcptosK2Desc;	
    				@Column(name="thcptos_naturaleza"   )
		private String thcptosNaturaleza;	
    				@Column(name="thcptos_porc"   )
		private Double thcptosPorc;	
    				@Column(name="thcptos_vlr_unt"   )
		private Double thcptosVlrUnt;	
    				@Column(name="thgrupos_id"   )
		private Integer thgruposId;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="thcptos")
		private List<Thcptosdian> thcptosdians = new ArrayList<>();	
        
}	