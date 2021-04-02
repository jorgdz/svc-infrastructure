package com.righttek.ms.importarmarcaciones.service.dto;

import com.righttek.ms.importarmarcaciones.service.command.IParam;

public class GenericParam implements IParam {

	private String value;
	
	public GenericParam(String value) {
		super();
		this.value = value;
	}
	
	/**
	 * 
	 * @return value
	 */
	public String getValue () {
		return this.value;
	}
}
