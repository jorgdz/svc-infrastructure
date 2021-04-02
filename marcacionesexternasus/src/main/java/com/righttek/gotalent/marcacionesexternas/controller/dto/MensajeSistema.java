/**
 * 
 */
package com.righttek.gotalent.marcacionesexternas.controller.dto;



/**
 * @author Righttek
 * @author CESAR GARCIA
 * @since 2020
 */

public class MensajeSistema  {

	private String codigo;
	private String mensaje;

	public MensajeSistema() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MensajeSistema(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
