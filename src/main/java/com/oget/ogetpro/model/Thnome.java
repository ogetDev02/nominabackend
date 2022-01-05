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
@Table(name = "thnome", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thnome implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "thnome_id", unique = true, nullable = false)
    @GeneratedValue (strategy  = GenerationType.IDENTITY)
    //@NotNull
    private Integer thnomeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thdocide_id")
    @NotNull
    private Thdocide thdocide;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thestnom_id")
    @NotNull
    private Thestnom thestnom;
    @Column(name = "thnome_cune")
    private String thnomeCune;
    @Column(name = "thnome_fecha_emision")
    private Date thnomeFechaEmision;
    @Column(name = "thnome_nombres")
    private String thnomeNombres;
    @Column(name = "thnome_nro_k3_document")
    private String thnomeNroK3Document;
    @Column(name = "thnome_prefijo")
    private String thnomePrefijo;
    @Column(name = "thnome_total_comprobante")
    private Double thnomeTotalComprobante;
    @Column(name = "thnome_xfile")
    private String thnomeXfile;
    @Column(name = "thnome_zip")
    private String thnomeZip;
}
