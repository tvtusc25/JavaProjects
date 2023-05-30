//Name: Trey Tuscai
//Date: Oct. 24 2021
//File: CatSocialAgent.java
//Section: A
//Project: 4
//Course: CS231

package groupingBehaviors;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class CatSocialAgent extends SocialAgent
{
	//variables
	private int category;
	Color lightRed = new Color(255, 200, 200);
	Color darkRed = new Color(255, 0, 0);
	
	//calls the parent constructor and set the category.
	public CatSocialAgent(double x0, double y0, int radius, int cat)
	{
		super(x0, y0, radius);
		category = cat;
	}
	
	//returns the category value.
	public int getCategory()
	{
		return category;
	}
	
	//returns a single character string indicating the category.
	public String toString()
	{
		return Integer.toString(category);
	}
	
	//draws a circle of radius 5 (i.e. it fits in a 10x10 box) at the Agent's location. 
	//Different categories should have different colors
	public void draw(Graphics g)
	{
		if (category == 0)
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
		
		if (category == 1)
		{
			if (!this.moved)
			{
				g.setColor(this.darkRed);
			}
			else
			{
				g.setColor(this.lightRed);
			}
		g.fillOval((int) x, (int) y, 10, 10);
		}
		
		if (category == 2)
		{
			if (!this.moved)
			{
				g.setColor(Color.darkGray);
			}
			else
			{
				g.setColor(Color.lightGray);
			}
		g.fillOval((int) x, (int) y, 10, 10);
		}
	}
	
	//implements the following rule.
	//different rules for categories
	public void updateState(Landscape scape)
	{
		Random random = new Random();
		int catNum0 = 0;
		int catNum1 = 0;
		int catNum2 = 0;
		
		int numNeighbors = scape.getNeighbors(getX(), getY(), getRadius()).size();
		ArrayList<Agent> neighbors = scape.getNeighbors(getX(), getY(), getRadius());
		
		for(int i = 0; i < numNeighbors; i++)
		{
			CatSocialAgent catAg = (CatSocialAgent) neighbors.get(i);
			
			if(catAg.getCategory() == 0)
			{
				catNum0++;
			}
			if(catAg.getCategory() == 1)
			{
				catNum1++;
			}
			if(catAg.getCategory() == 2)
			{
				catNum2++;
			}
			
		}
		
		if (numNeighbors >= 3 && (this.getCategory() == 0  && catNum0 > catNum1 + catNum2  || this.getCategory() == 1 && catNum1 > catNum0 + catNum2 || this.getCategory() == 2 && catNum2 > catNum0 + catNum1))
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
	
	/*public static void main(String[] args)
	{
		CatSocialAgent agent = new CatSocialAgent(5, 5, 10, 0);
		System.out.println(agent.getCategory());
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
