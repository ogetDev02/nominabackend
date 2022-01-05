package com.oget.ogetpro.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;
import org.datacontract.schemas._2004._07.DianResponse.DianResponse;
import org.datacontract.schemas._2004._07.UploadDocumentResponse.UploadDocumentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oget.ogetpro.model.Thctrliq;
import com.oget.ogetpro.dto.ConsultaDTO;
import com.oget.ogetpro.dto.MensajeDTO;
import com.oget.ogetpro.dto.ThctrliqDTO;
import com.oget.ogetpro.mappe.ThctrliqMapper;
import com.oget.ogetpro.service.ThctrliqService;
import com.oget.ogetpro.utils.Helper;
import com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType;

import colombia.dian.wcf.WSHttpBinding_IWcfDianCustomerServicesStub;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/thctrliq")
@CrossOrigin(origins = "*")
@Slf4j
public class ThctrliqRestcontroller {
	 @Autowired
	 private ThctrliqService thctrliqService;
	 @Autowired
	 private ThctrliqMapper delegate;
	 
	 
	 @GetMapping(value = "/nomelec/{fhi}/{fhf}")
	 public ResponseEntity<?> GenXml( @PathVariable("fhi") Date fhi, @PathVariable("fhf") Date fhf) throws JAXBException, ParseException, Exception {
		log.debug("Request to GenXml() Thctrliq");
		MensajeDTO<?> mensaje = null;
		thctrliqService.ConsultaXml(fhi, fhf);
		mensaje = new MensajeDTO<>(1,0,"Generado Corretamente","");
	  return new ResponseEntity<MensajeDTO<?>>(mensaje, HttpStatus.OK);
	 }
	 
	 @GetMapping(value = "/nomelec/xmlerror/{fhi}/{fhf}")
	 public ResponseEntity<?>Erroxml(@PathVariable("fhi") Date fhi, @PathVariable("fhf") Date fhf)throws JAXBException, ParseException, Exception{
		 log.info("Request to ErrorXml() Thctrliq");
		 MensajeDTO<?>mensaje = null;
		 thctrliqService.GenXMLErrors(fhi, fhf);
		 mensaje = new MensajeDTO<>(1,0,"Corregido Correctamen");
		 log.info("finish Request Thctrliq");
		 return new ResponseEntity<MensajeDTO<?>>(mensaje, HttpStatus.OK);
	 }
	 @GetMapping(value = "/nomelec/cert/{file}")
	 public NominaIndividualDeAjusteType imprimir_cert(@PathVariable("file")String file) throws JAXBException {
		 log.debug("Request to imprimir_certificado Thctrliq");
		 return thctrliqService.leerGenXml(file);
	 }
	 @GetMapping(value = "/nomelec/sendeErrors")
	 public ResponseEntity<?>SendErrosXml() throws Throwable{
		 log.info("Request to SendErrosXml() Thctrliq");
		 MensajeDTO<?>mensaje =null;
		 mensaje= thctrliqService.ErrorSenderXml();
		 return new ResponseEntity<MensajeDTO<?>>(mensaje,HttpStatus.OK);
	 }
	 @GetMapping(value = "/{thctrliqId}")
	    public ResponseEntity<?> findById(
	        @PathVariable("thctrliqId")
	    Integer thctrliqId) throws Exception {
	        log.debug("Request to findById() Thctrliq");

	        Thctrliq thctrliq = (thctrliqService.findById(thctrliqId).isPresent() == true)
	            ? thctrliqService.findById(thctrliqId).get() : null;

	        return ResponseEntity.ok()
	                             .body(delegate.thctrliqToThctrliqDTO(
	                thctrliq));
	    }

	    @GetMapping()
	    public ResponseEntity<?> findAll() throws Exception {
	        log.debug("Request to findAll() Thctrliq");

	        return ResponseEntity.ok()
	                             .body(delegate.listThctrliqToListThctrliqDTO(
	                thctrliqService.findAll()));
	    }

	    @PostMapping()
	    public ResponseEntity<?> save(@Valid
	    @RequestBody
	    ThctrliqDTO thctrliqDTO) throws Exception {
	        log.debug("Request to save Thctrliq: {}", thctrliqDTO);

	        Thctrliq thctrliq = delegate.thctrliqDTOToThctrliq(thctrliqDTO);
	        thctrliq = thctrliqService.save(thctrliq);

	        return ResponseEntity.ok()
	                             .body(delegate.thctrliqToThctrliqDTO(
	                thctrliq));
	    }

	    @PutMapping()
	    public ResponseEntity<?> update(@Valid
	    @RequestBody
	    ThctrliqDTO thctrliqDTO) throws Exception {
	        log.debug("Request to update Thctrliq: {}", thctrliqDTO);

	        Thctrliq thctrliq = delegate.thctrliqDTOToThctrliq(thctrliqDTO);
	        thctrliq = thctrliqService.update(thctrliq);

	        return ResponseEntity.ok()
	                             .body(delegate.thctrliqToThctrliqDTO(
	                thctrliq));
	    }

	    @DeleteMapping(value = "/{thctrliqId}")
	    public ResponseEntity<?> delete(
	        @PathVariable("thctrliqId")
	    Integer thctrliqId) throws Exception {
	        log.debug("Request to delete Thctrliq");

	        thctrliqService.deleteById(thctrliqId);

	        return ResponseEntity.ok().build();
	    }

	    @GetMapping(value = "/count")
	    public ResponseEntity<?> count() {
	        return ResponseEntity.ok().body(thctrliqService.count());
	    }
	    
	    @PostMapping("/registrarDian")
		public ResponseEntity<?> registrarDian(@RequestBody ConsultaDTO consulta) throws Exception {		
			String outcome = null;		
			MensajeDTO<?> mensaje = null;
			Integer tipoUsuario = 1;
			Long idValidacion = null;		
		
		  try {
		    Helper.writeLog(log, "INFO", "Proxy Host: " + System.getProperties().get("http.proxyHost"));
		    Helper.writeLog(log, "INFO", "Proxy Port: " + System.getProperties().get("http.proxyPort"));

		   // -> Deshabilitar los Proxys en caso de que existan
		    System.getProperties().put("http.proxyHost", "");
		    System.getProperties().put("http.proxyPort", "");
		    
		    //ADOWSHttpBinding_IWcfDianCustomerServices service= new ADOWSHttpBinding_IWcfDianCustomerServices("https://vpfe-hab.dian.gov.co/WcfDianCustomerServices.svc");
		    java.net.URL urlDian=new java.net.URL("https://vpfe-hab.dian.gov.co/WcfDianCustomerServices.svc");
		    WSHttpBinding_IWcfDianCustomerServicesStub  service=new WSHttpBinding_IWcfDianCustomerServicesStub(urlDian,null );
		    // File file= new File("/ogetdes/z08150034142100000004C.zip");
		    File file = new File("C:/home/ec2-user/ogetXML/icomallas/firmado/z08050074042100000065C.zip");
		    UploadDocumentResponse dianResponse = service.sendTestSetAsync("z08050074042100000065C.zip",FileUtils.readFileToByteArray(file),"0bcd79ba-345a-44b5-a5a2-a420767a6126");
		    DianResponse[] dianResponses= service.getStatusZip(dianResponse.getZipKey());
		   //08e394fe-7e57-468c-96ef-afe111dd338b- 4e86b749-0fc0-4f77-b64e-6f934e0e6477
		    for (DianResponse dianResponse1 : dianResponses) {
		    	log.info(dianResponse1.getStatusMessage());
		    	log.info(dianResponse1.getStatusDescription());
		    	log.info(dianResponse1.getStatusCode());
		    	String [] dianRes = dianResponse1.getErrorMessage();
		    	log.info(dianRes[0]);
		    	mensaje = new MensajeDTO<>(1,0,dianRes[0],"");
			}
		    
		    
		   // log.info(dianResponse.getZipKey());
		  } catch (Exception e) {
			log.error(e.getMessage(), e);
			mensaje = new MensajeDTO<>(2, 2, "Falló al consultar el servicio Dian Error:" + e.getMessage(),"");
		  }
		
		return new ResponseEntity<MensajeDTO<?>>(mensaje, HttpStatus.OK);
		}
	    
	    @PostMapping("/firmarXML")
		public ResponseEntity<?> firmarXML(@RequestBody ConsultaDTO consulta) throws Exception {
	    	String outcome = null;		
			MensajeDTO<String> mensaje = new MensajeDTO<String>();
			String [] parametros = consulta.getNombres().split(",");
			mensaje.setObject(thctrliqService.firmaXML(parametros[0], parametros[1], parametros[2], parametros[3]));
	    	return new ResponseEntity<MensajeDTO<String>>(mensaje, HttpStatus.OK);
	    }
	    @PostMapping("sendXML")
	    public ResponseEntity<?> enviarXML(@RequestBody ConsultaDTO consulta)throws Exception{
	    	MensajeDTO<String>mensaje = new MensajeDTO<String>();
	    	thctrliqService.proSendXml(consulta);
	    	mensaje.setObject("Recepcionado Correctamente"); 
	    	return new ResponseEntity<MensajeDTO<String>>(mensaje, HttpStatus.OK);
	    }
	    @PostMapping("/hab/sendXML")
	    public ResponseEntity<?> HABenviarXML(@RequestBody ConsultaDTO consulta)throws Exception{
	    	MensajeDTO<String>mensaje = new MensajeDTO<String>();
	    	thctrliqService.HABproSendXml(consulta);
	    	mensaje.setObject("Habilitado Correctamente"); 
	    	return new ResponseEntity<MensajeDTO<String>>(mensaje, HttpStatus.OK);
	    }
	    @GetMapping("/download/{fhi}/{fhf}")
	    public ResponseEntity<org.springframework.core.io.Resource > getdownloadFile(@PathVariable Date fhi, @PathVariable Date fhf, HttpServletResponse response)throws Exception {
	    	InputStream is =  thctrliqService.GenPreviewThcptosDian(fhi, fhf);
	    	InputStreamResource file = new InputStreamResource(is);
	    	String namefile = "equivalenciaDIAN.xlsx";
	    	   return ResponseEntity.ok()
	    		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + namefile)
	    		        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
	    		        .body(file);
    		  }
	    @GetMapping("/cert/imprimir/{path}")
	    public void action_imprimir(@PathVariable String path, HttpServletResponse response)throws IOException, JRException{
	    	JasperPrint jasperPrint = null;
	    	 response.setContentType("application/x-download");
	    	 response.setHeader("Content-Disposition", String.format("attachment; filename=\"nomina_electronica.pdf\""));
	    	 
	    	 OutputStream out = response.getOutputStream();
	    	 jasperPrint =thctrliqService.action_imprimir(path);
	    	 JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	    	 
	    }
}
