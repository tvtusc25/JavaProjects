//Name: Trey Tuscai
//Date: Nov. 23 2021
//File: PQHeap.java
//Section: A
//Project: 8
//Course: CS231

package wordTrends;

import java.util.Comparator;

public class PQHeap<T> 
{
	//fields
	 private Object[] heap;
	 private int maxSize = 10;
	 private int size;
	 Comparator<T> comp;
	 
	 //constructor
	 public PQHeap(Comparator<T> comparator)
	 {
		 this.size = 0;
		 this.heap = new Object[maxSize+1];
		 this.comp = comparator;
	 }
	
	 //returns the parent
	private int parent(int position)
	{
		return (position - 1) /  2;
	}
	
	//returns the left child
	private int leftChild(int position)
	{
		return (2 * position) + 1;
	} 
	
	//returns the right child
	private int rightChild(int position)
	{
			return (2 * position) + 2;
	}
	 
	//returns the size of heap
	 public int size()
	 {
		 return size;
	 }
	 
	 //swaps the values
	private void swap(int firstPosition, int secondPosition)
	{
			 Object tmp;
			 tmp = heap[firstPosition];
			 heap[firstPosition] = heap[secondPosition];
			 heap[secondPosition] = tmp;
	}
	
	//clears the heap
	public void clear()
	{
		heap = null;
	}
	
	//returns top value
	@SuppressWarnings("unchecked")
	public T peek()
	{
		return (T) heap[0];
	}
	 
	//adds to the heap
	 @SuppressWarnings("unchecked")
	public void add(T obj)
	 {
		 if(this.size > this.maxSize/3)
		 {
			
			this.maxSize *= 2;
			Object[] temp = new Object[maxSize];
			for(int i = 0; i < heap.length; i++)
			{
				temp[i] = heap[i];
			}
			heap = temp;
			//System.out.println("doubling size" + heap.length);	
		 }
		 
		heap[size++] = obj;
			
		// Traverse up and fix violated property
		int current = size-1;
		 
		while (comp.compare( (T) heap[current], (T) heap[parent(current)]) > 0) 
		{
			swap(current, parent(current));
			current = parent(current);
		}
	 }
	 
	 //removes from the heap and returns the removed item
	public T remove()
	{
			if(size > 0)
			{
				 T popped = this.peek();
				 //System.out.println("here " + heap[0]);
				 heap[0] = heap[--size];
				 heap[size+1] = popped;
				 
				 //System.out.println("here " + heap[0]);
				 maxHeapify(0);
				 return (T) popped;
			}
			else
				return null;
	}
	 
	//process for removal to keep heap in order
	@SuppressWarnings("unchecked")
	private void maxHeapify(int position)
	{
			int left = leftChild(position);
			int right = rightChild(position);
			
			while(left <= size)
			{
				Integer maxIndex = left;
				
				if(right < this.size && comp.compare((T) heap[right], (T) heap[left]) > 0)
					maxIndex = right;
				
				if(comp.compare((T) heap[position], (T) heap[maxIndex]) < 0)
				{
					swap(position, maxIndex);
					position = maxIndex;
					left = leftChild(position);
					right = rightChild(position);
					
				}
				else 
				{
					return;
				}
			}
	}

	//returns the heap as a string
	public String toString()
	{
		String heapString = "";
		for(int i = 0; i < size; i++)
		 {
			heapString += heap[i] + ", ";
		 }
		 	return heapString;
	}
	
	//main function
	public static void main (String[] args)
	{
		//PQHeap<KeyValuePair> heap = new PQHeap<KeyValuePair>(;
	}

}
