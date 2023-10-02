namespace GetAccountDetails
{
    public class AccountRequest
    {
        public string AccountNumber { get; set; }
    }

    public class AccountResponse
    {
        public string AccountNumber { get; set; }
        // Add other account details as needed
        public string AccountType { get; set; }
        public string AccountBalance { get; set; }

        public string Phone { get; set; }
        public Address Address { get; set; }
    }

    public class Address
    {
       
        public string FirstLine { get; set; }

        public string SecondLine { get; set; }
        public string Country { get; set; }
        public string PostalCode { get; set; }
 
    }
}