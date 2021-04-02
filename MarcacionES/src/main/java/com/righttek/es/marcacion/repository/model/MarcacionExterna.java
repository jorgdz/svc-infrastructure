package com.righttek.es.marcacion.repository.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author JORGE
 *
 */
@Entity
@Table(name = "marcacion_externa")
public class MarcacionExterna implements Serializable {
	
	private static final long serialVersionUID = -6179823885673464678L;

	@Id
	@Column(name = "id_marcacion", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger idMarcacion;
	
	@Column(name = "fecha_hora")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaHora;
	
	@Column(name = "id_origen_marcacion")
	private BigInteger idOrigenMarcacion;
	
	@Column(name = "codigo_marcacion")
	private int codigoMarcacion;
	
	@Column(name = "email_empleado")
	private String emailEmpleado;

	/**
	 * @return the idMarcacion
	 */
	public BigInteger getIdMarcacion() {
		return idMarcacion;
	}

	/**
	 * @param idMarcacion the idMarcacion to set
	 */
	public void setIdMarcacion(BigInteger idMarcacion) {
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
	public BigInteger getIdOrigenMarcacion() {
		return idOrigenMarcacion;
	}

	/**
	 * @param idOrigenMarcacion the idOrigenMarcacion to set
	 */
	public void setIdOrigenMarcacion(BigInteger idOrigenMarcacion) {
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
		return "MarcacionExterna [idMarcacion=" + idMarcacion + ", fechaHora=" + fechaHora + ", idOrigenMarcacion="
				+ idOrigenMarcacion + ", codigoMarcacion=" + codigoMarcacion + ", emailEmpleado=" + emailEmpleado + "]";
	}

}
