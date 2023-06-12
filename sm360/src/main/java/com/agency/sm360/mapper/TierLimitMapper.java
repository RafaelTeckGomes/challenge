package com.agency.sm360.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.agency.sm360.dto.GenericDto;
import com.agency.sm360.dto.TierLimitDto;
import com.agency.sm360.entity.GenericEntity;
import com.agency.sm360.entity.TierLimit;
import com.agency.sm360.entity.TierType;

@Component
public class TierLimitMapper implements IMapper {

	@Override
	@SuppressWarnings("unchecked")
	public <T> GenericDto convertToDto(T entity) {
		if (entity == null) {
			return null;
		}
		entity = entity instanceof Optional ? ((Optional<T>) entity).get() : entity;
		TierLimitDto dto = new TierLimitDto();
		dto.setId(((TierLimit) entity).getId());
		dto.setValue(((TierLimit) entity).getTypeLimit());
		dto.setType(((TierLimit) entity).getType().name());
		dto.setCreatedAt(((TierLimit) entity).getCreatedAt());
		return dto;
	}

	@Override
	public <T> GenericEntity convertToEntity(T dto) {
		TierLimit entity = new TierLimit();
		entity.setId(((TierLimitDto) dto).getId());
		entity.setTypeLimit(((TierLimitDto) dto).getValue());
		entity.setType(TierType.getType(((TierLimitDto) dto).getType()));
		return entity;
	}

	@Override
	public <T> List<GenericDto> convertToDtoList(List<T> entity) {
		List<GenericDto> list = entity.stream().map(e -> this.convertToDto((TierLimit) e)).collect(Collectors.toList());
		return list;
	}

	@Override
	public <T> List<GenericEntity> convertToEntityList(List<T> dto) {
		List<GenericEntity> list = dto.stream().map(e -> this.convertToEntity((TierLimitDto) e))
				.collect(Collectors.toList());
		return list;
	}

}
