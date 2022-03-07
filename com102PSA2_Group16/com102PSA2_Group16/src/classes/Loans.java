package classes;

import java.text.*;
import java.util.*;
import java.time.*;

//import java.time.LocalDate; //importing local date class

public class Loans {
    
    //instatiating the date object
    //Date date = new Date(); 
    
    // declaring class variables
    private String bardcode, userID, issueDate, dueDate;
    //declaring as final means we cannot alter values 
    private final int mediaLoan = 7, bookLoan = 28, maxBookRenew = 3, maxMediaRenew = 2;     
    private int renewCount; //counting number of renewals 
    private Scanner scan;
    
    public Loans(String barcode, String userID, String issueDate, String dueDate, int renewCount)
    {
        this.bardcode = barcode;
        this.userID = userID;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.renewCount = renewCount;       
    }
    
    public Loans()
    {
         
    }
 
    
    public void renewLoan()
    {
        
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
