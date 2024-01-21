package dev.habibzad.models;

import dev.habibzad.Exceptions.ExceedsCombinedBalanceLimitException;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class AccountHolder {

    public static final long BALANCE_LIMIT = 250000;
    private static int nextID = 1;

    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String ssn;
    private String email;
    private String phone;
    private String address;
    private final List<CheckingAccount> checkingAccounts;
    private final List<SavingsAccount> savingsAccounts;
    private final List<CDAccount> cdAccounts;
    private String username;

    public AccountHolder() {
        this.checkingAccounts = new ArrayList<>();
        this.savingsAccounts = new ArrayList<>();
        this.cdAccounts = new ArrayList<>();
    }

    // Parameterized Constructor


    public AccountHolder(String firstName, String middleName, String lastName, String ssn, String email, String phone, String address, String username) {
        this.id = nextID++;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.checkingAccounts = new ArrayList<>();
        this.savingsAccounts = new ArrayList<>();
        this.cdAccounts = new ArrayList<>();
        this.username = username;
    }

    public AccountHolder(String firstName, String lastName, String ssn, String email, String phone, String address, String username) {
        this(firstName, "", lastName, ssn, email, phone, address, username);
    }

    public void addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException {
        if (getCombinedBalance() + checkingAccount.getBalance() >= BALANCE_LIMIT) {
            throw new ExceedsCombinedBalanceLimitException("You have reached the maximum total balance across all accounts. Cannot create another account.");
        }
        this.checkingAccounts.add(checkingAccount);
    }

    public CheckingAccount addCheckingAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException {
        CheckingAccount newCheckingAccount = new CheckingAccount(openingBalance);
        if (getCombinedBalance() + openingBalance >= BALANCE_LIMIT) {
            throw new ExceedsCombinedBalanceLimitException("You have reached the maximum total balance across all accounts. Cannot create another.");
        }
        this.checkingAccounts.add(newCheckingAccount);
        return newCheckingAccount;
    }

    public void addSavingsAccount(SavingsAccount savingsAccount) throws ExceedsCombinedBalanceLimitException {
        if (getCombinedBalance() + savingsAccount.getBalance() >= BALANCE_LIMIT) {
            throw new ExceedsCombinedBalanceLimitException("You have reached the maximum total balance across all accounts. Cannot create another account.");
        } else {
            this.savingsAccounts.add(savingsAccount);
        }

    }

    public SavingsAccount addSavingsAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException {
        SavingsAccount newSavingsAccount = new SavingsAccount(openingBalance);
        if (getCombinedBalance() + openingBalance >= BALANCE_LIMIT) {
            throw new ExceedsCombinedBalanceLimitException("You have reached the maximum total balance across all accounts. Cannot create another.");
        } else {
            this.savingsAccounts.add(newSavingsAccount);
            return newSavingsAccount;
        }
    }

    public void addCdAccount(CDAccount cdAccount) throws ExceedsCombinedBalanceLimitException {
        if (getCombinedBalance() + cdAccount.getBalance() >= BALANCE_LIMIT) {
            throw new ExceedsCombinedBalanceLimitException("You have reached the maximum total balance across all accounts. Cannot create another account.");

        }
        this.cdAccounts.add(cdAccount);
    }

    public void addCdAccounts(double openingBalance, CDOffering offering) throws ExceedsCombinedBalanceLimitException {
        CDAccount newCDAccount = new CDAccount(openingBalance, offering);
        if (getCombinedBalance() + newCDAccount.getBalance() >= BALANCE_LIMIT) {
            throw new ExceedsCombinedBalanceLimitException("You have reached the maximum total balance across all accounts. Cannot create another account.");

        }
        this.cdAccounts.add(newCDAccount);
    }

    public double getCheckingBalance() {
        return checkingAccounts.stream()
                .mapToDouble(CheckingAccount::getBalance)
                .sum();
    }

    public double getSavingsBalance() {
        return savingsAccounts.stream().mapToDouble(SavingsAccount::getBalance).sum();
    }

    public double getCDBalance() {
       return cdAccounts.stream().mapToDouble(CDAccount::getBalance).sum();
    }

    public double getCombinedBalance() {
        return getCheckingBalance() + getSavingsBalance() + getCDBalance();
    }

    @Override
    public String toString() {
        return lastName + "," + middleName + "," + firstName + "," + ssn;
    }


    public int getNumberOfCheckingAccounts() {
        return this.checkingAccounts.size();
    }

    public int getNumberOfSavingsAccounts() {
        return this.savingsAccounts.size();
    }

    public int getNumberOfCDAccounts() {
        return this.cdAccounts.size();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public List<CheckingAccount> getCheckingAccounts() {
        return checkingAccounts;
    }

    public List<SavingsAccount> getSavingsAccounts() {
        return savingsAccounts;
    }

    public List<CDAccount> getCdAccounts() {
        return cdAccounts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
