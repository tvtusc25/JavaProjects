//Name: Trey Tuscai
//Date: Oct. 27 2021
//File: RandomCustomer.java
//Section: A
//Project: 5
//Course: CS231

package checkoutLines;

import java.util.ArrayList;
import java.util.Random;

public class RandomCustomer extends Customer
{
	//variables
	Random gen = new Random();
	
	//constructor. This should call the super class's constructor with the given number of items and 1 as the initial value for the time steps.
	public RandomCustomer( int num_items )
	{
		super(num_items, 1);
	}
	
	//returns an integer randomly chosen from the range 0 (inclusive) to the length of the list (exclusive).
	public int chooseLine(ArrayList<CheckoutAgent> checkouts)
	{
		return gen.nextInt(checkouts.size());
	}
}
