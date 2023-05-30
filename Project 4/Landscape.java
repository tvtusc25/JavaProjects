//Name: Trey Tuscai
//Date: Oct. 24 2021
//File: Landscape.java
//Section: A
//Project: 4
//Course: CS231

package groupingBehaviors;

import java.util.ArrayList;
import java.awt.Graphics;

public class Landscape 
{
	//variables
	private int width;
	private int height;
	LinkedList <Agent> agentList;
	
	//a constructor that sets the width and height fields, and initializes the agent list.
	public Landscape(int w, int h)
	{
		width = w;
		height = h;
		agentList = new LinkedList<Agent>();
	}
	
	//returns the height.
	public int getHeight()
	{
		return this.height;
	}
	
	//returns the width.
	public int getWidth()
	{
		return this.width;
	}
	
	//inserts an agent at the beginning of its list of agents.
	public void addAgent(Agent a)
	{
		this.agentList.addFirst(a);
	}
	
	//returns a String representing the Landscape
	public String toString()
	{
		return "Number of Agents: " + this.agentList.size();
	}
	
	//returns a list of the Agents within radius distance of the location x0, y0.
	public ArrayList<Agent> getNeighbors(double x0, double y0, double radius)
	{
		ArrayList<Agent> arrayAgent = agentList.toArrayList();
		ArrayList<Agent> agentNeighbors = new ArrayList<Agent>();
		
		//System.out.println(radius);
		for(int i = 0; i < arrayAgent.size(); i++)
		{
			if (Math.abs(arrayAgent.get(i).getX() - x0) < radius && Math.abs(arrayAgent.get(i).getY() - y0) < radius)
			{
				agentNeighbors.add(arrayAgent.get(i));
			}
		}
		//System.out.println(agentNeighbors);
		return agentNeighbors;	
	}
	
	public void draw(Graphics g)
	{
		ArrayList<Agent> arrayAgent = agentList.toArrayList();
		for(int i = 0; i < arrayAgent.size(); i++)
		{
			arrayAgent.get(i).draw(g);
		}
		
	}
	
	public void updateAgents()
	{
		ArrayList<Agent> arrayAgent = agentList.toShuffledList();
		for(Agent agent : arrayAgent)
		{
			agent.updateState(this);
		}
	}
	
	
	
	/*public static void main(String[] args)
	{
		Landscape landscape = new Landscape(10, 10);
		CatSocialAgent one = new CatSocialAgent(5, 5, 25,1);
		CatSocialAgent two = new CatSocialAgent(10, 10, 25, 1);
		CatSocialAgent three = new CatSocialAgent(15, 15, 25, 0);
		CatSocialAgent four = new CatSocialAgent(20, 20, 25, 0);
		CatSocialAgent five = new CatSocialAgent(200, 200, 25, 0);
		landscape.addAgent(one);
		landscape.addAgent(two);
		landscape.addAgent(three);
		landscape.addAgent(four);
		landscape.addAgent(five);
		System.out.println(landscape.toString());
		one.updateState(landscape);
		System.out.println(landscape.getNeighbors(5, 5, 25));
		System.out.println(landscape.getNeighbors(5, 5, 25).size());
	}*/
	

	
}
