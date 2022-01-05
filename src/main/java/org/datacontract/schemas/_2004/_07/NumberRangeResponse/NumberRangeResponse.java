/**
 * NumberRangeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.NumberRangeResponse;

public class NumberRangeResponse  implements java.io.Serializable {
    private java.lang.String resolutionNumber;

    private java.lang.String resolutionDate;

    private java.lang.String prefix;

    private java.lang.Long fromNumber;

    private java.lang.Long toNumber;

    private java.lang.String validDateFrom;

    private java.lang.String validDateTo;

    private java.lang.String technicalKey;

    public NumberRangeResponse() {
    }

    public NumberRangeResponse(
           java.lang.String resolutionNumber,
           java.lang.String resolutionDate,
           java.lang.String prefix,
           java.lang.Long fromNumber,
           java.lang.Long toNumber,
           java.lang.String validDateFrom,
           java.lang.String validDateTo,
           java.lang.String technicalKey) {
           this.resolutionNumber = resolutionNumber;
           this.resolutionDate = resolutionDate;
           this.prefix = prefix;
           this.fromNumber = fromNumber;
           this.toNumber = toNumber;
           this.validDateFrom = validDateFrom;
           this.validDateTo = validDateTo;
           this.technicalKey = technicalKey;
    }


    /**
     * Gets the resolutionNumber value for this NumberRangeResponse.
     * 
     * @return resolutionNumber
     */
    public java.lang.String getResolutionNumber() {
        return resolutionNumber;
    }


    /**
     * Sets the resolutionNumber value for this NumberRangeResponse.
     * 
     * @param resolutionNumber
     */
    public void setResolutionNumber(java.lang.String resolutionNumber) {
        this.resolutionNumber = resolutionNumber;
    }


    /**
     * Gets the resolutionDate value for this NumberRangeResponse.
     * 
     * @return resolutionDate
     */
    public java.lang.String getResolutionDate() {
        return resolutionDate;
    }


    /**
     * Sets the resolutionDate value for this NumberRangeResponse.
     * 
     * @param resolutionDate
     */
    public void setResolutionDate(java.lang.String resolutionDate) {
        this.resolutionDate = resolutionDate;
    }


    /**
     * Gets the prefix value for this NumberRangeResponse.
     * 
     * @return prefix
     */
    public java.lang.String getPrefix() {
        return prefix;
    }


    /**
     * Sets the prefix value for this NumberRangeResponse.
     * 
     * @param prefix
     */
    public void setPrefix(java.lang.String prefix) {
        this.prefix = prefix;
    }


    /**
     * Gets the fromNumber value for this NumberRangeResponse.
     * 
     * @return fromNumber
     */
    public java.lang.Long getFromNumber() {
        return fromNumber;
    }


    /**
     * Sets the fromNumber value for this NumberRangeResponse.
     * 
     * @param fromNumber
     */
    public void setFromNumber(java.lang.Long fromNumber) {
        this.fromNumber = fromNumber;
    }


    /**
     * Gets the toNumber value for this NumberRangeResponse.
     * 
     * @return toNumber
     */
    public java.lang.Long getToNumber() {
        return toNumber;
    }


    /**
     * Sets the toNumber value for this NumberRangeResponse.
     * 
     * @param toNumber
     */
    public void setToNumber(java.lang.Long toNumber) {
        this.toNumber = toNumber;
    }


    /**
     * Gets the validDateFrom value for this NumberRangeResponse.
     * 
     * @return validDateFrom
     */
    public java.lang.String getValidDateFrom() {
        return validDateFrom;
    }


    /**
     * Sets the validDateFrom value for this NumberRangeResponse.
     * 
     * @param validDateFrom
     */
    public void setValidDateFrom(java.lang.String validDateFrom) {
        this.validDateFrom = validDateFrom;
    }


    /**
     * Gets the validDateTo value for this NumberRangeResponse.
     * 
     * @return validDateTo
     */
    public java.lang.String getValidDateTo() {
        return validDateTo;
    }


    /**
     * Sets the validDateTo value for this NumberRangeResponse.
     * 
     * @param validDateTo
     */
    public void setValidDateTo(java.lang.String validDateTo) {
        this.validDateTo = validDateTo;
    }


    /**
     * Gets the technicalKey value for this NumberRangeResponse.
     * 
     * @return technicalKey
     */
    public java.lang.String getTechnicalKey() {
        return technicalKey;
    }


    /**
     * Sets the technicalKey value for this NumberRangeResponse.
     * 
     * @param technicalKey
     */
    public void setTechnicalKey(java.lang.String technicalKey) {
        this.technicalKey = technicalKey;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NumberRangeResponse)) return false;
        NumberRangeResponse other = (NumberRangeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resolutionNumber==null && other.getResolutionNumber()==null) || 
             (this.resolutionNumber!=null &&
              this.resolutionNumber.equals(other.getResolutionNumber()))) &&
            ((this.resolutionDate==null && other.getResolutionDate()==null) || 
             (this.resolutionDate!=null &&
              this.resolutionDate.equals(other.getResolutionDate()))) &&
            ((this.prefix==null && other.getPrefix()==null) || 
             (this.prefix!=null &&
              this.prefix.equals(other.getPrefix()))) &&
            ((this.fromNumber==null && other.getFromNumber()==null) || 
             (this.fromNumber!=null &&
              this.fromNumber.equals(other.getFromNumber()))) &&
            ((this.toNumber==null && other.getToNumber()==null) || 
             (this.toNumber!=null &&
              this.toNumber.equals(other.getToNumber()))) &&
            ((this.validDateFrom==null && other.getValidDateFrom()==null) || 
             (this.validDateFrom!=null &&
              this.validDateFrom.equals(other.getValidDateFrom()))) &&
            ((this.validDateTo==null && other.getValidDateTo()==null) || 
             (this.validDateTo!=null &&
              this.validDateTo.equals(other.getValidDateTo()))) &&
            ((this.technicalKey==null && other.getTechnicalKey()==null) || 
             (this.technicalKey!=null &&
              this.technicalKey.equals(other.getTechnicalKey())));
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
        if (getResolutionNumber() != null) {
            _hashCode += getResolutionNumber().hashCode();
        }
        if (getResolutionDate() != null) {
            _hashCode += getResolutionDate().hashCode();
        }
        if (getPrefix() != null) {
            _hashCode += getPrefix().hashCode();
        }
        if (getFromNumber() != null) {
            _hashCode += getFromNumber().hashCode();
        }
        if (getToNumber() != null) {
            _hashCode += getToNumber().hashCode();
        }
        if (getValidDateFrom() != null) {
            _hashCode += getValidDateFrom().hashCode();
        }
        if (getValidDateTo() != null) {
            _hashCode += getValidDateTo().hashCode();
        }
        if (getTechnicalKey() != null) {
            _hashCode += getTechnicalKey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NumberRangeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponse", "NumberRangeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resolutionNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponse", "ResolutionNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resolutionDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponse", "ResolutionDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prefix");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponse", "Prefix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponse", "FromNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponse", "ToNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validDateFrom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponse", "ValidDateFrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validDateTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponse", "ValidDateTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("technicalKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/NumberRangeResponse", "TechnicalKey"));
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
