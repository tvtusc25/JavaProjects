//Name: Trey Tuscai
//Date: Oct. 27 2021
//File: Customer.java
//Section: A
//Project: 5
//Course: CS231

package checkoutLines;

import java.awt.Graphics;
import java.util.ArrayList;

abstract class Customer 
{
	private int num_Items;
	private int time_steps;
	
	//constructor method. (By default, the number of time steps is zero.)
	public Customer(int num_Items)
	{
		this.num_Items = num_Items;
		this.time_steps = 0;
	}
	
	//constructor method.
	public Customer(int num_Items, int time_steps)
	{
		this.num_Items = num_Items;
		this.time_steps = time_steps;
	}
	
	//increments the number of time steps.
	public void incrementTime()
	{
		time_steps++;
	}
	
	//returns the number of time steps
	public int getTime()
	{
		return time_steps;
	}
	
	//decrements the number of items (indicating another item has been paid for).
	public void giveUpItem()
	{
		num_Items--;
	}
	
	//returns the number of items.
	public int getNumItems()
	{
		return num_Items;
	}
	
	public void drawItems(Graphics g)
	{
		g.drawRect(10, this.getNumItems(), 10, 10);
	}
	
	public abstract int chooseLine(ArrayList<CheckoutAgent> checkouts);
}
