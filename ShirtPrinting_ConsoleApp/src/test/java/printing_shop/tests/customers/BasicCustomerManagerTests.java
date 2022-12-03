package printing_shop.tests.customers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import printing_shop.domain.customers.BasicCustomerManager;
import printing_shop.domain.customers.ICustomerManager;
import printing_shop.domain.customers.ICustomerRepository;
import printing_shop.domain.customers.AddCustomerRequest;
import printing_shop.domain.customers.Customer;
import printing_shop.domain.exceptions.DatabaseInternalException;
import printing_shop.domain.exceptions.AttemptingChangeOfDeletedStatusToCurrentStatusException;
import printing_shop.utility.ILogger;

import java.time.LocalDateTime;
// statics
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;

public class BasicCustomerManagerTests {

    private final
    ICustomerRepository<Customer, AddCustomerRequest> customerRepository;

    public BasicCustomerManagerTests(){
        this.customerRepository = Mockito.mock(
            ICustomerRepository.class);
    }

    @Test
    public void changeDeletedStatus_given_active_customer_when_attempt_to_delete_returns_customer_with_the_same_status_then_throws_DatabaseInternalException(){
        // arrange
        Customer testCustomerActive = new Customer(
          "1",
          LocalDateTime.now(),
          false,
          "",
          "",
          "",
          LocalDateTime.now());

        Mockito
          .when(this.customerRepository.getAsync(anyString()))
          .thenReturn(testCustomerActive);
        Mockito
          .when(this.customerRepository.changeDeletedStatus(
              anyString(),
              anyBoolean()))
          .thenReturn(testCustomerActive);

        ICustomerManager<Customer, AddCustomerRequest> basicCustomerManger =
          new BasicCustomerManager(
          this.customerRepository,
          Mockito.mock(ILogger.class));

        // act
        // assert
        Assertions.assertThrows(
            DatabaseInternalException.class,
            () -> basicCustomerManger.changeDeletedStatus(
              anyString(),
              true));
    }
    
    @Test
    public void ChangeDeletedStatus_given_deleted_customer_when_attempt_to_delete_then_DeletingDeletedRecordException_will_be_thrown() {
        
        // arrange
        Customer testCustomerDeleted = new Customer(
          "1",
          LocalDateTime.now(),
          true,
          "",
          "",
          "",
          LocalDateTime.now());
        
        Mockito
          .when(this.customerRepository.getAsync(anyString()))
          .thenReturn(testCustomerDeleted);
        
        ICustomerManager<Customer, AddCustomerRequest> basicCustomerManger =
          new BasicCustomerManager(
            this.customerRepository,
            Mockito.mock(ILogger.class));
        
        // act
        // assert
        Assertions.assertThrows(
          AttemptingChangeOfDeletedStatusToCurrentStatusException.class,
          () -> basicCustomerManger.changeDeletedStatus(
            anyString(),
            true));
    }

    // UPDATE METHODS
    @Test
    public void updateEmailAddress_given_a_non_null_string_when_the_email_is_invalid_then_returns_argument_exception(){
        // arrange
        ICustomerManager<Customer, AddCustomerRequest> basicCustomerManager =
          new BasicCustomerManager(
            this.customerRepository,
            Mockito.mock(ILogger.class));
            
        // act
        // assert
    }
    
    @Test
    public void UpdateEmailAddress_given_a_valid_email_address_when_the_email_is_already_used_by_an_existing_account_then_throw_argument_exception(){
        // arrange
        String existingEmailAddress = "email@email.com";
        var emailAlreadyExistsCustomer = new Customer(
          "1111111",
          LocalDateTime.now().minusDays(20),
          false,
          existingEmailAddress,
          "first",
          "last",
          LocalDateTime.now().minusDays(10));
        Mockito.when(this.customerRepository.getByEmailAddress(anyString())).thenReturn(emailAlreadyExistsCustomer);
        
        ICustomerManager<Customer, AddCustomerRequest> basicCustomerManager =
          new BasicCustomerManager(
            this.customerRepository,
            Mockito.mock(ILogger.class));
        
        // act
        // assert
        Assertions.assertThrows(
          IllegalArgumentException.class,
          () -> basicCustomerManager.updateEmailAddress(
          anyString(),
          existingEmailAddress));
    }
}
