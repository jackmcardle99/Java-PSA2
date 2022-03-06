import classes.Items;
import classes.fileIO;
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
    
    fileIO fileHandling = new fileIO();


    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        Library lib = new Library();
        
        lib.menu();
    }
    
    
    private void menu() throws FileNotFoundException, IOException {
        
        boolean respone = true;
        int userInput;
        scan = new Scanner(System.in);
        
        while (respone) {
            System.out.println("What would you like to do?");
            System.out.println("(1) View items on loan \n"
                             + "(2) View All items \n"
                             + "(3) Stop Program");
            userInput = scan.nextInt();
            
            switch (userInput) {
                case 1: userInput = 1;
                break;
                case 2: userInput = 2;
                fileHandling.readItems();
                fileHandling.printSummary();
                break;
                case 3: userInput = 3;
                System.out.println("Application Terminated");
                respone = false;
                

                //ADD SLEEP TIMER FOR INPUT                                
            }
        }    
    }
}
