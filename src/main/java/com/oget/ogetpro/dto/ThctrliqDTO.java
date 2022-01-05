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
public class ThctrliqDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer thcargosId;
    private Integer thcencosId;
    private Integer thcriterId;
    private String thctrliqEstado;
    private Date thctrliqFechaFinPromedio;
    private Date thctrliqFechaLiqPrestacion;
    @NotNull
    private Integer thctrliqId;
    private String thctrliqObserva;
    private Integer thctrliqTiano;
    private Integer thgrunomId;
    private Integer thlocopeId;
    private Integer thsociedId;
    private Integer thtipliqId;
    private Integer thpertliId_Thpertli;
    private Integer thtipproId_Thtippro;
}
