package printing_shop;

public interface ICustomerRepository<CustomerType, AddRequestType> {
	CustomerType AddCustomer(AddRequestType request);
	
	Iterable<CustomerType> getAsync();
	
	CustomerType getAsync(String id);
	
	CustomerType getByEmailAddress(String emailAddress);
	
	
	CustomerType changeDeletedStatus(String id, boolean changeToDeleted);
  
  
  CustomerType updateEmailAddress(String id, String newEmailAddress);
  
  CustomerType updateFirstName(String id, String firstName);
  
  CustomerType updateLastName(String id, String lastName);
}