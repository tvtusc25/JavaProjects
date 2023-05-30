//Name: Trey Tuscai
//Date: Oct. 11 2021
//File: Sudoku.java
//Section: A
//Project: 3
//Course: CS231

package sudoku;
import java.util.*;


public class Sudoku {
	
	//variables & uses Scanner
	private Board board;
	private Random gen;
	private boolean necessary = true;
	Scanner keyboard = new Scanner(System.in);
	LandscapeDisplay display;
	
	//constructor
	public Sudoku()
	{
		//reads Scanner for board
		this.board = new Board();
		board.read(keyboard.next());
		this.display = new LandscapeDisplay(board, 40);
	}
	
	//constructor w/ number of randomized cells
	public Sudoku(int n)
	{
		
		gen = new Random();
		int rowRandom = gen.nextInt(9);
		int colRandom = gen.nextInt(9);
		int numRandom = (gen.nextInt(9)+1); 
		this.board = new Board();
		
		//randomizes cells
		while(board.numLocked() < n)
		{
			if (this.board.value(rowRandom, colRandom) == 0 && this.board.validValue(rowRandom, colRandom, numRandom) == true)
			{
	              this.board.set(rowRandom, colRandom, numRandom, true);
	              //System.out.println(board.get(rowRandom, colRandom));
			}
			else
			{
				rowRandom = gen.nextInt(9);
				colRandom = gen.nextInt(9);
				numRandom = (gen.nextInt(9)+1); 
			}
			
	     }
		this.display = new LandscapeDisplay(board, 30);
		
	}
	
	
	//solves board through use of stack
	public boolean solve(int delay)
	{
		
		CellStack stack = new CellStack();
		int numLockedCells = this.board.numLocked();
		
		//solves by going through stack
		while (stack.size() < (81-numLockedCells))
		{
			//delay for visualization
			if( delay > 0 ) 
			{
			        try 
			        {
			            Thread.sleep(delay);
			        }
			        catch(InterruptedException ex) 
			        {
			            System.out.println("Interrupted");
			        }
			        
			        display.repaint();
			        //System.out.println("reached");
			    }
			
			//finds best cell to solve
			Cell next = this.board.findBestCell();
			//System.out.println(stack.toString());
			
			//if it finds one then it pushes it onto the stack
			if (next != null)
			{
				stack.push(next);
			}
			else 
			{
				necessary = true;
				
				//if wrong
				while (necessary == true && stack.size() > 0)
				{
					//System.out.println("made it to 2nd while");
					Cell popped = stack.pop();
					//System.out.println(popped);
					//System.out.println(stack.size());
					int row = popped.getRow();
					int col = popped.getCol();
					
					//if wrong pops the cell and searches for new value
					for (int i = popped.getValue()+1; i <= 9; i++)
					{
						if(this.board.validValue(row, col, i))
						{
							this.board.set(row, col, i);
							stack.push(popped);
							//System.out.println(board.toString());
							necessary = false;
							break;
						}
					}
					
					//sets value to zero if it can't find a value
					if (necessary)
					{
						board.set(row, col, 0);
					}
				
				}
			}
			
			//if its unsolveable return false
			if (stack.size() == 0)
			{
				return false;
			}	
		}
		
		//if solves return true
		return true;
		}

	
	//returns board as a string
	public String toString()
	{
		return board.toString();
	}
	
		
	
	public static void main(String[] args)
	{
		Sudoku game = new Sudoku();
		game.solve(50);
		System.out.println(game.toString());
	}

}
