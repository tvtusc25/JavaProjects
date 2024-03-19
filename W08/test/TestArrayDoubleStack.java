package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IDoubleStack;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests array collection implementation.
 */
public class TestArrayDoubleStack extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;

    /**
     * Tests that the factory constructs a non-null double stack.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackObject() {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertNotNull(doubleStack1, "Failure: IFactory.makeDoubleStack returns null, expected non-null object");
    }

    /**
     * Tests pushing and popping elements from the double stack.
     * @throws StackOverflowException should not be thrown.
     * @throws StackEmptyException should not be thrown.
     */
    @Test
    public void pushAndPopElements() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);

        // Push some elements
        doubleStack.getFirstStack().push(1);
        doubleStack.getSecondStack().push(2);

        // Pop and check the elements
        assertEquals(1, doubleStack.getFirstStack().pop());
        assertEquals(2, doubleStack.getSecondStack().pop());
    }

    /**
     * Test StackOverflow by pushing too many elements to stack.
     * @throws StackOverflowException should be thrown.
     * @throws StackEmptyException should not be thrown.
     */
    @Test
    public void pushToFullStack() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(2);

        doubleStack.getFirstStack().push(1);

        assertThrows(StackOverflowException.class, () -> doubleStack.getFirstStack().push(2));
    }

    /**
     * Test stack empty exception by popping from empty second stack.
     * @throws StackOverflowException should not be thrown.
     * @throws StackEmptyException should be thrown.
     */
    @Test
    public void popFromEmptyStack() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(2);

        doubleStack.getFirstStack().push(1);

        assertThrows(StackEmptyException.class, () -> doubleStack.getSecondStack().pop());
    }

    /**
     * Test pushing and popping elements from both stacks.
     * @throws StackOverflowException should not be thrown.
     * @throws StackEmptyException should not be thrown.
     */
    @Test
    public void usingBothStacks() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(4);

        //push 1 and 2 to first
        doubleStack.getFirstStack().push(1);
        doubleStack.getFirstStack().push(2);
        //push 3 to second
        doubleStack.getSecondStack().push(3);

        assertEquals(2, doubleStack.getFirstStack().pop());
        assertEquals(3, doubleStack.getSecondStack().pop());

        //push 4 to first
        doubleStack.getFirstStack().push(4);
        //push 5 to second
        doubleStack.getSecondStack().push(5);

        assertEquals(5, doubleStack.getSecondStack().pop());
        assertEquals(4, doubleStack.getFirstStack().pop());
    }

    /**
     * Test with uneven stack size allocation. Stack one will always get 1 less.
     * @throws StackOverflowException should not be thrown.
     * @throws StackEmptyException should not be thrown.
     */
    @Test
    public void unevenStackSize() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(5);

        //contains two elements
        doubleStack.getFirstStack().push(1);
        doubleStack.getFirstStack().push(2);

        //contains three elements
        doubleStack.getSecondStack().push(3);
        doubleStack.getSecondStack().push(4);
        doubleStack.getSecondStack().push(5);

        assertEquals(2, doubleStack.getFirstStack().size());
        assertEquals(3, doubleStack.getSecondStack().size());
    }

    /**
     * Test clearing the stack.
     * @throws StackOverflowException should not be thrown.
     * @throws StackEmptyException should not be thrown.
     */
    @Test
    public void clearStack() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(3);

        doubleStack.getFirstStack().push(1);
        doubleStack.getSecondStack().push(2);
        doubleStack.getSecondStack().push(3);

        doubleStack.getFirstStack().clear();
        doubleStack.getSecondStack().clear();

        assertTrue(doubleStack.getFirstStack().isEmpty());
        assertTrue(doubleStack.getSecondStack().isEmpty());
    }

    /**
     * Test pushing elements to each stack and checking their size.
     * @throws StackOverflowException should not be thrown.
     * @throws StackEmptyException should not be thrown.
     */
    @Test
    public void pushAndCheckSize() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(5);

        doubleStack.getFirstStack().push(1);
        doubleStack.getFirstStack().push(2);
        doubleStack.getSecondStack().push(3);
        doubleStack.getSecondStack().push(4);

        assertEquals(2, doubleStack.getFirstStack().size());
        assertEquals(2, doubleStack.getSecondStack().size());
    }

    /**
     * Test getting the top with a stack with elements.
     * @throws StackOverflowException should be thrown.
     * @throws StackEmptyException should not be thrown.
     */
    @Test
    public void testTopWhenStackIsNotEmpty() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(5);
        doubleStack.getFirstStack().push(1);
        doubleStack.getFirstStack().push(2);

        assertEquals(2, doubleStack.getFirstStack().top());
    }

    /**
     * Test getting the top when the stack is empty.
     * @throws StackOverflowException should not be thrown.
     * @throws StackEmptyException should be thrown.
     */
    @Test
    public void testTopWhenStackIsEmpty() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(5);

        StackEmptyException exception = assertThrows(StackEmptyException.class, () -> doubleStack.getFirstStack().top());
    }

    /**
     * Tests pushing and popping a large number of elements.
     * @throws StackOverflowException should not be thrown.
     * @throws StackEmptyException should not be thrown.
     */
    @Test
    public void edgeCaseMaxSize() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(1000);

        for (int i = 0; i < 500; i++) {
            doubleStack.getFirstStack().push(i);
        }

        for (int i = 499; i >= 0; i--) {
            assertEquals(i, doubleStack.getFirstStack().pop());
        }
    }

    /**
     * Tests the min case.
     * @throws StackEmptyException should not be thrown.
     */
    @Test
    public void edgeCaseMinSize() throws StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(0);
        assertTrue(doubleStack.getFirstStack().isEmpty());
        assertTrue(doubleStack.getSecondStack().isEmpty());
    }

    /**
     * Tests mixed data types.
     * @throws StackOverflowException should not be thrown.
     * @throws StackEmptyException should not be thrown.
     */
    @Test
    public void mixedDataTypes() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);

        doubleStack.getFirstStack().push(1);
        doubleStack.getFirstStack().push("Two");
        assertEquals("Two", doubleStack.getFirstStack().pop());
        assertEquals(1, doubleStack.getFirstStack().pop());
    }
}
