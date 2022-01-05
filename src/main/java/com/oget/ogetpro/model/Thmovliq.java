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
@Table(name = "thmovliq", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thmovliq implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "thmovliq_id", unique = true, nullable = false)
    @NotNull
    private Integer thmovliqId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thctrliq_id")
    @NotNull
    private Thctrliq thctrliq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thvida2_id")
    @NotNull
    private Thvida2 thvida2;
    @Column(name = "thausent_id")
    private Integer thausentId;
    @Column(name = "thcargos_id")
    private Integer thcargosId;
    @Column(name = "thcencos_id")
    private Integer thcencosId;
    @Column(name = "thcptos_id")
    private Integer thcptosId;
    @Column(name = "thcxcplan_id")
    private Integer thcxcplanId;
    @Column(name = "thdestajo2_id")
    private Integer thdestajo2Id;
    @Column(name = "thentext_id")
    private Integer thentextId;
    @Column(name = "thgrunom_id")
    private Integer thgrunomId;
    @Column(name = "thlifpres_id")
    private Integer thlifpresId;
    @Column(name = "thlocope_id")
    private Integer thlocopeId;
    @Column(name = "thmovliq_anio")
    private Integer thmovliqAnio;
    @Column(name = "thmovliq_base")
    private Integer thmovliqBase;
    @Column(name = "thmovliq_cantidad")
    private Double thmovliqCantidad;
    @Column(name = "thmovliq_fecfin")
    private Date thmovliqFecfin;
    @Column(name = "thmovliq_fecini")
    private Date thmovliqFecini;
    @Column(name = "thmovliq_feclog")
    private Date thmovliqFeclog;
    @Column(name = "thmovliq_hralog")
    private Date thmovliqHralog;
    @Column(name = "thmovliq_hrs")
    private Double thmovliqHrs;
    @Column(name = "thmovliq_mes")
    private Integer thmovliqMes;
    @Column(name = "thmovliq_thcptos_nat")
    private String thmovliqThcptosNat;
    @Column(name = "thmovliq_usuario")
    private String thmovliqUsuario;
    @Column(name = "thmovliq_vlr")
    private Integer thmovliqVlr;
    @Column(name = "thnovfij_id")
    private Integer thnovfijId;
    @Column(name = "thnovoca_id")
    private Integer thnovocaId;
    @Column(name = "thpocc_id")
    private Integer thpoccId;
    @Column(name = "thprovac_id")
    private Integer thprovacId;
    @Column(name = "thsocied_id")
    private Integer thsociedId;
    @Column(name = "thturhv_id")
    private Integer thturhvId;
}
