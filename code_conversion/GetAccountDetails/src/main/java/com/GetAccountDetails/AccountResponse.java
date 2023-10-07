
package com.GetAccountDetails;

public class AccountResponse {
    private String AccountNumber;
    private String AccountType;
    private String AccountBalance;
    private String Phone;
    private Address Address;

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public String getAccountBalance() {
        return AccountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        AccountBalance = accountBalance;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Address getAddress() {
        return Address;
    }

    public void setAddress(Address address) {
        Address = address;
    }
}
