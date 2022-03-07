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
    private Scanner scan;
    private BufferedReader br;
    private FileReader fr;
    private String fileIn, fileOut, line;    
    
    //getters for arraylists, so we can use in other classes if needed
    public ArrayList<Items> getItemList()
    {
        return itemList;
    }
    
    public ArrayList<Users> getUserList()
    {
        return userList;
    }
    
    public ArrayList<Loans> getLoanList()
    {
        return loanList;
    }
    
    //method for reading ITEMS.csv
    public void readItems() throws IOException 
    {   
    fileIn = "ITEMS.csv";
    fileOut = ""; //when writing to file
    line = null;
       
    // Read all lines in from CSV file and add to itemList
        FileReader fileReader = new FileReader(fileIn);
        br = new BufferedReader(fileReader);
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] temp = line.split(",");
            String barcode = temp[0];
            String author = temp[1];
            String title = temp[2];
            String type = temp[3];
            short year = Short.parseShort(temp[4]);
            String isbn = temp[5];
            itemList.add(new Items(barcode, author, title, type, year, isbn));
        }
        br.close();             
    }
    
    //Method for reading USERS.csv
    public void readUsers() throws IOException
    {
     fileIn = "USERS.csv";
     fileOut = ""; //when writing to file
     line = null;  
        
    // Read all lines in from CSV file and add to itemList
        fr = new FileReader(fileIn);
        br = new BufferedReader(fr);
        br.readLine();

        while ((line = br.readLine()) != null) { 
            String[] temp = line.split(",");
            String userID = temp[0];
            String forename = temp[1];
            String surname = temp[2];
            String email = temp[3];

            userList.add(new Users(userID, forename, surname, email));
        }
        br.close();             
    }       
    
    //method for reading LOANS.csv
    public void readLoans() throws IOException, Exception 
    {
    fileIn = "LOANS.csv";
    fileOut = ""; //when writing to file
    line = null; 
        
    // Read all lines in from CSV file and add to itemList
        fr = new FileReader(fileIn);
        br = new BufferedReader(fr);
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] temp = line.split(",");
            String barcode = temp[0];
            String userID = temp[1];
            String issueDate = temp[2];
            String dueDate = temp[3];
            int numRenews = Integer.parseInt(temp[4]);
            loanList.add(new Loans(barcode, userID, issueDate, dueDate, numRenews));
        }
        br.close();    
    }  
    
    //methods for printing list ArrayList summaries
    public void printItemSummary()
    {
        for(Items items: itemList) {  
            System.out.println(items);        //loops through arrays and prints line by line
        }
    }
    
    public void printUserSummary()
    {
        for(Users users : userList)
        {
            System.out.println(users);
        }
    } 
    
    public void printLoanSummary()
    {         
        for(Loans loans : loanList)
        {
            System.out.println(loans);
        }
    }
    
    //this method is temporary and pointless, code is here so i know what to do later on
    public void printUser()
    {
        Users user = userList.get(1);
        System.out.println(user.getUserID());
    }

    public void writeToLoans()
    {
        //this method will write info passed from createLoan method in loans class to LOANS.csv    
    }
    
}

