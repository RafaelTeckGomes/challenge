package com.agency.sm360.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ListingNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7772479437178648620L;

	private final static String DEFAULT_MESSAGE = "LISTING NOT FOUND";

	public ListingNotFoundException() {
		super(DEFAULT_MESSAGE);
	}

	public ListingNotFoundException(String message) {
		super(message);
	}
}