//Name: Trey Tuscai
//Date: Sept. 19 2021
//File: Deck.java
//Section: A
//Project: 1
//Course: CS231

package blackjack;

import java.util.ArrayList;
import java.util.*;
import java.util.Random;

public class Deck {
	
	//variables and objects
	private Random generator = new Random();
	private ArrayList<Card> deck;
	//private ArrayList<Card> deckShuffle;
	private ArrayList<Card> deckStorage;
	private Card temp = new Card();
	
	//sets deck object and size
	//sets deckStorage object
	public Deck()
	{
		deck = new ArrayList<Card>(52);
		//deckShuffle = new ArrayList<Card>(52);
		deckStorage = new ArrayList<Card>();
	}
	
	//assigns deck
	public Deck(ArrayList<Card> deck)
	{
		deck = new ArrayList<Card>(52);
		
	}
	
	//builds deck
	public void build()
	{
		//adds Cards to deck
		for(int card=0; card < 9;card++)
		{
			for (int suit= 0; suit <4; suit++)	
			{
				deck.add(suit, new Card(card+1));
			}
		}
		for(int card=9; card< 10;card++)
			for (int suit= 0; suit < 16; suit++)			
				deck.add(suit, new Card(card+1));
	}
	
	//returns deck size
	public int size()
	{
		return deck.size();
	}
	
	//return deck as a String
	public String toString()
	{
		return deck.toString();
	}
	
	//deals card and removes it from deck and puts it in a storage deck
	public Card deal()
	{
		//removes and returns first card
		temp = deck.get(0);
		deckStorage.add(temp);
		deck.remove(temp);
		return temp;
	}
	
	//returns removed cards to original deck
	//shuffles deck through swapping random card positions
	public void shuffle()
	{
		for (int i = 0; i < deckStorage.size(); i++)
		{
			Card reAdd = deckStorage.get(i);
			deck.add(reAdd);
			
		}
		
		//easy/faster swap shuffle used w3schools
		for (int i = deck.size()-1; i>=1; i--)
		{
			Collections.swap(deck, i, generator.nextInt(i + 1));
		}
		
		//couple different shuffles
		//Collections.shuffle(deck);
		/*move card to temporary deck while shuffling
		for (int i = 51; i > 0; i--)
		{
			
			Card numRemoved = deck.remove((int)(Math.random()*i));
			deckShuffle.add(numRemoved);
			
		}

		//refill original deck with shuffled cards
		for (int i = 51; i> 0; i--)
		{
			Card numRemoved = deckShuffle.remove((int)(Math.random()*i));
			deck.add(numRemoved);
		}
		
			Card firstCard = deck.remove(0);
			deck.add(generator.nextInt(52), firstCard);
		*/
		
	}
	
	/*public static void main(String[] args)
	{
		// will write code here later
		Deck deck = new Deck();
		deck.build();
		deck.shuffle();
		System.out.println(deck.toString());
		
	}*/
	
	

}
