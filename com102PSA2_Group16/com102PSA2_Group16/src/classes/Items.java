package classes;

import java.util.ArrayList;

public class Items {
    
    //declaring class variables
    private String barcode, author, title, type, year, isbn;
    FileIO file = new  FileIO(); //instatiating class
    ArrayList<Items> itemList = file.getItemList(); //instatiating arraylist
   
    public Items(String barcode, String author, String title, String type, String year, String isbn)
    {
        this.barcode = barcode;
        this.author = author;
        this.title = title;
        this.type = type;
        this.year = year;
        this.isbn = isbn;
    }
    
    //constructor overloading
    public Items()
    {     
    }
    
    //method for printing ArrayList summary
    public void printItemSummary()
    {
        for(Items items: itemList) {  
            System.out.println(items);       
        }
    }
    
    public String toString(){ //method to output content of items file as string
        String itemsOutput =  "Barcode: " + this.barcode + ", " + "author: " 
                + this.author + ", " + "title: " + this.title + ", "+ "type: " 
                + this.type + ", "+ "year: " + this.year + ", "+ "isbn: " + this.isbn;
             
        return itemsOutput;  
    }
    
     // this method searches to see if item is found in array
    public boolean findItem(String inputBarcode)
    {
        boolean itemBarcodeFound = false;
        for (Items item : itemList)
        {
            if (inputBarcode.equals(item.getBarcode()))  
            {  
                itemBarcodeFound = true;     
            }                  
        }
        return itemBarcodeFound;
    }
    
//getters for items
    public String getBarcode() 
    {
        return this.barcode;
    }

    public String getType() 
    {
        return this.type;
    }   
}
