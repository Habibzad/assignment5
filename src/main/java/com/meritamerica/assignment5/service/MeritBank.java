package com.meritamerica.assignment5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.meritamerica.assignment5.Exceptions.NoSuchAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment5.Exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment5.models.AccountHolder;
import com.meritamerica.assignment5.models.CDAccount;
import com.meritamerica.assignment5.models.CDOffering;
import com.meritamerica.assignment5.models.CheckingAccount;
import com.meritamerica.assignment5.models.SavingsAccount;

@Service
public class MeritBank implements Bank{

	private Logger logger = LoggerFactory.getLogger(MeritBank.class);

	private final List<CDOffering> cdOfferings;
	private final List<AccountHolder> accountHolders;

//	Constructor
	public MeritBank() {
		this.cdOfferings = new ArrayList<>();
		this.accountHolders = new ArrayList<>();
	}

	@Override
	public List<CDOffering> getCDOfferings() {
		logger.info("returning cd offerings");
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
	public AccountHolder getAccountHolder(int id) throws NoSuchAccountException {
		try {
			return accountHolders.stream().filter(accountHolder1 -> accountHolder1.getId()==id).collect(Collectors.toList()).get(0);
		}catch (Exception e){
			throw new NoSuchAccountException(String.format("No account holder was found for id: %d", id));
		}
	}

	@Override
	public AccountHolder addAccountHolder(AccountHolder accountHolder) {
		accountHolders.add(accountHolder);
		return accountHolder;
	}

	@Override
	public CheckingAccount addCheckingAccount(int id, CheckingAccount checkingAccount) {
		for (AccountHolder ch : accountHolders) {
			if (ch.getId() == id) {
				try {
					ch.addCheckingAccount(checkingAccount);
				} catch (ExceedsCombinedBalanceLimitException e) {
					logger.debug(String.valueOf(e));
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
					logger.debug(String.valueOf(e));
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
					logger.debug(String.valueOf(e));
				}
			}
		}
		return cdAccount;
	}


}
