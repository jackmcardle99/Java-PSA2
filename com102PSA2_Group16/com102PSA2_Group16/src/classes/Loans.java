
package classes;
import java.io.IOException;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;


public class Loans {
    // instantiating objs of classes    
    FileIO file = new FileIO(); 
    Items items = new Items();
    Users user = new Users();
    Books book = new Books();
    Multimedia media = new Multimedia();
// declaring class variables
    private String bardcode, userID, issueDate, dueDate, inputBarcode,inputUserId;    
    private int renewCount;
    private Scanner scan;
//creating instances of arraylists 
    ArrayList<Users> userList = file.getUserList();
    ArrayList<Items> itemList = file.getItemList(); 
    ArrayList<Loans> loanList = file.getLoanList();
/* The variables declared below deal with the date for creating and renewing loans methods 
    declaring as final means we cannot alter values later in code */  
    private final LocalDate date = LocalDate.now(); //get current date in 00-00-0000 format
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //declare the format of 00/00/0000
    private final LocalDate bookDays = date.plusDays(book.getLoanLength()); //used to add the amount of days for item type book
    private final LocalDate multimediaDays = date.plusDays(media.getLoanLength()); //used to add the amount of days for item type book
    private final String currentDate = date.format(dateFormat); //get the issue date (current day)
    private final String dueBook = bookDays.format(dateFormat); //get the due date for books
    private final String dueMultimedia = multimediaDays.format(dateFormat); //get due date for multimedia 
   
    public Loans(String barcode, String userID, String issueDate, String dueDate, int renewCount)
    {
        this.bardcode = barcode;
        this.userID = userID;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.renewCount = renewCount;
    }
    
    ///constructor overloading
    public Loans(){}
    
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
     
     //this method searches to see if loan is found in array
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
    public void createLoan() throws IOException
    {       
        boolean loanBarcodeFound = false, itemBarcodeFound = false, userFound = false;
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
        { 
            for (int i = 0; i < itemList.size(); i++)
            {/*this loop checks the inputbarcode of the current iteration of loop
                    to set a loantype*/                                                                   //https://www.webucator.com/article/how-to-check-object-type-in-java/
                if(itemList.get(i) instanceof Books && itemList.get(i).getBarcode().equals(inputBarcode)) //instance of checks to see if obj is from specific an instance of
                {    //dependent on loantype,add new objects to array                                                                                       a specific class 
                    loanList.add(new Loans(inputBarcode, inputUserId, currentDate, dueBook, renewCount));
                    break; //add new loan obj
                }
                if (itemList.get(i) instanceof Multimedia && itemList.get(i).getBarcode().equals(inputBarcode))
                {          
                    loanList.add(new Loans(inputBarcode, inputUserId, currentDate, dueMultimedia, renewCount));
                    break;
                }      
            }    
            System.out.println("\nNew loan has been created for this item. \n");        
        }
        else 
        {  //validation for creating new loan
            System.out.println("\nThis loan couldn't be created for the specified reason(s)\n");
            if (itemBarcodeFound == false)
                System.out.println(" - The item barcode doesn't exist. ");
            if (loanBarcodeFound == true)
                System.out.println(" - The item is already on loan. ");
            if (userFound == false)
                System.out.println(" - The user ID doesnt exist. ");     
            this.createLoan();
        }       
        /*if the loan is determined available above, createloan method called and
                passed loantype */       
    }       
    
     public void renewLoan() throws IOException
    {
        scan = new Scanner(System.in);
        System.out.println("Please enter barcode of current loan you wish to renew.");
        inputBarcode = scan.nextLine();           
        /*Making sure that the book/multimedia is eligible for renew max book renew = 3 max multimedia = 2
        if the book renew == 3 or multimedia == 2 will show max renewal is reached*/
        boolean loanBarcodeFound = this.findLoan(inputBarcode);
        if (loanBarcodeFound == true)
        {
            for (int i = 0; i < loanList.size(); i++) //looping through length of loans
            {  
                while (loanList.get(i).getBarcode().equals(inputBarcode)) //finding the loan that = inputBarcode
                { //looping through item arraylist and validating type of object through barcode
                   for(Items item : itemList)
                    { //looping through itemList array to validate item type
                        if (item.getBarcode().equals(inputBarcode) && item instanceof Books)
                        {   //validation for renewal dates
                            if(loanList.get(i).getRenewCount() < book.getMaxRenew())
                            {   
                                renewCount = loanList.get(i).getRenewCount();
                                loanList.get(i).setIssueDate(currentDate);
                                loanList.get(i).setDueDate(dueBook);
                                loanList.get(i).setNumRenews(renewCount += 1);
                                break; //add 1 to the current renewcount                      
                            }
                            else
                            {
                                System.out.println("\nMax renewals for this item have been reached.");
                            }
                        }                       
                       
                        if (item.getBarcode().equals(inputBarcode) && item instanceof Multimedia)
                        {
                            if(loanList.get(i).getRenewCount() < media.getMaxRenew())
                            {   
                                renewCount = loanList.get(i).getRenewCount();
                                loanList.get(i).setIssueDate(currentDate);
                                loanList.get(i).setDueDate(dueMultimedia);
                                loanList.get(i).setNumRenews(renewCount += 1);
                                break;
                            }
                            else
                            {
                                System.out.println("\nMax renewals for this item have been reached.");
                            }
                        }               
                }
                break; 
                }                
            }
        }
        else if (loanBarcodeFound == false)
        {  //printed if barcode not found
             System.out.println("Please enter a valid barcode");
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
               System.out.println("\nAre you sure you want to remove this loan"+ " (y/n)\n"); 
                 confirm = scan.nextLine();
                 if (confirm.equals("Y") || confirm.equals("y"))
                 {
                     for (int i = 0; i < loanList.size(); i++)
                     {
                         if(loanList.get(i).getBarcode().equals(inputBarcode))
                         {   //converting format of date from / to - in csv                     
                            String dueDateLoan = loanList.get(i).getDueDate();                         
                            String formattedDueDate = dueDateLoan.replace("/", "-");                      
                            String[] divide = formattedDueDate.split("-");
                            String day = divide[0];  //changing D/M/Y to Y/M/D
                            String month = divide[1]; 
                            String year = divide[2];
                            String result = year + "-" + month + "-" + day;                       
                            LocalDate dueDate = LocalDate.parse(result);
                            boolean dateBefore = date.isAfter(dueDate);
                            
                            //if current day is after return date display this
                            if(dateBefore) 
                            {
                               System.out.println("This items was returned after due date");
                            }
                         } //finding loan which = inputBarcode
                         if(loanList.get(i).getBarcode().equals(inputBarcode))
                         {
                             loanList.remove(loanList.get(i)); //remove from arraylist
                             System.out.println("\nLoan has been removed removed.");
                             responseLoop = false;
                         }
                     }  
                 }  //validation for returning loan
                 else if (confirm.equals("N") || confirm.equals("n"))
                 {
                     System.out.println("\nCancelled action. ");
                     responseLoop = false;
                 }
           }
        } else {
            System.out.println("\nPlease enter valid barcode."); 
        }
    }

    //getters for loan variables
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
