package com.meritamerica.assignment5.models;

import javax.validation.Valid;

public class MeritBank {

	public static void addAccountHolder(@Valid AccountHolder accountHolder) {
		// TODO Auto-generated method stub
		AccountHolder[] newAccountHolders = new AccountHolder[accountHolders.length + 1];
		int i = 0;
		for (i = 0; i < accountHolders.length; i++) {
			newAccountHolders[i] = accountHolders[i];
		}
		newAccountHolders[i] = accountHolder;
		accountHolders = newAccountHolders;
	
	}

	public static AccountHolder[] getAccountHolders() {
		// TODO Auto-generated method stub
		return accountHolders;
		
		
	}

	public static AccountHolder addAccountHolder(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	private static AccountHolder[] accountHolders = new AccountHolder[0];
	private static CDOffering[] cdOfferings = new CDOffering[0];




	
	
	
}
