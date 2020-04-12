import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseAccessor 
{
	private String query;
	private String type;
	private String url;
	
	public DatabaseAccessor()
	{
		query = "";
		type = "media";
	}

	public DatabaseAccessor(String type)
	{
		if(type.equals("media"))
		{
		      url = "jdbc:sqlite:Media.db";
		}
		
		if(type.equals("user"))
		{
		      url = "jdbc:sqlite:Users.db";
		}
	}
	
	public String getBook()
	{
		String returnString = "";
		Connection conn = null;
		
		try 
		{
			url = "jdbc:sqlite:Media.db";		      
			conn = DriverManager.getConnection(url);

			Statement stmt = null;
			String query = "select * from Book";

			try
		      {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
		        
				int i = 0;
				//while (rs.next()) 
				//{
					String field1 = rs.getString("Title");		//Used with query1 for the name of an artist
					String field2 = rs.getString("Author");		//Used with query1 for the name of an artist
		            String field3 = rs.getString("Publisher");		//Used with query1 for the name of an artist
		            String field4 = rs.getString("Date");		//Used with query1 for the name of an artist
		            String field5 = rs.getString("Subject");		//Used with query1 for the name of an artist
		              
		            String concatString = field1 + "; " + field2 + "; " + field3 + "; " + field4 + "; " + field5;
		            System.out.println("Concatinating Book String: " + concatString);
		            returnString = concatString;
		            //i++;
		       // }
		          
		      } 
		      catch (SQLException e ) 
		      {
		          throw new Error("Problem", e);
		      } 
		      finally 
		      {
		          if (stmt != null) 
		          { 
		        	  stmt.close(); 
		          }
		      }

		    } 
		    catch (SQLException e) 
		    {
		        throw new Error("Problem", e);
		    }
		    finally 
		    {
		      try
		      {
		        if (conn != null) 
		        {
		            conn.close();
		        }
		      } 
		      catch (SQLException ex)
		      {
		          System.out.println(ex.getMessage());
		      }
		    }
		
		return returnString;
	}
}
