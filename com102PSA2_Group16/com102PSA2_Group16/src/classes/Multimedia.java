package classes;

//this class inherits from the items class
public class Multimedia extends Items {
    
    private final String itemType = "Multimedia";
   
    public void Multimedia(){}//constructor 
    
   //getters and setters for media type
   public void setMaxRenew()
    {
        super.maxRenew = 2;
    }
    
    public void setLoanLength()
    {
       super.loanLength = 7; 
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
