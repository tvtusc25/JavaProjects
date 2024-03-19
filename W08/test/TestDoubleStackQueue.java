package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import common.QueueEmptyException;
import common.QueueFullException;
import interfaces.IQueue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Tests double stack queue implementation.
 */
public class TestDoubleStackQueue extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;

    /**
     * Tests that the factory constructs a non-null object.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackQueue() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        assertNotNull(queue, "Failure: IFactory.makeDoubleStackQueue returns null, expected non-null object");
    }

    /**
     * Tests if you can successfully enqueue elements into the queue and then dequeue them in the correct order.
     * @throws QueueFullException should not be thrown.
     * @throws QueueEmptyException should not be thrown.
     */
    @Test
    public void enqueueAndDequeue() throws QueueFullException, QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    /**
     * Tests if the size method returns the correct size of the queue after enqueuing and dequeuing elements.
     * @throws QueueFullException should not be thrown.
     * @throws QueueEmptyException should not be thrown.
     */
    @Test
    public void queueSize() throws QueueFullException, QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);

        assertEquals(0, queue.size());

        queue.enqueue(1);
        assertEquals(1, queue.size());

        queue.enqueue(2);
        assertEquals(2, queue.size());

        queue.dequeue();
        assertEquals(1, queue.size());

        queue.dequeue();
        assertEquals(0, queue.size());
    }

    /**
     * Test if the isEmpty method correctly identifies an empty queue and a non-empty queue.
     * @throws QueueFullException should not be thrown.
     * @throws QueueEmptyException should not be thrown.
     */
    @Test
    public void isEmpty() throws QueueFullException, QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);

        assertTrue(queue.isEmpty());

        queue.enqueue(1);
        assertFalse(queue.isEmpty());

        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    /**
     * Tests if the clear method clears all elements from the queue.
     * @throws QueueFullException should not be thrown.
     * @throws QueueEmptyException should not be thrown.
     */
    @Test
    public void clearQueue() throws QueueFullException, QueueEmptyException {
       IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);

        queue.enqueue(1);
        queue.enqueue(2);

        assertFalse(queue.isEmpty());

        queue.clear();

        assertTrue(queue.isEmpty());
    }

    /**
     * Tests if a QueueFullException is thrown when trying to enqueue an element into a full queue.
     * @throws QueueFullException should be thrown.
     */
    @Test
    public void queueToFullStack() throws QueueFullException {
        IQueue queue = getFactory().makeDoubleStackQueue(2);

        queue.enqueue(1);

        assertThrows(QueueFullException.class, () -> queue.enqueue(2));
    }

    /**
     * Tests dequeueing from an empty queue.
     * @throws QueueEmptyException should be thrown.
     */
    @Test
    public void dequeueFromEmptyQueue() throws QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        assertThrows(QueueEmptyException.class, () -> queue.dequeue());
    }

    /**
     * Tests enqueueing elements, clearing the queue, and then enqueueing new elements.
     * @throws QueueFullException should not be thrown.
     * @throws QueueEmptyException should not be thrown.
     */
    @Test
    public void enqueueAfterClear() throws QueueFullException, QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.clear();
        queue.enqueue(3);

        assertEquals(3, queue.dequeue());
    }

    /**
     * Tests enqueueing and dequeueing a large number of elements.
     * @throws QueueFullException should not be thrown.
     * @throws QueueEmptyException should not be thrown.
     */
    @Test
    public void edgeCaseMaxSize() throws QueueFullException, QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(1000);
        for (int i = 0; i < 500; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 500; i++) {
            assertEquals(i, queue.dequeue());
        }
    }

    /**
     * Tests the min case.
     * @throws QueueEmptyException should not be thrown.
     */
    @Test
    public void edgeCaseMinSize() throws QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(0);
        assertTrue(queue.isEmpty());
    }

    /**
     * Tests mixed data types.
     * @throws QueueFullException should not be thrown.
     * @throws QueueEmptyException should not be thrown.
     */
    @Test
    public void mixedDataTypes() throws QueueFullException, QueueEmptyException {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);

        queue.enqueue(1);
        queue.enqueue("Two");
        assertEquals(1, queue.dequeue());
        assertEquals("Two", queue.dequeue());
    }
}
