package com.oget.ogetpro.controller;

import com.oget.ogetpro.model.Thpertli;
import com.oget.ogetpro.dto.ThpertliDTO;
import com.oget.ogetpro.mappe.ThpertliMapper;
import com.oget.ogetpro.service.ThpertliService;

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
@RequestMapping("/thpertli")
@CrossOrigin(origins = "*")
@Slf4j
public class ThpertliRestController {
    @Autowired
    private ThpertliService thpertliService;
    @Autowired
    private ThpertliMapper thpertliMapper;

    @GetMapping(value = "/{thpertliId}")
    public ResponseEntity<?> findById(
        @PathVariable("thpertliId")
    Integer thpertliId) throws Exception {
        log.debug("Request to findById() Thpertli");

        Thpertli thpertli = (thpertliService.findById(thpertliId).isPresent() == true)
            ? thpertliService.findById(thpertliId).get() : null;

        return ResponseEntity.ok()
                             .body(thpertliMapper.thpertliToThpertliDTO(
                thpertli));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        log.debug("Request to findAll() Thpertli");

        return ResponseEntity.ok()
                             .body(thpertliMapper.listThpertliToListThpertliDTO(
                thpertliService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid
    @RequestBody
    ThpertliDTO thpertliDTO) throws Exception {
        log.debug("Request to save Thpertli: {}", thpertliDTO);

        Thpertli thpertli = thpertliMapper.thpertliDTOToThpertli(thpertliDTO);
        thpertli = thpertliService.save(thpertli);

        return ResponseEntity.ok()
                             .body(thpertliMapper.thpertliToThpertliDTO(
                thpertli));
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid
    @RequestBody
    ThpertliDTO thpertliDTO) throws Exception {
        log.debug("Request to update Thpertli: {}", thpertliDTO);

        Thpertli thpertli = thpertliMapper.thpertliDTOToThpertli(thpertliDTO);
        thpertli = thpertliService.update(thpertli);

        return ResponseEntity.ok()
                             .body(thpertliMapper.thpertliToThpertliDTO(
                thpertli));
    }

    @DeleteMapping(value = "/{thpertliId}")
    public ResponseEntity<?> delete(
        @PathVariable("thpertliId")
    Integer thpertliId) throws Exception {
        log.debug("Request to delete Thpertli");

        thpertliService.deleteById(thpertliId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok().body(thpertliService.count());
    }
}
