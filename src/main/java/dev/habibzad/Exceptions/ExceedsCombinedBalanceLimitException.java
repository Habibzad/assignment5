package dev.habibzad.Exceptions;

public class ExceedsCombinedBalanceLimitException extends Exception {
	public ExceedsCombinedBalanceLimitException(String errorMessage) {
		super(errorMessage);
	}
}
