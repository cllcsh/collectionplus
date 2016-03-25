/**
 * DataSynServicePortTypeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osource.base.ws;

public class DataSynServicePortTypeServiceLocator extends org.apache.axis.client.Service implements com.osource.base.ws.DataSynServicePortTypeService {

    public DataSynServicePortTypeServiceLocator() {
    }


    public DataSynServicePortTypeServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DataSynServicePortTypeServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DataSynService
    private java.lang.String DataSynService_address = "http://localhost:8080/axis/services/DataSynService";

    public java.lang.String getDataSynServiceAddress() {
        return DataSynService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DataSynServiceWSDDServiceName = "DataSynService";

    public java.lang.String getDataSynServiceWSDDServiceName() {
        return DataSynServiceWSDDServiceName;
    }

    public void setDataSynServiceWSDDServiceName(java.lang.String name) {
        DataSynServiceWSDDServiceName = name;
    }

    public com.osource.base.ws.DataSynServicePortType getDataSynService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DataSynService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDataSynService(endpoint);
    }

    public com.osource.base.ws.DataSynServicePortType getDataSynService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.osource.base.ws.MDataSynServiceSoapBindingStub _stub = new com.osource.base.ws.MDataSynServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getDataSynServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDataSynServiceEndpointAddress(java.lang.String address) {
        DataSynService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.osource.base.ws.DataSynServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.osource.base.ws.MDataSynServiceSoapBindingStub _stub = new com.osource.base.ws.MDataSynServiceSoapBindingStub(new java.net.URL(DataSynService_address), this);
                _stub.setPortName(getDataSynServiceWSDDServiceName());
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
        if ("DataSynService".equals(inputPortName)) {
            return getDataSynService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8080/axis/services/DataSynService", "DataSynServicePortTypeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:8080/axis/services/DataSynService", "DataSynService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DataSynService".equals(portName)) {
            setDataSynServiceEndpointAddress(address);
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
