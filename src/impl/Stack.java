package impl;

import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IStack;

public class Stack implements IStack {
    //Shared Array
    private Object[] array;
    private int startIndex;
    private int endIndex;
    private int size;

    public Stack(Object[] array, int startIndex, int endIndex) {
        //constructor
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.size = 0;
    }

    @Override
    public void push(Object element) throws StackOverflowException {
        //if size is within margins, add to the array
        if (size >= endIndex - startIndex) {
            throw new StackOverflowException();
        }
        array[startIndex + size] = element;
        size++;
    }

    @Override
    public Object pop() throws StackEmptyException {
        //if not empty, decrease the size and return the new array
        if (isEmpty()) {
            throw new StackEmptyException();
        }
        size--;
        return array[startIndex + size];
    }

    @Override
    public Object top() throws StackEmptyException {
        //if not empty, return the top of the array
        if (isEmpty()) {
            throw new StackEmptyException();
        }
        return array[startIndex + size - 1];
    }

    @Override
    public int size() {
        //returns the size
        return size;
    }

    @Override
    public boolean isEmpty() {
        //returns true if the size is 0
        return size == 0;
    }

    @Override
    public void clear() {
        //sets the array size to 0 and sets the elements to null
        for (int i = startIndex; i < startIndex + size; i++) {
            array[i] = null;
        }
        size = 0;
    }
}
