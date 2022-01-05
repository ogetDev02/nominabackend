package com.oget.ogetpro.controller;

import com.oget.ogetpro.model.*;
import com.oget.ogetpro.dto.Thvida1DTO;
import com.oget.ogetpro.mappe.Thvida1Mapper;
import com.oget.ogetpro.service.Thvida1Service;

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
@RequestMapping("/thvida1")
@CrossOrigin(origins = "*")
@Slf4j
public class Thvida1RestController {
    @Autowired
    private Thvida1Service thvida1Service;
    @Autowired
    private Thvida1Mapper thvida1Mapper;

    @GetMapping(value = "/{thvida1Id}")
    public ResponseEntity<?> findById(
        @PathVariable("thvida1Id")
    Integer thvida1Id) throws Exception {
        log.debug("Request to findById() Thvida1");

        Thvida1 thvida1 = (thvida1Service.findById(thvida1Id).isPresent() == true)
            ? thvida1Service.findById(thvida1Id).get() : null;

        return ResponseEntity.ok()
                             .body(thvida1Mapper.thvida1ToThvida1DTO(thvida1));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Thvida1");

        return ResponseEntity.ok()
                             .body(thvida1Mapper.listThvida1ToListThvida1DTO(
                thvida1Service.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    Thvida1DTO thvida1DTO) throws Exception {
        log.debug("Request to save Thvida1: {}", thvida1DTO);

        Thvida1 thvida1 = thvida1Mapper.thvida1DTOToThvida1(thvida1DTO);
        thvida1 = thvida1Service.save(thvida1);

        return ResponseEntity.ok()
                             .body(thvida1Mapper.thvida1ToThvida1DTO(thvida1));
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid
    @RequestBody
    Thvida1DTO thvida1DTO) throws Exception {
        log.debug("Request to update Thvida1: {}", thvida1DTO);

        Thvida1 thvida1 = thvida1Mapper.thvida1DTOToThvida1(thvida1DTO);
        thvida1 = thvida1Service.update(thvida1);

        return ResponseEntity.ok()
                             .body(thvida1Mapper.thvida1ToThvida1DTO(thvida1));
    }

    @DeleteMapping(value = "/{thvida1Id}")
    public ResponseEntity<?> delete(@PathVariable("thvida1Id")
    Integer thvida1Id) throws Exception {
        log.debug("Request to delete Thvida1");

        thvida1Service.deleteById(thvida1Id);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(thvida1Service.count());
    }
}
