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
import com.oget.ogetpro.model.Thtippro;
import com.oget.ogetpro.exception.ZMessManager;
import com.oget.ogetpro.exception.ZMessManager.DeletingException;
import com.oget.ogetpro.exception.ZMessManager.EmptyFieldException;
import com.oget.ogetpro.exception.ZMessManager.NullEntityExcepcion;
import com.oget.ogetpro.repository.ThtipproRepository;
import com.oget.ogetpro.utility.Utilities;

import lombok.extern.slf4j.Slf4j;

@Scope("singleton")
@Service
@Slf4j
public class ThtipproService {
	@Autowired
	private ThtipproRepository thtipproRepository;
	
	@Autowired
	private Validator validator;
                
            		
	public void validate(Thtippro thtippro)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<Thtippro>> constraintViolations =validator.validate(thtippro);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
	
	
	@Transactional("nomTransactionManager")
	public Long count(){
	 	return thtipproRepository.count();
	}

	
	@Transactional("nomTransactionManager")
	public List<Thtippro> findAll(){
		log.debug("finding all Thtippro instances");
       	return thtipproRepository.findAll();
    }
			
			
	@Transactional("nomTransactionManager")
	public Thtippro save(Thtippro entity) throws Exception {
		log.debug("saving Thtippro instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Thtippro");
		}
		
		validate(entity);	
	
		if(thtipproRepository.existsById(entity.getThtipproId())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return thtipproRepository.save(entity);
	   
    }
			
			
	@Transactional("nomTransactionManager")	
	        public void delete(Thtippro entity) throws Exception {
            	log.debug("deleting Thtippro instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Thtippro");
	    		}
    	
                                if(entity.getThtipproId()==null){
                    throw new ZMessManager().new EmptyFieldException("thtipproId");
                    }
                        
            if(thtipproRepository.existsById(entity.getThtipproId())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getThtipproId()).ifPresent(entidad->{	            	
	                													List<Thctrliq> thctrliqsForThtipproId = entidad.getThctrliqsForThtipproId();
							                    if(Utilities.validationsList(thctrliqsForThtipproId)==true){
                       	 	throw new ZMessManager().new DeletingException("thctrliqsForThtipproId");
                        }
	                													List<Thctrliq> thctrliqsForThtipproSecId = entidad.getThctrliqsForThtipproSecId();
							                    if(Utilities.validationsList(thctrliqsForThtipproSecId)==true){
                       	 	throw new ZMessManager().new DeletingException("thctrliqsForThtipproSecId");
                        }
	                	            });
                       

           
            
            thtipproRepository.deleteById(entity.getThtipproId());
            log.debug("delete Thtippro successful");
            
           
            	
            }
            
	@Transactional("nomTransactionManager")    
	public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Thtippro instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("thtipproId");
            	}
            	if(thtipproRepository.existsById(id)){
           			delete(thtipproRepository.findById(id).get());
       			}    
            }	
			
	@Transactional("nomTransactionManager")		
	        public Thtippro update(Thtippro entity) throws Exception {

				log.debug("updating Thtippro instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Thtippro");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(thtipproRepository.existsById(entity.getThtipproId())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return thtipproRepository.save(entity);
	        
            }
			
	@Transactional("nomTransactionManager")
	        public Optional<Thtippro> findById(Integer thtipproId) {            
            	log.debug("getting Thtippro instance");
            	return thtipproRepository.findById(thtipproId);
            }
			

}
