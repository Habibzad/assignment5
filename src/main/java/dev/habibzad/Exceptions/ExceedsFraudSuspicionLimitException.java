package dev.habibzad.Exceptions;

public class ExceedsFraudSuspicionLimitException extends Exception {
	public ExceedsFraudSuspicionLimitException(String errorMessage) {
		super(errorMessage);
	}
}
