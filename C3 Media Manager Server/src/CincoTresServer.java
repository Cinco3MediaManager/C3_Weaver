import java.net.*;
import java.text.*;
import java.util.Date;
import java.io.*;

public class CincoTresServer 
{
	final static int MAX_CLIENTS = 1;
	private static ClientHandler[] clientArray = new ClientHandler[MAX_CLIENTS];
	private static FileWriter logWriter;
	
    //Cinco Tres Server Main method
    public static void main(String[] args) throws IOException
    {
        int portNumber = 1234;					//1234 is, on most systems, a non-reserved port
        boolean sessionActive = true;			//Is the session active
        ServerSocket serverSocket = new ServerSocket(portNumber);	//Server Socket is a binding of IP address and Port Number
        Socket clientSocket;		//Client Socket is binding of client IP and Port
            
        logWriter = new FileWriter("SessionLog.txt");	//Log writer for session log using a file writer
        Date date;										//Date will be initialized after a client connects, with the current time/date
        SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");	//Format for the date. d = day, M = month, y = year, H = hour, m = minutes, s= seconds
        
        int id = 0;					//Counter for the number of currently connected clients				
        while(sessionActive)		//Loop while the session is active
        {
        	clientSocket = serverSocket.accept();			//When a client requests to connect, accept.
        	//System.out.println("Client request accepted");	//Log connection
        	date = new Date();
        	//Log Connection in log file
        	logWriter.write("Client Request Accepted from: " + clientSocket.getInetAddress().getHostAddress() + " at: " + dateForm.format(date) + " ID assigned: " + id);
        	logWriter.flush();
        	
        	PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);	//Output to client. PrintWriter by default uses TCP, so data delivery is reliable
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
        	ClientHandler clientHandler = new ClientHandler(id, out, in, clientSocket);
        	
        	Thread t = new Thread(clientHandler);
        	
        	if(id < MAX_CLIENTS)
        	{
        		clientArray[id] = clientHandler;
        		id++;
        		t.start();
        	}
        	else
        	{
            	//System.out.println("Maximum number of clients being handled");
            	logWriter.write("Maximum number of clients reached " + clientSocket.getInetAddress().getHostAddress() + " at: " + dateForm.format(new Date()));
            	logWriter.flush();
        	}
        	
        }
        serverSocket.close();
        sessionActive = false;
    }
    
    public static void writeToLog(String logEntry)
    {
    	try
    	{
			logWriter.write(logEntry);
			logWriter.flush();
		} 
    	catch (IOException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    		
    		dbA = new DatabaseAccessor("media");
    	}
	
    	@Override
    	public void run()
    	{
    		String input, output, command;
		
    		CincoTresProtocol ctp = new CincoTresProtocol();

            //command = ctp.processInput("&");
          //  out.println(command);			
            
    		while(sessionActive)
    		{
    			try
    			{
    				input = in.readLine();				//Incoming String
    				command = ctp.processInput(input);	//Command String returned by processInput(). ctp instance processes input and issues a command which CincoTresServer executes
					output = "";
					
					if(command.equals("Book"))
					{
						//System.out.println(dbA.getNextBook());
						
						output = dbA.getNextBook();
						
						//System.out.println(dbA.getNextBook());
						//output = dbA.getNextBook();
						
						//System.out.println(dbA.getNextBook());
						//output = dbA.getNextBook();
					}
					else if(command.equals("verified"))
					{
						CincoTresServer.writeToLog("\nClient: " + clientID + " login verified");
						output = "verified";
					}
					else if(command.equals("quit"))
					{
						sessionActive = false;
					}
					else
					{
						output = command;
					}
					
					out.println(output);
    			}
    			catch (IOException e)
    			{
    				System.out.println("IO Exception encountered in Client Handler");
    				sessionActive = false;
    			}
    		}
    		try
    		{
    			System.out.println("Closing Connection");
    			this.in.close();
    			this.out.close();
    			this.s.close();
    			sessionActive = false;
    		}
    		catch(IOException e)
    		{
    			
    		}
    	}
    }
