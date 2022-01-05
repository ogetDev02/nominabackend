package com.oget.ogetpro.model;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table ( name="thdocide", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thdocide implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="thdocide_id", unique=true, nullable=false)
		@NotNull
		private Integer thdocideId;
		
	
	    
					@Column(name="thdocide_abrev"   )
		private String thdocideAbrev;	
    				@Column(name="thdocide_desc"   )
		private String thdocideDesc;	
    				@Column(name="thdocide_k1_cod"   )
		private String thdocideK1Cod;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="thdocide")
		private List<Thnome> thnomes = new ArrayList<>();	
        
}	