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
public class Thvida1DTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer thciudadIdExp;
    private Integer thdocideId;
    private String thvida1Apellido1;
    private String thvida1Apellido2;
    private String thvida1Barrio;
    private Integer thvida1CiudadNac;
    private Integer thvida1CiudadResid;
    private String thvida1Direccion;
    private String thvida1Docide;
    private String thvida1Email;
    private String thvida1EstadoCivil;
    private Date thvida1Fecnac;
    private String thvida1Grusan;
    @NotNull
    private Integer thvida1Id;
    private String thvida1K2Codhv;
    private String thvida1K3Nrodoc;
    private String thvida1K4Nombre;
    private String thvida1Nombres;
    private String thvida1Rh;
    private String thvida1Sexo;
    private String thvida1Telefono;
    private String thvida1ZonaPostal;
    private String thvidaFoto;
}
