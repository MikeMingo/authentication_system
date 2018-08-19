/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.util.Arrays;

/**
 *
 * @author mingo
 */
public class FileServer {
    
    private FileReader readFile;
    private String output ="";
    private BufferedReader fileBuffer;
    private String menuOption;
    
    
    public FileServer() {
        
        readFile = null;
        fileBuffer = null;
        menuOption = "r";
    
        
        
        
        
        
    }
    //BEGIN FILE METHODS
    
    public String read(String fileName) throws IOException {
        String bufferString;
        readFile = new FileReader(fileName);
        fileBuffer = new BufferedReader(readFile);
        while ((bufferString = fileBuffer.readLine()) != null) {
            output += bufferString + "\n";
        }
        return output;
    }
    public void setMenuOption() {
        menuOption = "q";
    }
    
    public String getMenuOption(){
        return menuOption;
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        //Starting instances of required classes
        FileServer fs = new FileServer();
        AuthSystem as = new AuthSystem();
        HashGenerator hg = new HashGenerator();
        
        //READ credentials file
        as.setCred("credentials.txt");
        
        //PRINT system greeting
        System.out.println("ZOO AUTHENTICATION SYSTEM");
        System.out.println();
        
        
        //WHILE exit condition not met AND total attempts less than three
        while (fs.getMenuOption() != "q" && as.getFailCount() < 3) {
            
            //GET username from user
            as.setUsername();
            
            //GET Password from user
            as.setPassword();
            
            //GET MD5hash and set hashes to comparable strings
            String userHash = hg.getMD5Hash(as.getPassword());
            String credHash = as.getCredAtIndex(as.indexOfUsername() + 1);
            
            //COMPARE credentials for validity
            if (userHash.equals(credHash)) {
                System.out.println();
                System.out.println("Welcome " + as.getUsername() + "!");
                System.out.println("Retrieving " + as.getCredAtIndex(as.indexOfUsername() + 3) + " file.");
                System.out.println();
                fs.read(as.getCredAtIndex(as.indexOfUsername() + 3) + ".txt");
            }
            else{
                as.setFailCount();
            }
        }
        
        
        if (as.getFailCount() > 2) {
            System.out.println();
            System.out.println("YOU HAVE BEEN LOCKED OUT OF THE AUTHENTICATION SYSTEM!");
        }
        else{
            System.out.println();
            System.out.println("LOGGING OUT OF AUTHENTICATION SYSTEM");
        }
       
    }
    
    
}
