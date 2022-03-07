package classes;

import java.util.*;
import java.io.*;

public class Items {
    private Scanner scan;
    private String barcode, author, title, type, isbn;
    private short year;
    
    public Items(String barcode, String author, String title, String type, short year, String isbn)
    {
        this.barcode = barcode;
        this.author = author;
        this.title = title;
        this.type = type;
        this.year = year;
        this.isbn = isbn;
    }
    
    public String toString(){ //method to output content of items file as string
        String itemsOutput =  "Barcode: " + this.barcode + ", " + "author: " 
                + this.author + ", " + "title: " + this.title + ", "+ "type: " 
                + this.type + ", "+ "year: " + this.year + ", "+ "isbn: " + this.isbn;
             
        return itemsOutput;  
    }
    
    public String getBarcode() {
        return this.barcode;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getType() {
        return this.type;
    }
    
    public short getYear() {
        return this.year;
    }
    
    public String getISBN() {
        return this.isbn;
    }
    
}
