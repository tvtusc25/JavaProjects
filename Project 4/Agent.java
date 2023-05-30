//Name: Trey Tuscai
//Date: Oct. 24 2021
//File: Agent.java
//Section: A
//Project: 4
//Course: CS231

package groupingBehaviors;

import java.awt.Graphics;

public class Agent 
{
	//variables;
	protected double x;
	protected double y;
	
	//a constructor that sets the position.
	public Agent(double x0, double y0)
	{
		this.x = x0;
		this.y = y0;	
	}
	
	//returns the x position.
	public double getX()
	{
		return this.x;
	}
	
	//returns the y position.
	public double getY()
	{
		return this.y;
	}
	
	//sets the x position.
	public void setX( double newX )
	{
		this.x = newX;
	}
	
	//sets the y position.
	public void setY( double newY )
	{
		this.y = newY;
	}
	
	//returns a String containing the x and y positions
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
	
	//does nothing, for now.
	public void updateState( Landscape scape )
	{
		
	}	
	
	//does nothing, for now.
	public void draw(Graphics g)
	{
	
	}

		// TODO Auto-generated method stub
	
	/*public static void main(String[] args)
	{
		Agent agent = new Agent(5.1, 6.1);
		System.out.println(agent.getX());
		System.out.println(agent.getY());
		agent.setX(3.024);
		agent.setY(4.245);
		System.out.println(agent.getX());
		System.out.println(agent.getY());
		System.out.println(agent.getX());
		System.out.println(agent.toString());		
	}*/
}
