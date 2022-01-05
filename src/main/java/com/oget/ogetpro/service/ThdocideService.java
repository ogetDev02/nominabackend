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

import com.oget.ogetpro.model.Thdocide;
import com.oget.ogetpro.model.Thnome;
import com.oget.ogetpro.exception.ZMessManager;
import com.oget.ogetpro.exception.ZMessManager.DeletingException;
import com.oget.ogetpro.exception.ZMessManager.EmptyFieldException;
import com.oget.ogetpro.exception.ZMessManager.NullEntityExcepcion;
import com.oget.ogetpro.repository.ThdocideRepository;
import com.oget.ogetpro.utility.Utilities;

import lombok.extern.slf4j.Slf4j;

@Scope("singleton")
@Service
@Slf4j
public class  ThdocideService{

	@Autowired
	private ThdocideRepository thdocideRepository;
	
	@Autowired
	private Validator validator;
                
            		
	public void validate(Thdocide thdocide)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<Thdocide>> constraintViolations =validator.validate(thdocide);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
	
	
	@Transactional(readOnly=true)
	public Long count(){
	 	return thdocideRepository.count();
	}

	
	@Transactional(readOnly=true)
	public List<Thdocide> findAll(){
		log.debug("finding all Thdocide instances");
       	return thdocideRepository.findAll();
    }
			
			
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)			
    public Thdocide save(Thdocide entity) throws Exception {
		log.debug("saving Thdocide instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Thdocide");
		}
		
		validate(entity);	
	
		if(thdocideRepository.existsById(entity.getThdocideId())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return thdocideRepository.save(entity);
	   
    }
			
			
			
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void delete(Thdocide entity) throws Exception {
            	log.debug("deleting Thdocide instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Thdocide");
	    		}
    	
                                if(entity.getThdocideId()==null){
                    throw new ZMessManager().new EmptyFieldException("thdocideId");
                    }
                        
            if(thdocideRepository.existsById(entity.getThdocideId())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getThdocideId()).ifPresent(entidad->{	            	
	                													List<Thnome> thnomes = entidad.getThnomes();
							                    if(Utilities.validationsList(thnomes)==true){
                       	 	throw new ZMessManager().new DeletingException("thnomes");
                        }
	                	            });
                       

           
            
            thdocideRepository.deleteById(entity.getThdocideId());
            log.debug("delete Thdocide successful");
            
           
            	
            }
            
            
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Thdocide instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("thdocideId");
            	}
            	if(thdocideRepository.existsById(id)){
           			delete(thdocideRepository.findById(id).get());
       			}    
            }	
			
			
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public Thdocide update(Thdocide entity) throws Exception {

				log.debug("updating Thdocide instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Thdocide");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(thdocideRepository.existsById(entity.getThdocideId())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return thdocideRepository.save(entity);
	        
            }
			
			
			@Transactional(readOnly=true)
            public Optional<Thdocide> findById(Integer thdocideId) {            
            	log.debug("getting Thdocide instance");
            	return thdocideRepository.findById(thdocideId);
            }
			
}
