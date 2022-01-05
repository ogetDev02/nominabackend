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
@Table(name = "ciudad", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ciudad implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ciudadid", unique = true, nullable = false)
    @NotNull
    private Integer ciudadid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamentoid")
    @NotNull
    private Departamento departamento;
    @Column(name = "coddian")
    private String coddian;
    @NotNull
    @NotEmpty
    @Size(max = 100)
    @Column(name = "nombre", nullable = false)
    private String nombre;
}
