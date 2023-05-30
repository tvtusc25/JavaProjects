package huntTheWumpus;

//Name: Trey Tuscai
//Date: Dec. 13 2021
//File: Wumpus.java
//Section: A
//Project: 9
//Course: CS231

import java.awt.Graphics;
import java.awt.Color;

public class Wumpus 
{
	//fields
	private Vertex home;
	private int state;
	
	//constructor
	public Wumpus(Vertex vertex)
	{
		this.home = vertex;
		this.home.setVisible(false);
		this.state = 0;
	}
	
	//returns the home node of the wumpus
	public Vertex getHome()
	{
		return this.home;
	}
	
	//sets the visibility
	public void setVisible(boolean visible)
	{
		this.home.setVisible(visible);
	}
	
	//returns true if visible
	public boolean getVisible()
	{
		return this.home.getVisible();
	}

	//sets wumpus status
	public void setLifeStatus(int state)
	{
		this.state = state;
	}
	
	//returns wumpus status
	public int getLifeStatus()
	{
		return this.state;
	}
	
	//draws the wumpus
	public void draw( Graphics g , int x0 , int y0 , int scale )
	{
		int xpos = x0 + this.getHome().getX()*scale;
		int ypos = y0 + this.getHome().getY()*scale;
		int border = 2;
		
		
			//wumpus is red if dead
			if (this.getVisible() == true && this.getLifeStatus() == -1)
			{
				//wumpus is near
				g.setColor(Color.red);
				g.fillRect(xpos+20, ypos+20, scale - 20*border, scale - 20 * border);
				g.setColor(Color.black);
				g.drawString("Wumpus dies...",25,615);
			}
			
			//wumpus is green if wins
			if (this.getVisible() == true && this.getLifeStatus() == 1)
			{
				//wumpus is near
				g.setColor(Color.green);
				g.fillRect(xpos+20, ypos+20, scale - 20*border, scale - 20 * border);
				g.setColor(Color.black);
				g.drawString("Wumpus wins..",25,615);
			}
			
			if( this.getVisible() == false ) 
			{
				return;
			}
	}
}
