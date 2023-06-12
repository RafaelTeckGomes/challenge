package com.agency.sm360.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agency.sm360.domain.exception.ListingNotFoundException;
import com.agency.sm360.domain.exception.TierLimitExceededException;
import com.agency.sm360.domain.exception.TierLimitNotFoundException;
import com.agency.sm360.domain.rule.TierLimitExceeededRule;
import com.agency.sm360.dto.GenericDto;
import com.agency.sm360.dto.ListingDto;
import com.agency.sm360.entity.Listing;
import com.agency.sm360.entity.ListingStatus;
import com.agency.sm360.mapper.ListingMapper;
import com.agency.sm360.repository.ListingRepository;

@Service
@Transactional
public class ListingServiceImpl implements ListingService {

	private static final Logger logger = LoggerFactory.getLogger(ListingServiceImpl.class);

	@Autowired
	private ListingRepository listingRepository;

	@Autowired
	private ListingMapper listingMapper;

	@Autowired
	private TierLimitExceeededRule tierLimitExceeededRule;

	@Override
	public ListingDto findById(String id) {
		logger.debug("ListingServiceImpl save id:{}", id);
		ListingDto dto = (ListingDto) listingMapper.convertToDto(listingRepository.findById(id));
		if (dto == null) {
			throw new ListingNotFoundException();
		}
		return dto;
	}

	@Override
	public ListingDto save(ListingDto dto) {
		logger.debug("ListingServiceImpl save dto:{}", dto);
		return (ListingDto) listingMapper
				.convertToDto(listingRepository.add((Listing) listingMapper.convertToEntity(dto)));
	}

	@Override
	public void delete(String id) {
		logger.debug("ListingServiceImpl delete id:{}", id);
		listingRepository.delete(id);
	}

	@Override
	public void publish(String listingId, String dealerId)
			throws TierLimitNotFoundException, TierLimitExceededException {
		logger.debug("ListingServiceImpl publish listingId:{} , dealerId:{}", listingId, dealerId);
		tierLimitExceeededRule.executeRule(dealerId,
				this.findAllListingByDealer(dealerId, ListingStatus.PUBLISHED.name()));

		ListingDto dto = (ListingDto) this.findById(listingId);
		dto.setStatus(ListingStatus.PUBLISHED.name());
		listingRepository.update((Listing) listingMapper.convertToEntity(dto));
	}

	@Override
	public void unPublish(String listingId) throws TierLimitNotFoundException {
		logger.debug("ListingServiceImpl unPublish listingId:{}", listingId);
		ListingDto dto = (ListingDto) this.findById(listingId);
		dto.setStatus(ListingStatus.DRAFT.name());
		listingRepository.update((Listing) listingMapper.convertToEntity(dto));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ListingDto> findAllListingByDealer(String dealerId, String status) {
		List<Listing> entityList = listingRepository.findByDealer(dealerId, ListingStatus.getStatus(status));
		List<? extends GenericDto> dtoList = listingMapper.convertToDtoList(entityList);
		logger.debug("ListingServiceImpl findAllListingByDealer dealerId:{} , status:{}", dealerId, status);
		return (List<ListingDto>) dtoList;
	}

	@Override
	public void update(ListingDto dto) {
		logger.debug("ListingServiceImpl update dto:{}", dto);
		validateId(dto.getId());
		listingRepository.update((Listing) listingMapper.convertToEntity(dto));
	}

	private void validateId(String id) throws ListingNotFoundException {
		findById(id);
	}

}
