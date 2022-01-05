package com.oget.ogetpro.model.seg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
@Entity
@Table(name = "parametros", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parametros implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "parametroid", unique = true, nullable = false)
    @NotNull
    private Integer parametroid;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "llave")
    private String llave;
    @Column(name = "roledesclarga")
    private String roledesclarga;
    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;
    @Column(name = "valor")
    private String valor;
}
