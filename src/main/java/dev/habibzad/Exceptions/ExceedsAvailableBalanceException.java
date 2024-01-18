package dev.habibzad.Exceptions;

public class ExceedsAvailableBalanceException extends Exception {
	ExceedsAvailableBalanceException(String errorMessage){
		super(errorMessage);
	}
}
