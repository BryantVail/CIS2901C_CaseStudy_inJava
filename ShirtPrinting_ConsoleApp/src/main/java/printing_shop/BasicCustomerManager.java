package printing_shop;

import printing_shop.domain.AddCustomerRequest;
import printing_shop.domain.Customer;
import printing_shop.domain.exceptions.DatabaseInternalException;
import printing_shop.domain.exceptions.AttemptingChangeOfDeletedStatusToCurrentStatusException;
import printing_shop.domain.exceptions.RecordDoesNotExistException;
import printing_shop.utility.ILogger;

public class BasicCustomerManager
        implements ICustomerManager<Customer, AddCustomerRequest> {

    ICustomerRepository customerRepository;
    ILogger logger;
    public BasicCustomerManager(
            ICustomerRepository customerRepository,
            ILogger logger){
        this.customerRepository = customerRepository;
        this.logger = logger;
    }
    @Override
    public Customer AddCustomer(AddCustomerRequest request) {

        return (Customer) customerRepository.AddCustomer(request);
    }

    @Override
    public Iterable<Customer> GetCustomers() {

        return customerRepository.getAsync();
    }


    @Override
    public Customer GetCustomer(String id) {

        return (Customer) customerRepository.GetCustomer(id);
    }

    @Override
    public Customer UpdateCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer changeDeletedStatus(String id, boolean changeToDeleted)
            throws DatabaseInternalException, RecordDoesNotExistException, AttemptingChangeOfDeletedStatusToCurrentStatusException {

        var customer = this.GetCustomer(id);
        boolean initialDeletedStatus = customer.Deleted;
        if(customer.Deleted == changeToDeleted){
            throw new AttemptingChangeOfDeletedStatusToCurrentStatusException();
        }else{
            Customer deletedCustomer = (Customer) this.customerRepository.changeDeletedStatus(
                id, changeToDeleted);
            if(deletedCustomer.Deleted != initialDeletedStatus){
                return deletedCustomer;
            }else{
                throw new DatabaseInternalException(
                    "deleted status not updated");
            }
        }
    }

    @Override
    public Customer getCustomerByEmail(String emailAddress) {
        return (Customer) this.customerRepository.getByEmailAddress(emailAddress);
        
    }
}


