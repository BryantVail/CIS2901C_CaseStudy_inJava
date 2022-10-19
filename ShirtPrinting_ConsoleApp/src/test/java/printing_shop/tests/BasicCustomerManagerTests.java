package printing_shop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import printing_shop.BasicCustomerManager;
import printing_shop.ICustomerManager;
import printing_shop.ICustomerRepository;
import printing_shop.domain.AddCustomerRequest;
import printing_shop.domain.Customer;
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
          .when(this.customerRepository.GetCustomer(anyString()))
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
    public void ChangeDeletedStatus_given_deleted_customer_when_attempt_to_delete_then_DeletingDeletedRecordException_will_be_thrown(){
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
         .when(this.customerRepository.GetCustomer(anyString()))
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

}
