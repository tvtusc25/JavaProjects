package impl;

import interfaces.IDoubleStack;
import interfaces.IFactory;
import interfaces.IQueue;

/**
 * This class implements a singleton factory.
 *
 */
public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    private Factory() {

    }

    /**
     * Method which returns an instance of the singleton Factory class.
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    @Override
    public IDoubleStack makeDoubleStack(int maxSize) {
        //return a double stack
        return new DoubleStack(maxSize);
    }

    @Override
    public IQueue makeDoubleStackQueue(int maxSize) {
        //return a double stack queue
        return new DoubleStackQueue(maxSize);
    }

}
