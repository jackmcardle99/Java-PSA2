package classes;

//this class inherits from the items class
public class Books extends Items {  
    private final String itemType = "Book";  
    
    public void Book(){}//constructor
    
    //getters and setters for book type
    public void setMaxRenew()
    {
        super.maxRenew = 3;
    }
    
    public void setLoanLength()
    {
       super.loanLength = 28; 
    }
    
    public int getMaxRenew()
    {
        return this.maxRenew;
    }
    
    public int getLoanLength()
    {
        return this.loanLength;
    }
    
    public String getItemType()
    {
        return this.itemType;
    }

}
