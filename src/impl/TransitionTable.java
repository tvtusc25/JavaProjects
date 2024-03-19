package impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.BadInputException;
import exceptions.NDTransitionException;
import interfaces.ITransition;
import interfaces.ITransitionTable;

/**
 * Class representing a transition table for an FSM.
 * 
 */
public class TransitionTable implements ITransitionTable {
    private final List<ITransition> transitions; // List of transitions

    public TransitionTable() {
        transitions = new ArrayList<>();
    }

    @Override
    public void addTransition(ITransition transition) throws NDTransitionException {
        // Check for duplicate transitions in the list
        if (transitions.contains(transition)) {
            throw new NDTransitionException();
        }

        // Add the transition to the list
        transitions.add(transition);
    }

    @Override
    public ITransition getTransition(int current_state, char input) throws BadInputException {
        // Search for the transition based on current_state and input
        for (ITransition transition : transitions) {
            if (transition.getCurrentState() == current_state && transition.getInput() == input) {
                return transition;
            }
        }

        // If no matching transition is found, throw an exception
        throw new BadInputException();
    }

    @Override
    public boolean hasTransitionsToIllegalStates() {
        //ArrayList to store valid states
        List<Integer> validStates = new ArrayList<>();

        //Populate the list with all valid states
        for (ITransition transition : transitions) {
            int currentState = transition.getCurrentState();
            int nextState = transition.getNextState();
            
            //Add currentState and nextState if not already in the list
            if (!validStates.contains(currentState)) {
                validStates.add(currentState);
            }
            if (!validStates.contains(nextState)) {
                validStates.add(nextState);
            }
        }

        //Check if any transitions point to illegal (non-existent) states
        for (ITransition transition : transitions) {
            int nextState = transition.getNextState();
            
            //If the nextState is not in the list of valid states, it's illegal
            if (!validStates.contains(nextState)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasMissingInputs() {
        // TODO Auto-generated method stub
        return false;
    }

}
