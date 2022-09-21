package printing_shop;

public interface ICustomerManager<CustomerType, AddRequestType>{
    CustomerType AddCustomer(AddRequestType request);
    Iterable<CustomerType> GetCustomers();
    CustomerType GetCustomer(String id);
    CustomerType UpdateCustomer(CustomerType customer);
    boolean DeleteCustomer(String id);
}