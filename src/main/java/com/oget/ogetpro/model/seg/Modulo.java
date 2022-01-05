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
@Table ( name="modulo", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Modulo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="moduloid", unique=true, nullable=false)
		@NotNull
		private Integer moduloid;
		
	
	    
					@Column(name="descripcion"   )
		private String descripcion;	
    				@Column(name="posicion"   )
		private Integer posicion;	
    				@Column(name="variable"   )
		private String variable;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="modulo")
		private List<Menus> menuses = new ArrayList<>();	
        
}	