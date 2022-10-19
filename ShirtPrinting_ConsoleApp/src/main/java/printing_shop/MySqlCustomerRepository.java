package printing_shop;

import printing_shop.domain.AddCustomerRequest;
import printing_shop.domain.Customer;
import printing_shop.domain.exceptions.DatabaseInternalException;
import printing_shop.domain.exceptions.IdCannotBeParsedToCorrectTypeException;
import printing_shop.domain.exceptions.RecordDoesNotExistException;
import printing_shop.utility.MySqlTypeConverter;

import java.io.NotActiveException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MySqlCustomerRepository
        implements ICustomerRepository<Customer, AddCustomerRequest> {
    private final String connectionString;
    private final String databasePassword;
    private final String databaseUser;

    public MySqlCustomerRepository(String connectionString){
        this.connectionString = connectionString;
        this.databaseUser = "root";
        this.databasePassword = "";

    }

    @Override
    public Customer AddCustomer(AddCustomerRequest request) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    this.connectionString);

            // addCustomer
            var callableStatement = connection.prepareCall("{call AddCustomer(?, ?, ?)}");
            callableStatement.setString(1, request.EmailAddress);
            callableStatement.setString(2, request.FirstName);
            callableStatement.setString(3, request.LastName);
            callableStatement.execute();

            // getCustomer after creating bc I haven't found how to do it in 1 call w/mySql
            var getCustomerByEmailAndLastName_callableStatement =
                    connection.prepareCall("{call customers_getByEmailAddress(?)}");
            getCustomerByEmailAndLastName_callableStatement.setString(1, request.EmailAddress);

            var resultSet =
              getCustomerByEmailAndLastName_callableStatement.executeQuery();


            if(resultSet.next()){
                var customer =  createCustomer(resultSet);
                // !!close!! //
                connection.close();
                return customer;
            }else{
                throw new SQLException("no arguments returned from Stored Procedure");
            }


        }
        catch(ClassNotFoundException exception){
            System.out.println("jdbc class not found");
            throw new RuntimeException("jdbc class not found");
        }
        catch(SQLException exception){
            throw new IllegalArgumentException(exception.getMessage());

        }

    }

    @Override
    public Iterable<Customer> getAsync() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection =
                    DriverManager.getConnection(this.connectionString);

            // run stored procedure with no output parameters to get data
            var callableStatement = connection.prepareCall("{call GetAllCustomers}");
            var resultSet = callableStatement.executeQuery();
            ArrayList<Customer> customers = new ArrayList<>();

            while(resultSet.next()){
                customers.add(createCustomer(resultSet));
            }

            return customers;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer GetCustomer(String id) throws RecordDoesNotExistException, DatabaseInternalException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection =
                    DriverManager.getConnection(this.connectionString);

            // run stored procedure to get return data
            var callableStatement =
                    connection.prepareCall("{call GetCustomerById(?)}");
            callableStatement.setInt("id", Integer.valueOf(id));
            var resultSet = callableStatement.executeQuery();

            boolean resultSetHasAtLeastOneValue = resultSet.next();
            if(resultSetHasAtLeastOneValue
                &
                String.valueOf(resultSet.getInt("id")).compareTo(id) == 0) {
                var customer = createCustomer(resultSet);

                connection.close();
                return customer;
            }else if(String.valueOf(resultSet.getInt("id")).compareTo(id) != 0){
                throw new RecordDoesNotExistException(id);
            }else{
                throw new SQLException(
                        "stored procedure \"GetCustomerById(?)\" did not return any records");
            }

        } catch (ClassNotFoundException e) {
            throw new DatabaseInternalException(e.getMessage()+"\n\nconfiguration or connection problem @ object_Not_Found");
        } catch (SQLException e) {
            throw new DatabaseInternalException(e.getMessage());
        }
    }

    @Override
    public Customer getByEmailAddress(String emailAddress) throws RecordDoesNotExistException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(this.connectionString);
            var callableStatement =
                    connection.prepareCall("{call customers_getByEmailAddress(?)}");
            callableStatement.setString("emailAddress", emailAddress.toLowerCase());
            var resultSet = callableStatement.executeQuery();

            if(resultSet.next()){
                var customer = createCustomer(resultSet);
                connection.close();
                if(
                  customer.EmailAddress.toLowerCase().compareTo(emailAddress.toLowerCase()) != 0){
                    throw new DatabaseInternalException("incorrect retrieval");
                }else{
                    connection.close();
                    return customer;
                }
            }else{
                throw new RecordDoesNotExistException("no customer with this email exists as a customer");
            }

        } catch (ClassNotFoundException e) {
            throw new DatabaseInternalException(e.getMessage());
        } catch (SQLException e) {
            throw new DatabaseInternalException(e.getMessage());
        }
    }

    @Override
    public Customer UpdateCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer changeDeletedStatus(String id, boolean changeToDeleted) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            int idOfInt = Integer.parseInt(id);

            Connection connection =
                DriverManager.getConnection(this.connectionString);
            
            var callableStatement =
                connection.prepareCall("{call customers_changeDeletedStatus(?, ?)}");
            callableStatement.setInt("customerId", idOfInt);
            callableStatement.setBoolean("changeToDeleted", changeToDeleted);
            var resultSet = callableStatement.executeQuery();

            resultSet.next();
            var customer =  createCustomer(resultSet);
            connection.close();
            return customer;

        } catch (ClassNotFoundException e) {
            throw new DatabaseInternalException(
                e.getMessage()+
                "\n\n\"Class.forName(com.mysql.cj.jdbc.Driver\" failed during runtime");
        }catch(NumberFormatException exception){
            throw new IdCannotBeParsedToCorrectTypeException(
                exception.getMessage(),
                id );
        } catch (SQLException e) {
            throw new DatabaseInternalException(e.getMessage());
        }
    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(
                String.valueOf(resultSet.getInt("Id")),
                MySqlTypeConverter.getLocalDateTime(resultSet.getString(
                        "createdDate")),
                resultSet.getBoolean("deleted"),
                resultSet.getString("emailAddress"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                MySqlTypeConverter.getLocalDateTime(
                        resultSet.getString("lastUpdated")) );
    }
}
