package com.aimachines.cdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ElementDto {
	@JacksonXmlProperty(isAttribute = true)
	private String Idx;

	@JacksonXmlText
	private String value;

	public ElementDto() {

	}

	/**
	 * @return the idx
	 */
	public String getIdx() {
		return Idx;
	}

	/**
	 * @param idx the idx to set
	 */
	public void setIdx(String idx) {
		Idx = idx;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
