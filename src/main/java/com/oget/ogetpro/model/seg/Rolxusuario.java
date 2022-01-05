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
@Table(name = "rolxusuario", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rolxusuario implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "rolxusuarioid", unique = true, nullable = false)
    @NotNull
    private Integer rolxusuarioid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleid")
    @NotNull
    private Roles roles;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioid")
    @NotNull
    private Usuario usuario;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;
}
