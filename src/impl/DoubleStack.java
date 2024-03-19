package impl;

import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IDoubleStack;
import interfaces.IStack;

public class DoubleStack implements IDoubleStack {
    //Shared Array
    private Object[] array;
    //Stack for first part of array
    private IStack firstStack;
    // Stack for the second part of the array
    private IStack secondStack;

    //Initialize array
    public DoubleStack(int size) {
        array = new Object[size];
        int halfSize = size / 2;
        this.firstStack = new Stack(array, 0, halfSize);
        this.secondStack = new Stack(array, halfSize, size);
    }

    @Override
    public IStack getFirstStack() {
        //return first stack
        return this.firstStack;
    }

    @Override
    public IStack getSecondStack() {
        //return second stack
        return this.secondStack;
    }
}
