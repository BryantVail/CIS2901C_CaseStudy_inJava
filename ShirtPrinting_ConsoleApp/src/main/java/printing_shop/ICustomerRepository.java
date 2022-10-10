package printing_shop;

import printing_shop.domain.exceptions.DatabaseInternalException;
import printing_shop.domain.exceptions.RecordDoesNotExistException;

public interface ICustomerRepository<CustomerType, AddRequestType>{
    CustomerType AddCustomer(AddRequestType request);
    Iterable<CustomerType> getAsync();
    CustomerType GetCustomer(String id) throws RecordDoesNotExistException, DatabaseInternalException;
    CustomerType GetCustomerByEmailAddress(String emailAddress);
    CustomerType UpdateCustomer(CustomerType customer);
    boolean DeleteCustomer(String id) throws RecordDoesNotExistException;

}