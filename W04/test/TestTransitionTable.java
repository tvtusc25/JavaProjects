package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import impl.Factory;
import interfaces.IFactory;
import interfaces.ITransitionTable;

/**
 * This is a JUnit test class for the FSM ADT.
 */
public class TestTransitionTable {


    private IFactory factory;
    private ITransitionTable transitionTable;


    /**
     * JUnit setup method to run before every other test.
     */
    @BeforeEach
    public void setup() {
        factory = Factory.getInstance();
        transitionTable = factory.makeTransitionTable();
    }


    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of ITransitionTable.
     */
    @Test
    public void transitionTableCreationNonNull() {
        assertNotNull(transitionTable);
    }

    /* ... and many more tests of your own below here to test the ADT ... */

}
