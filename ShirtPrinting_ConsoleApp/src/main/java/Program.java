

import printing_shop.BasicCustomerManager;
import printing_shop.consoleUI.ConsoleUI;
import printing_shop.ICustomerManager;
import printing_shop.ICustomerRepository;
import printing_shop.MySqlCustomerRepository;
import printing_shop.domain.AddCustomerRequest;
import printing_shop.domain.AddInventoryProduct;
import printing_shop.domain.InventoryProduct;
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
		
		while (true) {
			System.out.println(
				"Welcome to the Print shop manager!\n" +
					ConsoleUI.UnderscoreLine(10) +
					"\n" + ConsoleUI.UnderscoreLine(10) +
					"\n" + ConsoleUI.UnderscoreLine(10));
			
			System.out.println(
				"Select the letter corresponding with the Entity you would like to begin working with: \n\n" +
					"C for Customers\n" +
					"O for Orders\n" +
					"P for Products and Inventory\n");
			Scanner input = new Scanner(System.in);
			
			String value = input.nextLine().trim().toLowerCase();
			
			switch (value) {
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
		
		ICustomerManager<Customer, AddCustomerRequest> customerManager) {
		var input = new Scanner(System.in);
		
		while (true) {
			System.out.println(
				"Customer Actions: type the command to begin the process \n\n" +
					"\"Add\" for Adding a customer\n" +
					"\"GetAll\" for retrieving all the customers\n" +
					"\"GetByEmail\" to search by emailAddress\n" +
					"\"GetById\" to search for a customer by their Id\n" +
					"\"Deactivate\" to deactivate a customer in the system\n" +
					"\"Activate\" to activate an existing customer in the system\n" +
					"\"Update\" to update customer's information\n" +
					"\"exit\" to go back to previous menu");
			String value = input.nextLine().trim().toLowerCase();
			switch (value) {
				case "add": {
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
				}
				case "getall": {
					var customers
						= (Collection<Customer>) customerManager
						.GetCustomers();
					
					var customersArray = customers.toArray();
					for (int i = 0; i < customers.size(); i++) {
						printCustomer((Customer) customersArray[i]);
					}
					break;
				}
				case "getbyid": {
					System.out.println("Please enter customerId exactly");
					var id = input.nextLine();
					try {
						
						var customer = customerManager.get(id);
						printCustomer(customer);
						break;
					} catch (RecordDoesNotExistException exception) {
						System.out.println(
							"No record exists with the ID: " +
								id);
						break;
					} catch (DatabaseInternalException e) {
						System.out.println(
							"internal database error, not business logic");
						break;
					} catch (IdentifierNotParsableToCorrectTypeException exception) {
						break;
					}
				}
				case "update": {
					// outer loop, exit goes back to main customer menu
					while (true) {
						try {
							// get customer to update
							System.out.println("please enter customer email to select customer that you would like to update or \"exit\" to go to previous menu\n");
							
							String emailInput = input.nextLine().trim().toLowerCase();
							if (emailInput.compareTo("exit") == 0) {
								break;
							} else {
								var customer = customerManager.getByEmailAddress(emailInput);
								System.out.println("customer found: \n\n");
								printCustomer(customer);
								System.out.println("\n----------\n");
								
								while (true) {
									try{
										// list options on what to update
										System.out.println(
											"Aspects of Customer that can be updated: type the command to update \n\n" +
												"\"Email\" to change to email that is not used by another customer\n" +
												"\"First\" to change the first name\n" +
												"\"Last\" to change last name\n" +
												"\"exit\" to go back to previous menu");
										
										// evaluate input and ask for new value
										var userChoice = input.nextLine().trim().toLowerCase();
										if (userChoice.compareTo("exit") == 0) {
											break;
										} else {
											switch (userChoice) {
												case "email": {
													while (true) {
														try {
															// gather new value
															System.out.println("please enter what you would like for your new email address \nor enter \"exit\" to go back to the previous menu");
															var newEmail = input.nextLine().trim().toLowerCase();
															if (newEmail.compareTo("exit") == 0) {
																break;
															} else {
																var updatedCustomer =
																	customerManager.updateEmailAddress(customer.Id, newEmail);
																System.out.println("\n\nupdated customer:\n");
																printCustomer(updatedCustomer);
																break;
															}
														} catch (DatabaseInternalException exception) {
														
														} catch (IllegalArgumentException exception) {
															System.out.println(exception.getMessage());
														}
													} //end while(true)
													break;
												}
												case "first": {
													// gather new value
													while (true) {
														try {
															System.out.println("please enter what you would like for the new firstName on this account \nor Enter \"exit\" to go back to the previous menu");
															String newFirstName = input.nextLine().trim();
															if (newFirstName.compareTo("exit") == 0) {
																break;
															} else {
																Customer updatedCustomer =
																	customerManager.updateFirstName(customer.Id, newFirstName);
																System.out.println("\n\nupdated customer:\n");
																printCustomer(updatedCustomer);
																break;
															}
														} catch (DatabaseInternalException exception) {
															System.out.println("database error while doing update, please try again, if the problem persists please contact your administrator\n\n");
														} catch (IllegalArgumentException exception) {
															System.out.println("the first name entered is not valid or is the same value already present for firstName\n\n");
														}
													}
													break;
												}
												case "last": {
													while (true) {
														try {
															System.out.println("please enter what you would like for the new lastName on this account \nor Enter \"exit\" to go back to the previous menu");
															String newLastName = input.nextLine().trim();
															if (newLastName.compareTo("exit") == 0) {
																break;
															} else {
																var updatedCustomer =
																	customerManager.updateLastName(customer.Id, newLastName);
																System.out.println("\n\nupdated customer:\n");
																printCustomer(updatedCustomer);
																break;
															}
														} catch (DatabaseInternalException exception) {
															System.out.println("database error while doing update, please try again, if the problem persists please contact your administrator");
														} catch (IllegalArgumentException exception) {
															System.out.println(exception.getMessage()+"\n");
														}
													}// end while(true)
													break;
												}// end case "last":
												default: {
													System.out.println("command not recognized, please enter command again");
												}
											}
										}
									}catch(IllegalArgumentException exception){
										System.out.println(exception.getMessage()+"\n");
									}
								}// end while(true) loop
							}
						} catch (IdentifierNotParsableToCorrectTypeException exception) {
							System.out.println(exception.getMessage() + "\n\n");
							
						} catch (RecordDoesNotExistException exception) {
							System.out.println(exception.getMessage() + "\n\n");
						} catch (DatabaseInternalException exception) {
							System.out.println(exception.getMessage());
						}
					}// end outer while(true) loop
					break;
				}
				case "deactivate": {
					while (true) {
						System.out.println("Please enter email of customer to deactivate or \"back\" to go back");
						String emailAddress = input.nextLine().trim().toLowerCase();
						if (emailAddress.compareTo("back") == 0) {
							break;
						} else {
							try {
								var customerToDeactivate =
									customerManager.getByEmailAddress(
										emailAddress);
								var deactivatedCustomer =
									customerManager.changeDeletedStatus(
										customerToDeactivate.Id,
										true);
								System.out.println(
									"customer has been deactivated");
								printCustomer(deactivatedCustomer);
								break;
							} catch (RecordDoesNotExistException exception) {
								System.out.println("no customer found with the email address entered");
							} catch (DatabaseInternalException exception) {
								System.out.println(exception.getGenericMessage());
							} catch (AttemptingChangeOfDeletedStatusToCurrentStatusException exception) {
								System.out.println(exception.getGenericMessage());
								System.out.println("\n\n");
							} catch (Exception exception) {
								System.out.println("unknown application error");
							}
						}
					}
					break;
				}
				case "activate": {
					while (true) {
						System.out.println("Please enter email of customer to re-activate or \"back\" to go back");
						String emailAddress = input.nextLine().trim().toLowerCase();
						if (emailAddress.compareTo("back") == 0) {
							break;
						} else {
							try {
								var customerToDeactivate =
									customerManager.getByEmailAddress(emailAddress);
								var deactivatedCustomer =
									customerManager.changeDeletedStatus(
										customerToDeactivate.Id,
										false);
								System.out.println(
									"customer has been re-activated");
								printCustomer(deactivatedCustomer);
							} catch (RecordDoesNotExistException exception) {
								System.out.println("no customer found with the email address entered");
							} catch (DatabaseInternalException exception) {
								System.out.println(exception.getGenericMessage());
							} catch (AttemptingChangeOfDeletedStatusToCurrentStatusException exception) {
								System.out.println(exception.getGenericMessage());
								System.out.println("\n\n");
							} catch (Exception exception) {
								System.out.println("unknown application error");
							}
						}
					}
					break;
				}
				case "exit":
					return;
				default:
					System.out.println("input did not match a choice in the application, please try again\n\n");
				
			}
		}
	}
	
	public static void ManageBasicProducts(IProductManager<InventoryProduct, AddInventoryProduct> productManager){
	
	}
	public static void printCustomer(Customer customer) {
		System.out.println(
			"id: " + customer.Id + ", " +
				"email: " + customer.EmailAddress + ", " +
				"firstName: " + customer.FirstName + ", " +
				"lastName: " + customer.LastName + "\n" +
				ConsoleUI.UnderscoreLine(15));
	}
}
