//Name: Trey Tuscai
//Date: Nov. 23 2021
//File: HashMap.java
//Section: A
//Project: 8
//Course: CS231

package wordTrends;

import java.util.ArrayList;
import java.util.Comparator;

public class HashMap<K,V> implements MapSet<K,V>
{
	
	//Linked List parts
	@SuppressWarnings("hiding")
	private class HashNode<K, V>
	{
		KeyValuePair<K, V> data;
		HashNode<K,V> next;
		
		public HashNode(K key, V value)
		{
			this.data = new KeyValuePair<K, V>(key, value);
			this.next = null;
		}
		
		//returns the key
		public K getKey()
		{
			return this.data.getKey();
		}
		
		//returns the value
		public V getValue()
		{
			return this.data.getValue();
		}
		
		//sets the value
		public void setValue(V val)
		{
			this.data.setValue(val);
		}
		
		//gets the next Node
		public HashNode<K,V> getNext()
		{
			return this.next;
		}	
	}
	
	//fields
	private int collisions = 0;
	Comparator<K> comp;
	private int filled = 0;
	private int tableSize = 16;
	private HashNode<K, V>[] hashTable = null;
	
	
	// HashMap constructor that starts with default size hash table
	   @SuppressWarnings("unchecked")
	public HashMap(Comparator<K> comp) 
	   {
	        // code here
		   this.comp = comp;
		    this.hashTable = new HashNode[tableSize];
		    
	   }

	    // HashMap constructor that starts with the suggested capacity hash table
	    @SuppressWarnings("unchecked")
	public HashMap(Comparator<K> comp, int capacity) 
	    {
		    this.comp = comp;
		    this.hashTable = new HashNode[capacity];

	    }
	    
	    //hash function
	    private int hash(K key)
	    {
		    //https://opendsa-server.cs.vt.edu/ODSA/Books/CS3/html/HashFuncExamp.html
		    /*long sum = 0, mul = 1;
		    for (int i = 0; i < ((String) key).length(); i++) {
		      mul = (i % 4 == 0) ? 1 : mul * 256;
		      sum += ((String) key).charAt(i) * mul;
		    }
		    
		    return (int)(Math.abs(sum) % this.tableSize);*/
		    
		    return Math.abs(((String) key).hashCode()) % this.tableSize;
	    }
	    //puts pairs into the map
	public V put(K new_key, V new_value) 
	{
		/*if(this.filled > this.tableSize/3)
		{
			ArrayList<KeyValuePair<K, V>> data = this.entrySet();
			
			@SuppressWarnings("unchecked")
			HashNode<K, V>[] temp = new HashNode[this.tableSize * 2];
			this.tableSize *= 2;
			this.hashTable = temp;
			
			this.filled = 0;
			this.collisions = 0;
			
			//System.out.println("Doubling Size!");
			
			for(int i = 0; i < data.size(); i++)
			{
				this.put(data.get(i).getKey(), data.get(i).getValue());
			}
		}*/
		
		int position = hash(new_key);
		HashNode<K, V> current = hashTable[position];
		
		if(current == null)
		{
			hashTable[position] = new HashNode<K, V>(new_key, new_value);
			this.filled++;
		}
		else
		{
			while(current.getNext() != null && comp.compare(current.getKey(), new_key) != 0)
			{
				current= current.getNext();
			}
			if(comp.compare(current.getKey(), new_key) == 0)
			{
				current.setValue(new_value);
			}
			else
			{
				current.next = new HashNode<K, V>(new_key, new_value);
				collisions++;
				this.filled++;
			}
		}
		
		if(this.filled > this.tableSize/3)
		{
			ArrayList<KeyValuePair<K, V>> data = this.entrySet();
			
			@SuppressWarnings("unchecked")
			HashNode<K, V>[] temp = new HashNode[this.tableSize * 2];
			this.tableSize *= 2;
			this.hashTable = temp;
			
			this.filled = 0;
			this.collisions = 0;
			
			//System.out.println("Doubling Size!");
			
			for(int i = 0; i < data.size(); i++)
			{
				this.put(data.get(i).getKey(), data.get(i).getValue());
			}
		}
		
		return new_value;
	}
	
	//returns to string of map
	public String toString()
	{
		String result = "";
		for (int i = 0; i < tableSize; i++)
		{
			HashNode<K, V> current = hashTable[i];
			
			while(current != null)
			{
				result += current.getKey() + "=" + current.getValue() + ", ";
				current = current.getNext();
			}
		}
		return result;
	}
	
	//returns to string for file
	public String toFileString()
	{
		String result = "";
		for (int i = 0; i < tableSize; i++)
		{
			HashNode<K, V> current = hashTable[i];
			
			while(current != null)
			{
				result += current.getKey() + ": " + current.getValue() + "\n";
				current = current.getNext();
			}
		}
		return result;
	}

	//returns if the key is in the map
	public boolean containsKey(K key) 
	{
		int position = this.hash(key);
		HashNode<K, V> current = hashTable[position];
		while(current != null && current.getKey() != key)
		{
			current = current.getNext();
		}
		if(current == null)
		{
			return false;
		}
		if(current.getKey() == key)
		{
			return true;
		}
		return false;
	}

	//gets a value from the map using its key
	public V get(K key) 
	{
		int position = this.hash(key);
		HashNode<K, V> current = hashTable[position];
		while(current != null && comp.compare(current.getKey(), key) != 0)
		{
			current = current.getNext();
		}
		if(current == null)
		{
			return null;
		}
		if(comp.compare(current.getKey(), key) ==0)
		{
			return current.getValue();
		}
		return null;
	}

	//creates a list of the keys
	public ArrayList<K> keySet() 
	{
		ArrayList<K> set = new ArrayList<K>();
		
		for (int i = 0; i < this.tableSize; i ++)
		{
			HashNode<K,V> current = hashTable[i];
			while (current != null)
			{
				set.add(current.getKey());
				current = current.getNext();
			}
		}
		
		return set;
	}
	
	//creates a list of the values
	public ArrayList<V> values() 
	{
		ArrayList<V> set = new ArrayList<V>();
		
		for (int i = 0; i < this.tableSize; i ++)
		{
			HashNode<K,V> current = hashTable[i];
			while (current != null)
			{
				set.add(current.getValue());
				current = current.getNext();
			}
		}
		
		return set;
	}
	
	//creates a list of the pairs
	public ArrayList<KeyValuePair<K, V>> entrySet() 
	{
		ArrayList<KeyValuePair<K, V>> set = new ArrayList<KeyValuePair<K, V>>();
		
		for (int i = 0; i < this.tableSize; i ++)
		{
			HashNode<K,V> current = hashTable[i];
			while (current != null)
			{
				set.add(current.data);
				current = current.getNext();
			}
		}
		
		return set;
	}

	//returns the size of the map
	public int size() 
	{
		int size = 0;
		for (int i = 0; i < tableSize; i++)
		{
			HashNode<K, V> current = hashTable[i];
			
			while(current != null)
			{
				size++;
				current = current.getNext();
			}
		}
		return size;
	}

	//clears the map
	@SuppressWarnings("unchecked")
	public void clear() 
	{
		this.hashTable = new HashNode[this.tableSize];
	}
	
	//removes a key from the map
	public boolean remove(K key)
	{
		int position = hash(key);
		
		HashNode<K, V> target = hashTable[position];
		
		HashNode<K, V> current = target;
		HashNode<K, V> previous = null;
		
		while(current != null && current.getKey() != key)
		{
			previous = current;
			current = current.getNext();
		}
		
		if(current == null)
		{
			return false;
		}
		
		if(previous == null)
		{
			this.hashTable[position] = current.getNext();
		}
		
		else
		{
			previous.next = current.getNext();
		}
		
		this.filled--;
		
		return true;
	}
	
	//returns the number of collisions
	public int getCollisions()
	{
		return collisions;
	}
	
	/*public static void main (String args[])
	{
		HashMap<String, Integer> map = new HashMap<String, Integer>(new AscendingString());
		
		ArrayList<String> word = new ArrayList<String>();
		word.add("it");
		word.add("was");
		word.add("it");
		word.add("was");
		word.add("it");
		word.add("was");
		word.add("fun");
		word.add("it");
		word.add("was");
		word.add("it");
		word.add("was");
		word.add("it");
		word.add("was");
		word.add("fun");
		word.add("it");
		word.add("was");
		word.add("it");
		word.add("was");
		word.add("it");
		word.add("was");
		word.add("fun");
		word.add("it");
		word.add("was");
		word.add("it");
		word.add("was");
		word.add("it");
		word.add("was");
		word.add("fun");
		
		for(int i = 0; i < word.size(); i ++)
		{
			try
			{
				map.put(word.get(i), map.get(word.get(i))+1);
			}
			catch(NullPointerException nex)
			{
				map.put(word.get(i), 1);
			}
		}
		
		//map.put("This", 8);
		//map.put("nice", 9);
		//map.put("nice", 10);
		//map.put("ice", 11);
		//map.remove("is");
		//map.clear();
		//System.out.println(map.get("nice"));
		System.out.println(map.toString());
		System.out.println(map.entrySet());
		System.out.println(map.keySet());
		System.out.println(map.values());
		System.out.println(map.containsKey("is"));
		System.out.println(map.size());
	}*/
	
	//parts for the BST Map
	@Override
	public int depthL() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int depthR() {
		// TODO Auto-generated method stub
		return 0;
	}
}
