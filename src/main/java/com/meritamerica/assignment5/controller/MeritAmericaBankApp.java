package com.meritamerica.assignment5.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment5.models.AccountHolder;
import com.meritamerica.assignment5.models.MeritBank;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;




@RestController
public class MeritAmericaBankApp {
	ArrayList<AccountHolder> accountHolders = new ArrayList<AccountHolder>();
	AccountHolder accHolder = new AccountHolder("John", "A", "Doe", "000-222-3333");
	AccountHolder accHolder2 = new AccountHolder("Jane", "A", "Doe", "000-555-1111");
	
	//accountHolders.add(accHolder);
	
//	@GetMapping("/accountHolders")
//	public AccountHolder home() {
//		return accHolder2;
//	}

	@PostMapping(value = "/AccountHolder")
	
public AccountHolder addAccountHolder(@RequestBody  AccountHolder accountHolder) {
		MeritBank.addAccountHolder(accountHolder);
		return accountHolder;
	}
	// TODO complete
	@GetMapping(value = "/AccountHolder")
	public AccountHolder[] getListOfAccountHolders() {
		return MeritBank.getAccountHolders();
	}
	// TODO complete
	@GetMapping(value = "/AccountHolder/{id}")
	public AccountHolder getAccountHolderById(@PathVariable("id") AccountHolder id) throws Exception {
		AccountHolder accountHolder = MeritBank.addAccountHolder(id);
		if(accountHolder == null) { 
		
			throw new Exception("Account Not Found"); 
		}
		return accountHolder;
	}


	
	
	
	
}
