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
@Table(name = "diasfestivos", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diasfestivos implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "diasfestivosid", unique = true, nullable = false)
    @NotNull
    private Integer diasfestivosid;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha")
    private Date fecha;
}
