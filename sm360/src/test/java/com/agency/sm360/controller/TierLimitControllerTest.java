package com.agency.sm360.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.agency.sm360.dto.TierLimitDto;
import com.agency.sm360.entity.TierType;
import com.agency.sm360.service.TierLimitService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TierLimitController.class)
class TierLimitControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	TierLimitService tierLimitService;

	@Test
	public void getAllTimeLimitTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		List<TierLimitDto> dtoList = new ArrayList<TierLimitDto>();

		TierLimitDto dto1 = new TierLimitDto();
		dto1.setId("e4dc06a8-05c7-11ee-be56-0242ac120002");
		dto1.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		dto1.setType(TierType.SILVER.name());
		dto1.setValue(2L);
		dtoList.add(dto1);

		TierLimitDto dto2 = new TierLimitDto();
		dto2.setId("6d85ba9e-0627-11ee-be56-0242ac120002");
		dto2.setCreatedAt(sf.parse("2023-06-08T00:00:00.000+00:00"));
		dto2.setType(TierType.GOLD.name());
		dto2.setValue(10L);
		dtoList.add(dto2);

		TierLimitDto dto3 = new TierLimitDto();
		dto3.setId("73b37474-0627-11ee-be56-0242ac120002");
		dto3.setCreatedAt(sf.parse("2023-06-09T00:00:00.000+00:00"));
		dto3.setType(TierType.PLATINUM.name());
		dto3.setValue(50L);
		dtoList.add(dto3);

		Mockito.when(tierLimitService.findAll()).thenReturn(dtoList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/tierlimit").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].id", is("e4dc06a8-05c7-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$[0].createdAt", is("2023-06-07T00:00:00.000+00:00")))
				.andExpect(jsonPath("$[0].type", is(TierType.SILVER.name())))
				.andExpect(jsonPath("$[0].value", is(2)))
				.andExpect(jsonPath("$[1].id", is("6d85ba9e-0627-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$[1].createdAt", is("2023-06-08T00:00:00.000+00:00")))
				.andExpect(jsonPath("$[1].type", is(TierType.GOLD.name())))
				.andExpect(jsonPath("$[1].value", is(10)))
				.andExpect(jsonPath("$[2].id", is("73b37474-0627-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$[2].createdAt", is("2023-06-09T00:00:00.000+00:00")))
				.andExpect(jsonPath("$[2].type", is(TierType.PLATINUM.name())))
				.andExpect(jsonPath("$[2].value", is(50)));
	}

	@Test
	public void getTimeLimitTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		TierLimitDto dto1 = new TierLimitDto();
		dto1.setId("e4dc06a8-05c7-11ee-be56-0242ac120002");
		dto1.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		dto1.setType(TierType.SILVER.name());
		dto1.setValue(2L);

		Mockito.when(tierLimitService.findById("e4dc06a8-05c7-11ee-be56-0242ac120002")).thenReturn(dto1);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/tierlimit/e4dc06a8-05c7-11ee-be56-0242ac120002")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.id", is("e4dc06a8-05c7-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$.createdAt", is("2023-06-07T00:00:00.000+00:00")))
				.andExpect(jsonPath("$.type", is("SILVER")))
				.andExpect(jsonPath("$.value", is(2)));
	}

	@Test
	public void updateTimeLimitTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		TierLimitDto dto1 = new TierLimitDto();
		dto1.setId("e4dc06a8-05c7-11ee-be56-0242ac120002");
		dto1.setCreatedAt(sf.parse("2023-06-07T00:00:00.000+00:00"));
		dto1.setType(TierType.SILVER.name());
		dto1.setValue(2L);

		Mockito.doNothing().when(tierLimitService).update(dto1);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.patch("/api/tierlimit")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(dto1));

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isNoContent());
	}

}
