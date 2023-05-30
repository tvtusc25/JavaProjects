//Name: Trey Tuscai
//Date: Sept. 19 2021
//File: Simulation.java
//Section: A
//Project: 1
//Course: CS231

package blackjack;

public class Simulation {

	//starts new game
	//runs it a certain amount of times
	//gives win count and percentages
	public static void main(String[] args) {
		Blackjack play = new Blackjack(40);
		
		double timesPlayed = 1000;
		for (int i = 0; i < timesPlayed; i++)
		{
		System.out.println(play.game(true));
		}
		System.out.println("Player Wins: " + play.getPlayerWin() + " Pushes: " + play.getPush() + " Dealer Wins: " + play.getDealerWin());
		System.out.println("Player Wins: " + ((play.getPlayerWin()/timesPlayed)*100) + '%' + " Pushes: " + ((play.getPush()/timesPlayed)*100) + '%' + " Dealer Wins: " + ((play.getDealerWin()/timesPlayed)*100) + '%');
		
	}

}
