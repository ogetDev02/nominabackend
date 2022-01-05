package com.oget.ogetpro.repository.seg;

import com.oget.ogetpro.model.seg.Ciudad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
}
