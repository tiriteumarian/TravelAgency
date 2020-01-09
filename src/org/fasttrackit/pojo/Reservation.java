package org.fasttrackit.pojo;

public class Reservation {
	
	private int id;
	private int clientId;
	private int destinationId;
	private int transportationId;
	private int accommodationId;
	public Reservation(int id, int clientId, int destinationId, int transportationId, int accommodationId) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.destinationId = destinationId;
		this.transportationId = transportationId;
		this.accommodationId = accommodationId;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Reservation() {
		super();
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}
	public int getTransportationId() {
		return transportationId;
	}
	public void setTransportationId(int transportationId) {
		this.transportationId = transportationId;
	}
	public int getAccommodationId() {
		return accommodationId;
	}
	public void setAccommodationId(int accommodationId) {
		this.accommodationId = accommodationId;
	}
	
	

}
