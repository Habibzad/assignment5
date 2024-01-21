package dev.habibzad.dao;

import dev.habibzad.models.AccountHolder;

import java.util.List;

public interface AccountHolderDao {

    AccountHolder addAccountHolder(AccountHolder accountHolder);
    AccountHolder getAccountHolderById(int accountHolderId);
    List<AccountHolder> getAllAccountHolders();
    AccountHolder findAccountHolderByUsername(String username);
}
