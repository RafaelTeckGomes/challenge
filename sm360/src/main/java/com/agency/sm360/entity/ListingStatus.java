package com.agency.sm360.entity;

import com.agency.sm360.domain.exception.ListingStatusNotFoundException;

public enum ListingStatus {

	DRAFT, PUBLISHED;

	public static ListingStatus getStatus(String status) {
		try {
			return status != null ? ListingStatus.valueOf(status.toUpperCase()) : null;
		} catch (IllegalArgumentException ex) {
			throw new ListingStatusNotFoundException();
		}
	}

}
