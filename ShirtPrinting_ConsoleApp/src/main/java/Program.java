

import printing_shop.BasicCustomerManager;
import printing_shop.ConsoleUI.ConsoleUI;
import printing_shop.ICustomerManager;
import printing_shop.ICustomerRepository;
import printing_shop.MySqlCustomerRepository;
import printing_shop.domain.AddCustomerRequest;
import printing_shop.domain.Customer;
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

            switch(value){
                case "C":
                    ManageCustomers(customerManager);
                case "O":
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
                            "\"exit\" to go back to previous menu");
            String value = input.nextLine();
            switch(value){
                case "Add":
                    System.out.println("please enter the email for the customer");
                    AddCustomerRequest request = new AddCustomerRequest();
                    request.EmailAddress = input.nextLine();

                    System.out.println("enter the firstName of the customer");
                    request.FirstName = input.nextLine();

                    System.out.println("now please enter the last name of the customer");
                    request.LastName = input.nextLine();

                    var customer = customerManager.AddCustomer(request);
                    printCustomer(customer);
                    break;

                case "GetAll":
                    var customers
                            = (Collection<Customer>)customerManager
                            .GetCustomers();

                    var customersArray = customers.toArray();
                    for(int i=0; i < customers.size(); i++){
                        printCustomer((Customer) customersArray[i]);
                    }
                    break;
                case "GetById":
                    System.out.println("Please enter customerId exactly");
                    var id = input.nextLine();

                    customer = customerManager.GetCustomer(id);
                    printCustomer(customer);
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
