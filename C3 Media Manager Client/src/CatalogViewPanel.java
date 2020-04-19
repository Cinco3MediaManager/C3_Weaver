import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class CatalogViewPanel extends JPanel
{
	Line2D[] gridLinesX;
	Line2D[] gridLinesY;
	
	Color gray1 = new Color(25,25,25);
	Color gray2 = new Color(65,65,65);
	Color gray3 = new Color(85,85,85);
	Color gray4 = new Color(105,105,105);
	Color gray5 = new Color(125,125,125);

	MediaTile[] mediaTiles;
	
	private int panelSizeX;		//Width of the overall panel
	private int panelSizeY;		//Height of the overall panel
	private int tileWidth;		//Width of each grid tile
	private int tileHeight;		//Height of each grid tile
	private int numTilesX = 3;	//Number of tiles per row
	private int numTilesY = 3;	//Number of tiles per column
	private int padding = 25;	//Padding (in px) between tile and grid lines
	
	JLabel coverArt;
	
	public CatalogViewPanel()
	{
		panelSizeX = 1000;
		panelSizeY = 1000;
		
		tileWidth = (panelSizeX/numTilesX);
		tileHeight = (panelSizeY/numTilesY);
		
		populatePanel();
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setBackground(gray4);
		drawGrid(g);
		drawTiles(g);
	}
	
	private void populatePanel()
	{
		int numTiles = numTilesX*numTilesY;
		
		gridLinesX = new Line2D[numTilesY];
		gridLinesY = new Line2D[numTilesX];
		
		mediaTiles = new MediaTile[numTiles];
		
		for(int i = 0; i < numTilesX; i++)		//Draw the horizontal grid lines
		{
			int xLoc = (tileWidth + (i*tileWidth)); 
			gridLinesX[i] = new Line2D.Double(xLoc,0,xLoc,panelSizeY);
		}
		
		for(int i = 0; i < numTilesY; i++)		//Draw the vertical grid lines
		{
			int yLoc = (tileHeight + (i*tileHeight));
			gridLinesY[i] = new Line2D.Double(0,yLoc,panelSizeX,yLoc);
		}
		
		for(int i = 0; i < numTilesX; i++)		//Draw grid of media items
		{
			int yLoc = (15 + (i*tileHeight));	//Y location for this row

			for(int j = 0; j < numTilesY; j++)
			{
				int xLoc = (15 + (j*tileWidth));	//X location for this column
								
				mediaTiles[j + i*numTilesX] = new MediaTile(xLoc, yLoc, tileWidth-padding, tileHeight-padding);
			}
		}
		
		mediaTiles[1].setImagePath("Book_Cover2.jpg");
		mediaTiles[2].setImagePath("Book_Cover3.jpg");
		mediaTiles[3].setImagePath("Book_Cover4.jpg");
		mediaTiles[4].setImagePath("Book_Cover5.jpg");
		mediaTiles[5].setImagePath("Book_Cover6.jpg");
		mediaTiles[6].setImagePath("Book_Cover7.jpg");
		mediaTiles[7].setImagePath("Book_Cover8.jpg");
		mediaTiles[8].setImagePath("Book_Cover9.jpg");

	}
	
	private void drawGrid(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setStroke(new BasicStroke(2.0f,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));	
		
		g2d.setColor(Color.black);
		g2d.draw(gridLinesX[0]);
		g2d.draw(gridLinesX[1]);
		g2d.draw(gridLinesX[2]);
		
		g2d.draw(gridLinesY[0]);
		g2d.draw(gridLinesY[1]);
		g2d.draw(gridLinesY[2]);
	}

	private void drawTiles(Graphics g)
	{		
		mediaTiles[0].paintComponent(g);
		mediaTiles[5].paintComponent(g);
		
		mediaTiles[1].paintComponent(g);
		mediaTiles[6].paintComponent(g);
		
		mediaTiles[2].paintComponent(g);
		mediaTiles[7].paintComponent(g);
				
		mediaTiles[3].paintComponent(g);
		mediaTiles[8].paintComponent(g);
		
		mediaTiles[4].paintComponent(g);

	}

}
