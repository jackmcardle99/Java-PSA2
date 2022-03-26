package classes;

import java.util.ArrayList;

public class Users {
    
    private String userID, forename, surname, email;
    FileIO file = new FileIO(); //instatiating class
    ArrayList<Users> userList = file.getUserList(); //instatiating arraylist
    //constructor for users
    public Users(String userID, String forename, String surname, String email)
    {
        this.userID = userID;
        this.forename = forename;
        this.surname = surname;
        this.email = email;        
    }
    
    ///constructor overloading
    public Users()
    {     
    }
    
    //method for printing ArrayList summary
    public void printUserSummary()
    {
        for(Users users : userList)
        {
            System.out.println(users);
        }
    } 
    
    public String toString()
    {
        String usersOutput =  "User ID: " + this.userID + ", " + "Forename: " + this.forename 
                + ", " + "Surname: " + this.surname + ", "+ "Email: " + this.email + ".";             
        return usersOutput;  
    }
    
    // this method searches to see if user is found in array
    public boolean findUser(String inputUserId)
    {
        boolean userFound = false;
        for (Users user : userList)
        {
            if (inputUserId.equals(user.getUserID()))
                userFound = true;          
        }     
        return userFound;
    }
    
//getters for users class
    public String getUserID()
    {
        return this.userID;
    }
   
}
