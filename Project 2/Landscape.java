//Name: Trey Tuscai
//Date: Sept. 27 2021
//File: Landscape.java
//Section: A
//Project: 2
//Course: CS231

package gameOfLife;

import java.awt.Graphics;
import java.util.ArrayList;

public class Landscape {
	
	//variables
	public Cell[][] landscape;
	private int rows;
	private int cols;
	
	//instantiates variables and landscape grid
	public Landscape (int rows, int cols)
	{
		this.rows = rows;
		this.cols = cols; 
		landscape = new Cell[rows][cols];
		for (int row = 0; row < landscape.length; row++)
		         for (int col = 0; col < landscape[row].length; col++) 
		            landscape[row][col] = new Cell();
	}
	
	//resets landscape
	public void reset()
	{
		for (int row = 0; row < landscape.length; row++)
		         for (int col = 0; col < landscape[row].length; col++) 
		     	    landscape[row][col].reset();
	}
	
	//returns num of rows
	public int getRows()
	{
		return this.rows;
	}
	
	//returns num of cols
	public int getCols()
	{
		return this.cols;
	}
	
	//returns the Cell
	public Cell getCell( int row, int col)
	{
		return landscape[row][col];
	}
	
	//draws the Cells
	public void draw(Graphics g, int gridScale )
	{
		for (int row = 0; row < landscape.length; row++)
		         for (int col = 0; col < landscape[row].length; col++) 
		     	    landscape[row][col].draw(g, row*gridScale, col*gridScale, gridScale);
	}
	
	//prints landscape as String
	public String toString()
	{
		for (int row = 0; row < landscape.length; row++) 
		{ 
		         for (int col = 0; col < landscape[row].length; col++) 
		         {
		            System.out.print(landscape[row][col] + " ");
		         }
		         System.out.print("\n");
		}
		return "text image";
	}
	
	//finds the num of neighbors 
	//accounts if Cell doesn't have all neighbors
	public ArrayList<Cell> getNeighbors( int row, int col )
	{
		ArrayList<Cell> result = new ArrayList<Cell>();
		
		for (int i = row-1; i< row+2; i++)
		{
			for(int j = col-1; j< col+2; j++)
			{
				if (i>-1 && i<landscape.length && j>-1 && j<landscape[i].length) 
					if (!(row == i && col == j))
				{
					result.add(getCell(i, j));
				}
			}
		}
		return result;
	}
	
	//advances the landscape by creating a temporary landscape filled with the alive states of the 
	//original landscape and updating each cell based on its num of neighbors
	public void advance()
	{
		Landscape landscapeTemp = new Landscape(this.rows, this.cols);
		
		for (int row = 0; row < landscape.length; row++) 
		{ 
		         for (int col = 0; col < landscape[row].length; col++) 
		         {
		     	    landscapeTemp.landscape[row][col] = new Cell(landscape[row][col].getAlive());
		         }    
		}
		
		for (int row = 0; row < landscapeTemp.getRows(); row++) 
		{ 
		         for (int col = 0; col < landscapeTemp.getCols(); col++) 
		         {
		     	    landscapeTemp.landscape[row][col].updateState(landscapeTemp.getNeighbors(row, col));
		         }    
		}
			this.landscape = landscapeTemp.landscape;
	}
	
	/*public static void main (String[] args)
	{
		Landscape landscape = new Landscape(10,10);
		landscape.getCell(0,0).setAlive(true);
		landscape.getCell(0,1).setAlive(true);
		landscape.getCell(0,2).setAlive(true);
		landscape.draw(g,10);
		System.out.println(landscape.getRows());
		System.out.println(landscape.getCols());
		System.out.println(landscape.toString());
		System.out.println(landscape.getNeighbors(1,1));
		landscape.advance();
		System.out.println(landscape.toString());
		
	}*/
	
}
