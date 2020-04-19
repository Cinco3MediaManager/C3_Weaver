import java.io.*;
import java.net.*;

public class CincoTresClient 
{	
	LoginWindow log;
	private boolean guiLaunched;
	static Socket s2;
	
	MemberGUI gui;
	
    public CincoTresClient()
    {
        String hostName = "192.168.1.6";		//Replace with IP Address of localhost if needed
        int portNumber = 1234;					//make sure port matches one in server class
        
     //  Socket s;								//IP and Port binding
        PrintWriter out;						//Writer to server
        BufferedReader in;						//Reader from server
        
        log = new LoginWindow();				//Login window object
        guiLaunched = false;					//has the main gui been launched
        
        try
        {
        	Socket s = new Socket(hostName, portNumber);	//Create Socket with IP and Port #s
        	s2 = s;						//Copy to pass to the read/send methods
        	out = new PrintWriter(s.getOutputStream(), true);	//Create writer to server
        	in = new BufferedReader(new InputStreamReader(s.getInputStream()));	//Create reader from server
        	
        	BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));		//Currently Unused
        	

        	//Phase 1: Handshake
        	sendMessage("&1");
        	String response = readMessage();
        	//End Handshake
        	
		//Phase 2: Send & Read Pairs
        	//Next send triggered by LoginWindow (for now). Send/read should (normally) occur in pairs
        	response = readMessage();
        	if(response.equals("verified"))
        	{
        		launchMemberGUI();
        	}
		else
		{
			//Notify user Password/Username is invalid, try again
			//Can use something like JErrorBox
		}
        }

      catch(IOException e)
      {
    	  System.out.println("IO exception in client");
      }
      
    }
    
    private void launchMemberGUI()
    {
    	gui = new MemberGUI();
    	guiLaunched = true;	//No longer needed
    }
    
    public static void sendMessage(String message)
    {
    	String msg = message; //Copy message for safety
    	
    	try
    	{
			PrintWriter toServer = new PrintWriter(s2.getOutputStream(),true);
			System.out.println("sendMessage Called with: " + msg);	//For testing
			toServer.println(msg);
		} 
    	catch (IOException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	msg = null;
    }
    
    public static String readMessage()
    {
    	String message = "";
    	
    	try 
    	{
			BufferedReader in = new BufferedReader(new InputStreamReader(s2.getInputStream()));
        	message = in.readLine();
        	System.out.println("In read message: " + message);	//For testing
		} 
    	catch (IOException e)
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//Create reader from server
    	
    	return message;
    }
}
