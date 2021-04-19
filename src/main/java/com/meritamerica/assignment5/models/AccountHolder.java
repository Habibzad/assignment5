package com.meritamerica.assignment5.models;

import com.meritamerica.assignment5.Exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment5.Exceptions.ExceedsFraudSuspicionLimitException;
import com.sun.el.parser.ParseException;

public class AccountHolder{
	// Constants
	public static final long BALANCE_LIMIT = 250000;

	// Instance variables
	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String ssn;
	private CheckingAccount[] checkingAccounts;
	private SavingsAccount[] saveAccounts;
	private CDAccount[] cdAccounts;

	private static long nextAccountNumber;
	private static int idGen = 0;
	

	// Constructor
	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		this.setId(1);
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
		checkingAccounts = new CheckingAccount[0];
		saveAccounts = new SavingsAccount[0];
		cdAccounts = new CDAccount[0];
		nextAccountNumber = 1000;
	}

	// Getters and Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSSN() {
		return ssn;
	}

	public void setSSN(String ssn) {
		this.ssn = ssn;
	}

	public CheckingAccount[] getCheckingAccounts() {
		return this.checkingAccounts;
	}

	public void setCheckingAccounts(CheckingAccount[] checkAccounts) {
		this.checkingAccounts = checkAccounts;
	}

	public int getNumberOfCheckingAccounts() {
		return this.checkingAccounts.length;
	}

	public double getCheckingBalance() {
		double total = 0;
		for (int i = 0; i < checkingAccounts.length; i++) {
			total += checkingAccounts[i].getBalance();
		}
		return total;
	}

	public SavingsAccount[] getSavingsAccounts() {
		return this.saveAccounts;
	}

	public int getNumberOfSavingsAccounts() {
		return this.saveAccounts.length;
	}

	public double getSavingsBalance() {
		double total = 0;

		for (int i = 0; i < saveAccounts.length; i++) {
			total += saveAccounts[i].getBalance();
		}

		return total;
	}

	public void setSavingsAccounts(SavingsAccount[] saveAccounts) {
		this.saveAccounts = saveAccounts;
	}

	public CDAccount[] getCdAccounts() {
		return this.cdAccounts;
	}

	public int getNumberOfCDAccounts() {
		return this.cdAccounts.length;
	}

	public double getCDBalance() {
		double total = 0;

		for (int i = 0; i < cdAccounts.length; i++) {
			total += cdAccounts[i].getBalance();
		}

		return total;
	}

	public void setCdAccounts(CDAccount[] cdAccounts) {
		this.cdAccounts = cdAccounts;
	}

	public double getCombinedBalance() {
		double total = 0;

		for (int i = 0; i < checkingAccounts.length; i++) {
			total += checkingAccounts[i].getBalance();
		}

		for (int i = 0; i < saveAccounts.length; i++) {
			total += saveAccounts[i].getBalance();
		}

		for (int i = 0; i < cdAccounts.length; i++) {
			total += cdAccounts[i].getBalance();
		}

		return total;
	}



	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount)
			throws ExceedsCombinedBalanceLimitException {
		if (getCombinedBalance() + checkingAccount.getBalance() >= BALANCE_LIMIT) {

			throw new ExceedsCombinedBalanceLimitException(
					"You have reached the maximum total balance across all accounts. Cannot create another.");

		} else {
			CheckingAccount[] newCheckingAccounts = new CheckingAccount[checkingAccounts.length + 1];
			int i;
			for (i = 0; i < checkingAccounts.length; i++) {
				newCheckingAccounts[i] = checkingAccounts[i];
			}

			newCheckingAccounts[i] = checkingAccount;
			checkingAccounts = newCheckingAccounts;

			return checkingAccount;
		}
	}

	// Add savings account
	public SavingsAccount addSavingsAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException {
		SavingsAccount newSavingsAccount = new SavingsAccount(openingBalance);
		if (getCombinedBalance() + openingBalance >= BALANCE_LIMIT) {

			throw new ExceedsCombinedBalanceLimitException(
					"You have reached the maximum total balance across all accounts. Cannot create another.");

		} else {
			// Add a deposit transaction
			return addSavingsAccount(newSavingsAccount);
		}
	}

	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) throws ExceedsCombinedBalanceLimitException {
		if (getCombinedBalance() + savingsAccount.getBalance() >= BALANCE_LIMIT) {
			throw new ExceedsCombinedBalanceLimitException(
					"You have reached the maximum total balance across all accounts. Cannot create another.");

		} else {
			SavingsAccount[] newArray = new SavingsAccount[saveAccounts.length + 1];
			int i;
			for (i = 0; i < saveAccounts.length; i++) {
				newArray[i] = saveAccounts[i];
			}
			newArray[i] = savingsAccount;
			saveAccounts = newArray;
			return savingsAccount;
		}
	}

	// Add CD Accounts
	public CDAccount addCDAccount(CDAccount cdAccount) {
		CDAccount[] tempArray = new CDAccount[cdAccounts.length + 1];
		int i;
		for (i = 0; i < cdAccounts.length; i++) {
			tempArray[i] = cdAccounts[i];
		}
		tempArray[i] = cdAccount;
		cdAccounts = tempArray;
		return cdAccount;
	}

	public static long getNewAccountNumber() {
		// TODO Auto-generated method stub
		return nextAccountNumber++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = idGen++;
	}

	public static AccountHolder readFromString(String line) {
		// TODO Auto-generated method stub
		return null;
	}


}
