package printing_shop.domain;

public class AddCustomerRequest {
    public AddCustomerRequest(){

    }
    public AddCustomerRequest(
            String emailAddress,
            String firstName,
            String lastName
    ){
        this.EmailAddress = emailAddress;
        this.FirstName = firstName;
        this.LastName = lastName;
    }
    public String EmailAddress;
    public String FirstName;
    public String LastName;
}
