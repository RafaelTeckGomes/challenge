package com.agency.sm360.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.agency.sm360.dto.ListingDto;
import com.agency.sm360.entity.ListingStatus;
import com.agency.sm360.service.ListingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ListingController.class)
class ListingControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	ListingService listingService;

	@Test
	public void getAllListingByDealerTest() throws Exception {
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

		ListingDto dto3 = new ListingDto();
		dto3.setId("df6affee-07c0-11ee-be56-0242ac120002");
		dto3.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		dto3.setPrice(17000.00);
		dto3.setStatus(ListingStatus.DRAFT.name());
		dto3.setVehicle("AUDI A4 2019");
		dto3.setDealerId("05a2426c-05c8-11ee-be56-0242ac120002");
		dtoList.add(dto3);

		Mockito.when(listingService.findAllListingByDealer("05a2426c-05c8-11ee-be56-0242ac120002",
				ListingStatus.DRAFT.name())).thenReturn(dtoList);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/listing/dealer/05a2426c-05c8-11ee-be56-0242ac120002/status/DRAFT")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].id", is("05a2426c-05c8-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$[0].createdAt", is("2023-06-07T00:00:00.000+00:00")))
				.andExpect(jsonPath("$[0].price", is(15000.00)))
				.andExpect(jsonPath("$[0].vehicle", is("HONDA CIVIC 2021")))
				.andExpect(jsonPath("$[1].dealerId", is("05a2426c-05c8-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$[0].status", is(ListingStatus.DRAFT.name())))
				.andExpect(jsonPath("$[1].id", is("6f735356-05ce-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$[1].createdAt", is("2023-06-07T00:00:00.000+00:00")))
				.andExpect(jsonPath("$[1].price", is(8000.00)))
				.andExpect(jsonPath("$[1].vehicle", is("TOYOTA COROLA 2008")))
				.andExpect(jsonPath("$[1].dealerId", is("05a2426c-05c8-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$[1].status", is(ListingStatus.DRAFT.name())))
				.andExpect(jsonPath("$[2].id", is("df6affee-07c0-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$[2].createdAt", is("2023-06-07T00:00:00.000+00:00")))
				.andExpect(jsonPath("$[2].price", is(17000.00)))
				.andExpect(jsonPath("$[2].vehicle", is("AUDI A4 2019")))
				.andExpect(jsonPath("$[2].dealerId", is("05a2426c-05c8-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$[2].status", is(ListingStatus.DRAFT.name())));
	}
	
	@Test
	public void getListingTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));

		ListingDto dto1 = new ListingDto();
		dto1.setId("05a2426c-05c8-11ee-be56-0242ac120002");
		dto1.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		dto1.setPrice(15000.00);
		dto1.setStatus(ListingStatus.DRAFT.name());
		dto1.setVehicle("HONDA CIVIC 2021");
		dto1.setDealerId("05a2426c-05c8-11ee-be56-0242ac120002");


		Mockito.when(listingService.findById("05a2426c-05c8-11ee-be56-0242ac120002")).thenReturn(dto1);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/listing/05a2426c-05c8-11ee-be56-0242ac120002")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.id", is("05a2426c-05c8-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$.createdAt", is("2023-06-07T00:00:00.000+00:00")))
				.andExpect(jsonPath("$.price", is(15000.00)))
				.andExpect(jsonPath("$.vehicle", is("HONDA CIVIC 2021")))
				.andExpect(jsonPath("$.dealerId", is("05a2426c-05c8-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$.status", is(ListingStatus.DRAFT.name())));
	}
	
	@Test
	public void createListingTest() throws Exception {
		ListingDto dto1 = new ListingDto();
		dto1.setId("05a2426c-05c8-11ee-be56-0242ac120002");
		dto1.setCreatedAt(new Date());
		dto1.setPrice(15000.00);
		dto1.setStatus(ListingStatus.DRAFT.name());
		dto1.setVehicle("HONDA CIVIC 2021");
		dto1.setDealerId("05a2426c-05c8-11ee-be56-0242ac120002");

		Mockito.when(listingService.save(Mockito.any(ListingDto.class))).thenReturn(dto1);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/listing").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsBytes(dto1)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(jsonPath("$.id", is("05a2426c-05c8-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$.createdAt", notNullValue()))
				.andExpect(jsonPath("$.price", is(15000.00)))
				.andExpect(jsonPath("$.vehicle", is("HONDA CIVIC 2021")))
				.andExpect(jsonPath("$.dealerId", is("05a2426c-05c8-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$.status", is(ListingStatus.DRAFT.name())));

	}
	
	@Test
	public void deleteListingTest() throws Exception {
		Mockito.doNothing().when(listingService).delete("05a2426c-05c8-11ee-be56-0242ac120002");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.delete("/api/listing/05a2426c-05c8-11ee-be56-0242ac120002")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8");

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@Test
	public void updateListingTest() throws Exception {
		ListingDto dto1 = new ListingDto();
		dto1.setId("05a2426c-05c8-11ee-be56-0242ac120002");
		dto1.setCreatedAt(new Date());
		dto1.setPrice(17000.00);
		dto1.setStatus(ListingStatus.DRAFT.name());
		dto1.setVehicle("HONDA CIVIC 2021");
		dto1.setDealerId("05a2426c-05c8-11ee-be56-0242ac120002");

		Mockito.doNothing().when(listingService).update(dto1);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.patch("/api/listing")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(dto1));

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	
	@Test
	public void publishTest() throws Exception {
		ListingDto dto1 = new ListingDto();
		dto1.setId("05a2426c-05c8-11ee-be56-0242ac120002");
		dto1.setStatus(ListingStatus.PUBLISHED.name());
		dto1.setDealerId("05a2426c-05c8-11ee-be56-0242ac120002");
		Mockito.doNothing().when(listingService).publish("5a2426c-05c8-11ee-be56-0242ac120002",
				"05a2426c-05c8-11ee-be56-0242ac120002");

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.patch(
				"/api/listing/05a2426c-05c8-11ee-be56-0242ac120002/dealer/05a2426c-05c8-11ee-be56-0242ac120002/publish")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(dto1));

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	
	@Test
	public void unpublishTest() throws Exception {
		ListingDto dto1 = new ListingDto();
		dto1.setId("05a2426c-05c8-11ee-be56-0242ac120002");
		dto1.setStatus(ListingStatus.DRAFT.name());
		dto1.setDealerId("05a2426c-05c8-11ee-be56-0242ac120002");
		Mockito.doNothing().when(listingService).publish("5a2426c-05c8-11ee-be56-0242ac120002",
				"05a2426c-05c8-11ee-be56-0242ac120002");

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.patch(
				"/api/listing/05a2426c-05c8-11ee-be56-0242ac120002/dealer/05a2426c-05c8-11ee-be56-0242ac120002/publish")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(dto1));

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
		
	}


}
