package com.aimachines.cdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectDto {
	
	@JacksonXmlProperty(localName = "V")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ElementDto[] vElements;

	public ObjectDto() {
	}

	/**
	 * @return the vElements
	 */
	public ElementDto[] getvElements() {
		return vElements;
	}

	/**
	 * @param vElements the vElements to set
	 */
	public void setvElements(ElementDto[] vElements) {
		this.vElements = vElements;
	}

	
	
}
