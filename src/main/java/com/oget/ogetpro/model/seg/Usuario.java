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
@Table ( name="usuario", schema="public" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;


			@Id
	    @Column(name="usuarioid", unique=true, nullable=false)
		@NotNull
		private Integer usuarioid;
		
	
			@ManyToOne(fetch=FetchType.LAZY)
   		@JoinColumn(name="roleid"  	 )
		@NotNull
		private Roles roles;	
        
					@Column(name="estado"   )
		private Integer estado;	
    				@Column(name="fechamodificacion"   )
		private Date fechamodificacion;	
    				@Column(name="logo"   )
		private String logo;	
    				@Column(name="usrauthnoldap"   )
		private Integer usrauthnoldap;	
    				@Column(name="usrlogin"   )
		private String usrlogin;	
    				@Column(name="usrmail"   )
		private String usrmail;	
    				@Column(name="usrnit"   )
		private String usrnit;	
    				@Column(name="usrnombre"   )
		private String usrnombre;	
    				@Column(name="usrpwd"   )
		private String usrpwd;	
    				@Column(name="usuariomodificacion"   )
		private String usuariomodificacion;	
        
		@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
		private List<Opcuser> opcusers = new ArrayList<>();	
    	@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
		private List<Rolxusuario> rolxusuarios = new ArrayList<>();	
        
}	