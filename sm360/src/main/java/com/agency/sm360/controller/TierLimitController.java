package com.agency.sm360.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agency.sm360.dto.TierLimitDto;
import com.agency.sm360.service.TierLimitService;

@RestController
@RequestMapping(value = "/api")
public class TierLimitController {

	@Autowired
	private TierLimitService tierLimitService;

	@PatchMapping(path = "/tierlimit", produces = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateTimeLimit(@RequestBody TierLimitDto dto) {
		tierLimitService.update(dto);
	}

	@GetMapping(path = "/tierlimit/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<TierLimitDto> getTimeLimit(@PathVariable("id") String id) {
		TierLimitDto dto = tierLimitService.findById(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping(path = "/tierlimit", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<TierLimitDto>> getAllTimeLimit() {
		List<TierLimitDto> listDto = tierLimitService.findAll();
		return new ResponseEntity<>(listDto, HttpStatus.OK);
	}
}
