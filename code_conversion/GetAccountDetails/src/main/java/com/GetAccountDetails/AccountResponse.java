
package com.GetAccountDetails;

public class AccountResponse {
<<<<<<< HEAD
    private String accountNumber;
    private String accountType;
    private String accountBalance;
    private String phone;
    private Address address;
    public AccountResponse(String TaccountNumber, String TaccountType, String TaccountBalance, Address Taddress, String Tphone) {
        accountNumber=TaccountNumber;
        accountType=TaccountType;
        accountBalance=TaccountBalance;
        phone=Tphone;
        address=Taddress;
    }
=======
    private String AccountNumber;
    private String AccountType;
    private String AccountBalance;
    private String Phone;
    private Address Address;

>>>>>>> 3465bb1923f979d15ba114cb2fe07d6d08bc3b8c
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
