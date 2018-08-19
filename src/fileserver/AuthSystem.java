
package fileserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


/**
 *This class is used for storage and retrieval of
 * information related to user verification
 * @author mingo
 */
public class AuthSystem {
    // initializing variables for class
    private String userName = null;
    private String userPass = null;
    private String credentials = "";
    private String[] credArray;
    
    /**
     * Default constructor
     */
    public AuthSystem() {

    }
    
    
    /**
     * This method is used to prompt the user for input
     * and set the input to variable userName
     */
    public void setUsername() {
        System.out.println();
        System.out.println("Enter Username: ");
        Scanner nameScan = new Scanner(System.in);
        userName = nameScan.nextLine();
    }
    /**
     * This method is used to retrieve the userName variable
     * @return String userName
     */
    public String getUsername() {
        return userName;
    }
    /**
     * This method is used to prompt the user for input
     * and set the input to variable userPass
     */
    public void setPassword() {
        System.out.println();
        System.out.println("Enter Password: ");
        Scanner passScan = new Scanner(System.in);
        userPass = passScan.nextLine();
    }
    /**
     * This method is used to retrieve the userPass variable
     * @return String userPass
     */
    public String getPassword() {
        return userPass;
    }
    /**
     * This method is used to get the index value of the 
     * current userName variable by way of the array credArray
     * @return integer userName index value
     */
    public int indexOfUsername () {
        return Arrays.asList(credArray).indexOf(userName);

    }
    /**
     * This method is used to create an array for indexing based
     * on the filename passed into it 
     * @param fileName
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void setCred(String fileName) throws FileNotFoundException, IOException{
        File credFile = new File(fileName);
        Scanner credScan = new Scanner(credFile);
        while (credScan.hasNextLine()) {
            credentials += credScan.next();
            credentials += " ";
        }
        //regex used on split to allow spaces within quotes to be left alone
        credArray = credentials.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    }
    /**
     * This method returns the string value found at given index
     * @param number
     * @return String value at index
     */
    public String getCredAtIndex(int number) {
        //regex used to remove quotes for easier passing of string
        return credArray[number].replaceAll("[^a-zA-Z0-9 .]","");
    }
    
}
