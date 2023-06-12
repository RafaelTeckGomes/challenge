package com.agency.sm360.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
public abstract class GenericEntity implements Serializable {

	private static final long serialVersionUID = -5991511952236401031L;

	@Id
	@Column(updatable = false)
	private String id;

	@Column(name = "created_at", updatable = false)
	public Date createdAt;

	@PrePersist
	void createdAt() {
		this.id = UUID.randomUUID().toString();
		this.createdAt = new java.sql.Date(new java.util.Date().getTime());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public abstract String toString();

}
