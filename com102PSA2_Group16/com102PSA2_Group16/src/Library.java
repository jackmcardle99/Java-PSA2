import classes.*;
import classes.FileIO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 *
 * @authors 
 * Jack McArdle - B00733578
 * Mark Allison - B00847098
 */

public class Library {
    
    private Scanner scan;
    
    FileIO file  = new FileIO();
    Loans loan = new Loans();
    Users user = new Users();



    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        // TODO code application logic here
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
    
    private void writeOnExit() throws IOException
    {
       file.writeNewLoan(); //method to call on program termination to write to the files
    }
            
    private void menu() throws FileNotFoundException, IOException, Exception {
        
        boolean response = true;
        int userInput;
        scan = new Scanner(System.in);
        
        //basic structure for user input for user to use within the terminal
        while (response) {
            System.out.println("\nWhat would you like to do?\n");
            System.out.println("""
                               (1) TESTING NEW METHODS (PLACEHOLDER) 
                               (2) View all items 
                               (3) View all users 
                               (4) View items on loan 
                               (5) Issue new loan 
                               (6) Renew Loan
                               (7) Return Loan
                               (8) Refresh Library
                               (9) Stop program
                               """); 
            
            userInput = scan.nextInt();
            
            switch (userInput) {
                case 1: userInput = 1;
//                    loan.returnLoan();
                    break;
                case 2: userInput = 2;
                    file.printItemSummary();
                    break;
                case 3: userInput = 3;
                    file.printUserSummary();
                    break;
                case 4: userInput = 4;
                    file.printLoanSummary();                   
                    break;
                case 5: userInput = 5;
                    loan.loanEligibility();
                    break;  
                case 6: userInput = 6;
                    loan.renewLoanEligibility();
                    break;
                case 7:
                    loan.returnLoan();
                    break;
                case 8: userInput = 7;
                    this.initialRead();
                    break;
                case 9: userInput = 8;
                    this.writeOnExit();
                    //loan.createLoan(loan.getLoanType());    
                    
                    System.out.println("\nApplication Terminated");
                    response = false;
                

                //ADD SLEEP TIMER FOR INPUT 2/3 second                              
            }
        }    
    }
}
