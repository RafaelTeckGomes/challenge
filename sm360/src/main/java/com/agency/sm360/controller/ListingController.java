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

import com.agency.sm360.dto.ListingDto;
import com.agency.sm360.service.ListingService;

@RestController
@RequestMapping(value = "/api")
public class ListingController {

	@Autowired
	private ListingService listingService;

	@GetMapping(path = "/listing/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<ListingDto> getListing(@PathVariable("id") String id) {
		ListingDto dto = listingService.findById(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping(path = "/listing/dealer/{id}/status/{status}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<ListingDto>> getAllListingByDealer(@PathVariable("id") String id,
			@PathVariable("status") String status) {
		List<ListingDto> listDto = listingService.findAllListingByDealer(id, status);
		return new ResponseEntity<>(listDto, HttpStatus.OK);
	}

	@PostMapping(path = "/listing", produces = "application/json")
	public ResponseEntity<ListingDto> createListing(@RequestBody ListingDto dto) {
		ListingDto dtoCreated = listingService.save(dto);
		return new ResponseEntity<>(dtoCreated, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/listing/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteListing(@PathVariable("id") String id) {
		listingService.delete(id);
	}

	@PatchMapping(path = "/listing", produces = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateListing(@RequestBody ListingDto dto) {
		listingService.update(dto);
	}

	@PatchMapping(path = "/listing/{listingId}/dealer/{dealerId}/publish", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void publish(@PathVariable("listingId") String listingId,
			@PathVariable("dealerId") String dealerId) {
		listingService.publish(listingId, dealerId);
	}

	@PatchMapping(path = "/listing/unpublish/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void unpublish(@PathVariable("id") String id) {
		listingService.unPublish(id);
	}

}