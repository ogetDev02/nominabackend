package com.oget.ogetpro.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oget.ogetpro.model.Thmovliq;
import com.oget.ogetpro.exception.ZMessManager;
import com.oget.ogetpro.exception.ZMessManager.EmptyFieldException;
import com.oget.ogetpro.exception.ZMessManager.NullEntityExcepcion;
import com.oget.ogetpro.repository.ThmovliqRepository;

import lombok.extern.slf4j.Slf4j;

@Scope("singleton")
@Service
@Slf4j
public class ThmovliqService {
	 @Autowired
	    private ThmovliqRepository thmovliqRepository;
	    @Autowired
	    private Validator validator;

	    
	    public void validate(Thmovliq thmovliq) throws ConstraintViolationException {
	        Set<ConstraintViolation<Thmovliq>> constraintViolations = validator.validate(thmovliq);

	        if (!constraintViolations.isEmpty()) {
	            throw new ConstraintViolationException(constraintViolations);
	        }
	    }

	    @Transactional("nomTransactionManager")
	    public Long count() {
	        return thmovliqRepository.count();
	    }

	    @Transactional("nomTransactionManager")
	    public List<Thmovliq> findAll() {
	        log.debug("finding all Thmovliq instances");

	        return thmovliqRepository.findAll();
	    }

	    @Transactional("nomTransactionManager")
	    public Thmovliq save(Thmovliq entity) throws Exception {
	        log.debug("saving Thmovliq instance");

	        if (entity == null) {
	            throw new ZMessManager().new NullEntityExcepcion("Thmovliq");
	        }

	        validate(entity);

	        if (thmovliqRepository.existsById(entity.getThmovliqId())) {
	            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
	        }

	        return thmovliqRepository.save(entity);
	    }

	    @Transactional("nomTransactionManager")
	    public void delete(Thmovliq entity) throws Exception {
	        log.debug("deleting Thmovliq instance");

	        if (entity == null) {
	            throw new ZMessManager().new NullEntityExcepcion("Thmovliq");
	        }

	        if (entity.getThmovliqId() == null) {
	            throw new ZMessManager().new EmptyFieldException("thmovliqId");
	        }

	        if (thmovliqRepository.existsById(entity.getThmovliqId()) == false) {
	            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
	        }

	        thmovliqRepository.deleteById(entity.getThmovliqId());
	        log.debug("delete Thmovliq successful");
	    }

	    @Transactional("nomTransactionManager")
	    public void deleteById(Integer id) throws Exception {
	        log.debug("deleting Thmovliq instance");

	        if (id == null) {
	            throw new ZMessManager().new EmptyFieldException("thmovliqId");
	        }

	        if (thmovliqRepository.existsById(id)) {
	            delete(thmovliqRepository.findById(id).get());
	        }
	    }

	    @Transactional("nomTransactionManager")
	    public Thmovliq update(Thmovliq entity) throws Exception {
	        log.debug("updating Thmovliq instance");

	        if (entity == null) {
	            throw new ZMessManager().new NullEntityExcepcion("Thmovliq");
	        }

	        validate(entity);

	        if (thmovliqRepository.existsById(entity.getThmovliqId()) == false) {
	            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
	        }

	        return thmovliqRepository.save(entity);
	    }

	    @Transactional("nomTransactionManager")
	    public Optional<Thmovliq> findById(Integer thmovliqId) {
	        log.debug("getting Thmovliq instance");

	        return thmovliqRepository.findById(thmovliqId);
	    }

}
