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
    
    public FileServer() {
        
        readFile = null;
        fileBuffer = null;
        
        
        
        
    }
    //BEGIN FILE METHODS
    
    public String read(String fileName) throws IOException {
        String bufferString = "";
        readFile = new FileReader(fileName);
        fileBuffer = new BufferedReader(readFile);
        while ((bufferString = fileBuffer.readLine()) != null) {
            output += bufferString + "\n";
        }
        return output;
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        FileServer fs = new FileServer();
        AuthSystem as = new AuthSystem();
        fs.read("zookeeper.txt");
        System.out.print(fs.output);
        as.setUsername("mike");
        System.out.println(as.getUsername());
        as.setCred("credentials.txt"); 
        System.out.println(as.getCred());
        System.out.println(as.getCredAtIndex(as.indexOfUsername("bernie.gorilla")+2));
        System.out.println(as.indexOfUsername("rosario.dawson"));
        
    }
    
}
