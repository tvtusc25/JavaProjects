//Name: Trey Tuscai
//Date: Nov. 23 2021
//File: WordTrendsFinder.java
//Section: A
//Project: 8
//Course: CS231

package wordTrends;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class WordTrendsFinder 
{
	//fields
	String baseFile;
	int fileNumStart;
	int fileNumEnd;
	ArrayList<String> words;
	WordCounter3 wc;
	
	//constructor
	public WordTrendsFinder(String baseFile, int fileNumStart, int fileNumEnd, String word1, String word2, String word3, String word4, String word5, String word6)
	{
		this.baseFile = baseFile;
		this.fileNumStart = fileNumStart;
		this.fileNumEnd = fileNumEnd;
		words = new ArrayList<String>();
		words.add(word1);
		words.add(word2);
		words.add(word3);
		words.add(word4);
		words.add(word5);
		words.add(word6);
		wc = new WordCounter3("hashmap");
	}
	
	//finds the words and prints their frequency for every year
	public String find()
	{
		String frequencies = "";
		for(int word = 0; word < words.size(); word++)
		{
			frequencies += words.get(word);
			for(int year = fileNumStart; year <= fileNumEnd; year++)
			{
				wc.readWordCountFile(baseFile + year);
				frequencies += " " + ((double) wc.getValue(words.get(word))/wc.totalWordCount) + " ";
			}	
			frequencies += "\n";
		}
		return frequencies;
	}
	
	
	//main function
	public static void main(String[] args)
	{
		WordTrendsFinder finder = new WordTrendsFinder(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3], args[4], args[5], args[6], args[7], args[8]);
		System.out.println(finder.find());
	}

}
