//Name: Trey Tuscai
//Date: Nov. 23 2021
//File: AscendingString.java
//Section: A
//Project: 8
//Course: CS231

package wordTrends;

import java.util.Comparator;

public class AscendingString implements Comparator<String>
{
	//compares two strings
	public int compare(String a, String b)
	{
		return b.compareTo(a);
	}
}