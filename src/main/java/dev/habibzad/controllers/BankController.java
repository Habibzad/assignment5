package dev.habibzad.controllers;

import dev.habibzad.service.MeritBank;
import java.util.List;

import javax.validation.Valid;

import dev.habibzad.Exceptions.ExceedsCombinedBalanceLimitException;
import dev.habibzad.Exceptions.InvalidArgumentException;
import dev.habibzad.Exceptions.NoSuchAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.habibzad.models.AccountHolder;
import dev.habibzad.models.CDAccount;
import dev.habibzad.models.CDOffering;
import dev.habibzad.models.CheckingAccount;
import dev.habibzad.models.SavingsAccount;

@RestController
public class BankController {

	private final Logger logger = LoggerFactory.getLogger(BankController.class);

	private final MeritBank bankService;

	public BankController(MeritBank bankService) {
		this.bankService = bankService;
	}

	@GetMapping("/CDOfferings")
	public List<CDOffering> getCDOfferings() {
		return bankService.getCDOfferings();
	}

	@GetMapping("/CDOfferings/{id}")
	public CDOffering getCDOffering(@PathVariable("id") int id) throws NoSuchAccountException {
		if (id > bankService.getCDOfferings().size()) {
			throw new NoSuchAccountException("Account with this id does not exist");
		}
		CDOffering cdOffering = null;
		for (CDOffering cdof : bankService.getCDOfferings()) {
			if (cdof.getId() == id) {
				cdOffering = cdof;
			}
		}
		return cdOffering;
	}

	@PostMapping("/CDOfferings")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering addCDOffering(@RequestBody CDOffering cdOffering) throws InvalidArgumentException {
		if (cdOffering.getInterestRate() <= 0 || cdOffering.getInterestRate() >= 1 || cdOffering.getTerm() < 1) {
			throw new InvalidArgumentException("Invalid Term or Interest Rate");
		}
		CDOffering cdof = new CDOffering(cdOffering.getInterestRate(), cdOffering.getTerm());
		bankService.addCDOffering(cdof);
		return cdof;
	}

	@GetMapping("/accountHolders")
	public List<AccountHolder> getAccountHolders() {
		return bankService.getAccountHolders();
	}

	@GetMapping("/accountHolders/{accountHolderID}")
	public AccountHolder getAccountHolder(@PathVariable int accountHolderID) throws NoSuchAccountException {
		if (accountHolderID > bankService.getAccountHolders().size()) {
			logger.warn("No account exists");
			throw new NoSuchAccountException("No such account");
		}
        return bankService.getAccountHolder(accountHolderID);
	}

	@PostMapping("/accountHolders")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder addAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
		bankService.addAccountHolder(accountHolder);
		return accountHolder;
	}

	@PostMapping("/accountHolders/{id}/checkingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckingAccount addCheckingAccount(@PathVariable int id, @RequestBody CheckingAccount checkingAccount)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException {
		AccountHolder accountHolder = bankService.getAccountHolder(id);
		CheckingAccount cha = new CheckingAccount(checkingAccount.getBalance());
		accountHolder.addCheckingAccount(cha);
		return cha;
	}

	@GetMapping("/accountHolders/{id}/checkingAccounts")
	public List<CheckingAccount> getCheckingAccounts(@PathVariable int id) throws NoSuchAccountException {
		return bankService.getAccountHolder(id).getCheckingAccounts();
	}

	@PostMapping("/accountHolders/{id}/savingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addSavingsAccounts(@PathVariable int id, @RequestBody SavingsAccount savingsAccount)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException {
		AccountHolder accountHolder = bankService.getAccountHolder(id);
		SavingsAccount sva = new SavingsAccount(savingsAccount.getBalance());
		accountHolder.addSavingsAccount(sva);
		return sva;
	}

	@GetMapping("/accountHolders/{id}/savingsAccounts")
	public List<SavingsAccount> getSavingsAccounts(@PathVariable int id) throws NoSuchAccountException {
		return bankService.getAccountHolder(id).getSavingsAccounts();
	}

	@PostMapping("/accountHolders/{id}/cdAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount addCDAccount(@PathVariable int id, @RequestBody CDAccount cdAccount)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException {
		AccountHolder accountHolder = bankService.getAccountHolder(id);
		CDOffering cdo = bankService.getCDOffering(cdAccount.getCdOffering().getId());
		CDAccount cdAcc = new CDAccount(cdAccount.getBalance(), cdo);
		accountHolder.addCdAccount(cdAcc);
		return cdAcc;
	}

	@GetMapping("/accountHolders/{id}/cdAccounts")
	public List<CDAccount> getCDAccounts(@PathVariable int id) throws NoSuchAccountException {
		return bankService.getAccountHolder(id).getCdAccounts();
	}
}
