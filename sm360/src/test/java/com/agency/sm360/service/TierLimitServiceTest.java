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

import com.agency.sm360.dto.TierLimitDto;
import com.agency.sm360.entity.TierType;

@ExtendWith(MockitoExtension.class)
public class TierLimitServiceTest {

	@Mock
	TierLimitService tierLimitService;

	@Test
	public void getTierLimitTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		TierLimitDto dto1 = new TierLimitDto();
		dto1.setId("e4dc06a8-05c7-11ee-be56-0242ac120002");
		dto1.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		dto1.setType(TierType.GOLD.name());
		dto1.setValue(12L);

		Mockito.when(tierLimitService.findById("05a2426c-05c8-11ee-be56-0242ac120002")).thenReturn(dto1);
		TierLimitDto dtoResult = tierLimitService.findById("05a2426c-05c8-11ee-be56-0242ac120002");

		assertNotNull(dtoResult);
		assertEquals(dtoResult.getId(), "e4dc06a8-05c7-11ee-be56-0242ac120002");
		assertEquals(dtoResult.getCreatedAt(), sf.parse("2023-06-07T00:00:00.000+00:00"));
		assertEquals(dtoResult.getType(), TierType.GOLD.name());
		assertEquals(dtoResult.getValue(), 12L);

	}

	@Test
	public void updateTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		TierLimitDto dto1 = new TierLimitDto();
		dto1.setId("73b37474-0627-11ee-be56-0242ac120002");
		dto1.setCreatedAt(sf.parse("2023-06-09T00:00:00.000+00:00"));
		dto1.setType(TierType.PLATINUM.name());
		dto1.setValue(50L);
		Mockito.doNothing().when(tierLimitService).update(dto1);

		tierLimitService.update(dto1);
		verify(tierLimitService, times(1)).update(dto1);
		;

	}

}
