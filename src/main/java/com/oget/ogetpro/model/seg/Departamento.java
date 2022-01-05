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
@Table ( name="departamento", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departamento implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="departamentoid", unique=true, nullable=false)
		@NotNull
		private Integer departamentoid;
		
	
	    
					@Column(name="coddian"   )
		private String coddian;	
    					@NotNull
						@NotEmpty
			@Size(max=100)
							@Column(name="nombre"  , nullable=false  )
		private String nombre;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="departamento")
		private List<Ciudad> ciudads = new ArrayList<>();	
        
}	