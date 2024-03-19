package impl;

import exceptions.BadTableException;
import exceptions.BadInputException;
import exceptions.NDTransitionException;
import interfaces.IFiniteStateMachine;
import interfaces.ITransition;
import interfaces.ITransitionTable;

/**
 * Class representing a finite state machine.
 *
 */
public class FiniteStateMachine implements IFiniteStateMachine {

    @Override
    public void addTransition(ITransition transition) throws NDTransitionException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String interpret(String input) throws BadTableException, BadInputException {
        // TODO Auto-generated method stub
        return null;
    }

}
