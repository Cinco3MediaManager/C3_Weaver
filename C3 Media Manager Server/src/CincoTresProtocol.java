
import java.net.*;
import java.io.*;

//For testing Purposes Only. Class will need to be written/rewritten to behave properly. This class processes server input and 
//determines action to take. Also formats output and retusn to server for sending to client
public class CincoTresProtocol 
{
	int type = 0;
	
	//Testing only. processInput is passed input from the server and processes it. A cooresponding "processOutput" will need to 
   	//Be created
	public String processInput(String theInput)
	{
		String theOutput = "";
    	int len = theInput.length();
    	
    	if(len > 0)
    	{
    		char command = theInput.charAt(0);
   			
   			if(command == '&')		// & is the prefix for client-server Handshake;
   			{
   				theOutput = "Welcome to C3 Media Manager. This is the handshake phase.\n";	
    		}
    	
    		if(command == '*')		// * is the prefix for username and password pairs; seperated by #. Ex: *username#password
    		{	
			//Testing only. replace with method for verifying credentials. ex: verifyCredentials(some string)
    			if(theInput.equals("*jeb#pass"))
   				{
   					theOutput = "verified";
   				}	
   				else
    			{
    				theOutput = "Invalid Password or Username";
    			}
   			}
    		
    		if(theInput.equals("Book"))
    		{
    			theOutput = "Book";
    		}
    		if(theInput.contentEquals("quit"))
    		{
    			theOutput = "quit";
    		}	
    	}
    	
    	else
    	{	
   			theOutput = "";
    	}
		
    	return theOutput; 	
    }
}


