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
@Table ( name="thestnom", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thestnom implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="thestnom_id", unique=true, nullable=false)
		@NotNull
		private Integer thestnomId;
		
	
	    
					@Column(name="thestnom_descripcion"   )
		private String thestnomDescripcion;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="thestnom")
		private List<Thnome> thnomes = new ArrayList<>();	
    	@OneToMany(fetch=FetchType.LAZY, mappedBy="thestnom")
		private List<Thtracking> thtrackings = new ArrayList<>();	
        
}	