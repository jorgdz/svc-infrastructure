package com.righttek.ms.importarmarcaciones.exception.custom;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -646395508526405507L;
	
	public NotFoundException(String message) {
		super(message);
	}
}
