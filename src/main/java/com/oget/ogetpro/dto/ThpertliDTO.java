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
public class ThpertliDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer thpertliAnio;
    private Integer thpertliAnioAuto;
    private Integer thpertliAnioCont;
    private Date thpertliFechaFinal;
    private Date thpertliFechaInicial;
    private Date thpertliFechaPago;
    @NotNull
    private Integer thpertliId;
    private Integer thpertliK1Ano;
    private Integer thpertliK1Periodo;
    private Integer thpertliK1Tipliq;
    private Integer thpertliMes;
    private Integer thpertliMesAuto;
    private Integer thpertliMesCont;
}
