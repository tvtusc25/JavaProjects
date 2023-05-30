//Name: Trey Tuscai
//Date: Oct. 11 2021
//File: Cell.java
//Section: A
//Project: 3
//Course: CS231

package sudoku;
import java.awt.Color;
import java.awt.Graphics;

public class Cell
{
	
	//variables
	int rowIndex;
	int colIndex;
	int value;
	boolean locked;
	
	//constructor
	public Cell()
	{
		this.rowIndex = 0;
		this.colIndex = 0;
		this.value = 0;
		this.locked = false;
	}

	//initializes variables to parameter values w/ locked set to false
	public Cell(int row, int col, int value)
	{
		this.rowIndex = row;
		this.colIndex = col;
		this.value = value;
		this.locked = false;	
	}
	
	////initializes variables to parameter values w/ locked
	public Cell(int row, int col, int value, boolean locked)
	{
		this.rowIndex = row;
		this.colIndex = col;
		this.value = value;
		this.locked = locked;	
	}
	
	//returns rows
	public int getRow()
	{
		return this.rowIndex;
	}
	
	//returns columns
	public int getCol()
	{
		return this.colIndex;
	}
	
	//returns value
	public int getValue()
	{
		return this.value;
	}
	
	//sets value
	public void setValue(int newval)
	{
		this.value = newval;
	}
	
	//returns locked
	public boolean isLocked()
	{
		return this.locked;
	}
	
	//sets locked
	public void setLocked(boolean lock)
	{
		this.locked = lock;
	}
	
	//clones cell
	public Cell Clone()
	{
		Cell Clone = new Cell(getRow(), getCol(), getValue(), isLocked());
		return Clone;
		
	}
	
	//returns cells as a string with row, col, value, and if locked 
	public String toString()
	{
		return "position: " + getRow() + ", " + getCol() + " value: " + getValue() + " locked: " + isLocked();
	}
	
	//draws the numbers and sets their colors
	public void draw(Graphics g, int x0, int y0, int scale) 
	{

		//Colors w/ numbers
		char[] data = {(char)('0' + this.value)};
		if (this.value == 1)
			g.setColor(Color.red);
		if (this.value == 2)
			g.setColor(Color.green);
		if (this.value == 3)
			g.setColor(Color.blue);
		if (this.value == 4)
			g.setColor(Color.magenta);
		if (this.value == 5)
			g.setColor(Color.orange);
		if (this.value == 6)
			g.setColor(Color.pink);
		if (this.value == 7)
			g.setColor(Color.cyan);
		if (this.value == 8)
			g.setColor(Color.darkGray);
		if (this.value == 9)
			g.setColor(Color.lightGray);
		
		g.drawChars(data, 0, data.length, x0+40, y0+40);
		//g.drawString(Integer.toString(getValue()), x0, y0+10);
	}
	
	/*public static void main (String[] args)
	{
		Cell newCell = new Cell();
		Cell newCell1 = new Cell(0, 0, 1);
		Cell newCell2 = new Cell(0, 1, 2, true); 
		System.out.println(newCell.getRow());
		System.out.println(newCell.getCol());
		System.out.println(newCell.getValue());
		System.out.println(newCell.setValue(3));
		System.out.println(newCell.isLocked());
		System.out.println(newCell.setLocked(true));
		Clone();
		System.out.println(newCell.toString());
		draw(g, 0, 0, 30);
	}*/
}
	
	
	

