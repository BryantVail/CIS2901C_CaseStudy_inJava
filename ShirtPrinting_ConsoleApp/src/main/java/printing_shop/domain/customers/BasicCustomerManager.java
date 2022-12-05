package printing_shop.domain.customers;

import printing_shop.domain.exceptions.DatabaseInternalException;
import printing_shop.domain.exceptions.AttemptingChangeOfDeletedStatusToCurrentStatusException;
import printing_shop.domain.exceptions.RecordDoesNotExistException;
import printing_shop.utility.CommonRegex;
import printing_shop.utility.ILogger;

public class BasicCustomerManager
	implements ICustomerManager<Customer, AddCustomerRequest> {
	
	ICustomerRepository customerRepository;
	ILogger logger;
	
	public BasicCustomerManager(
		ICustomerRepository customerRepository,
		ILogger logger) {
		this.customerRepository = customerRepository;
		this.logger = logger;
	}
	
	@Override
	public Customer AddCustomer(AddCustomerRequest request) {
		
		return (Customer) customerRepository.add(request);
	}
	
	@Override
	public Iterable<Customer> GetCustomers() {
		
		return customerRepository.getAsync();
	}
	
	
	@Override
	public Customer get(String id) {
		
		return (Customer) customerRepository.getAsync(id);
	}
	
	@Override
	public Customer updateEmailAddress(String id, String newEmailAddress) {
		if(CommonRegex.isValidEmailAddress(newEmailAddress)) {
			try {
				var customer = this.getByEmailAddress(newEmailAddress);
				throw new IllegalArgumentException(
					"email is already actively in use in the system, please use a different email");
			} catch (RecordDoesNotExistException exception) {
				return (Customer) this.customerRepository.updateEmailAddress(id, newEmailAddress);
			}
		}
		else{
			throw new IllegalArgumentException("email address "+newEmailAddress+" is not valid");
		}
	}
	
	@Override
	public Customer updateFirstName(String id, String newFirstName)
	throws DatabaseInternalException, RecordDoesNotExistException, IllegalArgumentException{
		
		if(newFirstName.isEmpty()){
			throw new IllegalArgumentException("new first name cannot be an empty string");
		}else{
			// get record
			var customer = this.get(id);
			// compare first name to new first name
			if(customer.FirstName.compareTo(newFirstName) == 0){
				throw new IllegalArgumentException("existing firstName and proposed new firstName are the same");
			}else{
				return (Customer) this.customerRepository.updateFirstName(id, newFirstName);
			}
		}
	}
	
	@Override
	public Customer updateLastName(String id, String newLastName)
	throws IllegalArgumentException, DatabaseInternalException{
		if(newLastName.isEmpty()){
			throw new IllegalArgumentException("new lastName cannot be an empty string");
		}else{
			// check if new is the same as existing
			var existingCustomer = this.get(id);
			if(existingCustomer.LastName.compareTo(newLastName) == 0){
				throw new IllegalArgumentException("existing lastName and proposed new lastName are the same");
			}else{
				return (Customer) this.customerRepository.updateLastName(id, newLastName);
			}
		}
	}
	
	@Override
	public Customer changeDeletedStatus(String id, boolean changeToDeleted)
	throws DatabaseInternalException, RecordDoesNotExistException, AttemptingChangeOfDeletedStatusToCurrentStatusException {
		
		var customer = this.get(id);
		boolean initialDeletedStatus = customer.Deleted;
		if (customer.Deleted == changeToDeleted) {
			throw new AttemptingChangeOfDeletedStatusToCurrentStatusException();
		} else {
			Customer deletedCustomer =
				(Customer) this.customerRepository.changeDeletedStatus(
					id,
					changeToDeleted);
			if (deletedCustomer.Deleted != initialDeletedStatus) {
				return deletedCustomer;
			} else {
				throw new DatabaseInternalException(
					"deleted status not updated");
			}
		}
	}
	
	@Override
	public Customer getByEmailAddress(String emailAddress) {
		return (Customer) this.customerRepository.getByEmailAddress(emailAddress);
		
	}
}


