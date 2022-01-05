package com.oget.ogetpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer estado;
    private Date fechamodificacion;
    private String logo;
    private Integer usrauthnoldap;
    private String usrlogin;
    private String usrmail;
    private String usrnit;
    private String usrnombre;
    private String usrpwd;
    @NotNull
    private Integer usuarioid;
    private String usuariomodificacion;
    private Integer roleid_Roles;
}
