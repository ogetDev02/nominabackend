package com.oget.ogetpro.controller;

import com.oget.ogetpro.model.*;
import com.oget.ogetpro.dto.ThtrackingDTO;
import com.oget.ogetpro.mappe.ThtrackingMapper;
import com.oget.ogetpro.service.ThtrackingService;

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
@RequestMapping("/api/v1/thtracking")
@CrossOrigin(origins = "*")
@Slf4j
public class ThtrackingRestController {
    @Autowired
    private ThtrackingService thtrackingService;
    @Autowired
    private ThtrackingMapper thtrackingMapper;

    @GetMapping(value = "/{thtrackingId}")
    public ResponseEntity<?> findById(
        @PathVariable("thtrackingId")
    Integer thtrackingId) throws Exception {
        log.debug("Request to findById() Thtracking");

        Thtracking thtracking = (thtrackingService.findById(thtrackingId)
                                                  .isPresent() == true)
            ? thtrackingService.findById(thtrackingId).get() : null;

        return ResponseEntity.ok()
                             .body(thtrackingMapper.thtrackingToThtrackingDTO(
                thtracking));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Thtracking");

        return ResponseEntity.ok()
                             .body(thtrackingMapper.listThtrackingToListThtrackingDTO(
                thtrackingService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    ThtrackingDTO thtrackingDTO) throws Exception {
        log.debug("Request to save Thtracking: {}", thtrackingDTO);

        Thtracking thtracking = thtrackingMapper.thtrackingDTOToThtracking(thtrackingDTO);
        thtracking = thtrackingService.save(thtracking);

        return ResponseEntity.ok()
                             .body(thtrackingMapper.thtrackingToThtrackingDTO(
                thtracking));
    }

    @PutMapping()
    public ResponseEntity<?> update(
        @Valid
    @RequestBody
    ThtrackingDTO thtrackingDTO) throws Exception {
        log.debug("Request to update Thtracking: {}", thtrackingDTO);

        Thtracking thtracking = thtrackingMapper.thtrackingDTOToThtracking(thtrackingDTO);
        thtracking = thtrackingService.update(thtracking);

        return ResponseEntity.ok()
                             .body(thtrackingMapper.thtrackingToThtrackingDTO(
                thtracking));
    }

    @DeleteMapping(value = "/{thtrackingId}")
    public ResponseEntity<?> delete(
        @PathVariable("thtrackingId")
    Integer thtrackingId) throws Exception {
        log.debug("Request to delete Thtracking");

        thtrackingService.deleteById(thtrackingId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(thtrackingService.count());
    }
}
