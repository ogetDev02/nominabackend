package com.oget.ogetpro.controller;

import com.oget.ogetpro.model.*;
import com.oget.ogetpro.dto.ThestnomDTO;
import com.oget.ogetpro.mappe.ThestnomMapper;
import com.oget.ogetpro.service.ThestnomService;

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


@RestController
@RequestMapping("/api/v1/thestnom")
@CrossOrigin(origins = "*")
@Slf4j
public class ThestnomRestController {
    @Autowired
    private ThestnomService thestnomService;
    @Autowired
    private ThestnomMapper thestnomMapper;

    @GetMapping(value = "/{thestnomId}")
    public ResponseEntity<?> findById(
        @PathVariable("thestnomId")
    Integer thestnomId) throws Exception {
        log.debug("Request to findById() Thestnom");

        Thestnom thestnom = (thestnomService.findById(thestnomId).isPresent() == true)
            ? thestnomService.findById(thestnomId).get() : null;

        return ResponseEntity.ok()
                             .body(thestnomMapper.thestnomToThestnomDTO(
                thestnom));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Thestnom");

        return ResponseEntity.ok()
                             .body(thestnomMapper.listThestnomToListThestnomDTO(
                thestnomService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    ThestnomDTO thestnomDTO) throws Exception {
        log.debug("Request to save Thestnom: {}", thestnomDTO);

        Thestnom thestnom = thestnomMapper.thestnomDTOToThestnom(thestnomDTO);
        thestnom = thestnomService.save(thestnom);

        return ResponseEntity.ok()
                             .body(thestnomMapper.thestnomToThestnomDTO(
                thestnom));
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid
    @RequestBody
    ThestnomDTO thestnomDTO) throws Exception {
        log.debug("Request to update Thestnom: {}", thestnomDTO);

        Thestnom thestnom = thestnomMapper.thestnomDTOToThestnom(thestnomDTO);
        thestnom = thestnomService.update(thestnom);

        return ResponseEntity.ok()
                             .body(thestnomMapper.thestnomToThestnomDTO(
                thestnom));
    }

    @DeleteMapping(value = "/{thestnomId}")
    public ResponseEntity<?> delete(
        @PathVariable("thestnomId")
    Integer thestnomId) throws Exception {
        log.debug("Request to delete Thestnom");

        thestnomService.deleteById(thestnomId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(thestnomService.count());
    }
}
