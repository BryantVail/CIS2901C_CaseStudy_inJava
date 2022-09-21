package printing_shop;

public interface ICustomerRepository<CustomerType, AddRequestType>{
    CustomerType AddCustomer(AddRequestType request);
    Iterable<CustomerType> getCustomers();
    CustomerType GetCustomer(String id);
    CustomerType UpdateCustomer(CustomerType customer);
    boolean DeleteCustomer(String id);

}