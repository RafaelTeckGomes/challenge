package com.agency.sm360.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dealer")
public class Dealer extends GenericEntity {

	private static final long serialVersionUID = -7994477930297725544L;

	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tier_limit_id")
	private TierLimit tierLimit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TierLimit getTierLimit() {
		return tierLimit;
	}

	public void setTierLimit(TierLimit tierLimit) {
		this.tierLimit = tierLimit;
	}

	@Override
	public String toString() {
		return "Dealer [name=" + name + ", tierLimit=" + tierLimit + ", getId()=" + getId() + ", getCreatedAt()="
				+ getCreatedAt() + "]";
	}

}
