package com.oget.ogetpro.controller;

import com.oget.ogetpro.model.Thtippro;
import com.oget.ogetpro.dto.ThtipproDTO;
import com.oget.ogetpro.mappe.ThtipproMapper;
import com.oget.ogetpro.service.ThtipproService;

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
@RequestMapping("/thtippro")
@CrossOrigin(origins = "*")
@Slf4j
public class ThtipproRestController {
    @Autowired
    private ThtipproService thtipproService;
    @Autowired
    private ThtipproMapper thtipproMapper;

    @GetMapping(value = "/{thtipproId}")
    public ResponseEntity<?> findById(
        @PathVariable("thtipproId")
    Integer thtipproId) throws Exception {
        log.debug("Request to findById() Thtippro");

        Thtippro thtippro = (thtipproService.findById(thtipproId).isPresent() == true)
            ? thtipproService.findById(thtipproId).get() : null;

        return ResponseEntity.ok()
                             .body(thtipproMapper.thtipproToThtipproDTO(
                thtippro));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Thtippro");

        return ResponseEntity.ok()
                             .body(thtipproMapper.listThtipproToListThtipproDTO(
                thtipproService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    ThtipproDTO thtipproDTO) throws Exception {
        log.debug("Request to save Thtippro: {}", thtipproDTO);

        Thtippro thtippro = thtipproMapper.thtipproDTOToThtippro(thtipproDTO);
        thtippro = thtipproService.save(thtippro);

        return ResponseEntity.ok()
                             .body(thtipproMapper.thtipproToThtipproDTO(
                thtippro));
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid
    @RequestBody
    ThtipproDTO thtipproDTO) throws Exception {
        log.debug("Request to update Thtippro: {}", thtipproDTO);

        Thtippro thtippro = thtipproMapper.thtipproDTOToThtippro(thtipproDTO);
        thtippro = thtipproService.update(thtippro);

        return ResponseEntity.ok()
                             .body(thtipproMapper.thtipproToThtipproDTO(
                thtippro));
    }

    @DeleteMapping(value = "/{thtipproId}")
    public ResponseEntity<?> delete(
        @PathVariable("thtipproId")
    Integer thtipproId) throws Exception {
        log.debug("Request to delete Thtippro");

        thtipproService.deleteById(thtipproId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(thtipproService.count());
    }
}
