package com.agency.sm360.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agency.sm360.domain.exception.TierLimitExceededException;
import com.agency.sm360.domain.rule.TierLimitExceeededRule;
import com.agency.sm360.dto.ListingDto;
import com.agency.sm360.entity.ListingStatus;

@ExtendWith(MockitoExtension.class)
public class ListingServiceTest {

	@Mock
	ListingService listingService;

	@Mock
	TierLimitExceeededRule tierLimitExceeededRule;

	@Test
	public void throwExceptionOnPublishTest() throws Exception {
		String idListing = "05a2426c-05c8-11ee-be56-0242ac120002";
		String idDealer = "05a2426c-05c8-11ee-be56-0242ac120002";
		Mockito.doThrow(new TierLimitExceededException()).when(listingService).publish(idListing, idDealer);
		assertThrows(TierLimitExceededException.class, () -> listingService.publish(idListing, idDealer));
	}

	@Test
	public void publishTest() throws Exception {
		String idListing = "05a2426c-05c8-11ee-be56-0242ac120002";
		String idDealer = "05a2426c-05c8-11ee-be56-0242ac120002";

		Mockito.doNothing().when(listingService).publish(idListing, idDealer);
		listingService.publish(idListing, idDealer);
		verify(listingService, times(1)).publish(idListing, idDealer);
	}

	@Test
	public void unPublishTest() throws Exception {
		String idListing = "05a2426c-05c8-11ee-be56-0242ac120002";
		Mockito.doNothing().when(listingService).unPublish(idListing);
		listingService.unPublish(idListing);
		verify(listingService, times(1)).unPublish(idListing);

	}

	@Test
	public void saveTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		ListingDto dtoMock = new ListingDto();
		dtoMock.setId("05a2426c-05c8-11ee-be56-0242ac120002");
		dtoMock.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		dtoMock.setPrice(15000.00);
		dtoMock.setStatus(ListingStatus.DRAFT.name());
		dtoMock.setVehicle("HONDA CIVIC 2021");
		dtoMock.setDealerId("05a2426c-05c8-11ee-be56-0242ac120002");

		ListingDto dtoMockInput = new ListingDto();
		dtoMockInput.setPrice(15000.00);
		dtoMock.setVehicle("HONDA CIVIC 2021");
		dtoMock.setDealerId("05a2426c-05c8-11ee-be56-0242ac120002");

		Mockito.when(listingService.save(dtoMockInput)).thenReturn(dtoMock);
		ListingDto dtoResult = listingService.save(dtoMockInput);

		assertNotNull(dtoResult);
		assertEquals(dtoResult.getPrice(), 15000.00);
		assertEquals(dtoResult.getStatus(), ListingStatus.DRAFT.name());
		assertEquals(dtoResult.getVehicle(), "HONDA CIVIC 2021");
		assertEquals(dtoResult.getDealerId(), "05a2426c-05c8-11ee-be56-0242ac120002");

	}
	
	@Test
	public void deleteTest() throws Exception {
		Mockito.doNothing().when(listingService).delete("05a2426c-05c8-11ee-be56-0242ac120002");
		listingService.delete("05a2426c-05c8-11ee-be56-0242ac120002");
		verify(listingService, times(1)).delete("05a2426c-05c8-11ee-be56-0242ac120002");
	}

	@Test
	public void updateTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		ListingDto dtoMock = new ListingDto();
		dtoMock.setId("05a2426c-05c8-11ee-be56-0242ac120002");
		dtoMock.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		dtoMock.setPrice(15000.00);
		dtoMock.setStatus(ListingStatus.DRAFT.name());
		dtoMock.setVehicle("HONDA CIVIC 2021");
		dtoMock.setDealerId("05a2426c-05c8-11ee-be56-0242ac120002");

		ListingDto dtoMockInput = new ListingDto();
		dtoMockInput.setPrice(15000.00);
		dtoMock.setVehicle("HONDA CIVIC 2021");
		dtoMock.setDealerId("05a2426c-05c8-11ee-be56-0242ac120002");

		Mockito.doNothing().when(listingService).update(dtoMockInput);

		listingService.update(dtoMockInput);
		verify(listingService, times(1)).update(dtoMockInput);
		;

	}

	public void findByIdTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		ListingDto dtoMock = new ListingDto();
		dtoMock.setId("05a2426c-05c8-11ee-be56-0242ac120002");
		dtoMock.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		dtoMock.setPrice(15000.00);
		dtoMock.setStatus(ListingStatus.DRAFT.name());
		dtoMock.setVehicle("HONDA CIVIC 2021");
		dtoMock.setDealerId("05a2426c-05c8-11ee-be56-0242ac120002");

		ListingDto dtoMockInput = new ListingDto();
		dtoMockInput.setPrice(15000.00);
		dtoMock.setVehicle("HONDA CIVIC 2021");
		dtoMock.setDealerId("05a2426c-05c8-11ee-be56-0242ac120002");

		Mockito.when(listingService.findById("05a2426c-05c8-11ee-be56-0242ac120002")).thenReturn(dtoMock);
		ListingDto dtoResult = listingService.findById("05a2426c-05c8-11ee-be56-0242ac120002");

		assertNotNull(dtoResult);
		assertEquals(dtoResult.getPrice(), 15000.00);
		assertEquals(dtoResult.getStatus(), ListingStatus.DRAFT.name());
		assertEquals(dtoResult.getVehicle(), "HONDA CIVIC 2021");
		assertEquals(dtoResult.getDealerId(), "05a2426c-05c8-11ee-be56-0242ac120002");
	}

	public void findAllListingByDealerTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		List<ListingDto> dtoList = new ArrayList<ListingDto>();

		ListingDto dto1 = new ListingDto();
		dto1.setId("05a2426c-05c8-11ee-be56-0242ac120002");
		dto1.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		dto1.setPrice(15000.00);
		dto1.setStatus(ListingStatus.DRAFT.name());
		dto1.setVehicle("HONDA CIVIC 2021");
		dto1.setDealerId("05a2426c-05c8-11ee-be56-0242ac120002");
		dtoList.add(dto1);

		ListingDto dto2 = new ListingDto();
		dto2.setId("6f735356-05ce-11ee-be56-0242ac120002");
		dto2.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		dto2.setPrice(8000.00);
		dto2.setStatus(ListingStatus.DRAFT.name());
		dto2.setVehicle("TOYOTA COROLA 2008");
		dto2.setDealerId("05a2426c-05c8-11ee-be56-0242ac120002");
		dtoList.add(dto2);

		Mockito.when(listingService.findAllListingByDealer("05a2426c-05c8-11ee-be56-0242ac120002",
				ListingStatus.DRAFT.name())).thenReturn(dtoList);
		List<ListingDto> dtoResultList = listingService.findAllListingByDealer("05a2426c-05c8-11ee-be56-0242ac120002",
				ListingStatus.DRAFT.name());
		
		assertNotNull(dtoResultList);
		assertEquals(dtoResultList.size(), 2);
		assertEquals(dtoResultList.get(0).getPrice(), 15000.00);
		assertEquals(dtoResultList.get(0).getStatus(), ListingStatus.DRAFT.name());
		assertEquals(dtoResultList.get(0).getVehicle(), "HONDA CIVIC 2021");
		assertEquals(dtoResultList.get(0).getDealerId(), "05a2426c-05c8-11ee-be56-0242ac120002");

		assertEquals(dtoResultList.get(1).getPrice(), 8000.00);
		assertEquals(dtoResultList.get(1).getStatus(), ListingStatus.DRAFT.name());
		assertEquals(dtoResultList.get(1).getVehicle(), "TOYOTA COROLA 2008");
		assertEquals(dtoResultList.get(1).getDealerId(), "05a2426c-05c8-11ee-be56-0242ac120002");

	}

}
