package evasion.elements;

import java.util.ArrayList;

/**
 * @author Daniel Garcia
 */
public class ObstacleQueue<E> extends ArrayList<E>{

    public E peekFromQueue() {
        if (!(isEmpty())) {
            return get(0);
        }
        return null;
    }

    public void pullFromQueue() {
        if (!(isEmpty())) {
            remove(0);
        }
    }
}
