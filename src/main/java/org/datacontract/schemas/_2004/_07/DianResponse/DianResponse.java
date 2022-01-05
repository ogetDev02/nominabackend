/**
 * DianResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.DianResponse;

public class DianResponse  implements java.io.Serializable {
    private java.lang.String[] errorMessage;

    private java.lang.Boolean isValid;

    private java.lang.String statusCode;

    private java.lang.String statusDescription;

    private java.lang.String statusMessage;

    private byte[] xmlBase64Bytes;

    private byte[] xmlBytes;

    private java.lang.String xmlDocumentKey;

    private java.lang.String xmlFileName;

    public DianResponse() {
    }

    public DianResponse(
           java.lang.String[] errorMessage,
           java.lang.Boolean isValid,
           java.lang.String statusCode,
           java.lang.String statusDescription,
           java.lang.String statusMessage,
           byte[] xmlBase64Bytes,
           byte[] xmlBytes,
           java.lang.String xmlDocumentKey,
           java.lang.String xmlFileName) {
           this.errorMessage = errorMessage;
           this.isValid = isValid;
           this.statusCode = statusCode;
           this.statusDescription = statusDescription;
           this.statusMessage = statusMessage;
           this.xmlBase64Bytes = xmlBase64Bytes;
           this.xmlBytes = xmlBytes;
           this.xmlDocumentKey = xmlDocumentKey;
           this.xmlFileName = xmlFileName;
    }


    /**
     * Gets the errorMessage value for this DianResponse.
     * 
     * @return errorMessage
     */
    public java.lang.String[] getErrorMessage() {
        return errorMessage;
    }


    /**
     * Sets the errorMessage value for this DianResponse.
     * 
     * @param errorMessage
     */
    public void setErrorMessage(java.lang.String[] errorMessage) {
        this.errorMessage = errorMessage;
    }


    /**
     * Gets the isValid value for this DianResponse.
     * 
     * @return isValid
     */
    public java.lang.Boolean getIsValid() {
        return isValid;
    }


    /**
     * Sets the isValid value for this DianResponse.
     * 
     * @param isValid
     */
    public void setIsValid(java.lang.Boolean isValid) {
        this.isValid = isValid;
    }


    /**
     * Gets the statusCode value for this DianResponse.
     * 
     * @return statusCode
     */
    public java.lang.String getStatusCode() {
        return statusCode;
    }


    /**
     * Sets the statusCode value for this DianResponse.
     * 
     * @param statusCode
     */
    public void setStatusCode(java.lang.String statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * Gets the statusDescription value for this DianResponse.
     * 
     * @return statusDescription
     */
    public java.lang.String getStatusDescription() {
        return statusDescription;
    }


    /**
     * Sets the statusDescription value for this DianResponse.
     * 
     * @param statusDescription
     */
    public void setStatusDescription(java.lang.String statusDescription) {
        this.statusDescription = statusDescription;
    }


    /**
     * Gets the statusMessage value for this DianResponse.
     * 
     * @return statusMessage
     */
    public java.lang.String getStatusMessage() {
        return statusMessage;
    }


    /**
     * Sets the statusMessage value for this DianResponse.
     * 
     * @param statusMessage
     */
    public void setStatusMessage(java.lang.String statusMessage) {
        this.statusMessage = statusMessage;
    }


    /**
     * Gets the xmlBase64Bytes value for this DianResponse.
     * 
     * @return xmlBase64Bytes
     */
    public byte[] getXmlBase64Bytes() {
        return xmlBase64Bytes;
    }


    /**
     * Sets the xmlBase64Bytes value for this DianResponse.
     * 
     * @param xmlBase64Bytes
     */
    public void setXmlBase64Bytes(byte[] xmlBase64Bytes) {
        this.xmlBase64Bytes = xmlBase64Bytes;
    }


    /**
     * Gets the xmlBytes value for this DianResponse.
     * 
     * @return xmlBytes
     */
    public byte[] getXmlBytes() {
        return xmlBytes;
    }


    /**
     * Sets the xmlBytes value for this DianResponse.
     * 
     * @param xmlBytes
     */
    public void setXmlBytes(byte[] xmlBytes) {
        this.xmlBytes = xmlBytes;
    }


    /**
     * Gets the xmlDocumentKey value for this DianResponse.
     * 
     * @return xmlDocumentKey
     */
    public java.lang.String getXmlDocumentKey() {
        return xmlDocumentKey;
    }


    /**
     * Sets the xmlDocumentKey value for this DianResponse.
     * 
     * @param xmlDocumentKey
     */
    public void setXmlDocumentKey(java.lang.String xmlDocumentKey) {
        this.xmlDocumentKey = xmlDocumentKey;
    }


    /**
     * Gets the xmlFileName value for this DianResponse.
     * 
     * @return xmlFileName
     */
    public java.lang.String getXmlFileName() {
        return xmlFileName;
    }


    /**
     * Sets the xmlFileName value for this DianResponse.
     * 
     * @param xmlFileName
     */
    public void setXmlFileName(java.lang.String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DianResponse)) return false;
        DianResponse other = (DianResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorMessage==null && other.getErrorMessage()==null) || 
             (this.errorMessage!=null &&
              java.util.Arrays.equals(this.errorMessage, other.getErrorMessage()))) &&
            ((this.isValid==null && other.getIsValid()==null) || 
             (this.isValid!=null &&
              this.isValid.equals(other.getIsValid()))) &&
            ((this.statusCode==null && other.getStatusCode()==null) || 
             (this.statusCode!=null &&
              this.statusCode.equals(other.getStatusCode()))) &&
            ((this.statusDescription==null && other.getStatusDescription()==null) || 
             (this.statusDescription!=null &&
              this.statusDescription.equals(other.getStatusDescription()))) &&
            ((this.statusMessage==null && other.getStatusMessage()==null) || 
             (this.statusMessage!=null &&
              this.statusMessage.equals(other.getStatusMessage()))) &&
            ((this.xmlBase64Bytes==null && other.getXmlBase64Bytes()==null) || 
             (this.xmlBase64Bytes!=null &&
              java.util.Arrays.equals(this.xmlBase64Bytes, other.getXmlBase64Bytes()))) &&
            ((this.xmlBytes==null && other.getXmlBytes()==null) || 
             (this.xmlBytes!=null &&
              java.util.Arrays.equals(this.xmlBytes, other.getXmlBytes()))) &&
            ((this.xmlDocumentKey==null && other.getXmlDocumentKey()==null) || 
             (this.xmlDocumentKey!=null &&
              this.xmlDocumentKey.equals(other.getXmlDocumentKey()))) &&
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
        if (getErrorMessage() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrorMessage());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrorMessage(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIsValid() != null) {
            _hashCode += getIsValid().hashCode();
        }
        if (getStatusCode() != null) {
            _hashCode += getStatusCode().hashCode();
        }
        if (getStatusDescription() != null) {
            _hashCode += getStatusDescription().hashCode();
        }
        if (getStatusMessage() != null) {
            _hashCode += getStatusMessage().hashCode();
        }
        if (getXmlBase64Bytes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getXmlBase64Bytes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getXmlBase64Bytes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getXmlBytes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getXmlBytes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getXmlBytes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getXmlDocumentKey() != null) {
            _hashCode += getXmlDocumentKey().hashCode();
        }
        if (getXmlFileName() != null) {
            _hashCode += getXmlFileName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DianResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DianResponse", "DianResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DianResponse", "ErrorMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isValid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DianResponse", "IsValid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DianResponse", "StatusCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DianResponse", "StatusDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DianResponse", "StatusMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xmlBase64Bytes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DianResponse", "XmlBase64Bytes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xmlBytes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DianResponse", "XmlBytes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xmlDocumentKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DianResponse", "XmlDocumentKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xmlFileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/DianResponse", "XmlFileName"));
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
