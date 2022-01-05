package com.oget.ogetpro.repository.seg;

import com.oget.ogetpro.model.seg.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
