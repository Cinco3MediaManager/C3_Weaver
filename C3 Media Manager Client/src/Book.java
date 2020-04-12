import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

//Test class for Book object data structure
public class Book
{
	private String title;
	private String author;
	private String subject;
	private String date;
	private String publisher;
	private BufferedImage coverPhoto;
	private BufferedImage defaultImage;
	
	public Book()
	{
		title = "Default_Title";
		author = "Default_Author";
		subject = "Default_Subject";
		date = "00/00/00";					// mm/dd/year
		publisher = "Default_Publisher";			
	}
	
	public Book(String title, String author, String subject, String date, String publisher)
	{
		this.title = title; 
		this.author = author;
		this.subject = subject;
		this.date = date;					// mm/dd/year
		this.publisher = publisher;			
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public void setDate(String date)
	{
		this.date = date;					// mm/dd/year
	}
	
	public void setPublisher(String publisher)
	{
		this.publisher = publisher;			
	}
	
	public void setCoverImage(String imageURL)
	{
		try
		{
			coverPhoto = ImageIO.read(new File(imageURL));
		}
		catch(IOException e)
		{
			System.out.println("Error Fetching Image. Setting To default");
			coverPhoto = defaultImage;
		}
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public String getSubject()
	{
		return subject;
	}
	
	public String getDate()
	{
		return date;					// mm/dd/year
	}
	
	public String getPublisher()
	{
		return publisher;			
	}

}

