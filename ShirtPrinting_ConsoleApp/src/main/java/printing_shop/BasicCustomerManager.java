package printing_shop;

import printing_shop.domain.AddCustomerRequest;
import printing_shop.domain.Customer;
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
        return customerRepository.getCustomers();
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
    public boolean DeleteCustomer(String id) {
        var customer = this.GetCustomer(id);
        boolean successful = false;
        try{
            successful = this.customerRepository.DeleteCustomer(id);
            return successful;
        }catch(Exception exception){
            return false;
        }
    }
}


