//Name: Trey Tuscai
//Date: Sept. 19 2021
//File: Blackjack.java
//Section: A
//Project: 1
//Course: CS231

package blackjack;

import java.util.Scanner;

public class Blackjack {
	
	//variables and objects
	private int reshuffle;
	private Deck deck;
	private Hand player;
	private Hand dealer;
	private boolean start = true;
	private int playerWin;
	private int dealerWin;
	private int push;
	Scanner keyboard;
	
	//assigns variables
	//builds deck and shuffles it
	public Blackjack(int reshuffleCutoff)
	{
		this.reshuffle = reshuffleCutoff;
		deck = new Deck();
		deck.build();
		deck.shuffle();
		player = new Hand();
		dealer = new Hand();
		this.playerWin = 0;
		this.dealerWin = 0;
		this.push = 0;
		keyboard = new Scanner(System.in);
	}
	
	//returns player win count
	public int getPlayerWin()
	{
		return this.playerWin;
	}
	
	//returns dealer win count
	public int getDealerWin()
	{
		return this.dealerWin;
	}
	
	//returns push count
	public int getPush()
	{
		return this.push;
	}
	
	//resets hand and shuffles when too many cards are used
	public void reset()
	{
		player.reset();
		dealer.reset();
		if (deck.size() < this.reshuffle)
			deck.shuffle();
	}
	
	//deals to both player and dealer
	public void deal()
	{
		player.add(deck.deal());
		dealer.add(deck.deal());
		player.add(deck.deal());
		dealer.add(deck.deal());
	}
	
	//players turn whether to hit or stand
	public boolean playerTurn()
	{ 
		while (player.getTotalValue() <= 16)
			player.add(deck.deal());
		
		if(player.getTotalValue() >21)
			return false;
			return true;
	}
	
	//dealers turn whether to hit or stand
	public boolean dealerTurn()
	{
		while (dealer.getTotalValue() <= 17)
			dealer.add(deck.deal());
		if(dealer.getTotalValue() >21)
			return false;
			return true;
	}
	
	//when to reshuffle
	public void setReshuffleCutoff(int cutoff)
	{
		this.reshuffle = cutoff;
	}
	
	//returns when to reshuffle
	public int getReshuffleCutoff()
	{
		return this.reshuffle;
	}
	
	//returns game
	public String toString()
	{
		return "|Player Cards: " + player.toString() + " total: " + player.getTotalValue() + "| Dealer Cards: " + dealer.toString() + " total: " + dealer.getTotalValue() + "|";
	}
	
	//tests to see win, loss, or push
	public int winLose()
	{
				if (player.getTotalValue() > dealer.getTotalValue())
				{
					this.playerWin++;
					return 1;
				}
				else if (player.getTotalValue() < dealer.getTotalValue() && dealer.getTotalValue() <= 21)
				{
					this.dealerWin++;
					return -1;
				}
				else if (dealer.getTotalValue() > 21)
				{
					this.playerWin++;
					return 1;
				}
				else if (player.getTotalValue() == dealer.getTotalValue())
				{
					this.push++;
					return 0;
				}
				this.dealerWin++;
				return -1;
	}
	
	//runs full game
	public int game(boolean verbose)
	{
		this.start = verbose;
		if (start == true)
		{
			reset();
			deal();
			playerTurn();
			if (playerTurn() == true)
			{
				dealerTurn();
				System.out.println(toString());
				return winLose();

			}
		}
		System.out.println(toString());
			dealerWin++;
			return -1;
	}
	
	//for the interactive version, returns a user friendly string telling whether win lose or push
	//rather than 1, -1, 0
	public String playerControlledWinLose()
	{
				if (player.getTotalValue() > dealer.getTotalValue())
				{
					this.playerWin++;
					return "You Win!";
				}
				else if (player.getTotalValue() < dealer.getTotalValue() && dealer.getTotalValue() <= 21)
				{
					this.dealerWin++;
					return "You Lose!";
				}
				else if (dealer.getTotalValue() > 21)
				{
					this.playerWin++;
					return "You Win!";
				}
				else if (player.getTotalValue() == dealer.getTotalValue())
				{
					this.push++;
					return "Push! (Tie)";
				}
				this.dealerWin++;
				return "You Lose!";
	}
	
	//allows player input
	public String playerTurnInteractive()
	{
		//resets and deals then asks for player input
		reset();
		deal();
		System.out.println("Player Turn:");
		System.out.println(toString());
		System.out.println("hit or stand?");
		String hitStand = keyboard.next();
		
		//logic for hitting and standing
		while(player.getTotalValue() <= 21 || hitStand == "hit")
		{
			//player hit and stand
			if (hitStand.equals("stand"))
			{
				break;
			}
			if (hitStand.equals("hit"));
			{
				player.add(deck.deal());
			}
			
			//System.out.println(toString());
			
			//keep going?
			if(player.getTotalValue() <= 21)
			{
				System.out.println(toString());
				System.out.println("hit or stand?");
				hitStand = keyboard.next();	
			}
		}
		
		//dealer turn
		if (player.getTotalValue() <= 21)
		{
			dealerTurn();
			System.out.println("Dealer Turn:");
			System.out.println(toString());
			return playerControlledWinLose();
		}
		System.out.println(toString());
		return "You Lose!";
	}
	
	/*  public static void main (String[] args)
	{
		Blackjack play = new Blackjack(40);
		System.out.println(play.playerTurnInteractive());
		
	}
	*/
} 


