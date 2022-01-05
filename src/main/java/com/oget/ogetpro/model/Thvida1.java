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
@Table ( name="thvida1", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thvida1 implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="thvida1_id", unique=true, nullable=false)
		@NotNull
		private Integer thvida1Id;
		
	
	    
					@Column(name="thciudad_id_exp"   )
		private Integer thciudadIdExp;	
    				@Column(name="thdocide_id"   )
		private Integer thdocideId;	
    				@Column(name="thvida1_apellido1"   )
		private String thvida1Apellido1;	
    				@Column(name="thvida1_apellido2"   )
		private String thvida1Apellido2;	
    				@Column(name="thvida1_barrio"   )
		private String thvida1Barrio;	
    				@Column(name="thvida1_ciudad_nac"   )
		private Integer thvida1CiudadNac;	
    				@Column(name="thvida1_ciudad_resid"   )
		private Integer thvida1CiudadResid;	
    				@Column(name="thvida1_direccion"   )
		private String thvida1Direccion;	
    				@Column(name="thvida1_docide"   )
		private String thvida1Docide;	
    				@Column(name="thvida1_email"   )
		private String thvida1Email;	
    				@Column(name="thvida1_estado_civil"   )
		private String thvida1EstadoCivil;	
    				@Column(name="thvida1_fecnac"   )
		private Date thvida1Fecnac;	
    				@Column(name="thvida1_grusan"   )
		private String thvida1Grusan;	
    				@Column(name="thvida1_k2_codhv"   )
		private String thvida1K2Codhv;	
    				@Column(name="thvida1_k3_nrodoc"   )
		private String thvida1K3Nrodoc;	
    				@Column(name="thvida1_k4_nombre"   )
		private String thvida1K4Nombre;	
    				@Column(name="thvida1_nombres"   )
		private String thvida1Nombres;	
    				@Column(name="thvida1_rh"   )
		private String thvida1Rh;	
    				@Column(name="thvida1_sexo"   )
		private String thvida1Sexo;	
    				@Column(name="thvida1_telefono"   )
		private String thvida1Telefono;	
    				@Column(name="thvida1_zona_postal"   )
		private String thvida1ZonaPostal;	
    				@Column(name="thvida_foto"   )
		private String thvidaFoto;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="thvida1")
		private List<Thvida2> thvida2s = new ArrayList<>();	
        
}	