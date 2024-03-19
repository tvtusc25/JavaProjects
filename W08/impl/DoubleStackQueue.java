package impl;

import common.QueueEmptyException;
import common.QueueFullException;
import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IDoubleStack;
import interfaces.IQueue;

public class DoubleStackQueue implements IQueue {

    private final IDoubleStack doubleStack;

    public DoubleStackQueue(int maxSize) {
        //create queue through a double stack
        this.doubleStack = Factory.getInstance().makeDoubleStack(maxSize);
    }

    @Override
    public void enqueue(Object element) throws QueueFullException {
        //try to push element to stack, catch full
        try {
            doubleStack.getFirstStack().push(element);
        } catch (common.StackOverflowException e) {
            throw new QueueFullException();
        }
    }

    @Override
    public Object dequeue() throws QueueEmptyException {
        try {
            //if second stack is not empty, pop from it
            if (!doubleStack.getSecondStack().isEmpty()) {
                return doubleStack.getSecondStack().pop();
            } else {
                //else push the entire first stack to the second stack
                while (!doubleStack.getFirstStack().isEmpty()) {
                    try {
                        doubleStack.getSecondStack().push(doubleStack.getFirstStack().pop());
                    } catch (common.StackOverflowException e) {
                        throw new RuntimeException();
                    }
                }
                //if the second stack is not empty, pop those elements
                if (!doubleStack.getSecondStack().isEmpty()) {
                    return doubleStack.getSecondStack().pop();
                } else {
                    throw new QueueEmptyException();
                }
            }
            //queue is empty
        } catch (common.StackEmptyException e) {
            throw new QueueEmptyException();
        }
    }


    @Override
    public int size() {
        //returns the size of the queue by combining the stack sizes
        return doubleStack.getFirstStack().size() + doubleStack.getSecondStack().size();
    }

    @Override
    public boolean isEmpty() {
        //returns true if both stacks are empty
        return doubleStack.getFirstStack().isEmpty() && doubleStack.getSecondStack().isEmpty();
    }

    @Override
    public void clear() {
        //clears both stacks
        doubleStack.getFirstStack().clear();
        doubleStack.getSecondStack().clear();
    }
}
