package org.snomed.otf.scheduler.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class JobParameter {

	public enum Type { BOOLEAN, STRING, CONCEPT, CONCEPT_LIST }
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String paramKey;
	
	@ManyToOne
	@JsonIgnore
	JobParameters parentParams;
	
	Type type;
	
	@JsonInclude(Include.ALWAYS)
	String value;
	
	String description;
	
	String defaultValue;
	
	String validationRule;
	
	Boolean mandatory;
	
	protected JobParameter () {}
	
	protected JobParameter (JobParameters parentParams, String key) {
		this.parentParams = parentParams;
		this.paramKey = key;
	}

	//This function is for chaining and adding a sibling
	//The JobParameter returned is a new one, not "this"
	public JobParameter add(String paramKey) {
		return parentParams.add(paramKey);
	}
	
	public JobParameter withType (Type type) {
		this.type = type;
		return this;
	}
	
	public JobParameter withDescription (String description) {
		this.description = description;
		return this;
	}
	
	public JobParameter withValue(Object value) {
		return setValue(value);
	}

	public JobParameter setValue(Object value) {
		if (value == null) {
			this.value = null;
		} else {
			this.value = value.toString();
		}
		return this;
	}

	public String getValue() {
		return value;
	}
	
	public JobParameter withDefaultValue(Object defaultValue) {
		if (defaultValue == null) {
			this.defaultValue = null;
		} else {
			this.defaultValue = defaultValue.toString();
		}
		return this;
	}

	public JobParameters build() {
		return parentParams;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String key) {
		this.paramKey = key;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public JobParameters getParentParams() {
		return parentParams;
	}

	public void setParentParams(JobParameters parentParams) {
		this.parentParams = parentParams;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getValidationRule() {
		return validationRule;
	}

	public void setValidationRule(String validationRule) {
		this.validationRule = validationRule;
	}

	public void setValue(String value) {
		this.value = value;
	}
}