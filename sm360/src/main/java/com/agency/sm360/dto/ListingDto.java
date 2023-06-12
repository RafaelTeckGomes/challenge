package com.agency.sm360.dto;

public class ListingDto extends GenericDto {

	public ListingDto(String id) {
		super(id);
	}

	public ListingDto() {
	}

	private static final long serialVersionUID = 1868983732337830922L;

	private String dealerId;

	private String status;

	private String vehicle;

	private Double price;

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ListingDto [dealerId=" + dealerId + ", status=" + status + ", vehicle=" + vehicle + ", price=" + price
				+ ", getDealerId()=" + getDealerId() + ", getStatus()=" + getStatus() + ", getVehicle()=" + getVehicle()
				+ ", getPrice()=" + getPrice() + ", getId()=" + getId() + ", getCreatedAt()=" + getCreatedAt() + "]";
	}

}
