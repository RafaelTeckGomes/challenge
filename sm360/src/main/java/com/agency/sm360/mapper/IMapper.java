package com.agency.sm360.mapper;

import java.util.List;

import com.agency.sm360.dto.GenericDto;
import com.agency.sm360.entity.GenericEntity;

public interface IMapper {

	public <T> GenericDto convertToDto(T entity);

	public <T> GenericEntity convertToEntity(T dto);

	public <T> List<GenericDto> convertToDtoList(List<T> entity);

	public <T> List<GenericEntity> convertToEntityList(List<T> dto);

}
