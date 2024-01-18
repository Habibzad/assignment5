package dev.habibzad.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidArgumentException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9102921814854493350L;

	public InvalidArgumentException(String message) {
		super(message);
	}
}
