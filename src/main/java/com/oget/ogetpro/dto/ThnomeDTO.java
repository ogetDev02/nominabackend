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
public class ThnomeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String thnomeCune;
    private Date thnomeFechaEmision;
    private Integer thnomeId;
    private String thestnom_descripcion;
    @NotNull
    private String thnomeNombres;
    private String thnomeNroK3Document;
    private String thnomePrefijo;
    private Double thnomeTotalComprobante;
    private String thnomeXfile;
    private String thnomeZip;
    private Integer thdocideId_Thdocide;
    private Integer thestnomId_Thestnom;
}
