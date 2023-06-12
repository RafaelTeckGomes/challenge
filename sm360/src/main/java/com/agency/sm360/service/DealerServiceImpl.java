package com.agency.sm360.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agency.sm360.domain.exception.DealerNotFoundException;
import com.agency.sm360.dto.DealerDto;
import com.agency.sm360.dto.GenericDto;
import com.agency.sm360.entity.Dealer;
import com.agency.sm360.mapper.DealerMapper;
import com.agency.sm360.repository.DealerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class DealerServiceImpl implements DealerService {

	private static final Logger logger = LoggerFactory.getLogger(DealerServiceImpl.class);

	@Autowired
	private DealerRepository dealerRepository;

	@Autowired
	private DealerMapper dealerMapper;

	@Override
	public DealerDto save(DealerDto dto) {
		logger.debug("DealerServiceImpl save dto:{}",dto);
		return (DealerDto) dealerMapper.convertToDto(dealerRepository.save((Dealer) dealerMapper.convertToEntity(dto)));
	}

	@Override
	public void delete(String id) {
		logger.debug("DealerServiceImpl delete id:{}",id);
		dealerRepository.delete((Dealer) dealerMapper.convertToEntity(new DealerDto(id)));
	}

	@Override
	public void update(DealerDto dto) {
		logger.debug("DealerServiceImpl update dto:{}",dto);
		validateId(dto.getId());
		dealerRepository.save((Dealer) dealerMapper.convertToEntity(dto));
	}

	@Override
	public DealerDto findById(String id) {
		logger.debug("DealerServiceImpl findById id:{}",id);
		return (DealerDto) dealerMapper.convertToDto(
				dealerRepository.findById(id).orElseThrow(() -> new DealerNotFoundException("DEALER NOT FOUND")));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DealerDto> findAll() {
		List<Dealer> entityList = (List<Dealer>) dealerRepository.findAll();
		List<? extends GenericDto> dtoList = dealerMapper.convertToDtoList(entityList);
		logger.debug("DealerServiceImpl findAll size:{}",dtoList.size());
		return (List<DealerDto>) dtoList;
	}

	private void validateId(String id) {
		logger.debug("DealerServiceImpl validateId id:{}",id);
		findById(id);
	}

}
