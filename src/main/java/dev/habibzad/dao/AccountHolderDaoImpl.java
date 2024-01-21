package dev.habibzad.dao;

import dev.habibzad.Exceptions.AccountHolderNotFoundException;
import dev.habibzad.models.AccountHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountHolderDaoImpl implements AccountHolderDao{

    private final List<AccountHolder> accountHolders = new ArrayList<>();

    @Override
    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
        accountHolders.add(accountHolder);
        return accountHolder;
    }

    @Override
    public AccountHolder getAccountHolderById(int accountHolderId) {
        return accountHolders.stream()
                .filter(holder -> holder.getId() == accountHolderId)
                .findFirst()
                .orElseThrow(() -> new AccountHolderNotFoundException("Account holder with id: " + accountHolderId + " was not found"));
    }

    @Override
    public List<AccountHolder> getAllAccountHolders() {
        return accountHolders;
    }

    @Override
    public AccountHolder findAccountHolderByUsername(String username) {
        return accountHolders.stream()
                .filter(accountHolder -> accountHolder.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new AccountHolderNotFoundException("Account holder with username: " + username + " was not found"));
    }
}
