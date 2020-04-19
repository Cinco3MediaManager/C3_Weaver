import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MediaTile extends JPanel
{
	JLabel coverArtLabel;
	Rectangle2D background;
	
	Color color;
	Color medGrey  = new Color(45,45,45);
	private int xLoc;
	private int yLoc;
	private int width;
	private int height;
	private int bgWidth = 200;
	private int bgHeight = 200;
	
	private int type;
	
	private String imageURL;
	
	private Book assignedBook;
	
	public MediaTile()
	{
		background = new Rectangle(0,0,bgWidth,bgHeight);
	}
	
	public MediaTile(int xLoc, int yLoc, int width, int height)
	{
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.width = width;
		this.height = height;
		
		color = medGrey;
		imageURL = "Book_Cover.jpg";
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		BufferedImage img = null;
		
		try
		{
			img = ImageIO.read(new File(imageURL));
		}
		catch(IOException e)
		{
			System.out.println("Error retrieving image from: " + imageURL);
		}
		
		g.setColor(color);
		g.fillRect(xLoc, yLoc, width, height);
		g.drawImage(img, xLoc+25, yLoc+25, color, null);
		
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public void setImagePath(String imageURL)
	{
		this.imageURL = imageURL;
	}
	
	public void assignBook(Book book)
	{
		assignedBook = book;
	}
	
	/*
	 * If type is included as a field use this. If not use individual 
	public void assignMediaItem(Object t)
	{
		if(t.type.equals("book"))
		{
			MediaObject = t;
		}
		if(t.type.equals("book"))
		{
			
		}
	}
	*/
}
