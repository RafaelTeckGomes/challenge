package com.agency.sm360.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TierTypeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2114119244074134965L;
	private final static String DEFAULT_MESSAGE = "TIER TYPE NOT FOUND";

	public TierTypeNotFoundException() {
		super(DEFAULT_MESSAGE);
	}

	public TierTypeNotFoundException(String message) {
		super(message);
	}
}
