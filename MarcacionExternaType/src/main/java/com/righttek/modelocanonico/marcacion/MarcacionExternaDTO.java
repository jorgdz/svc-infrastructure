package com.righttek.modelocanonico.marcacion;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class MarcacionExternaDTO {
	
	private int idMarcacion;
	
	@NotNull(message = "La fecha no debe ser nula.")
	private Date fechaHora;
	
	@Positive(message = "Solo se admiten valores positivos.")
	private int idOrigenMarcacion;
	
	@Min(value = 1L, message = "Los valores permitidos son 1-2-3-4.")
	@Max(value = 4L, message = "Los valores permitidos son 1-2-3-4.")
	private int codigoMarcacion;
	
	@Email(message = "Formato de email no válido.")
	private String emailEmpleado;

	/**
	 * @return the idMarcacion
	 */
	public int getIdMarcacion() {
		return idMarcacion;
	}

	/**
	 * @param idMarcacion the idMarcacion to set
	 */
	public void setIdMarcacion(int idMarcacion) {
		this.idMarcacion = idMarcacion;
	}

	/**
	 * @return the fechaHora
	 */
	public Date getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora the fechaHora to set
	 */
	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return the idOrigenMarcacion
	 */
	public int getIdOrigenMarcacion() {
		return idOrigenMarcacion;
	}

	/**
	 * @param idOrigenMarcacion the idOrigenMarcacion to set
	 */
	public void setIdOrigenMarcacion(int idOrigenMarcacion) {
		this.idOrigenMarcacion = idOrigenMarcacion;
	}

	/**
	 * @return the codigoMarcacion
	 */
	public int getCodigoMarcacion() {
		return codigoMarcacion;
	}

	/**
	 * @param codigoMarcacion the codigoMarcacion to set
	 */
	public void setCodigoMarcacion(int codigoMarcacion) {
		this.codigoMarcacion = codigoMarcacion;
	}

	/**
	 * @return the emailEmpleado
	 */
	public String getEmailEmpleado() {
		return emailEmpleado;
	}

	/**
	 * @param emailEmpleado the emailEmpleado to set
	 */
	public void setEmailEmpleado(String emailEmpleado) {
		this.emailEmpleado = emailEmpleado;
	}

	@Override
	public String toString() {
		return "MarcacionExternaDTO [idMarcacion=" + idMarcacion + ", fechaHora=" + fechaHora + ", idOrigenMarcacion="
				+ idOrigenMarcacion + ", codigoMarcacion=" + codigoMarcacion + ", emailEmpleado=" + emailEmpleado + "]";
	}
}
