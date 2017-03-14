package com.twojr.protocol.network;

/**
 * Created by Jason on 2/14/2017.
 */
public interface IDevice {
    void start();
    void close();
    void send();
    void read();
    void discover();
    String print();
}
