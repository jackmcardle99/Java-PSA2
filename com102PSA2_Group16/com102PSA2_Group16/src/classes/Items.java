package classes;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Items {
   
    private Scanner scan;
    private String barcode;
    private String author;
    private String title;
    private String type;
    private short year;
    private String isbn;
    
    public Items(String barcode, String author, String title, String type, short year, String isbn)
    {
        this.barcode = barcode;
        this.author = author;
        this.title = title;
        this.type = type;
        this.year = year;
        this.isbn = isbn;
    }


    public String toString(){
        String output =  "Barcode: " + this.barcode + ", " + "author: " + this.author + ", " + "title: " + this.title + ", "+ "type: " + this.type + ", "+ "year: " + this.year + ", "+ "isbn: " + this.isbn;
             
        return output;  
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
