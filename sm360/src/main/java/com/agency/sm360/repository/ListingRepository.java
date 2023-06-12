package com.agency.sm360.repository;

import java.util.List;

import com.agency.sm360.entity.Listing;
import com.agency.sm360.entity.ListingStatus;

public interface ListingRepository  {

	List<Listing> findByDealer(String dealerId, ListingStatus listingStatus);

	Listing findById(String id);

	void update(Listing entity);

	void delete(String id);

	Listing add(Listing entity);

}
