package com.oget.ogetpro.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oget.ogetpro.dto.ThcptosDTO;
import com.oget.ogetpro.dto.ThcptosdianDTO;
import com.oget.ogetpro.exception.ZMessManager;
import com.oget.ogetpro.model.Thcptos;
//import com.oget.ogetpro.exception.ZMessManager;
//import com.oget.ogetpro.exception.ZMessManager.EmptyFieldException;
//import com.oget.ogetpro.exception.ZMessManager.NullEntityExcepcion;
import com.oget.ogetpro.model.Thcptosdian;
import com.oget.ogetpro.repository.ThcptosdianRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Setter
@Slf4j
public class ThcptosdianService {
	
	@Autowired
	private ThcptosdianRepository thcptosdianRepository;
	@Autowired
	private Validator validator;

	 public void validate(Thcptosdian thcptosdian)
		        throws ConstraintViolationException {
		        Set<ConstraintViolation<Thcptosdian>> constraintViolations = validator.validate(thcptosdian);

		        if (!constraintViolations.isEmpty()) {
		        }
		    }
	@Transactional("nomTransactionManager")
	public Long count() {
        return thcptosdianRepository.count();
    }
	
	@Transactional("nomTransactionManager")
	public List<Thcptosdian> findAll() {
        log.debug("finding all Thcptosdian instances");
        return thcptosdianRepository.findAll();
    }
	@Transactional("nomTransactionManager")
    public Thcptosdian save(Thcptosdian entity) throws Exception {
        log.debug("saving Thcptosdian instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Thcptosdian");
        }

        validate(entity);

        return thcptosdianRepository.save(entity);
    }
	@Transactional("nomTransactionManager")
    public void delete(Thcptosdian entity) throws Exception {
        log.debug("deleting Thcptosdian instance");

        if (entity == null) {
          throw new ZMessManager().new NullEntityExcepcion("Thcptosdian");
        }

        if (entity.getThcptosdianid() == null) {
            throw new ZMessManager().new EmptyFieldException("thcptosdianid");
        }

        if (thcptosdianRepository.existsById(entity.getThcptosdianid()) == false) {
            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        thcptosdianRepository.deleteById(entity.getThcptosdianid());
        log.debug("delete Thcptosdian successful");
    }
	@Transactional("nomTransactionManager")
    public void deleteById(Integer id) throws Exception {
        log.debug("deleting Thcptosdian instance");

        if (id == null) {
            throw new ZMessManager().new EmptyFieldException("thcptosdianid");
        }

        if (thcptosdianRepository.existsById(id)) {
            delete(thcptosdianRepository.findById(id).get());
        }
    }
	@Transactional("nomTransactionManager")
    public Thcptosdian update(Thcptosdian entity) throws Exception {
        log.debug("updating Thcptosdian instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Thcptosdian");
        }

        validate(entity);

        if (thcptosdianRepository.existsById(entity.getThcptosdianid()) == false) {
         throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        return thcptosdianRepository.save(entity);
    }
	@Transactional("nomTransactionManager")
    public Optional<Thcptosdian> findById(Integer thcptosdianid) {
        log.debug("getting Thcptosdian instance");

        return thcptosdianRepository.findById(thcptosdianid);
    }

}
