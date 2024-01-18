package dev.habibzad.service;

import java.util.List;

import dev.habibzad.Exceptions.NoSuchAccountException;
import dev.habibzad.models.AccountHolder;
import dev.habibzad.models.CDAccount;
import dev.habibzad.models.CDOffering;
import dev.habibzad.models.CheckingAccount;
import dev.habibzad.models.SavingsAccount;

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
