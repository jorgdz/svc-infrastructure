package com.righttek.logicacomun.utilitarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class DataValidator {
	
	private DataValidator() {}
	
	public static final boolean validarFecha(String fecha) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);
			sdf.parse(fecha);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
