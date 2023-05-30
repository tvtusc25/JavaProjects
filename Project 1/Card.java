//Name: Trey Tuscai
//Date: Sept. 19 2021
//File: Card.java
//Section: A
//Project: 1
//Course: CS231

package blackjack;

public class Card {
	
	//variables
	private int value;
	
	//sets value to 0
	public Card()
	{		
		this.value = 0;
	}
	
	//constructor that sets the value of card
	public Card (int val)
	{		
		this.value = val;
	}
	
	//returns the value
	public int getValue ()
	{		
		return this.value;
	}
	
	//returns value as a String
	public String toString()
	{
		return Integer.toString(this.value);
	}
	/*
	public static void main(String[] args)
	{
		// will write code here later
		//Card myCard2 = new Card(10);
		//System.out.println(myCard2);
		
	}
	*/

}
