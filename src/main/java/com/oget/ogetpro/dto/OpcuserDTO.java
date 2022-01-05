package com.oget.ogetpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;

import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpcuserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer estado;
    @NotNull
    private Integer opcuserid;
    private String usuariomodificacion;
    private Integer opcidn_Opciones;
    private Integer usuarioid_Usuario;
}
