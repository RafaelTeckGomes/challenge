package com.agency.sm360.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.agency.sm360.dto.DealerDto;
import com.agency.sm360.dto.GenericDto;
import com.agency.sm360.dto.TierLimitDto;
import com.agency.sm360.entity.Dealer;
import com.agency.sm360.entity.GenericEntity;
import com.agency.sm360.entity.TierLimit;

@Component
public class DealerMapper implements IMapper {

	@Override
	@SuppressWarnings("unchecked")
	public <T> GenericDto convertToDto(T entity) {
		if (entity == null) {
			return null;
		}
		entity = entity instanceof Optional ? ((Optional<T>) entity).get() : entity;
		DealerDto dto = new DealerDto();
		dto.setId(((Dealer) entity).getId());
		dto.setName(((Dealer) entity).getName());
		dto.setCreatedAt(((Dealer) entity).getCreatedAt());
		if (((Dealer) entity).getTierLimit() != null) {
			TierLimitMapper tierLimitMapper = new TierLimitMapper();
			TierLimitDto tierLimitDto = (TierLimitDto) tierLimitMapper.convertToDto(((Dealer) entity).getTierLimit());
			dto.setTierLimit(tierLimitDto);
		}
		return dto;
	}

	@Override
	public <T> GenericEntity convertToEntity(T dto) {
		Dealer entity = new Dealer();
		entity.setId(((DealerDto) dto).getId());
		entity.setName(((DealerDto) dto).getName());
		if (((DealerDto) dto).getTierLimit() != null) {
			TierLimitMapper tierLimitMapper = new TierLimitMapper();
			TierLimit tierLimit = (TierLimit) tierLimitMapper.convertToEntity(((DealerDto) dto).getTierLimit());
			entity.setTierLimit(tierLimit);
		}
		return entity;
	}

	@Override
	public <T> List<GenericDto> convertToDtoList(List<T> entity) {
		List<GenericDto> list = entity.stream().map(e -> this.convertToDto((Dealer) e)).collect(Collectors.toList());
		return list;
	}

	@Override
	public <T> List<GenericEntity> convertToEntityList(List<T> dto) {
		List<GenericEntity> list = dto.stream().map(e -> this.convertToEntity((DealerDto) e))
				.collect(Collectors.toList());
		return list;
	}

}
