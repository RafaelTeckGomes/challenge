package com.agency.sm360.entity;

import com.agency.sm360.domain.exception.TierTypeNotFoundException;

public enum TierType {

	SILVER, GOLD, PLATINUM;

	public static TierType getType(String type) {
		try {
			return type != null ? TierType.valueOf(type.toUpperCase()) : null;
		} catch (IllegalArgumentException ex) {
			throw new TierTypeNotFoundException();
		}
	}

}
