package com.agency.sm360.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "tier_limit")
public class TierLimit extends GenericEntity {


	private static final long serialVersionUID = -2059913562666510189L;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private TierType type;

	@Column(nullable = false)
	private Long typeLimit;

	public TierType getType() {
		return type;
	}

	public void setType(TierType type) {
		this.type = type;
	}

	public Long getTypeLimit() {
		return typeLimit;
	}

	public void setTypeLimit(Long typeLimit) {
		this.typeLimit = typeLimit;
	}

	@Override
	public String toString() {
		return "TierLimit [type=" + type + ", typeLimit=" + typeLimit + ", getType()=" + getType() + ", getTypeLimit()="
				+ getTypeLimit() + ", getId()=" + getId() + ", getCreatedAt()=" + getCreatedAt() + "]";
	}



}
