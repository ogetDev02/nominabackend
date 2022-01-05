package com.oget.ogetpro.controller;

import com.oget.ogetpro.model.*;
import com.oget.ogetpro.dto.Thvida2DTO;
import com.oget.ogetpro.mappe.Thvida2Mapper;
import com.oget.ogetpro.service.Thvida2Service;

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
@RequestMapping("/thvida2")
@CrossOrigin(origins = "*")
@Slf4j
public class Thvida2RestController {
    @Autowired
    private Thvida2Service thvida2Service;
    @Autowired
    private Thvida2Mapper thvida2Mapper;

    @GetMapping(value = "/{thvida2Id}")
    public ResponseEntity<?> findById(
        @PathVariable("thvida2Id")
    Integer thvida2Id) throws Exception {
        log.debug("Request to findById() Thvida2");

        Thvida2 thvida2 = (thvida2Service.findById(thvida2Id).isPresent() == true)
            ? thvida2Service.findById(thvida2Id).get() : null;

        return ResponseEntity.ok()
                             .body(thvida2Mapper.thvida2ToThvida2DTO(thvida2));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Thvida2");

        return ResponseEntity.ok()
                             .body(thvida2Mapper.listThvida2ToListThvida2DTO(
                thvida2Service.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    Thvida2DTO thvida2DTO) throws Exception {
        log.debug("Request to save Thvida2: {}", thvida2DTO);

        Thvida2 thvida2 = thvida2Mapper.thvida2DTOToThvida2(thvida2DTO);
        thvida2 = thvida2Service.save(thvida2);

        return ResponseEntity.ok()
                             .body(thvida2Mapper.thvida2ToThvida2DTO(thvida2));
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid
    @RequestBody
    Thvida2DTO thvida2DTO) throws Exception {
        log.debug("Request to update Thvida2: {}", thvida2DTO);

        Thvida2 thvida2 = thvida2Mapper.thvida2DTOToThvida2(thvida2DTO);
        thvida2 = thvida2Service.update(thvida2);

        return ResponseEntity.ok()
                             .body(thvida2Mapper.thvida2ToThvida2DTO(thvida2));
    }

    @DeleteMapping(value = "/{thvida2Id}")
    public ResponseEntity<?> delete(@PathVariable("thvida2Id")
    Integer thvida2Id) throws Exception {
        log.debug("Request to delete Thvida2");

        thvida2Service.deleteById(thvida2Id);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(thvida2Service.count());
    }
}
