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
        lib.initialRead();
        lib.menu();
    }
    private void initialRead() throws IOException, Exception
    {
        file.readItems();
        file.readUsers();
        file.readLoans();
    }
    
    private void menu() throws FileNotFoundException, IOException, Exception {
        
        boolean response = true;
        int userInput;
        scan = new Scanner(System.in);
        
        while (response) {
            System.out.println("\nWhat would you like to do?\n");
            System.out.println("(1) N/A \n"
                             + "(2) View all items \n"
                             + "(3) View all users \n" 
                             + "(4) View items on loan \n"
                             + "(5) Issue new loan \n"
                             + "(6) Renew Loan\n"
                             + "(7) Stop program\n"); 
            
            userInput = scan.nextInt();
            
            switch (userInput) {
                case 1: userInput = 1;
                   // loan.addTime();
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
//                    file.clearArray();
//                    this.initialRead();
                    break;  
                case 6: userInput = 6;
                    loan.renewLoan(loan.renewLoanEligibility());
                    break;
                case 7: userInput = 7;
                    loan.createLoan(loan.getLoanType());
                
                    System.out.println("\nApplication Terminated");
                    response = false;
                

                //ADD SLEEP TIMER FOR INPUT                                
            }
        }    
    }
}
