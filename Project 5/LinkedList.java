//Name: Trey Tuscai
//Date: Oct. 27 2021
//File: LinkedList.java
//Section: A
//Project: 5
//Course: CS231

package checkoutLines;

import java.util.Iterator;    // defines the Iterator interface
import java.util.ArrayList;   
import java.util.Collections; // contains a shuffle function

public class LinkedList<T> implements Iterable<T>
{
	// Return a new LLIterator pointing to the head of the list
	public Iterator<T> iterator() 
	{
		return new LLIterator( this.head );
	}
	
	@SuppressWarnings("hiding")
	public class Node<T>
	{
		T data;
		Node<T> next;
		
		//a constructor that initializes next to null and the container field to item.
		public Node(T item)
		{
			this.data = item;
			this.next = null;
		}
		
		//returns the value of the container field.
		public T getThing()
		{
			return this.data;
		}
	
		//sets next to the given node.
		public void setNext(Node<T> n)
		{
			this.next = n;
		}
		
		
		//returns the next field.
		public Node<T> getNext()
		{
			return this.next;
		}
	}
	
	//variables
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	//constructor that initializes the fields so it is an empty list.
	public LinkedList()
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	//empties the list (resets the fields so it is an empty list).
	public void clear()
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	//returns the size of the list.
	public int size()
	{
		return this.size;
	}
	
	//inserts the item at the specified position in the list.
	public void add(int index, T newItem)
	{
		Node<T> current = this.head;
		
		//if size is zero or current is null, there are different situations
		if (this.size == 0 && current == null)
		{
			Node<T> newNode = new Node<T>(newItem);
			this.head = newNode;
			this.tail = newNode;
		}
		else if (index == 0)
		{
			Node<T> newNode = new Node<T>(newItem);
			newNode.setNext(this.head);
			this.head = newNode;
		}
		else	
		{
			Node<T> newNode = new Node<T>(newItem);
			Node<T> temp = this.head;
		
			int counter = 0;
			while(counter < index - 1)
			{
				temp = temp.getNext();
				counter++;
			}
			
			newNode.setNext(temp.getNext());
			temp.setNext(newNode);
		}
		this.size++;
	}
	
	// inserts the item at the beginning of the list.
	public void addFirst(T item)
	{
		if (this.size == 0)
		{
			Node<T> newNode = new Node<T>(item);
			this.head = newNode;
			this.tail = newNode;
		}
		else
		{
			Node<T> newNode = new Node<T>(item);
			newNode.setNext(this.head);
			this.head = newNode;
		}
		
		//System.out.println("added at start: " + item);
		this.size++;
	}
	
	//appends the item at the end of the list.
	public void addLast(T item)
	{

		if (this.size == 0)
		{
			Node<T> newNode = new Node<T>(item);
			this.head = newNode;
			this.tail = newNode;
		}
		else
		{
			Node<T> newNode = new Node<T>(item);
			this.tail.setNext(newNode);
			this.tail = newNode;
		}
		
		//System.out.println("added at end: " + item);
		this.size++;
	}
	
	//removes the item at the specified position in the list.
	public T remove (int index)
	{
		if (this.size != 0)
		{
			Node<T> current = this.head;
			Node<T> previous = null;
			int counter = 0;
			while (counter < index)
			{
				previous = current;
				current = current.getNext();
				
				counter++;
			}
			if (current == this.head)
			{
				T result = current.getThing();
				this.head = this.head.getNext();
				this.size--;
				return result;
			}
			else if (current == this.tail)
			{
				T result = current.getThing();
				this.tail = previous;
				previous.setNext(null);
				this.size--;
				return result;
				
			}
			else
			{
				T result = current.getThing();
				previous.setNext(current.getNext());
				this.size--;
				return result;
			}	
		}
		return null;
	}
	
	public T peek()
	{
		Node<T> current = this.head;
		T result = current.getThing();
		return result;
	}
	
	//returns linkedList as a string for debugging
	public String toString()
	{
		String result = "";
		//write cool code here!
		Node<T> temp = this.head;
		
		while(temp!= null)
		{
			result += temp.getThing() + ", ";
			temp = temp.getNext();
		}
		
		
		return result;
	}
	
	private class LLIterator implements Iterator<T>
	{
		Node<T> current;
		
		//the constructor for the LLIterator given the head of a list.
		public LLIterator(Node<T> head)
		{
			this.current = head;
		}
		
		//returns true if there are still values to traverse (if the current node reference is not null).
		public boolean hasNext()
		{
			return this.current != null;
		}

		//returns the next item in the list, which is the item contained in the current node. 
		//The method also needs to move the traversal along to the next node in the list.
		public T next() 
		{
			if(hasNext())
			{
                    T data = this.current.data;
                    this.current = this.current.next;
                    return data;
                }
                return null;
		}
		
		//does nothing. Implementing this function is optional for an Iterator.
		public void remove()
		{
			//optional implementation
		}
	}
	
	//returns an ArrayList of the list contents in order.
	public ArrayList<T> toArrayList()
	{
		Node<T> temp = this.head;
		ArrayList<T> convert = new ArrayList<T>();
		
		while(temp!= null)
		{
			convert.add(temp.getThing());
			temp = temp.getNext();
		}
		return convert;
	}
	
	//returns an ArrayList of the list contents in shuffled order.
	public ArrayList<T> toShuffledList()
	{
		Node<T> temp = this.head;
		ArrayList<T> convert = new ArrayList<T>();
		
		while(temp!= null)
		{
			convert.add(temp.getThing());
			temp = temp.getNext();
		}
		
		Collections.shuffle(convert);
		return convert;
	}
	
	/*public void reverse()
	{
		Node<T> current = this.head;
		Node<T> nextNode = null;
		Node<T> previous = null;
		
		while (current != null)
		{
			nextNode = current.next;
			current.next = previous;
			previous = current;
			current = nextNode;
		}
		
		this.head = previous;
	}*/
	
	
	/*public static void main(String[] args)
	{
		LinkedList<Integer> myList = new LinkedList<Integer>();
		myList.addLast(4);
		myList.addFirst(8);
		myList.addFirst(10);
		myList.addLast(6);
		myList.add(0,2);
		//myList.remove(0);
		//myList.clear();
		myList.add(0,1);
		myList.add(0,2);
		myList.add(0,3);
		System.out.println(myList.toString());
		ArrayList<Integer> alist = myList.toArrayList();
		for(Integer item: alist) 
		{
			System.out.printf("thing %d\n", item);
		}
		ArrayList<Integer> alist = myList.toShuffledList();
		for(Integer bitem: alist) 
		{
			System.out.printf("thing %d\n", bitem);
		}
		
	}*/
}
