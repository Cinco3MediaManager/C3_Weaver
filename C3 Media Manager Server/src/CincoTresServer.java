import java.net.*;
import java.io.*;

public class CincoTresServer 
{
	final static int MAX_CLIENTS = 1;
	private static ClientHandler[] clientArray = new ClientHandler[MAX_CLIENTS];
	
    //Cinco Tres Server Main method
    public static void main(String[] args) throws IOException
    {
        int portNumber = 1234;					//1234 is, on most systems, a non-reserved port
        boolean sessionActive = true;			//Is the session active
        ServerSocket serverSocket = new ServerSocket(portNumber);	//Server Socket is a binding of IP address and Port Number
        Socket clientSocket;		//Client Socket is binding of client IP and Port
                
        int i = 0;					//Counter for the number of currently connected clients				
        
        while(sessionActive)		//Loop while the session is active
        {
        	clientSocket = serverSocket.accept();			//When a client requests to connect, accept.
        	System.out.println("Client request accepted");	//Log connection
        	
        	PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);	//Output to client
            	BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
        	System.out.println("Creating new Client Handler");
        	ClientHandler clientHandler = new ClientHandler(i, out, in, clientSocket);
        	
        	Thread t = new Thread(clientHandler);
        	System.out.println("Adding client to list of handled clients");
        	
        	if(i < MAX_CLIENTS)
        	{
        		clientArray[i] = clientHandler;
        		i++;
        		t.start();
        	}
        	else
        	{
            	System.out.println("Maximum number of clients being handled");
        	}
        	
        }
        serverSocket.close();
    }
}

    class ClientHandler implements Runnable
    {
    	private int clientID;
    	PrintWriter out;
    	BufferedReader in;
    	Socket s;
    	boolean sessionActive;
    	
        DatabaseAccessor dbA;
	
    	public ClientHandler(int clientID, PrintWriter out, BufferedReader in, Socket s)
    	{
    		this.clientID = clientID;
    		this.out = out;
    		this.in = in;
    		this.s = s;
    		this.sessionActive = true;
    		
    		dbA = new DatabaseAccessor();
    	}
	
    	@Override
    	public void run()
    	{
    		String input, output;
		
    		CincoTresProtocol ctp = new CincoTresProtocol();

            output = ctp.processInput("&");
            out.println(output);			
            
    		while(sessionActive)
    		{
    			try
    			{
    				input = in.readLine();
    				output = ctp.processInput(input);
					
					if(output.equals("Book"))
					{
						System.out.println(dbA.getBook());
						output = dbA.getBook();
					}
					
					System.out.println("Found outout ptinting: "+ output);
					out.println(output);
    			}
    			catch (IOException e)
    			{
    				System.out.println("IO Exception encountered in Client Handler");
    			}
    		}
    		try
    		{
    			System.out.println("Closing Connection");
    			this.in.close();
    			this.out.close();
    			this.s.close();
    		}
    		catch(IOException e)
    		{
    			
    		}
    	}
    }

