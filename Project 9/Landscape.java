//Name: Trey Tuscai
//Date: Dec. 13 2021
//File: Landscape.java
//Section: A
//Project: 9
//Course: CS231

package huntTheWumpus;

import java.util.ArrayList;
import java.awt.Graphics;

public class Landscape 
{
	//variables
	private int width;
	private int height;
	LinkedList<Vertex> vertices;
	Hunter hunter;
	Wumpus wumpus;
	
	//a constructor that sets the width and height fields, and initializes the agent list.
	public Landscape(int w, int h)
	{
		width = w;
		height = h;
		vertices = new LinkedList<Vertex>();
		hunter = null;
		wumpus = null;
	}
	
	//sets the hunter
	public void setHunter(Hunter v)
	{
		this.hunter = v;
	}
	
	//returns the hunter
	public Hunter getHunter()
	{
		return this.hunter;
	}
	
	//sets the wumpus
	public void setWumpus(Wumpus v)
	{
		this.wumpus = v;
	}
	
	//returns the wumpus
	public Wumpus getWumpus()
	{
		return this.wumpus;
	}
	
	//returns the height.
	public int getHeight()
	{
		return this.height;
	}
	
	//returns the width.
	public int getWidth()
	{
		return this.width;
	}
	
	//inserts an agent at the beginning of its list of agents.
	public void addBackgroundAgent(Vertex a)
	{
		this.vertices.addFirst(a);
	}
	
	//returns a String representing the Landscape
	public String toString()
	{
		return "Number of Vertices: " + this.vertices.size();
	}
	
	//draws the landscape
	public void draw(Graphics g, int scale)
	{
		ArrayList<Vertex> vertArray = vertices.toArrayList();
		for(int i = 0; i < vertArray.size(); i++)
		{
			vertArray.get(i).draw(g, scale);
		}
		
		//hunter.move(Vertex.Direction.SOUTH);
		hunter.draw(g, 0, 0, scale);
		wumpus.draw(g, 0, 0, scale);
		
		
	}
}