package com.agency.sm360.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.agency.sm360.dto.GenericDto;
import com.agency.sm360.dto.ListingDto;
import com.agency.sm360.entity.Dealer;
import com.agency.sm360.entity.GenericEntity;
import com.agency.sm360.entity.Listing;
import com.agency.sm360.entity.ListingStatus;

@Component
public class ListingMapper implements IMapper {

	@Override
	@SuppressWarnings("unchecked")
	public <T> GenericDto convertToDto(T entity) {
		if (entity == null) {
			return null;
		}
		entity = entity instanceof Optional ? ((Optional<T>) entity).get() : entity;
		ListingDto dto = new ListingDto();
		dto.setId(((Listing) entity).getId());
		dto.setDealerId(((Listing) entity).getDealer().getId());
		dto.setStatus(((Listing) entity).getState().name());
		dto.setVehicle(((Listing) entity).getVehicle());
		dto.setPrice(((Listing) entity).getPrice());
		dto.setCreatedAt(((Listing) entity).getCreatedAt());
		return dto;
	}

	@Override
	public <T> GenericEntity convertToEntity(T dto) {
		Listing entity = new Listing();
		entity.setId(((ListingDto) dto).getId());
		Dealer dealer = new Dealer();
		dealer.setId(((ListingDto) dto).getDealerId());
		entity.setDealer(dealer);
		entity.setStatus(ListingStatus.getStatus(((ListingDto) dto).getStatus()));
		entity.setVehicle(((ListingDto) dto).getVehicle());
		entity.setPrice(((ListingDto) dto).getPrice());
		return entity;
	}

	@Override
	public <T> List<GenericDto> convertToDtoList(List<T> entity) {
		List<GenericDto> list = entity.stream().map(e -> this.convertToDto((Listing) e)).collect(Collectors.toList());
		return list;
	}

	@Override
	public <T> List<GenericEntity> convertToEntityList(List<T> dto) {
		List<GenericEntity> list = dto.stream().map(e -> this.convertToEntity((ListingDto) e))
				.collect(Collectors.toList());
		return list;
	}

}
