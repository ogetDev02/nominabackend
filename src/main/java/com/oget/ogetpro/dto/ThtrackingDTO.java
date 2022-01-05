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
public class ThtrackingDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date thtrackingFecha;
    private Integer thtrackingId;
    @NotNull
    private Integer thestnomId_Thestnom;
    private Integer thnomeId_Thnome;
}
