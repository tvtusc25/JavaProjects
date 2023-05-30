//Name: Trey Tuscai
//Date: Oct. 24 2021
//File: SocialAgent.java
//Section: A
//Project: 4
//Course: CS231

package groupingBehaviors;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class SocialAgent extends Agent
{
	//variables
	protected boolean moved;
	protected int R;
	Color darkBlue = new Color(0, 0, 153);
	Color lightBlue = new Color(51, 204, 255);
	
	//The constructor, which calls the super class constructor and sets the radius field.
	public SocialAgent(double x0, double y0, int radius)
	{
		super(x0, y0);
		this.R = radius;
	}
	
	//sets the cell's radius of sensitivity to the value of radius.
	public void setRadius(int radius)
	{
		this.R = radius;
	}
	
	//returns the cell's radius of sensitivity.
	public int getRadius()
	{
		return this.R;
	}
	
	public void updateState( Landscape scape )
	{
		Random random = new Random();
		int numNeighbors = scape.getNeighbors(getX(), getY(), getRadius()).size();
		if (numNeighbors > 3)
		{
			if (random.nextInt(101) == 1)
			{
				//System.out.println("lucky: " + numNeighbors + "number of n");
				setX(getX() + (random.nextDouble()*20) - 10);
				setY(getY() + (random.nextDouble()*20) - 10);
				moved = true;
			}
			moved = false;
		}
		else
		{
			//System.out.println("real move: " + numNeighbors + "number of n");
			setX(getX() + (random.nextDouble()*20) - 10);
			setY(getY() + (random.nextDouble()*20) - 10);
			moved = true;
		}
	}	
	
	//draws a circle of radius 5 (i.e. it fits in a 10x10 box) at the Agent's location.
	public void draw(Graphics g)
	{
		if (!this.moved)
		{
			g.setColor(this.darkBlue);
		}
		else
		{
			g.setColor(this.lightBlue);
		}
		g.fillOval((int) x, (int) y, 10, 10);
	}
	
	/*public static void main(String[] args)
	{
		SocialAgent agent = new SocialAgent(5, 5, 10);
		System.out.println(agent.getX());
		System.out.println(agent.getY());
		agent.setX(3.024);
		agent.setY(4.245);
		agent.updateState();
		agent.draw(g);
		System.out.println(agent.getRadius());
		System.out.println(agent.setRadius(5));
		System.out.println(agent.toString());		
	}*/
	
}
