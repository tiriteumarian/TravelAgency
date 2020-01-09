package org.fasttrackit.pojo;

public class Destination {
	
	private int id;
	private String country;
	private String region;
	private String city;
	private String description;
	private String imagePath;
	
	public Destination(int id, String country, String region, String city, String description, String imagePath) {
		super();
		this.id = id;
		this.country = country;
		this.region = region;
		this.city = city;
		this.description = description;
		this.setImagePath(imagePath);
	}
	
	public int getId() {
		return id;
		}
	public void setId(int id) {
		this.id = id;
	}
	public Destination() {
		super();
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getAllDestination() {
		return this.country + " - " + this.city;
	
	}
}
