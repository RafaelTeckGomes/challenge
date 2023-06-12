package com.agency.sm360.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "listing")
public class Listing extends GenericEntity {

	private static final long serialVersionUID = -8916515435208205832L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dealer_id")
	private Dealer dealer;

	@Enumerated(EnumType.ORDINAL)
	private ListingStatus status;

	@Column(nullable = false)
	private String vehicle;

	@Column(nullable = false)
	private Double price;

	@PrePersist
	void initialState() {
		this.status = ListingStatus.DRAFT;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
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

	public ListingStatus getState() {
		return status;
	}

	public void setStatus(ListingStatus status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Listing [dealer=" + dealer + ", status=" + status + ", vehicle=" + vehicle + ", price=" + price
				+ ", getDealer()=" + getDealer() + ", getVehicle()=" + getVehicle() + ", getPrice()=" + getPrice()
				+ ", getState()=" + getState() + ", getCreatedAt()=" + getCreatedAt() + "]";
	}

}
