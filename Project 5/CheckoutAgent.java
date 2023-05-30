//Name: Trey Tuscai
//Date: Oct. 27 2021
//File: CheckoutAgent.java
//Section: A
//Project: 5
//Course: CS231

package checkoutLines;

import java.awt.Graphics;
import java.awt.Color;

public class CheckoutAgent 
{
	//variables
	private int x;
	private int y;
	MyQueue<Customer> customers;

	//constructor. The queue should be initialized to an empty MyQueue<Customer>.
	public CheckoutAgent(int x, int y)
	{
		this.x = x;
		this.y = y;
		customers = new MyQueue<Customer>();
	}
	
	//add a Customer to its queue.
	public void addCustomerToQueue( Customer c )
	{
		customers.offer(c);
	}
	
	//returns the number of Customers in its queue.
	public int getNumInQueue()
	{
		return customers.getSize();
	}
	
	//returns x value
	public int getX()
	{
		return this.x;
	}
	
	//returns y value
	public int getY()
	{
		return this.y;
	}
	
	
	//draws the CheckoutAgent
	public void draw(Graphics g)
	{
		int N = this.getNumInQueue();
		g.setColor(Color.RED);
		g.fillRect(this.getX(), this.getY()-(N*10), 10, N*10);
		g.drawRect(this.getX(), this.getY()-(N*10), 10, N*10);
		
		if(customers.peek() != null)
		{
			g.setColor(Color.BLUE);
			g.fillRect(this.getX()+15, this.getY()-(customers.peek().getNumItems()*10), 10, customers.peek().getNumItems()*10);
		}
	}
	
	
	public void updateState( Landscape scape )
	{
		for(Customer cust : customers)
		{
			cust.incrementTime();
		}
		if(customers.peek() != null)
		{
			customers.peek().giveUpItem();
			if(customers.peek().getNumItems() == 0)
			{
				Customer rem = customers.poll();
				scape.addFinishedCustomer(rem);
			}
		}
	}
	/*public static void main(String[] args)
	{
		CheckoutAgent ca = new CheckoutAgent(0, 0);
		Graphics g = new Graphics();
		ca.addCustomerToQueue(customer);
		ca.getNumInQueue();
		ca.getX();
		ca.getY();
		ca.draw(g);
		ca.updateState(scape);
		
	}*/
	
}
