

import printing_shop.BasicCustomerManager;
import printing_shop.consoleUI.ConsoleUI;
import printing_shop.ICustomerManager;
import printing_shop.ICustomerRepository;
import printing_shop.MySqlCustomerRepository;
import printing_shop.domain.AddCustomerRequest;
import printing_shop.domain.Customer;
import printing_shop.domain.exceptions.*;
import printing_shop.utility.BasicLogger;
import printing_shop.utility.ILogger;


import java.util.Collection;
import java.util.Scanner;

public class Program {


    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
//        ShopApplication app = new ShopApplication(
//                BasicCustomerManager
//        );
        String connectionString =
                "jdbc:mysql://root:@localhost:3306/printshop_eatsleepprint";
        ICustomerRepository customerRepository =
                new MySqlCustomerRepository(connectionString);
        ILogger logger = new BasicLogger(connectionString);
        ICustomerManager customerManager = new BasicCustomerManager(
                customerRepository,
                logger);

        while(true){
            System.out.println(
                    "Welcome to the Print shop manager!\n" +
                    ConsoleUI.UnderscoreLine(10) +
                    "\n"+ ConsoleUI.UnderscoreLine(10)+
                    "\n"+ ConsoleUI.UnderscoreLine(10));

            System.out.println(
                    "Select the letter corresponding with the Entity you would like to begin working with: \n\n" +
                    "C for Customers\n" +
                    "O for Orders\n" +
                    "P for Products and Inventory\n");
            Scanner input = new Scanner(System.in);

            String value = input.nextLine();

            switch(value.toLowerCase()){
                case "c":
                    ManageCustomers(customerManager);
                    System.out.println(ConsoleUI.UnderscoreLine(10));
                    break;
                case "o":
                    System.out.println(
                            "I'm sorry, this feature is not ready");
                    break;
                case "p":
                    System.out.println(
                            "I'm sorry, this feature is not ready");
                    break;
                default:
                    break;
            }


        }
    }

    public static void ManageCustomers(
            ICustomerManager<Customer, AddCustomerRequest> customerManager){
        var input = new Scanner(System.in);

        while(true){
            System.out.println(
                "Customer Actions: type the command to begin the process \n\n" +
                    "\"Add\" for Adding a customer\n" +
                    "\"GetAll\" for retrieving all the customers\n"+
                    "\"GetById\" to search for a customer by their Id\n" +
                    "\"GetByEmail\" to search by emailAddress\n"+
                    "\"Deactivate\" to deactivate a customer in the system\n"+
                    "\"Activate\" to activate an existing customer in the system\n"+
                    "\"exit\" to go back to previous menu");
            String value = input.nextLine();
            switch(value.toLowerCase()){
                case "add":
                    AddCustomerRequest request =
                            new AddCustomerRequest();
                    System.out.println(
                        "please enter the email for the customer");

                    request.EmailAddress = input.nextLine();

                    System.out.println(
                        "enter the firstName of the customer");
                    request.FirstName = input.nextLine();

                    System.out.println(
                        "now please enter the last name of the customer");
                    request.LastName = input.nextLine();

                    var customer = customerManager.AddCustomer(request);
                    printCustomer(customer);
                    break;

                case "getall":
                    var customers
                            = (Collection<Customer>)customerManager
                            .GetCustomers();

                    var customersArray = customers.toArray();
                    for(int i=0; i < customers.size(); i++){
                        printCustomer((Customer) customersArray[i]);
                    }
                    break;
                case "getbyid":

                    System.out.println("Please enter customerId exactly");
                    var id = input.nextLine();
                    try{

                        customer = customerManager.GetCustomer(id);
                        printCustomer(customer);
                    }catch (RecordDoesNotExistException exception){
                        System.out.println(
                            "No record exists with the ID: "+
                            id);
                    } catch (DatabaseInternalException e) {
                        System.out.println(
                            "internal database error, not business logic");
                    } catch(IdentifierNotParsableToCorrectTypeException exception){

                    }
                case "deactivate":
                    while(true){
                        System.out.println("Please enter email of customer to deactivate or \"back\" to go back");
                        String emailAddress = input.nextLine();
                        if(emailAddress.toLowerCase().compareTo("back") == 0){
                            break;
                        }else{
                            try{
                                var customerToDeactivate =
                                    customerManager.getCustomerByEmail(
                                      emailAddress.toLowerCase());
                                var deactivatedCustomer =
                                 customerManager.changeDeletedStatus(
                                    customerToDeactivate.Id,
                                    true);
                                System.out.println(
                                  "customer has been deactivated");
                                printCustomer(deactivatedCustomer);
                            }catch(RecordDoesNotExistException exception){
                                System.out.println("no customer found with the email address entered");
                            }catch(DatabaseInternalException exception){
                                System.out.println(exception.getGenericMessage());
                            }catch(AttemptingChangeOfDeletedStatusToCurrentStatusException exception){
                                System.out.println(exception.getGenericMessage());
                                System.out.println("\n\n");
                            }catch(Exception exception){
                                System.out.println("unknown application error");
                            }
                        }
                    }
                    break;

                case "activate":
                    while(true){
                        System.out.println("Please enter email of customer to re-activate or \"back\" to go back");
                        String emailAddress = input.nextLine();
                        if(emailAddress.toLowerCase().compareTo("back") == 0){
                            break;
                        }else{
                            try{
                                var customerToDeactivate =
                                  customerManager.getCustomerByEmail(emailAddress);
                                var deactivatedCustomer =
                                  customerManager.changeDeletedStatus(
                                    customerToDeactivate.Id,
                                    false);
                                System.out.println(
                                  "customer has been re-activated");
                                printCustomer(deactivatedCustomer);
                            }catch(RecordDoesNotExistException exception){
                                System.out.println("no customer found with the email address entered");
                            }catch(DatabaseInternalException exception){
                                System.out.println(exception.getGenericMessage());
                            }catch(AttemptingChangeOfDeletedStatusToCurrentStatusException exception){
                                System.out.println(exception.getGenericMessage());
                                System.out.println("\n\n");
                            }catch(Exception exception){
                                System.out.println("unknown application error");
                            }
                        }
                    }
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("input did not match a choice in the application, please try again\n\n");

            }
        }
    }

    public static void printCustomer(Customer customer){
        System.out.println(
            "id: "+customer.Id+", "+
            "email: "+customer.EmailAddress+", "+
            "firstName: "+customer.FirstName+", "+
            "lastName: "+customer.LastName+"\n" +
            ConsoleUI.UnderscoreLine(15));
    }
}
