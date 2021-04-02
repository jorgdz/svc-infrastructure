package com.righttek.es.marcacion.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import com.righttek.modelocanonico.marcacion.MarcacionExternaDTO;

public final class MarcacionDataValidator {
	
	private MarcacionDataValidator() {}
	
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
	
	public static final double validarAtraso (MarcacionExternaDTO marcacion) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		String hora = sdf.format(marcacion.getFechaHora());
		
		LocalTime horaReferencial = LocalTime.parse("08:30:00");
		LocalTime horaMarcacion = LocalTime.parse(hora);
		
		double deltaMinutos = 0.0;
		
		if (horaMarcacion.isAfter(horaReferencial)) {
			deltaMinutos = Math.abs(ChronoUnit.MINUTES.between(horaReferencial, horaMarcacion));
		}
		
		return deltaMinutos;
	}
}
