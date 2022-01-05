package com.oget.ogetpro.service;

import com.oget.ogetpro.model.Thvarnom;
import com.oget.ogetpro.exception.ZMessManager;
import com.oget.ogetpro.exception.ZMessManager.EmptyFieldException;
import com.oget.ogetpro.exception.ZMessManager.NullEntityExcepcion;
import com.oget.ogetpro.repository.ThvarnomRepository;

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
public class ThvarnomService  {
	 @Autowired
	    private ThvarnomRepository thvarnomRepository;
	    @Autowired
	    private Validator validator;

	    
	    public void validate(Thvarnom thvarnom) throws ConstraintViolationException {
	        Set<ConstraintViolation<Thvarnom>> constraintViolations = validator.validate(thvarnom);

	        if (!constraintViolations.isEmpty()) {
	            throw new ConstraintViolationException(constraintViolations);
	        }
	    }

	    
	    @Transactional(readOnly = true)
	    public Long count() {
	        return thvarnomRepository.count();
	    }

	    
	    @Transactional(readOnly = true)
	    public List<Thvarnom> findAll() {
	        log.debug("finding all Thvarnom instances");

	        return thvarnomRepository.findAll();
	    }

	    
	    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	    public Thvarnom save(Thvarnom entity) throws Exception {
	        log.debug("saving Thvarnom instance");

	        if (entity == null) {
	            throw new ZMessManager().new NullEntityExcepcion("Thvarnom");
	        }

	        validate(entity);

	        if (thvarnomRepository.existsById(entity.getThvarnomId())) {
	            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
	        }

	        return thvarnomRepository.save(entity);
	    }

	    
	    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	    public void delete(Thvarnom entity) throws Exception {
	        log.debug("deleting Thvarnom instance");

	        if (entity == null) {
	            throw new ZMessManager().new NullEntityExcepcion("Thvarnom");
	        }

	        if (entity.getThvarnomId() == null) {
	            throw new ZMessManager().new EmptyFieldException("thvarnomId");
	        }

	        if (thvarnomRepository.existsById(entity.getThvarnomId()) == false) {
	            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
	        }

	        thvarnomRepository.deleteById(entity.getThvarnomId());
	        log.debug("delete Thvarnom successful");
	    }

	    
	    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	    public void deleteById(Integer id) throws Exception {
	        log.debug("deleting Thvarnom instance");

	        if (id == null) {
	            throw new ZMessManager().new EmptyFieldException("thvarnomId");
	        }

	        if (thvarnomRepository.existsById(id)) {
	            delete(thvarnomRepository.findById(id).get());
	        }
	    }

	    
	    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	    public Thvarnom update(Thvarnom entity) throws Exception {
	        log.debug("updating Thvarnom instance");

	        if (entity == null) {
	            throw new ZMessManager().new NullEntityExcepcion("Thvarnom");
	        }

	        validate(entity);

	        if (thvarnomRepository.existsById(entity.getThvarnomId()) == false) {
	            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
	        }

	        return thvarnomRepository.save(entity);
	    }

	    
	    @Transactional(readOnly = true)
	    public Optional<Thvarnom> findById(Integer thvarnomId) {
	        log.debug("getting Thvarnom instance");

	        return thvarnomRepository.findById(thvarnomId);
	    }

}
