package com.meritamerica.assignment5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.meritamerica.assignment5.Exceptions.AccountHolderNotFoundException;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment5.Exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment5.models.AccountHolder;
import com.meritamerica.assignment5.models.CDAccount;
import com.meritamerica.assignment5.models.CDOffering;
import com.meritamerica.assignment5.models.CheckingAccount;
import com.meritamerica.assignment5.models.SavingsAccount;

@Service
public class MeritBank implements Bank{

	List<CDOffering> cdOfferings;
	List<AccountHolder> accountHolders;

//	Constructor
	public MeritBank() {
		this.cdOfferings = new ArrayList<>();
		this.accountHolders = new ArrayList<>();
	}

	@Override
	public List<CDOffering> getCDOfferings() {
		return cdOfferings;
	}

	@Override
	public CDOffering getCDOffering(int id) {
		CDOffering cdOffering = null;
		for (CDOffering cdoffering : cdOfferings) {
			if (cdoffering.getId() == id) {
				cdOffering = cdoffering;
			}
		}
		return cdOffering;
	}

	@Override
	public CDOffering addCDOffering(CDOffering cdOffering) {
		this.cdOfferings.add(cdOffering);
		return cdOffering;
	}

	@Override
	public void clearCDOfferings() {
		cdOfferings.clear();
	}

	@Override
	public List<AccountHolder> getAccountHolders() {
		return accountHolders;
	}

	@Override
	public AccountHolder getAccountHolder(int id) {
		try {
			return accountHolders.stream().filter(accountHolder1 -> accountHolder1.getId()==id).collect(Collectors.toList()).get(0);
		}catch (Exception e){
			throw new AccountHolderNotFoundException(String.format("No account holder was found for id: %d", id));
		}
	}

	@Override
	public AccountHolder addAccountHolder(AccountHolder accountHolder) {
		accountHolders.add(accountHolder);
		return accountHolder;
	}

	// @Override
	/*
	 * public double getAllAccountsBalances() { double total = 0; for (int i = 0; i
	 * < accountHolders.size(); i++) { total +=
	 * accountHolders.get(i).getCombinedBalance(); } return total; }
	 */

	@Override
	public CheckingAccount addCheckingAccount(int id, CheckingAccount checkingAccount) {
		for (AccountHolder ch : accountHolders) {
			if (ch.getId() == id) {
				try {
					ch.addCheckingAccount(checkingAccount);
				} catch (ExceedsCombinedBalanceLimitException e) {
					e.printStackTrace();
				}
			}
		}
		return checkingAccount;
	}

	@Override
	public SavingsAccount addSavingsAccount(int id, SavingsAccount savingsAccount) {
		for (AccountHolder ch : accountHolders) {
			if (ch.getId() == id) {
				try {
					ch.addSavingsAccount(savingsAccount);
				} catch (ExceedsCombinedBalanceLimitException e) {
					e.printStackTrace();
				}
			}
		}
		return savingsAccount;
	}

	@Override
	public CDAccount addCDAccount(int id, CDAccount cdAccount) {
		for (AccountHolder ch : accountHolders) {
			if (ch.getId() == id) {
				try {
					ch.addCdAccount(cdAccount);
				} catch (ExceedsCombinedBalanceLimitException e) {
					e.printStackTrace();
				}
			}
		}
		return cdAccount;
	}

	@Override // To be deleted
	public double getAllAccountsBalances() {
		// TODO Auto-generated method stub
		return 0;
	}

}
