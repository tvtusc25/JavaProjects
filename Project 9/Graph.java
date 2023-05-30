//Name: Trey Tuscai
//Date: Dec. 13 2021
//File: Graph.java
//Section: A
//Project: 9
//Course: CS231
package huntTheWumpus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph 
{
	//fields
	ArrayList<Vertex> vertices;
	Comparator<Vertex> comp;
	
	//constructor
	public Graph()
	{
		vertices = new ArrayList<Vertex>();
	}
	
	//add vertices to graph
	public void addVertices(Vertex vert)
	{
		vertices.add(vert);
	}
	
	//counts the number of vertices in graph
	public int vertexCount()
	{
		return vertices.size();
	}
	
	//returns true if vertice is in graph
	public boolean inGraph(Vertex query)
	{
		for(Vertex vertex : vertices)
		{
			if(Vertex.matchPosition(vertex, query))
				return true;
		}
		return false;
	}
	
	//creates ArrayList of vertices
	public ArrayList<Vertex> getVertices()
	{
		return vertices;
	}
	
	//add one way connection between two vertices
	public void addUniEdge(Vertex v1, Vertex v2)
	{
		if(!this.inGraph(v1))
			vertices.add(v1);
		if(!this.inGraph(v2))
			vertices.add(v2);
		v1.connect(v2);
	}
	
	//adds to way connection between two vertices
	public void addBiEdge(Vertex v1, Vertex v2)
	{
		if(!this.inGraph(v1))
			vertices.add(v1);
		if(!this.inGraph(v2))
			vertices.add(v2);
		v1.connect(v2);
		v2.connect(v1);
	}
	
	//shortest path algorithm
	public void shortestPath(Vertex v0)
	{	
		for(Vertex vert: vertices)
		{
			vert.reset();
		}
		
		PriorityQueue<Vertex> vertQueue = new PriorityQueue<>();
		
		v0.setCost(0);
		vertQueue.add(v0);
		
		while (!vertQueue.isEmpty())
		{
			Vertex current = vertQueue.poll();
			if(current.getVisited())
			{
				continue;
			}
			current.setVisited(true);
			
			for(Vertex neighbor: current.getNeighbors())
			{
				double distance = current.getCost() + current.distance(neighbor);
				if(!neighbor.getVisited() && distance < neighbor.getCost())
				{
					neighbor.setCost(distance);
					neighbor.setParent(current);
					vertQueue.add(neighbor);
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		Graph g = new Graph();
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		Vertex v3 = new Vertex();
		Vertex v4 = new Vertex();
		Vertex v5 = new Vertex();
		
		g.addVertices(v1);
		g.addVertices(v2);
		g.addVertices(v3);
		g.addVertices(v4);
		g.addVertices(v5);
		
		
		
			
	}
}
