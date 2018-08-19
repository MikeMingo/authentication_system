/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


/**
 *
 * @author mingo
 */
public class AuthSystem {
    
    private String userName;
    private String userPass;
    private String credentials;
    private String[] credArray;
    
    //Constructor for class AuthSystem
    public AuthSystem() {
        userName = null;
        userPass = null;
        credentials ="";
    }
    
    //BEGIN METHODS
    
   
    //Setter for variable userName in class AuthSystem
    public void setUsername() {
        System.out.println();
        System.out.println("Enter Username: ");
        Scanner nameScan = new Scanner(System.in);
        userName = nameScan.nextLine();
    }
    //Getter for variable userName in class AuthSystem
    public String getUsername() {
        return userName;
    }
    //Setter for variable userPass in class AuthSystem
    public void setPassword() {
        System.out.println();
        System.out.println("Enter Password: ");
        Scanner passScan = new Scanner(System.in);
        userPass = passScan.nextLine();
    }
    //Getter for variable userPass in class AuthSystem
    public String getPassword() {
        return userPass;
    }
    
    public int indexOfUsername () {
        return Arrays.asList(credArray).indexOf(userName);

    }
    
    public void setCred(String fileName) throws FileNotFoundException, IOException{
        File credFile = new File(fileName);
        Scanner credScan = new Scanner(credFile);
        while (credScan.hasNextLine()) {
            credentials += credScan.next();
            credentials += " ";
        
        }
        credArray = credentials.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    }
    
    public String getCredAtIndex(int number) {
   
        return credArray[number].replaceAll("[^a-zA-Z0-9 .]","");
    }
    
    public String getCred() {
        return credentials;
    }
    
    
    
    //METHODS PERTAINING TO FILE OPERATION
    
}
