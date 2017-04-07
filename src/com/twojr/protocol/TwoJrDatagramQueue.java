package com.twojr.protocol;

import java.util.LinkedList;

/**
 * Created by Jason on 4/7/2017.
 */
public class TwoJrDatagramQueue {

    public TwoJrDatagramQueue() {
        valuesList = new LinkedList<>();
    }

    // List containing all of the datagrams
    LinkedList<TwoJrDataGram> valuesList;

    // Inserts datagram at the end of the queue
    public void insert(TwoJrDataGram nextIn) {
        valuesList.addLast(nextIn);
    }

    // Returns the next datagram in the queue
    public TwoJrDataGram getNext() {
        return valuesList.removeFirst();
    }

    // Returns first element in queue without removing it from the list
    public TwoJrDataGram peekNext() {
        return valuesList.peekFirst();
    }

    // Returns last element in queue without removing it from the list
    public TwoJrDataGram peekLast() {
        return valuesList.peekLast();
    }

    // Places datagram add the head of the queue
    public void insertHead(TwoJrDataGram nextFirst) {
        valuesList.addFirst(nextFirst);
    }


}
