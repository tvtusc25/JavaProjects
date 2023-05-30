//Name: Trey Tuscai
//Date: Dec. 13 2021
//File: Hunter.java
//Section: A
//Project: 9
//Course: CS231

package huntTheWumpus;

import java.awt.Graphics;
import java.awt.Color;

public class Hunter 
{
	//fields
	private Vertex location;
	private int state;
	
	//constructor
	public Hunter(Vertex v)
	{
		this.location = v;
		this.location.setVisible(true);
		this.state = 1;
	}
	
	//returns vertex of hunter
	public Vertex getLocation()
	{
		return this.location;
	}
	
	//sets the location of hunter
	public void setLocation(Vertex loc)
	{
		this.location = loc;
	}
	
	//sets the hunters state
	public void setHunterState( int x )
	{
		this.state = x;
	}
	
	//returns the hunters state
	public int getHunterState()
	{
		return this.state;
	}
	
	
		
	//hunter movement
	public void move(Vertex.Direction dir)
	{
		switch (dir)
		{
		case NORTH:
			if (this.location.getNeighbor(location.getX(), location.getY()-1) != null)
			{
				this.setLocation(this.getLocation().getNeighbor(location.getX(), location.getY()-1));
				this.getLocation().setVisible(true);
			}
			break;
		case SOUTH:
			if (this.location.getNeighbor(location.getX(), location.getY()+1) != null)
			{
				this.setLocation(this.getLocation().getNeighbor(location.getX(), location.getY()+1));
				this.getLocation().setVisible(true);
			}
			break;
		case EAST:
			if (this.location.getNeighbor(location.getX()+1, location.getY()) != null)
			{
				this.setLocation(this.getLocation().getNeighbor(location.getX()+1, location.getY()));
				this.getLocation().setVisible(true);
			}
			break;
		case WEST:
			if (this.location.getNeighbor(location.getX()-1, location.getY()) != null)
			{
				this.setLocation(this.getLocation().getNeighbor(location.getX()-1, location.getY()));
				this.getLocation().setVisible(true);
			}
			break;
		}
	}
	
	//draws hunter
	public void draw(Graphics g , int x0 , int y0 , int scale )
	{
		int xpos = x0 + this.location.getX()*scale;
		int ypos = y0 + this.location.getY()*scale;
		int border = 2;
		
		
		//hunter shot and missed
		if( this.getHunterState() == -2 ) 
		{
			g.setColor(Color.black);
			g.drawString("Hunter shot and missed...",25,600);
		}
		
		//hunter shot and missed and dead
		if( this.getHunterState() == -1 ) 
		{
			g.setColor(Color.black);
			g.drawString("Hunter was killed by Wumpus...",25,600);
		}
		
		//if hunter is armed
		if(this.getHunterState() == 0){
			g.setColor(Color.red);
			g.fillRect( xpos+30,ypos+15, scale - 29*border,scale - 29 * border);      // Draw head
		      g.drawLine( xpos+32,ypos+20, xpos+32,ypos+25);     // Draw neck
		      g.fillRect( xpos+27,ypos+25, scale - 27*border,scale - 25 * border );    // Draw body
		      g.drawLine( xpos+27,ypos+25, xpos+22,ypos+40 );   // Draw right arm
		      g.drawLine( xpos+37,ypos+25, xpos+41,ypos+40 );    // Draw left arm
		      g.drawLine( xpos+29,ypos+38, xpos+29,ypos+50 );    // Draw left leg
		      g.drawLine( xpos+35,ypos+38, xpos+35,ypos+50 );   // Draw right leg
			g.setColor(Color.black);
			g.drawString("Hunter is armed...",25,600);
		}
		
		//if hunter is not armed nor eaten
		if (this.getHunterState() == 1)
		{
			g.setColor(Color.black);
			g.fillRect( xpos+30,ypos+15, scale - 29*border,scale - 29 * border);      // Draw head
		      g.drawLine( xpos+32,ypos+20, xpos+32,ypos+25);     // Draw neck
		      g.fillRect( xpos+27,ypos+25, scale - 27*border,scale - 25 * border );    // Draw body
		      g.drawLine( xpos+27,ypos+25, xpos+22,ypos+40 );   // Draw right arm
		      g.drawLine( xpos+37,ypos+25, xpos+41,ypos+40 );    // Draw left arm
		      g.drawLine( xpos+29,ypos+38, xpos+29,ypos+50 );    // Draw left leg
		      g.drawLine( xpos+35,ypos+38, xpos+35,ypos+50 );   // Draw right leg
			g.drawString("Hunter is unarmed...",25,600);
				
		}
		
		//if hunter won
		if(this.getHunterState() == 2)
		{
			g.setColor(Color.green);
			g.fillRect( xpos+30,ypos+15, scale - 29*border,scale - 29 * border);      // Draw head
		      g.drawLine( xpos+32,ypos+20, xpos+32,ypos+25);     // Draw neck
		      g.fillRect( xpos+27,ypos+25, scale - 27*border,scale - 25 * border );    // Draw body
		      g.drawLine( xpos+27,ypos+25, xpos+22,ypos+40 );   // Draw right arm
		      g.drawLine( xpos+37,ypos+25, xpos+41,ypos+40 );    // Draw left arm
		      g.drawLine( xpos+29,ypos+38, xpos+29,ypos+50 );    // Draw left leg
		      g.drawLine( xpos+35,ypos+38, xpos+35,ypos+50 );   // Draw right leg
			g.setColor(Color.black);
			g.drawString("Hunter wins...",25,600);
		}
	}
	
	public static void main(String[] args)
	{
		Vertex v1 = new Vertex();
		Hunter hunter = new Hunter(v1);
		Vertex v2 = new Vertex(1,0);
		v1.connect(v2);
		System.out.println(v1.getNeighbors());
		System.out.println(hunter.getLocation().getNeighbor(1,0));
		v2.connect(v1);
		hunter.move(Vertex.Direction.EAST);
		System.out.println(hunter.getLocation());
			
	}
}
