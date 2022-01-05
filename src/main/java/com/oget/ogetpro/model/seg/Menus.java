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
@Table ( name="menus", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menus implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="menuidn", unique=true, nullable=false)
		@NotNull
		private Integer menuidn;
		
	
			@ManyToOne(fetch=FetchType.LAZY)
   		@JoinColumn(name="moduloid"  	 )
		@NotNull
		private Modulo modulo;	
        
					@Column(name="estado"   )
		private Integer estado;	
    				@Column(name="mendescv"   )
		private String mendescv;	
    				@Column(name="menicon"   )
		private String menicon;	
    				@Column(name="menpath"   )
		private String menpath;	
    				@Column(name="posicion"   )
		private Integer posicion;	
    				@Column(name="usuariomodificacion"   )
		private String usuariomodificacion;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="menus")
		private List<Opciones> opcioneses = new ArrayList<>();	
        
}	