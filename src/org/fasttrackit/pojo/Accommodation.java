package org.fasttrackit.pojo;

public class Accommodation {
	
	private int id;
	private String name;
	private String type;
	private double price;
	private String contact;
	private String details;
	public Accommodation(int id, String name, String type, double price, String contact, String details) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.contact = contact;
		this.details = details;
	}
	
	public Accommodation() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
