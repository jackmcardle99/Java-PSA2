package classes;

//this class inherits from the items class, for creating book objects

public class Books extends Items {  

    //constructor, inherited from the superclass, in order to create book obj
    public Books(String barcode, String author, String title, String type, String year, String isbn)
    {
        super(barcode, author, title, type, year, isbn);    
    }
    
    public Books(){} //overloading constructor
    
    private int maxRenew = 3, loanLength = 28; 
    
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
