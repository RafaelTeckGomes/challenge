package com.agency.sm360.domain.rule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agency.sm360.domain.exception.TierLimitExceededException;
import com.agency.sm360.dto.DealerDto;
import com.agency.sm360.dto.ListingDto;
import com.agency.sm360.mapper.DealerMapper;
import com.agency.sm360.repository.DealerRepository;

@Component
public class TierLimitExceeededRule {

	private static final Logger logger = LoggerFactory.getLogger(TierLimitExceeededRule.class);

	@Autowired
	private DealerRepository dealerRepository;

	@Autowired
	private DealerMapper dealerMapper;

	private final String TIER_LIMIT_EXCEEDED = "Your tier limit has been exceeded! Acquire a new tier for publish this listing.";

	public void executeRule(String dealerId, List<ListingDto> listingListDto) {
		logger.debug("TierLimitExceeededRule executeRule dealerId:{} , size:{}", dealerId, listingListDto.size());
		DealerDto dealerDto = (DealerDto) dealerMapper.convertToDto(dealerRepository.findById(dealerId));

		if (listingListDto.stream().count() >= ((DealerDto) dealerDto).getTierLimit().getValue()) {
			logger.debug("TierLimitExceeededRule executeRule TierLimitExceededException  - dealerId:{}", dealerId);
			throw new TierLimitExceededException(TIER_LIMIT_EXCEEDED);
		}

	}

}
