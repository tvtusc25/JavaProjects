package impl;

import interfaces.ITransition;

/**
 * Class representing a single transition for an FSM, equivalent to a row in a transition table.
 *
 */
public class Transition implements ITransition {
    private int currentState;
    private char input;
    private char output;
    private int nextState;

    public Transition(int currentState, char input, char output, int nextState) {
        this.currentState = currentState;
        this.input = input;
        this.output = output;
        this.nextState = nextState;
    }

    @Override
    public int getCurrentState() {
        // TODO Auto-generated method stub
        return currentState;
    }

    @Override
    public char getInput() {
        // TODO Auto-generated method stub
        return input;
    }

    @Override
    public char getOutput() {
        // TODO Auto-generated method stub
        return output;
    }

    @Override
    public int getNextState() {
        // TODO Auto-generated method stub
        return nextState;
    }

}
