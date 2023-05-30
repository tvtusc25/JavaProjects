//Name: Trey Tuscai
//Date: Oct. 27 2021
//File: PickyCustomer.java
//Section: A
//Project: 5
//Course: CS231

package checkoutLines;

import java.util.ArrayList;

public class PickyCustomer extends Customer
{
	public PickyCustomer( int num_items, int num_lines )
	{
		super(num_items, num_lines);
	}
	
	public int chooseLine(ArrayList<CheckoutAgent> checkouts)
	{
		int shortestLine = Integer.MAX_VALUE;
		int lineNum = 0;
		for(int i = 0; i < checkouts.size(); i++)
		{
			if(checkouts.get(i).getNumInQueue() <= shortestLine)
				{
					shortestLine = checkouts.get(i).getNumInQueue();
					lineNum = i;
				}
		}
		return lineNum;
	}
}
