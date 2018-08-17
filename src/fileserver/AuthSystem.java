/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver;


/**
 *
 * @author mingo
 */
public class AuthSystem {
    
    private String userName;
    private String userPass;
    
    //Constructor for class AuthSystem
    public AuthSystem() {
        userName = null;
        userPass = null;
    }
    
    //BEGIN METHODS
    
    //Setter for variable userName in class AuthSystem
    public void setUsername(String name) {
        userName = name;
    }
    //Getter for variable userName in class AuthSystem
    public String getUsername() {
        return userName;
    }
    //Setter for variable userPass in class AuthSystem
    public void setPassword(String password) {
        userPass = password;
    }
    //Getter for variable userPass in class AuthSystem
    public String getPassword() {
        return userPass;
    }
    
    
    //METHODS PERTAINING TO FILE OPERATION
    
}
