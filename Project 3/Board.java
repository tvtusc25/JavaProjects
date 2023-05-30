//Name: Trey Tuscai
//Date: Oct. 11 2021
//File: Board.java
//Section: A
//Project: 3
//Course: CS231

package sudoku;
import java.awt.Color;
import java.awt.Graphics;
import java.io.*;
//import java.util.Scanner;



public class Board {
	
	//variables
	public static final int Size = 9;
	private Cell[][] board;
	
	//constructor
	public Board()
	{
		this.board = new Cell[Board.Size][Board.Size];
		for (int row = 0; row < this.board.length; row++)
		         for (int col = 0; col < this.board[row].length; col++) 
		            this.board[row][col] = new Cell(row, col, 0);
		
	}
	
	//converts the 2d array into a readable string
	public String toString()
	{
		String boardString ="";
		for (int row = 0; row < this.board.length; row++) {
		         for (int col = 0; col < this.board[row].length; col++) 
		         {
		     	    boardString += this.board[row][col].getValue() + " ";
		     	    if (col == 2 || col == 5)
		     		    boardString += " ";
		         }
		         boardString += "\n";
		         if (row == 2 || row == 5)
		     	    boardString += "\n";
		}
		return boardString;
	            
	}
	
	//returns the number of columns
	public int getCols()
	{
		return Size;
	}
	
	//returns the number of rows
	public int getRows()
	{
		return Size;
	}
	
	//returns the cell at position (r, c)
	public Cell get(int r, int c)
	{
		return this.board[r][c];
	}
	
	//returns true if the cell at (r, c) is locked
	public boolean isLocked(int r, int c)
	{
		return this.board[r][c].isLocked();
	}
	
	//returns the number of locked cells
	public int numLocked()
	{
		int numLocked = 0;
		for (int row = 0; row < this.board.length; row++)
		{
		         for (int col = 0; col < this.board[row].length; col++) 
		         {
		     	    if (this.board[row][col].isLocked())
		     	    {
		     		    numLocked++;
		     	    }
		         }
		}
		return numLocked;
	}
	
	//returns the value of the cell at (r, c)
	public int value(int r, int c)
	{
		return this.board[r][c].getValue();
	}
	
	//sets the value of the cell at (r, c)
	public void set(int r, int c, int value)
	{
		this.board[r][c].setValue(value);
	}
	
	//sets the value and locked status of the cell at (r, c)
	public void set(int r, int c, int value, boolean locked)
	{
		this.board[r][c].setValue(value);
		this.board[r][c].setLocked(locked);
	}
	

	//returns true if the value is valid for the position according to row, col, & block
	public boolean validValue(int row, int col, int value)
	{
		// /Users/treytuscai/Desktop/board10solved.txt 
		//tests row & col
		for (int i = 0; i < 9; i++)
		{
			if(!(row ==i))
				if (value == this.board[i][col].getValue())
					return false;
			
			if (!(col == i))
				if (value == this.board[row][i].getValue())
					return false;
		}
		
		
		//tests block of 3x3
		int startRow = (row / 3) * 3;
		int startCol = (col / 3) * 3;
		    
		for(int i = startRow; i < startRow + 3; i++)
		    for(int j = startCol; j < startCol + 3; j++)
		        {
		     	  if (!(row==i && col == j))
		     		  if(this.board[i][j].getValue()==value)
		     			  return false;
		        }
		    	
		return true;	
	}
	
	//tests if each value on board is valid for row, col, & block and between 1 and 9
	public boolean validSolution()
	{
		for (int row = 0; row < this.board.length; row++)
		         for (int col = 0; col < this.board[row].length; col++) 
		            if (validValue(row,col, this.board[row][col].getValue()) == false || this.board[row][col].getValue() < 1 || this.board[row][col].getValue() > 9) 
		            {
		          	  return false;
		            }
		return true;
	}
	
	//finds the best cell to solve first
	public Cell findBestCell()
	{
		for(int r = 0; r < getRows(); r++)
		{
			for (int c = 0; c < getCols(); c++)
			{
				if (value(r, c) == 0 && isLocked(r, c) == false)
				{
					for (int i = 1; i <= 9; i++)
					{
						if (validValue(r, c, i))
						{
							set(r, c, i);
							//System.out.println("chosen cell" + board.get(r,c));
							return get(r,c);
						}
					}
					return null;
				}
			}
		}
		return null;
	}

	
	//reads files and splits up numbers into the board
	public boolean read(String filename) 
	{
			    try {

				    FileReader fileReader = new FileReader(filename);
				    BufferedReader bufferedReader = new BufferedReader(fileReader);
			    
				    String line = bufferedReader.readLine();
				    
				    //reads each line and splits it accoridng to spaces
				    while (line != null)
				    {
					    
					    //System.out.println(line);   
					    //line = bufferedReader.readLine(); 
					    for (int row = 0; row < this.board.length; row++) 
					    {
						    String[] splitArray = line.split("\\s+");
						    for (int col = 0; col < this.board[row].length; col++) 
						    {
							    	if(Integer.parseInt(splitArray[col]) > 0)
						            set(row, col, Integer.parseInt(splitArray[col]), true);
							    	set(row, col, Integer.parseInt(splitArray[col]));
						            
						    }
						    line = bufferedReader.readLine();
					    } 
					    
				    }
				    
				    bufferedReader.close();
				    return true;
			    }
			    catch(FileNotFoundException ex) {
			      System.out.println("Board.read():: unable to open file " + filename );
			    }
			    catch(IOException ex) {
			      System.out.println("Board.read():: error reading file " + filename);
			    }

			    return false;
		}
	
	//draws the board lines and each cell
	public void draw(Graphics g, int scale)
	{
		g.setColor(Color.white);
		int xDisplace = 40;
		int yDisplace = 40;
		
		for (int col = 0; col < 9; col++)
		{
			if (col == 0)
			{
				g.drawLine(25, 20, 25, 380);
			}
			g.drawLine(25+xDisplace, 20, 25+xDisplace, 380);
			xDisplace +=40;
		}
		
		for (int row = 0; row < 9; row++)
		{
			if (row == 0)
			{
				g.drawLine(25, 20, 385, 20);
			}
			g.drawLine(25, 20+yDisplace, 385, 20+yDisplace);
			yDisplace +=40;
		}
		
 	    
		for (int row = 0; row < this.board.length; row++) {
		         for (int col = 0; col < this.board[row].length; col++) 
		         {
		     	   // g.drawLine(0, 0, 0, 100);
		     	   // g.drawLine(0, 0, 0, 100);
		     	    board[col][row].draw(g, row*scale, col*scale, scale);
		         
		         }
		}
	}
	
		
		/* public static void main (String[] args)
		{
			Scanner keyboard = new Scanner(System.in);
			Board board = new Board();
			board.read(keyboard.next());
			System.out.println(board.toString());
			for (int i = 0; i <= 81; i++)
			System.out.println(board.findBestCell());
			System.out.println(board.toString());
			//System.out.println(board.get(0,1));
			//System.out.println(board.isLocked(0,1));
			//System.out.println(board.getCols());
			//System.out.println(board.getRows());
			//System.out.println(board.numLocked());
			//board.set(0,1,5);
			//board.set(0,1,5, true);
			//System.out.println(board.get(0,1));
			//System.out.println(board.value(0,1));
			//board.read(keyboard.next());
			//keyboard.close();
		}*/
	}
