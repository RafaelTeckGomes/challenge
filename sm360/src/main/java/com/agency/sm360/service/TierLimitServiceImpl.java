package com.agency.sm360.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agency.sm360.domain.exception.TierLimitNotFoundException;
import com.agency.sm360.dto.GenericDto;
import com.agency.sm360.dto.TierLimitDto;
import com.agency.sm360.entity.TierLimit;
import com.agency.sm360.mapper.TierLimitMapper;
import com.agency.sm360.repository.TierLimitRepository;

@Service
@Transactional
public class TierLimitServiceImpl implements TierLimitService {

	private static final Logger logger = LoggerFactory.getLogger(TierLimitServiceImpl.class);

	@Autowired
	private TierLimitRepository tierLimitRepository;

	@Autowired
	private TierLimitMapper tierLimitMapper;

	@Override
	public void update(TierLimitDto dto) {
		logger.debug("TierLimitServiceImpl update dto:{}", dto);
		validateId(dto.getId());
		tierLimitRepository.save((TierLimit) tierLimitMapper.convertToEntity(dto));

	}

	@Override
	public TierLimitDto findById(String id) {
		logger.debug("TierLimitServiceImpl findById id:{}", id);
		return (TierLimitDto) tierLimitMapper
				.convertToDto(tierLimitRepository.findById(id).orElseThrow(() -> new TierLimitNotFoundException(id)));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TierLimitDto> findAll() {
		List<TierLimit> entityList = (List<TierLimit>) tierLimitRepository.findAll();
		List<? extends GenericDto> dtoList = tierLimitMapper.convertToDtoList(entityList);
		logger.debug("TierLimitServiceImpl findAll  size:{}", dtoList.size());
		return (List<TierLimitDto>) dtoList;
	}

	private void validateId(String id) throws TierLimitNotFoundException {
		findById(id);
	}

}
