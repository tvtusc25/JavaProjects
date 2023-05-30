//Name: Trey Tuscai
//Date: Oct. 11 2021
//File: CellStack.java
//Section: A
//Project: 3
//Course: CS231

package sudoku;

public class CellStack {
	
	//variables
	Cell[] stack;
	private int top;
	private int capacity;
	
	//constructor
	public CellStack()
	{
		capacity = 5;
		stack = new Cell[5];
		this.top = 0;
	}
	
	//constructor w/ max capacity
	public CellStack(int max)
	{
		this.capacity = max;
		stack = new Cell[this.capacity];
		this.top = 0;
	}
	
	//pushes cell onto stack
	public void push(Cell c)
	{
		//expands by 2x if full
		if (this.isFull())
		{
			Cell[] temp = new Cell[capacity*2];
			this.capacity *= 2;
			
			for (int i = 0; i < this.top; i++)
			{
				temp[i] = this.stack[i];
			}
			this.stack = temp;
		}
		this.stack[top] = c;
		this.top++;
	}
	
	//pops Cell off the stack and returns it
	public Cell pop() 
	{
		//return null if empty
		if (this.isEmpty())
		{
			return null;
		}
		this.top--;
		Cell data = this.stack[this.top];
		return data;
	}
	
	//returns true if full
	public boolean isFull()
	{
		return (this.top +1 == this.capacity);
	}

	//returns true if empty
	public boolean isEmpty()
	{
		return (this.top == 0);
	}
	
	//returns size
	public int size()
	{
		return top;
	}
	
	//returns if empty
	public boolean empty()
	{
		
		if (this.top == 0)
			return true;
		return false;
	}
	
	//truns stack into string for easy visualization
	public String toString()
	{
		String stacker = "";
		if (stack.length > 0)
		{
		for (int i = 0; i < stack.length; i++)
		{
			stacker += " " + stack[i];
		}
		}
		return stacker;
	}
	
	/*public static void main (String [] args)
	{
		CellStack stack = new CellStack();
		Cell cell = new Cell(1, 0, 5);
		Cell cell1 = new Cell(2, 0, 6);
		Cell cell2 = new Cell(3, 0, 7);
		
		stack.push(cell);
		stack.push(cell1);
		stack.push(cell2);
		stack.push(cell);
		stack.push(cell1);
		stack.push(cell2);
		stack.push(cell);
		stack.push(cell1);
		stack.push(cell2);
		//System.out.println(stack.pop().getValue());
		//int row = stack.pop().getValue();
		//System.out.println(row);
		System.out.println(stack.toString());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
		
		
	}*/
}
