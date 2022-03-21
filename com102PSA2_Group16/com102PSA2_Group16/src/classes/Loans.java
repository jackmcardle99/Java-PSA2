package classes;
import java.io.IOException;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Loans {
    
// declaring class variables
    private String bardcode, userID, issueDate, dueDate, loanType, oldLine, renewedLoan, newLoan;
    private String book = "Book", multimedia = "Multimedia";
    private LocalDate date;
    private DateTimeFormatter dateFormat;
//declaring as final means we cannot alter values 
    private final int mediaLoan =7, bookLoan = 28, maxBookRenew = 3, maxMediaRenew = 2;     
    private int renewCount; //counting number of renewals 
    private Scanner scan;
    FileIO file = new FileIO();
    Items item1 = new Items();
    private String inputBarcode, inputUserId, removeConfirm;
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
    
    //overloading constructor
    public Loans(String oldLine, String renewedLoan, String newLoan)
    {
        this.oldLine = oldLine;
        this.renewedLoan = renewedLoan;
        this.newLoan = newLoan;

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
        
       //Looping through item array to ensure barcode actually exists   
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
//        loanType = this.getLoanType(inputBarcode);
          this.createLoan(this.getLoanType(inputBarcode));
    }
    
    public String getLoanType(String inputBarcode) throws IOException
    {   
        //variable to contain itemList size as int 
        int length = itemList.size();
        
        //loops through itemlist using the inputBarcode to see if itemList type equals Book or Multimedia 
        //then returns loantype containing either multimedia or book
        for (int i = 0; i < length; i++)
        {
            if (itemList.get(i).getBarcode().equals(inputBarcode) && itemList.get(i).getType().equals(book))
            {
                loanType = "Book";              
            }
            else if (itemList.get(i).getBarcode().equals(inputBarcode) && itemList.get(i).getType().equals(multimedia))
            {
                loanType = "Multimedia";         
            } 
        }          
        return loanType;
    }
    
     public void createLoan(String loanType) throws IOException
    {
        //code used for getting the date converting the date format and adding time
        date = LocalDate.now(); //get current date in 00-00-0000 format
        dateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy"); //declare the format of 00/00/0000
        LocalDate bookDays = date.plusDays(bookLoan); //used to add the amount of days for item type book
        LocalDate multimediaDays = date.plusDays(mediaLoan); //used to add the amount of days for item type book
        String addIssueDate = date.format(dateFormat); //get the issue date (current day)
        String dueBook = bookDays.format(dateFormat); //get the due date for books
        String dueMultimedia = multimediaDays.format(dateFormat); //get due date for multimedia
        
        //temp variables that will be used to add new loan to loanList Array
        String tempBarcode;
        String tempUserID; 
        String tempIssueDate; 
        String tempDueDate; 
        int tempNumRenews;

        //if loantype equals book get the data from variables accordingly and put them into the temp variables
        //to then be added to the loanList array
        if(loanType.equals(book))
        {
            tempBarcode = inputBarcode; 
            tempUserID = inputUserId;
            tempIssueDate = addIssueDate;
            tempDueDate = dueBook;
            tempNumRenews = renewCount;
            
            loanList.add(new Loans(tempBarcode, tempUserID, tempIssueDate, tempDueDate, tempNumRenews));
        }
        //if loantype equals multimedia get the data from variables accordingly and put them into the temp variables
        //to then be added to the loanList array
        else if (loanType.equals(multimedia))
        {
            tempBarcode = inputBarcode; 
            tempUserID = inputUserId;
            tempIssueDate = addIssueDate;
            tempDueDate = dueMultimedia;
            tempNumRenews = renewCount;
             
            loanList.add(new Loans(tempBarcode, tempUserID, tempIssueDate, tempDueDate, tempNumRenews));
        }      
        
        //loops through loans printing each row
        for (Loans loans : loanList)
        {
            System.out.println(loans);        
        }
    }
            
    public void renewLoan(String loanType, String inputBarcode, String renewUserID, int renewCount) throws IOException
    {   
        //code used for getting the date converting the date format and adding time
        date = LocalDate.now(); //get current date in 00-00-0000 format
        dateFormat = DateTimeFormatter.ofPattern("d/MM/yyyy"); //declare the format of 00/00/0000
        LocalDate bookDays = date.plusDays(bookLoan); //used to add the amount of days for item type book
        LocalDate multimediaDays = date.plusDays(mediaLoan); //used to add the amount of days for item type book
        String renewDate = date.format(dateFormat); //get the issue date (current day)
        String dueBook = bookDays.format(dateFormat); //get the due date for books
        String dueMultimedia = multimediaDays.format(dateFormat); //get due date for multimedia  
        
        //variable to contain itemList size as int 
        int length = loanList.size(); 
        
        //structure for renewing the loan/+=1 to the renew count 
        for (int i = 0; i < length; i++)
        {   
            if (loanType.equals(book))
            {          
                if(loanList.get(i).getBarcode().equals(inputBarcode) && loanType.equals(book))
                {
                    loanList.get(i).setIssueDate(renewDate);
                    loanList.get(i).setDueDate(dueBook);
                    loanList.get(i).setNumRenews(renewCount += 1);
                }
            }
            else if (loanType.equals(multimedia))
            {
                if(loanList.get(i).getBarcode().equals(inputBarcode) && loanType.equals(multimedia))
                {
                    loanList.get(i).setIssueDate(renewDate);
                    loanList.get(i).setDueDate(dueMultimedia);
                    loanList.get(i).setNumRenews(renewCount += 1);
                }
            }       
        }
    }
    
    public void renewLoanEligibility() throws IOException
    {
        scan = new Scanner(System.in);
        String renewUserID;
        System.out.println("Please enter barcode of current loan you wish to renew.");
        inputBarcode = scan.nextLine();
        String loanType = this.getLoanType(inputBarcode); //calling method to assign item type
        System.out.println(loanType);
        int length = loanList.size(); //variable to contain itemList size as int 
        int renewCount;       
        
        //Making sure that the book/multimedia is eligible for renew max book renew = 3 max multimedia = 2
        //if the book renew == 3 or multimedia == 2 will show max renewal is reached
        for (int i = 0; i < length; i++)
        {
            if(loanList.get(i).getBarcode().equals(inputBarcode) && loanType.equals(book))
            {
                if (loanList.get(i).getRenewCount() < maxBookRenew)
                {
                    
                    renewCount = loanList.get(i).getRenewCount();
                    renewUserID = loanList.get(i).getUserID();
                    this.renewLoan(loanType, inputBarcode, renewUserID, renewCount);
                }
                else
                {
                    System.out.println("Max renewals reached.");
                }
            }
            else if (loanList.get(i).getBarcode().equals(inputBarcode) && loanType.equals(multimedia))
            {
                if (loanList.get(i).getRenewCount() < maxMediaRenew)
                {
                    renewCount = loanList.get(i).getRenewCount();
                    renewUserID = loanList.get(i).getUserID();
                    this.renewLoan(loanType, inputBarcode, renewUserID, renewCount);
                }
                else
                {
                    System.out.println("Max renewals reached.");
                }
            }
        }              
    }
    
    public void returnLoan()
    {
                 
        boolean available = false, loanBarcodeFound = false, itemBarcodeFound = false;
        scan = new Scanner(System.in);
        int length = loanList.size();
 
        //getting user input for the barcode in loans
        System.out.println("Please enter barcode for loan. ");
        inputBarcode = scan.nextLine();     
        
        //Looping through loan array to ensure that barcode is not already in use 
        for (Loans loan : loanList)
        {
            if (inputBarcode.equals(loan.getBarcode()))               
                loanBarcodeFound = false;  
        }
        
        //Looping through item array to ensure barcode actually exists   
        for (Items item : itemList)
        {
            if (inputBarcode.equals(item.getBarcode()))  
            {  
                itemBarcodeFound = true;     
            }                  
        }

         //decision structure to ensure loan is present and if so remove confirmation and loan removal 
        if (loanBarcodeFound == false && itemBarcodeFound == true)        
                available = true;                   
                if (available == true)
                {
                    System.out.println("\nDo you want to remove this loan? Y or N\n");     
                    removeConfirm = scan.nextLine();
                    
                    if (removeConfirm.equals("Y") || removeConfirm.equals("y"))
                    {
                        for (int i = 0; i < loanList.size(); i++)
                        {
                            if(loanList.get(i).getBarcode().equals(inputBarcode))
                            {
                                loanList.remove(loanList.get(i));
                                System.out.println("Loan Removed");
                            }
                        }  
                    }   
                }
                else 
                {
                    if (itemBarcodeFound == false)
                        System.out.println(" - The item barcode doesn't exist. "); 
                }       
        
//  The program should allow the librarian to record the return of an item on loan. To do this, the librarian
//  supplies the barcode. Items are always returned on or before their due date.
//  When an item on loan is returned the corresponding loan object is removed from the list of loans.   
    }
        
    public String toString()
    {
        //basic code to make the loanList readable and look good
        String loanOutput = "Barcode: " + this.bardcode + ", " + "User ID: " + this.userID 
                            + ", " + "Issue Date: " + this.issueDate + ", " + "Due Date: "
                            + this.dueDate + "' " + "Renewals: " + this.renewCount;
        return loanOutput;
    }
    
    public String toFileString()
    {
        //used for writing to the csv file using the correct format 
        String loanOutput = this.bardcode + "," + this.userID 
                            + "," + this.issueDate + "," + this.dueDate + "," + this.renewCount;
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
    
    public String getOldLine()
    {
        return this.oldLine;
    }
    
    public String getRenewedLoan()
    {
        return this.renewedLoan;
    }
    
    public String getNewLoan()
    {
        return this.newLoan;
    }
    
    //SETTERS
    
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
