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
public class RolesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer estado;
    private String roledesc;
    private String roledesclarga;
    @NotNull
    private Integer roleid;
    private Integer tiempoexpira;
    private String usuariomodificacion;
}
