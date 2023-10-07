
package com.GetAccountDetails;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WebService1 {
    private static final String BACKEND_URL = "http://backend-url.com";

    @GetMapping("/GetAccountDetails/{accountNumber}")
    public AccountResponse getAccountDetails(@PathVariable String accountNumber) {
        String readTask = null;
        AccountResponse accountResponse = new AccountResponse();

        // Make HTTP request to backend URL
        // ...

        // Parse response and populate accountResponse object
        // ...

        return accountResponse;
    }
}
