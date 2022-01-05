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
@Table ( name="roles", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="roleid", unique=true, nullable=false)
		@NotNull
		private Integer roleid;
		
	
	    
					@Column(name="estado"   )
		private Integer estado;	
    				@Column(name="roledesc"   )
		private String roledesc;	
    				@Column(name="roledesclarga"   )
		private String roledesclarga;	
    				@Column(name="tiempoexpira"   )
		private Integer tiempoexpira;	
    				@Column(name="usuariomodificacion"   )
		private String usuariomodificacion;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="roles")
		private List<Opcrole> opcroles = new ArrayList<>();	
    	@OneToMany(fetch=FetchType.LAZY, mappedBy="roles")
		private List<Rolxusuario> rolxusuarios = new ArrayList<>();	
    	@OneToMany(fetch=FetchType.LAZY, mappedBy="roles")
		private List<Usuario> usuarios = new ArrayList<>();	
        
}	