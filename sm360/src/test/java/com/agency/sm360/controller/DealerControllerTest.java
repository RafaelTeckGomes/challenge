package com.agency.sm360.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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

import com.agency.sm360.dto.DealerDto;
import com.agency.sm360.dto.TierLimitDto;
import com.agency.sm360.entity.TierType;
import com.agency.sm360.service.DealerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DealerController.class)
class DealerControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	DealerService dealerService;

	@Test
	public void getAllDealerTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		List<DealerDto> dtoList = new ArrayList<DealerDto>();
		
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
		dtoList.add(dto1);
		
		
		DealerDto dto2 = new DealerDto();
		dto2.setId("cd3437e0-07a3-11ee-be56-0242ac120002");
		dto2.setCreatedAt(sf.parse("2023-06-08T00:00:00.000+00:00"));
		dto2.setName("DEALER2");
		
		TierLimitDto tierLimit2 = new TierLimitDto();
		tierLimit2.setId("6d85ba9e-0627-11ee-be56-0242ac120002");
		tierLimit2.setCreatedAt(sf.parse("2023-06-08T00:00:00.000+00:00"));
		tierLimit2.setType(TierType.GOLD.name());
		tierLimit2.setValue(10L);
		dto2.setTierLimit(tierLimit2);
		dtoList.add(dto2);


		Mockito.when(dealerService.findAll()).thenReturn(dtoList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/dealer").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is("05a2426c-05c8-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$[0].name", is("DEALER1")))
				.andExpect(jsonPath("$[0].createdAt", is("2023-06-07T00:00:00.000+00:00")))
				.andExpect(jsonPath("$[0].tierLimit.type", is(TierType.SILVER.name())))
				.andExpect(jsonPath("$[0].tierLimit.value", is(2)))			
				.andExpect(jsonPath("$[1].id", is("cd3437e0-07a3-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$[1].name", is("DEALER2")))
				.andExpect(jsonPath("$[1].createdAt", is("2023-06-08T00:00:00.000+00:00")))
				.andExpect(jsonPath("$[1].tierLimit.type", is(TierType.GOLD.name())))
				.andExpect(jsonPath("$[1].tierLimit.value", is(10)));
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
		tierLimit.setType(TierType.SILVER.name());
		tierLimit.setValue(2L);
		dto1.setTierLimit(tierLimit);

		Mockito.when(dealerService.findById("05a2426c-05c8-11ee-be56-0242ac120002")).thenReturn(dto1);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/dealer/05a2426c-05c8-11ee-be56-0242ac120002")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.id", is("05a2426c-05c8-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$.name", is("DEALER1")))
				.andExpect(jsonPath("$.createdAt", is("2023-06-07T00:00:00.000+00:00")))
				.andExpect(jsonPath("$.tierLimit", notNullValue()));
		
	}
	
	@Test
	public void updateDealerTest() throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		DealerDto dto1 = new DealerDto();
		dto1.setId("05a2426c-05c8-11ee-be56-0242ac120002\"");
		dto1.setName("NEW NAME DEALER");

		Mockito.doNothing().when(dealerService).update(dto1);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.patch("/api/dealer")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(dto1));

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isNoContent());

	}
	
	@Test
	public void deleteDealerTest() throws Exception {
		Mockito.doNothing().when(dealerService).delete("05a2426c-05c8-11ee-be56-0242ac120002\"");
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.delete("/api/dealer/05a2426c-05c8-11ee-be56-0242ac120002\"")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8");

		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
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

		Mockito.when(dealerService.save(Mockito.any(DealerDto.class))).thenReturn(dto1);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/dealer").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsBytes(dto1)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(jsonPath("$.id", is("05a2426c-05c8-11ee-be56-0242ac120002")))
				.andExpect(jsonPath("$.name", is("DEALER1")))
				.andExpect(jsonPath("$.createdAt", is("2023-06-07T00:00:00.000+00:00")))
				.andExpect(jsonPath("$.tierLimit", notNullValue()));
	}


}
