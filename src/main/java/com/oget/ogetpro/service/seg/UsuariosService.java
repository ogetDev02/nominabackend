package com.oget.ogetpro.service.seg;

import com.oget.ogetpro.model.seg.Opcuser;
import com.oget.ogetpro.model.seg.Rolxusuario;
import com.oget.ogetpro.model.seg.Usuario;
//import com.oget.ogetpro.exception.ZMessManager;
//import com.oget.ogetpro.exception.ZMessManager.DeletingException;
//import com.oget.ogetpro.exception.ZMessManager.EmptyFieldException;
//import com.oget.ogetpro.exception.ZMessManager.NullEntityExcepcion;
import com.oget.ogetpro.repository.seg.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Scope("singleton")
@Service
@Slf4j
public class UsuariosService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional("segTransactionManager")
	public Long count(){
	 	return usuarioRepository.count();
	}
	
	@Transactional("segTransactionManager")
	public List<Usuario> findAll(){
		log.debug("finding all Usuario instances");
       	return usuarioRepository.findAll();
    }
			
	public Usuario save(Usuario entity) throws Exception {
		log.debug("saving Usuario instance");
	   
	    
	    if(entity==null){
		//	throw new ZMessManager().new NullEntityExcepcion("Usuario");
		}
		
		//validate(entity);	
	
		if(usuarioRepository.existsById(entity.getUsuarioid())){
          // throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return usuarioRepository.save(entity);
	   
    }
			
	public void delete(Usuario entity) throws Exception {
            	log.debug("deleting Usuario instance");
            	
	            if(entity==null){
	    		//	throw new ZMessManager().new NullEntityExcepcion("Usuario");
	    		}
    	
                                if(entity.getUsuarioid()==null){
               //     throw new ZMessManager().new EmptyFieldException("usuarioid");
                    }
                        
            if(usuarioRepository.existsById(entity.getUsuarioid())==false){
           	//	throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getUsuarioid()).ifPresent(entidad->{	            	
	                													List<Opcuser> opcusers = entidad.getOpcusers();
							                  //  if(Utilities.validationsList(opcusers)==true){
                     //  	 	throw new ZMessManager().new DeletingException("opcusers");
                   //     }
	                													List<Rolxusuario> rolxusuarios = entidad.getRolxusuarios();
						//	                    if(Utilities.validationsList(rolxusuarios)==true){
             //          	 	throw new ZMessManager().new DeletingException("rolxusuarios");
                        //}
	                	            });
                       

           
            
            usuarioRepository.deleteById(entity.getUsuarioid());
            log.debug("delete Usuario successful");
            
           
            	
            }

	public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Usuario instance");
            	if(id==null){
           // 		throw new ZMessManager().new EmptyFieldException("usuarioid");
            	}
            	if(usuarioRepository.existsById(id)){
           			delete(usuarioRepository.findById(id).get());
       			}    
            }	

	public Usuario update(Usuario entity) throws Exception {

				log.debug("updating Usuario instance");
				
	           
	            
	            	if(entity==null){
		    	//		throw new ZMessManager().new NullEntityExcepcion("Usuario");
		    		}
		    		
	            //validate(entity);
	            
	            
	            if(usuarioRepository.existsById(entity.getUsuarioid())==false){
           		//	throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return usuarioRepository.save(entity);
	        
            }
	public Optional<Usuario> findById(Integer usuarioid) {            
            	log.debug("getting Usuario instance");
            	return usuarioRepository.findById(usuarioid);
            }
			

	
}
