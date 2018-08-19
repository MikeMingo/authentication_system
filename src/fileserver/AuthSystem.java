/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    private int failCount;
    
    //Constructor for class AuthSystem
    public AuthSystem() {
        userName = null;
        userPass = null;
        credentials ="";
        failCount = 0;
    }
    
    //BEGIN METHODS
    public void setFailCount() {
        System.out.println();
        System.out.println("Username/Password combination incorrect.");
        failCount += 1;
        System.out.println();
        if ((3 - failCount) >1){
            System.out.println("You have " + (3 - failCount) + "attempts remaining before lockout.");
        }
        else{
            System.out.println("You have " + (3 - failCount) + "attempt remaining before lockout.");
        }
        
    }
    public int getFailCount() {
        return failCount;
    }
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
