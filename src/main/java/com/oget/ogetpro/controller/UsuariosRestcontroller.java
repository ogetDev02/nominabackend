package com.oget.ogetpro.controller;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oget.ogetpro.model.seg.Usuario;
import com.oget.ogetpro.dto.UsuarioDTO;
import com.oget.ogetpro.mapper.seg.UsuarioMapper;
import com.oget.ogetpro.service.seg.UsuariosService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
@Slf4j
public class UsuariosRestcontroller {
	
	@Autowired
	private UsuariosService services;
	@Autowired
	private UsuarioMapper delegate;
	 
	@GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Usuario");

        return ResponseEntity.ok()
                             .body(delegate.listUsuarioToListUsuarioDTO(
                services.findAll()));
    }

	 @GetMapping(value = "/{usuarioid}")
	    public ResponseEntity<?> findById(
	        @PathVariable("usuarioid")
	    Integer usuarioid) throws Exception {
	        log.debug("Request to findById() Usuario");

	        Usuario usuario = (services.findById(usuarioid).isPresent() == true)
	            ? services.findById(usuarioid).get() : null;

	        return ResponseEntity.ok()
	                             .body(delegate.usuarioToUsuarioDTO(usuario));
	    }
	 
	    @PostMapping()
	    public ResponseEntity<?> save(@Valid
	    @RequestBody
	    UsuarioDTO usuarioDTO) throws Exception {
	        log.debug("Request to save Usuario: {}", usuarioDTO);

	        Usuario usuario = delegate.usuarioDTOToUsuario(usuarioDTO);
	        usuario = services.save(usuario);

	        return ResponseEntity.ok()
	                             .body(delegate.usuarioToUsuarioDTO(usuario));
	    }

	    @PutMapping()
	    public ResponseEntity<?> update(@Valid
	    @RequestBody
	    UsuarioDTO usuarioDTO) throws Exception {
	        log.debug("Request to update Usuario: {}", usuarioDTO);

	        Usuario usuario = delegate.usuarioDTOToUsuario(usuarioDTO);
	        usuario = services.update(usuario);

	        return ResponseEntity.ok()
	                             .body(delegate.usuarioToUsuarioDTO(usuario));
	    }

	    @DeleteMapping(value = "/{usuarioid}")
	    public ResponseEntity<?> delete(@PathVariable("usuarioid")
	    Integer usuarioid) throws Exception {
	        log.debug("Request to delete Usuario");

	        services.deleteById(usuarioid);

	        return ResponseEntity.ok().build();
	    }

	    @GetMapping(value = "/count")
	    public ResponseEntity<?> count() {
	        return ResponseEntity.ok().body(services.count());
	    }
}
