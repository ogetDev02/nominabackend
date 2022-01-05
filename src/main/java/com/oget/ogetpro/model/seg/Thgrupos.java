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
@Table(name = "thgrupos", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thgrupos implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "thgrupos_id", unique = true, nullable = false)
    @NotNull
    private Integer thgruposId;
    @Column(name = "thausent_k2_fecini")
    private Date thausentK2Fecini;
    @Column(name = "thausent_k2_nroreg_hv")
    private Integer thausentK2NroregHv;
    @Column(name = "thgrupos_abrev")
    private String thgruposAbrev;
    @Column(name = "thgrupos_k1_cod")
    private String thgruposK1Cod;
    @Column(name = "thgrupos_k2_desc")
    private String thgruposK2Desc;
}
