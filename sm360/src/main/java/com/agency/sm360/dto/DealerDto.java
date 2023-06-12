package com.agency.sm360.dto;

public class DealerDto extends GenericDto {

	public DealerDto(String id) {
		super(id);
	}

	public DealerDto() {
	}

	private static final long serialVersionUID = -3693871337086509308L;

	private String name;

	private TierLimitDto tierLimit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TierLimitDto getTierLimit() {
		return tierLimit;
	}

	public void setTierLimit(TierLimitDto tierLimit) {
		this.tierLimit = tierLimit;
	}

	@Override
	public String toString() {
		return "DealerDto [name=" + name + ", tierLimit=" + tierLimit + ", getName()=" + getName() + ", getTierLimit()="
				+ getTierLimit() + ", getId()=" + getId() + ", getCreatedAt()=" + getCreatedAt() + "]";
	}

}
