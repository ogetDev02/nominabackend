package com.oget.ogetpro.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemLiquidacionDTO {
	
	private Date FECHAPERFIN;
	private Date FECHAPERINI;
	private String NOMBRES;
	private String NRODOCUMENTO;
	private String TIPODOCUMENTO;
	private String CODIGOHV;
	private Date FECHAINGRESO;
	private String CARGO;
	private String SALUD;
	private String PENSIONES;
	private String ENTIDADBANCARIA;
	private String NROCUENTA;
	private BigDecimal SALARIO;
	private Date FECHAPAGO;
	private String CODCPTO;
	private String CONCEPTO;
	private BigDecimal CANTIDAD;
	private BigDecimal VALORDEVENGO;
	private BigDecimal VALORDEDUCCION;
	private String OBSERVACIONES;
	private String CUNE;
	private String TELEMPRESA;
	private String FAXEMPRESA;
	private String CIUDADEMPRESA;
	private String PAISEMPRESA;
	private String NITEMPRESA;
	private String DIGEMPRESA;
	private String DIRECCIONEMPRESA;
	private String NOMBREEMPRESA;
	private String APARTADO;
	private String MAILEMPRESA;
	private String WEBEMPRESA;
	private String ELABORO;
	private String REVISO;

}
