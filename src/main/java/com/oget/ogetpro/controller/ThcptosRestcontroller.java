package com.oget.ogetpro.controller;

import java.util.List;

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

import com.oget.ogetpro.dto.ThcptosDTO;
import com.oget.ogetpro.mappe.ThcptosMapper;
import com.oget.ogetpro.model.Thcptos;
import com.oget.ogetpro.service.ThcptosService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/thcptos")
@CrossOrigin(origins = "*")
@Slf4j
public class ThcptosRestcontroller {
	
	@Autowired
	private ThcptosService services;

	@Autowired
	private ThcptosMapper delegate;
	  @GetMapping(value = "/{thcptosId}")
	    public ResponseEntity<?> findById(
	        @PathVariable("thcptosId")
	    Integer thcptosId) throws Exception {
	        log.debug("Request to findById() Thcptos");

	        Thcptos thcptos = (services.findById(thcptosId).isPresent() == true)
	            ? services.findById(thcptosId).get() : null;

	        return ResponseEntity.ok()
	                             .body(delegate.thcptosToThcptosDTO(thcptos));
	    }

	    @GetMapping()
	    public ResponseEntity<?> findAll() throws Exception {
	        log.debug("Request to findAll() Thcptos");

	        return ResponseEntity.ok()
	                             .body(delegate.listThcptosToListThcptosDTO(
	                services.findAll()));
	    }

	    @PostMapping()
	    public ResponseEntity<?> save(
	    @RequestBody
	    ThcptosDTO thcptosDTO) throws Exception {
	        log.debug("Request to save Thcptos: {}", thcptosDTO);

	        Thcptos thcptos = delegate.thcptosDTOToThcptos(thcptosDTO);
	        thcptos = services.save(thcptos);

	        return ResponseEntity.ok()
	                             .body(delegate.thcptosToThcptosDTO(thcptos));
	    }

	    @PutMapping()
	    public ResponseEntity<?> update(
	    @RequestBody
	    ThcptosDTO thcptosDTO) throws Exception {
	        log.debug("Request to update Thcptos: {}", thcptosDTO);

	        Thcptos thcptos = delegate.thcptosDTOToThcptos(thcptosDTO);
	        thcptos = services.update(thcptos);

	        return ResponseEntity.ok()
	                             .body(delegate.thcptosToThcptosDTO(thcptos));
	    }

	    @DeleteMapping(value = "/{thcptosId}")
	    public ResponseEntity<?> delete(@PathVariable("thcptosId")
	    Integer thcptosId) throws Exception {
	        log.debug("Request to delete Thcptos");

	        services.deleteById(thcptosId);

	        return ResponseEntity.ok().build();
	    }

	    @GetMapping(value = "/count")
	    public ResponseEntity<?> count() {
	        return ResponseEntity.ok().body(services.count());
	    }

}
