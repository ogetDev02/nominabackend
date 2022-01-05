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
public class ThcptosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer thalgoriId;
    private String thcptosAbrev;
    private String thcptosAgruBase;
    private Integer thcptosCaractLiq;
    private Integer thcptosDiasFactor;
    private Integer thcptosDiasPres;
    @NotNull
    private Integer thcptosId;
    private Integer thcptosIndBasico;
    private Integer thcptosK1Cod;
    private String thcptosK2Desc;
    private String thcptosNaturaleza;
    private Double thcptosPorc;
    private Double thcptosVlrUnt;
    private Integer thgruposId;
}
