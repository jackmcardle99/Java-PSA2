import classes.*;
import classes.FileIO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 *
 * @authors 
 * Jack McArdle - B00733578 - mcardle-j9@ulster.ac.uk
 * Mark Allison - B00847098 - allison-m2@ulster.ac.uk
 */

//Main class of program, repsonsible for program menu, and other vital methods

public class Library {
    
    //declaring class variables and instatiating objects for other classes
    private Scanner scan;    
    FileIO file  = new FileIO();
    Loans loan = new Loans();
    Users user = new Users();
    Items item = new Items();

    public static void main(String[] args) throws FileNotFoundException, IOException, Exception 
    {
        Library lib = new Library();     
        lib.start();    
    }
    
    private void start() throws Exception
    {
        Library lib = new Library();  
        lib.initialRead(); //on start read files into arraylists
        lib.menu(); //calls upon the menu method to allow user to access the program
    }
    private void initialRead() throws IOException, Exception
    {
        file.readItems(); //allows to read files into arraylists
        file.readUsers();
        file.readLoans();
    }
            
    private void menu() throws FileNotFoundException, IOException, Exception {
        int x = 0;
        boolean response = true;
        String userInput;
        scan = new Scanner(System.in);
        
        //basic structure for user input for user to use within the terminal
        while (response) {
            System.out.println("\nWhat would you like to do?\n");
            System.out.println(""" 
                               (1) View all items 
                               (2) View all users 
                               (3) View items on loan 
                               (4) Issue new loan 
                               (5) Renew Loan
                               (6) Return Loan
                               (7) Stop program
                               """);   
            userInput = scan.nextLine();          

            try {
            x = Integer.parseInt(userInput);
            if (x < 1 || x > 7) {
                System.out.println("Please enter a valid response (1-7)"); 
            } 
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid response (1-7)"); 
                }
            
            switch (x) {
                case 1: x = 1;               
                    item.printItemSummary();  
                    break;
                case 2: x = 2;
                    user.printUserSummary();
                    break;
                case 3: x = 3;
                    loan.printLoanSummary();
                    break;
                case 4: x = 4;
                    loan.createLoan();                   
                    break;
                case 5: x = 5;
                    loan.renewLoan();
                    break;  
                case 6: x = 6;
                    loan.returnLoan();
//                    loan.testing();
                    break;
                case 7: x = 7;
                    file.writeToFile();
                    System.out.println("\nApplication Terminated");
                    response = false;                         
            }
        }    
    }
}
