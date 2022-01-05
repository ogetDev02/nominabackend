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
import com.oget.ogetpro.model.Thvida2;
import com.oget.ogetpro.exception.ZMessManager;
import com.oget.ogetpro.exception.ZMessManager.DeletingException;
import com.oget.ogetpro.exception.ZMessManager.EmptyFieldException;
import com.oget.ogetpro.exception.ZMessManager.NullEntityExcepcion;
import com.oget.ogetpro.repository.Thvida2Repository;
import com.oget.ogetpro.utility.Utilities;

import lombok.extern.slf4j.Slf4j;

@Scope("singleton")
@Service
@Slf4j
public class Thvida2Service {

		@Autowired
		private Thvida2Repository thvida2Repository;
		
		@Autowired
		private Validator validator;
	                
	            		
		public void validate(Thvida2 thvida2)throws ConstraintViolationException{		
			
			Set<ConstraintViolation<Thvida2>> constraintViolations =validator.validate(thvida2);
			 if (!constraintViolations.isEmpty()) {			
				throw new ConstraintViolationException(constraintViolations);
			}
			
		}
		
		@Transactional("nomTransactionManager")
		public Long count(){
		 	return thvida2Repository.count();
		}

		
		public List<Thvida2> findAll(){
			log.debug("finding all Thvida2 instances");
	       	return thvida2Repository.findAll();
	    }
				
				
		
	    public Thvida2 save(Thvida2 entity) throws Exception {
			log.debug("saving Thvida2 instance");
		   
		    
		    if(entity==null){
				throw new ZMessManager().new NullEntityExcepcion("Thvida2");
			}
			
			validate(entity);	
		
			if(thvida2Repository.existsById(entity.getThvida2Id())){
	           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
	        }    
		
		    return thvida2Repository.save(entity);
		   
	    }
				
				
				
	            public void delete(Thvida2 entity) throws Exception {
	            	log.debug("deleting Thvida2 instance");
	            	
		            if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Thvida2");
		    		}
	    	
	                                if(entity.getThvida2Id()==null){
	                    throw new ZMessManager().new EmptyFieldException("thvida2Id");
	                    }
	                        
	            if(thvida2Repository.existsById(entity.getThvida2Id())==false){
	           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
	        	} 
	            
	            	            findById(entity.getThvida2Id()).ifPresent(entidad->{	            	
		                													List<Thmovliq> thmovliqs = entidad.getThmovliqs();
								                    if(Utilities.validationsList(thmovliqs)==true){
	                       	 	throw new ZMessManager().new DeletingException("thmovliqs");
	                        }
		                	            });
	                       

	           
	            
	            thvida2Repository.deleteById(entity.getThvida2Id());
	            log.debug("delete Thvida2 successful");
	            
	           
	            	
	            }
	            
	            
	            public void deleteById(Integer id) throws Exception {            
	            	log.debug("deleting Thvida2 instance");
	            	if(id==null){
	            		throw new ZMessManager().new EmptyFieldException("thvida2Id");
	            	}
	            	if(thvida2Repository.existsById(id)){
	           			delete(thvida2Repository.findById(id).get());
	       			}    
	            }	
				
				
	            public Thvida2 update(Thvida2 entity) throws Exception {

					log.debug("updating Thvida2 instance");
					
		           
		            
		            	if(entity==null){
			    			throw new ZMessManager().new NullEntityExcepcion("Thvida2");
			    		}
			    		
		            validate(entity);
		            
		            
		            if(thvida2Repository.existsById(entity.getThvida2Id())==false){
	           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
	        		}	            
		
		            return thvida2Repository.save(entity);
		        
	            }
				
				
	            public Optional<Thvida2> findById(Integer thvida2Id) {            
	            	log.debug("getting Thvida2 instance");
	            	return thvida2Repository.findById(thvida2Id);
	            }
				

}
