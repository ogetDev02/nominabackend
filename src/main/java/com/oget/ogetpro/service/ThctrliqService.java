package com.oget.ogetpro.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.util.IOUtils;
import org.datacontract.schemas._2004._07.DianResponse.DianResponse;
import org.datacontract.schemas._2004._07.UploadDocumentResponse.UploadDocumentResponse;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateQuery;
import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oget.ogetpro.algorit.GFG;
import com.oget.ogetpro.algorit.QR;
import com.oget.ogetpro.date.GregorianCalendarDate;
import com.oget.ogetpro.dto.ConsultaDTO;
import com.oget.ogetpro.dto.ItemLiquidacionDTO;
import com.oget.ogetpro.dto.MensajeDTO;
import com.oget.ogetpro.dto.ThdocideDTO;
import com.oget.ogetpro.dto.ThestnomDTO;
import com.oget.ogetpro.dto.ThnomeDTO;
import com.oget.ogetpro.dto.ThtrackingDTO;
import com.oget.ogetpro.exception.ZMessManager;
import com.oget.ogetpro.exception.ZMessManager.DeletingException;
import com.oget.ogetpro.exception.ZMessManager.EmptyFieldException;
import com.oget.ogetpro.exception.ZMessManager.NullEntityExcepcion;
import com.oget.ogetpro.mappe.ThcptosdianMapper;
import com.oget.ogetpro.mappe.ThestnomMapper;
import com.oget.ogetpro.mappe.ThnomeMapper;
import com.oget.ogetpro.mappe.ThtrackingMapper;
import com.oget.ogetpro.mappe.ThvarnomMapper;
import com.oget.ogetpro.model.Thctrliq;
import com.oget.ogetpro.model.Thdocide;
import com.oget.ogetpro.model.Thestnom;
import com.oget.ogetpro.model.Thmovliq;
import com.oget.ogetpro.model.Thnome;
import com.oget.ogetpro.model.Thtracking;
import com.oget.ogetpro.model.Thvarnom;
import com.oget.ogetpro.repository.ThctrliqRepository;
import com.oget.ogetpro.repository.ThnomeRepository;
import com.oget.ogetpro.utility.Signer;
import com.oget.ogetpro.utility.Utilities;
import com.oget.ogetpro.utils.Helper;
import com.oget.ogetpro.xmlne.ExtensionContentType;
import com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType;
import com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Eliminar;
import com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar;
import com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.ReemplazandoPredecesor;
import com.oget.ogetpro.xmlne.NominaIndividualType;
import com.oget.ogetpro.xmlne.UBLExtensionType;
import com.oget.ogetpro.xmlne.UBLExtensionsType;
import com.oget.ogetpro.xmlne.NominaIndividualType.Deducciones;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados;
import com.oget.ogetpro.xmlne.NominaIndividualType.Empleador;
import com.oget.ogetpro.xmlne.NominaIndividualType.FechasPagos;
import com.oget.ogetpro.xmlne.NominaIndividualType.InformacionGeneral;
import com.oget.ogetpro.xmlne.NominaIndividualType.LugarGeneracionXML;
import com.oget.ogetpro.xmlne.NominaIndividualType.Novedad;
import com.oget.ogetpro.xmlne.NominaIndividualType.NumeroSecuenciaXML;
import com.oget.ogetpro.xmlne.NominaIndividualType.Pago;
import com.oget.ogetpro.xmlne.NominaIndividualType.Periodo;
import com.oget.ogetpro.xmlne.NominaIndividualType.ProveedorXML;
import com.oget.ogetpro.xmlne.NominaIndividualType.Trabajador;
import com.sun.el.parser.ParseException;

import colombia.dian.wcf.WSHttpBinding_IWcfDianCustomerServicesStub;

import com.oget.ogetpro.xmlne.NominaIndividualType.Deducciones.Anticipos;
import com.oget.ogetpro.xmlne.NominaIndividualType.Deducciones.FondoPension;
import com.oget.ogetpro.xmlne.NominaIndividualType.Deducciones.FondoSP;
import com.oget.ogetpro.xmlne.NominaIndividualType.Deducciones.Libranzas;
import com.oget.ogetpro.xmlne.NominaIndividualType.Deducciones.OtrasDeducciones;
import com.oget.ogetpro.xmlne.NominaIndividualType.Deducciones.PagosTerceros;
import com.oget.ogetpro.xmlne.NominaIndividualType.Deducciones.Salud;
import com.oget.ogetpro.xmlne.NominaIndividualType.Deducciones.Sanciones;
import com.oget.ogetpro.xmlne.NominaIndividualType.Deducciones.Sindicatos;
import com.oget.ogetpro.xmlne.NominaIndividualType.Deducciones.Libranzas.Libranza;
import com.oget.ogetpro.xmlne.NominaIndividualType.Deducciones.Sanciones.Sancion;
import com.oget.ogetpro.xmlne.NominaIndividualType.Deducciones.Sindicatos.Sindicato;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Auxilios;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Basico;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Bonificaciones;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.BonoEPCTVs;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Cesantias;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Comisiones;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Compensaciones;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HEDDFs;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HEDs;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HENDFs;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HENs;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HRDDFs;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HRNDFs;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HRNs;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HuelgasLegales;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Incapacidades;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Licencias;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.OtrosConceptos;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Primas;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Transporte;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Vacaciones;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Auxilios.Auxilio;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Bonificaciones.Bonificacion;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.BonoEPCTVs.BonoEPCTV;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Compensaciones.Compensacion;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HEDDFs.HEDDF;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HEDs.HED;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HENDFs.HENDF;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HENs.HEN;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HRDDFs.HRDDF;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HRNDFs.HRNDF;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HRNs.HRN;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.HuelgasLegales.HuelgaLegal;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Incapacidades.Incapacidad;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Licencias.LicenciaMP;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Licencias.LicenciaNR;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Licencias.LicenciaR;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.OtrosConceptos.OtroConcepto;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Vacaciones.VacacionesCompensadas;
import com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Vacaciones.VacacionesComunes;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Scope("singleton")
@Service
@Slf4j
public class ThctrliqService {
	
	@Autowired
	private ThctrliqRepository thctrliqRepository;
	@Autowired
	private ThnomeService thnomeService;
	@Autowired
	private ThvarnomService thvarnomService;
	@Autowired
	private ThtrackingService thtrackingService;
	@Autowired
	private ThestnomService thestnomService;
	@Autowired
	private Validator validator;
	@Autowired
	private ThnomeMapper delegate;
	@Autowired
	private ThtrackingMapper delegaTracking;
	@Autowired
	private ThvarnomMapper delegaMapper;
	@Autowired
	private ThestnomMapper delegaEstnom;
	
	

	/*KEY CERTIFCATE*/
    String certifado_pass ="";
    String certificado_file ="distriventas.pfx";
    public static final String  testsetid="60b6edcf-3464-473c-9016-b8d7e3f88086";
    public String certAlias()throws IOException{
    	ResourceBundle rs = null;

		try {
			rs = ResourceBundle.getBundle("seguridad");
		} catch (MissingResourceException var6) {
			throw new RuntimeException("Preferencias para WSS no encontradas");
		}

		String alias = rs.getString("org.apache.ws.security.crypto.merlin.keystore.alias");
		return alias;
    }

	
    public String certFile()throws IOException{
    	ResourceBundle rs = null;

		try {
			rs = ResourceBundle.getBundle("seguridad");
		} catch (MissingResourceException var6) {
			throw new RuntimeException("Preferencias para WSS no encontradas");
		}

		certificado_file = rs.getString("org.apache.ws.security.crypto.merlin.file");
		return certificado_file;
    }
	public String KeyPass()throws IOException{
		ResourceBundle rs = null;

		try {
			rs = ResourceBundle.getBundle("seguridad");
		} catch (MissingResourceException var6) {
			throw new RuntimeException("Preferencias para WSS no encontradas");
		}

		certifado_pass = rs.getString("org.apache.ws.security.crypto.merlin.keystore.password");
		return certifado_pass;
	}
	
	GregorianCalendarDate date = new GregorianCalendarDate();
	NominaIndividualType nominaIndividual = new NominaIndividualType();
	LugarGeneracionXML lugarGenXml = new LugarGeneracionXML();
	com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Anticipos anticiposDev = new com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.Anticipos();
	FondoPension fondoPension = new FondoPension();
	FondoSP fondoSp = new FondoSP();
	
	
	PagosTerceros pagosTerceros = new PagosTerceros();
	Salud salud = new Salud();
	Sanciones sanciones = new Sanciones();
	Sancion sancion = new Sancion();
	Sindicatos sindicatos = new Sindicatos();
	Sindicato sindicato = new Sindicato();
	Auxilios auxilios = new Auxilios();
	Auxilio auxilio = new Auxilio();
	Basico basico = new Basico();
	Bonificaciones bonificaciones = new Bonificaciones();
	Bonificacion bonificacion = new Bonificacion();
	BonoEPCTVs bonoEPCTVs = new BonoEPCTVs();
	BonoEPCTV bonoEPCTV = new BonoEPCTV();
	
	
	Compensacion compensacion = new Compensacion();
	Compensaciones compensaciones = new Compensaciones();
	HEDDFs hFs = new HEDDFs();
	HEDDF heddf = new HEDDF();
	HEDs heDs = new HEDs();
	HED hed = new HED();
	HENDFs hFs2 = new HENDFs();
	HENDF hendf = new HENDF();
	HENs hNs = new HENs();
	HEN  hen = new HEN();
	HRDDFs hrFs = new HRDDFs();
	HRDDF hrddf = new HRDDF();
	HRNDFs hrnFs = new HRNDFs();
	HRNDF hrndf = new HRNDF();
	HRNs hrNs = new HRNs();
	HRN  hrn = new HRN();
	HuelgasLegales huelgasLegales = new HuelgasLegales();
	HuelgaLegal huelgaLegal = new HuelgaLegal();
	Licencias licencias = new Licencias();
	
	com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.PagosTerceros pagosTerceros2 = new com.oget.ogetpro.xmlne.NominaIndividualType.Devengados.PagosTerceros();
	
	Vacaciones vacaciones = new Vacaciones();
	VacacionesComunes comunes = new VacacionesComunes();
	VacacionesCompensadas compensadas = new VacacionesCompensadas();
	Empleador empleador = new Empleador();
	FechasPagos fechasPagos = new FechasPagos();
	InformacionGeneral igeneral = new InformacionGeneral();
	Novedad novedad = new Novedad();
	NumeroSecuenciaXML numeroSecuenciaXML = new NumeroSecuenciaXML();
	Pago pago = new Pago();
	Periodo periodo = new Periodo();
	Trabajador trabajador = new Trabajador();
	Transporte transporte = new Transporte();
	ProveedorXML proveedorXML = new ProveedorXML();
	GFG gfg = new GFG();
	UBLExtensionsType extensionsType = new UBLExtensionsType();
	UBLExtensionType extensionType = new UBLExtensionType();
	ExtensionContentType contentType = new ExtensionContentType();
	
	@PersistenceContext()
    private EntityManager em;
	
    public void validate(Thctrliq thctrliq)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<Thctrliq>> constraintViolations =validator.validate(thctrliq);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
    @Modifying
    @Transactional(transactionManager = "nomTransactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void ConsultaXml(Date fhi, Date fhf) throws JAXBException,  java.text.ParseException, Exception {
    	log.info("start function ConsultaXml");
    	org.hibernate.Session session = (Session) this.em.getDelegate();
    	Query nativeQuery = session.createNamedQuery("NominaElectronica");
    	nativeQuery.setParameter("fhi", fhi, TemporalType.TIMESTAMP);
    	nativeQuery.setParameter("fhf", fhf , TemporalType.TIMESTAMP);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Object[]> data  = nativeQuery.getResultList();
		
		Query varnom1 = session.createNamedQuery("variablesne");
		varnom1.setParameter("llave", "CONT");
		List<Object[]>conteo = varnom1.getResultList();
		
		Long contador= Math.round(Double.parseDouble(conteo.get(0)[4].toString()));
//		Integer contador = 165;
		String concat = "";
		String path="";
		//DECLARACION DE OBJECTO;
		Libranzas libranzas;
		Libranza libranza;
		Anticipos anticiposDedu;
		Comisiones comisiones;
		OtrosConceptos conceptos;
		OtroConcepto concepto;
		Deducciones deducciones;
		Devengados devengados;
		OtrasDeducciones otrasDeducciones;
		Primas primas;
		Cesantias cesantias;
   		Incapacidades incapacidades;
		Incapacidad incapacidad;
		LicenciaMP licenciaMP;
		LicenciaNR licenciaNR;
		LicenciaR licenciaR;
		VacacionesComunes comunes;
		VacacionesCompensadas compensadas;
		List<Object[]>criterio;
		List<Object[]>vaca;
		List<Object[]>dias_trabajado;
		List<Object[]>thv21criter;
		List<Object[]>thlifpres;
		List<Object[]>ausents;
		for(Object[] cont : data) {
			try {
				libranza = new Libranza();
				libranzas  = new Libranzas();
				anticiposDedu  = new Anticipos();
				comisiones=  new Comisiones();
				devengados= new Devengados();
				 deducciones= new Deducciones();
				 conceptos = new OtrosConceptos();
				 concepto =   new OtroConcepto();
				 otrasDeducciones = new OtrasDeducciones();
				//CONSULTA DECRETO 2090 
				Query nativeQuery1 = session.createNamedQuery("CategoriaCriterio");
				nativeQuery1.setParameter("thvida1", cont[0]);
				criterio = nativeQuery1.getResultList();
				for(Object[] crite: criterio) {
					if (crite[2]!=null && crite[2].equals("2090")) {
						trabajador.setAltoRiesgoPension(true);
					}else {
						trabajador.setAltoRiesgoPension(false);
					}
				}
				//CONSULTA DE VACACIONES ASOCIADO
				Query nativeQuery2 = session.createNamedQuery("ConsulVacaciones");
				nativeQuery2.setParameter("fhi", fhi, TemporalType.TIMESTAMP);
		    	nativeQuery2.setParameter("fhf", fhf , TemporalType.TIMESTAMP);
		    	nativeQuery2.setParameter("thvida2", cont[22]);
		    	vaca = nativeQuery2.getResultList();
		    	if (vaca.size()>0 ) {
					for(Object[] vacaComunes: vaca) {
						comunes =new VacacionesComunes();
						comunes.setFechaInicio(date.ConvertFechaXml(vacaComunes[2].toString()));
						comunes.setFechaFin(date.ConvertFechaXml(vacaComunes[3].toString()));
						comunes.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(cont[58].toString()))));
						comunes.setPago(BigDecimal.valueOf(Double.parseDouble(cont[59].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
						vacaciones.getVacacionesComunes().clear();
						vacaciones.getVacacionesComunes().add(comunes);
						devengados.setVacaciones(vacaciones);
						
						//Pendiente el valor del pago
					}
					vaca.clear();
					}
		    	if(Double.parseDouble(cont[60].toString())>0 || Double.parseDouble(cont[61].toString())>0) {
					compensadas = new VacacionesCompensadas();
					compensadas.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(cont[60].toString()))));
					compensadas.setPago(BigDecimal.valueOf(Double.parseDouble(cont[61].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
					vacaciones.getVacacionesCompensadas().clear();
					vacaciones.getVacacionesCompensadas().add(compensadas);
					devengados.setVacaciones(vacaciones);
					}
			    /*Quien Genera la nomina electronica*/
				proveedorXML.setNIT((cont[12].toString()));
				proveedorXML.setRazonSocial(cont[17]!=null?cont[17].toString():"");
				proveedorXML.setDV(BigInteger.valueOf(Long.parseLong(cont[18].toString())));
				/*Datos del trabajador */
				trabajador.setTipoDocumento(BigInteger.valueOf(Long.parseLong(cont[23].toString())));
				trabajador.setCodigoTrabajador(cont[1]!=null? cont[1].toString():"");
				trabajador.setLugarTrabajoDepartamentoEstado(BigInteger.valueOf(76));
				trabajador.setNumeroDocumento(BigInteger.valueOf(Long.parseLong(cont[1].toString())));
				trabajador.setPrimerApellido(cont[2]!=null? cont[2].toString():"");
				trabajador.setSegundoApellido(cont[3]!=null? cont[3].toString():"");
				String [] name = cont[4].toString().split(" ");
				trabajador.setPrimerNombre(name.length>0?name[0]:"");
				trabajador.setOtrosNombres(name.length>1?name[1]:" ");
				trabajador.setLugarTrabajoPais("CO");
				/*Periodo de nomina electronica*/
				periodo.setFechaIngreso(date.ConvertFechaXml(date.XMLFechaToGregorian(cont[5].toString())));
				/*validacion de la fecha de retiro*/
				if(cont[6] != null ) {
				periodo.setFechaRetiro(date.ConvertFechaXml(date.XMLFechaToGregorian(cont[6].toString())));
				}else {
				periodo.setFechaRetiro(null);
				}
				periodo.setFechaLiquidacionInicio(date.ConvertFechaXml(date.XMLFechaToGregorian(cont[7].toString())));
				periodo.setFechaLiquidacionFin(date.ConvertFechaXml(date.XMLFechaToGregorian(cont[8].toString())));
//				/*query que consulta los dias laborados*/
				Query nativeQuery3 = session.createNamedQuery("diastrabajados");
				nativeQuery3.setParameter("fhi", fhi, TemporalType.TIMESTAMP);
		    	nativeQuery3.setParameter("fhf", fhf , TemporalType.TIMESTAMP);
		    	nativeQuery3.setParameter("thvida1", cont[0]);
		    	dias_trabajado = nativeQuery3.getResultList();
		    	for(Object[] dias: dias_trabajado) {
		    		if(dias.length >0) {
		    		periodo.setTiempoLaborado(dias[1]!=null?dias[1].toString():"0");
		    		}else {
		    			periodo.setTiempoLaborado("0");
		    		}
		    		
		    	}
		    
				periodo.setFechaGen(date.ConvertFechaXml(date.XMLFechaToGregorian(cont[9].toString())));
				/*secuencia del XML*/
				numeroSecuenciaXML.setCodigoTrabajador(cont[10]!=null? cont[10].toString():"");
				numeroSecuenciaXML.setPrefijo(cont[11]!=null?cont[11].toString():"");
				String cons1 = cont[7].toString().substring(0, 4);
				String cons2 = cont[7].toString().substring(5,7);
				concat = cons1+cons2;
				numeroSecuenciaXML.setConsecutivo(BigInteger.valueOf(Long.parseLong(concat+contador)));
				numeroSecuenciaXML.setNumero(cont[11].toString()+concat+contador);
				/*generacion del XML*/
				lugarGenXml.setIdioma("es");
				lugarGenXml.setPais("CO");
				lugarGenXml.setDepartamentoEstado(BigInteger.valueOf(Long.parseLong(cont[14].toString())));
				lugarGenXml.setMunicipioCiudad(BigInteger.valueOf(Long.parseLong(cont[14].toString()+cont[15].toString())));
				/*Informacion general */
				Query thvarnom = session.createNamedQuery("variablesne");
				thvarnom.setParameter("llave", "NE");
				List<Object[]> ne =  thvarnom.getResultList();
				igeneral.setTipoXML(BigInteger.valueOf(Math.round(Double.parseDouble(ne.get(0)[4].toString()))));
				
				thvarnom.setParameter("llave", "AMBIENTE");
				List<Object[]>amb = thvarnom.getResultList();
				igeneral.setAmbiente(BigInteger.valueOf(Math.round(Double.parseDouble(amb.get(0)[4].toString()))));
				
				thvarnom.setParameter("llave", "PATH");
				List<Object[]>outfile = thvarnom.getResultList();
				 path= outfile.get(0)[2].toString();
				igeneral.setVersion("V1.0: Documento Soporte de Pago de Nómina Electrónica");
				igeneral.setFechaGen(date.ConvertFechaXml(date.XMLFechaToGregorian(cont[9].toString())));
				igeneral.setHoraGen(date.ConvertFechaXml(date.XMLHoraToGregorian(cont[9].toString())+"-05:00"));
				
				/*Consulta thvarnom variable de pingID */
				thvarnom.setParameter("llave", "pingID");
				List<Object[]>softID = thvarnom.getResultList();
				//(NumNE** + FecNE** + HorNE** + ValDev** + ValDed** + ValTolNE** + NitNE** + DocEmp** + TipoXML** + Software-Pin**+TipAmb**)
				String cune = cont[11]+concat+contador+ date.XMLFechaParseGregorian(cont[9].toString()) + (date.XMLHoraToGregorian(cont[9].toString())+"-05:00") +(BigDecimal.valueOf(Double.parseDouble(cont[85].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN))+ (BigDecimal.valueOf( Double.parseDouble(cont[86].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN))
				+(BigDecimal.valueOf(Double.parseDouble(cont[85].toString())-Double.parseDouble(cont[86].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN)) 
				+cont[12].toString()+cont[1].toString()+ Math.round(Double.parseDouble(ne.get(0)[4].toString())) +Math.round(Double.parseDouble(softID.get(0)[4].toString()))+Math.round(Double.parseDouble(amb.get(0)[4].toString()));
				/*SE ASFIGNA EL SOFTWARE DE LA LLAVE pingID*/
				proveedorXML.setSoftwareID(softID.get(0)[2].toString());
				log.info(cont[11]+concat+contador+":"+cune);
				try {
				String softwarePin = softID.get(0)[2].toString()+Math.round(Double.parseDouble(softID.get(0)[4].toString())) +cont[11].toString()+concat+contador;
				proveedorXML.setSoftwareSC(gfg.encrypThisString(softwarePin));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			
				try {
					igeneral.setCUNE(gfg.encrypThisString(cune));
					igeneral.setEncripCUNE("CUNE-SHA384");
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				switch(cont[16].toString()) {
				case "S":
					igeneral.setPeriodoNomina(BigInteger.valueOf(1));
					break;
				case "D":
					igeneral.setPeriodoNomina(BigInteger.valueOf(2));
					break;
				case "C":
					igeneral.setPeriodoNomina(BigInteger.valueOf(3));
					break;
				case "Q":
					igeneral.setPeriodoNomina(BigInteger.valueOf(4));
					break;
				case "M":
					igeneral.setPeriodoNomina(BigInteger.valueOf(5));
					break;
				}
				igeneral.setTipoMoneda("COP");
				igeneral.setTRM(BigDecimal.valueOf(0.0));
				/*Datos de la compañia o empresa*/
				empleador.setRazonSocial(cont[17]!=null?cont[17].toString():"");
				empleador.setNIT((cont[12].toString()));
				empleador.setDV(BigInteger.valueOf(Long.parseLong(cont[18].toString())));
				empleador.setPais("CO");
				empleador.setDepartamentoEstado(BigInteger.valueOf(Long.parseLong(cont[14].toString())));
				empleador.setMunicipioCiudad(BigInteger.valueOf(Long.parseLong(cont[14].toString()+cont[15].toString())));
				empleador.setDireccion(cont[19]!=null?cont[19].toString():"");
				
				trabajador.setTipoTrabajador(cont[20]!=null?String.format("%02d", Integer.parseInt(cont[20].toString())):"01");
				trabajador.setSubTipoTrabajador(cont[21]!=null?String.format("%02d", Integer.parseInt(cont[21].toString())):"00");
				trabajador.setLugarTrabajoMunicipioCiudad(BigInteger.valueOf(Long.parseLong(cont[14].toString()+cont[15].toString())));
				
				
				/* validando el salario integrar del empleado con la info de tabla plinfapo */
				trabajador.setLugarTrabajoDireccion(cont[24]!=null?cont[24].toString():"");
				if (cont[25]== null) {
					trabajador.setSalarioIntegral(false);
				}else if(cont[25]!=null && cont[25].equals("S")) {
					trabajador.setSalarioIntegral(true);
				}else {
					trabajador.setSalarioIntegral(false);
				}
				//CONSULTA DE QUERY tipo criterio thvida21
				Query nativeQuery4 = session.createNamedQuery("tipoCriteriothv1");
				nativeQuery4.setParameter("thvida1", cont[0]);
		    	thv21criter = nativeQuery4.getResultList();
		    	for(Object[] thv21: thv21criter) {
				/* tipo contrato del trabajador validando thvida2_fecha_hasta y thvida21 criterios */
				if(cont[26]==null && thv21[2]!=null && thv21[2].equals("19") ||cont[26]==null && thv21[2]!=null && thv21[2].equals("20")) {
					trabajador.setTipoContrato(BigInteger.valueOf(4));
				}else if(cont[26]==null && thv21[2]!=null && thv21[2].equals("12") || cont[26]==null && thv21[2]!=null && thv21[2].equals("13")) {
					trabajador.setTipoContrato(BigInteger.valueOf(3));
				}else if(cont[26]!=null) {
					trabajador.setTipoContrato(BigInteger.valueOf(1));
				}
				else if(cont[26]==null) {
					trabajador.setTipoContrato(BigInteger.valueOf(1));
				}
		    	}
				trabajador.setSueldo(BigDecimal.valueOf(Double.parseDouble(cont[27]!=null?cont[27].toString():"0")).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				
				pago.setForma(BigInteger.valueOf(1));
				
				/*metodo de pago validando thvida2_ind_pago */
				if(cont[28]!=null && cont[28].equals(1)) {
					pago.setMetodo(BigInteger.valueOf(10));
				}else if(cont[28]!= null && cont[28].equals(2)){
					pago.setMetodo(BigInteger.valueOf(47));
				}else if(cont[28]!= null && cont[28].equals(3)) {
					pago.setMetodo(BigInteger.valueOf(20));
				}
				if(cont[28].equals(2)) {
					pago.setBanco(cont[31]!=null?cont[29].toString():"");
				
				/*tipo de cuenta bancaria */
				if (cont[30]!= null && cont[30].equals("A")) {
					pago.setTipoCuenta("AHORRO");
				}else if(cont[30]!=null && cont[30].equals("C")) {
					pago.setTipoCuenta("CORRIENTE");
				}
				/*pendiente validacion cuando numero de cuenta es NA*/
				
				pago.setNumeroCuenta(cont[31]!=null?cont[31].toString():"0");
				}
				fechasPagos.getFechaPago().clear();
				fechasPagos.getFechaPago().add(date.ConvertFechaXml(date.XMLFechaToGregorian(cont[9].toString())));
				
				if(Double.parseDouble(cont[37].toString())>0 ||Double.parseDouble(cont[38].toString())>0 || Double.parseDouble(cont[39].toString())>0) {
				hed.setCantidad(BigDecimal.valueOf(Double.parseDouble(cont[37].toString())));
				if(Double.parseDouble(cont[38].toString())>100) {
					hed.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[38].toString())-100).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				}else {
					hed.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[38].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
				}
				hed.setPago(BigDecimal.valueOf(Double.parseDouble(cont[39].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				heDs.getHED().clear();
				heDs.getHED().add(hed);
				devengados.setHEDs(heDs);
				}
				if(Double.parseDouble(cont[40].toString())>0 ||Double.parseDouble(cont[41].toString())>0 || Double.parseDouble(cont[42].toString())>0) {
				hen.setCantidad(BigDecimal.valueOf(Double.parseDouble(cont[40].toString())));
				hen.setPago(BigDecimal.valueOf(Double.parseDouble(cont[42].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				if(Double.parseDouble(cont[41].toString())>100) {
					hen.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[41].toString())-100).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				}else {
					hen.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[41].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
				}
				hNs.getHEN().clear();
				hNs.getHEN().add(hen);
				devengados.setHENs(hNs);
				}
				if(Double.parseDouble(cont[43].toString())>0 ||Double.parseDouble(cont[44].toString())>0 || Double.parseDouble(cont[45].toString())>0) {
				hrn.setCantidad(BigDecimal.valueOf(Double.parseDouble(cont[43].toString())));
				if(Double.parseDouble(cont[44].toString())>100) {
					hrn.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[44].toString())-100).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				}else {
					hrn.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[44].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				}
				
				hrn.setPago(BigDecimal.valueOf(Double.parseDouble(cont[45].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				hrNs.getHRN().clear();
				hrNs.getHRN().add(hrn);
				devengados.setHRNs(hrNs);
				}
				if(Double.parseDouble(cont[46].toString())>0 ||Double.parseDouble(cont[47].toString())>0 || Double.parseDouble(cont[48].toString())>0) {
				heddf.setCantidad(BigDecimal.valueOf(Double.parseDouble(cont[46].toString())));
				if(Double.parseDouble(cont[47].toString())>100) {
					heddf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[47].toString())-100).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				}else {
					heddf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[47].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
				}
				
				heddf.setPago(BigDecimal.valueOf(Double.parseDouble(cont[48].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				hFs.getHEDDF().clear();
				hFs.getHEDDF().add(heddf);
				devengados.setHEDDFs(hFs);
				}
				if(Double.parseDouble(cont[49].toString())>0 ||Double.parseDouble(cont[50].toString())>0 || Double.parseDouble(cont[51].toString())>0) {
				hrddf.setCantidad(BigDecimal.valueOf(Double.parseDouble(cont[49].toString())));
				if(Double.parseDouble(cont[51].toString())>100) {
					hrddf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[50].toString())-100).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
				}else {
					hrddf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[50].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
				}
				hrddf.setPago(BigDecimal.valueOf(Double.parseDouble(cont[51].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				hrFs.getHRDDF().clear();
				hrFs.getHRDDF().add(hrddf);
				devengados.setHRDDFs(hrFs);
				}
				if(Double.parseDouble(cont[52].toString())>0 ||Double.parseDouble(cont[53].toString())>0 || Double.parseDouble(cont[54].toString())>0) {
				hendf.setCantidad(BigDecimal.valueOf(Double.parseDouble(cont[52].toString())));
				if(Double.parseDouble(cont[54].toString())>100) {
					hendf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[53].toString())-100).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
				}else {
					hendf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[53].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				}
				
				hendf.setPago(BigDecimal.valueOf(Double.parseDouble(cont[54].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				hFs2.getHENDF().clear();
				hFs2.getHENDF().add(hendf);
				devengados.setHENDFs(hFs2);
				}
				if(Double.parseDouble(cont[55].toString())>0 ||Double.parseDouble(cont[56].toString())>0 || Double.parseDouble(cont[57].toString())>0) {
				hrndf.setCantidad(BigDecimal.valueOf(Double.parseDouble(cont[55].toString())));
				if(Double.parseDouble(cont[56].toString())>100) {
					hrndf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[56].toString())-100).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
				}else {
					hrndf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[56].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
				}
				
				hrndf.setPago(BigDecimal.valueOf(Double.parseDouble(cont[57].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				hrnFs.getHRNDF().clear();
				hrnFs.getHRNDF().add(hrndf);
				devengados.setHRNDFs(hrnFs);
				}
				if(Double.parseDouble(cont[34].toString())>0) {
				transporte.setAuxilioTransporte(BigDecimal.valueOf(Double.parseDouble(cont[34].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
					if(Double.parseDouble(cont[35].toString())>0 || Double.parseDouble(cont[36].toString())>0) {
				transporte.setViaticoManutAlojS(BigDecimal.valueOf(Double.parseDouble(cont[35].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				transporte.setViaticoManutAlojNS(BigDecimal.valueOf(Double.parseDouble(cont[36].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
					}
					devengados.getTransporte().clear();
					devengados.getTransporte().add(transporte);
				}
				if(trabajador.getTipoTrabajador().equals("19") || trabajador.getTipoTrabajador().equals("12")) {
				 basico.setDiasTrabajados(BigInteger.valueOf(0));
				 basico.setSueldoTrabajado(BigDecimal.valueOf(0).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				 devengados.setApoyoSost(BigDecimal.valueOf(Double.parseDouble(cont[89].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				 
				}else {
				basico.setDiasTrabajados(BigInteger.valueOf(Math.round(Double.parseDouble(cont[32].toString()))));
				basico.setSueldoTrabajado(BigDecimal.valueOf(Double.parseDouble(cont[33].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				}
				if(cont[87]!=null && Double.parseDouble(cont[62].toString())>0) {
				//CONSULTA DE QUERY tipo criterio thvida21
				Query nativeQuery7 = session.createNamedQuery("thlifpres");
				nativeQuery7.setParameter("thlifpres", cont[87]);
		    	thlifpres = nativeQuery7.getResultList();
		    	primas = new Primas();
		    	/*Validar como se debe evaluar los dias trabajados de thlifpres*/
		    	if(Double.parseDouble(cont[62].toString())>0 || Double.parseDouble(cont[63].toString())>0) {
		    	primas.setCantidad(BigInteger.valueOf(Long.parseLong(thlifpres.get(0)[11].toString())));
				primas.setPago(BigDecimal.valueOf(Double.parseDouble(cont[62].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				primas.setPagoNS(BigDecimal.valueOf(Double.parseDouble(cont[63].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				devengados.setPrimas(primas);
		    	}
		    	cesantias = new Cesantias();
		    	if(Double.parseDouble(cont[64].toString())>0 || Double.parseDouble(cont[65].toString())>0) {
		    	cesantias.setPago(BigInteger.valueOf(Math.round(Double.parseDouble(cont[64].toString()))));
				Double porc = ((Double.parseDouble(thlifpres.get(0)[11].toString())*12)/360);
				cesantias.setPorcentaje(BigDecimal.valueOf(porc).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				cesantias.setPagoIntereses(BigDecimal.valueOf(Double.parseDouble(cont[65].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				devengados.setCesantias(cesantias);
				thlifpres.clear();
		    		}
				}
				/*CONSULTA DE AUSENTISMO*/
				Query nativeQuery5 = session.createNamedQuery("ausentimosThvida2");
				nativeQuery5.setParameter("fhi", fhi, TemporalType.TIMESTAMP);
		    	nativeQuery5.setParameter("fhf", fhf , TemporalType.TIMESTAMP);
		    	nativeQuery5.setParameter("thvida2", cont[22]);
		    	ausents = nativeQuery5.getResultList();
		    	if(ausents != null && ausents.size() >0 ) {
		    	for(Object [] ausent: ausents ) {
		    			incapacidad = new Incapacidad();
		    			incapacidades = new Incapacidades();
						incapacidad.setFechaInicio(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[0].toString())));
						incapacidad.setFechaFin(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[1].toString())));
						
						switch(ausent[2].toString()) {
						case "0":
							incapacidad.setTipo(BigInteger.valueOf(1));
							incapacidad.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(ausent[3].toString()))));
							incapacidad.setPago(BigDecimal.valueOf(Double.parseDouble(ausent[5].toString())+ Double.parseDouble(ausent[6].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				
							incapacidades.getIncapacidad().clear();
							incapacidades.getIncapacidad().add(incapacidad);
							devengados.setIncapacidades(incapacidades);
							break;
						case "1":
							incapacidad.setTipo(BigInteger.valueOf(3));
							incapacidad.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(ausent[3].toString()))));
							incapacidad.setPago(BigDecimal.valueOf(Double.parseDouble(ausent[5].toString())+ Double.parseDouble(ausent[6].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				
							incapacidades.getIncapacidad().clear();
							incapacidades.getIncapacidad().add(incapacidad);
							devengados.setIncapacidades(incapacidades);
							break;
						case "2":
							licenciaMP = new LicenciaMP();
							licenciaMP.setFechaInicio(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[0].toString())));
							licenciaMP.setFechaFin(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[1].toString())));
							licenciaMP.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(ausent[3].toString()))));
							licenciaMP.setPago(BigDecimal.valueOf(Double.parseDouble(cont[70].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
							licencias.getLicenciaMP().clear();
							licencias.getLicenciaMP().add(licenciaMP);
							devengados.setLicencias(licencias);
							break;
						case "3":
							incapacidad.setTipo(BigInteger.valueOf(2));
							incapacidad.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(ausent[3].toString()))));
							incapacidad.setPago(BigDecimal.valueOf(Double.parseDouble(ausent[5].toString())+ Double.parseDouble(ausent[6].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				
							incapacidades.getIncapacidad().clear();
							incapacidades.getIncapacidad().add(incapacidad);
							devengados.setIncapacidades(incapacidades);
							break;
						case "4":
							licenciaNR = new LicenciaNR();
							licenciaNR.setFechaInicio(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[0].toString())));
							licenciaNR.setFechaFin(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[1].toString())));
							//SE VALIDA SI AUSENTIMOS FRACIONES HORAS
							if(Double.parseDouble(ausent[4].toString())<8) {
								licenciaNR.setCantidad(BigInteger.valueOf(0));
							}else {
							licenciaNR.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(ausent[3].toString()))));
							}
							licencias.getLicenciaNR().clear();
							licencias.getLicenciaNR().add(licenciaNR);
							devengados.setLicencias(licencias);
							break;
						case "5":
							if(vacaciones.getVacacionesComunes()==null) {
							licenciaR = new LicenciaR();
							licenciaR.setFechaInicio(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[0].toString())));
							licenciaR.setFechaFin(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[1].toString())));
							licenciaR.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(ausent[3].toString()))));
							licenciaR.setPago(BigDecimal.valueOf(Double.parseDouble(ausent[5].toString())+ Double.parseDouble(ausent[6].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
							licencias.getLicenciaR().clear();
							licencias.getLicenciaR().add(licenciaR);
							devengados.setLicencias(licencias);
							}
							break;
						}
		    		}

	
					}
				extensionsType.getUBLExtension();
				extensionType.getExtensionContent();
				contentType.getAny();
				contentType.setAny(null);
				extensionType.setExtensionContent(contentType);
				extensionsType.getUBLExtension().clear();
				extensionsType.getUBLExtension().add(extensionType);
				
				devengados.setBasico(basico);
				
				//if(Double.parseDouble(cont[79].toString())>0 ||Double.parseDouble(cont[78].toString())>0) {
				deducciones.getSalud();
				salud.setDeduccion(BigDecimal.valueOf(Double.parseDouble(cont[79].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				salud.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[78].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				deducciones.setSalud(salud);
				//}
				//if(Double.parseDouble(cont[80].toString())>0 || Double.parseDouble(cont[81].toString())>0) {
				deducciones.getFondoPension();
				fondoPension.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[80].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				fondoPension.setDeduccion(BigDecimal.valueOf(Double.parseDouble(cont[81].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				deducciones.setFondoPension(fondoPension);
				//}
				if(Double.parseDouble(cont[82].toString())>0 || Double.parseDouble(cont[83].toString())>0 || Double.parseDouble(cont[84].toString())>0) {
				deducciones.getFondoSP();
				fondoSp.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[82].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				fondoSp.setDeduccion(BigDecimal.valueOf(Double.parseDouble(cont[83].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				Double fSpValor = Double.parseDouble(cont[84].toString())-Double.parseDouble(cont[101].toString());
				if(fSpValor<0) {
					fSpValor=fSpValor*-1;
				}
				if(Double.parseDouble(cont[84].toString())>0) {
				fondoSp.setDeduccionSub(BigDecimal.valueOf(fSpValor).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				fondoSp.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[98].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				}
				deducciones.setFondoSP(fondoSp);
				}
				if(Double.parseDouble(cont[71].toString())>0  ) {
				bonificacion.setBonificacionS(BigDecimal.valueOf(Double.parseDouble(cont[71].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				bonificaciones.getBonificacion().clear();
				bonificaciones.getBonificacion().add(bonificacion);
				devengados.setBonificaciones(bonificaciones);
				}
				if(Double.parseDouble(cont[72].toString())>0) {
					bonificacion.setBonificacionNS(BigDecimal.valueOf(Double.parseDouble(cont[72].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
					bonificaciones.getBonificacion().clear();
					bonificaciones.getBonificacion().add(bonificacion);
					devengados.setBonificaciones(bonificaciones);
			
				}
				if(Double.parseDouble(cont[74].toString())>0 ) {
					auxilio.setAuxilioNS(BigDecimal.valueOf(Double.parseDouble(cont[74].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
					if(Double.parseDouble(cont[73].toString())>0) {
					auxilio.setAuxilioS(BigDecimal.valueOf(Double.parseDouble(cont[73].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
					}
					auxilios.getAuxilio().clear();
				auxilios.getAuxilio().add(auxilio);
				devengados.setAuxilios(auxilios);
				}
				if(Double.parseDouble(cont[76].toString())>0 || Double.parseDouble(cont[77].toString())>0) {
				devengados.setIndemnizacion(BigDecimal.valueOf(Double.parseDouble(cont[76].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				devengados.setReintegro(BigDecimal.valueOf(Double.parseDouble(cont[77].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				}	
//				novedad.getCUNENov();
//				novedad.setCUNENov("false");
				if(Double.parseDouble(cont[88].toString())>0) {
				otrasDeducciones.getOtraDeduccion().add(BigDecimal.valueOf(Double.parseDouble(cont[88].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				deducciones.setOtrasDeducciones(otrasDeducciones);
				}
				/*NUEVO NIE AGREGADOS A LA CONSULTA*/
				if(Double.parseDouble(cont[90].toString())>0) {
					deducciones.setReintegro(BigDecimal.valueOf(Double.parseDouble(cont[90].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				}
				if(Double.parseDouble(cont[91].toString())>0) {
					devengados.setReintegro(BigDecimal.valueOf(Double.parseDouble(cont[91].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				}
				if(Double.parseDouble(cont[92].toString())>0) {
					deducciones.setDeuda(BigDecimal.valueOf(Double.parseDouble(cont[92].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				}
				if(Double.parseDouble(cont[93].toString())>0) {
					deducciones.setCooperativa(BigDecimal.valueOf(Double.parseDouble(cont[93].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				}
				if(Double.parseDouble(cont[94].toString())>0) {
					concepto.setDescripcionConcepto("Conceptos No Salarial");
					concepto.setConceptoNS(BigDecimal.valueOf(Double.parseDouble(cont[94].toString())+Double.parseDouble(cont[101].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					conceptos.getOtroConcepto().add(concepto);
					devengados.setOtrosConceptos(conceptos);
				}
				if(Double.parseDouble(cont[95].toString())>0) {
					concepto.setDescripcionConcepto("Conceptos Salarial");
					concepto.setConceptoS(BigDecimal.valueOf(Double.parseDouble(cont[95].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					conceptos.getOtroConcepto().add(concepto);
					devengados.setOtrosConceptos(conceptos);
				}
				if(Double.parseDouble(cont[96].toString())>0) {
					comisiones.getComision().add(BigDecimal.valueOf(Double.parseDouble(cont[96].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					devengados.setComisiones(comisiones);
				}
				if(Double.parseDouble(cont[97].toString())>0) {
					anticiposDedu.getAnticipo().add(BigDecimal.valueOf(Double.parseDouble(cont[97].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
					deducciones.setAnticipos(anticiposDedu);
				}
				if(Double.parseDouble(cont[99].toString())>0) {
					deducciones.setRetencionFuente(BigDecimal.valueOf(Double.parseDouble(cont[99].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				}
					if (Double.parseDouble(cont[100].toString()) > 0) {
						libranza.setDeduccion(BigDecimal.valueOf(Double.parseDouble(cont[100].toString())).setScale(2,
								BigDecimal.ROUND_HALF_EVEN));
						libranza.setDescripcion("Descuento Libranza");
						libranzas.getLibranza().add(libranza);
						deducciones.setLibranzas(libranzas);
					}
//				nominaIndividual.setNovedad(novedad);
				nominaIndividual.setPeriodo(periodo);
				nominaIndividual.setProveedorXML(proveedorXML);
				nominaIndividual.setCodigoQR(igeneral.getAmbiente().equals(BigInteger.valueOf(1))?"https://catalogo-vpfe.dian.gov.co/document/searchqr?documentkey=" + gfg.encrypThisString(cune)
				:"https://catalogo-vpfe-hab.dian.gov.co/document/searchqr?documentkey=" + gfg.encrypThisString(cune));
				nominaIndividual.setTrabajador(trabajador);
				nominaIndividual.setNumeroSecuenciaXML(numeroSecuenciaXML);
				nominaIndividual.setLugarGeneracionXML(lugarGenXml);
				nominaIndividual.setDeducciones(deducciones);
				nominaIndividual.setFechasPagos(fechasPagos);
				nominaIndividual.setSchemaLocation("dian:gov:co:facturaelectronica:NominaIndividual NominaIndividualElectronicaXSD.xsd");
				nominaIndividual.setInformacionGeneral(igeneral);
				nominaIndividual.setEmpleador(empleador);
				nominaIndividual.setPago(pago);
				nominaIndividual.setDevengados(devengados);
				nominaIndividual.setDevengadosTotal(BigDecimal.valueOf(Double.parseDouble(cont[85].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				nominaIndividual.setDeduccionesTotal(BigDecimal.valueOf(Double.parseDouble(cont[86].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				nominaIndividual.setComprobanteTotal(BigDecimal.valueOf(Double.parseDouble(cont[85].toString())-Double.parseDouble(cont[86].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				nominaIndividual.setUBLExtensions(extensionsType);
				
				JAXBContext jaxbContext = JAXBContext.newInstance(NominaIndividualType.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				
				/*Ruta donde se crearan los archivos XML*/
				//jaxbMarshaller.marshal(nominaIndividual, System.out);
				jaxbMarshaller.marshal(nominaIndividual, new File(path+"sinfirmar/nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.xml"));
				//FIRMA DE XML
				try {
				firmaXML("C:/ogetdes/"+certFile(), KeyPass(), path+"sinfirmar/nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.xml", 
						path+"firmado/nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.xml");
				ComprimirXml(Paths.get(path+"firmado/nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.xml"),path+"firmado/z"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.zip");
				//MensajeDTO<?> msj= sendXml("z"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.zip");
				/*GENERADOR DE IMAGES CODIGO QR*/
				genQr(path+"images/", nominaIndividual.getCodigoQR() , "nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.png");
				}catch (Exception e) {
					log.error("[ConsultaXml] " + e.getMessage()  + " error");
					jaxbMarshaller.marshal(nominaIndividual, new File(path+"error/nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.xml"));
				}
				ThnomeDTO thnomeDTO = new ThnomeDTO();
					Thestnom thestnom = new Thestnom();
					ThdocideDTO thdocide = new ThdocideDTO();
					thestnom.setThestnomId(6);
					Query nativeQuery6 = session.createNamedQuery("consultipodoc");
					nativeQuery6.setParameter("iddoc", cont[23]);
			    	List<Object[]>tipodoc = nativeQuery6.getResultList();
			    	if(tipodoc !=null && tipodoc.size() >0 ) {
			    		for(Object [] tipodocs: tipodoc ) {
			    			thdocide.setThdocideId(Integer.parseInt(tipodocs[0].toString()));
			    			thdocide.setThdocideK1Cod(tipodocs[1].toString());
			    			thdocide.setThdocideDesc(tipodocs[2].toString());
			    			thdocide.setThdocideAbrev(tipodocs[3].toString());
					 }
			    	}
			    	java.util.Date fecha = sdf.parse(cont[9].toString());
			    	//thnomeDTO.setThnomeId(contador);
			    	thnomeDTO.setThnomeFechaEmision(fecha);
			    	thnomeDTO.setThestnomId_Thestnom(thestnom.getThestnomId());
			    	thnomeDTO.setThdocideId_Thdocide(thdocide.getThdocideId());
			    	thnomeDTO.setThnomeNombres(cont[4]!=null?cont[2].toString()+" "+cont[4].toString():"");
			    	thnomeDTO.setThnomeNroK3Document(cont[1]!=null? cont[1].toString():"");
			    	thnomeDTO.setThnomePrefijo(cont[11].toString()+concat+contador);
			    	thnomeDTO.setThnomeTotalComprobante(cont[27]!=null?Double.parseDouble(cont[27].toString()):0.0);
			    	thnomeDTO.setThnomeXfile("nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.xml");
			    	thnomeDTO.setThnomeZip("z"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.zip");
			    	thnomeDTO.setThnomeCune(gfg.encrypThisString(cune)); 
			    	
					Thnome thnome = delegate.thnomeDTOToThnome(thnomeDTO);
			        thnome = thnomeService.save(thnome);
			        
			        Long idconteo = Math.round(Double.parseDouble(conteo.get(0)[0].toString()));
					Optional<Thvarnom> thvarnom2 = thvarnomService.findById(idconteo.intValue());
			       	if(thvarnom2.isPresent()) {
			       		Thvarnom thvarnom3 = thvarnom2.get();
			       		Double d = contador.doubleValue()+1;
			       		thvarnom3.setThvarnomValor(d);
			       		thvarnomService.update(thvarnom3);
			       	}
			        
			     /*   ThtrackingDTO thtrackingDTO = new ThtrackingDTO();
			        thtrackingDTO.setThtrackingFecha(fecha);
			        thtrackingDTO.setThnomeId_Thnome(thnome.getThnomeId());
			        thtrackingDTO.setThestnomId_Thestnom(6);
			         Thtracking thtracking= delegaTracking.thtrackingDTOToThtracking(thtrackingDTO);
			         thtracking = thtrackingService.save(thtracking);*/
			        session.flush();
				contador++;
				
			} catch (DatatypeConfigurationException e) {
				log.error(e.getMessage());
			}
		}
		log.info("Finish end send xml");
 	}
    
    @Transactional("nomTransactionManager")
    public void GenXMLErrors(Date fhi, Date fhf)throws JAXBException,  java.text.ParseException, Exception {
    	
    	log.info("Start function GenXMLErrors()");
    	org.hibernate.Session session = (Session) this.em.getDelegate();
    	Query nativeQuery = session.createNamedQuery("ErrosrXml");
    	List<Object[]> ErrorFile  = nativeQuery.getResultList();
    	
//		Integer contador = 165;
		String concat = "";
		String path="";
		//DECLARACION DE OBJECTO;
		Libranzas libranzas;
		Libranza libranza;
		Anticipos anticiposDedu;
		Comisiones comisiones;
		OtrosConceptos conceptos;
		OtroConcepto concepto;
		Deducciones deducciones;
		Devengados devengados;
		OtrasDeducciones otrasDeducciones;
		Primas primas;
		Cesantias cesantias;
   		Incapacidades incapacidades;
		Incapacidad incapacidad;
		LicenciaMP licenciaMP;
		LicenciaNR licenciaNR;
		LicenciaR licenciaR;
		VacacionesComunes comunes;
		VacacionesCompensadas compensadas;
		List<Object[]>criterio;
		List<Object[]>vaca;
		List<Object[]>dias_trabajado;
		List<Object[]>thv21criter;
		List<Object[]>thlifpres;
		List<Object[]>ausents;
    	for (Object[] file:ErrorFile) {
    		Thnome thnome=thnomeService.findById(Integer.parseInt(file[3].toString())).get();
    		Query thv1 = session.createNamedQuery("thvida1");
    		thv1.setParameter("thvida", thnome.getThnomeNroK3Document());
    		List<Object[]>numdocut = thv1.getResultList();
    		
        	Long contador= Math.round(Double.parseDouble(thnome.getThnomeXfile().substring(15, 23)));
        	Query nativeQuery01 = session.createNamedQuery("NominaElectronicaError");
        	nativeQuery01.setParameter("thvida", numdocut.get(0)[0]);
        	nativeQuery01.setParameter("fhi", fhi, TemporalType.TIMESTAMP);
        	nativeQuery01.setParameter("fhf", fhf , TemporalType.TIMESTAMP);
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		List<Object[]> data  = nativeQuery01.getResultList();
    		for(Object[] cont:data) {
    			try {
    				libranza = new Libranza();
    				libranzas = new Libranzas();
    				anticiposDedu = new Anticipos();
    				comisiones= new Comisiones();
    				conceptos = new OtrosConceptos();
    				concepto = new OtroConcepto();
    				devengados= new Devengados();
    				 deducciones= new Deducciones();
    				 otrasDeducciones = new OtrasDeducciones();
    				//CONSULTA DECRETO 2090 
    				Query nativeQuery1 = session.createNamedQuery("CategoriaCriterio");
    				nativeQuery1.setParameter("thvida1", cont[0]);
    				criterio = nativeQuery1.getResultList();
    				for(Object[] crite: criterio) {
    					if (crite[2]!=null && crite[2].equals("2090")) {
    						trabajador.setAltoRiesgoPension(true);
    					}else {
    						trabajador.setAltoRiesgoPension(false);
    					}
    				}
    				//CONSULTA DE VACACIONES ASOCIADO
    				Query nativeQuery2 = session.createNamedQuery("ConsulVacaciones");
    				nativeQuery2.setParameter("fhi", fhi, TemporalType.TIMESTAMP);
    		    	nativeQuery2.setParameter("fhf", fhf , TemporalType.TIMESTAMP);
    		    	nativeQuery2.setParameter("thvida2", cont[22]);
    		    	vaca = nativeQuery2.getResultList();
    		    	if (vaca.size()>0 ) {
    					for(Object[] vacaComunes: vaca) {
    						comunes =new VacacionesComunes();
    						comunes.setFechaInicio(date.ConvertFechaXml(vacaComunes[2].toString()));
    						comunes.setFechaFin(date.ConvertFechaXml(vacaComunes[3].toString()));
    						comunes.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(cont[58].toString()))));
    						comunes.setPago(BigDecimal.valueOf(Double.parseDouble(cont[59].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    						vacaciones.getVacacionesComunes().clear();
    						vacaciones.getVacacionesComunes().add(comunes);
    						devengados.setVacaciones(vacaciones);
    						//Pendiente el valor del pago
    					}
    					vaca.clear();
    					}
					if(Double.parseDouble(cont[60].toString())>0 || Double.parseDouble(cont[61].toString())>0) {
					compensadas = new VacacionesCompensadas();
					compensadas.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(cont[60].toString()))));
					compensadas.setPago(BigDecimal.valueOf(Double.parseDouble(cont[61].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
					vacaciones.getVacacionesCompensadas().clear();
					vacaciones.getVacacionesCompensadas().add(compensadas);
					devengados.setVacaciones(vacaciones);
					}
    			    /*Quien Genera la nomina electronica*/
    				proveedorXML.setNIT((cont[12].toString()));
    				proveedorXML.setRazonSocial(cont[17]!=null?cont[17].toString():"");
    				proveedorXML.setDV(BigInteger.valueOf(Long.parseLong(cont[18].toString())));
    				/*Datos del trabajador */
    				trabajador.setTipoDocumento(BigInteger.valueOf(Long.parseLong(cont[23].toString())));
    				trabajador.setCodigoTrabajador(cont[1]!=null? cont[1].toString():"");
    				trabajador.setLugarTrabajoDepartamentoEstado(BigInteger.valueOf(76));
    				trabajador.setNumeroDocumento(BigInteger.valueOf(Long.parseLong(cont[1].toString())));
    				trabajador.setPrimerApellido(cont[2]!=null? cont[2].toString():"");
    				trabajador.setSegundoApellido(cont[3]!=null? cont[3].toString():"");
    				String [] name = cont[4].toString().split(" ");
    				trabajador.setPrimerNombre(name.length>0?name[0].trim():"");
    				trabajador.setOtrosNombres(name.length>1?name[1].trim():" ");
    				trabajador.setLugarTrabajoPais("CO");
    				/*Periodo de nomina electronica*/
    				periodo.setFechaIngreso(date.ConvertFechaXml(date.XMLFechaToGregorian(cont[5].toString())));
    				/*validacion de la fecha de retiro*/
    				if(cont[6] != null ) {
    				periodo.setFechaRetiro(date.ConvertFechaXml(date.XMLFechaToGregorian(cont[6].toString())));
    				}else {
    				periodo.setFechaRetiro(null);
    				}
    				periodo.setFechaLiquidacionInicio(date.ConvertFechaXml(date.XMLFechaToGregorian(cont[7].toString())));
    				periodo.setFechaLiquidacionFin(date.ConvertFechaXml(date.XMLFechaToGregorian(cont[8].toString())));
//    				/*query que consulta los dias laborados*/
    				Query nativeQuery3 = session.createNamedQuery("diastrabajados");
    				nativeQuery3.setParameter("fhi", fhi, TemporalType.TIMESTAMP);
    		    	nativeQuery3.setParameter("fhf", fhf , TemporalType.TIMESTAMP);
    		    	nativeQuery3.setParameter("thvida1", cont[0]);
    		    	dias_trabajado = nativeQuery3.getResultList();
    		    	for(Object[] dias: dias_trabajado) {
    		    		if(dias.length >0) {
    		    		periodo.setTiempoLaborado(dias[1]!=null?dias[1].toString():"0");
    		    		}else {
    		    			periodo.setTiempoLaborado("0");
    		    		}
    		    		
    		    	}
    		    
    				periodo.setFechaGen(date.ConvertFechaXml(date.XMLFechaToGregorian(cont[9].toString())));
    				/*secuencia del XML*/
    				numeroSecuenciaXML.setCodigoTrabajador(cont[10]!=null? cont[10].toString():"");
    				numeroSecuenciaXML.setPrefijo(cont[11]!=null?cont[11].toString():"");
    				String cons1 = cont[7].toString().substring(0, 4);
    				String cons2 = cont[7].toString().substring(5,7);
    				concat = cons1+cons2;
    				numeroSecuenciaXML.setConsecutivo(BigInteger.valueOf(Long.parseLong(concat+contador)));
    				numeroSecuenciaXML.setNumero(cont[11].toString()+concat+contador);
    				/*generacion del XML*/
    				lugarGenXml.setIdioma("es");
    				lugarGenXml.setPais("CO");
    				lugarGenXml.setDepartamentoEstado(BigInteger.valueOf(Long.parseLong(cont[14].toString())));
    				lugarGenXml.setMunicipioCiudad(BigInteger.valueOf(Long.parseLong(cont[14].toString()+cont[15].toString())));
    				/*Informacion general */
    				Query thvarnom = session.createNamedQuery("variablesne");
    				thvarnom.setParameter("llave", "NE");
    				List<Object[]> ne =  thvarnom.getResultList();
    				igeneral.setTipoXML(BigInteger.valueOf(Math.round(Double.parseDouble(ne.get(0)[4].toString()))));
    				
    				thvarnom.setParameter("llave", "AMBIENTE");
    				List<Object[]>amb = thvarnom.getResultList();
    				igeneral.setAmbiente(BigInteger.valueOf(Math.round(Double.parseDouble(amb.get(0)[4].toString()))));
    				
    				thvarnom.setParameter("llave", "PATH");
    				List<Object[]>outfile = thvarnom.getResultList();
    				 path= outfile.get(0)[2].toString();
    				igeneral.setVersion("V1.0: Documento Soporte de Pago de Nómina Electrónica");
    				igeneral.setFechaGen(date.ConvertFechaXml(date.XMLFechaToGregorian(cont[9].toString())));
    				igeneral.setHoraGen(date.ConvertFechaXml(date.XMLHoraToGregorian(cont[9].toString())+"-05:00"));
    				
    				/*Consulta thvarnom variable de pingID */
    				thvarnom.setParameter("llave", "pingID");
    				List<Object[]>softID = thvarnom.getResultList();
    				//(NumNE** + FecNE** + HorNE** + ValDev** + ValDed** + ValTolNE** + NitNE** + DocEmp** + TipoXML** + Software-Pin**+TipAmb**)
    				String cune = cont[11]+concat+contador+ date.XMLFechaParseGregorian(cont[9].toString()) + (date.XMLHoraToGregorian(cont[9].toString())+"-05:00") +(BigDecimal.valueOf(Double.parseDouble(cont[85].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN))+ (BigDecimal.valueOf( Double.parseDouble(cont[86].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN))
    				+(BigDecimal.valueOf(Double.parseDouble(cont[85].toString())-Double.parseDouble(cont[86].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN)) 
    				+cont[12].toString()+cont[1].toString()+ Math.round(Double.parseDouble(ne.get(0)[4].toString())) +Math.round(Double.parseDouble(softID.get(0)[4].toString()))+Math.round(Double.parseDouble(amb.get(0)[4].toString()));
    				/*SE ASFIGNA EL SOFTWARE DE LA LLAVE pingID*/
    				proveedorXML.setSoftwareID(softID.get(0)[2].toString());
    				log.info(cont[11]+concat+contador+":"+cune);
    				try {
    			    log.info("HuellaSC: " + softID.get(0)[2].toString()+Math.round(Double.parseDouble(softID.get(0)[4].toString()))+numeroSecuenciaXML.getNumero());		
    				String softwarePin = softID.get(0)[2].toString()+Math.round(Double.parseDouble(softID.get(0)[4].toString()))+numeroSecuenciaXML.getNumero();
    				proveedorXML.setSoftwareSC(gfg.encrypThisString(softwarePin));
    				} catch (NoSuchAlgorithmException e) {
    					e.printStackTrace();
    				}
    			
    				try {
    					igeneral.setCUNE(gfg.encrypThisString(cune));
    					igeneral.setEncripCUNE("CUNE-SHA384");
    				} catch (NoSuchAlgorithmException e) {
    					e.printStackTrace();
    				}
    				switch(cont[16].toString()) {
    				case "S":
    					igeneral.setPeriodoNomina(BigInteger.valueOf(1));
    					break;
    				case "D":
    					igeneral.setPeriodoNomina(BigInteger.valueOf(2));
    					break;
    				case "C":
    					igeneral.setPeriodoNomina(BigInteger.valueOf(3));
    					break;
    				case "Q":
    					igeneral.setPeriodoNomina(BigInteger.valueOf(4));
    					break;
    				case "M":
    					igeneral.setPeriodoNomina(BigInteger.valueOf(5));
    					break;
    				}
    				igeneral.setTipoMoneda("COP");
    				igeneral.setTRM(BigDecimal.valueOf(0.0));
    				/*Datos de la compañia o empresa*/
    				empleador.setRazonSocial(cont[17]!=null?cont[17].toString():"");
    				empleador.setNIT((cont[12].toString()));
    				empleador.setDV(BigInteger.valueOf(Long.parseLong(cont[18].toString())));
    				empleador.setPais("CO");
    				empleador.setDepartamentoEstado(BigInteger.valueOf(Long.parseLong(cont[14].toString())));
    				empleador.setMunicipioCiudad(BigInteger.valueOf(Long.parseLong(cont[14].toString()+cont[15].toString())));
    				empleador.setDireccion(cont[19]!=null?cont[19].toString():"");
    				
    				trabajador.setTipoTrabajador(cont[20]!=null?String.format("%02d", Integer.parseInt(cont[20].toString())):"01");
    				trabajador.setSubTipoTrabajador(cont[21]!=null?String.format("%02d", Integer.parseInt(cont[21].toString())):"00");
    				trabajador.setLugarTrabajoMunicipioCiudad(BigInteger.valueOf(Long.parseLong(cont[14].toString()+cont[15].toString())));
    				
    				
    				/* validando el salario integrar del empleado con la info de tabla plinfapo */
    				trabajador.setLugarTrabajoDireccion(cont[24]!=null?cont[24].toString():"");
    				if (cont[25]== null) {
    					trabajador.setSalarioIntegral(false);
    				}else if(cont[25]!=null && cont[25].equals("S")) {
    					trabajador.setSalarioIntegral(true);
    				}else {
    					trabajador.setSalarioIntegral(false);
    				}
    				//CONSULTA DE QUERY tipo criterio thvida21
    				Query nativeQuery4 = session.createNamedQuery("tipoCriteriothv1");
    				nativeQuery4.setParameter("thvida1", cont[0]);
    		    	thv21criter = nativeQuery4.getResultList();
    		    	for(Object[] thv21: thv21criter) {
    				/* tipo contrato del trabajador validando thvida2_fecha_hasta y thvida21 criterios */
    				if(cont[26]==null && thv21[2]!=null && thv21[2].equals("19") ||cont[26]==null && thv21[2]!=null && thv21[2].equals("20")) {
    					trabajador.setTipoContrato(BigInteger.valueOf(4));
    				}else if(cont[26]==null && thv21[2]!=null && thv21[2].equals("12") || cont[26]==null && thv21[2]!=null && thv21[2].equals("13")) {
    					trabajador.setTipoContrato(BigInteger.valueOf(3));
    				}else if(cont[26]!=null) {
    					trabajador.setTipoContrato(BigInteger.valueOf(1));
    				}
    				else if(cont[26]==null) {
    					trabajador.setTipoContrato(BigInteger.valueOf(1));
    				}
    		    	}
    				trabajador.setSueldo(BigDecimal.valueOf(Double.parseDouble(cont[27]!=null?cont[27].toString():"0")).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				
    				pago.setForma(BigInteger.valueOf(1));
    				
    				/*metodo de pago validando thvida2_ind_pago */
    				if(cont[28]!=null && cont[28].equals(1)) {
    					pago.setMetodo(BigInteger.valueOf(10));
    				}else if(cont[28]!= null && cont[28].equals(2)){
    					pago.setMetodo(BigInteger.valueOf(47));
    				}else if(cont[28]!= null && cont[28].equals(3)) {
    					pago.setMetodo(BigInteger.valueOf(20));
    				}
    				if(cont[28].equals(2)) {
    				pago.setBanco(cont[31]!=null?cont[29].toString():"");
    				/*tipo de cuenta bancaria */
    				if (cont[30]!= null && cont[30].equals("A")) {
    					pago.setTipoCuenta("AHORRO");
    				}else if(cont[30]!=null && cont[30].equals("C")) {
    					pago.setTipoCuenta("CORRIENTE");
    				}
    				/*pendiente validacion cuando numero de cuenta es NA*/
    				
    				pago.setNumeroCuenta(cont[31]!=null?cont[31].toString():"0");
    				}
    				fechasPagos.getFechaPago().clear();
    				fechasPagos.getFechaPago().add(date.ConvertFechaXml(date.XMLFechaToGregorian(cont[9].toString())));
    				
    				if(Double.parseDouble(cont[37].toString())>0 ||Double.parseDouble(cont[38].toString())>0 || Double.parseDouble(cont[39].toString())>0) {
    					hed.setCantidad(BigDecimal.valueOf(Double.parseDouble(cont[37].toString())));
    					if(Double.parseDouble(cont[38].toString())>100) {
    						hed.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[38].toString())-100).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					}else {
    						hed.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[38].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
    					}
    					hed.setPago(BigDecimal.valueOf(Double.parseDouble(cont[39].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					heDs.getHED().clear();
    					heDs.getHED().add(hed);
    					devengados.setHEDs(heDs);
    					}
    					if(Double.parseDouble(cont[40].toString())>0 ||Double.parseDouble(cont[41].toString())>0 || Double.parseDouble(cont[42].toString())>0) {
    					hen.setCantidad(BigDecimal.valueOf(Double.parseDouble(cont[40].toString())));
    					hen.setPago(BigDecimal.valueOf(Double.parseDouble(cont[42].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					if(Double.parseDouble(cont[41].toString())>100) {
    						hen.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[41].toString())-100).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					}else {
    						hen.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[41].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
    					}
    					hNs.getHEN().clear();
    					hNs.getHEN().add(hen);
    					devengados.setHENs(hNs);
    					}
    					if(Double.parseDouble(cont[43].toString())>0 ||Double.parseDouble(cont[44].toString())>0 || Double.parseDouble(cont[45].toString())>0) {
    					hrn.setCantidad(BigDecimal.valueOf(Double.parseDouble(cont[43].toString())));
    					if(Double.parseDouble(cont[44].toString())>100) {
    						hrn.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[44].toString())-100).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					}else {
    						hrn.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[44].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					}
    					
    					hrn.setPago(BigDecimal.valueOf(Double.parseDouble(cont[45].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					hrNs.getHRN().clear();
    					hrNs.getHRN().add(hrn);
    					devengados.setHRNs(hrNs);
    					}
    					if(Double.parseDouble(cont[46].toString())>0 ||Double.parseDouble(cont[47].toString())>0 || Double.parseDouble(cont[48].toString())>0) {
    					heddf.setCantidad(BigDecimal.valueOf(Double.parseDouble(cont[46].toString())));
    					if(Double.parseDouble(cont[47].toString())>100) {
    						heddf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[47].toString())-100).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					}else {
    						heddf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[47].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
    					}
    					
    					heddf.setPago(BigDecimal.valueOf(Double.parseDouble(cont[48].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					hFs.getHEDDF().clear();
    					hFs.getHEDDF().add(heddf);
    					devengados.setHEDDFs(hFs);
    					}
    					if(Double.parseDouble(cont[49].toString())>0 ||Double.parseDouble(cont[50].toString())>0 || Double.parseDouble(cont[51].toString())>0) {
    					hrddf.setCantidad(BigDecimal.valueOf(Double.parseDouble(cont[49].toString())));
    					if(Double.parseDouble(cont[51].toString())>100) {
    						hrddf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[50].toString())-100).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
    					}else {
    						hrddf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[50].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
    					}
    					hrddf.setPago(BigDecimal.valueOf(Double.parseDouble(cont[51].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					hrFs.getHRDDF().clear();
    					hrFs.getHRDDF().add(hrddf);
    					devengados.setHRDDFs(hrFs);
    					}
    					if(Double.parseDouble(cont[52].toString())>0 ||Double.parseDouble(cont[53].toString())>0 || Double.parseDouble(cont[54].toString())>0) {
    					hendf.setCantidad(BigDecimal.valueOf(Double.parseDouble(cont[52].toString())));
    					if(Double.parseDouble(cont[54].toString())>100) {
    						hendf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[53].toString())-100).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
    					}else {
    						hendf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[53].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					}
    					
    					hendf.setPago(BigDecimal.valueOf(Double.parseDouble(cont[54].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					hFs2.getHENDF().clear();
    					hFs2.getHENDF().add(hendf);
    					devengados.setHENDFs(hFs2);
    					}
    					if(Double.parseDouble(cont[55].toString())>0 ||Double.parseDouble(cont[56].toString())>0 || Double.parseDouble(cont[57].toString())>0) {
    					hrndf.setCantidad(BigDecimal.valueOf(Double.parseDouble(cont[55].toString())));
    					if(Double.parseDouble(cont[56].toString())>100) {
    						hrndf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[56].toString())-100).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
    					}else {
    						hrndf.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[56].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));	
    					}
    					
    					hrndf.setPago(BigDecimal.valueOf(Double.parseDouble(cont[57].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					hrnFs.getHRNDF().clear();
    					hrnFs.getHRNDF().add(hrndf);
    					devengados.setHRNDFs(hrnFs);
    					}
    				if(Double.parseDouble(cont[34].toString())>0) {
    				transporte.setAuxilioTransporte(BigDecimal.valueOf(Double.parseDouble(cont[34].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					if(Double.parseDouble(cont[35].toString())>0 || Double.parseDouble(cont[36].toString())>0) {
    				transporte.setViaticoManutAlojS(BigDecimal.valueOf(Double.parseDouble(cont[35].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				transporte.setViaticoManutAlojNS(BigDecimal.valueOf(Double.parseDouble(cont[36].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					}
    					devengados.getTransporte().clear();
    					devengados.getTransporte().add(transporte);
    				}
    				if(trabajador.getTipoTrabajador().equals("19") || trabajador.getTipoTrabajador().equals("12")) {
    				 basico.setDiasTrabajados(BigInteger.valueOf(0));
    				 basico.setSueldoTrabajado(BigDecimal.valueOf(0).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    				 devengados.setApoyoSost(BigDecimal.valueOf(Double.parseDouble(cont[89].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				 
    				}else {
    				basico.setDiasTrabajados(BigInteger.valueOf(Math.round(Double.parseDouble(cont[32].toString()))));
    				basico.setSueldoTrabajado(BigDecimal.valueOf(Double.parseDouble(cont[33].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				}
    				if(cont[87]!=null && Double.parseDouble(cont[62].toString())>0) {
    				//CONSULTA DE QUERY tipo criterio thvida21
    				Query nativeQuery7 = session.createNamedQuery("thlifpres");
    				nativeQuery7.setParameter("thlifpres", cont[87]);
    		    	thlifpres = nativeQuery7.getResultList();
    		    	primas = new Primas();
    		    	/*Validar como se debe evaluar los dias trabajados de thlifpres*/
    		    	if(Double.parseDouble(cont[62].toString())>0 || Double.parseDouble(cont[63].toString())>0) {
    		    	primas.setCantidad(BigInteger.valueOf(Long.parseLong(thlifpres.get(0)[11].toString())));
    				primas.setPago(BigDecimal.valueOf(Double.parseDouble(cont[62].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				primas.setPagoNS(BigDecimal.valueOf(Double.parseDouble(cont[63].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				devengados.setPrimas(primas);
    		    	}
    		    	cesantias = new Cesantias();
    		    	if(Double.parseDouble(cont[64].toString())>0 || Double.parseDouble(cont[65].toString())>0) {
    		    	cesantias.setPago(BigInteger.valueOf(Math.round(Double.parseDouble(cont[64].toString()))));
    				Double porc = ((Double.parseDouble(thlifpres.get(0)[11].toString())*12)/360);
    				cesantias.setPorcentaje(BigDecimal.valueOf(porc).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				cesantias.setPagoIntereses(BigDecimal.valueOf(Double.parseDouble(cont[65].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				devengados.setCesantias(cesantias);
    				thlifpres.clear();
    		    		}
    				}
    				/*CONSULTA DE AUSENTISMO*/
    				Query nativeQuery5 = session.createNamedQuery("ausentimosThvida2");
    				nativeQuery5.setParameter("fhi", fhi, TemporalType.TIMESTAMP);
    		    	nativeQuery5.setParameter("fhf", fhf , TemporalType.TIMESTAMP);
    		    	nativeQuery5.setParameter("thvida2", cont[22]);
    		    	ausents = nativeQuery5.getResultList();
    		    	if(ausents != null && ausents.size() >0 ) {
    		    	for(Object [] ausent: ausents ) {
    		    			incapacidad = new Incapacidad();
    		    			incapacidades = new Incapacidades();
    						incapacidad.setFechaInicio(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[0].toString())));
    						incapacidad.setFechaFin(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[1].toString())));
    						
    						switch(ausent[2].toString()) {
    						case "0":
    							incapacidad.setTipo(BigInteger.valueOf(1));
    							incapacidad.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(ausent[3].toString()))));
    							incapacidad.setPago(BigDecimal.valueOf(Double.parseDouble(ausent[5].toString())+ Double.parseDouble(ausent[6].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				
    							incapacidades.getIncapacidad().clear();
    							incapacidades.getIncapacidad().add(incapacidad);
    							devengados.setIncapacidades(incapacidades);
    							break;
    						case "1":
    							incapacidad.setTipo(BigInteger.valueOf(3));
    							incapacidad.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(ausent[3].toString()))));
    							incapacidad.setPago(BigDecimal.valueOf(Double.parseDouble(ausent[5].toString())+ Double.parseDouble(ausent[6].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				
    							incapacidades.getIncapacidad().clear();
    							incapacidades.getIncapacidad().add(incapacidad);
    							devengados.setIncapacidades(incapacidades);
    							break;
    						case "2":
    							licenciaMP = new LicenciaMP();
    							licenciaMP.setFechaInicio(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[0].toString())));
    							licenciaMP.setFechaFin(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[1].toString())));
    							licenciaMP.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(ausent[3].toString()))));
    							licenciaMP.setPago(BigDecimal.valueOf(Double.parseDouble(cont[70].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    							licencias.getLicenciaMP().clear();
    							licencias.getLicenciaMP().add(licenciaMP);
    							devengados.setLicencias(licencias);
    							break;
    						case "3":
    							incapacidad.setTipo(BigInteger.valueOf(2));
    							incapacidad.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(ausent[3].toString()))));
    							incapacidad.setPago(BigDecimal.valueOf(Double.parseDouble(ausent[5].toString())+ Double.parseDouble(ausent[6].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				
    							incapacidades.getIncapacidad().clear();
    							incapacidades.getIncapacidad().add(incapacidad);
    							devengados.setIncapacidades(incapacidades);
    							break;
    						case "4":
    							licenciaNR = new LicenciaNR();
    							licenciaNR.setFechaInicio(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[0].toString())));
    							licenciaNR.setFechaFin(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[1].toString())));
    							//SE VALIDA SI AUSENTIMOS FRACIONES HORAS
    							if(Double.parseDouble(ausent[4].toString())<8) {
    								licenciaNR.setCantidad(BigInteger.valueOf(0));
    							}else {
    							licenciaNR.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(ausent[3].toString()))));
    							}
    							licencias.getLicenciaNR().clear();
    							licencias.getLicenciaNR().add(licenciaNR);
    							devengados.setLicencias(licencias);
    							break;
    						case "5":
    							if(vacaciones.getVacacionesComunes()==null) {
    								licenciaR = new LicenciaR();
        							licenciaR.setFechaInicio(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[0].toString())));
        							licenciaR.setFechaFin(date.ConvertFechaXml(date.XMLFechaToGregorian(ausent[1].toString())));
        							licenciaR.setCantidad(BigInteger.valueOf(Math.round(Double.parseDouble(ausent[3].toString()))));
        							licenciaR.setPago(BigDecimal.valueOf(Double.parseDouble(ausent[5].toString())+ Double.parseDouble(ausent[6].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
        							licencias.getLicenciaR().clear();
        							licencias.getLicenciaR().add(licenciaR);
        							devengados.setLicencias(licencias);	
    							}
    							
    							break;
    						}
    		    		}

    	
    					}
    				extensionsType.getUBLExtension();
    				extensionType.getExtensionContent();
    				contentType.getAny();
    				contentType.setAny(null);
    				extensionType.setExtensionContent(contentType);
    				extensionsType.getUBLExtension().clear();
    				extensionsType.getUBLExtension().add(extensionType);
    				
    				devengados.setBasico(basico);
    				
    				//if(Double.parseDouble(cont[79].toString())>0 ||Double.parseDouble(cont[78].toString())>0) {
    				deducciones.getSalud();
    				salud.setDeduccion(BigDecimal.valueOf(Double.parseDouble(cont[79].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    				salud.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[78].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				deducciones.setSalud(salud);
    				//}
    				//if(Double.parseDouble(cont[80].toString())>0 || Double.parseDouble(cont[81].toString())>0) {
    				deducciones.getFondoPension();
    				fondoPension.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[80].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				fondoPension.setDeduccion(BigDecimal.valueOf(Double.parseDouble(cont[81].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				deducciones.setFondoPension(fondoPension);
    				//}
    				if(Double.parseDouble(cont[82].toString())>0 || Double.parseDouble(cont[83].toString())>0 || Double.parseDouble(cont[84].toString())>0) {
    				deducciones.getFondoSP();
    				fondoSp.setPorcentaje(BigDecimal.valueOf(Double.parseDouble(cont[82].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				fondoSp.setDeduccion(BigDecimal.valueOf(Double.parseDouble(cont[83].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				Double fSpValor = Double.parseDouble(cont[84].toString())-Double.parseDouble(cont[101].toString());
    				if(fSpValor<=0) {
    					fSpValor=fSpValor*-1;
    				}else {
    					fSpValor =  Double.parseDouble(cont[84].toString());
    				}
    				if(Double.parseDouble(cont[84].toString())>0) {
    				fondoSp.setDeduccionSub(BigDecimal.valueOf(fSpValor).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				fondoSp.setPorcentajeSub(BigDecimal.valueOf(Double.parseDouble(cont[98].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				}
    				deducciones.setFondoSP(fondoSp);
    				}
    				if(Double.parseDouble(cont[71].toString())>0  ) {
    				bonificacion.setBonificacionS(BigDecimal.valueOf(Double.parseDouble(cont[71].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				bonificaciones.getBonificacion().clear();
    				bonificaciones.getBonificacion().add(bonificacion);
    				devengados.setBonificaciones(bonificaciones);
    				}
    				if(Double.parseDouble(cont[72].toString())>0) {
    					bonificacion.setBonificacionNS(BigDecimal.valueOf(Double.parseDouble(cont[72].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					bonificaciones.getBonificacion().clear();
    					bonificaciones.getBonificacion().add(bonificacion);
    					devengados.setBonificaciones(bonificaciones);
    			
    				}
    				if(Double.parseDouble(cont[74].toString())>0 ) {
    					auxilio.setAuxilioNS(BigDecimal.valueOf(Double.parseDouble(cont[74].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					if(Double.parseDouble(cont[73].toString())>0) {
    					auxilio.setAuxilioS(BigDecimal.valueOf(Double.parseDouble(cont[73].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    					}
    					auxilios.getAuxilio().clear();
    				auxilios.getAuxilio().add(auxilio);
    				devengados.setAuxilios(auxilios);
    				}
    				if(Double.parseDouble(cont[76].toString())>0 || Double.parseDouble(cont[77].toString())>0) {
    				devengados.setIndemnizacion(BigDecimal.valueOf(Double.parseDouble(cont[76].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				devengados.setReintegro(BigDecimal.valueOf(Double.parseDouble(cont[77].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				}	
//    				novedad.getCUNENov();
//    				novedad.setCUNENov("false");
    				if(Double.parseDouble(cont[88].toString())>0) {
    				otrasDeducciones.getOtraDeduccion().add(BigDecimal.valueOf(Double.parseDouble(cont[88].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    				deducciones.setOtrasDeducciones(otrasDeducciones);
    				}
    				/*NUEVO NIE AGREGADOS A LA CONSULTA*/
    				if(Double.parseDouble(cont[90].toString())>0) {
    					deducciones.setReintegro(BigDecimal.valueOf(Double.parseDouble(cont[90].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    				}
    				if(Double.parseDouble(cont[91].toString())>0) {
    					devengados.setReintegro(BigDecimal.valueOf(Double.parseDouble(cont[91].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    				}
    				if(Double.parseDouble(cont[92].toString())>0) {
    					deducciones.setDeuda(BigDecimal.valueOf(Double.parseDouble(cont[92].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    				}
    				if(Double.parseDouble(cont[93].toString())>0) {
    					deducciones.setCooperativa(BigDecimal.valueOf(Double.parseDouble(cont[93].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    				}
    				if(Double.parseDouble(cont[94].toString())>0) {
    					concepto.setDescripcionConcepto("Conceptos No Salarial");
    					concepto.setConceptoNS(BigDecimal.valueOf(Double.parseDouble(cont[94].toString())+Double.parseDouble(cont[101].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    					conceptos.getOtroConcepto().add(concepto);
    					devengados.setOtrosConceptos(conceptos);
    				}
    				if(Double.parseDouble(cont[95].toString())>0) {
    					concepto.setDescripcionConcepto("Conceptos Salarial");
    					concepto.setConceptoS(BigDecimal.valueOf(Double.parseDouble(cont[95].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    					conceptos.getOtroConcepto().add(concepto);
    					devengados.setOtrosConceptos(conceptos);
    				}
    				if(Double.parseDouble(cont[96].toString())>0) {
    					comisiones.getComision().add(BigDecimal.valueOf(Double.parseDouble(cont[96].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    					devengados.setComisiones(comisiones);
    				}
    				if(Double.parseDouble(cont[97].toString())>0) {
    					anticiposDedu.getAnticipo().add(BigDecimal.valueOf(Double.parseDouble(cont[97].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    					deducciones.setAnticipos(anticiposDedu);
    				}
    				if(Double.parseDouble(cont[99].toString())>0) {
    					deducciones.setRetencionFuente(BigDecimal.valueOf(Double.parseDouble(cont[99].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    				}
    				if(Double.parseDouble(cont[100].toString())>0) {
    					libranza.setDeduccion(BigDecimal.valueOf(Double.parseDouble(cont[100].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    					libranza.setDescripcion("Descuento Libranza");
    					libranzas.getLibranza().add(libranza);
    					deducciones.setLibranzas(libranzas);
    				}
//    				nominaIndividual.setNovedad(novedad);
    				nominaIndividual.setPeriodo(periodo);
    				nominaIndividual.setProveedorXML(proveedorXML);
    				nominaIndividual.setCodigoQR(igeneral.getAmbiente().equals(BigInteger.valueOf(1))?"https://catalogo-vpfe.dian.gov.co/document/searchqr?documentkey=" + gfg.encrypThisString(cune):
    				"https://catalogo-vpfe-hab.dian.gov.co/document/searchqr?documentkey=" + gfg.encrypThisString(cune));
    				nominaIndividual.setTrabajador(trabajador);
    				nominaIndividual.setNumeroSecuenciaXML(numeroSecuenciaXML);
    				nominaIndividual.setLugarGeneracionXML(lugarGenXml);
    				nominaIndividual.setDeducciones(deducciones);
    				nominaIndividual.setFechasPagos(fechasPagos);
    				nominaIndividual.setSchemaLocation("dian:gov:co:facturaelectronica:NominaIndividual NominaIndividualElectronicaXSD.xsd");
    				nominaIndividual.setInformacionGeneral(igeneral);
    				nominaIndividual.setEmpleador(empleador);
    				nominaIndividual.setPago(pago);
    				nominaIndividual.setDevengados(devengados);
    				nominaIndividual.setDevengadosTotal(BigDecimal.valueOf(Double.parseDouble(cont[85].toString())).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    				nominaIndividual.setDeduccionesTotal(BigDecimal.valueOf(Double.parseDouble(cont[86].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				nominaIndividual.setComprobanteTotal(BigDecimal.valueOf(Double.parseDouble(cont[85].toString())-Double.parseDouble(cont[86].toString())).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    				nominaIndividual.setUBLExtensions(extensionsType);
    				
    				JAXBContext jaxbContext = JAXBContext.newInstance(NominaIndividualType.class);
    				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    				
    				/*Ruta donde se crearan los archivos XML*/
    				//jaxbMarshaller.marshal(nominaIndividual, System.out);
    				jaxbMarshaller.marshal(nominaIndividual, new File(path+"sinfirmar/nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.xml"));
    				//FIRMA DE XML
    				try {
    				firmaXML("C:/ogetdes/"+certFile(), KeyPass(), path+"sinfirmar/nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.xml", 
    						path+"firmado/nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.xml");
    				ComprimirXml(Paths.get(path+"firmado/nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.xml"),path+"firmado/z"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.zip");
    				// ErrorSenderXml("z"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.zip","nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.xml", thnome.getThnomeId().toString(), file[0].toString());
    				/*GENERAR IMAGES CODIGO QR*/
    				genQr(path+"images/", nominaIndividual.getCodigoQR() , "nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.png");
    				}catch (Exception e) {
    					jaxbMarshaller.marshal(nominaIndividual, new File(path+"error/nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.xml"));
    				}
    				ThnomeDTO thnomeDTO = new ThnomeDTO();
    					Thestnom thestnom = new Thestnom();
    					ThdocideDTO thdocide = new ThdocideDTO();
    					thestnom.setThestnomId(6);
    					Query nativeQuery6 = session.createNamedQuery("consultipodoc");
    					nativeQuery6.setParameter("iddoc", cont[23]);
    			    	List<Object[]>tipodoc = nativeQuery6.getResultList();
    			    	if(tipodoc !=null && tipodoc.size() >0 ) {
    			    		for(Object [] tipodocs: tipodoc ) {
    			    			thdocide.setThdocideId(Integer.parseInt(tipodocs[0].toString()));
    			    			thdocide.setThdocideK1Cod(tipodocs[1].toString());
    			    			thdocide.setThdocideDesc(tipodocs[2].toString());
    			    			thdocide.setThdocideAbrev(tipodocs[3].toString());
    					 }
    			    	}
    			    	java.util.Date fecha = sdf.parse(cont[9].toString());
    			    	thnomeDTO.setThnomeId(thnome.getThnomeId());
    			    	thnomeDTO.setThnomeFechaEmision(fecha);
    			    	thnomeDTO.setThestnomId_Thestnom(thestnom.getThestnomId());
    			    	thnomeDTO.setThdocideId_Thdocide(thdocide.getThdocideId());
    			    	thnomeDTO.setThnomeNombres(cont[4]!=null?cont[2].toString()+" "+cont[4].toString():"");
    			    	thnomeDTO.setThnomeNroK3Document(cont[1]!=null? cont[1].toString():"");
    			    	thnomeDTO.setThnomePrefijo(cont[11].toString()+concat+contador);
    			    	thnomeDTO.setThnomeTotalComprobante(cont[27]!=null?Double.parseDouble(cont[27].toString()):0.0);
    			    	thnomeDTO.setThnomeXfile("nie"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.xml");
    			    	thnomeDTO.setThnomeZip("z"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.zip");
    			    	thnomeDTO.setThnomeCune(gfg.encrypThisString(cune)); 
    			    	
    					Thnome thnome1 = delegate.thnomeDTOToThnome(thnomeDTO);
    			        thnome1 = thnomeService.update(thnome1);
    			        
    			        session.flush();
    				
    				
    			} catch (DatatypeConfigurationException e) {
    				log.error(e.getMessage());
    			}
    		}
    	}
    }
    
    @Transactional("nomTransactionManager")
    public NominaIndividualDeAjusteType leerGenXml(String path) throws JAXBException {
    	log.info("[leerGenXml] INICIO");
    	String rutain = "";
    	String rutaout = "";
    	    	try {
    		NominaIndividualType nominaIndividual = new NominaIndividualType();
    		org.hibernate.Session session = (Session) this.em.getDelegate();
        	Query nativeQuery = session.createNamedQuery("thnomefile");
        	nativeQuery.setParameter("file", path);
        	List<Object[]> data  = nativeQuery.getResultList();
        	
        	Query thvarnom = session.createNamedQuery("variablesne");
        	thvarnom.setParameter("llave", "PATH");
			List<Object[]>outfile = thvarnom.getResultList();
			String output = outfile.get(0)[2].toString();
			 rutain = output+"sinfirmar/";
	    	 rutaout = output+"ajustes/";

    	  	File file = new File(rutain+path);
        	JAXBContext jaxbContext  = JAXBContext.newInstance(NominaIndividualType.class);
        	Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        	nominaIndividual = (NominaIndividualType) jaxbUnmarshaller.unmarshal(file);
        	NominaIndividualDeAjusteType nominaIndividualDeAjusteType = new NominaIndividualDeAjusteType();
        	ReemplazandoPredecesor reemplazandoPredecesor = new ReemplazandoPredecesor();
        	com.oget.ogetpro.xmlaj.UBLExtensionsType extensionsType = new com.oget.ogetpro.xmlaj.UBLExtensionsType();
    		com.oget.ogetpro.xmlaj.UBLExtensionType extensionType = new com.oget.ogetpro.xmlaj.UBLExtensionType();
    		com.oget.ogetpro.xmlaj.ExtensionContentType contentType = new com.oget.ogetpro.xmlaj.ExtensionContentType();
    		
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Empleador empleador = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Empleador();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Deducciones deducciones = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Deducciones();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Deducciones.Salud salud = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Deducciones.Salud();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Deducciones.FondoPension fondoPension = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Deducciones.FondoPension();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Deducciones.OtrasDeducciones otrasDeducciones = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Deducciones.OtrasDeducciones();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados devengados = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Basico basico = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Basico();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Auxilios auxilios = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Auxilios();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Bonificaciones bonificaciones = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Bonificaciones();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Cesantias cesantias = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Cesantias();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Comisiones comisiones = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Comisiones();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.HEDDFs fs = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.HEDDFs();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.HEDs ds = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.HEDs();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.HENDFs fs2 = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.HENDFs();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.HENs ns = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.HENs();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.HRDDFs fs3 = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.HRDDFs();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.HRNDFs fs4 = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.HRNDFs();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.HRNs ns2 = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.HRNs();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Primas primas = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Primas();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Bonificaciones bonificaciones2 = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Bonificaciones();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Bonificaciones.Bonificacion bonificacion = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Devengados.Bonificaciones.Bonificacion();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Trabajador trabajador = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Trabajador();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Pago pago = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Pago();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Periodo periodo = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.Periodo();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.NumeroSecuenciaXML numeroSecuenciaXML = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.NumeroSecuenciaXML();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.LugarGeneracionXML generacionXML = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.LugarGeneracionXML();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.ProveedorXML proveedorXML = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.ProveedorXML();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.InformacionGeneral informacionGeneral = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.InformacionGeneral();
        	com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.FechasPagos fechasPagos = new com.oget.ogetpro.xmlaj.NominaIndividualDeAjusteType.Reemplazar.FechasPagos();
        	
        	Reemplazar reemplazar = new Reemplazar();
        	Eliminar eliminar = new Eliminar();
        	for(Object[] nome: data) {
        		reemplazandoPredecesor.setFechaGenPred(date.ConvertFechaXml(nome[3].toString()));
        		reemplazandoPredecesor.setNumeroPred(nome[2].toString());
        		reemplazandoPredecesor.setCUNEPred(nome[10].toString());
        		fechasPagos.getFechaPago().add(nominaIndividual.getFechasPagos().getFechaPago().get(0));
        		periodo.setTiempoLaborado(nominaIndividual.getPeriodo().getTiempoLaborado());
        		periodo.setFechaGen(nominaIndividual.getPeriodo().getFechaGen());
        		periodo.setFechaIngreso(nominaIndividual.getPeriodo().getFechaIngreso());
        		periodo.setFechaLiquidacionFin(nominaIndividual.getPeriodo().getFechaLiquidacionFin());
        		periodo.setFechaLiquidacionInicio(nominaIndividual.getPeriodo().getFechaLiquidacionInicio());
        		periodo.setFechaRetiro(nominaIndividual.getPeriodo().getFechaRetiro());
        		numeroSecuenciaXML.setCodigoTrabajador(nominaIndividual.getNumeroSecuenciaXML().getCodigoTrabajador());
        		numeroSecuenciaXML.setConsecutivo(BigInteger.valueOf(Long.parseLong(nominaIndividual.getNumeroSecuenciaXML().getConsecutivo().toString().substring(0, 6)+nominaIndividual.getNumeroSecuenciaXML().getConsecutivo().toString().substring(6))));
        		numeroSecuenciaXML.setNumero("LA"+numeroSecuenciaXML.getConsecutivo());
        		numeroSecuenciaXML.setPrefijo("LA");
        		generacionXML.setDepartamentoEstado(nominaIndividual.getLugarGeneracionXML().getDepartamentoEstado());
        		generacionXML.setIdioma(nominaIndividual.getLugarGeneracionXML().getIdioma());
        		generacionXML.setMunicipioCiudad(nominaIndividual.getLugarGeneracionXML().getMunicipioCiudad());
        		generacionXML.setPais(nominaIndividual.getLugarGeneracionXML().getPais());
        		proveedorXML.setDV(nominaIndividual.getProveedorXML().getDV());
        		proveedorXML.setNIT(BigInteger.valueOf(Long.parseLong(nominaIndividual.getProveedorXML().getNIT())));
        		proveedorXML.setOtrosNombres(nominaIndividual.getProveedorXML().getOtrosNombres());
        		proveedorXML.setPrimerApellido(nominaIndividual.getProveedorXML().getPrimerApellido());
        		proveedorXML.setPrimerNombre(nominaIndividual.getProveedorXML().getPrimerNombre());
        		proveedorXML.setRazonSocial(nominaIndividual.getProveedorXML().getRazonSocial());
        		proveedorXML.setSegundoApellido(nominaIndividual.getProveedorXML().getSegundoApellido());
        		proveedorXML.setSoftwareID(nominaIndividual.getProveedorXML().getSoftwareID());
        		
        		informacionGeneral.setAmbiente(nominaIndividual.getInformacionGeneral().getAmbiente());
        		informacionGeneral.setEncripCUNE(nominaIndividual.getInformacionGeneral().getEncripCUNE());
        		informacionGeneral.setPeriodoNomina(nominaIndividual.getInformacionGeneral().getPeriodoNomina());
        		informacionGeneral.setVersion("V1.0: Nota de Ajuste de Documento Soporte de Pago de Nómina Electrónica");
        		informacionGeneral.setTRM(nominaIndividual.getInformacionGeneral().getTRM());
        		informacionGeneral.setTipoXML(BigInteger.valueOf(103));
        		informacionGeneral.setTipoMoneda(nominaIndividual.getInformacionGeneral().getTipoMoneda());
        		informacionGeneral.setFechaGen(nominaIndividual.getInformacionGeneral().getFechaGen());
        		informacionGeneral.setHoraGen(nominaIndividual.getInformacionGeneral().getHoraGen());
        		
        		reemplazar.setInformacionGeneral(informacionGeneral);
        		reemplazar.setLugarGeneracionXML(generacionXML);
        		reemplazar.setNumeroSecuenciaXML(numeroSecuenciaXML);
        		reemplazar.setPeriodo(periodo);
        		reemplazar.setDevengados(devengados);
        		reemplazar.setReemplazandoPredecesor(reemplazandoPredecesor);
        		reemplazar.setComprobanteTotal(nominaIndividual.getComprobanteTotal());
        		reemplazar.setDeduccionesTotal(nominaIndividual.getDeduccionesTotal());
        		reemplazar.setDevengadosTotal(nominaIndividual.getDevengadosTotal());
        		empleador.setDepartamentoEstado(nominaIndividual.getEmpleador().getDepartamentoEstado());
        		empleador.setDireccion(nominaIndividual.getEmpleador().getDireccion());
        		empleador.setDV(nominaIndividual.getEmpleador().getDV());
        		empleador.setNIT(BigInteger.valueOf(Long.parseLong(nominaIndividual.getEmpleador().getNIT())) );
        		empleador.setMunicipioCiudad(nominaIndividual.getEmpleador().getMunicipioCiudad());
        		empleador.setOtrosNombres(nominaIndividual.getEmpleador().getOtrosNombres());
        		empleador.setPais(nominaIndividual.getEmpleador().getPais());
        		empleador.setPrimerApellido(nominaIndividual.getEmpleador().getPrimerApellido());
        		empleador.setPrimerNombre(nominaIndividual.getEmpleador().getPrimerNombre());
        		empleador.setRazonSocial(nominaIndividual.getEmpleador().getRazonSocial());
        		empleador.setSegundoApellido(nominaIndividual.getEmpleador().getSegundoApellido());
        		salud.setPorcentaje(nominaIndividual.getDeducciones().getSalud().getPorcentaje());
        		salud.setDeduccion(nominaIndividual.getDeducciones().getSalud().getDeduccion());
        		fondoPension.setDeduccion(nominaIndividual.getDeducciones().getFondoPension().getDeduccion());
        		fondoPension.setPorcentaje(nominaIndividual.getDeducciones().getFondoPension().getPorcentaje());
        		
        		if(nominaIndividual.getDeducciones().getOtrasDeducciones()!=null) {
        		otrasDeducciones.getOtraDeduccion().add((nominaIndividual.getDeducciones().getOtrasDeducciones().getOtraDeduccion().get(0)));
        		deducciones.setOtrasDeducciones(otrasDeducciones);
        		}
        		basico.setDiasTrabajados(nominaIndividual.getDevengados().getBasico().getDiasTrabajados());
        		basico.setSueldoTrabajado(nominaIndividual.getDevengados().getBasico().getSueldoTrabajado());
        		if(nominaIndividual.getDevengados().getCesantias()!=null) {
        		cesantias.setPago(nominaIndividual.getDevengados().getCesantias().getPago());
        		cesantias.setPagoIntereses(nominaIndividual.getDevengados().getCesantias().getPagoIntereses());
        		cesantias.setPorcentaje(nominaIndividual.getDevengados().getCesantias().getPorcentaje());
        		devengados.setCesantias(cesantias);
        		}
        		if(nominaIndividual.getDevengados().getPrimas()!=null) {
        			primas.setCantidad(nominaIndividual.getDevengados().getPrimas().getCantidad());
        			primas.setPago(nominaIndividual.getDevengados().getPrimas().getPago());
        			primas.setPagoNS(nominaIndividual.getDevengados().getPrimas().getPagoNS());
        			devengados.setPrimas(primas);
        		}
        		
        		if(nominaIndividual.getDevengados().getBonificaciones()!=null) {
        			bonificacion.setBonificacionNS(nominaIndividual.getDevengados().getBonificaciones().getBonificacion().get(0).getBonificacionS());
        			bonificaciones2.getBonificacion().add(bonificacion);
        			devengados.setBonificaciones(bonificaciones2);
        		}
        		devengados.setBasico(basico);
        		deducciones.setSalud(salud);
        		deducciones.setFondoPension(fondoPension);
        		trabajador.setCodigoTrabajador(nominaIndividual.getTrabajador().getCodigoTrabajador());
        		trabajador.setLugarTrabajoDepartamentoEstado(nominaIndividual.getTrabajador().getLugarTrabajoDepartamentoEstado());
        		trabajador.setLugarTrabajoDireccion(nominaIndividual.getTrabajador().getLugarTrabajoDireccion());
        		trabajador.setLugarTrabajoMunicipioCiudad(nominaIndividual.getTrabajador().getLugarTrabajoMunicipioCiudad());
        		trabajador.setLugarTrabajoPais(nominaIndividual.getTrabajador().getLugarTrabajoPais());
        		trabajador.setNumeroDocumento(nominaIndividual.getTrabajador().getNumeroDocumento());
        		trabajador.setOtrosNombres(nominaIndividual.getTrabajador().getOtrosNombres());
        		trabajador.setPrimerApellido(nominaIndividual.getTrabajador().getPrimerApellido());
        		trabajador.setPrimerNombre(nominaIndividual.getTrabajador().getPrimerNombre());
        		trabajador.setSegundoApellido(nominaIndividual.getTrabajador().getSegundoApellido());
        		trabajador.setSubTipoTrabajador(nominaIndividual.getTrabajador().getSubTipoTrabajador());
        		trabajador.setSueldo(nominaIndividual.getTrabajador().getSueldo());
        		trabajador.setTipoContrato(nominaIndividual.getTrabajador().getTipoContrato());
        		trabajador.setTipoDocumento(nominaIndividual.getTrabajador().getTipoDocumento());
        		trabajador.setTipoTrabajador(nominaIndividual.getTrabajador().getTipoTrabajador());
        		trabajador.setSalarioIntegral(nominaIndividual.getTrabajador().isSalarioIntegral());
        		pago.setMetodo(nominaIndividual.getPago().getMetodo());
        		pago.setForma(nominaIndividual.getPago().getForma());
        		
        		//47 METODO DE PAGO TRANSFERENCIA BANCARIA
        		if(pago.getMetodo().equals(BigInteger.valueOf(47))) {
        			pago.setNumeroCuenta(nominaIndividual.getPago().getNumeroCuenta());
            		pago.setTipoCuenta(nominaIndividual.getPago().getTipoCuenta());	
            		pago.setBanco(nominaIndividual.getPago().getBanco());
        		}
        		extensionsType.getUBLExtension();
        		extensionType.getExtensionContent();
        		contentType.getAny();	
        		contentType.setAny(null);
        		extensionType.setExtensionContent(contentType);
        		extensionsType.getUBLExtension().clear();
        		extensionsType.getUBLExtension().add(extensionType);
        		
        		
        		reemplazar.setPago(pago);
        		reemplazar.setTrabajador(trabajador);
        		reemplazar.setDeducciones(deducciones);
        		reemplazar.setEmpleador(empleador);
        		reemplazar.getCodigoQR();
				thvarnom.setParameter("llave", "AJ");
				List<Object[]> aj =  thvarnom.getResultList();

				thvarnom.setParameter("llave", "pingID");
				List<Object[]> pingID =  thvarnom.getResultList();
				
				thvarnom.setParameter("llave", "AMBIENTE");
				List<Object[]>amb = thvarnom.getResultList();
				//NumNIAE** + FecNIAE** + HorNIAE** + ValDev** + ValDed** + ValTol** + NitNIAE** + DocEmp** + TipoXML** + Software-Pin** + TipAmb**
				proveedorXML.setSoftwareSC(gfg.encrypThisString(nominaIndividual.getProveedorXML().getSoftwareID() + Math.round(Double.parseDouble(pingID.get(0)[4].toString()))+ "LA"+numeroSecuenciaXML.getConsecutivo()));
        		String cune = numeroSecuenciaXML.getNumero()+informacionGeneral.getFechaGen()+ informacionGeneral.getHoraGen()+reemplazar.getDevengadosTotal() + reemplazar.getDeduccionesTotal() + reemplazar.getComprobanteTotal() +
        				reemplazar.getEmpleador().getNIT() + reemplazar.getTrabajador().getNumeroDocumento() + 
        				Math.round(Double.parseDouble(aj.get(0)[4].toString())) + Math.round(Double.parseDouble(pingID.get(0)[4].toString())) +Math.round(Double.parseDouble(amb.get(0)[4].toString()))  ;
        		log.info(cune);
        		try {
        			informacionGeneral.setCUNE(gfg.encrypThisString(cune));
        		} catch (NoSuchAlgorithmException e) {
        			e.printStackTrace();
        		}
        		reemplazar.setFechasPagos(fechasPagos);
        		reemplazar.setProveedorXML(proveedorXML);
        		/*agregar el cune */
        		reemplazar.setCodigoQR(reemplazar.getInformacionGeneral().getAmbiente().equals(BigInteger.valueOf(1))?"https://catalogo-vpfe.dian.gov.co/document/searchqr?documentkey=" + gfg.encrypThisString(cune):
            	"https://catalogo-vpfe-hab.dian.gov.co/document/searchqr?documentkey="+ gfg.encrypThisString(cune));
            	nominaIndividualDeAjusteType.setTipoNota(BigInteger.valueOf(1));
            	nominaIndividualDeAjusteType.setUBLExtensions(extensionsType);
            	nominaIndividualDeAjusteType.getReemplazar();
            	nominaIndividualDeAjusteType.setReemplazar(reemplazar);
            	nominaIndividualDeAjusteType.setSchemaLocation("dian:gov:co:facturaelectronica:NominaIndividualDeAjuste NominaIndividualDeAjusteElectronicaXSD.xsd");
            	JAXBContext jaxbContext1 = JAXBContext.newInstance(NominaIndividualDeAjusteType.class);
        		Marshaller jaxbMarshaller = jaxbContext1.createMarshaller();
        		
        		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        		//jaxbMarshaller.marshal(nominaIndividualDeAjusteType, System.out);
        		OutputStream os = new FileOutputStream(rutaout+"niae"+String.format("%010d", reemplazar.getEmpleador().getNIT())+nominaIndividual.getNumeroSecuenciaXML().getConsecutivo().toString().substring(2, 4)+String.format("%08d", Integer.parseInt(nominaIndividual.getNumeroSecuenciaXML().getConsecutivo().toString().substring(6)))+"C.xml");
        		jaxbMarshaller.marshal(nominaIndividualDeAjusteType, os);
            	
        		try {
    				firmaXML("C:/ogetdes/"+certFile(), KeyPass(), output+"ajustes/niae"+String.format("%010d", reemplazar.getEmpleador().getNIT())+nominaIndividual.getNumeroSecuenciaXML().getConsecutivo().toString().substring(2, 4)+String.format("%08d", Integer.parseInt(nominaIndividual.getNumeroSecuenciaXML().getConsecutivo().toString().substring(6)))+"C.xml", 
    						output+"ajustes/firmado/niae"+String.format("%010d", reemplazar.getEmpleador().getNIT())+nominaIndividual.getNumeroSecuenciaXML().getConsecutivo().toString().substring(2, 4)+String.format("%08d", Integer.parseInt(nominaIndividual.getNumeroSecuenciaXML().getConsecutivo().toString().substring(6)))+"C.xml");
    				ComprimirXml(Paths.get(output+"ajustes/firmado/niae"+String.format("%010d", reemplazar.getEmpleador().getNIT())+nominaIndividual.getNumeroSecuenciaXML().getConsecutivo().toString().substring(2, 4)+String.format("%08d", Integer.parseInt(nominaIndividual.getNumeroSecuenciaXML().getConsecutivo().toString().substring(6)))+"C.xml"),
    						output+"ajustes/firmado/z"+String.format("%010d", reemplazar.getEmpleador().getNIT())+nominaIndividual.getNumeroSecuenciaXML().getConsecutivo().toString().substring(2, 4)+String.format("%08d", Integer.parseInt(nominaIndividual.getNumeroSecuenciaXML().getConsecutivo().toString().substring(6)))+"C.zip");
    				//MensajeDTO<?> msj= sendXml("z"+String.format("%010d", cont[12])+cons1.substring(2,4)+String.format("%08d", contador)+"C.zip");
    				}catch (Exception e) {
    					jaxbMarshaller.marshal(nominaIndividual, new File(output+"error/nie"+String.format("%010d", reemplazar.getEmpleador().getNIT())+nominaIndividual.getNumeroSecuenciaXML().getConsecutivo().toString().substring(2, 4)+String.format("%08d", Integer.parseInt(nominaIndividual.getNumeroSecuenciaXML().getConsecutivo().toString().substring(6)))+"C.xml"));
    				}
        	}
        	log.info("[]leerGenXml FINOK");
        	return nominaIndividualDeAjusteType;
    	}
    	catch (Exception e) {
    		throw new ZMessManager(e.getMessage()+  " Error archivo " +rutain+ path);
    	}
    }

    @Transactional("nomTransactionManager")
	public Long count(){
	 	return thctrliqRepository.count();
	}
    @Transactional("nomTransactionManager")
	public List<Thctrliq> findAll(){
		log.debug("finding all Thctrliq instances");
       	return thctrliqRepository.findAll();
    }
    
	 @Transactional("nomTransactionManager")		
	public Thctrliq save(Thctrliq entity) throws Exception {
		log.debug("saving Thctrliq instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Thctrliq");
		}
		
		validate(entity);	
	
		if(thctrliqRepository.existsById(entity.getThctrliqId())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return thctrliqRepository.save(entity);
	   
    }
	 
	 @Transactional("nomTransactionManager")
	 public MensajeDTO ErrorSenderXml()throws Exception {
		 log.info("[ErrorSenderXml] INICIO");
		 org.hibernate.Session session = (Session) this.em.getDelegate();
		 Query nativeQuery = session.createNamedQuery("ErrosrXml");
	    	List<Object[]> ErrorFile  = nativeQuery.getResultList();
		 Query thvarnom = session.createNamedQuery("variablesne");
	     	thvarnom.setParameter("llave", "PATH");
				List<Object[]>outfile = thvarnom.getResultList();
				thvarnom.setParameter("llave", "AMBIENTE");
				List<Object[]>tipAmbiente= thvarnom.getResultList();	
				
				String output = outfile.get(0)[2].toString();
				 String path = output+"firmado/";
				 MensajeDTO<?> mensaje = null;
				 java.net.URL urlDian;
				 for (Object[] files:ErrorFile) {
			    		Thnome thnome=thnomeService.findById(Integer.parseInt(files[3].toString())).get();		 
		 try {
			 Helper.writeLog(log, "INFO", "Proxy Host: " + System.getProperties().get("http.proxyHost"));
			 Helper.writeLog(log, "INFO", "Proxy Port: " + System.getProperties().get("http.proxyPort"));

			 // -> Deshabilitar los Proxys en caso de que existan
			    System.getProperties().put("http.proxyHost", "");
			    System.getProperties().put("http.proxyPort", "");
			    File file= new File(path+thnome.getThnomeZip());
			    if(Math.round(Double.parseDouble(tipAmbiente.get(0)[4].toString()))==1) {
			    	urlDian=new java.net.URL("https://vpfe.dian.gov.co/WcfDianCustomerServices.svc");
			    }else {
			    	urlDian=new java.net.URL("https://vpfe-hab.dian.gov.co/WcfDianCustomerServices.svc");	
			    }
			    WSHttpBinding_IWcfDianCustomerServicesStub  service=new WSHttpBinding_IWcfDianCustomerServicesStub(urlDian,null );
			    DianResponse dianResponse= service.sendNominaSync(FileUtils.readFileToByteArray(file));
			    Query estnom = session.createNamedQuery("thestnom");
			     estnom.setParameter("code",Integer.parseInt(dianResponse.getStatusCode()));
			    
			     List<Object[]>estcode = estnom.getResultList();
			     if(estcode.size()>0) {
			    	 ThtrackingDTO thtrackingDTO = new ThtrackingDTO();
			    	 	thtrackingDTO.setThtrackingId(Integer.parseInt(files[0].toString()));
				        thtrackingDTO.setThtrackingFecha(Calendar.getInstance().getTime());
				        thtrackingDTO.setThnomeId_Thnome(thnome.getThnomeId());
				        thtrackingDTO.setThestnomId_Thestnom(Integer.parseInt(dianResponse.getStatusCode()));
				         Thtracking thtracking= delegaTracking.thtrackingDTOToThtracking(thtrackingDTO);
				         thtracking = thtrackingService.update(thtracking);
				         //SE GENERA NOMINA DE AJUSTE DEL DOCUMENTO ENVIADO 
				        /* if(dianResponse.getStatusCode().equals("00")) {
				          log.info("[leerGenXml-proSendXml] INICIO");
				        	 leerGenXml(filenameXml);
				          log.info("[leerGenXml-proSendXml] FINOK");
				         }*/
				         session.flush();
			     }
		} catch (Exception e) {
			log.error("[ErrorSenderXml] Error " + e.getMessage());
			mensaje = new MensajeDTO<>(2, 2, "Falló al consultar el servicio Dian Error:" + e.getMessage(),"");
		}
		
		}
		 mensaje = new MensajeDTO<>(1,0,"Enviados","");
		 log.info("[ErrorSenderXml] FINOK");
		 return mensaje;
	 }
	 @Transactional("nomTransactionManager")
	 public MensajeDTO proSendXml(ConsultaDTO consulta) throws  Exception {
		 log.info("[proSendXml] INICIO");
		 org.hibernate.Session session = (Session) this.em.getDelegate();
		 /*SE ENVIA DOCUMENTOS POR FECHA DE EMISION*/
     	Query nativeQuery = session.createNamedQuery("sendXmlFh");
     	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		 java.util.Date ff = sdf.parse(consulta.getFechaExpDoc());
		 nativeQuery.setParameter("fh", ff, TemporalType.TIMESTAMP);
		 List<Object[]>files = nativeQuery.getResultList();
		 Query thvarnom = session.createNamedQuery("variablesne");
     	thvarnom.setParameter("llave", "PATH");
			List<Object[]>outfile = thvarnom.getResultList();
			
			thvarnom.setParameter("llave", "AMBIENTE");
			List<Object[]>tipAmbiente= thvarnom.getResultList();
			String output = outfile.get(0)[2].toString();
		 String outcome = null;		
			MensajeDTO<?> mensaje = null;
			Integer tipoUsuario = 1;
			Long idValidacion = null;
		 String zip="";
		 java.net.URL urlDian;
		 for(Object[] archivo: files) {
		  String path = output+"firmado/";//Local
		  zip = archivo[9].toString();
		  try {
			    Helper.writeLog(log, "INFO", "Proxy Host: " + System.getProperties().get("http.proxyHost"));
			    Helper.writeLog(log, "INFO", "Proxy Port: " + System.getProperties().get("http.proxyPort"));

			   // -> Deshabilitar los Proxys en caso de que existan
			    System.getProperties().put("http.proxyHost", "");
			    System.getProperties().put("http.proxyPort", "");
			    
			    //ADOWSHttpBinding_IWcfDianCustomerServices service= new ADOWSHttpBinding_IWcfDianCustomerServices("https://vpfe-hab.dian.gov.co/WcfDianCustomerServices.svc");
			    if(Math.round(Double.parseDouble(tipAmbiente.get(0)[4].toString()))==1) {
			    	urlDian=new java.net.URL("https://vpfe.dian.gov.co/WcfDianCustomerServices.svc");
			    }else {
			    	urlDian=new java.net.URL("https://vpfe-hab.dian.gov.co/WcfDianCustomerServices.svc");	
			    }
			   
			    WSHttpBinding_IWcfDianCustomerServicesStub  service=new WSHttpBinding_IWcfDianCustomerServicesStub(urlDian,null );
			     File file= new File(path+zip);
			     DianResponse dianResponse= service.sendNominaSync(FileUtils.readFileToByteArray(file));
			     
			     dianResponse.getStatusCode();
			     Query estnom = session.createNamedQuery("thestnom");
			     estnom.setParameter("code",Integer.parseInt(dianResponse.getStatusCode()));
			    
			     List<Object[]>estcode = estnom.getResultList();
			     if(estcode.size()>0) {
			    	 ThtrackingDTO thtrackingDTO = new ThtrackingDTO();
				        thtrackingDTO.setThtrackingFecha(Calendar.getInstance().getTime());
				        thtrackingDTO.setThnomeId_Thnome(Integer.parseInt(archivo[0].toString()));
				        thtrackingDTO.setThestnomId_Thestnom(Integer.parseInt(dianResponse.getStatusCode()));
				         Thtracking thtracking= delegaTracking.thtrackingDTOToThtracking(thtrackingDTO);
				         thtracking = thtrackingService.save(thtracking);
				         //SE GENERA NOMINA DE AJUSTE DEL DOCUMENTO ENVIADO 
				         if(dianResponse.getStatusCode().equals("00")) {
				          log.info("[leerGenXml-proSendXml] INICIO");
				        	 leerGenXml(archivo[8].toString());
				          log.info("[leerGenXml-proSendXml] FINOK");
				         }
			     }else {
			    	 ThestnomDTO thestnomDTO = new ThestnomDTO();
			    	 thestnomDTO.setThestnomId(Integer.parseInt(dianResponse.getStatusCode()));
			    	 thestnomDTO.setThestnomDescripcion(dianResponse.getStatusDescription());
			    	 Thestnom thestnom = delegaEstnom.thestnomDTOToThestnom(thestnomDTO);
			    	 thestnom = thestnomService.save(thestnom);
			    	 
			    	 /*SE INSERTA CODIGO NO ALMACENADO AL TRACKING*/
			    	 ThtrackingDTO thtrackingDTO = new ThtrackingDTO();
				        thtrackingDTO.setThtrackingFecha(ff);
				        thtrackingDTO.setThnomeId_Thnome(Integer.parseInt(archivo[0].toString()));
				        thtrackingDTO.setThestnomId_Thestnom(thestnom.getThestnomId());
				         Thtracking thtracking= delegaTracking.thtrackingDTOToThtracking(thtrackingDTO);
				         thtracking = thtrackingService.save(thtracking);
			     }
			     
			     
			  } catch (Exception e) {
				log.error(e.getMessage(), e);
				mensaje = new MensajeDTO<>(2, 2, "Falló al consultar el servicio Dian Error:" + e.getMessage(),"");
			  }
		  mensaje = new MensajeDTO<>(1,0,"Enviados","");
		 }
		 log.info("[proSendXml] FINOK");
		 return mensaje;
	 }
	 @Transactional("nomTransactionManager")
	 public MensajeDTO HABproSendXml(ConsultaDTO consulta) throws  Exception {
		 log.info("[HABproSendXml] INICIO");
		 org.hibernate.Session session = (Session) this.em.getDelegate();
		 /*SE ENVIA DOCUMENTOS POR FECHA DE EMISION*/
     	Query nativeQuery = session.createNamedQuery("sendXmlFh");
     	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		 java.util.Date ff = sdf.parse(consulta.getFechaExpDoc());
		 nativeQuery.setParameter("fh", ff, TemporalType.TIMESTAMP);
		 List<Object[]>files = nativeQuery.getResultList();
		 Query thvarnom = session.createNamedQuery("variablesne");
     	thvarnom.setParameter("llave", "PATH");
			List<Object[]>outfile = thvarnom.getResultList();
			String output = outfile.get(0)[2].toString();
		 String outcome = null;		
			MensajeDTO<?> mensaje = null;
			Integer tipoUsuario = 1;
			Long idValidacion = null;
		 String zip="";
		 int flag=1; 
		 for(Object[] archivo: files) {
			 if(flag<=6) {
			 String path = output+"firmado/";//Local
		  zip = archivo[9].toString();
		  try {
			    Helper.writeLog(log, "INFO", "Proxy Host: " + System.getProperties().get("http.proxyHost"));
			    Helper.writeLog(log, "INFO", "Proxy Port: " + System.getProperties().get("http.proxyPort"));

			   // -> Deshabilitar los Proxys en caso de que existan
			    System.getProperties().put("http.proxyHost", "");
			    System.getProperties().put("http.proxyPort", "");
			    
			    //ADOWSHttpBinding_IWcfDianCustomerServices service= new ADOWSHttpBinding_IWcfDianCustomerServices("https://vpfe-hab.dian.gov.co/WcfDianCustomerServices.svc");
			    java.net.URL urlDian=new java.net.URL("https://vpfe-hab.dian.gov.co/WcfDianCustomerServices.svc");
			    WSHttpBinding_IWcfDianCustomerServicesStub  service=new WSHttpBinding_IWcfDianCustomerServicesStub(urlDian,null );
			     File file= new File(path+zip);
			     log.info("[HABproSendXml] XML: " + zip);
			     UploadDocumentResponse dianResponse = service.sendTestSetAsync(zip,FileUtils.readFileToByteArray(file),testsetid);

			     log.info(dianResponse.getZipKey());
				    TimeUnit.SECONDS.sleep(3);
				    DianResponse[] dianResponses= service.getStatusZip(dianResponse.getZipKey());
				    leerGenXml(archivo[8].toString());
				    for (DianResponse dianResponseq : dianResponses) {
				    	log.info("StatusMessage: "+dianResponseq.getStatusMessage());
				    	log.info("Description: "+dianResponseq.getStatusDescription());
				    	log.info("StatusCode" +dianResponseq.getStatusCode());
				    }
			  } catch (Exception e) {
				log.error(e.getMessage(), e);
				mensaje = new MensajeDTO<>(2, 2, "Falló al consultar el servicio Dian Error:" + e.getMessage(),"");
			  }
		 
		 }
		 if(flag <=6) {
			 String pathaj= output+"ajustes/firmado/";
			 zip = archivo[9].toString();
			 java.net.URL urlDian=new java.net.URL("https://vpfe-hab.dian.gov.co/WcfDianCustomerServices.svc");
			    WSHttpBinding_IWcfDianCustomerServicesStub  service=new WSHttpBinding_IWcfDianCustomerServicesStub(urlDian,null );
			     File file= new File(pathaj+zip);
			     log.info("[HABproSendXml] Ajuste XML: " + zip);
			     UploadDocumentResponse dianResponse = service.sendTestSetAsync(zip,FileUtils.readFileToByteArray(file),testsetid);

			     log.info(dianResponse.getZipKey());
				    TimeUnit.SECONDS.sleep(3);
				    DianResponse[] dianResponses= service.getStatusZip(dianResponse.getZipKey());
				    //leerGenXml(archivo[8].toString());
				    for (DianResponse dianResponseq : dianResponses) {
				    	log.info("StatusMessage: "+dianResponseq.getStatusMessage());
				    	log.info("Description: "+dianResponseq.getStatusDescription());
				    	log.info("StatusCode" +dianResponseq.getStatusCode());
				    }
			 mensaje = new MensajeDTO<>(1,0,"HABILITADO","");
		 }
		 
		 if(flag==6) {
			 break;
		 }
		 flag++;
		 }
		 log.info("[HABproSendXml] FINOK");
		 return mensaje;
	 }
	 
	 public MensajeDTO sendXml(String zip) {
		 
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
		     File file= new File("C:/home/ec2-user/ogetXML/firmado/"+zip);
		    // service.sendNominaSync(FileUtils.readFileToByteArray(file));
		     UploadDocumentResponse dianResponse = service.sendTestSetAsync(zip,FileUtils.readFileToByteArray(file),"3342ed67-163c-4af2-805c-e1d1ad234dc0");
		    log.info(dianResponse.getZipKey());
		    TimeUnit.SECONDS.sleep(3);
		    DianResponse[] dianResponses= service.getStatusZip(dianResponse.getZipKey());
		   //08e394fe-7e57-468c-96ef-afe111dd338b- 4e86b749-0fc0-4f77-b64e-6f934e0e6477
		    for (DianResponse dianResponseq : dianResponses) {
		    	log.info(dianResponseq.getStatusMessage());
		    	log.info(dianResponseq.getStatusDescription());
		    	log.info(dianResponseq.getStatusCode());
		    	String [] dianRes = dianResponseq.getErrorMessage();
		    	if(dianRes.length>0) {
		    		log.info(dianRes[0]);
		    	}
		    	mensaje = new MensajeDTO<>(1,0,dianRes[0],"");
			}
		    
		  } catch (Exception e) {
			log.error(e.getMessage(), e);
			mensaje = new MensajeDTO<>(2, 2, "Falló al consultar el servicio Dian Error:" + e.getMessage(),"");
		  }
		  return mensaje;
	 }
			
	 @Transactional("nomTransactionManager")
	 public void delete(Thctrliq entity) throws Exception {
            	log.debug("deleting Thctrliq instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Thctrliq");
	    		}
    	
                                if(entity.getThctrliqId()==null){
                    throw new ZMessManager().new EmptyFieldException("thctrliqId");
                    }
                        
            if(thctrliqRepository.existsById(entity.getThctrliqId())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getThctrliqId()).ifPresent(entidad->{	            	
	                													List<Thmovliq> thmovliqs = entidad.getThmovliqs();
							                    if(Utilities.validationsList(thmovliqs)==true){
                       	 	throw new ZMessManager().new DeletingException("thmovliqs");
                        }
	                	            });
                       

           
            
            thctrliqRepository.deleteById(entity.getThctrliqId());
            log.debug("delete Thctrliq successful");
            
           
            	
            }
	 @Transactional("nomTransactionManager")   
     public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Thctrliq instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("thctrliqId");
            	}
            	if(thctrliqRepository.existsById(id)){
           			delete(thctrliqRepository.findById(id).get());
       			}    
            }	
	 @Transactional("nomTransactionManager")	
	 public Thctrliq update(Thctrliq entity) throws Exception {

				log.debug("updating Thctrliq instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Thctrliq");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(thctrliqRepository.existsById(entity.getThctrliqId())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return thctrliqRepository.save(entity);
	        
            }
	 @Transactional("nomTransactionManager")		
	 public Optional<Thctrliq> findById(Integer thctrliqId) {            
            	log.debug("getting Thctrliq instance");
            	return thctrliqRepository.findById(thctrliqId);
            }
	
	 
	 @Transactional("nomTransactionManager")
	 public String firmaXML(String urlcertificadop12, String passworp12, String urlxmlNoFirmado, String urlxmlFirmado)throws Exception {            
     	// C:\ogetdes\distriventas.p12 NuTAatxfA3 C:\ogetdes\niae08150034142100000004C.xml C:\ogetdes\niae08150034142100000004C_firmado.xml
		 Signer signer = new Signer();
	     signer.sign(urlcertificadop12, passworp12, urlxmlNoFirmado, urlxmlFirmado );
	 	return urlxmlFirmado;
     }
	 
	 public void ComprimirXml(Path source, String nameFile)throws Exception {
		 log.debug("Getting Start Compresion..");
		 try ( ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(nameFile));
			 FileInputStream fis  = new FileInputStream(source.toFile());) {
			ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
			zos.putNextEntry(zipEntry);
			
			byte[] buffer = new byte[1024];
			int len;
			while((len = fis.read(buffer))>0) {
				zos.write(buffer,0,len);
			}
			zos.close();
			log.debug("Getting finish process ZIP ");
		 }catch (Exception e) {
			log.error(e.getMessage());
		}
	 }
	 @Transactional("nomTransactionManager")
	 public void genQr(String pathname, String url, String nameImage) {
		   String textToQr = url;
	       		
	       
	       try {
	    	   QR qr = new QR();
	    	   qr.writeQR(textToQr, pathname+nameImage);

	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	 }
	 
	 @Transactional("nomTransactionManager")
	 public ByteArrayInputStream  GenPreviewThcptosDian(Date fhi, Date fhf) {
		 ByteArrayOutputStream jboss= new ByteArrayOutputStream();	
		 try {
		 		 org.hibernate.Session session = (Session) this.em.getDelegate();
			     	Query nativeQuery = session.createNamedQuery("conceptosLiquidados");
			     	nativeQuery.setParameter("fhi", fhi, TemporalType.TIMESTAMP);
			    	nativeQuery.setParameter("fhf", fhf , TemporalType.TIMESTAMP);
			    	List<Object[]>rows = nativeQuery.getResultList();
			    	
					String [] titulos = new String[5];
					
			        titulos[0]="THCPTOS_ID";
			        titulos[1]="DESCRIPCION"; 
			        titulos[2]="CODIGO CONCEPTO";
			        titulos[3]="DIAN_ID";
			        titulos[4]="EQUIVALENCIA";
			     
			        
			        HSSFWorkbook workbook = new HSSFWorkbook();
			        HSSFSheet sheet = workbook.createSheet("EquivalenciaDian");
			        HSSFRow header=sheet.createRow(0);   
				    HSSFCellStyle cellStyle = workbook.createCellStyle();  
			        HSSFPalette palette=workbook.getCustomPalette();
			        palette.setColorAtIndex(HSSFColor.RED.index, (byte)255, (byte)150, (byte)1);
			        cellStyle.setFillForegroundColor(palette.getColor(HSSFColor.RED.index).getIndex());
			        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
			        cellStyle.setAlignment(HorizontalAlignment.CENTER);
			        HSSFFont my_font=workbook.createFont();
			        my_font.setBold(true);
			        cellStyle.setFont(my_font);
			        cellStyle.setBorderLeft(BorderStyle.THIN); 
			        cellStyle.setBorderRight(BorderStyle.THIN);
			        cellStyle.setBorderTop(BorderStyle.THIN); 
			        cellStyle.setBorderBottom(BorderStyle.THIN);
			        HSSFCellStyle cellStylefecha = workbook.createCellStyle();
			        HSSFCreationHelper createHelper = workbook.getCreationHelper();
			    	cellStylefecha.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy")); 
			    	for(int i=0; i <titulos.length;i++) {
			            HSSFCell cell = header.createCell(i);
			            cell.setCellType(CellType.STRING);
			            cell.setCellValue(titulos[i]); 
			            cell.setCellStyle(cellStyle);
			     }
			    	int i=0;
			    	for(Object[] row: rows) {
			    		Query nativeQuery1 = session.createNamedQuery("thcptos");
			    		nativeQuery1.setParameter("thcptos", row[0]);
			    		List<Object[]>cptosd = nativeQuery1.getResultList();
			    		
			    		if(cptosd.size()>0) {
			    		for(Object[] cptod: cptosd) {
			    			try {
			    				HSSFRow rowx = sheet.createRow(i+1);
				    			
				    			HSSFCell cell = rowx.createCell(0);        	 
					            cell.setCellValue(row[0]!=null?row[0].toString():""); 
					            cell.setCellType(CellType.STRING);
					            
					            HSSFCell cell1 = rowx.createCell(1);
					            cell1.setCellValue(row[1]!=null?row[2].toString()+ "-" +row[1].toString():"");
					            cell1.setCellType(CellType.STRING);
					            
					            HSSFCell cell2 = rowx.createCell(2);
					            cell2.setCellValue(row[2]!=null?row[2].toString():"");
					            cell2.setCellType(CellType.STRING);
					            
					            HSSFCell cell3 = rowx.createCell(3);
					            cell3.setCellValue(cptod[1]!=null?cptod[1].toString():"");
					            cell3.setCellType(CellType.STRING);
					            
					            HSSFCell cell4 = rowx.createCell(4);
					            cell4.setCellValue("SI");
					            cell4.setCellType(CellType.STRING);
					            
					          
					            
							} catch (Exception e) {
								log.error("Error creando filas: " + e.getMessage());
							}
			    			i++;
			    		}
			    		}else {
			    			HSSFRow rowx = sheet.createRow(i+1);
			    			
			    			HSSFCell cell = rowx.createCell(0);        	 
				            cell.setCellValue(row[0]!=null?row[0].toString():""); 
				            cell.setCellType(CellType.STRING);
				            
				            HSSFCell cell1 = rowx.createCell(1);
				            cell1.setCellValue(row[1]!=null?row[2].toString()+ "-" +row[1].toString():"");
				            cell1.setCellType(CellType.STRING);
				            
				            HSSFCell cell2 = rowx.createCell(2);
				            cell2.setCellValue(row[2]!=null?row[2].toString():"");
				            cell2.setCellType(CellType.STRING);
				            
				            HSSFCell cell4 = rowx.createCell(4);
				            cell4.setCellValue("NO");
				            cell4.setCellType(CellType.STRING);
			    		}
			    	}
			    	for(i=0;i<header.getPhysicalNumberOfCells();i++){
						sheet.autoSizeColumn(i);
			        }	
		    			
		    		workbook.write(jboss);
					jboss.flush();
					jboss.close();
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		 	return  new ByteArrayInputStream(jboss.toByteArray());
		
	       	 }
	 @Transactional("nomTransactionManager")
	 public JasperPrint action_imprimir(String path) {
		 log.info("[action_imprimir] INICIO");
		 String rutain = "";
	     String rutaout = "";
	     JasperPrint jasperPrintFinal = null;
	     int cont=0;
		 try {
			 List<JRBeanCollectionDataSource> resultSetDataSource=new ArrayList<JRBeanCollectionDataSource>();
			 	NominaIndividualType nominaIndividual = new NominaIndividualType();
	    		org.hibernate.Session session = (Session) this.em.getDelegate();
	        	Query nativeQuery = session.createNamedQuery("thnomefile");
	        	nativeQuery.setParameter("file", path);
	        	List<Object[]> data  = nativeQuery.getResultList();
	        	
	        	Query thvarnom = session.createNamedQuery("variablesne");
	        	thvarnom.setParameter("llave", "PATH");
				List<Object[]>outfile = thvarnom.getResultList();
				String output = outfile.get(0)[2].toString();
				 rutain = output+"sinfirmar/";
		    	 rutaout = output+"images/";
		    	 
		    	String logo="C:/home/ec2-user/ogetdes/documentos/archivos/ogetsas.jpg";
		    	 
	    	  	File file = new File(rutain+path);
	        	JAXBContext jaxbContext  = JAXBContext.newInstance(NominaIndividualType.class);
	        	Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	        	nominaIndividual = (NominaIndividualType) jaxbUnmarshaller.unmarshal(file);
	        	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        	
	        	List<ItemLiquidacionDTO> list = new ArrayList<ItemLiquidacionDTO>();
	        	
	        	 List<Object[]> listitems=new ArrayList<Object[]>();
	        	 Object[] listamov=null;
	        	 String codCpto="";
	        	 String cpto="";
	        	 BigDecimal cantidad=null;
	        	 BigDecimal VlrDevengo= null;
	        	 BigDecimal VlrDeduccion = null;
	        	if(nominaIndividual.getDevengados().getAnticipos()!= null) {
	        		codCpto="NIE194";
	        		cpto="Anticipos";
	        		cantidad = null;
	        		VlrDevengo =nominaIndividual.getDevengados().getAnticipos().getAnticipo().get(0);
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
		 			//list.add(itemLiquidacion);
		    	}
	        	if(nominaIndividual.getDevengados().getApoyoSost() !=null) {
	        		codCpto="NIE157";
	        		cpto="Apoyo sostenimiento";
	        		cantidad = null;
	        		VlrDevengo =nominaIndividual.getDevengados().getApoyoSost();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDevengados().getAuxilios()!=null) {
	        		 if(nominaIndividual.getDevengados().getAuxilios().getAuxilio().get(0).getAuxilioS()!=null) {
	        		codCpto="NIE141";
	        		cpto="Auxilio Salarial";
	        		cantidad = null;
	        		VlrDevengo =nominaIndividual.getDevengados().getAuxilios().getAuxilio().get(0).getAuxilioS();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        		 }else {
    				codCpto="NIE142";
	        		cpto="Auxilio No Salarial";
	        		cantidad = null;
	        		VlrDevengo =nominaIndividual.getDevengados().getAuxilios().getAuxilio().get(0).getAuxilioNS();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov); 
	        		 }
	        	}
	        	if(nominaIndividual.getDevengados().getBasico()!=null) {
	        		codCpto="NIE070";
	        		cpto="Sueldo Trabajado";
	        		cantidad = BigDecimal.valueOf(nominaIndividual.getDevengados().getBasico().getDiasTrabajados().longValue());
	        		VlrDevengo =nominaIndividual.getDevengados().getBasico().getSueldoTrabajado();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDevengados().getBonificaciones()!=null) {
	        		codCpto="NIE139";
	        		cpto="Bonificacion Salarial";
	        		cantidad = null;
	        		VlrDevengo =nominaIndividual.getDevengados().getBonificaciones().getBonificacion().get(0).getBonificacionS();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDevengados().getBonificaciones()!=null) {
	        		codCpto="NIE140";
	        		cpto="Bonificacion No Salarial";
	        		cantidad = null;
	        		VlrDevengo =nominaIndividual.getDevengados().getBonificaciones().getBonificacion().get(0).getBonificacionNS();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDevengados().getBonifRetiro() !=null) {
	        		codCpto="NIE159";
	        		cpto="Bonificacion No Salarial";
	        		cantidad = null;
	        		VlrDevengo =nominaIndividual.getDevengados().getBonifRetiro();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	         	}
	        	if(nominaIndividual.getDevengados().getCesantias()!=null) {
	        		codCpto="NIE120";
	        		cpto="Cesantias";
	        		cantidad = nominaIndividual.getDevengados().getCesantias().getPorcentaje();
	        		VlrDevengo =BigDecimal.valueOf(Long.parseLong(nominaIndividual.getDevengados().getCesantias().getPago().toString()));
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDevengados().getComisiones()!=null) {
	        		codCpto="NIE155";
	        		cpto="Comisiones";
	        		cantidad = null;
	        		VlrDevengo =BigDecimal.valueOf(Double.parseDouble(nominaIndividual.getDevengados().getComisiones().getComision().get(0).toString()));
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDevengados().getCompensaciones() !=null) {
	        		codCpto="NIE149";
	        		cpto="Compesaciones Ordinaria";
	        		cantidad = null;
	        		VlrDevengo =nominaIndividual.getDevengados().getCompensaciones().getCompensacion().get(0).getCompensacionO();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDevengados().getCompensaciones()!=null) {
	        		codCpto="NIE150";
	        		cpto="Compesaciones Extraordinaria";
	        		cantidad = null;
	        		VlrDevengo =nominaIndividual.getDevengados().getCompensaciones().getCompensacion().get(0).getCompensacionE();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	
	        	if(nominaIndividual.getDevengados().getHEDDFs()!=null ) {
	        		for(int i=0; i<nominaIndividual.getDevengados().getHEDDFs().getHEDDF().size(); i++) {
	        			codCpto="NIE093";
		        		cpto="Hora Extra Diurna Festivo";
		        		cantidad = nominaIndividual.getDevengados().getHEDDFs().getHEDDF().get(i).getCantidad();
		        		VlrDevengo =nominaIndividual.getDevengados().getHEDDFs().getHEDDF().get(i).getPago();
		        		VlrDeduccion = null;
		        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
		        		listitems.add(listamov);
		        	}
	        	}
	        	if(nominaIndividual.getDevengados().getHEDs()!=null) {
	        		for(int i=0; i<nominaIndividual.getDevengados().getHEDs().getHED().size(); i++) {
	        			codCpto="NIE078";
		        		cpto="Hora Extra Diaria";
		        		cantidad = nominaIndividual.getDevengados().getHEDs().getHED().get(i).getCantidad();
		        		VlrDevengo =nominaIndividual.getDevengados().getHEDs().getHED().get(i).getPago();
		        		VlrDeduccion = null;
		        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
		        		listitems.add(listamov);
	  	        		}
	        	}
	        	if(nominaIndividual.getDevengados().getHENDFs()!=null) {
	        		for(int i=0; i<nominaIndividual.getDevengados().getHENDFs().getHENDF().size(); i++) {
	        			codCpto="NIE103";
		        		cpto="Hora Recargo Nocturno Festivo";
		        		cantidad = nominaIndividual.getDevengados().getHENDFs().getHENDF().get(i).getCantidad();
		        		VlrDevengo =nominaIndividual.getDevengados().getHENDFs().getHENDF().get(i).getPago();
		        		VlrDeduccion = null;
		        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
		        		listitems.add(listamov);
	  	        	}
	        	}
	        	if(nominaIndividual.getDevengados().getHENs()!=null) {
	        		for(int i=0; i<nominaIndividual.getDevengados().getHENs().getHEN().size(); i++) {
	        			codCpto="NIE083";
		        		cpto="Hora Extra Nocturna";
		        		cantidad = nominaIndividual.getDevengados().getHENs().getHEN().get(i).getCantidad();
		        		VlrDevengo =nominaIndividual.getDevengados().getHENs().getHEN().get(i).getPago();
		        		VlrDeduccion = null;
		        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
		        		listitems.add(listamov);
	        		}
	        	}
	        	if(nominaIndividual.getDevengados().getHRDDFs()!=null) {
	        		for(int i=0; i<nominaIndividual.getDevengados().getHRDDFs().getHRDDF().size(); i++) {
	        			codCpto="NIE098";
		        		cpto="Hora Extra Diurna Dominical";
		        		cantidad = nominaIndividual.getDevengados().getHRDDFs().getHRDDF().get(i).getCantidad();
		        		VlrDevengo =nominaIndividual.getDevengados().getHRDDFs().getHRDDF().get(i).getPago();
		        		VlrDeduccion = null;
		        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
		        		listitems.add(listamov);
	        		}
	        	}
	        	if(nominaIndividual.getDevengados().getHRNDFs()!=null) {
	        		for(int i=0; i<nominaIndividual.getDevengados().getHRNDFs().getHRNDF().size(); i++) {
	        			codCpto="NIE108";
		        		cpto="Hora Recargo Nocturno Dominical";
		        		cantidad = nominaIndividual.getDevengados().getHRNDFs().getHRNDF().get(i).getCantidad();
		        		VlrDevengo =nominaIndividual.getDevengados().getHRNDFs().getHRNDF().get(i).getPago();
		        		VlrDeduccion = null;
		        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
		        		listitems.add(listamov);
	        		}
	        	}
	        	if(nominaIndividual.getDevengados().getHRNs()!= null) {
	        		for(int i=0; i<nominaIndividual.getDevengados().getHRNs().getHRN().size(); i++) {
	        			codCpto="NIE088";
		        		cpto="Hora Recargo Nocturno";
		        		cantidad = nominaIndividual.getDevengados().getHRNs().getHRN().get(i).getCantidad();
		        		VlrDevengo =nominaIndividual.getDevengados().getHRNs().getHRN().get(i).getPago();
		        		VlrDeduccion = null;
		        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
		        		listitems.add(listamov);
	        		}
	        	}
	        	if(nominaIndividual.getDevengados().getIncapacidades()!=null) {
	        		for(int i=0; i<nominaIndividual.getDevengados().getIncapacidades().getIncapacidad().size(); i++) {
	        			codCpto="NIE127";
		        		cpto="Incapacidad";
		        		cantidad = BigDecimal.valueOf(Double.parseDouble(nominaIndividual.getDevengados().getIncapacidades().getIncapacidad().get(i).getCantidad().toString()));
		        		VlrDevengo =nominaIndividual.getDevengados().getIncapacidades().getIncapacidad().get(i).getPago();
		        		VlrDeduccion = null;
		        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
		        		listitems.add(listamov);
	        		}
	        	}
	        	if(nominaIndividual.getDevengados().getIndemnizacion() !=null) {
	        		codCpto="NIE160";
	        		cpto="Indemnizacion";
	        		cantidad = null;
	        		VlrDevengo =nominaIndividual.getDevengados().getIndemnizacion();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDevengados().getLicencias()!=null) {
	        		for(int i=0; i<nominaIndividual.getDevengados().getLicencias().getLicenciaMP().size(); i++) {
	        			codCpto="NIE131";
		        		cpto="Licencia Remunerada MP";
		        		cantidad = BigDecimal.valueOf(Double.parseDouble(nominaIndividual.getDevengados().getLicencias().getLicenciaMP().get(i).getCantidad().toString()));
		        		VlrDevengo =nominaIndividual.getDevengados().getLicencias().getLicenciaMP().get(i).getPago();
		        		VlrDeduccion = null;
		        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
		        		listitems.add(listamov);
	        		}
	        	}
	        	if(nominaIndividual.getDevengados().getLicencias()!=null) {
	        		for(int i=0; i<nominaIndividual.getDevengados().getLicencias().getLicenciaNR().size(); i++) {
	        			codCpto="NIE135";
		        		cpto="Licencia No Remunerada";
		        		cantidad = BigDecimal.valueOf(Double.parseDouble(nominaIndividual.getDevengados().getLicencias().getLicenciaNR().get(i).getCantidad().toString()));
		        		VlrDevengo =BigDecimal.valueOf(0);
		        		VlrDeduccion = null;
		        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
		        		listitems.add(listamov);
	        		}
	        	}
	        	if(nominaIndividual.getDevengados().getLicencias()!= null) {
	        		for(int i=0; i<nominaIndividual.getDevengados().getLicencias().getLicenciaNR().size(); i++) {
	        			codCpto="NIE135";
		        		cpto="Licencia Remunerada";
		        		cantidad = BigDecimal.valueOf(Double.parseDouble(nominaIndividual.getDevengados().getLicencias().getLicenciaR().get(i).getCantidad().toString()));
		        		VlrDevengo =nominaIndividual.getDevengados().getLicencias().getLicenciaR().get(i).getPago();
		        		VlrDeduccion = null;
		        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
		        		listitems.add(listamov);
	        		}
	        	}
	        	if(nominaIndividual.getDevengados().getOtrosConceptos()!=null) {
	        		for(int i=0; i<nominaIndividual.getDevengados().getOtrosConceptos().getOtroConcepto().size(); i++) {
	        			codCpto="NIE147";
		        		cpto="Otros Conceptos Salarial";
		        		cantidad = null;
		        		VlrDevengo =nominaIndividual.getDevengados().getOtrosConceptos().getOtroConcepto().get(i).getConceptoS();
		        		VlrDeduccion = null;
		        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
		        		listitems.add(listamov);
	        		}
	        	}
	        	if(nominaIndividual.getDevengados().getOtrosConceptos()!=null) {
	        		for(int i=0; i<nominaIndividual.getDevengados().getOtrosConceptos().getOtroConcepto().size(); i++) {
	        			codCpto="NIE148";
		        		cpto="Otros Conceptos No Salarial";
		        		cantidad = null;
		        		VlrDevengo =nominaIndividual.getDevengados().getOtrosConceptos().getOtroConcepto().get(i).getConceptoNS();
		        		VlrDeduccion = null;
		        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
		        		listitems.add(listamov);
	        		}	
	        	}
	        	if(nominaIndividual.getDevengados().getPrimas()!=null) {
	        		codCpto="NIE118";
	        		cpto="Primas";
	        		cantidad = BigDecimal.valueOf(Double.parseDouble(nominaIndividual.getDevengados().getPrimas().getCantidad().toString()));
	        		VlrDevengo =nominaIndividual.getDevengados().getPrimas().getPago();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
            	}
	        	if(nominaIndividual.getDevengados().getReintegro()!=null) {
	        		codCpto="NIE201";
	        		cpto="Reintengro";
	        		cantidad = null;
	        		VlrDevengo =nominaIndividual.getDevengados().getReintegro();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
            	}
	        	if(nominaIndividual.getDevengados().getTeletrabajo()!=null) {
	        
	        		codCpto="NIE158";
	        		cpto="Teletrabajo";
	        		cantidad = null;
	        		VlrDevengo =nominaIndividual.getDevengados().getTeletrabajo();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
            	}
	        	if(nominaIndividual.getDevengados().getTransporte()!=null) {
	        		codCpto="NIE070";
	        		cpto="Auxilio de transporte";
	        		cantidad = BigDecimal.valueOf(nominaIndividual.getDevengados().getBasico().getDiasTrabajados().longValue());
	        		VlrDevengo =nominaIndividual.getDevengados().getTransporte().get(0).getAuxilioTransporte();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDevengados().getVacaciones()!=null) {
	        		codCpto="NIE112";
	        		cpto="Vacaciones Comunes";
	        		cantidad = BigDecimal.valueOf(Double.parseDouble(nominaIndividual.getDevengados().getVacaciones().getVacacionesComunes().get(0).getCantidad().toString()));
	        		VlrDevengo =nominaIndividual.getDevengados().getVacaciones().getVacacionesComunes().get(0).getPago();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDevengados().getVacaciones()!=null) {
	        		codCpto="NIE116";
	        		cpto="Vacaciones Compensadas";
	        		cantidad = BigDecimal.valueOf(Double.parseDouble(nominaIndividual.getDevengados().getVacaciones().getVacacionesCompensadas().get(0).getCantidad().toString()));
	        		VlrDevengo =nominaIndividual.getDevengados().getVacaciones().getVacacionesCompensadas().get(0).getPago();
	        		VlrDeduccion = null;
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	/** DEVENGADOS */
	        	if(nominaIndividual.getDeducciones().getAFC()!=null) {
	        		codCpto="NIE179";
	        		cpto="AFC";
	        		cantidad = null;
	        		VlrDevengo =null;
	        		VlrDeduccion = nominaIndividual.getDeducciones().getAFC();
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDeducciones().getAnticipos() !=null) {
	        		codCpto="NIE196";
	        		cpto="Anticipos";
	        		cantidad = null;
	        		VlrDevengo =null;
	        		VlrDeduccion = BigDecimal.valueOf(Double.parseDouble(nominaIndividual.getDeducciones().getAnticipos().getAnticipo().get(0).toString()));
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	 	       	}
	        	if(nominaIndividual.getDeducciones().getCooperativa() !=null) {
	        		codCpto="NIE180";
	        		cpto="Cooperativa";
	        		cantidad = null;
	        		VlrDevengo =null;
	        		VlrDeduccion = nominaIndividual.getDeducciones().getCooperativa();
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	 	    	}
	        	if(nominaIndividual.getDeducciones().getDeuda()!=null) {
	        		codCpto="NIE185";
	        		cpto="Deuda";
	        		cantidad = null;
	        		VlrDevengo =null;
	        		VlrDeduccion = nominaIndividual.getDeducciones().getDeuda();
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	 	    	}
	        	if(nominaIndividual.getDeducciones().getEducacion()!=null) {
	        		codCpto="NIE183";
	        		cpto="Educacion";
	        		cantidad = null;
	        		VlrDevengo =null;
	        		VlrDeduccion = nominaIndividual.getDeducciones().getDeuda();
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDeducciones().getEmbargoFiscal()!=null) {
	        		codCpto="NIE181";
	        		cpto="EmbargoFiscal";
	        		cantidad = null;
	        		VlrDevengo =null;
	        		VlrDeduccion = nominaIndividual.getDeducciones().getEmbargoFiscal();
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDeducciones().getFondoPension()!=null) {
	        		codCpto="NIE166";
	        		cpto="Fondo Pension ";
	        		cantidad = nominaIndividual.getDeducciones().getFondoPension().getPorcentaje();
	        		VlrDevengo =null;
	        		VlrDeduccion = nominaIndividual.getDeducciones().getFondoPension().getDeduccion();
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDeducciones().getFondoSP()!=null) {
	        		codCpto="NIE168";
	        		cpto="FondoPs";
	        		cantidad = nominaIndividual.getDeducciones().getFondoSP().getPorcentaje();
	        		VlrDevengo =null;
	        		VlrDeduccion = nominaIndividual.getDeducciones().getFondoSP().getDeduccion();
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);	        	}
	        	if(nominaIndividual.getDeducciones().getLibranzas()!=null) {
	        		codCpto="NIE176";
	        		cpto="Libranzas";
	        		cantidad = null;
	        		VlrDevengo =null;
	        		VlrDeduccion = nominaIndividual.getDeducciones().getLibranzas().getLibranza().get(0).getDeduccion();
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);	     
	        	}
	        	if(nominaIndividual.getDeducciones().getOtrasDeducciones()!=null) {
	        		codCpto="NIE197";
	        		cpto="OtrasDeducciones";
	        		cantidad = null;
	        		VlrDevengo =null;
	        		VlrDeduccion = BigDecimal.valueOf(Double.parseDouble(nominaIndividual.getDeducciones().getOtrasDeducciones().getOtraDeduccion().get(0).toString()));
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDeducciones().getPagosTerceros()!=null) {
	        		codCpto="NIE195";
	        		cpto="Pago terceros";
	        		cantidad = null;
	        		VlrDevengo =null;
	        		VlrDeduccion = BigDecimal.valueOf(Double.parseDouble(nominaIndividual.getDeducciones().getPagosTerceros().getPagoTercero().get(0).toString()));
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDeducciones().getPensionVoluntaria()!=null) {
	        		codCpto="NIE198";
	        		cpto="PensionVoluntaria";
	        		cantidad = null;
	        		VlrDevengo =null;
	        		VlrDeduccion = nominaIndividual.getDeducciones().getPensionVoluntaria();
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDeducciones().getPlanComplementarios()!=null) {
	        		codCpto="NIE182";
	        		cpto="Plan complementario";
	        		cantidad = null;
	        		VlrDevengo =null;
	        		VlrDeduccion = nominaIndividual.getDeducciones().getPlanComplementarios();
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDeducciones().getReintegro()!=null) {
	        		codCpto="NIE184";
	        		cpto="Reintegro";
	        		cantidad = null;
	        		VlrDevengo =null;
	        		VlrDeduccion = nominaIndividual.getDeducciones().getReintegro();
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDeducciones().getRetencionFuente()!=null) {
	         		codCpto="NIE177";
	        		cpto="Retencion Fuente";
	        		cantidad = null;
	        		VlrDevengo =null;
	        		VlrDeduccion = nominaIndividual.getDeducciones().getRetencionFuente();
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	if(nominaIndividual.getDeducciones().getSalud()!=null) {
	        		codCpto="NIE163";
	        		cpto="Salud";
	        		cantidad = nominaIndividual.getDeducciones().getSalud().getPorcentaje();
	        		VlrDevengo =null;
	        		VlrDeduccion = nominaIndividual.getDeducciones().getSalud().getDeduccion();
	        		listamov = new Object[]{codCpto,cpto,cantidad,VlrDevengo,VlrDeduccion};
	        		listitems.add(listamov);
	        	}
	        	
	        	for(Object[] objects :listitems) {
		        	ItemLiquidacionDTO itemLiquidacion=new ItemLiquidacionDTO(); 			
		 			itemLiquidacion.setFECHAPERINI(sdf.parse(nominaIndividual.getPeriodo().getFechaLiquidacionInicio().toString()));
		 			itemLiquidacion.setFECHAPERFIN(sdf.parse(nominaIndividual.getPeriodo().getFechaLiquidacionFin().toString()));
		 			itemLiquidacion.setNOMBRES(nominaIndividual.getTrabajador().getSegundoApellido() + " " + nominaIndividual.getTrabajador().getPrimerApellido()+
		 					" "+ nominaIndividual.getTrabajador().getPrimerNombre() + " " + nominaIndividual.getTrabajador().getOtrosNombres());
		 			itemLiquidacion.setNRODOCUMENTO(nominaIndividual.getTrabajador().getNumeroDocumento().toString());
		 			itemLiquidacion.setTIPODOCUMENTO(nominaIndividual.getTrabajador().getTipoDocumento().equals(BigInteger.valueOf(13))?"CC":"CC");
		 			itemLiquidacion.setCODIGOHV(nominaIndividual.getTrabajador().getCodigoTrabajador());
		 			itemLiquidacion.setFECHAINGRESO(sdf.parse(nominaIndividual.getPeriodo().getFechaIngreso().toString()));
		 			Query thcargos = session.createNamedQuery("thcargos");
		 			thcargos.setParameter("nrodocument", nominaIndividual.getTrabajador().getNumeroDocumento().toString());
		 			List<Object[]>cargo = thcargos.getResultList();
		 			itemLiquidacion.setCARGO(cargo.get(0)[0]!=null?cargo.get(0)[0].toString():" ");
		 			itemLiquidacion.setSALUD(null);
		 			itemLiquidacion.setPENSIONES(null);
		 			itemLiquidacion.setENTIDADBANCARIA(nominaIndividual.getPago().getBanco());
		 			itemLiquidacion.setNROCUENTA(nominaIndividual.getPago().getNumeroCuenta()==null?nominaIndividual.getPago().getNumeroCuenta():"");
		 			itemLiquidacion.setSALARIO(nominaIndividual.getTrabajador().getSueldo());
		 			itemLiquidacion.setFECHAPAGO(sdf.parse(nominaIndividual.getFechasPagos().getFechaPago().get(0).toString()));

		 			itemLiquidacion.setOBSERVACIONES("Nomina Electronica:" + nominaIndividual.getTrabajador().getPrimerNombre() + " " 
		 					+nominaIndividual.getTrabajador().getPrimerApellido() + " Con identificación: " + nominaIndividual.getTrabajador().getNumeroDocumento());
		 			//PARA CONSULTAR CON EL NIT THSOCIED
		 			Query thsocied = session.createNamedQuery("thsocied");
		 			List<Object[]> socieda = thsocied.getResultList();
		 			itemLiquidacion.setCUNE(nominaIndividual.getInformacionGeneral().getCUNE());
		 			itemLiquidacion.setTELEMPRESA(socieda.get(0)[0]!=null?socieda.get(0)[0].toString():" ");
		 			itemLiquidacion.setCIUDADEMPRESA(socieda.get(0)[1]!=null?socieda.get(0)[1].toString():" ");
		 			itemLiquidacion.setPAISEMPRESA("COLOMBIA");
		 			itemLiquidacion.setWEBEMPRESA(" ");
		 			itemLiquidacion.setMAILEMPRESA(socieda.get(0)[2]!=null?socieda.get(0)[2].toString():" ");
		 			/*--------------------------------------------------*/
		 			itemLiquidacion.setNITEMPRESA(nominaIndividual.getEmpleador().getNIT());
		 			itemLiquidacion.setDIGEMPRESA(nominaIndividual.getEmpleador().getDV().toString());
		 			itemLiquidacion.setDIRECCIONEMPRESA(nominaIndividual.getEmpleador().getDireccion());
		 			itemLiquidacion.setNOMBREEMPRESA(nominaIndividual.getEmpleador().getRazonSocial());
		 			
		 			itemLiquidacion.setELABORO("OGET S.A.S");
		 			itemLiquidacion.setREVISO("");
		 			
		 			itemLiquidacion.setCODCPTO(objects[0].toString());
		 			itemLiquidacion.setCONCEPTO(objects[1].toString());
		 			itemLiquidacion.setCANTIDAD(objects[2]!=null?BigDecimal.valueOf(Double.parseDouble(objects[2].toString())):null);
		 			itemLiquidacion.setVALORDEVENGO(objects[3]!=null?BigDecimal.valueOf(Double.parseDouble(objects[3].toString())):null);
		 			itemLiquidacion.setVALORDEDUCCION(objects[4]!=null?BigDecimal.valueOf(Double.parseDouble(objects[4].toString())):null);
		 			list.add(itemLiquidacion);

	        	}
	        	
	        	JRBeanCollectionDataSource resultSetDataSource2=new JRBeanCollectionDataSource(list);
	        	resultSetDataSource.add(resultSetDataSource2);
	        	
	        	List <JasperPrint> jasperPrintList=new ArrayList<JasperPrint>();   
	        	
	        	InputStream rutaLogoEmpresa=null;     	
       	       	InputStream inputStream =null; 
       	       	InputStream codigoQR = null;
        		String  images = path.substring(0, 24);
        		String QR = rutaout+images+".png";
       	     for(int j=0; j<resultSetDataSource.size(); j++) {
       	       	try {
	        		
	    	    	 inputStream = getClass().getClassLoader().getResourceAsStream("ogetdoc.jasper");
	        		 rutaLogoEmpresa= new FileInputStream(new File(logo)); 
	        		 codigoQR = new FileInputStream(new File(QR));
	    	    	 Map parameters = new HashMap();
	                 parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);  
	    	    	 parameters.put("rutaLogoEmpresa", rutaLogoEmpresa); 
	    	    	 parameters.put("tituloSalario", "SALARIO");
	    	    	 parameters.put("codigoQR", codigoQR);
	    	    	 JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, resultSetDataSource.get(j));
	    	    	 jasperPrintList.add(jasperPrint); 
	        		}catch (Exception e) {
					  e.printStackTrace();
				}finally {
					try {
						if(inputStream!=null)inputStream.close();
						if(rutaLogoEmpresa!=null)rutaLogoEmpresa.close();
						if(codigoQR!=null)codigoQR.close();
					} catch (IOException e) {
						e.printStackTrace();
					}		
				}
	        	
       	     }
         jasperPrintFinal= jasperPrintList.get(0);
	       for (int index = 1; index < jasperPrintList.size(); index += 1) {
	           List<JRPrintPage> pages = jasperPrintList.get(index).getPages();
	           for (JRPrintPage page : pages) {
	           	jasperPrintFinal.addPage(page);
	           }
	       } 
	       
	  /*     ByteArrayOutputStream os = new ByteArrayOutputStream();
      	 JasperExportManager.exportReportToPdfStream(jasperPrintFinal, os);
      	 os.flush();
            os.close();
            InputStream is = new ByteArrayInputStream(os.toByteArray());
	    */   
		 }catch (Exception e) {
		  log.error("[action_imprimir] " + e.getMessage() + " error");
		}
		 log.info("[action_imprimir] FINOK");
		 return jasperPrintFinal;
	 }
}
