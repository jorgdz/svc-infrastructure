package com.righttek.ms.importarmarcaciones.exception.custom;

public class MarcacionFechaException extends RuntimeException {

	private static final long serialVersionUID = 2068808338534721406L;
	
	public MarcacionFechaException(String context) {
		super(context);
	}
}
