package com.agency.sm360.dto;

public class TierLimitDto extends GenericDto {

	public TierLimitDto(String id) {
		super(id);
	}

	public TierLimitDto() {

	}

	private static final long serialVersionUID = -5858821941106326178L;

	private String type;

	private Long value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TierLimitDto [type=" + type + ", value=" + value + ", getType()=" + getType() + ", getValue()="
				+ getValue() + ", getId()=" + getId() + ", getCreatedAt()=" + getCreatedAt() + "]";
	}

}
