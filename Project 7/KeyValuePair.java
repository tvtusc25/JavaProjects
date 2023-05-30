//Name: Trey Tuscai
//Date: Nov. 15 2021
//File: KeyValuePair.java
//Section: A
//Project: 7
//Course: CS231

package comparingDataStructures;

public class KeyValuePair<Key, Value>
{
	//fields
	Key k = null;
	Value v = null;
	
	// the constructor initializing the key and value fields.
	public KeyValuePair( Key k, Value v )
	{
		this.k = k;
		this.v = v;
	}
	
	//returns the key.
	public Key getKey()
	{
		return this.k;
	}
	
	//returns the value.
	public Value getValue()
	{
		return this.v;
	}
	
	//sets the value
	public void setValue( Value v )
	{
		this.v = v;
	}
	
	//returns a String containing both the key and value.
	public String toString()
	{
		return k + ": " + v;
	}
	
	/*public static void main(String[] args)
	{
		KeyValuePair<Integer, Integer> kvp = new KeyValuePair<Integer, Integer>(5, 10);
		System.out.println("key " + kvp.getKey());
		System.out.println("value " + kvp.getValue());
		kvp.setValue(6);
		System.out.println("value " + kvp.getValue());
		System.out.println(kvp.toString());
	}*/
}
