
package com.GetAccountDetails;

public class Address {
    private String FirstLine;
    private String SecondLine;
    private String Country;
    private String PostalCode;

    public String getFirstLine() {
        return FirstLine;
    }

    public void setFirstLine(String firstLine) {
        FirstLine = firstLine;
    }

    public String getSecondLine() {
        return SecondLine;
    }

    public void setSecondLine(String secondLine) {
        SecondLine = secondLine;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }
}
