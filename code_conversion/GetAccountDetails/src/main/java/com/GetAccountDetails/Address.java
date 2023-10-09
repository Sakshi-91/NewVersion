
package com.GetAccountDetails;

public class Address {
    private String firstLine;
    private String secondLine;
    private String country;
    private String postalCode;
    public Address(String addressLine1, String addressLine2, String Tcountry, String TpostalCode) {
        firstLine=addressLine1;
        secondLine=addressLine2;
        country=Tcountry;
        postalCode=TpostalCode;
    }
    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public void setSecondLine(String secondLine) {
        this.secondLine = secondLine;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
' '
