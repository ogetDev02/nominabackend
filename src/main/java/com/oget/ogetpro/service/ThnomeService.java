package com.oget.ogetpro.service;

import com.oget.ogetpro.model.Thnome;
import com.oget.ogetpro.exception.ZMessManager;
import com.oget.ogetpro.exception.ZMessManager.EmptyFieldException;
import com.oget.ogetpro.exception.ZMessManager.NullEntityExcepcion;
import com.oget.ogetpro.repository.ThnomeRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Scope("singleton")
@Service
@Slf4j
public class ThnomeService {
    @Autowired
    private ThnomeRepository thnomeRepository;
    @Autowired
    private Validator validator;

    
 public void validate(Thnome thnome)
	        throws ConstraintViolationException {
	        Set<ConstraintViolation<Thnome>> constraintViolations = validator.validate(thnome);

	        if (!constraintViolations.isEmpty()) {
	        }
	    }

    @Transactional("nomTransactionManager")
    public Long count() {
        return thnomeRepository.count();
    }

    
    @Transactional("nomTransactionManager")
    public List<Thnome> findAll() {
        log.debug("finding all Thnome instances");

        return thnomeRepository.findAll();
    }

    @Transactional(transactionManager = "nomTransactionManager")
    public Thnome save(Thnome entity) throws Exception {
        log.debug("saving Thnome instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Thnome");
        }

        validate(entity);

        return thnomeRepository.save(entity);
    }

    
    @Transactional("nomTransactionManager")
    public void delete(Thnome entity) throws Exception {
        log.debug("deleting Thnome instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Thnome");
        }

        if (entity.getThnomeId() == null) {
            throw new ZMessManager().new EmptyFieldException("thnomeId");
        }

        if (thnomeRepository.existsById(entity.getThnomeId()) == false) {
            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        thnomeRepository.deleteById(entity.getThnomeId());
        log.debug("delete Thnome successful");
    }

    
    @Transactional("nomTransactionManager")
    public void deleteById(Integer id) throws Exception {
        log.debug("deleting Thnome instance");

        if (id == null) {
            throw new ZMessManager().new EmptyFieldException("thnomeId");
        }

        if (thnomeRepository.existsById(id)) {
            delete(thnomeRepository.findById(id).get());
        }
    }

    
    @Transactional("nomTransactionManager")
    public Thnome update(Thnome entity) throws Exception {
        log.debug("updating Thnome instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Thnome");
        }

        validate(entity);

        if (thnomeRepository.existsById(entity.getThnomeId()) == false) {
            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        return thnomeRepository.save(entity);
    }

    
    @Transactional("nomTransactionManager")
    public Optional<Thnome> findById(Integer thnomeId) {
        log.debug("getting Thnome instance");

        return thnomeRepository.findById(thnomeId);
    }
}
