
package classes;
import java.io.IOException;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;


public class Loans {
    // instantiating classes    
    FileIO file = new FileIO(); 
    Items items = new Items();
    Users user = new Users();
    Multimedia media = new Multimedia();
    Books book = new Books();
// declaring class variables
    private String bardcode, userID, issueDate, dueDate, loanType,inputBarcode, 
                                                    inputUserId;
    
//declaring variable and setting it to item type from items classes
    private String bookItem = book.getItemType();
    private String mediaItem = media.getItemType();
    private int renewCount;
//declaring as final means we cannot alter values later in code   
    private Scanner scan;

//creating instances of arraylists from fileio class
    ArrayList<Users> userList = file.getUserList();
    ArrayList<Items> itemList = file.getItemList(); 
    ArrayList<Loans> loanList = file.getLoanList();
// The variables declared below deal with the date for creating and renewing loans methods 
    private final LocalDate date = LocalDate.now(); //get current date in 00-00-0000 format
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //declare the format of 00/00/0000
    private LocalDate bookDays = date.plusDays(book.getLoanLength()); //used to add the amount of days for item type book
    private LocalDate multimediaDays = date.plusDays(media.getLoanLength()); //used to add the amount of days for item type book
    private String currentDate = date.format(dateFormat); //get the issue date (current day)
    private String dueBook = bookDays.format(dateFormat); //get the due date for books
    private String dueMultimedia = multimediaDays.format(dateFormat); //get due date for multimedia 
   
    public Loans(String barcode, String userID, String issueDate, String dueDate, int renewCount)
    {
        this.bardcode = barcode;
        this.userID = userID;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.renewCount = renewCount;
           }
    
    ///constructor overloading
    public Loans()
    {   
    }
    
    //method for printing ArrayList summary
    public void printLoanSummary()
    {         
        for(Loans loans : loanList)
        {
            System.out.println(loans);
        }
    }
    
    //toString methods below for printing items in a readable manner
    public String toString()
    {
        String loanOutput = "Barcode: " + this.bardcode + ", " + "User ID: " + this.userID 
                            + ", " + "Issue Date: " + this.issueDate + ", " + "Due Date: "
                            + this.dueDate + ", " + "Renewals: " + this.renewCount;
        return loanOutput;
    }
    
    public String toFileString()
    { /*this toString is different than the one above as its printing different 
        to file*/
        String loanOutput = this.bardcode + "," + this.userID 
                            + "," + this.issueDate + "," + this.dueDate + "," + this.renewCount;
        return loanOutput;
    }
     
    // this method searches to see if loan is found in array
    public boolean findLoan(String inputBarcode)
    {
        boolean loanBarcodeFound = false;
        for (Loans loan : loanList)
        {
            if (inputBarcode.equals(loan.getBarcode()))               
                loanBarcodeFound = true;  
        }
        return loanBarcodeFound;
    }

//This method checks that the item is actually available
    public void loanEligibility() throws IOException
    {       
        boolean available = false, loanBarcodeFound = false, 
                                    itemBarcodeFound = false, userFound = false;
        scan = new Scanner(System.in);
        //getting user input for new loan details
        System.out.println("\nPlease enter User ID for loan.");
        inputUserId = scan.nextLine();
        System.out.println("Please enter barcode for loan. ");
        inputBarcode = scan.nextLine();      
            
       //calling methods to search arrays and assigning to boolean variables
        userFound = user.findUser(inputUserId);
        itemBarcodeFound = items.findItem(inputBarcode);
        loanBarcodeFound = this.findLoan(inputBarcode);
        //decision structure to ensure loan is available
        if (loanBarcodeFound == false && itemBarcodeFound == true && userFound == true)        
                available = true;                   
                if (available == true)
                {
                    System.out.println("\nNew loan has been created for this item. \n"); 
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
        /*if the loan is determined available above, createloan method called and
                passed loantype */
        this.createLoan(this.getLoanType(inputBarcode));
    }
    
    //this method is used to determine the type of loan being processed
    public String getLoanType(String inputBarcode) throws IOException
    {   
        //variable to contain itemList size as int 
        int length = itemList.size();
        //loops through itemlist using the inputBarcode to see if itemList type equals Book or Multimedia 
        //then returns loantype containing either multimedia or book
        for (int i = 0; i < length; i++)
        {/*this decision structure checks the inputbarcode of the current iteration of loop
                to set a loantype*/
            if (itemList.get(i).getBarcode().equals(inputBarcode) && itemList.get(i).getType().equals(bookItem))
            {   
                loanType = bookItem;
                book.setLoanLength();
                book.setMaxRenew();
            }
            else if (itemList.get(i).getBarcode().equals(inputBarcode) && itemList.get(i).getType().equals(mediaItem))
            {
                loanType = mediaItem;
                media.setLoanLength();
                media.setMaxRenew();
            } 
        }          
        return loanType;
    }
    
    //method for creating new loan
     public void createLoan(String loanType) throws IOException
    {    
        if(loanType.equals(bookItem))
        {    //dependent on loantype,add new objects to array
            loanList.add(new Loans(inputBarcode, inputUserId, currentDate, dueBook, renewCount));
        }
        else if (loanType.equals(mediaItem))
        {          
            loanList.add(new Loans(inputBarcode, inputUserId, currentDate, dueMultimedia, renewCount));
        }      
    }
    
    //method for renewing existing loan
    public void renewLoan(String loanType, String inputBarcode, String renewUserID, int renewCount) throws IOException
    {        
        //variable to contain itemList size as int 
        int length = loanList.size(); 
        
        //structure for renewing the loan/+=1 to the renew count 
        for (int i = 0; i < length; i++)
        {   
            if (loanType.equals(bookItem))
            {          
                if(loanList.get(i).getBarcode().equals(inputBarcode) && loanType.equals(bookItem))
                {
                    loanList.get(i).setIssueDate(currentDate);
                    loanList.get(i).setDueDate(dueBook);
                    loanList.get(i).setNumRenews(renewCount += 1);
                }
            }
            else if (loanType.equals(mediaItem))
            {
                if(loanList.get(i).getBarcode().equals(inputBarcode) && loanType.equals(mediaItem))
                {
                    loanList.get(i).setIssueDate(currentDate);
                    loanList.get(i).setDueDate(dueMultimedia);
                    loanList.get(i).setNumRenews(renewCount += 1);
                }
            }       
        }
        System.out.println("\nLoan has been renewed successfully\n");
    }
    
    public void renewLoanEligibility() throws IOException
    {
        scan = new Scanner(System.in);
        String renewUserID;
        System.out.println("Please enter barcode of current loan you wish to renew.");
        inputBarcode = scan.nextLine();
        String loanType = this.getLoanType(inputBarcode); //calling method to assign item type
        int length = loanList.size(); //variable to contain itemList size as int 
        int renewCount;       
        /*Making sure that the book/multimedia is eligible for renew max book renew = 3 max multimedia = 2
        if the book renew == 3 or multimedia == 2 will show max renewal is reached*/
        for (int i = 0; i < length; i++)
        {
            if(loanList.get(i).getBarcode().equals(inputBarcode) && loanType.equals(bookItem))
            {
                if (loanList.get(i).getRenewCount() < book.getMaxRenew())
                {                   
                    renewCount = loanList.get(i).getRenewCount();
                    renewUserID = loanList.get(i).getUserID();
                    this.renewLoan(loanType, inputBarcode, renewUserID, renewCount);
                }
                else
                {
                    System.out.println("Max renewals reached for this item have been reached.");
                }
            }
            else if (loanList.get(i).getBarcode().equals(inputBarcode) && loanType.equals(mediaItem))
            {
                if (loanList.get(i).getRenewCount() < media.getMaxRenew())
                {
                    renewCount = loanList.get(i).getRenewCount();
                    renewUserID = loanList.get(i).getUserID();
                    this.renewLoan(loanType, inputBarcode, renewUserID, renewCount);
                }
                else
                {
                    System.out.println("Max renewals reached for this item have been reached.");
                }
            }
        }              
    }
    
   //method for returning a loan
    public void returnLoan()
    {          
        String confirm = "";
        Boolean loanBarcodeFound = false;
         
       
        scan = new Scanner(System.in);
        //getting user input for the barcode in loans
        System.out.println("Please enter barcode for loan. ");
        inputBarcode = scan.nextLine();  
        loanBarcodeFound = this.findLoan(inputBarcode); //calling search method
         //decision structure to ensure loan is present and if so remove confirmation and loan removal 
        if (loanBarcodeFound == true)
        {      
           boolean responseLoop = true;//boolean to control loop
           while (responseLoop)
           {
               System.out.println("\nAre you sure you want to remove this loan"
                                                                + " (y/n)\n"); 
                 confirm = scan.nextLine();
                 if (confirm.equals("Y") || confirm.equals("y"))
                 {
                     for (int i = 0; i < loanList.size(); i++)
                     {
                         if(loanList.get(i).getBarcode().equals(inputBarcode))
                         {
                             
                            String dueDateLoan = loanList.get(i).getDueDate();
                            
                            String formattedDueDate = dueDateLoan.replace("/", "-");   
  


                            int result = currentDate.compareTo(formattedDueDate);
                            
                            if (result > 0) 
                            {
                               System.out.println("This items was returned after due date");
                            }   
                            
  
                         }
                         if(loanList.get(i).getBarcode().equals(inputBarcode))
                         {
                             loanList.remove(loanList.get(i));
                             System.out.println("\nLoan has been removed removed.");
                             responseLoop = false;
                         }
                     }  
                 }
                 else if (confirm.equals("N") || confirm.equals("n"))
                 {
                     System.out.println("\nCancelled action. ");
                     responseLoop = false;
                 }
           }
        } else {
            System.out.println("\nPlease enter valid response."); 
        }
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
        
    //SETTERS for renewing loan 
    public void setIssueDate(String newIssueDate)
    {
        this.issueDate = newIssueDate;
    }
    
    public void setDueDate(String newDueDate)
    {
        this.dueDate = newDueDate;
    }
    
    public void setNumRenews(int numRenews)
    {
        this.renewCount = numRenews;
    }
}
