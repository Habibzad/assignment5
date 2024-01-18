package dev.habibzad.models;

import java.time.LocalDate;

public class BankAccount {
	private static long nextAccountNumber = 1;

	private long accountNumber;
	private double balance;
	private double interestRate;
	private LocalDate openingDate;

	public BankAccount() {
	}

	public BankAccount(double balance) {
		this.accountNumber = nextAccountNumber++;
		this.balance = balance;
		this.interestRate = 0.01; // Interest Rate = 1%
		this.openingDate = LocalDate.now();
	}

	public boolean withdraw(double amount) {
		if (amount <= this.balance) {
			this.balance -= amount;
			return true;
		} else {
			return false;
		}
	}

	public boolean deposit(double amount) {
		if (amount <= 0) {
			return false;
		} else {
			this.balance += amount;
			return true;
		}
	}

	public double futureValue(int years) {
		return getBalance() * (Math.pow(1 + getInterestRate(), years));
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", balance=" + balance + ", interestRate=" + interestRate
				+ ", openingDate=" + openingDate + "]";
	}
}
