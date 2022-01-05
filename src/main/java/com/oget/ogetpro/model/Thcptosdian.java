package com.oget.ogetpro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


import com.sun.istack.NotNull;


@Entity
@Table(name = "thcptosdian", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thcptosdian implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "thcptosdianid", unique = true, nullable = false)
    @NotNull
    private Integer thcptosdianid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thcptos_id")
    @NotNull
    private Thcptos thcptos;
    @Column(name = "thcptosdian_descripcion")
    private String thcptosdianDescripcion;
    @Column(name = "thcptosdian_id_dian")
    private String thcptosdianIdDian;
    @Column(name = "thcptosdian_xpath")
    private String thcptosdianXpath;
}
