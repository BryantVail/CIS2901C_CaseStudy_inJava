package printing_shop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import printing_shop.MySqlCustomerRepository;
import printing_shop.domain.AddCustomerRequest;
import printing_shop.domain.Customer;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MySqlCustomerRepositoryTests {

    private final String connectionString;

    public MySqlCustomerRepositoryTests(){
        this.connectionString = "jdbc:mysql://root:@localhost:3306/printshop_eatsleepprint";

    }

    @Test void
    AddCustomer_given_valid_input_when_database_aligns_with_inline_sqlCommands_runs_without_exception_and_returns_record_that_was_created(){
        // arrange
        var mySqlCustomerRepository = new MySqlCustomerRepository(
                connectionString);
        String emailAddress = "email_"+ LocalDateTime.now().toString()+"@gmail.com";
        // act
        var customer = mySqlCustomerRepository.AddCustomer(new AddCustomerRequest(
                emailAddress,
                "firstName",
                "lastName"
        ));
        // assert
        Assertions.assertEquals(customer.EmailAddress, emailAddress);
        Assertions.assertTrue(Integer.valueOf(customer.Id) > 10000);
    }


    @Test
    void GetCustomer_by_id__given_a_known_id_returns_row_with_expected_email() {

        // arrange
        var mySqlCustomerRepository =
                new MySqlCustomerRepository(connectionString);
        int id = 1020304;

        // act
        var customer = mySqlCustomerRepository.GetCustomer(String.valueOf(id));

        // assert
        Assertions.assertEquals("bryanttv@outlook.com", customer.EmailAddress);

    }

    @Test void GetCustomers_given_an_active_database_returns_more_than_10_records(){
        // arrange
        var mySqlCustomerRepository
                = new MySqlCustomerRepository(connectionString);

        // act
        var customers = mySqlCustomerRepository.getCustomers();

        // assert
        Assertions.assertTrue(((Collection<Customer>)customers).size() > 10);
    }

    @Test
    void updateCustomer() throws Exception {
        throw new Exception();
    }

    @Test
    void deleteCustomer() throws Exception {
        throw new Exception();
    }
}