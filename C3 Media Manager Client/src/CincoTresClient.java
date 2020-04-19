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
        
     //   Socket s;								//IP and Port binding
        PrintWriter out;						//Writer to server
        BufferedReader in;						//Reader from server
        
        log = new LoginWindow();				//Login window object
        guiLaunched = false;					//has the main gui been launched
        
        try
        {
        	Socket s = new Socket(hostName, portNumber);	//Create Socket with IP and Port #s
        	s2 = s;
        	out = new PrintWriter(s.getOutputStream(), true);	//Create writer to server
        	in = new BufferedReader(new InputStreamReader(s.getInputStream()));	//Create reader from server
        	
        	BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));		//Currently Unused
        	

        	//Phase 1: Handshake
        	sendMessage("&1");
        	String response = readMessage();
        	
        	//Next send triggered by LoginWindow (for now). Send/read should (normally) occur in pairs
        	response = readMessage();
        	if(response.equals("verified"))
        	{
        		launchMemberGUI();
        	}
        		
        	//
        

        	Thread send = new Thread(new Runnable()			//Thread that handles sending items to server
        	{
        		@Override
        		public void run()
        		{ 
        			boolean sent = false; 
        			boolean sessionActive = true;
        			while(sessionActive)
        			{
        				try 
        				{
        					String message = "";
        					
        					if(!log.isFilledOut())
        					{
        						try 
        						{
        							Thread.sleep(1000);

        						}
        						catch(InterruptedException e)
        						{
        							
        						}
        					}
        					
        					if(log.isFilledOut() && sent == false)
        					{
        						//String username = log.getUserName();
        					//	String password = log.getPassword();
        					//	String loginPair = "*" + username + "#" + password;
        						
        					//	System.out.println("Username and Password Pair: " + loginPair);
        					//	message = loginPair;
        						sent = true;
        					}
        					
        					if(log.isExitClicked())
        					{
        						sessionActive = false;
        						message = "quit";
        					}
        					
        					out.println(message);
        					out.flush();
        				}
        				finally
        				{
        					
        				}
        			}
        			
        		}
        	});
        
        Thread read = new Thread(new Runnable()  
        { 
            @Override
            public void run() 
            { 
            	boolean sessionActive = true;
            	
                while (sessionActive) 
                { 
                    try 
                    { 
                        // read the message sent to this client 
                    	String message = in.readLine();
                    	
                    	if(message.equals("verified"))
                		{
                			if(guiLaunched == false)
                			{
                				//launchMemberGUI();
                			}
                		}
                    	
                    	else if(message.equals("quit"))
                		{
                			 sessionActive = false;
                		}
                    	else
                    	{
                    		System.out.println("Message Recieved from Server: " + message);
                    	}
                    } 
                    catch (IOException e) 
                    {
                    	
                    }
 
             }//End while
                
             try	    //Try to close connections
             {
            	 s.close();
            	 in.close();
            	 out.close();
             }
             catch (IOException e)
             {
            	 
             }
            } 
        }); 		//End of Thread read
        
      // read.start();
       //send.start();
      }

      catch(IOException e)
      {
    	  System.out.println("IO exception in client");
      }
      
    }
    
    private void launchMemberGUI()
    {
    	gui = new MemberGUI();
    	guiLaunched = true;
    }
    
    public static void sendMessage(String message)
    {
    	String msg = message; //Copy message for safety
    	
    	try
    	{
			PrintWriter toServer = new PrintWriter(s2.getOutputStream(),true);
			System.out.println("sendMessage Called with: " + msg);
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
        	System.out.println("In read message: " + message);
		} 
    	catch (IOException e)
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//Create reader from server
    	
    	return message;
    }
}