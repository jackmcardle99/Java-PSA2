package classes;

import java.text.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

//import java.time.LocalDate; //importing local date class

public class Loans {
    
// declaring class variables
    private String bardcode, userID, issueDate, dueDate;
    
    //declaring as final means we cannot alter values 
    private final int mediaLoan = 7, bookLoan = 28, maxBookRenew = 3, maxMediaRenew = 2;     
    private int renewCount; //counting number of renewals 
    private Scanner scan;
    FileIO file = new FileIO();
    Items item1 = new Items();
    
    //creating instances of arraylists from fileio class
    ArrayList<Users> userList = file.getUserList();
    ArrayList<Items> itemList = file.getItemList(); 
    ArrayList<Loans> loanList = file.getLoanList();
    
    public Loans(String barcode, String userID, String issueDate, String dueDate, int renewCount)
    {
        this.bardcode = barcode;
        this.userID = userID;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.renewCount = renewCount;       
    }
    
    //overload contructor! Dont remove
    public Loans()
    {   
    }
 
    public boolean loanEligibility()
    {
        Loans newLoan = null;
        boolean available = false, loanBarcodeFound = false, itemBarcodeFound = false, userFound = false;
        scan = new Scanner(System.in);
        String inputUserId, inputBarcode;
        
            
        //getting user input for new loan details
        System.out.println("\nPlease enter User ID for loan. ");
        inputUserId = scan.nextLine();
        System.out.println("Please enter barcode for loan. ");
        inputBarcode = scan.nextLine();
        
        //looping through users to ensure that they exist
        for (Users user : userList)
        {
            if (inputUserId.equals(user.getUserID()))
                userFound = true;          
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
                {
                    System.out.println("\nThis item is available for loan!\n");                   
                }
                else 
                {
                    System.out.println("\nThis loan couldn't be created for the specified reason(s)\n");
                    if (itemBarcodeFound == false)
                        System.out.println(" - The item barcode doesn't exist. ");
                    if (loanBarcodeFound == true)
                        System.out.println(" - The item is already on loan. ");
                    if (userFound == false)
                        System.out.println(" - The user ID doesnt exist. ");     
                    this.loanEligibility();
                }   
        return available;
    }
        
    public Loans createLoan(boolean availability)
    {
        Loans newLoan = null;
        
        for (Items item : itemList)
        {
            if(item.getType().equals("Book"))
            {
                //loan 28 days
            }
            else
            {
                //loan 7 days
            }
        }
        
        //if item = book 14 days
        //if item = media 7 days
        
        //barcode = item barcode
        //userid = user id
        // then determine date
        //num renews
        return newLoan;
    }
            
    public void renewLoan()
    {
        //if item < 3 renewals, then renew
        //if renew = true renewCount +=1
        //book has max renewal of 3, media has max renewal of 2, they are declared at top of class
    }
    
    public String toString()
    {
        String loanOutput = "Barcode: " + this.bardcode + ", " + "User ID: " + this.userID 
                            + ", " + "Issue Date: " + this.issueDate + ", " + "Due Date: "
                            + this.dueDate + "' " + "Renewals: " + this.renewCount;
        return loanOutput;
    }
    
    //this method is turning string version of date form file into date format
    //still have work to do in order to make it work 100% correctly
    //https://stackoverflow.com/questions/6510724/how-to-convert-java-string-to-date-object
    // need this method for increasing loan by 2 weeks, possibly?
    // modulus possibly?
    public Date StringToDate(String strDate) throws Exception
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = df.parse(strDate);
        return date;
    }
    
//    public Date StringToDate(String strDate)
//    {
//        Date date = null;
//        try
//        {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            result = dateFormat.parse(strDate);
//        }
//        catch(ParseException e)
//        {
//            
//            e.printStackTrace();
//        }
//        return result;
//    }
    
    public void addTime() {
        LocalDate date = LocalDate.now(); //get current date in 00-00-0000 format
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/yyyy"); //declare the format of 00/00/0000
        
        LocalDate bookDays = date.plusDays(28); //used to add the amount of days for item type book
        LocalDate multimediaDays = date.plusDays(14); //used to add the amount of days for item type book
        String issueDate = date.format(formatters); //get the issue date (current day)
        String dueDateBook = bookDays.format(formatters); //get the due date for books
        String dueMultimedia = multimediaDays.format(formatters); //get due date for multimedia
            
        System.out.println(dueMultimedia); //simple print to show date will remove in final product
    }
    
 

    
    //getters
    public String getBarcode()
    {
        return this.bardcode;
    }
    
    public String getUserID()
    {
        return this.userID;
    }
    
    public String getIssueDate()
    {
        return this.issueDate;
    }
    public String getDueDate()
    {
        return this.dueDate;
    }
    
    public int getRenewCount()
    {
        return this.renewCount;
    }
}
