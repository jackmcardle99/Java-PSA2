package classes;

import java.util.*;
import java.io.File;
import java.io.IOException;

public class FileIO 
{
    //initialize arraylists for csv files
    private static ArrayList<Items> itemList = new ArrayList<Items>();
    private static ArrayList<Users> userList = new ArrayList<Users>();
    private static ArrayList<Loans> loanList = new ArrayList<Loans>();
    
    //declaring class variables   
    private Scanner scanFile;
    private File readFile;
    private StringTokenizer token;
    
    public void readItems() throws IOException 
    {   
        //inpu8tting the file into the program
        readFile = new File("ITEMS.csv");
        scanFile = new Scanner(readFile);
        scanFile.nextLine(); //skips first line of file
        token = null;
        
        //initializing variables that will be needed to represent the objects from items
        String barcode, author, title, type, isbn = "";
        Short year = 0;
        
        while(scanFile.hasNextLine()) {
            //telling the tokenizer that ',' is the delimiter between tokens
            token = new StringTokenizer(scanFile.nextLine(), ",");        
            //assigning each token to its related variable
            barcode = token.nextToken();
            author = token.nextToken();
            title = token.nextToken();
            type = token.nextToken();
            year = Short.parseShort(token.nextToken());
            isbn = token.nextToken();
            
            Items items = new Items(barcode, author, title, type, year, isbn);
            
            itemList.add(items);         
        } 
    }
    
    public void readUsers() throws IOException
    {
        readFile = new File("USERS.csv");
        scanFile = new Scanner(readFile);
        scanFile.nextLine(); //skips first line
        token = null;
        
        String userID, forename, surname, email = "";
        
        while(scanFile.hasNextLine())
        {
            //telling the tokenizer that ',' is the delimiter between tokens
            token = new StringTokenizer(scanFile.nextLine(), ",");         
            userID = token.nextToken();
            forename = token.nextToken();
            surname = token.nextToken();
            email = token.nextToken();
            
            //passing variables to user objects
            Users users = new Users(userID, forename, surname, email);
            
            userList.add(users); 
        }      
    }
    
    public void printUserSummary()
    {
        for(Users users : userList)
        {
            System.out.println(users);
        }
    } 
    
    public void readLoans() throws IOException, Exception
    {
         readFile = new File("LOANS.csv");
         scanFile = new Scanner(readFile);       
         scanFile.nextLine(); //skips first line of file
         token = null;
         
         String barcode, userID, issueDate, dueDate;
         int numRenews;

// may need these 2 lines below dont remove
//         Date issueDate = new Date(int year, int month, int date);
//         Date dueDate;
         
        while(scanFile.hasNextLine())
        {
            token = new StringTokenizer(scanFile.nextLine(), ",");
            barcode = token.nextToken();
            userID = token.nextToken();
            issueDate = token.nextToken();
            dueDate = token.nextToken();
            numRenews = Integer.parseInt(token.nextToken());
            
            Loans loans = new Loans(barcode, userID, issueDate, dueDate, numRenews);
            
            loanList.add(loans);
            
            //  these 2 lines below are temporary!!!!!
            System.out.println(loans.StringToDate(issueDate));
            System.out.println(loans.StringToDate(dueDate));
            
        }   
    }
   
    public void printLoanSummary()
    {         
        for(Loans loans : loanList)
        {
            System.out.println(loans);
        }
    }
    
    public void printItemSummary() {
        for(Items items: itemList) {  
            System.out.println(items);        //shows all items in the ITEMS.csv line by line
        }

//        int index = 0;
//        while (itemList.size() > index) {
//            System.out.println(itemList.get(index++).getBarcode());    //shows all barcodes available
//        }
               
//        System.out.println(itemList.get(2).getBarcode());  //Shows barcode in pos 2


//        System.out.println(itemList.get(2));
    }
}
