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
@Table(name = "thtracking", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thtracking implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "thtracking_id", unique = true, nullable = false)
    @GeneratedValue (strategy  = GenerationType.IDENTITY)
    private Integer thtrackingId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thestnom_id")
    @NotNull
    private Thestnom thestnom;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thnome_id")
    @NotNull
    private Thnome thnome;
    @Column(name = "thtracking_fecha")
    private Date thtrackingFecha;
}
