
package fileserver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.util.Scanner;

/**
 *This class opens files associated with users while
 * controlling operational flow of the program through
 * decision trees
 * @author mingo
 */
public class FileServer {
    //initializing variables for class
    private FileReader readFile = null;
    private String output ="";
    private BufferedReader fileBuffer = null;
    private String menuOption = "r";
    private int failCount = 0;
    
    /**
     * Default constructor
     */
    public FileServer() {
       
    }
    
    /**
     * This method opens a file based on input and passes
     * information to the string variable output using a 
     * buffered reader before closing the file
     * @param fileName
     * @exception FileNotFoundException
     * @return String output
     */
    public String read(String fileName)  {
        String bufferString;
        output = "";
        try {
            readFile = new FileReader(fileName + ".txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        fileBuffer = new BufferedReader(readFile);
        try {
            while ((bufferString = fileBuffer.readLine()) != null) {
                output += bufferString + "\n";
            }
        } catch (IOException ex) {
            Logger.getLogger(FileServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            readFile.close();
        } catch (IOException ex) {
            Logger.getLogger(FileServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    /**
     * This method increments the integer variable failCount and 
     * notifies the user of remaining login attempts
     */
    public void setFailCount() {
        System.out.println();
        System.out.println("Username/Password combination incorrect.");
        failCount += 1;
        System.out.println();
        if ((3 - failCount) > 1) {
            System.out.println("You have " + (3 - failCount) + " attempts remaining before lockout.");
        }
        else if ((3 - failCount) == 0){
            System.out.println("You have " + (3 - failCount) + " attempts remaining, locking system.");
        }
        else {
            System.out.println("You have " + (3 - failCount) + " attempt remaining before lockout.");
        }
        
    }
     /**
      * This method retrieves the current value of variable failCount
      * @return integer
      */
     public int getFailCount() {
        return failCount;
    }
    /**
     * This method requests input from user and uses that input
     * to choose an action to perform between allowing a new
     * login or ending the session  user will be prompted for input 
     * until the correct input is received
     */
    public void setMenuOption() {
        int menuToggle = 0;
        Scanner menuScan = new Scanner(System.in);
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println();
        System.out.println("(N)ew User / (E)nd Session");
        System.out.println();
        while (menuToggle != 1) {
            menuOption = menuScan.next();
            if (null == menuOption) {
                System.out.println("Please enter either 'N' or 'E':");
            }else switch (menuOption) {
                case "E":
                case "e":
                    menuOption = "q";
                    menuToggle = 1;
                    break;
                case "N":
                case "n":
                    System.out.println();
                    System.out.println("Resetting Total Login Attempts.");
                    failCount= 0;
                    System.out.println();
                    System.out.println("WELCOME TO THE ZOO AUTHENTICATION SYSTEM");
                    menuToggle = 1;
                    break;
                default:
                    System.out.println("Please enter either 'N' or 'E':");
                    break;
            }
        }
        
    }
    /**
     * This method gets the current string value of menuOption
     * used to allow termination of program
     * @return String menuOption
     */
    public String getMenuOption(){
        return menuOption;
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Starting instances of required classes
        FileServer fs = new FileServer();
        AuthSystem as = new AuthSystem();
        HashGenerator hg = new HashGenerator();
        
        try {
            //READ credentials file
            as.setCred("credentials.txt");
        } catch (IOException ex) {
            Logger.getLogger(FileServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //PRINT system greeting
        System.out.println("WELCOME TO THE ZOO AUTHENTICATION SYSTEM");
        System.out.println();
        
        
        //WHILE exit condition not met AND total attempts less than three
        while (!"q".equals(fs.getMenuOption()) && fs.getFailCount() < 3) {
            
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
                System.out.println();
                //ASSOCIATE role file with user
                System.out.println("Retrieving " + as.getCredAtIndex(as.indexOfUsername() + 3) + " file.");
                System.out.println();
                System.out.println();
                //OPEN DISPLAY CLOSE file
                System.out.println(fs.read(as.getCredAtIndex(as.indexOfUsername() + 3)));
                //GET user input for logout
                fs.setMenuOption();
            }
            else{
                //increment total attempts
                fs.setFailCount();
            }
        }
        
        //DISPLAY logout feedback to user depending on type
        if (fs.getFailCount() > 2) {
            System.out.println();
            System.out.println("YOU HAVE BEEN LOCKED OUT OF THE AUTHENTICATION SYSTEM!");
        }
        else{
            System.out.println();
            System.out.println("LOGGING OUT OF AUTHENTICATION SYSTEM");
        }
       
    }
    
    
}
