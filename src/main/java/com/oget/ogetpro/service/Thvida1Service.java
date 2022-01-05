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

import com.oget.ogetpro.model.Thvida1;
import com.oget.ogetpro.model.Thvida2;
import com.oget.ogetpro.exception.ZMessManager;
import com.oget.ogetpro.exception.ZMessManager.DeletingException;
import com.oget.ogetpro.exception.ZMessManager.EmptyFieldException;
import com.oget.ogetpro.exception.ZMessManager.NullEntityExcepcion;
import com.oget.ogetpro.repository.Thvida1Repository;
import com.oget.ogetpro.utility.Utilities;

import lombok.extern.slf4j.Slf4j;
@Scope("singleton")
@Service
@Slf4j
public class Thvida1Service {
		@Autowired
		private Thvida1Repository thvida1Repository;
		
		@Autowired
		private Validator validator;
	                
	            		
		public void validate(Thvida1 thvida1)throws ConstraintViolationException{		
			
			Set<ConstraintViolation<Thvida1>> constraintViolations =validator.validate(thvida1);
			 if (!constraintViolations.isEmpty()) {			
				throw new ConstraintViolationException(constraintViolations);
			}
			
		}
		
		@Transactional("nomTransactionManager")
		public Long count(){
		 	return thvida1Repository.count();
		}

		
		public List<Thvida1> findAll(){
			log.debug("finding all Thvida1 instances");
	       	return thvida1Repository.findAll();
	    }
				
				
		
	    public Thvida1 save(Thvida1 entity) throws Exception {
			log.debug("saving Thvida1 instance");
		   
		    
		    if(entity==null){
				throw new ZMessManager().new NullEntityExcepcion("Thvida1");
			}
			
			validate(entity);	
		
			if(thvida1Repository.existsById(entity.getThvida1Id())){
	           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
	        }    
		
		    return thvida1Repository.save(entity);
		   
	    }
				
				
				
	            public void delete(Thvida1 entity) throws Exception {
	            	log.debug("deleting Thvida1 instance");
	            	
		            if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Thvida1");
		    		}
	    	
	                                if(entity.getThvida1Id()==null){
	                    throw new ZMessManager().new EmptyFieldException("thvida1Id");
	                    }
	                        
	            if(thvida1Repository.existsById(entity.getThvida1Id())==false){
	           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
	        	} 
	            
	            	            findById(entity.getThvida1Id()).ifPresent(entidad->{	            	
		                													List<Thvida2> thvida2s = entidad.getThvida2s();
								                    if(Utilities.validationsList(thvida2s)==true){
	                       	 	throw new ZMessManager().new DeletingException("thvida2s");
	                        }
		                	            });
	                       

	           
	            
	            thvida1Repository.deleteById(entity.getThvida1Id());
	            log.debug("delete Thvida1 successful");
	            
	           
	            	
	            }
	            
	            
	            public void deleteById(Integer id) throws Exception {            
	            	log.debug("deleting Thvida1 instance");
	            	if(id==null){
	            		throw new ZMessManager().new EmptyFieldException("thvida1Id");
	            	}
	            	if(thvida1Repository.existsById(id)){
	           			delete(thvida1Repository.findById(id).get());
	       			}    
	            }	
				
				
	            public Thvida1 update(Thvida1 entity) throws Exception {

					log.debug("updating Thvida1 instance");
					
		           
		            
		            	if(entity==null){
			    			throw new ZMessManager().new NullEntityExcepcion("Thvida1");
			    		}
			    		
		            validate(entity);
		            
		            
		            if(thvida1Repository.existsById(entity.getThvida1Id())==false){
	           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
	        		}	            
		
		            return thvida1Repository.save(entity);
		        
	            }
				
				
	            public Optional<Thvida1> findById(Integer thvida1Id) {            
	            	log.debug("getting Thvida1 instance");
	            	return thvida1Repository.findById(thvida1Id);
	            }
				

}
