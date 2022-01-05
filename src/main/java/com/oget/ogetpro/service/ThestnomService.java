package com.oget.ogetpro.service;

import com.oget.ogetpro.model.Thnome;
import com.oget.ogetpro.model.Thtracking;
import com.oget.ogetpro.exception.ZMessManager;
import com.oget.ogetpro.exception.ZMessManager.DeletingException;
import com.oget.ogetpro.exception.ZMessManager.EmptyFieldException;
import com.oget.ogetpro.exception.ZMessManager.NullEntityExcepcion;
import com.oget.ogetpro.model.Thestnom;
import com.oget.ogetpro.repository.ThestnomRepository;
import com.oget.ogetpro.utility.Utilities;

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
public class  ThestnomService{

	@Autowired
	private ThestnomRepository thestnomRepository;
	
	@Autowired
	private Validator validator;
                
            		
	public void validate(Thestnom thestnom)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<Thestnom>> constraintViolations =validator.validate(thestnom);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
	
	
	@Transactional(readOnly=true)
	public Long count(){
	 	return thestnomRepository.count();
	}

	@Transactional(readOnly=true)
	public List<Thestnom> findAll(){
		log.debug("finding all Thestnom instances");
       	return thestnomRepository.findAll();
    }
			
			
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)			
    public Thestnom save(Thestnom entity) throws Exception {
		log.debug("saving Thestnom instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Thestnom");
		}
		
		validate(entity);	
	
//		if(thestnomRepository.existsById(entity.getThestnomId())){
//           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
//        }    
	
	    return thestnomRepository.save(entity);
	   
    }
			
			
			
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void delete(Thestnom entity) throws Exception {
            	log.debug("deleting Thestnom instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Thestnom");
	    		}
    	
                                if(entity.getThestnomId()==null){
                    throw new ZMessManager().new EmptyFieldException("thestnomId");
                    }
                        
            if(thestnomRepository.existsById(entity.getThestnomId())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getThestnomId()).ifPresent(entidad->{	            	
	                													List<Thnome> thnomes = entidad.getThnomes();
							                    if(Utilities.validationsList(thnomes)==true){
                       	 	throw new ZMessManager().new DeletingException("thnomes");
                        }
	                													List<Thtracking> thtrackings = entidad.getThtrackings();
							                    if(Utilities.validationsList(thtrackings)==true){
                       	 	throw new ZMessManager().new DeletingException("thtrackings");
                        }
	                	            });
                       

           
            
            thestnomRepository.deleteById(entity.getThestnomId());
            log.debug("delete Thestnom successful");
            
           
            	
            }
            
            
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Thestnom instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("thestnomId");
            	}
            	if(thestnomRepository.existsById(id)){
           			delete(thestnomRepository.findById(id).get());
       			}    
            }	
			
			
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public Thestnom update(Thestnom entity) throws Exception {

				log.debug("updating Thestnom instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Thestnom");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(thestnomRepository.existsById(entity.getThestnomId())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return thestnomRepository.save(entity);
	        
            }
			
			
			@Transactional(readOnly=true)
            public Optional<Thestnom> findById(Integer thestnomId) {            
            	log.debug("getting Thestnom instance");
            	return thestnomRepository.findById(thestnomId);
            }
}
