//Name: Trey Tuscai
//Date: Nov. 23 2021
//File: CommonWordsFinder.java
//Section: A
//Project: 8
//Course: CS231

package wordTrends;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class CommonWordsFinder 
{
	//fields
	int totalWordCount;
	PQHeap<KeyValuePair<String,Integer>> heap;
	
	//constructor
	public CommonWordsFinder()
	{
		heap = new PQHeap<KeyValuePair<String,Integer>>(new KVComparator());
		totalWordCount = 0;
	}
	
	//reads the written file to build a map
		public void readWordCountFile( String filename )
		{
			 try 
			    {
				    FileReader fileReader = new FileReader(filename);
				    BufferedReader bufferedReader = new BufferedReader(fileReader);
				    
				    String firstLine = bufferedReader.readLine();
				    totalWordCount = Integer.parseInt(firstLine.substring(firstLine.lastIndexOf(" ") +1 ));
				    //System.out.println(firstLine);
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
									   
									    this.heap.add(new KeyValuePair<String, Integer>(word.substring(0, word.lastIndexOf(":")), Integer.parseInt(word.substring(word.lastIndexOf(" ")+1))));
								    }
								    catch(NullPointerException nex)
								    {
									    
									    this.heap.add(new KeyValuePair<String, Integer>(word.substring(0, word.lastIndexOf(":")), 0));
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
		
		//returns the heap as a string
		public String toString()
		{
			return heap.toString();
		}
		
		//removes the top value and returns it
		public KeyValuePair<String, Integer> remove()
		{
			return heap.remove();
		}
		
		//main function
		public static void main(String[] args)
		{
			CommonWordsFinder cwf = new CommonWordsFinder();
			DecimalFormat df = new DecimalFormat("#.###");
			for(int i = 0; i < args.length; i ++)
			{
				cwf.readWordCountFile(args[i]);
				for(int N = 0; N <= 10; N++)
				{
					KeyValuePair<String, Integer> kvp = cwf.remove();
					String frequency = df.format((double) kvp.getValue()/ cwf.totalWordCount);
					System.out.println(kvp.getKey() + " " + frequency);
				}
				System.out.println();
			}
			//cwf.readWordCountFile("/Users/treytuscai/Desktop/counts_ct");
			//System.out.println(cwf.toString());
			/*System.out.println(cwf.remove());
			System.out.println(cwf.remove());
			System.out.println(cwf.remove());
			System.out.println(cwf.remove());
			System.out.println(cwf.remove());
			System.out.println(cwf.remove());
			System.out.println(cwf.remove());*/
		}
}
