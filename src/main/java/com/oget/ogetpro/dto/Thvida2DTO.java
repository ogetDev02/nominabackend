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
public class Thvida2DTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    private Integer thcargosId;
    @NotNull
    private Integer thcencosId;
    @NotNull
    private Integer thgrunomId;
    private Integer thgrupresId;
    @NotNull
    private Integer thlocopeId;
    @NotNull
    private Integer thsociedId;
    @NotNull
    private Integer thtipliqId;
    private String thvida2Estado;
    private Date thvida2FechaHasta;
    private Date thvida2FechaInicio;
    private Date thvida2FechaRetiro;
    private Date thvida2FechaUltaum;
    private Date thvida2FechaUlting;
    private Date thvida2FecultRetiro;
    private Double thvida2HrsRemun;
    @NotNull
    private Integer thvida2Id;
    private Integer thvida2IndPago;
    private Double thvida2PorcTiempo;
    private Double thvida2VlrRemuneracion;
    private Integer thvida1Id_Thvida1;
}
