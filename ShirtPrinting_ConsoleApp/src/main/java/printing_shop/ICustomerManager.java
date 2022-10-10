package printing_shop;

import com.mysql.cj.x.protobuf.MysqlxExpr;
import printing_shop.domain.exceptions.DatabaseInternalException;
import printing_shop.domain.exceptions.IdentifierNotParsableToCorrectTypeException;
import printing_shop.domain.exceptions.RecordDoesNotExistException;

public interface ICustomerManager<CustomerType, AddRequestType>{
    CustomerType AddCustomer(AddRequestType request);
    Iterable<CustomerType> GetCustomers();
    CustomerType GetCustomer(String id) throws DatabaseInternalException, IdentifierNotParsableToCorrectTypeException, RecordDoesNotExistException;
    CustomerType UpdateCustomer(CustomerType customer);
    boolean DeleteCustomer(String id) throws DatabaseInternalException, RecordDoesNotExistException;
}