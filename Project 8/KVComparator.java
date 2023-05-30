//Name: Trey Tuscai
//Date: Nov. 23 2021
//File: KVComparator.java
//Section: A
//Project: 8
//Course: CS231

package wordTrends;

import java.util.Comparator;

//compares two kvps 
public class KVComparator implements Comparator<KeyValuePair<String,Integer>> 
{
	public int compare( KeyValuePair<String,Integer> i1, KeyValuePair<String, Integer> i2 ) 
	{
	     // returns negative number if i2 comes after i1 lexicographically
	     int diff = i2.getValue() - i1.getValue();
	     if (diff == 0)
	         return 0;
	     if (diff < 0)
	         return 1;
	     else
	         return -1;
	}

}
