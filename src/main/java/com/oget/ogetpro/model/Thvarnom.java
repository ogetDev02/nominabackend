package com.oget.ogetpro.model;

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
@Table(name = "thvarnom", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thvarnom implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "thvarnom_id", unique = true, nullable = false)
    @GeneratedValue (strategy  = GenerationType.IDENTITY)
    private Integer thvarnomId;
    @Column(name = "thvarnom_desc")
    private String thvarnomDesc;
    @Column(name = "thvarnom_estado")
    private Integer thvarnomEstado;
    @Column(name = "thvarnom_llave")
    private String thvarnomLlave;
    @Column(name = "thvarnom_valor")
    private Double thvarnomValor;
}
