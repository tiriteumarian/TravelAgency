package org.fasttrackit.pojo;

public class Transportation {

	private int id;
	private String type;
	private String company;
	private String contact;
	private String details;
	private int fromDestinationId;
	private int toDestinationId;
	private double price;

	public Transportation(int id, String type, String company, String contact, String details, int fromDestinationId,
			int toDestinationId, double price) {
		super();
		this.id = id;
		this.type = type;
		this.company = company;
		this.contact = contact;
		this.details = details;
		this.fromDestinationId = fromDestinationId;
		this.toDestinationId = toDestinationId;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Transportation() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public int getFromDestinationId() {
		return fromDestinationId;
	}

	public void setFromDestinationId(int fromDestinationId) {
		this.fromDestinationId = fromDestinationId;
	}

	public int getToDestinationId() {
		return toDestinationId;
	}

	public void setToDestinationId(int toDestinationId) {
		this.toDestinationId = toDestinationId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
