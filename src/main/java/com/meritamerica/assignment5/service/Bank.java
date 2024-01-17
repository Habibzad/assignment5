package com.meritamerica.assignment5.service;

import java.util.List;

import com.meritamerica.assignment5.Exceptions.NoSuchAccountException;
import com.meritamerica.assignment5.models.AccountHolder;
import com.meritamerica.assignment5.models.CDAccount;
import com.meritamerica.assignment5.models.CDOffering;
import com.meritamerica.assignment5.models.CheckingAccount;
import com.meritamerica.assignment5.models.SavingsAccount;

public interface Bank {
	public List<CDOffering> getCDOfferings();
	public CDOffering getCDOffering(int id);
	public CDOffering addCDOffering(CDOffering cdOffering);
	public void clearCDOfferings();
	public List<AccountHolder> getAccountHolders();
	public AccountHolder getAccountHolder(int id) throws NoSuchAccountException;
	public AccountHolder addAccountHolder(AccountHolder accountHolder);
	public CheckingAccount addCheckingAccount(int id, CheckingAccount checkingAccount);
	public SavingsAccount addSavingsAccount(int id, SavingsAccount savingsAccount);
	public CDAccount addCDAccount(int id, CDAccount cdAccount);
}
