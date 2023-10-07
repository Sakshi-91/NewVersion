
package com.GetAccountDetails;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class WebService1 {

    @GetMapping("/GetAccountDetails")
    public AccountResponse getAccountDetails(@RequestParam String AccountNumber) {
        String readTask = null;
        AccountResponse accountResponse = new AccountResponse();

        // Your SOAP request logic here

        return accountResponse;
    }
}
