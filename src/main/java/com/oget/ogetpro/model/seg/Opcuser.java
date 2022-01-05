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
@Table(name = "opcuser", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Opcuser implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "opcuserid", unique = true, nullable = false)
    @NotNull
    private Integer opcuserid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opcidn")
    @NotNull
    private Opciones opciones;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioid")
    @NotNull
    private Usuario usuario;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;
}
