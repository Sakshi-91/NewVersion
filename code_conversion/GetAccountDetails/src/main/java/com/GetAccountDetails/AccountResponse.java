
package com.GetAccountDetails;

public class AccountResponse {
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
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
 
 
 
