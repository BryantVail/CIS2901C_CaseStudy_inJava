package printing_shop.domain;

import java.time.LocalDateTime;

public class Customer {


    public Customer(
            String id,
            LocalDateTime createdDate,
            boolean deleted,
            String emailAddress,
            String firstName,
            String lastName,
            LocalDateTime lastUpdated

    ){
        this.Id = id;
        this.CreatedDate = createdDate;
        this.Deleted = deleted;
        this.EmailAddress = emailAddress;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.LastUpdated = lastUpdated;
    }


    public String Id;
    public LocalDateTime CreatedDate;
    public boolean Deleted;
    public String EmailAddress;
    public String FirstName;
    public String LastName;
    public LocalDateTime LastUpdated;

}
