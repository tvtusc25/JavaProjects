//Name: Trey Tuscai
//Date: Sept. 19 2021
//File: Hand.java
//Section: A
//Project: 1
//Course: CS231

package blackjack;

import java.util.ArrayList;

public class Hand {
	
	//variables and objects
	private ArrayList<Card> handList;
	private int total;

	//sets variable values
	//creates hand
	public Hand() 
	{
		this.total = 0;
		handList = new ArrayList<Card>();		
	}
	
	//assigns hand
	public Hand(ArrayList<Card> hand)
	{
		handList = hand;
	}
	
	//resets hand
	public void reset()
	{
		handList.clear();
	}
	
	//adds card to hand
	public void add( Card card )
	{
		handList.add(card);
	}
	
	//returns size of hand
	public int size()
	{
		return handList.size();
	}
	
	//gets a card from the hand
	public Card getCard( int i )
	{
		return handList.get(i);
	}
	
	//returns total value of hand in blackjack terms
	public int getTotalValue()
	{
		this.total=0;
		for (int i=0; i< handList.size(); i++)
		{
			int value = handList.get(i).getValue();
			this.total += value;
		}
		return this.total;
	}
	
	//returns hand as a String
	public String toString()
	{
		return handList.toString(); 
	}
	
	/* public static void main(String[] args)
	{
		Hand hand = new Hand();
		Hand hand2 = new Hand();
		Card myCard2 = new Card(10);
		Card myCard3 = new Card(7);
		Card myCard4 = new Card(8);
		Card myCard5 = new Card(8);
		hand.add(myCard2);
		hand.add(myCard3);
		hand2.add(myCard4);
		hand2.add(myCard5);
		hand2.reset();
		hand2.getCard(1);
		System.out.println(hand.size());
		System.out.println(hand.getTotalValue());
		System.out.println(hand2.toString());
	}
	*/
}

