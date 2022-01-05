/**
 * NumberRangeResponseList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.NumberRangeResponseList;

public class NumberRangeResponseList  implements java.io.Serializable {
    private java.lang.String operationCode;

    private java.lang.String operationDescription;

    private org.datacontract.schemas._2004._07.NumberRangeResponse.NumberRangeResponse[] responseList;

    public NumberRangeResponseList() {
    }

    public NumberRangeResponseList(
           java.lang.String operationCode,
           java.lang.String operationDescription,
           org.datacontract.schemas._2004._07.NumberRangeResponse.NumberRangeResponse[] responseList) {
           this.operationCode = operationCode;
           this.operationDescription = operationDescription;
           this.responseList = responseList;
    }


    /**
     * Gets the operationCode value for this NumberRangeResponseList.
     * 
     * @return operationCode
     */
    public java.lang.String getOperationCode() {
        return operationCode;
    }


    /**
     * Sets the operationCode value for this NumberRangeResponseList.
     * 
     * @param operationCode
     */
    public void setOperationCode(java.lang.String operationCode) {
        this.operationCode = operationCode;
    }


    /**
     * Gets the operationDescription value for this NumberRangeResponseList.
     * 
     * @return operationDescription
     */
    public java.lang.String getOperationDescription() {
        return operationDescription;
    }


    /**
     * Sets the operationDescription value for this NumberRangeResponseList.
     * 
     * @param operationDescription
     */
    public void setOperationDescription(java.lang.String operationDescription) {
        this.operationDescription = operationDescription;
    }


    /**
     * Gets the responseList value for this NumberRangeResponseList.
     * 
     * @return responseList
     */
    public org.datacontract.schemas._2004._07.NumberRangeResponse.NumberRangeResponse[] getResponseList() {
        return responseList;
    }


    /**
     * Sets the responseList value for this NumberRangeResponseList.
     * 
     * @param responseList
     */
    public void setResponseList(org.datacontract.schemas._2004._07.NumberRangeResponse.NumberRangeResponse[] responseList) {
        this.responseList = responseList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NumberRangeResponseList)) return false;
        NumberRangeResponseList other = (NumberRangeResponseList) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.operationCode==null && other.getOperationCode()==null) || 
             (this.operationCode!=null &&
              this.operationCode.equals(other.getOperationCode()))) &&
            ((this.operationDescription==null && other.getOperationDescription()==null) || 
             (this.operationDescription!=null &&
              this.operationDescription.equals(other.getOperationDescription()))) &&
            ((this.responseList==null && other.getResponseList()==null) || 
             (this.responseList!=null &&
              java.util.Arrays.equals(this.responseList, other.getResponseList())));
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
        if (getOperationCode() != null) {
            _hashCode += getOperationCode().hashCode();
        }
        if (getOperationDescription() != null) {
            _hashCode += getOperationDescription().hashCode();
        }
        if (getResponseList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResponseList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResponseList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NumberRangeResponseList.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponseList", "NumberRangeResponseList"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operationCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponseList", "OperationCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operationDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponseList", "OperationDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponseList", "ResponseList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponse", "NumberRangeResponse"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponse", "NumberRangeResponse"));
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
