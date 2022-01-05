package com.oget.ogetpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import com.oget.ogetpro.model.Thcptos;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThcptosdianDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String thcptosdianDescripcion;
    private String thcptosdianIdDian;
    private String thcptosdianXpath;

    @NotNull
    private Integer thcptosdianid;
    private Integer thcptosId_Thcptos;
    private Thcptos thcptoss;
}
