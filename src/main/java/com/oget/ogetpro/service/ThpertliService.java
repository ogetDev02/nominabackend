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

import com.oget.ogetpro.model.Thctrliq;
import com.oget.ogetpro.model.Thpertli;
import com.oget.ogetpro.exception.ZMessManager;
import com.oget.ogetpro.exception.ZMessManager.DeletingException;
import com.oget.ogetpro.exception.ZMessManager.EmptyFieldException;
import com.oget.ogetpro.exception.ZMessManager.NullEntityExcepcion;
import com.oget.ogetpro.repository.ThpertliRepository;
import com.oget.ogetpro.utility.Utilities;

import lombok.extern.slf4j.Slf4j;

@Scope("singleton")
@Service
@Slf4j
public class ThpertliService {
	@Autowired
	private ThpertliRepository thpertliRepository;
	
	@Autowired
	private Validator validator;
                
            		
	public void validate(Thpertli thpertli)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<Thpertli>> constraintViolations =validator.validate(thpertli);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
	
	@Transactional("nomTransactionManager")
	public Long count(){
	 	return thpertliRepository.count();
	}

	@Transactional("nomTransactionManager")
	public List<Thpertli> findAll(){
		log.debug("finding all Thpertli instances");
       	return thpertliRepository.findAll();
    }
			
			
	@Transactional("nomTransactionManager")
	public Thpertli save(Thpertli entity) throws Exception {
		log.debug("saving Thpertli instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Thpertli");
		}
		
		validate(entity);	
	
		if(thpertliRepository.existsById(entity.getThpertliId())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return thpertliRepository.save(entity);
	   
    }
			
			
	@Transactional("nomTransactionManager")
	public void delete(Thpertli entity) throws Exception {
            	log.debug("deleting Thpertli instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Thpertli");
	    		}
    	
                                if(entity.getThpertliId()==null){
                    throw new ZMessManager().new EmptyFieldException("thpertliId");
                    }
                        
            if(thpertliRepository.existsById(entity.getThpertliId())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getThpertliId()).ifPresent(entidad->{	            	
	                													List<Thctrliq> thctrliqs = entidad.getThctrliqs();
							                    if(Utilities.validationsList(thctrliqs)==true){
                       	 	throw new ZMessManager().new DeletingException("thctrliqs");
                        }
	                	            });
                       

           
            
            thpertliRepository.deleteById(entity.getThpertliId());
            log.debug("delete Thpertli successful");
            
           
            	
            }
            
	@Transactional("nomTransactionManager")        
	public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Thpertli instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("thpertliId");
            	}
            	if(thpertliRepository.existsById(id)){
           			delete(thpertliRepository.findById(id).get());
       			}    
            }	
			
	@Transactional("nomTransactionManager")		
	public Thpertli update(Thpertli entity) throws Exception {

				log.debug("updating Thpertli instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Thpertli");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(thpertliRepository.existsById(entity.getThpertliId())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return thpertliRepository.save(entity);
	        
            }
			
	@Transactional("nomTransactionManager")		
	public Optional<Thpertli> findById(Integer thpertliId) {            
            	log.debug("getting Thpertli instance");
            	return thpertliRepository.findById(thpertliId);
            }
			

}
