package com;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(Include.NON_NULL)
public class Value extends BaseValue {

    @JsonProperty("Attribute")
    @XmlElement(name = "Attribute")
    private String attribute;

    @XmlElement(name = "Value")
    @JsonProperty("Value")
    private String value;

    @XmlElement(name = "Unit")
    @JsonProperty("Unit")
    private String unit;
    
    // STORY: VELO-8350
    // Author: ZAhmed, Date: 11/04/2015, Fix Version 1.3.10
    // Changes: Need an additional field for API to expose the RADAR Field for different criteria: CustomValueCode
    @XmlElement(name = "CustomerOrderCode")
    @JsonProperty("CustomerOrderCode")
    private String customerOrderCode;


    @JsonIgnore
    private String criteriaType;

    @JsonIgnore
    public String getCriteriaType() {
        return criteriaType;
    }

    @JsonIgnore
    public void setCriteriaType(String criteriaType) {
        this.criteriaType = criteriaType;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCustomerOrderCode() {
        return customerOrderCode;
    }

    public void setCustomerOrderCode(String customerOrderCode) {
        this.customerOrderCode = customerOrderCode;
    }

    @Override
    public String toString() {
        return "[\"Value\": \"" + this.getValue() + "\"]";
    }

}
