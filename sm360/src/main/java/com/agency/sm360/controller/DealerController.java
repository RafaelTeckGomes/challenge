package com.agency.sm360.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agency.sm360.dto.DealerDto;
import com.agency.sm360.service.DealerService;

@RestController
@RequestMapping(value = "/api")
public class DealerController {

	@Autowired
	private DealerService dealerService;

	@PostMapping(path = "/dealer", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DealerDto> createDealer(@RequestBody DealerDto dto) {
		DealerDto dtoCreated = dealerService.save(dto);
		return new ResponseEntity<>(dtoCreated, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/dealer/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDealer(@PathVariable("id") String id) {
		dealerService.delete(id);
	}

	@PatchMapping(path = "/dealer", produces = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateDealer(@RequestBody DealerDto dto) {
		dealerService.update(dto);
	}

	@GetMapping(path = "/dealer/{id}", produces = "application/json")
	public ResponseEntity<DealerDto> getDealer(@PathVariable("id") String id) {
		DealerDto dto = dealerService.findById(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping(path = "/dealer", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<DealerDto>> getAllDealer() {
		List<DealerDto> listDto = dealerService.findAll();
		return new ResponseEntity<>(listDto, HttpStatus.OK);
	}

}