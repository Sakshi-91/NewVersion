
package com.GetAccountDetails;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebService1 {

    @GetMapping("/GetAccountDetails/{AccountNumber}")
    public AccountResponse getAccountDetails(@PathVariable String AccountNumber) {
        String readTask = null;
        AccountResponse accountResponse = new AccountResponse();
        RestTemplate restTemplate = new RestTemplate();

        String soapRequest = "<soapenv:Envelope xmlns:soapenv=\\"http://schemas.xmlsoap.org/soap/envelope/\\" " +
                "xmlns:get=\\"http://example.com/GetAccountDetails/\\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<get:GetAgreementDetailsRequest>" +
                "<get:AgreementNumber>" + AccountNumber + "</get:AgreementNumber>" +
                "</get:GetAgreementDetailsRequest>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";

        String url = "http://backend-url/GetAgreementDetails";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(soapRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            readTask = response.getBody();
            XDocument soapResponseXml = XDocument.Parse(readTask);

            String AgreementType = soapResponseXml.Descendants(soapNamespace + "Body")
                    .Descendants(dataNamespace + "AgreementType")
                    .FirstOrDefault()?.Value;
            String Balance = soapResponseXml.Descendants(soapNamespace + "Body")
                    .Descendants(dataNamespace + "Balance")
                    .FirstOrDefault()?.Value;
            String AddressLine1 = soapResponseXml.Descendants(soapNamespace + "Body")
                    .Descendants(dataNamespace + "AddressLine1")
                    .FirstOrDefault()?.Value;
            String AddressLine2 = soapResponseXml.Descendants(soapNamespace + "Body")
                    .Descendants(dataNamespace + "AddressLine2")
                    .FirstOrDefault()?.Value;
            String Country = soapResponseXml.Descendants(soapNamespace + "Body")
                    .Descendants(dataNamespace + "Country")
                    .FirstOrDefault()?.Value;
            String PostalCode = soapResponseXml.Descendants(soapNamespace + "Body")
                    .Descendants(dataNamespace + "PostalCode")
                    .FirstOrDefault()?.Value;
            String Phone = soapResponseXml.Descendants(soapNamespace + "Body")
                    .Descendants(dataNamespace + "Phone")
                    .FirstOrDefault()?.Value;

            accountResponse.setAccountNumber(AccountNumber);
            accountResponse.setAccountType(AgreementType);
            accountResponse.setAccountBalance(Balance);
            accountResponse.setAddress(new Address(AddressLine1, AddressLine2, Country, PostalCode));
            accountResponse.setPhone(Phone);
        }

        return accountResponse;
    }
}
