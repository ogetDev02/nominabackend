package com.oget.ogetpro.service;

import com.oget.ogetpro.model.Thtracking;
import com.oget.ogetpro.exception.ZMessManager;
import com.oget.ogetpro.exception.ZMessManager.EmptyFieldException;
import com.oget.ogetpro.exception.ZMessManager.NullEntityExcepcion;
import com.oget.ogetpro.repository.ThtrackingRepository;

import lombok.extern.slf4j.Slf4j;

import java.math.*;

import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Scope("singleton")
@Service
@Slf4j
public class  ThtrackingService {
    @Autowired
    private ThtrackingRepository thtrackingRepository;
    @Autowired
    private Validator validator;

    
    public void validate(Thtracking thtracking)
        throws ConstraintViolationException {
        Set<ConstraintViolation<Thtracking>> constraintViolations = validator.validate(thtracking);

        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    
    @Transactional(readOnly = true)
    public Long count() {
        return thtrackingRepository.count();
    }

    
    @Transactional(readOnly = true)
    public List<Thtracking> findAll() {
        log.debug("finding all Thtracking instances");

        return thtrackingRepository.findAll();
    }

    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Thtracking save(Thtracking entity) throws Exception {
        log.debug("saving Thtracking instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Thtracking");
        }

        validate(entity);


        return thtrackingRepository.save(entity);
    }

    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Thtracking entity) throws Exception {
        log.debug("deleting Thtracking instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Thtracking");
        }

        if (entity.getThtrackingId() == null) {
            throw new ZMessManager().new EmptyFieldException("thtrackingId");
        }

        if (thtrackingRepository.existsById(entity.getThtrackingId()) == false) {
            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        thtrackingRepository.deleteById(entity.getThtrackingId());
        log.debug("delete Thtracking successful");
    }

    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteById(Integer id) throws Exception {
        log.debug("deleting Thtracking instance");

        if (id == null) {
            throw new ZMessManager().new EmptyFieldException("thtrackingId");
        }

        if (thtrackingRepository.existsById(id)) {
            delete(thtrackingRepository.findById(id).get());
        }
    }

    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Thtracking update(Thtracking entity) throws Exception {
        log.debug("updating Thtracking instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Thtracking");
        }

        validate(entity);

        if (thtrackingRepository.existsById(entity.getThtrackingId()) == false) {
            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        return thtrackingRepository.save(entity);
    }

    
    @Transactional(readOnly = true)
    public Optional<Thtracking> findById(Integer thtrackingId) {
        log.debug("getting Thtracking instance");

        return thtrackingRepository.findById(thtrackingId);
    }
}
