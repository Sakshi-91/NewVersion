
package com.GetAccountDetails;

public class Address {
<<<<<<< HEAD
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
=======
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
>>>>>>> 3465bb1923f979d15ba114cb2fe07d6d08bc3b8c
    }
}
