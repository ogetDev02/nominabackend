package com.oget.ogetpro.controller;

import com.oget.ogetpro.model.*;
import com.oget.ogetpro.dto.ThvarnomDTO;
import com.oget.ogetpro.mappe.ThvarnomMapper;
import com.oget.ogetpro.service.ThvarnomService;

import lombok.extern.slf4j.Slf4j;

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

import javax.validation.Valid;


/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org
* www.zathuracode.org
*
*/
@RestController
@RequestMapping("/api/v1/thvarnom")
@CrossOrigin(origins = "*")
@Slf4j
public class ThvarnomRestController {
    @Autowired
    private ThvarnomService thvarnomService;
    @Autowired
    private ThvarnomMapper thvarnomMapper;

    @GetMapping(value = "/{thvarnomId}")
    public ResponseEntity<?> findById(
        @PathVariable("thvarnomId")
    Integer thvarnomId) throws Exception {
        log.debug("Request to findById() Thvarnom");

        Thvarnom thvarnom = (thvarnomService.findById(thvarnomId).isPresent() == true)
            ? thvarnomService.findById(thvarnomId).get() : null;

        return ResponseEntity.ok()
                             .body(thvarnomMapper.thvarnomToThvarnomDTO(
                thvarnom));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Thvarnom");

        return ResponseEntity.ok()
                             .body(thvarnomMapper.listThvarnomToListThvarnomDTO(
                thvarnomService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    ThvarnomDTO thvarnomDTO) throws Exception {
        log.debug("Request to save Thvarnom: {}", thvarnomDTO);

        Thvarnom thvarnom = thvarnomMapper.thvarnomDTOToThvarnom(thvarnomDTO);
        thvarnom = thvarnomService.save(thvarnom);

        return ResponseEntity.ok()
                             .body(thvarnomMapper.thvarnomToThvarnomDTO(
                thvarnom));
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid
    @RequestBody
    ThvarnomDTO thvarnomDTO) throws Exception {
        log.debug("Request to update Thvarnom: {}", thvarnomDTO);

        Thvarnom thvarnom = thvarnomMapper.thvarnomDTOToThvarnom(thvarnomDTO);
        thvarnom = thvarnomService.update(thvarnom);

        return ResponseEntity.ok()
                             .body(thvarnomMapper.thvarnomToThvarnomDTO(
                thvarnom));
    }

    @DeleteMapping(value = "/{thvarnomId}")
    public ResponseEntity<?> delete(
        @PathVariable("thvarnomId")
    Integer thvarnomId) throws Exception {
        log.debug("Request to delete Thvarnom");

        thvarnomService.deleteById(thvarnomId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(thvarnomService.count());
    }
}
