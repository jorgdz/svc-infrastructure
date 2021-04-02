package com.righttek.es.marcacion.exception.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 3423670784775426098L;
	
	private LocalDateTime timestamps;
	
	private String path;
	
	private int code;
	
	private String message;
	
	private String exception;
	
	private List<String> details;
	
	public ErrorResponse(Exception ex, String path, int code) {
		this.timestamps = LocalDateTime.now();
		this.message = ex.getMessage();
		this.exception = ex.getClass().getSimpleName();
		this.path = path;
		this.code = code;
	}
	
	public ErrorResponse (String message, Exception ex, String path, int code) {
		this.timestamps = LocalDateTime.now();
		this.message = message;
		this.exception = ex.getClass().getSimpleName();
		this.path = path;
		this.code = code;
	}

	public ErrorResponse (List<String> details, Exception ex, String path, int code) {
		this.timestamps = LocalDateTime.now();
		this.details = details;
		this.exception = ex.getClass().getSimpleName();
		this.path = path;
		this.code = code;
	}
	
	public ErrorResponse (String message, List<String> details, Exception ex, String path, int code) {
		this.timestamps = LocalDateTime.now();
		this.message = message;
		this.details = details;
		this.exception = ex.getClass().getSimpleName();
		this.path = path;
		this.code = code;
	}
	
	/**
	 * @return the timestamps
	 */
	public LocalDateTime getTimestamps() {
		return timestamps;
	}

	/**
	 * @param timestamps the timestamps to set
	 */
	public void setTimestamps(LocalDateTime timestamps) {
		this.timestamps = timestamps;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}

	/**
	 * @param exception the exception to set
	 */
	public void setException(String exception) {
		this.exception = exception;
	}

	/**
	 * @return the details
	 */
	public List<String> getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(List<String> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ErrorResponse [timestamps=" + timestamps + ", path=" + path + ", code=" + code + ", message=" + message
				+ ", exception=" + exception + "]";
	}
	
}
