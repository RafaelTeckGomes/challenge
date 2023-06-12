package com.agency.sm360.service;

import java.util.List;

import com.agency.sm360.dto.TierLimitDto;

public interface TierLimitService {

	public void update(TierLimitDto dto);

	public TierLimitDto findById(String id);
	
	public List<TierLimitDto> findAll();

}
