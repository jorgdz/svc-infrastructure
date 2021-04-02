package com.righttek.modelocanonico.email;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author JORGE
 *
 */
public class EmailNotificacionAtraso {
	
	@NotNull
	@NotBlank
	private String asunto;
	
	@Email
	@NotNull
	private String destinatario;
	
	private String nombreDestinatario;
	
	private String nombreEmpleado;
	
	private double minutosAtraso;

	/**
	 * @return the asunto
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * @param asunto the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return the destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/**
	 * @param destinatario the destinatario to set
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * @return the nombreDestinatario
	 */
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	/**
	 * @param nombreDestinatario the nombreDestinatario to set
	 */
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	/**
	 * @return the nombreEmpleado
	 */
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	/**
	 * @param nombreEmpleado the nombreEmpleado to set
	 */
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	/**
	 * @return the minutosAtraso
	 */
	public double getMinutosAtraso() {
		return minutosAtraso;
	}

	/**
	 * @param minutosAtraso the minutosAtraso to set
	 */
	public void setMinutosAtraso(double minutosAtraso) {
		this.minutosAtraso = minutosAtraso;
	}

	@Override
	public String toString() {
		return "EmailNotificacionAtraso [asunto=" + asunto + ", destinatario=" + destinatario + ", nombreDestinatario="
				+ nombreDestinatario + ", nombreEmpleado=" + nombreEmpleado + ", minutosAtraso=" + minutosAtraso + "]";
	}
	
}
