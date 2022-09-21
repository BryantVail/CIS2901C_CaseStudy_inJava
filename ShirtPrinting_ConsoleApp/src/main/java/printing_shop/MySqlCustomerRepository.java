package printing_shop;

import printing_shop.domain.AddCustomerRequest;
import printing_shop.domain.Customer;

import java.io.NotActiveException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.SQLException;
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
                    connection.prepareCall("{call GetCustomerBy_EmailAddress_FirstName_And_LastName(?, ?, ?)}");
            getCustomerByEmailAndLastName_callableStatement.setString(1, request.EmailAddress);
            getCustomerByEmailAndLastName_callableStatement.setString(2, request.FirstName);
            getCustomerByEmailAndLastName_callableStatement.setString(3, request.LastName);

            var resultSet =  getCustomerByEmailAndLastName_callableStatement.executeQuery();


            if(resultSet.next()){
                var customer =  new Customer(
                        String.valueOf(resultSet.getInt("Id")),
                        resultSet.getString("emailAddress"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"));
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
    public Iterable<Customer> getCustomers() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection =
                    DriverManager.getConnection(this.connectionString);

            // run stored procedure with no output parameters to get data
            var callableStatement = connection.prepareCall("{call GetAllCustomers}");
            var resultSet = callableStatement.executeQuery();
            ArrayList<Customer> customers = new ArrayList<>();

            while(resultSet.next()){
                customers.add(new Customer(
                        String.valueOf(resultSet.getInt("Id")),
                        resultSet.getString("emailAddress"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName") ));
            }

            return customers;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer GetCustomer(String id) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection =
                    DriverManager.getConnection(this.connectionString);

            // run stored procedure to get return data
            var callableStatement =
                    connection.prepareCall("{call GetCustomerById(?)}");
            callableStatement.setInt("id", Integer.valueOf(id));
            var resultSet = callableStatement.executeQuery();

            if(resultSet.next()){
                var customer = new Customer(
                        String.valueOf(resultSet.getInt("Id")),
                        resultSet.getString("EmailAddress"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"));

                connection.close();
                return customer;

            }else{
                throw new SQLException(
                        "stored procedure \"GetCustomerById(?)\" did not return any records");
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer UpdateCustomer(Customer customer) {
        return null;
    }

    @Override
    public boolean DeleteCustomer(String id) {
        return false;
    }
}
