package com.oget.ogetpro.repository.seg;

import com.oget.ogetpro.model.seg.Departamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;



public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
}
