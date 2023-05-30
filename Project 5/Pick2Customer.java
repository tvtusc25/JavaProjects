//Name: Trey Tuscai
//Date: Oct. 27 2021
//File: Pick2Customer.java
//Section: A
//Project: 5
//Course: CS231

package checkoutLines;

import java.util.ArrayList;
import java.util.Random;

public class Pick2Customer extends Customer
{
	public Pick2Customer( int num_items )
	{
		super(num_items, 2);
	}
	
	public int chooseLine(ArrayList<CheckoutAgent> checkouts)
	{
		Random gen = new Random();
		int rand1 = gen.nextInt(checkouts.size());
		int rand2 = gen.nextInt(checkouts.size());
		
		//ensures the to random lines are not the same
		while(rand1 == rand2)
		{
			rand1 = gen.nextInt(checkouts.size());
			rand2 = gen.nextInt(checkouts.size());
		}
		
		//finds the smaller line and returns it
		if (checkouts.get(rand1).getNumInQueue() >= checkouts.get(rand2).getNumInQueue())
		{
			return rand2;
		}
		
		return rand1;
	}
}
