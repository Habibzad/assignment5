package com.meritamerica.assignment5.controllers;

import com.meritamerica.assignment5.service.MeritBank;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment5.Exceptions.InterestRateNotInRangeException;
import com.meritamerica.assignment5.Exceptions.NoSuchAccountException;
import com.meritamerica.assignment5.models.AccountHolder;
import com.meritamerica.assignment5.models.CDOffering;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
public class BankController {
	 Logger logs = LoggerFactory.getLogger(BankController.class);

	@Autowired
	private MeritBank meritBank;
	
	//CDOfferings
	@GetMapping("/CDOfferings")
	public List<CDOffering> getCDOfferings() {
		return meritBank.getCDOfferings();
	}
	
	@PostMapping("/CDOfferings")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering addCDOffering(@RequestBody CDOffering cdOffering) throws InterestRateNotInRangeException{
		if(cdOffering.getInterestRate()<=0 || cdOffering.getInterestRate()>=1) {
			logs.warn("No offering exists");
			throw new InterestRateNotInRangeException("Interest rate mush be between 0 and 1");
		}
		meritBank.addCDOffering(cdOffering);
		return cdOffering;
	}
	
	//AccountHolders
	@GetMapping("/accountHolders")
	public List<AccountHolder> getAccountHolders() {
		return meritBank.getAccountHolders();
	}
	
	@GetMapping("/accountHolders/{accountHolderID}")
	public AccountHolder getAccountHolder(@PathVariable String accountHolderID) throws NoSuchAccountException {
		int accHolderID = Integer.parseInt(accountHolderID);
		if(accHolderID>meritBank.getAccountHolders().size()) {
			logs.warn("No account exists");
			throw new NoSuchAccountException("No such account");
		}
		AccountHolder accountHold = meritBank.getAccountHolder(accHolderID);
		return accountHold;
	}
	
	@PostMapping("/accountHolders")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder addAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
		meritBank.addAccountHolder(accountHolder);
		return accountHolder;
	}
	
	
}
