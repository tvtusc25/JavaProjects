//Name: Trey Tuscai
//Date: Nov. 15 2021
//File: WordCounter.java
//Section: A
//Project: 7
//Course: CS231

package comparingDataStructures;

import java.io.BufferedReader;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WordCounter2 
{
	
	//fields
	MapSet<String, Integer> map;
	//BSTMap<String, Integer> map;
	//HashMap<String, Integer> hmap;
	String data_structure;
	ArrayList<String> readWords;
	int totalWordCount = 0;
	long time = 0;
	
	//constructor which decides the map type
	public WordCounter2( String data_structure )
	{
		this.data_structure = data_structure;
		if(this.data_structure.equals("bst"))
		{
			map = new BSTMap<String, Integer>(new AscendingString());
		}
		else if(this.data_structure.equals("hashmap"))
		{
			map = new HashMap<String, Integer>(new AscendingString());
		}
	}
	
	//creates an arraylist of the words in the file
	public ArrayList<String> readWords(String filename)
	{
		
		this.readWords = new ArrayList<String>();
		
		try 
		    {
			    FileReader fileReader = new FileReader(filename);
			    BufferedReader bufferedReader = new BufferedReader(fileReader);
		    
			    String line = bufferedReader.readLine();
			    
			    while(line != null)
			    {
				    String[] words = line.split("[^a-zA-Z0-9']");
				    for (int i = 0; i < words.length; i++) 
				    {
					    String word = words[i].trim().toLowerCase();
					    //System.out.println(word);
					    if(word.length() > 0)
					    {
						   readWords.add(word);
						   //System.out.println(word);
					    }
				    }
				    line = bufferedReader.readLine();
			    }
			    
			    bufferedReader.close();
		    }
		    
		    catch(FileNotFoundException ex) 
		    {
		      System.out.println("WordCounter.analyze():: unable to open file " + filename );
		    }
		    catch(IOException ex) 
		    {
		      System.out.println("WordCounter.analyze():: error reading file " + filename);
		    }
		totalWordCount = readWords.size();
		return readWords;
	}
	
	//builds the map using the arraylist
	public double buildMap( ArrayList<String> words )
	{
		long start = 0;
		long end = 0;
		
		if(this.data_structure.equals("bst"))
		{
			start = System.nanoTime();
			for(String word: readWords)
			{
				try
				{
					this.map.put(word, this.getCount(word)+1);
				}
				catch(NullPointerException nex)
				{
					this.map.put(word, 1);
				}
			}
			end = System.nanoTime();
		}
		
		else if(this.data_structure.equals("hashmap"))
		{
			start = System.nanoTime();
			for(String word: readWords)
			{
				try
				{
					this.map.put(word, map.get(word)+1);
				}
				catch(NullPointerException nex)
				{
					this.map.put(word, 1);
				}
			}
			end = System.nanoTime();
		}
		
		System.out.print("Runtime: ");
		time = end - start;
		return time;
	}
	
	//returns the runtime
	public long getTime()
	{
		return time;
	}
	
	//gets the count of occurrences
	public int getCount( String word )
	{
		if(this.data_structure.equals("bst"))
		{
			return map.get(word);
		}
		else if(this.data_structure.equals("hashmap"))
		{
			return map.get(word);
		}
		else
			return 0;
	}
	
	//returns the map as a string
	public String toString()
	{
		if(this.data_structure.equals("bst"))
		{
			return map.toString();
		}
		else if(this.data_structure.equals("hashmap"))
		{
			System.out.println(map.entrySet());
			return map.toString();
		}
		else
			return null;
		
	}
	
	//clears the map
	public void clearMap()
	{
		if(this.data_structure.equals("bst"))
		{
			map.clear();
		}
		else if(this.data_structure.equals("hashmap"))
		{
			map.clear();
		}
	}
	
	//returns the total word count
	public int totalWordCount()
	{
		return totalWordCount;
	}
	
	//returns the unique word count
	public int uniqueWordCount()
	{
		if(this.data_structure.equals("bst"))
		{
			return map.size();
		}
		else if(this.data_structure.equals("hashmap"))
		{
			return map.size();
		}
		else
			return 0;
	}
	
	//returns the map as a string for a file
	public String toFileString()
	{
		if(this.data_structure.equals("bst"))
		{
			return map.toFileString();
		}
		else if(this.data_structure.equals("hashmap"))
		{
			return map.toFileString();
		}
		else 
			return null;
	}
	
	//writes the file
	public void writeWordCountFile( String filename )
	{
		try 
		{
			FileWriter fileWriter = new FileWriter(filename);
			fileWriter.write("totalWordCount : " + this.totalWordCount() + "\n" + this.toFileString());
			fileWriter.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//reads the written file to build a map
	public void readWordCountFile( String filename )
	{
		this.clearMap();
		 try 
		    {
			    FileReader fileReader = new FileReader(filename);
			    BufferedReader bufferedReader = new BufferedReader(fileReader);
			    
			    String firstLine = bufferedReader.readLine();
			    totalWordCount = Integer.parseInt(firstLine.substring(firstLine.lastIndexOf(" ") +1 ));
			    System.out.println(firstLine);
			    String line = bufferedReader.readLine();
			   			    
			    while(line != null)
			    {
				    String[] words = line.split("\\r?\\n");
				    //totalWordCount = Integer.parseInt(words[0].substring(words[0].lastIndexOf(" ") +1 ));
				    
				    for (int i = 0; i < words.length; i++) 
				    {
					    String word = words[i].trim().toLowerCase();
					    //System.out.println(word);
					    //System.out.println(word);
					    if(word.length() > 0)
					    {
						    //getCount() is null and won't allow comparison to zero
						    //NullPointerException
						    //set it to value 1 originally then exception won't occur
						    if(this.data_structure.equals("bst"))
							{
							    try
							    {
								    this.map.put(word.substring(0, word.lastIndexOf(":")), Integer.parseInt(word.substring(word.lastIndexOf(" ")+1)));
							    }
							    catch(NullPointerException nex)
							    {
								    this.map.put(word, 1);
							    }
							}
						    else
						    {
							    try
							    {
								    this.map.put(word.substring(0, word.lastIndexOf(":")), Integer.parseInt(word.substring(word.lastIndexOf(" ")+1)));
							    }
							    catch(NullPointerException nex)
							    {
								    this.map.put(word, 1);
							    }
						    }
						    
					    }
				    }
				    line = bufferedReader.readLine();
			    }
			    
			    bufferedReader.close();
		    }
		    
		    catch(FileNotFoundException ex) 
		    {
		      System.out.println("WordCounter.analyze():: unable to open file " + filename );
		    }
		    catch(IOException ex) 
		    {
		      System.out.println("WordCounter.analyze():: error reading file " + filename);
		    }
		 
		 	//this.writeWordCountFile(filename);
	}
	
	//returns num of collisions
	public int getCollisions()
	{
		return map.getCollisions();
	}
	
	//returns depth/height of tree
	public int getDepth()
	{
		if(map.depthL() > map.depthR())
		{
			return map.depthL();
		}
		else
			return map.depthR();
	}
	
	public static void main(String[] args)
	{
		WordCounter2 wordC = new WordCounter2("hashmap");
		long average = 0;
		ArrayList<Long> times = new ArrayList<Long>();
		for(int year = 2008; year <= 2015; year++)
		{
			System.out.println("Year:" + year);
			for(int i = 1; i <= 5; i++)
			{
				wordC.clearMap();
				System.out.println("Run: " + i);
				System.out.println(wordC.buildMap(wordC.readWords("//Users/treytuscai/Desktop/TextFiles/reddit_comments_" + year + ".txt")));
				//System.out.println(wordC.getTime());
				times.add(wordC.getTime());
				//System.out.println("Total Words: " + wordC.totalWordCount());
				//System.out.println("Unique Words: " + wordC.uniqueWordCount());
				//System.out.println("Depth " + wordC.getDepth());
				//System.out.println("Collisions " + wordC.getCollisions());
			}
			times.remove(Collections.min(times));
			times.remove(Collections.max(times));
			for(int i = 0; i < times.size(); i++)
			{
				average+= times.get(i);
			}
			average/=times.size();
			//System.out.println(times.toString());
			System.out.println("Robust Average: " + average);
			times.clear();			
		}
		
		//wordC.readWordCountFile("/Users/treytuscai/Desktop/bstcount");
		//wordC.writeWordCountFile("bstcount_v2");
		//System.out.println(wordC.uniqueWordCount());
		//wordC.clearMap();
	}
	
}
