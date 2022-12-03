package printing_shop.domain.customers;

import printing_shop.domain.exceptions.DatabaseInternalException;
import printing_shop.domain.exceptions.IdentifierNotParsableToCorrectTypeException;
import printing_shop.domain.exceptions.RecordDoesNotExistException;

public interface ICustomerManager<CustomerType, AddRequestType>{
    CustomerType AddCustomer(AddRequestType request);
    Iterable<CustomerType> GetCustomers();
    CustomerType get(String id) throws DatabaseInternalException, IdentifierNotParsableToCorrectTypeException, RecordDoesNotExistException;
    CustomerType updateEmailAddress(String id, String newEmail);
    CustomerType updateFirstName(String id, String newFirstName);
    CustomerType updateLastName(String id, String newLastName);
    CustomerType changeDeletedStatus(String id, boolean changeToDeleted) throws DatabaseInternalException, RecordDoesNotExistException;

    CustomerType getByEmailAddress(String emailAddress);
}