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
@Table(name = "opcrole", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Opcrole implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "opcroleidn", unique = true, nullable = false)
    @NotNull
    private Integer opcroleidn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opcidn")
    @NotNull
    private Opciones opciones;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleid")
    @NotNull
    private Roles roles;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;
}
