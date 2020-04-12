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
	private int xLoc;
	private int yLoc;
	private int width;
	private int height;
	
	private String imageURL;
	
	public MediaTile()
	{
		background = new Rectangle(0,0,200,200);
	}
	
	public MediaTile(int xLoc, int yLoc, int width, int height)
	{
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.width = width;
		this.height = height;
		
		color = new Color(45,45,45);
		imageURL = "Book_Cover.jpg";
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		BufferedImage img = null;
		try
		{
			//img = ImageIO.read(new File("Book_Icon_Default.png"));
			img = ImageIO.read(new File(imageURL));
		}
		catch(IOException e)
		{
			
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
}

