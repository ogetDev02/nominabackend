/**
 * XmlParamsResponseTrackId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.XmlParamsResponseTrackId;

public class XmlParamsResponseTrackId  implements java.io.Serializable {
    private java.lang.String documentKey;

    private java.lang.String processedMessage;

    private java.lang.String senderCode;

    private java.lang.Boolean success;

    private java.lang.String xmlFileName;

    public XmlParamsResponseTrackId() {
    }

    public XmlParamsResponseTrackId(
           java.lang.String documentKey,
           java.lang.String processedMessage,
           java.lang.String senderCode,
           java.lang.Boolean success,
           java.lang.String xmlFileName) {
           this.documentKey = documentKey;
           this.processedMessage = processedMessage;
           this.senderCode = senderCode;
           this.success = success;
           this.xmlFileName = xmlFileName;
    }


    /**
     * Gets the documentKey value for this XmlParamsResponseTrackId.
     * 
     * @return documentKey
     */
    public java.lang.String getDocumentKey() {
        return documentKey;
    }


    /**
     * Sets the documentKey value for this XmlParamsResponseTrackId.
     * 
     * @param documentKey
     */
    public void setDocumentKey(java.lang.String documentKey) {
        this.documentKey = documentKey;
    }


    /**
     * Gets the processedMessage value for this XmlParamsResponseTrackId.
     * 
     * @return processedMessage
     */
    public java.lang.String getProcessedMessage() {
        return processedMessage;
    }


    /**
     * Sets the processedMessage value for this XmlParamsResponseTrackId.
     * 
     * @param processedMessage
     */
    public void setProcessedMessage(java.lang.String processedMessage) {
        this.processedMessage = processedMessage;
    }


    /**
     * Gets the senderCode value for this XmlParamsResponseTrackId.
     * 
     * @return senderCode
     */
    public java.lang.String getSenderCode() {
        return senderCode;
    }


    /**
     * Sets the senderCode value for this XmlParamsResponseTrackId.
     * 
     * @param senderCode
     */
    public void setSenderCode(java.lang.String senderCode) {
        this.senderCode = senderCode;
    }


    /**
     * Gets the success value for this XmlParamsResponseTrackId.
     * 
     * @return success
     */
    public java.lang.Boolean getSuccess() {
        return success;
    }


    /**
     * Sets the success value for this XmlParamsResponseTrackId.
     * 
     * @param success
     */
    public void setSuccess(java.lang.Boolean success) {
        this.success = success;
    }


    /**
     * Gets the xmlFileName value for this XmlParamsResponseTrackId.
     * 
     * @return xmlFileName
     */
    public java.lang.String getXmlFileName() {
        return xmlFileName;
    }


    /**
     * Sets the xmlFileName value for this XmlParamsResponseTrackId.
     * 
     * @param xmlFileName
     */
    public void setXmlFileName(java.lang.String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof XmlParamsResponseTrackId)) return false;
        XmlParamsResponseTrackId other = (XmlParamsResponseTrackId) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentKey==null && other.getDocumentKey()==null) || 
             (this.documentKey!=null &&
              this.documentKey.equals(other.getDocumentKey()))) &&
            ((this.processedMessage==null && other.getProcessedMessage()==null) || 
             (this.processedMessage!=null &&
              this.processedMessage.equals(other.getProcessedMessage()))) &&
            ((this.senderCode==null && other.getSenderCode()==null) || 
             (this.senderCode!=null &&
              this.senderCode.equals(other.getSenderCode()))) &&
            ((this.success==null && other.getSuccess()==null) || 
             (this.success!=null &&
              this.success.equals(other.getSuccess()))) &&
            ((this.xmlFileName==null && other.getXmlFileName()==null) || 
             (this.xmlFileName!=null &&
              this.xmlFileName.equals(other.getXmlFileName())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDocumentKey() != null) {
            _hashCode += getDocumentKey().hashCode();
        }
        if (getProcessedMessage() != null) {
            _hashCode += getProcessedMessage().hashCode();
        }
        if (getSenderCode() != null) {
            _hashCode += getSenderCode().hashCode();
        }
        if (getSuccess() != null) {
            _hashCode += getSuccess().hashCode();
        }
        if (getXmlFileName() != null) {
            _hashCode += getXmlFileName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(XmlParamsResponseTrackId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/XmlParamsResponseTrackId", "XmlParamsResponseTrackId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/XmlParamsResponseTrackId", "DocumentKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processedMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/XmlParamsResponseTrackId", "ProcessedMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/XmlParamsResponseTrackId", "SenderCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("success");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/XmlParamsResponseTrackId", "Success"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xmlFileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/XmlParamsResponseTrackId", "XmlFileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
