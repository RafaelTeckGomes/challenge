package com.agency.sm360.dto;

import java.io.Serializable;
import java.util.Date;

public abstract class GenericDto implements Serializable {

	public GenericDto(String id) {
		this.id = id;
	}
	public GenericDto() {
	}

	private static final long serialVersionUID = -3432265659453192664L;

	private String id;

	public Date createdAt;

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
