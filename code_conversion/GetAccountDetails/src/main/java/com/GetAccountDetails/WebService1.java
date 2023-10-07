
package com.GetAccountDetails;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WebService1 {
    private static final String BACKEND_URL = "http://backend-url.com";

    @GetMapping("/GetAccountDetails/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccountDetails(@PathVariable String accountNumber) {
        String soapRequest = String.format(
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                        "xmlns:get=\"http://ai.transform.backend.com/services/GetAgreementDetails\">\n" +
                        "<soapenv:Header/>\n" +
                        "<soapenv:Body>\n" +
                        "<get:GetAgreementDetailsRequest>\n" +
                        "<get:AgreementNumber>%s</get:AgreementNumber>\n" +
                        "</get:GetAgreementDetailsRequest>\n" +
                        "</soapenv:Body>\n" +
                        "</soapenv:Envelope>", accountNumber);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        HttpEntity<String> httpEntity = new HttpEntity<>(soapRequest, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:8088/GetAgreementDetails", HttpMethod.POST, httpEntity, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String soapResponse = responseEntity.getBody();
            AccountResponse accountResponse = parseSoapResponse(soapResponse);
            return new ResponseEntity<>(accountResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private AccountResponse parseSoapResponse(String soapResponse) {
        // Parse the SOAP response XML and extract the values
        // You can use libraries like JAXB or manually parse the XML
        // Replace the following lines with your actual parsing logic
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(soapResponse)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Element envelope = document.getDocumentElement();
        Element body = (Element) envelope.getElementsByTagName("soapenv:Body").item(0);

        // Get the GetAgreementDetailsResponse element
        Element response = (Element) body.getElementsByTagName("get:GetAgreementDetailsResponse").item(0);

        // Get elements within GetAgreementDetailsResponse
        String agreementNumber = response.getElementsByTagName("get:AgreementNumber").item(0).getTextContent();
        String agreementType =  response.getElementsByTagName("get:AgreementType").item(0).getTextContent();
        String balance =  response.getElementsByTagName("get:Balance").item(0).getTextContent();
        Element contactPoint = (Element) response.getElementsByTagName("get:ContactPoint").item(0);
        String addressLine1 =  contactPoint.getElementsByTagName("get:AddressLine1").item(0).getTextContent();
        String addressLine2 =  contactPoint.getElementsByTagName("get:AddressLine2").item(0).getTextContent();
        String country =  contactPoint.getElementsByTagName("get:Country").item(0).getTextContent();
        String postalCode =  contactPoint.getElementsByTagName("get:PostalCode").item(0).getTextContent();
        String phone =  response.getElementsByTagName("get:Phone").item(0).getTextContent();

        Address address = new Address(addressLine1, addressLine2, country, postalCode);
        AccountResponse ar =  new AccountResponse(agreementNumber, agreementType, balance, address, phone);
        return ar;
=======

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
>>>>>>> 3465bb1923f979d15ba114cb2fe07d6d08bc3b8c
    }
}
