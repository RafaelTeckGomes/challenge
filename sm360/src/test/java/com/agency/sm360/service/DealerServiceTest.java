package com.agency.sm360.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agency.sm360.dto.DealerDto;
import com.agency.sm360.dto.TierLimitDto;
import com.agency.sm360.entity.TierType;

@ExtendWith(MockitoExtension.class)
public class DealerServiceTest {

	@Mock
	DealerService dealerService;

	@Test
	public void createDealerTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		DealerDto dto1 = new DealerDto();
		dto1.setId("05a2426c-05c8-11ee-be56-0242ac120002");
		dto1.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		dto1.setName("DEALER1");
		TierLimitDto tierLimit = new TierLimitDto();
		tierLimit.setId("e4dc06a8-05c7-11ee-be56-0242ac120002");
		tierLimit.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		tierLimit.setType(TierType.SILVER.name());
		tierLimit.setValue(2L);
		dto1.setTierLimit(tierLimit);

		Mockito.when(dealerService.save(dto1)).thenReturn(dto1);
		DealerDto dtoResult = dealerService.save(dto1);

		assertNotNull(dtoResult);
		assertEquals(dtoResult.getId(), "05a2426c-05c8-11ee-be56-0242ac120002");
		assertEquals(dtoResult.getCreatedAt(), sf.parse("2023-06-07T00:00:00.000+00:00"));
		assertEquals(dtoResult.getTierLimit().getType(), TierType.SILVER.name());
		assertEquals(dtoResult.getTierLimit().getValue(), 2L);

	}

	@Test
	public void getDealerTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		DealerDto dto1 = new DealerDto();
		dto1.setId("05a2426c-05c8-11ee-be56-0242ac120002");
		dto1.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		dto1.setName("DEALER1");
		TierLimitDto tierLimit = new TierLimitDto();
		tierLimit.setId("e4dc06a8-05c7-11ee-be56-0242ac120002");
		tierLimit.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		tierLimit.setType(TierType.GOLD.name());
		tierLimit.setValue(2L);
		dto1.setTierLimit(tierLimit);

		Mockito.when(dealerService.findById("05a2426c-05c8-11ee-be56-0242ac120002")).thenReturn(dto1);
		DealerDto dtoResult = dealerService.findById("05a2426c-05c8-11ee-be56-0242ac120002");

		assertNotNull(dtoResult);
		assertEquals(dtoResult.getId(), "05a2426c-05c8-11ee-be56-0242ac120002");
		assertEquals(dtoResult.getCreatedAt(), sf.parse("2023-06-07T00:00:00.000+00:00"));
		assertEquals(dtoResult.getTierLimit().getType(), TierType.GOLD.name());
		assertEquals(dtoResult.getTierLimit().getValue(), 2L);

	}

	@Test
	public void deleteTest() throws Exception {
		Mockito.doNothing().when(dealerService).delete("05a2426c-05c8-11ee-be56-0242ac120002");
		dealerService.delete("05a2426c-05c8-11ee-be56-0242ac120002");
		verify(dealerService, times(1)).delete("05a2426c-05c8-11ee-be56-0242ac120002");
	}

	@Test
	public void updateTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		DealerDto dto1 = new DealerDto();
		dto1.setId("05a2426c-05c8-11ee-be56-0242ac120002");
		dto1.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		dto1.setName("NEW DEALER");
		TierLimitDto tierLimit = new TierLimitDto();
		tierLimit.setId("e4dc06a8-05c7-11ee-be56-0242ac120002");
		tierLimit.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		tierLimit.setType(TierType.PLATINUM.name());
		tierLimit.setValue(50L);
		dto1.setTierLimit(tierLimit);

		Mockito.doNothing().when(dealerService).update(dto1);

		dealerService.update(dto1);
		verify(dealerService, times(1)).update(dto1);
		;

	}

}
