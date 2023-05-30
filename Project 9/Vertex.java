//Name: Trey Tuscai
//Date: Dec. 13 2021
//File: Vertex.java
//Section: A
//Project: 9
//Course: CS231

package huntTheWumpus;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class Vertex implements Comparable<Vertex>
{
	//public static final String Direction = null;
	
	
	//fields
	private ArrayList<Vertex> neighbors;
	private int xCoord;
	private int yCoord;
	private boolean visible;
	private double cost;
	private boolean visited;
	private Vertex parent;
	
	
	//constructor
	public Vertex()
	{
		this.neighbors = new ArrayList<Vertex>();
		this.xCoord = 0;
		this.yCoord = 0;
		this.visible = false;
		this.cost = Double.MAX_VALUE;
		this.visited = false;
		this.parent = null;
	}
	
	public Vertex(int x, int y)
	{
		this.neighbors = new ArrayList<Vertex>();
		this.xCoord = x;
		this.yCoord = y;
		this.visible = false;
		this.cost = Double.MAX_VALUE;
		this.visited = false;
		this.parent = null;
	}
	
	//constructor w/ coordinates
	public Vertex(int x, int y, boolean visited)
	{
		this.neighbors = new ArrayList<Vertex>();
		this.xCoord = x;
		this.yCoord = y;
		this.visible = false;
		this.cost = Double.MAX_VALUE;
		this.visited = visited;
		this.parent = null;
	}
	
	public enum Direction{NORTH, SOUTH, EAST, WEST}
	
	
	//returns x coordinate
	public int getX()
	{
		return xCoord;
	}
	
	//returns y coordinate
	public int getY()
	{
		return yCoord;
	}
	
	//set coordinates
	public void setPosition(int x, int y)
	{
		this.xCoord = x;
		this.yCoord = y;
	}
	
	//returns visibility
	public boolean getVisible()
	{
		return this.visible;
	}
	
	//set visibility
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
	
	//returns the Euclidean distance between this vertex and the other vertex based on their x and y positions.
	public double distance(Vertex other)
	{
		return Math.sqrt(Math.pow(this.yCoord - other.getY(), 2) + Math.pow(this.xCoord- other.getX(), 2));
	}
	
	//returns visited
	public boolean getVisited()
	{
		return this.visited;
	}
	
	//set visited
	public void setVisited(boolean visited)
	{
		this.visited = visited;
	}
	
	//returns parent Vertex
	public Vertex getParent()
	{
		return parent;
	}
	
	//set parent Vertex
	public void setParent(Vertex parent)
	{
		this.parent = parent;
	}
	
	//returns cost
	public double getCost()
	{
		return cost;
	}
	
	//set cost
	public void setCost(double cost)
	{
		this.cost = cost;
	}
	
	//updates this vertex' adjacency list/map so that it connects with the other Vertex. This is a uni-directional link.
	public void connect(Vertex other)
	{
		this.neighbors.add(other);
	}
	
	
	//returns the Vertex at position (x, y) if the Vertex is in the adjacency list, otherwise null.
	public Vertex getNeighbor(int x, int y)
	{
		for(Vertex vert: this.neighbors)
		{
			if(vert.getX() == x && vert.getY() == y)
			{
				return vert;
			}
		}
		return null;
	}

	
	//returns an ArrayList of type Vertex which contains all of this Vertex' neighbors.
	public ArrayList<Vertex> getNeighbors()
	{
		return this.neighbors;
	}
	
	//returns the number of connected vertices.
	public int numNeighbors()
	{
		return this.neighbors.size();
	}
	
	//public String toString()
	public String toString()
	{
		return "(" + this.getX() + "," + this.getY() + ") " + "Number of Neighbors: " + this.numNeighbors() + ", " + "Cost: " + cost + ", " + "Visited: " + visited;
	}
	
	//returns a value < 0 if this vertex comes before other, 0 if this is equal to other, and a value > 0 if this comes after other.
	public int compareTo(Vertex other) 
	{
		if(this.getCost() < other.getCost())
			return -1;
		else if (this.getCost() > other.getCost())
			return 1;
		return 0;
	}
	
	//returns true if the x and y positions of the two vertices match.
	public static boolean matchPosition( Vertex a, Vertex b )
	{
		if(a.getX() == b.getX() && a.getY() == b.getY())
			return true;
		return false;
			
	}
	
	//resets vertices
	public void reset() 
	{
		this.setVisited(false);
		this.setCost(Double.MAX_VALUE);
		this.setParent(null);
	}
	
	//draws the rooms
	public void draw(Graphics g, int scale) {
	        if (!this.visible)
	            return;
	        int xpos = (int)this.getX()*scale;
	        int ypos = (int)this.getY()*scale;
	        int border = 2;
	        int half = scale / 2;
	        int eighth = scale / 8;
	        int sixteenth = scale / 16;
	        
	        // draw rectangle for the walls of the room
	        if (this.getCost() <= 2)
	            // wumpus is nearby
	            g.setColor(Color.red);
	        else
	            // wumpus is not nearby
	            g.setColor(Color.black);
	        
	        g.drawRect(xpos + border, ypos + border, scale - 2*border, scale - 2 * border);
	        
	        // draw doorways as boxes
	        g.setColor(Color.black);
	        if (this.getNeighbor( this.getX(), this.getY()-1 ) != null )
	            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
	        if (this.getNeighbor( this.getX(), this.getY()+1 ) != null )
	            g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth), eighth, eighth + sixteenth);
	        if (this.getNeighbor( this.getX()-1, this.getY()) != null)
	            g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
	        if (this.getNeighbor( this.getX()+1, this.getY()) != null)
	            g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth, eighth + sixteenth, eighth);
	    }
	
	//main method/testing
	public static void main(String[] args)
	{
		Vertex vertex = new Vertex();
		//System.out.println(vertex.toString());
		Vertex vertex2 = new Vertex(-1, 5, false);
		vertex.connect(vertex2);
		System.out.println(vertex.distance(vertex2));
		//System.out.println(vertex.getNeighbors());
		//System.out.println(vertex2.getNeighbors());
		System.out.println(vertex.compareTo(vertex2));
		System.out.println(Vertex.Direction.NORTH);
		
		
	}
}
