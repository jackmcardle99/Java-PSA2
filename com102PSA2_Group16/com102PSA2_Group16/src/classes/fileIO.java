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

    
    public void printUserSummary()
    {
        for(Users users : userList)
        {
            System.out.println(users);
        }
    } 
    
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
        
//            these 2 lines below are temporary!!!!!
//            System.out.println(loans.StringToDate(issueDate));
//            System.out.println(loans.StringToDate(dueDate));
    }
    
    
    //could shorten these down into 1 method????????????????????????
    public void printLoanSummary()
    {         
        for(Loans loans : loanList)
        {
            System.out.println(loans);
        }
    }
    
    public void printUser()
    {
        Users user = userList.get(1);
        System.out.println(user.getUserID());
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
    
    public Loans createLoan()
    {
        Loans newLoan = null;
        boolean available = false, loanBarcodeFound = false, itemBarcodeFound = false, userFound = false;
        scan = new Scanner(System.in);
        String inputUserId, inputBarcode, userStrNotFound, itemBarcodeStr, loanBarcodeStr;
        FileIO file = new FileIO();
        
        //getting user input for new loan details
        System.out.println("Please enter User ID for loan. ");
        inputUserId = scan.nextLine();
        System.out.println("Please enter barcode for loan. ");
        inputBarcode = scan.nextLine();
        
        
        //looping through users to ensure that they exist
        for (Users user : userList)
        {
            if (inputUserId.equals(user.getUserID()))
                userFound = true;
            else
                userStrNotFound = "This User ID does not exist. ";               
        }
       
        //Looping through iteem array to ensure barcode actually exists
        for (Items item : itemList)
        {
            if (inputBarcode.equals(item.getBarcode()))         
                itemBarcodeFound = true;  
        }

        //Looping through loan array to ensure that barcode is not already in use 
        for (Loans loan : loanList)
        {
            if (inputBarcode.equals(loan.getBarcode()))               
                loanBarcodeFound = true;  
        } 
        
        //decision structure to ensure loan is available
        if (loanBarcodeFound == false && itemBarcodeFound == true)        
                available = true;                   
                if (available == true)
                    System.out.println("\nThis item is available for loan!\n");
                else
                {
                    System.out.println("\nThis loan couldn't be created for the specified reason(s)\n");
                    if (itemBarcodeFound == false)
                        System.out.println(" - The item barcode doesn't exist. ");
                    if (loanBarcodeFound == true)
                        System.out.println(" - The item is already on loan. ");
                    if (userFound == false)
                        System.out.println(" - The user ID doesnt exist. ");                    
                }       
            
                                
        return newLoan;
    }
}

