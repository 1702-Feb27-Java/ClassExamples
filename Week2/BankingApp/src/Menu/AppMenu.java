package Menu;
import java.util.Scanner;

import Services.CustomerService;


public class AppMenu {
	static Scanner reader= new Scanner(System.in);
      public static void main(String[] args) {
    	  MainMenu();
      }
      
      public static void MainMenu(){
    	  System.out.println(" Enter 1: To create a new Account");
    	  System.out.println(" Enter 2: To login");
    	  System.out.println("Eter any anything else to quit to the application");
    	  System.out.print(" Please make a choice:");
    	  int choice= Integer.parseInt(reader.nextLine());
    	  switch(choice){
    	  case 1: 
    		  // account creation Menu
    		  customerSignUp();
    	  case 2: 
    		  // login Menu
    	  default:
    		  System.out.println("Thank you for using our bank");
    		  System.exit(0);
    	  
    	  }
      }
      
      public static void customerSignUp(){
    	  System.out.print("Enter your first Name:");
    	  String firstName=reader.nextLine();
    	  System.out.println("Enter your last name");
    	  String lastName=reader.nextLine();
    	  System.out.print("Enter your username");
    	  String username=reader.nextLine();
    	  System.out.print("Enter your password");
    	  String password=reader.nextLine();
    	  
    	  CustomerService newCustomer= new CustomerService();
    	  newCustomer.signUp(firstName, lastName, username, password);
    	 
    	 System.out.println(" Thank you for creating a  new account"); 
    	 System.out.println(" Please login to to open an account"); 
      }

	
}
