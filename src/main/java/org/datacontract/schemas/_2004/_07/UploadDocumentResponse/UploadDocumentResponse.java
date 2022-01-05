/**
 * UploadDocumentResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.UploadDocumentResponse;

public class UploadDocumentResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.XmlParamsResponseTrackId.XmlParamsResponseTrackId[] errorMessageList;

    private java.lang.String zipKey;

    public UploadDocumentResponse() {
    }

    public UploadDocumentResponse(
           org.datacontract.schemas._2004._07.XmlParamsResponseTrackId.XmlParamsResponseTrackId[] errorMessageList,
           java.lang.String zipKey) {
           this.errorMessageList = errorMessageList;
           this.zipKey = zipKey;
    }


    /**
     * Gets the errorMessageList value for this UploadDocumentResponse.
     * 
     * @return errorMessageList
     */
    public org.datacontract.schemas._2004._07.XmlParamsResponseTrackId.XmlParamsResponseTrackId[] getErrorMessageList() {
        return errorMessageList;
    }


    /**
     * Sets the errorMessageList value for this UploadDocumentResponse.
     * 
     * @param errorMessageList
     */
    public void setErrorMessageList(org.datacontract.schemas._2004._07.XmlParamsResponseTrackId.XmlParamsResponseTrackId[] errorMessageList) {
        this.errorMessageList = errorMessageList;
    }


    /**
     * Gets the zipKey value for this UploadDocumentResponse.
     * 
     * @return zipKey
     */
    public java.lang.String getZipKey() {
        return zipKey;
    }


    /**
     * Sets the zipKey value for this UploadDocumentResponse.
     * 
     * @param zipKey
     */
    public void setZipKey(java.lang.String zipKey) {
        this.zipKey = zipKey;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UploadDocumentResponse)) return false;
        UploadDocumentResponse other = (UploadDocumentResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorMessageList==null && other.getErrorMessageList()==null) || 
             (this.errorMessageList!=null &&
              java.util.Arrays.equals(this.errorMessageList, other.getErrorMessageList()))) &&
            ((this.zipKey==null && other.getZipKey()==null) || 
             (this.zipKey!=null &&
              this.zipKey.equals(other.getZipKey())));
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
        if (getErrorMessageList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrorMessageList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrorMessageList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getZipKey() != null) {
            _hashCode += getZipKey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UploadDocumentResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/UploadDocumentResponse", "UploadDocumentResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorMessageList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/UploadDocumentResponse", "ErrorMessageList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/XmlParamsResponseTrackId", "XmlParamsResponseTrackId"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/XmlParamsResponseTrackId", "XmlParamsResponseTrackId"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zipKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/UploadDocumentResponse", "ZipKey"));
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
