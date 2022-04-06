package classes;
import java.io.*;
import java.util.*;

//This class was created in order to contain all file manipulation in one centralised location and keep things managed neatly.

public class FileIO 
{
    //initialize arraylists for storing variables csv files
    private static final ArrayList<Items> itemList = new ArrayList<Items>();
    private static final ArrayList<Users> userList = new ArrayList<Users>();
    private static final ArrayList<Loans> loanList = new ArrayList<Loans>();
    
    //declaring class variables   
    private BufferedReader inputStream; //reads the content of files as text
    private BufferedWriter outputStream;
    private FileReader fr; //for reading and writing files
    private FileWriter fw;
    private String fileIn, fileOut, line;  //string for file name 
     
    //method for reading ITEMS.csv
    public void readItems() throws IOException 
    {   
    fileIn = "ITEMS.csv";
    fileOut = ""; //when writing to file
    line = null;
       
    // Read all lines in from CSV file and add to itemList
        FileReader fileReader = new FileReader(fileIn);
        inputStream = new BufferedReader(fileReader);
        inputStream.readLine();
        while ((line = inputStream.readLine()) != null) //while line is not empty
        {
            String[] temp = line.split(",");   //temp array to store file contents   
            if (temp[3].equals("Book")) //if item is of type book then create book item
            {                           //else create media item, inheritance at work
                itemList.add(new Books(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]));
            }
            else
            {
                itemList.add(new Multimedia(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]));
            }
            
        }
            inputStream.close();  //close bufferedreader           
    }
    
    //Method for reading USERS.csv
    public void readUsers() throws IOException
    {
     fileIn = "USERS.csv";
     fileOut = ""; //when writing to file
     line = null;  
        
    // Read all lines in from CSV file and add to itemList
        fr = new FileReader(fileIn);
        inputStream = new BufferedReader(fr);
        inputStream.readLine();

        while ((line = inputStream.readLine()) != null) 
        { 
            String[] temp = line.split(",");
            userList.add(new Users(temp[0], temp[1], temp[2], temp[3]));
        }
        inputStream.close();             
    }       
    
    //method for reading LOANS.csv
    public void readLoans() throws IOException, Exception 
    {
    fileIn = "LOANS.csv";
    fileOut = ""; //when writing to file
    line = null; 
        
    // Read all lines in from CSV file and add to itemList
        fr = new FileReader(fileIn);
        inputStream = new BufferedReader(fr);
        inputStream.readLine();

        while ((line = inputStream.readLine()) != null) 
        {
            String[] temp = line.split(",");
            int numRenews = Integer.parseInt(temp[4]);
            loanList.add(new Loans(temp[0], temp[1], temp[2], temp[3], numRenews));
        }
        inputStream.close();    
    }  

    public void writeToFile() throws IOException // WHEN PROGRAM EXITS THIS IS CALLED
    {        
        fileOut = "LOANS.csv";
        fw = new FileWriter(fileOut, false); //false means overwriting
        outputStream = new BufferedWriter(fw);
        
        outputStream.write("Barcode,User_id,Issue_Date,Due_Date,numRenews"); //making sure the heading is still  present when overwriting
        outputStream.newLine();
        for (int i = 0; i < loanList.size(); i++)
        {         
            outputStream.write(loanList.get(i).toFileString());     //writing line by line with the format of toFileString from Loans file
            outputStream.newLine();
        }
        outputStream.close();  
    }  
    
    //getters for arraylists, so we can use in other classes as needed
    public ArrayList<Items> getItemList()
    {
        return itemList;
    }
    
    public ArrayList<Users> getUserList()
    {
        return userList;
    }
    
    public ArrayList<Loans> getLoanList()
    {
        return loanList;
    }
}

