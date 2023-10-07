
package com.GetAccountDetails;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@RestController
public class WebService1 {

    @GetMapping("/GetAccountDetails/{accountNumber}")
    public AccountResponse getAccountDetails(@PathVariable String accountNumber) {
        String soapRequest = "<soapenv:Envelope xmlns:soapenv=\\"http://schemas.xmlsoap.org/soap/envelope/\\" xmlns:get=\\"http://example.com/GetAgreementDetails/\\">\
" +
                "    <soapenv:Header/>\
" +
                "    <soapenv:Body>\
" +
                "        <get:GetAgreementDetailsRequest>\
" +
                "            <get:AgreementNumber>" + accountNumber + "</get:AgreementNumber>\
" +
                "        </get:GetAgreementDetailsRequest>\
" +
                "    </soapenv:Body>\
" +
                "</soapenv:Envelope>";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> requestEntity = new HttpEntity<>(soapRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        String soapResponse = restTemplate.exchange("http://backend-url/GetAgreementDetails", HttpMethod.POST, requestEntity, String.class).getBody();

        // Parse the soapResponse and extract the required values
        // ...

        AccountResponse accountResponse = new AccountResponse();
        // Set the values to accountResponse
        // ...

        return accountResponse;
    }
}
