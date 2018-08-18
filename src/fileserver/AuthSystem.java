/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


/**
 *
 * @author mingo
 */
public class AuthSystem {
    
    private String userName;
    private String userPass;
    private String[] credArray;
    
    //Constructor for class AuthSystem
    public AuthSystem() {
        userName = null;
        userPass = null;
        credArray =null;
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
    
    public void setCredArray(String fileName) throws FileNotFoundException, IOException{
        String bufferString = "";
        String tempSplit = "";
        FileReader readFile = new FileReader(fileName);
        BufferedReader fileBuffer = new BufferedReader(readFile);
        while ((bufferString = fileBuffer.readLine()) != null) {
            tempSplit += bufferString + "\n";
        }
        credArray = tempSplit.trim().split(" ");
        System.out.print(Arrays.toString(credArray));
        
    }
    
    public String getCredArray(int number) {
        return credArray[number];
    }
    
    
    //METHODS PERTAINING TO FILE OPERATION
    
}
