package classes;

import java.util.*;

public class Users {
    
    private String userID, forename, surname, email;
    
    public Users(String userID, String forename, String surname, String email)
    {
        this.userID = userID;
        this.forename = forename;
        this.surname = surname;
        this.email = email;        
    }
    
    public String toString(){
        String usersOutput =  "User ID: " + this.userID + ", " + "Forename: " + this.forename 
                + ", " + "Surname: " + this.surname + ", "+ "Email: " + this.email + ".";
             
        return usersOutput;  
    }
    
    //getters and setters
    public String getUserID()
    {
        return this.userID;
    }
    
    public String getForename()
    {
        return this.forename;
    }
    
    public String getSurname()
    {
        return this.surname;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
}
