package com.example.accountdetails;

public class AccountModel {
    private String accountNumber;
    private String accountName;
    private String balance;

    // Default constructor
    public AccountModel() {
    }

    // Parameterized constructor
    public AccountModel(String accountNumber, String accountName, String balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
    }

    // Getters and setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
