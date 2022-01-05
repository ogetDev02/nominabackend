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
public class ThdocideDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String thdocideAbrev;
    private String thdocideDesc;
    @NotNull
    private Integer thdocideId;
    private String thdocideK1Cod;
}
