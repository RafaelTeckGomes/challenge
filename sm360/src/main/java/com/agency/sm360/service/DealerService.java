package com.agency.sm360.service;

import java.util.List;

import com.agency.sm360.dto.DealerDto;

public interface DealerService {

	public DealerDto save(DealerDto dto);

	public void delete(String id);

	public void update(DealerDto dto);

	public DealerDto findById(String id);

	public List<DealerDto> findAll();

}
