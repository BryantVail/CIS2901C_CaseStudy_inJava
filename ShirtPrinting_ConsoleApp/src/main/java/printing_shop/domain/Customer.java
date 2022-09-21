package printing_shop.domain;

public class Customer {

    public Customer(
            String id,
            String emailAddress,
            String firstName,
            String lastName
    ){
        this.Id = id;
        this.EmailAddress = emailAddress;
        this.FirstName = firstName;
        this.LastName = lastName;
    }
    public String Id;
    public String EmailAddress;
    public String FirstName;
    public String LastName;

}
