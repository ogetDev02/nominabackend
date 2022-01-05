package com.oget.ogetpro.controller;

import com.oget.ogetpro.model.*;
import com.oget.ogetpro.dto.ThdocideDTO;
import com.oget.ogetpro.mappe.ThdocideMapper;
import com.oget.ogetpro.service.ThdocideService;

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
@RequestMapping("/api/v1/thdocide")
@CrossOrigin(origins = "*")
@Slf4j
public class ThdocideRestController {
    @Autowired
    private ThdocideService thdocideService;
    @Autowired
    private ThdocideMapper thdocideMapper;

    @GetMapping(value = "/{thdocideId}")
    public ResponseEntity<?> findById(
        @PathVariable("thdocideId")
    Integer thdocideId) throws Exception {
        log.debug("Request to findById() Thdocide");

        Thdocide thdocide = (thdocideService.findById(thdocideId).isPresent() == true)
            ? thdocideService.findById(thdocideId).get() : null;

        return ResponseEntity.ok()
                             .body(thdocideMapper.thdocideToThdocideDTO(
                thdocide));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Thdocide");

        return ResponseEntity.ok()
                             .body(thdocideMapper.listThdocideToListThdocideDTO(
                thdocideService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    ThdocideDTO thdocideDTO) throws Exception {
        log.debug("Request to save Thdocide: {}", thdocideDTO);

        Thdocide thdocide = thdocideMapper.thdocideDTOToThdocide(thdocideDTO);
        thdocide = thdocideService.save(thdocide);

        return ResponseEntity.ok()
                             .body(thdocideMapper.thdocideToThdocideDTO(
                thdocide));
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid
    @RequestBody
    ThdocideDTO thdocideDTO) throws Exception {
        log.debug("Request to update Thdocide: {}", thdocideDTO);

        Thdocide thdocide = thdocideMapper.thdocideDTOToThdocide(thdocideDTO);
        thdocide = thdocideService.update(thdocide);

        return ResponseEntity.ok()
                             .body(thdocideMapper.thdocideToThdocideDTO(
                thdocide));
    }

    @DeleteMapping(value = "/{thdocideId}")
    public ResponseEntity<?> delete(
        @PathVariable("thdocideId")
    Integer thdocideId) throws Exception {
        log.debug("Request to delete Thdocide");

        thdocideService.deleteById(thdocideId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(thdocideService.count());
    }
}
