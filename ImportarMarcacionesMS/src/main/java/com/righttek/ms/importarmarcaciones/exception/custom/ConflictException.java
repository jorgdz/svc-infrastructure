package com.righttek.ms.importarmarcaciones.exception.custom;

public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = 2496874558212705316L;
	
	public ConflictException(String message) {
		super(message);
	}
}
