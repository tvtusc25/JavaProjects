//Name: Trey Tuscai
//Date: Nov. 8 2021
//File: WordCounter.java
//Section: A
//Project: 6
//Course: CS231

package wordFrequences;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WordCounter 
{
	//fields
	BSTMap<String,Integer> map;
	int totalWordCount;
	
	public WordCounter()
	{
		this.map = new BSTMap<String, Integer>(new AscendingString());
		this.totalWordCount = 0;
	}
	
	//analyzes the file and adds words to BST
	public void analyze(String filename)
	{
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
								    //getCount() is null and won't allow comparison to zero
								    //NullPointerException
								    //set it to value 1 originally then exception won't occur
								    try
								    {
									    this.map.put(word, this.getCount(word)+1);
								    }
								    catch(NullPointerException nex)
								    {
									    this.map.put(word, 1);
								    }
								    this.totalWordCount++;
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
	}
	
	//analyzes words without common words
	public void analyzeWithoutCommon(String filename, String commonFilename)
	{
				    try 
				    {
					    FileReader fileReader = new FileReader(filename);
					    BufferedReader bufferedReader = new BufferedReader(fileReader);
					    String line = bufferedReader.readLine();
					    
					    //reads and puts all the common words in an array
					    FileReader fileReaderCommon = new FileReader(commonFilename);
					    BufferedReader bufferedReaderCommon = new BufferedReader(fileReaderCommon); 
					    String lineCommon = bufferedReaderCommon.readLine();
					    ArrayList<String> com = new ArrayList<String>();
					    
					    //adds to arraylist
					    while(lineCommon != null)
					    {
						    String[] wordsCommon = lineCommon.split("\\r?\\n");
						    for(int j = 0; j < wordsCommon.length; j++)
						    {
							    com.add(wordsCommon[j]);
							    //System.out.println(com.get(j));
						    }
						   lineCommon = bufferedReaderCommon.readLine();    
					    }
					  
					    
					    //adds to bst 
					    while(line != null)
					    {
						    String[] words = line.split("[^a-zA-Z0-9']");
						    //String[] wordsCommon = lineCommon.split("\\r?\\n");
						    for (int i = 0; i < words.length; i++) 
						    {
							    String word = words[i].trim().toLowerCase();
							    
							    		boolean isCommon = false;
								    for(int j = 0; j < com.size(); j++)
								    {
									    if(word.compareTo(com.get(j)) == 0)
									    {
										    //System.out.println(word + " " + com.get(j));
										    isCommon = true;
									    }
								    }
								    
								    if(word.length() > 0 && isCommon == false)
								    {
									    try
									    {
										    this.map.put(word, this.getCount(word)+1);
									    }
									    		catch(NullPointerException nex)
									    {
											this.map.put(word, 1);
									    }
											this.totalWordCount++;
								    }
									   
						    }
						    line = bufferedReader.readLine();
					    }
					    
					    bufferedReader.close();
					    bufferedReaderCommon.close();
				    }
				    
				    catch(FileNotFoundException ex) 
				    {
				      System.out.println("WordCounter.analyze():: unable to open file " + filename );
				    }
				    catch(IOException ex) 
				    {
				      System.out.println("WordCounter.analyze():: error reading file " + filename);
				    }
	}
	
	//returns total word count
	public int getTotalWordCount()
	{
		return totalWordCount;
	}
	
	//returns unique word count
	public int getUniqueWordCount()
	{
		return map.size();
	}
	
	//returns count of a word
	public int getCount( String word )
	{
		return map.get(word);
	}
	
	//returns frequency of a word
	public double getFrequency( String word )
	{
		return map.get(word) / (double)this.getTotalWordCount();
	}
	
	//returns arraylist of kvps
	public ArrayList<KeyValuePair<String, Integer>> entrySet() 
	{
	    return map.entrySet();
	}
	
	//returns bst as a string
	public String toString()
	{
		return map.toString();
	}
	
	//returna a bst as a string in a file
	public String toFileString()
	{
		return map.toFileString();
	}
	
	//writes the file 
	public void writeWordCountFile( String filename )
	{
		try 
		{
			FileWriter fileWriter = new FileWriter(filename);
			fileWriter.write("totalWordCount : " + this.getTotalWordCount() + "\n" + this.toFileString());
			fileWriter.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//reads a file with word counts and makes a bst with it
	public void readWordCountFile( String filename )
	{
		map.clear();
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
	
	public static void main(String[] args)
	{
		WordCounter wordC = new WordCounter();
		wordC.analyze("/Users/treytuscai/Desktop/counttest.txt");
		System.out.println(wordC.getUniqueWordCount());
		System.out.println(wordC.getTotalWordCount());
		System.out.println(wordC.entrySet());
		System.out.println(wordC.getCount("it"));
		System.out.println(wordC.getFrequency("it"));
		System.out.println(wordC.toString());
		wordC.writeWordCountFile("count");
		wordC.readWordCountFile("/Users/treytuscai/Desktop/count");
		//System.out.println(wordC.getFrequency("was"));
				
	}
}
