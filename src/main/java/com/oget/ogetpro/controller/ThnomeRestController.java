package com.oget.ogetpro.controller;

import com.oget.ogetpro.model.*;
import com.oget.ogetpro.dto.ThnomeDTO;
import com.oget.ogetpro.mappe.ThnomeMapper;
import com.oget.ogetpro.service.ThnomeService;

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
@RequestMapping("/api/v1/thnome")
@CrossOrigin(origins = "*")
@Slf4j
public class ThnomeRestController {
    @Autowired
    private ThnomeService thnomeService;
    @Autowired
    private ThnomeMapper thnomeMapper;

    @GetMapping(value = "/{thnomeId}")
    public ResponseEntity<?> findById(@PathVariable("thnomeId")
    Integer thnomeId) throws Exception {
        log.debug("Request to findById() Thnome");

        Thnome thnome = (thnomeService.findById(thnomeId).isPresent() == true)
            ? thnomeService.findById(thnomeId).get() : null;

        return ResponseEntity.ok().body(thnomeMapper.thnomeToThnomeDTO(thnome));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Thnome");

        return ResponseEntity.ok()
                             .body(thnomeMapper.listThnomeToListThnomeDTO(
                thnomeService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    ThnomeDTO thnomeDTO) throws Exception {
        log.debug("Request to save Thnome: {}", thnomeDTO);

        Thnome thnome = thnomeMapper.thnomeDTOToThnome(thnomeDTO);
        thnome = thnomeService.save(thnome);

        return ResponseEntity.ok().body(thnomeMapper.thnomeToThnomeDTO(thnome));
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid
    @RequestBody
    ThnomeDTO thnomeDTO) throws Exception {
        log.debug("Request to update Thnome: {}", thnomeDTO);

        Thnome thnome = thnomeMapper.thnomeDTOToThnome(thnomeDTO);
        thnome = thnomeService.update(thnome);

        return ResponseEntity.ok().body(thnomeMapper.thnomeToThnomeDTO(thnome));
    }

    @DeleteMapping(value = "/{thnomeId}")
    public ResponseEntity<?> delete(@PathVariable("thnomeId")
    Integer thnomeId) throws Exception {
        log.debug("Request to delete Thnome");

        thnomeService.deleteById(thnomeId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(thnomeService.count());
    }
}
