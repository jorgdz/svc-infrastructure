package com.righttek.gotalent.marcacionesexternas.utils.convert;

/**
 * @author Righttek
 * @author CESAR GARCIA
 * @since 2020
 */

public class ResponseMarcacion {

	private String dateMarked;
	private String length;
	private int idMarking;
	private String location;
	private String minDelay;
	private String minGrace;
	private String latitude;
	private int typeMarked;
	private int userId;

	public ResponseMarcacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDateMarked() {
		return dateMarked;
	}

	public void setDateMarked(String dateMarked) {
		this.dateMarked = dateMarked;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public int getIdMarking() {
		return idMarking;
	}

	public void setIdMarking(int idMarking) {
		this.idMarking = idMarking;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMinDelay() {
		return minDelay;
	}

	public void setMinDelay(String minDelay) {
		this.minDelay = minDelay;
	}

	public String getMinGrace() {
		return minGrace;
	}

	public void setMinGrace(String minGrace) {
		this.minGrace = minGrace;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public int getTypeMarked() {
		return typeMarked;
	}

	public void setTypeMarked(int typeMarked) {
		this.typeMarked = typeMarked;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
