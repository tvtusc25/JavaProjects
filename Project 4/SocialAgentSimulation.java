//Name: Trey Tuscai
//Date: Oct. 24 2021
//File: SocialAgentSimulation.java
//Section: A
//Project: 4
//Course: CS231

package groupingBehaviors;

import java.util.Random;
import java.util.Scanner;

public class SocialAgentSimulation 
{
	public static void main(String[] args) throws InterruptedException
	{
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Landscape Size: ");
		int landscapeSize = keyboard.nextInt();
		
		//new landscape
		Landscape scape = new Landscape(landscapeSize,landscapeSize);
		Random gen = new Random();
		

		System.out.println("Number of Agents: ");
		int agentNum = keyboard.nextInt();
		
		System.out.println("Size of Agent Radii: ");
		int agentRadii = keyboard.nextInt();

		//adds chosen number of agents w/ random positions and chosen radius 
		for(int i=0;i<agentNum;i++) 
		{
			scape.addAgent(new SocialAgent( gen.nextDouble() * scape.getWidth(),
			gen.nextDouble() * scape.getHeight(), agentRadii) );
		}

		//updates program
		LandscapeDisplay display = new LandscapeDisplay(scape);
		for (int i = 0; i <= 200; i++)
	      {
	     	 scape.updateAgents();
	     	 display.repaint();
	     	 Thread.sleep(60);
	      }
		keyboard.close();
	}
}
