package com.oget.ogetpro.repository;

import com.oget.ogetpro.model.Thnome;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface ThnomeRepository extends JpaRepository<Thnome, Integer> {
	@Modifying
	@Query(value = "INSERT INTO public.thnome(thdocide_id, thnome_prefijo, thnome_fecha_emision, thnome_nro_k3_document, thnome_nombres, thnome_total_comprobante, thestnom_id) VALUES (?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
	public void insertarGenXml(int iddoc, String prefijo, Date  fecha, String nro, String nombres, Double total, int estado);
}
