//Name: Trey Tuscai
//Date: Oct. 27 2021
//File: MyQueue.java
//Section: A
//Project: 5
//Course: CS231

package checkoutLines;

import java.util.ArrayList;
import java.util.Iterator;

public class MyQueue<T> implements Iterable<T>
{
	@SuppressWarnings("hiding")
	private class Node<T>
	{
		//variables
		private Node<T> next;
		private Node<T> prev;
		private T val;
		
		//constructor for Node
		public Node (T val)
		{
			this.next = null;
			this.prev = null;
			this.val = val;
			
		}
		
		//returns a value
		public T getVal()
		{
			return this.val;
		}
		
		//returns the next Node
		public Node<T> getNext()
		{
			return this.next;
		}
		
		//returns the previous Node
		@SuppressWarnings("unused")
		public Node<T> getPrev()
		{
			return this.prev;
		}
		
		//sets the next Node value to n
		public void setNext(Node<T> n)
		{
			this.next = n;
		}
		
		//sets the previous Node value to p
		public void setPrev(Node<T> p)
		{
			this.prev = p;
		}	
	}
	
	//variables
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	//constructor for Queue
	public MyQueue()
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	// Inserts item into this queue if possible. Returns true if successful.
	public boolean offer(T item) 
	{
		Node<T> newNode = new Node<T>(item);
		
		if(this.size== 0)
		{
			this.tail = newNode;
			this.head = newNode;
		}
		else
		{
			newNode.setPrev(this.tail);
			this.tail.setNext(newNode);
			this.tail = newNode;
		}
		
		size++;
		return true;
	}

	// Returns, but does not remove, the head of this queue, or returns null if this queue is empty.
	public T peek() 
	{
		if (this.head == null)
		{
			return null;	
		}
			return this.head.getVal();
	}
		
	// Returns and removes the head of this queue, or returns null if this queue is empty.
	public T poll() 
	{
		T remove = null;
		
		if(this.size == 0)
		{
			System.out.println("Empty queue, cannot poll! ");
		}
		else if (size == 1)
		{
			remove = this.head.getVal();
			this.tail = null;
			this.head = null;
		}
		else
		{
			remove = this.head.getVal();
			this.head = this.head.getNext();
			this.head.setPrev(null);
		}
		
		this.size--;
		return remove;
	}
	
	//returns the size of the queue
	public int getSize()
	{
		return this.size;
	}
	
	//implements queue iterator
	private class QIterator implements Iterator<T> 
	{
		 private Node<T> current;

		 //constructor for iterator
		 public QIterator(Node<T> head) 
		 {
			 current = head;
		 }
		 
		 //check whether the queue has next or not
		 public boolean hasNext() 
		 {
			if (current != null) 
			{
				return true;
			}
		 return false;
		 }
		 
		 // returns the value of the next item in the list
		 public T next() 
		 {
			 if (this.hasNext() == true) 
			 {
				 T val = current.getVal();
				 current = current.getNext();
				 return val;
			 }
		 return null;
		 }
		 
	}

	//returns an iterator that points to the head of the queue
	public Iterator<T> iterator() 
	{
		return new QIterator(this.head);
	}
	
	//converts queue to ArrayList
	public ArrayList<T> toArrayList()
	{
		ArrayList<T> result = new ArrayList<T>();
		//flag <T> if issues persist
		Iterator<T> iter = this.iterator();
		
		while(iter.hasNext())
		{
			result.add(iter.next());
		}
		
		return result;
	}
	
	/*public static void main (String[] args)
	{
		MyQueue<Integer> queue = new MyQueue<Integer>();
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		queue.offer(4);
		queue.offer(5);
		ArrayList<Integer> alist = queue.toArrayList();
		for(Integer item: alist) 
		{
			System.out.printf("item: %d\n", item);
		}
		System.out.println("peeking: " + queue.peek());
		queue.poll();
		queue.poll();
		queue.poll();
		queue.poll();
		queue.poll();
		queue.poll();
		ArrayList<Integer> blist = queue.toArrayList();
		for(Integer item: blist) 
		{
			System.out.printf("item: %d\n", item);
		}
		
	}*/
	
}
