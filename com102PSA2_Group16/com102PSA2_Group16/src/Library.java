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
    
    FileIO file = new FileIO();
    //


    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        // TODO code application logic here
        Library lib = new Library();
        
        lib.menu();
    }
    
    
    private void menu() throws FileNotFoundException, IOException, Exception {
        
        boolean respone = true;
        int userInput;
        scan = new Scanner(System.in);
        
        while (respone) {
            System.out.println("What would you like to do?");
            System.out.println("(1) View items on loan \n"
                             + "(2) View all items \n"
                             + "(3) View all users \n" 
                             + "(4) Date \n"
                             + "(5) Stop Program ");
            
            userInput = scan.nextInt();
            
          /* Important!!! repeating input will duplicate file length, e.g. pressing 3 two times will double length
            */ 
            switch (userInput) {
                case 1: userInput = 1;
                    break;
                case 2: userInput = 2;
                    file.readItems();
                    file.printItemSummary();
                    break;
                case 3: userInput = 3;
                    file.readUsers();
                    file.printUserSummary();
                    break;
                case 4: userInput = 4;
                    file.readLoans();
                    file.printLoanSummary();                   
                    break;                   
                case 5: userInput = 5;
                System.out.println("Application Terminated");
                respone = false;
                

                //ADD SLEEP TIMER FOR INPUT                                
            }
        }    
    }
}
