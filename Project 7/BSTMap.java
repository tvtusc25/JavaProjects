//Name: Trey Tuscai
//Date: Nov. 15 2021
//File: BSTMap.java
//Section: A
//Project: 7
//Course: CS231

package comparingDataStructures;

/*
***Put your name here***

Template for the BSTMap classes
Fall 2020
CS 231 Project 6
*/
import java.util.ArrayList;
import java.util.Comparator;

public class BSTMap<K, V> implements MapSet<K, V> 
{

	// fields here
	private Comparator<K> comp;
	private TNode root;
	
	// constructor: takes in a Comparator object
	public BSTMap( Comparator<K> comp ) 
	{
		// initialize fields heres
		this.comp = comp;
		this.root = null;
	}

	// adds or updates a key-value pair
	// If there is already a pair with new_key in the map, then update
	// the pair's value to new_value.
	// If there is not already a pair with new_key, then
	// add pair with new_key and new_value.
	// returns the old value or null if no old value existed
	public V put( K key, V value ) 
	{
		// check for and handle the special case
		if(this.root == null)
		{
			this.root = new TNode(key, value);
			return null;
		}
		
		// call the root node's put method
		this.root.put(key, value, comp);
		return value;
	}

  // gets the value at the specified key or null
	public V get( K key ) 
	{
	    // check for and handle the special case
	    if(this.root == null)
	    {
		    return null;
	    }
	    
       // call the root node's get method
	    return this.root.get(key, comp);    
	}
  
	//Returns true if the map contains a key-value pair with the given key
	public boolean containsKey(K key) 
	{
	    //check for and handle special case
	    if(this.root == null)
	    {
		    return false;
	    }
	    //call the root node's containKey method
  		return this.root.containsKey(key);
	}	

  // Returns an ArrayList of all the keys in the map. There is no
  // defined order for the keys.
  public ArrayList<K> keySet() 
  {
	    ArrayList<K> keyList = new ArrayList<K>();
	    if(root == null)
		    return null;
	    else
	    {
		   // keyList.add(root.kvp.getKey());
		    return this.root.keySet(keyList);
	    }
  }

  //returns an arraylist of values
  public ArrayList<V> values() 
  {
	    ArrayList<V> valueList = new ArrayList<V>();
	    if(root == null)
		    return null;
	    else
	    {
		    return this.root.values(valueList);
	    }
  }

  //returns and arraylist of kvp
  public ArrayList<KeyValuePair<K, V>> entrySet() 
  {
	    ArrayList<KeyValuePair<K, V>> entrySet = new ArrayList<KeyValuePair<K, V>>();
	    if(root == null)
		    return null;
	    else
	    {
		   // keyList.add(root.kvp.getKey());
		    return this.root.entrySet(entrySet);
	    }
	    
  }
  
  //converts BST to string
  public String toString() 
  {
	    String result = "";
	    if(root == null)
		    return "";
	    else
	    {
		   // keyList.add(root.kvp.getKey());
		    return this.root.toString("Root " +result);
	    }
  }
  
  //toString to text file
  public String toFileString() 
  {
	    String result2 = "";
	    if(root == null)
		    return "";
	    else
	    {
		   // keyList.add(root.kvp.getKey());
		    return this.root.toFileString(result2);
	    }
  }

  //returns size of map
  public int size() 
  {
	    if(root == null)
		    return 0;
	    else
	    {
		    return this.root.size();
	    }
  }
  
  //returns the depth of the map (left)
  public int depthL() 
  {
	  if(root == null)
		    return -1;
	  else
	  {
		    return this.root.depthL();
	  }  
  }
  
  //returns the depth of the map (right)
  public int depthR() 
  {
	  if(root == null)
		    return -1;
	  else
	  {
		    return this.root.depthR();
	  }  
  }

  //clears map
  public void clear() 
  {
  		if(root != null)
  		{
  			this.root.clear();
  		}
  	
  }

  // Write stubs (functions with no code) for the remaining
  // functions in the MapSet interface


	// entrySet notes: For the sake of the word-counting project, the
  // pairs should be added to the list by a pre-order traversal.



  // You can make this a nested class
  private class TNode 
  {
          // need fields for the left and right children
          // need a KeyValuePair to hold the data at this node
	    	  private TNode left, right;
	    	  private KeyValuePair<K,V> kvp;
	    	  

          // constructor, given a key and a value
          public TNode( K k, V v ) 
          {
                  // initialize all of the TNode fields
        	  left = right = null;
        	  kvp = new KeyValuePair<K,V>(k, v);
        	  	
          }

        // Takes in a key, a value, and a comparator and inserts
        // the TNode in the subtree rooted at this node 
		// Returns the value associated with the key in the subtree
		// rooted at this node or null if the key does not already exist
          public V put( K key, V value, Comparator<K> comp ) 
          {
        	  // implement the binary search tree put
               // insert only if the key doesn't already exist
        	  //if the keys are the same
        	  if(comp.compare(this.kvp.getKey(), key) == 0) 
        	  {
        		  V oldVal = this.kvp.getValue();
        		  this.kvp.setValue(value);
        		  return oldVal;
        	  }
        	  
        	  //if the key is larger
        	  if (comp.compare(this.kvp.getKey(), key) < 0)
        	  {
        		  //insert in the left subtree
        		  if(this.left == null)
        		  {
        			  this.left = new TNode(key, value);
        			  return null;
        		  }
    			 return this.left.put(key, value, comp);
        	  } 
        	  
        	  //if the key is smaller
        	  if(this.right == null)
    			{
      			  this.right = new TNode(key, value);
      			  return null;
    			}
        	  
    			 //insert in the right subtree
    			 return this.right.put(key, value, comp);
          }

          // Takes in a key and a comparator
          // Returns the value associated with the key or null
          public V get( K key, Comparator<K> comp ) 
          {	  
        	  //if the keys are the same
        	  if(comp.compare(this.kvp.getKey(), key) == 0) 
        	  {
        		  return this.kvp.getValue();
        	  }
       
        	  //if the key is smaller
        	  if (comp.compare(this.kvp.getKey(), key) < 0)
        	  {
        		  //get the value in the left subtree
        		  if(this.left != null)
        		  {
        			  return this.left.get(key, comp);
        		  } 
        	  }
      
        	  //if the key is larger
        	  if(this.right != null)
        	  {
        		  //get the value in the right subtree
        		 return this.right.get(key, comp);
        	  }
    			 return null;

          }

       // Any additional methods you want to add below, for
       // example, for building ArrayLists
          
          // Returns true if the map contains a key-value pair with the given key
          public boolean containsKey(K key) 
          {
        	  if(comp.compare(this.kvp.getKey(), key) == 0) 
        	  {
        		  return true;
        	  }
       
        	  //if the key is smaller
        	  if (comp.compare(this.kvp.getKey(), key) < 0)
        	  {
        		  //get the value in the left subtree
        		  if(this.left != null)
        		  {
        			  return this.left.containsKey(key);
        		  } 
        	  }
      
        	  //if the key is larger
        	  if(this.right != null)
        	  {
        		  //get the value in the right subtree
        		 return this.right.containsKey(key);
        	  }
    			 //insert in the right subtree
    			 return false;
          }
          
          // Returns an ArrayList of all the keys in the map. There is no
          // defined order for the keys.
          public ArrayList<K> keySet(ArrayList<K> keyList)
          {
        	  keyList.add(this.kvp.getKey());
        	  if(this.left != null)
        	  {
        		  this.left.keySet(keyList);
        	  }
        	  if(this.right != null)
        	  {
        		  this.right.keySet(keyList);
        	  }
        	  
        	  return keyList;
          }
          
          
          // Returns an ArrayList of all the values in the map. These should
          // be in the same order as the keySet.
          public ArrayList<V> values(ArrayList<V> valueList) 
          {
        	  valueList.add(this.kvp.getValue());
        	  if(this.left != null)
        	  {
        		  this.left.values(valueList);
        	  }
        	  if(this.right != null)
        	  {
        		  this.right.values(valueList);
        	  }
        	  
        	  return valueList;
          }
          
          // return an ArrayList of pairs.
          public ArrayList<KeyValuePair<K, V>> entrySet(ArrayList<KeyValuePair<K, V>> entrySet) 
          {
        	  entrySet.add(this.kvp);
        	  if(this.left != null)
        	  {
        		  this.left.entrySet(entrySet);
        	  }
        	  if(this.right != null)
        	  {
        		  this.right.entrySet(entrySet);
        	  }
        	  
        	  return entrySet;
          }
          
          // Returns the number of key-value pairs in the map.
          public int size() 
          {
        	  int size= 1;
        	  if(this.left != null)
        	  {
        		  //System.out.println("left");
        		  size+=this.left.size();
        	  }
        	  if(this.right != null)
        	  {
        		 // System.out.println("right");
        		  size+=this.right.size();
        	  }
        	  
        	  return size;
          }
          
          public int depthL() 
          {
        	  int depthL= 0;
        	  
        	  if(this.left != null)
        	  {
        		  //System.out.println("left");
        		  depthL =this.left.depthL() +1;
        	  }
        		  return depthL;
          }
          
          public int depthR() 
          {
        	  int depthR= 0;
        	  
        	  if(this.right != null)
        	  {
        		  //System.out.println("left");
        		  depthR =this.right.depthL() +1;
        	  }
        		  return depthR;
          }
          
          // removes all mappings from this MapSet
          public void clear() 
          {
        	  if(left!= null)
        	  {
        		  left = null;
        	  }
        	  if(right!= null)
        	  {
        		  right = null;
        	  }
        	  root = null;
          }
          
          // return an ArrayList of pairs.
          public String toString(String result) 
          {
        	  result += this.kvp + " \n";
        	  if(this.left != null)
        	  {
        		 result += this.left.toString("Left: ");
        	  }
        	  if(this.right != null)
        	  {
        		 result += this.right.toString("Right: ");
        	  }
        	  
        	  return result;
          }
          
          //toString to File
          public String toFileString(String result2) 
          {
        	  result2 += this.kvp + " \n";
        	  if(this.left != null)
        	  {
        		 result2 += this.left.toFileString("");
        	  }
        	  if(this.right != null)
        	  {
        		 result2 += this.right.toFileString("");
        	  }
        	  
        	  return result2;
          }
     

          
          
  }// end of TNode class

  // test function
  /*public static void main( String[] argv ) {
          

          // create a BSTMap
          BSTMap<String, Integer> bst = new BSTMap<String, Integer>( new AscendingString() );

          bst.put( "twenty", 20 );
          bst.put( "ten", 10 );
          bst.put( "eleven", 11 );
          bst.put( "five", 5 );
          bst.put( "six", 6 );
         // System.out.println(bst.keySet());
         // System.out.println(bst.values());
         // System.out.println(bst.entrySet());
          System.out.println(bst.toString());
          //System.out.println(bst.toFileString());
         // System.out.println(bst.size());
        //  bst.clear();
          //System.out.println(bst.size());

         // System.out.println( bst.get( "eleven" ) );
         // System.out.println( bst.get( "ten" ) );
         // System.out.println( bst.containsKey( "six" ) );

          // put more test code here

          
  }*/

  //part for hash map
@Override
public int getCollisions() {
	// TODO Auto-generated method stub
	return 0;
}


}