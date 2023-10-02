using System;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Web.Services;
using System.Xml.Linq;

namespace GetAccountDetails
{
    /// <summary>
    /// Summary description for WebService1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class WebService1 : System.Web.Services.WebService
    {

        [WebMethod]
        public AccountResponse GetAgreementDetails(string AgreementNumber)
        {
            // localhost.GetAgreementDetailsRequest obj = new localhost.GetAgreementDetailsRequest();
            //obj.AgreementNumber = "108201520416";
            string readTask = null;
            var accountResponse =new AccountResponse();
            using (var client = new HttpClient())
            {
                client.BaseAddress = new Uri("http://localhost:8088/");
                string soapRequest = $@"
<soapenv:Envelope xmlns:soapenv=""http://schemas.xmlsoap.org/soap/envelope/"" xmlns:get=""http://ai.transform.backend.com/services/GetAgreementDetails"">
<soapenv:Header/>
<soapenv:Body>
<get:GetAgreementDetailsRequest>
<get:AgreementNumber>1224324</get:AgreementNumber>
</get:GetAgreementDetailsRequest>
</soapenv:Body>
</soapenv:Envelope>";
                StringContent content = new StringContent(soapRequest, Encoding.UTF8, "application/xml");
                
                //HTTP GET
                var responseTask = client.PostAsync("GetAgreementDetails", content);
                responseTask.Wait();

                var result = responseTask.Result;
                if (result.IsSuccessStatusCode)
                {

                    readTask = result.Content.ReadAsStringAsync().Result;
                    
                    XDocument soapResponseXml = XDocument.Parse(readTask);
                    XNamespace soapNamespace = "http://schemas.xmlsoap.org/soap/envelope/";
                    XNamespace dataNamespace = "http://ai.transform.backend.com/services/GetAgreementDetails"; // Replace with the actual namespace used in your SOAP response
                    var agreementNumber = soapResponseXml.Descendants(soapNamespace + "Body")
                        .Descendants(dataNamespace + "AgreementNumber")
                        .FirstOrDefault()?.Value;
                    var AgreementType = soapResponseXml.Descendants(soapNamespace + "Body")
                        .Descendants(dataNamespace + "AgreementType")
                        .FirstOrDefault()?.Value;
                    var Balance = soapResponseXml.Descendants(soapNamespace + "Body")
                        .Descendants(dataNamespace + "Balance")
                        .FirstOrDefault()?.Value;
                    var AddressLine1 = soapResponseXml.Descendants(soapNamespace + "Body")
                        .Descendants(dataNamespace + "AddressLine1")
                        .FirstOrDefault()?.Value;
                    var AddressLine2 = soapResponseXml.Descendants(soapNamespace + "Body")
                        .Descendants(dataNamespace + "AddressLine2")
                        .FirstOrDefault()?.Value;
                    var Country = soapResponseXml.Descendants(soapNamespace + "Body")
                      .Descendants(dataNamespace + "Country")
                      .FirstOrDefault()?.Value;
                    var PostalCode = soapResponseXml.Descendants(soapNamespace + "Body")
                                          .Descendants(dataNamespace + "PostalCode")
                                          .FirstOrDefault()?.Value;
                    var Phone = soapResponseXml.Descendants(soapNamespace + "Body")
                                        .Descendants(dataNamespace + "Phone")
                                        .FirstOrDefault()?.Value;

                    accountResponse = new AccountResponse
                    {
                        AccountNumber = agreementNumber,
                        AccountType = AgreementType,
                        AccountBalance = Balance,
                        Address = new Address
                        {
                            FirstLine = AddressLine1,
                            SecondLine = AddressLine2,
                            Country = Country,
                            PostalCode = PostalCode
                        },
                        Phone = Phone
                    };
                    
                }

            }
            return accountResponse;
            //return readTask;
        }

    }
}
