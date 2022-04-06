package classes;

//this class inherits from the items class
public class Multimedia extends Items {
    
    //constructor, inherited from the superclass, in order to create media obj
    public Multimedia(String barcode, String author, String title, String type, String year, String isbn)
    {
        super(barcode, author, title, type, year, isbn);
    }
    
    private int maxRenew = 2, loanLength = 7;
    
    public Multimedia(){} //overloading constructor
    
   @Override //overriding from the superclass methods
    public int getMaxRenew()
    {
        return this.maxRenew;
    }
    
   @Override
    public int getLoanLength()
    {
        return this.loanLength;
    }

}
