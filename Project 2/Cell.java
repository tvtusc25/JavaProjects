//Name: Trey Tuscai
//Date: Sept. 27 2021
//File: Cell.java
//Section: A
//Project: 2
//Course: CS231

package gameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Cell {

	//variables
	private boolean alive;
	private int neighborCount;
	
	//sets default Cell to dead
	public Cell () {
		alive = false;
		neighborCount = 0;
	}
	
	//specifies the Cell's state (true = alive | false = dead)
	public Cell(boolean alive) 
	{
		this.alive = alive;
	}
	
	//returns whether the cell is alive
	public boolean getAlive()
	{
		return this.alive;
	}
	
	//resets Cell to default (dead)
	public void reset()
	{
		this.alive = false;
	}
	
	//sets Cell's state
	public void setAlive( boolean alive )
	{
		this.alive = alive;
	}
	
	//draws the cell as a rectangle and fills with color
	public void draw( Graphics g, int x, int y, int scale )
	{
		//if alive cell is white
		if (getAlive() == true)
		{
			g.setColor(Color.white);
			g.fillRect(x, y, scale, scale);
		}
		else
		{
			//if dead cell is black
			g.setColor(Color.black);
			g.fillRect(x, y, scale, scale);
		}
					
	}
	
	
	public void updateState( ArrayList<Cell> neighbors )
	{
		//if cell is alive must have 2 or 3 to stay that way or it is dead
		if (getAlive()==true) 
		{
		for (int i = 0; i < neighbors.size(); i++)
		{
			if(neighbors.get(i).getAlive() == true)
			{
				neighborCount++;
			}
		}
		if (neighborCount >= 2 && neighborCount < 4)
			
			setAlive(true);
		else 
			setAlive(false);
		}
		
		//if cell is dead must have 3 neighbors or it stays dead
		if (getAlive()==false) 
		{
		for (int i = 0; i < neighbors.size(); i++)
		{
			if(neighbors.get(i).getAlive() == true)
			{
				neighborCount++;
			}
		}
		if (neighborCount == 3)
			setAlive(true);
		else
			setAlive(false);
		}
	}
	
	//returns the alive state of a Cell
	public String toString()
	{
		if (this.alive == false)
			return " ";
		else 
			return "0";
	}
	
	/* public static void main (String [] args)
	{
		Cell cellOne = new Cell(false);
		System.out.println(cellOne.toString());
		cellOne.setAlive(true);
		cellOne.getAlive();
		cellOne.updateState(neighbors);
		cellOne.draw(g, 10, 10, 10);
		System.out.println(cellOne.toString());
		cellOne.reset();
		System.out.println(cellOne.toString());
	}*/
}
