package classes;

import java.io.BufferedReader;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;

public class FileIO 
{
    //initialize arraylists for csv files
    private static ArrayList<Items> itemList = new ArrayList<Items>();
    private static ArrayList<Users> userList = new ArrayList<Users>();
    private static ArrayList<Loans> loanList = new ArrayList<Loans>();
    
    //declaring class variables   
    private Scanner scanFile;
    
    public void readItems() throws IOException 
    {   
    String fileIn = "ITEMS.csv";
    String fileOut = ""; //when writing to file
    String line = null;
    
    
        
    // Read all lines in from CSV file and add to itemList
        FileReader fileReader = new FileReader(fileIn);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();

        while ((line = bufferedReader.readLine()) != null) {
            String[] temp = line.split(",");
            String barcode = temp[0];
            String author = temp[1];
            String title = temp[2];
            String type = temp[3];
            short year = Short.parseShort(temp[4]);
            String isbn = temp[5];
            itemList.add(new Items(barcode, author, title, type, year, isbn));
        }
        bufferedReader.close();             
    }
    
    public void readUsers() throws IOException
    {
    String fileIn = "USERS.csv";
    String fileOut = ""; //when writing to file
    String line = null;
    
    
        
    // Read all lines in from CSV file and add to itemList
        FileReader fileReader = new FileReader(fileIn);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();

        while ((line = bufferedReader.readLine()) != null) {
            String[] temp = line.split(",");
            String userID = temp[0];
            String forename = temp[1];
            String surname = temp[2];
            String email = temp[3];

            userList.add(new Users(userID, forename, surname, email));
        }
        bufferedReader.close();             
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
    String fileIn = "LOANS.csv";
    String fileOut = ""; //when writing to file
    String line = null;
    
    
        
    // Read all lines in from CSV file and add to itemList
        FileReader fileReader = new FileReader(fileIn);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();

        while ((line = bufferedReader.readLine()) != null) {
            String[] temp = line.split(",");
            String barcode = temp[0];
            String userID = temp[1];
            String issueDate = temp[2];
            String dueDate = temp[3];
            int numRenews = Integer.parseInt(temp[4]);
            loanList.add(new Loans(barcode, userID, issueDate, dueDate, numRenews));
        }
        bufferedReader.close();    
        
//            these 2 lines below are temporary!!!!!
//            System.out.println(loans.StringToDate(issueDate));
//            System.out.println(loans.StringToDate(dueDate));
    }
    
    public void printLoanSummary()
    {         
        for(Loans loans : loanList)
        {
            System.out.println(loans);
        }
    }
    

    public void printItemSummary()
    {
        for(Items items: itemList) {  
            System.out.println(items);        //shows all items in the ITEMS.csv line by line
        }

//        int index = 0;
//        while (itemList.size() > index) {
//            System.out.println(itemList.get(index++).getBarcode());    //shows all barcodes available
//        }
               
//        System.out.println(itemList.get(0).getBarcode());  //Shows barcode in pos 2


//        System.out.println(itemList.get(2));
    }
}

