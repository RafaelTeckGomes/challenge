package com.agency.sm360.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class TierLimitExceededException extends RuntimeException {

	private static final long serialVersionUID = 7772479437178648620L;

	public TierLimitExceededException() {
	}

	public TierLimitExceededException(String message) {
		super(message);
	}
}