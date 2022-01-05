package com.oget.ogetpro.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oget.ogetpro.exception.ZMessManager;
import com.oget.ogetpro.exception.ZMessManager.DeletingException;
import com.oget.ogetpro.exception.ZMessManager.EmptyFieldException;
import com.oget.ogetpro.exception.ZMessManager.NullEntityExcepcion;
import com.oget.ogetpro.model.Thcptos;
import com.oget.ogetpro.model.Thcptosdian;
import com.oget.ogetpro.repository.ThcptosRepository;
import com.oget.ogetpro.utility.Utilities;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ThcptosService {
	
	@Autowired
	ThcptosRepository thcptosRepository;
	
	@Autowired
	private Validator validator;
	
	         		
	public void validate(Thcptos thcptos)throws ConstraintViolationException{		
			
			Set<ConstraintViolation<Thcptos>> constraintViolations =validator.validate(thcptos);
			 if (!constraintViolations.isEmpty()) {			
				throw new ConstraintViolationException(constraintViolations);
			}
			
		}
	
	@Transactional("nomTransactionManager")
	public Long count(){
	 	return thcptosRepository.count();
	}
	
	@Transactional("nomTransactionManager")
	public List<Thcptos> findAll(){
		log.debug("finding all Thcptos instances");
       	return thcptosRepository.findAll();
    }
			
	@Transactional("nomTransactionManager")		
	public Thcptos save(Thcptos entity) throws Exception {
		log.debug("saving Thcptos instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Thcptos");
		}
		
		validate(entity);	
	
		if(thcptosRepository.existsById(entity.getThcptosId())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return thcptosRepository.save(entity);
	   
    }
			
	@Transactional("nomTransactionManager")
	public void delete(Thcptos entity) throws Exception {
            	log.debug("deleting Thcptos instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Thcptos");
	    		}
    	
                                if(entity.getThcptosId()==null){
                    throw new ZMessManager().new EmptyFieldException("thcptosId");
                    }
                        
            if(thcptosRepository.existsById(entity.getThcptosId())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getThcptosId()).ifPresent(entidad->{	            	
	                													List<Thcptosdian> thcptosdians = entidad.getThcptosdians();
							                    if(Utilities.validationsList(thcptosdians)==true){
                       	 	throw new ZMessManager().new DeletingException("thcptosdians");
                        }
	                	            });
                       

           
            
            thcptosRepository.deleteById(entity.getThcptosId());
            log.debug("delete Thcptos successful");
            
           
            	
            }
	@Transactional("nomTransactionManager") 
    public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Thcptos instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("thcptosId");
            	}
            	if(thcptosRepository.existsById(id)){
           			delete(thcptosRepository.findById(id).get());
       			}    
            }	
			
			public Thcptos update(Thcptos entity) throws Exception {

				log.debug("updating Thcptos instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Thcptos");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(thcptosRepository.existsById(entity.getThcptosId())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return thcptosRepository.save(entity);
	        
            }
		@Transactional("nomTransactionManager")	
		public Optional<Thcptos> findById(Integer thcptosId) {            
            	log.debug("getting Thcptos instance");
            	return thcptosRepository.findById(thcptosId);
            }
			

}
