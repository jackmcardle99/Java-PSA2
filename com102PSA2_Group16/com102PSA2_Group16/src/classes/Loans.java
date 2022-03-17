package classes;
import java.io.IOException;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Loans {
    
// declaring class variables
    private String bardcode, userID, issueDate, dueDate, loanType;
    private LocalDate date;
    private DateTimeFormatter dateFormat;
//declaring as final means we cannot alter values 
    private final int mediaLoan =7, bookLoan = 28, maxBookRenew = 3, maxMediaRenew = 2;     
    private int renewCount; //counting number of renewals 
    private Scanner scan;
    FileIO file = new FileIO();
    Items item1 = new Items();
    private String inputUserId, inputBarcode;
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
 
    public void loanEligibility() throws IOException
    {
        String loanType = null;
        boolean available = false, loanBarcodeFound = false, itemBarcodeFound = false, userFound = false;
        scan = new Scanner(System.in);
 
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
        
//        //Looping through iteem array to ensure barcode actually exists   
        for (Items item : itemList)
        {
            if (inputBarcode.equals(item.getBarcode()))  
            {  
                itemBarcodeFound = true;     
            }                  
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
        this.getLoanType(inputBarcode);
    }
    
    public String getLoanType(String inputBarcode) throws IOException
    {        
        
        boolean found = false;
  
        int length = itemList.size();
       
 
        for (int i = 0; i < length; i++)
        {
            if (itemList.get(i).getBarcode().equals(this.inputBarcode) && itemList.get(i).getType().equals("Book"))
            {
                loanType = "Book";
                
            }
            else if (itemList.get(i).getBarcode().equals(this.inputBarcode) && itemList.get(i).getType().equals("Multimedia"))
            {
                loanType = "Multimedia";         
            } 
        }          
        return loanType;
    }
    
     public void createLoan(String loanType) throws IOException
    {
        date = LocalDate.now(); //get current date in 00-00-0000 format
        dateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy"); //declare the format of 00/00/0000
        LocalDate bookDays = date.plusDays(bookLoan); //used to add the amount of days for item type book
        LocalDate multimediaDays = date.plusDays(mediaLoan); //used to add the amount of days for item type book
        String addIssueDate = date.format(dateFormat); //get the issue date (current day)
        String dueBook = bookDays.format(dateFormat); //get the due date for books
        String dueMultimedia = multimediaDays.format(dateFormat); //get due date for multimedia
        
        String newLoan = inputBarcode + "," + inputUserId + ",";

        if(loanType.equals("Book"))
        {
            newLoan += addIssueDate + "," + dueBook + "," + "0";
        }
        else if (loanType.equals("Multimedia"))
        {
            newLoan += addIssueDate + "," + dueMultimedia + "," + "0";
        }
        
     
        file.writeToLoans(newLoan);
        
        
    }
            
    public void renewLoan(String loanType) throws IOException
    {
        System.out.println(loanType); 

        
        int length = loanList.size();
       
 
        for (int i = 0; i < length; i++)
        {
            if(loanList.get(i).getBarcode().equals(this.inputBarcode))
            {
               renewCount = (loanList.get(i).getRenewCount() + 1);

               System.out.println(renewCount); 
            }     
        }
        
    
        //if item < 3 renewals, then renew
        //if renew = true renewCount +=1
        //book has max renewal of 3, media has max renewal of 2, they are declared at top of class
        
        //The program should allow the librarian to renew an existing loan.
        //On supply of the item barcode, the loan’s return date is increased by two weeks from the current
        //date for books and one week for multimedia items and the number of renews is increased by one. A
        //book cannot be renewed more than three times and the maximum number of renews for a
        //multimedia item is two.
    }
    
    public void renewLoanEligibility() throws IOException
    {
        scan = new Scanner(System.in);
        boolean loanBarcodeFound = false;
        
        System.out.println("Please enter barcode of current loan you wish to renew. ");
        inputBarcode = scan.nextLine();
        
        
        for (Loans loan : loanList)
        {
            if (inputBarcode.equals(loan.getBarcode()))  
            {  
                loanBarcodeFound = false;     
            }  
        }
        
        this.getLoanType(inputBarcode);
                
    }
    
    public String toString()
    {
        String loanOutput = "Barcode: " + this.bardcode + ", " + "User ID: " + this.userID 
                            + ", " + "Issue Date: " + this.issueDate + ", " + "Due Date: "
                            + this.dueDate + "' " + "Renewals: " + this.renewCount;
        return loanOutput;
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
        
    public String getLoanType()
    {
        return this.loanType;
    }
    
    
}
