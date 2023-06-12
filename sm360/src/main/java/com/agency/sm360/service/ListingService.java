package com.agency.sm360.service;

import java.util.List;

import com.agency.sm360.domain.exception.TierLimitNotFoundException;
import com.agency.sm360.dto.ListingDto;

public interface ListingService {

	public ListingDto findById(String id);

	public List<ListingDto> findAllListingByDealer(String dealerId, String status);

	public ListingDto save(ListingDto dto);

	public void delete(String id);
	
	public void update(ListingDto dto);

	public void publish(String listingId, String dealerId) throws TierLimitNotFoundException;

	public void unPublish(String listingId) throws TierLimitNotFoundException;

}
