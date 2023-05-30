//Name: Trey Tuscai
//Date: Oct. 27 2021
//File: Landscape.java
//Section: A
//Project: 5
//Course: CS231

package checkoutLines;

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Graphics;
import java.awt.Color;

public class Landscape 
{
	//variables
	private int height;
	private int width;
	ArrayList<CheckoutAgent> checkouts;
	LinkedList<Customer> finishedCustomers;
	
	//constructor. The list of finished customers should be initialized to an empty list.
	public Landscape(int w, int h, ArrayList<CheckoutAgent> checkouts)
	{
		this.width = w;
		this.height = h;
		this.checkouts = checkouts;
		finishedCustomers = new LinkedList<Customer>();
	}
	
	//return the height of the Landscape.
	public int getHeight()
	{
		return height;
	}
	
	//return the width of the Landscape.
	public int getWidth()
	{
		return width;
	}
	
	//return a string indicating how many checkouts and finished customers are in the landscape.
	public String toString()
	{
		return "Number of checkouts: " + checkouts.size() + ", Number of Finished Customers: " + finishedCustomers.size();
	}
	
	//add the Customer to the list of finished customers.
	public void addFinishedCustomer(Customer c )
	{
		finishedCustomers.addFirst(c);
	}
	
	//loop through the CheckoutAgents, calling the draw method on each.
	public void draw( Graphics g )
	{
		for (int i = 0; i < checkouts.size(); i++)
		{
			checkouts.get(i).draw(g);
		}
		
		Graphics2D g2d = (Graphics2D) g;// obtain the graphics context 
		AffineTransform at = new AffineTransform();
		at.rotate(Math.PI / 2); 
		g2d.setTransform(at);
		
		for (int i = 0; i < checkouts.size(); i++)
		{
			g.setColor(Color.BLUE);
			g2d.drawString("Items", 490, -65-(100*i));
			g.setColor(Color.RED);
			g2d.drawString("Customers", 490, -50-(100*i));
		}
		g2d.dispose();
	}
	
	//should loop through all of the CheckoutAgents, and call updateState.
	public void updateCheckouts()
	{
		for (int i = 0; i < checkouts.size(); i++)
		{
			checkouts.get(i).updateState(this);
		}
	}
	
	//compute and print the average and standard deviation of the time-to-leave for all of the Customers in the finished customer list.
	public void printFinishedCustomerStatistics()
	{
		//variables for total time and average time
		double totalSteps = 0.0;
		double avgTime = 0.0;
		ArrayList<Integer> getTimes = new ArrayList<Integer>();
		double standardD = 0.0;
		
		//adds all the time steps to totalSteps by iterating through linked list
		for(int i = 0; i < finishedCustomers.size(); i++)
		{
			getTimes.add(finishedCustomers.peek().getTime());
			totalSteps += finishedCustomers.remove(i).getTime();
		}
		
		//divides total steps by number of finished customers
		
		//standard deviation
		avgTime  =   totalSteps / finishedCustomers.size();
		for(int i = 0; i < getTimes.size(); i++)
		{
			standardD += Math.pow(getTimes.get(i)- avgTime, 2);
		}
		//System.out.println(standardD);
		//System.out.println(finishedCustomers.size());
		standardD = Math.sqrt(standardD/finishedCustomers.size());
		
		System.out.println("Average Time to Leave: " + avgTime + " time steps");
		System.out.println("Standard Deviation: " + standardD + " time steps");
		
		
	}
	
	
}
