/**
 * WcfDianCustomerServicesLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package colombia.dian.wcf;

public class WcfDianCustomerServicesLocator extends org.apache.axis.client.Service implements colombia.dian.wcf.WcfDianCustomerServices {

    public WcfDianCustomerServicesLocator() {
    }


    public WcfDianCustomerServicesLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WcfDianCustomerServicesLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSHttpBinding_IWcfDianCustomerServices
    private java.lang.String WSHttpBinding_IWcfDianCustomerServices_address = "https://vpfe-hab.dian.gov.co/WcfDianCustomerServices.svc";

    public java.lang.String getWSHttpBinding_IWcfDianCustomerServicesAddress() {
        return WSHttpBinding_IWcfDianCustomerServices_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSHttpBinding_IWcfDianCustomerServicesWSDDServiceName = "WSHttpBinding_IWcfDianCustomerServices";

    public java.lang.String getWSHttpBinding_IWcfDianCustomerServicesWSDDServiceName() {
        return WSHttpBinding_IWcfDianCustomerServicesWSDDServiceName;
    }

    public void setWSHttpBinding_IWcfDianCustomerServicesWSDDServiceName(java.lang.String name) {
        WSHttpBinding_IWcfDianCustomerServicesWSDDServiceName = name;
    }

    public colombia.dian.wcf.IWcfDianCustomerServices getWSHttpBinding_IWcfDianCustomerServices() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSHttpBinding_IWcfDianCustomerServices_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSHttpBinding_IWcfDianCustomerServices(endpoint);
    }

    public colombia.dian.wcf.IWcfDianCustomerServices getWSHttpBinding_IWcfDianCustomerServices(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            colombia.dian.wcf.WSHttpBinding_IWcfDianCustomerServicesStub _stub = new colombia.dian.wcf.WSHttpBinding_IWcfDianCustomerServicesStub(portAddress, this);
            _stub.setPortName(getWSHttpBinding_IWcfDianCustomerServicesWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSHttpBinding_IWcfDianCustomerServicesEndpointAddress(java.lang.String address) {
        WSHttpBinding_IWcfDianCustomerServices_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (colombia.dian.wcf.IWcfDianCustomerServices.class.isAssignableFrom(serviceEndpointInterface)) {
                colombia.dian.wcf.WSHttpBinding_IWcfDianCustomerServicesStub _stub = new colombia.dian.wcf.WSHttpBinding_IWcfDianCustomerServicesStub(new java.net.URL(WSHttpBinding_IWcfDianCustomerServices_address), this);
                _stub.setPortName(getWSHttpBinding_IWcfDianCustomerServicesWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WSHttpBinding_IWcfDianCustomerServices".equals(inputPortName)) {
            return getWSHttpBinding_IWcfDianCustomerServices();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://wcf.dian.colombia", "WcfDianCustomerServices");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://wcf.dian.colombia", "WSHttpBinding_IWcfDianCustomerServices"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSHttpBinding_IWcfDianCustomerServices".equals(portName)) {
            setWSHttpBinding_IWcfDianCustomerServicesEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
