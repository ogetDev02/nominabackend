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
@Table ( name="thtippro", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thtippro implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="thtippro_id", unique=true, nullable=false)
		@NotNull
		private Integer thtipproId;
		
	
	    
					@Column(name="thtippro_abrev"   )
		private String thtipproAbrev;	
    				@Column(name="thtippro_k2_desc"   )
		private String thtipproK2Desc;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="thtipproByThtipproId")
		private List<Thctrliq> thctrliqsForThtipproId = new ArrayList<>();	
    	@OneToMany(fetch=FetchType.LAZY, mappedBy="thtipproByThtipproSecId")
		private List<Thctrliq> thctrliqsForThtipproSecId = new ArrayList<>();	
        
}	