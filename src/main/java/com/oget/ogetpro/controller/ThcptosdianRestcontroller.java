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

import com.oget.ogetpro.dto.ThcptosdianDTO;
import com.oget.ogetpro.mappe.ThcptosdianMapper;
import com.oget.ogetpro.model.Thcptosdian;
import com.oget.ogetpro.service.ThcptosdianService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/thcptosdian")
@CrossOrigin(origins = "*")
@Slf4j
public class ThcptosdianRestcontroller {
	
	@Autowired
	private ThcptosdianService services;
	
	@Autowired
	private ThcptosdianMapper delegate;
	
	 @GetMapping(value = "/{thcptosdianid}")
	    public ResponseEntity<?> findById(
	        @PathVariable("thcptosdianid")
	    Integer thcptosdianid) throws Exception {
	        log.debug("Request to findById() Thcptosdian");

	        Thcptosdian thcptosdian = (services.findById(thcptosdianid)
	                                                     .isPresent() == true)
	            ? services.findById(thcptosdianid).get() : null;

	        return ResponseEntity.ok()
	                             .body(delegate.thcptosdianToThcptosdianDTO(
	                thcptosdian));
	    }
	    
	    @GetMapping()
	    public ResponseEntity<?> findAll() throws Exception {
	        log.debug("Request to findAll() Thcptosdian");

	        return ResponseEntity.ok()
	                             .body(delegate.listThcptosdianToListThcptosdianDTO(
	                services.findAll()));
	    }

	    @PostMapping()
	    public ResponseEntity<?> save(
	    @RequestBody
	    ThcptosdianDTO thcptosdianDTO) throws Exception {
	        log.debug("Request to save Thcptosdian: {}", thcptosdianDTO);

	        Thcptosdian thcptosdian = delegate.thcptosdianDTOToThcptosdian(thcptosdianDTO);
	        thcptosdian = services.save(thcptosdian);

	        return ResponseEntity.ok()
	                             .body(delegate.thcptosdianToThcptosdianDTO(
	                thcptosdian));
	    }

	    @PutMapping()
	    public ResponseEntity<?> update(
	    @RequestBody
	    ThcptosdianDTO thcptosdianDTO) throws Exception {
	        log.debug("Request to update Thcptosdian: {}", thcptosdianDTO);

	        Thcptosdian thcptosdian = delegate.thcptosdianDTOToThcptosdian(thcptosdianDTO);
	        thcptosdian = services.update(thcptosdian);

	        return ResponseEntity.ok()
	                             .body(delegate.thcptosdianToThcptosdianDTO(
	                thcptosdian));
	    }

	    @DeleteMapping(value = "/{thcptosdianid}")
	    public ResponseEntity<?> delete(
	        @PathVariable("thcptosdianid")
	    Integer thcptosdianid) throws Exception {
	        log.debug("Request to delete Thcptosdian");

	        services.deleteById(thcptosdianid);

	        return ResponseEntity.ok().build();
	    }

	    @GetMapping(value = "/count")
	    public ResponseEntity<?> count() {
	        return ResponseEntity.ok().body(services.count());
	    }

}
