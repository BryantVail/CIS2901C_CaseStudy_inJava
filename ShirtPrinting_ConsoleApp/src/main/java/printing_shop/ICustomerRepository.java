package printing_shop;

public interface ICustomerRepository<CustomerType, AddRequestType>{
    CustomerType AddCustomer(AddRequestType request);
    Iterable<CustomerType> getAsync();
    CustomerType GetCustomer(String id);
    CustomerType getByEmailAddress(String emailAddress);
    CustomerType UpdateCustomer(CustomerType customer);
    CustomerType changeDeletedStatus(String id, boolean changeToDeleted );

}