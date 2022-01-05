package com.oget.ogetpro.repository.seg;

import com.oget.ogetpro.model.seg.Opcuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;



public interface OpcuserRepository extends JpaRepository<Opcuser, Integer> {
}
