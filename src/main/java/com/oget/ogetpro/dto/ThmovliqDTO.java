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
public class ThmovliqDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer thausentId;
    private Integer thcargosId;
    private Integer thcencosId;
    private Integer thcptosId;
    private Integer thcxcplanId;
    private Integer thdestajo2Id;
    private Integer thentextId;
    private Integer thgrunomId;
    private Integer thlifpresId;
    private Integer thlocopeId;
    private Integer thmovliqAnio;
    private Integer thmovliqBase;
    private Double thmovliqCantidad;
    private Date thmovliqFecfin;
    private Date thmovliqFecini;
    private Date thmovliqFeclog;
    private Date thmovliqHralog;
    private Double thmovliqHrs;
    @NotNull
    private Integer thmovliqId;
    private Integer thmovliqMes;
    private String thmovliqThcptosNat;
    private String thmovliqUsuario;
    private Integer thmovliqVlr;
    private Integer thnovfijId;
    private Integer thnovocaId;
    private Integer thpoccId;
    private Integer thprovacId;
    private Integer thsociedId;
    private Integer thturhvId;
    private Integer thctrliqId_Thctrliq;
    private Integer thvida2Id_Thvida2;
}
