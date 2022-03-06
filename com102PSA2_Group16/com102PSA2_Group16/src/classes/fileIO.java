package classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class fileIO {

    //initialize arraylist for storing the items from the csv file
    private static ArrayList<Items> itemList = new ArrayList<Items>();
    
    public static void readItems() throws IOException {
        
        
        //inpu8tting the file into the program
        File file = new File("ITEMS.csv");
        Scanner readFile = new Scanner(file);
        readFile.nextLine(); //skips for line (all strings would mess up the arraylist
        
        StringTokenizer token = null;
        
        //initializing variables that will be needed to represent the objects from items
        String barcode = "";
        String author = "";
        String title = "";
        String type = "";
        Short year = 0;
        String isbn = "";
        
   
        while(readFile.hasNextLine()) {
            token = new StringTokenizer(readFile.nextLine(), ",");
            
            
            //using information from one line to intialize the variables needed to create the object 
            barcode = token.nextToken();
            author = token.nextToken();
            title = token.nextToken();
            type = token.nextToken();
            year = Short.parseShort(token.nextToken());
            isbn = token.nextToken();
            
            Items items = new Items(barcode, author, title, type, year, isbn);
            
            itemList.add(items);         
        } 
    }
    
    public void printSummary() {
        for(Items items: itemList) {  
            System.out.println(items);        //shows all items in the ITEMS.csv line by line
        }

//        int index = 0;
//        while (itemList.size() > index) {
//            System.out.println(itemList.get(index++).getBarcode());    //shows all barcodes available
//        }
               
//        System.out.println(itemList.get(2).getBarcode());  //Shows barcode in pos 2


//        System.out.println(itemList.get(2));
    }
}
