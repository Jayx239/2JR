package com.twojr.protocol.devices;

import com.twojr.protocol.network.NetworkPacket;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

import static com.twojr.protocol.network.INetPacket.MAXPACKETSIZE;

/**
 * Created by Jason on 3/29/2017.
 */
public class TransmitterRunnable implements Runnable {

    public TransmitterRunnable(OutputStream outStream, int sleepTime) {
        netPackets = new LinkedList<NetworkPacket>();
        this.outStream = outStream;
        lock = new ReentrantLock();
        running = true;
        this.sleepTimeMs = sleepTimeMs;
        this.sleepTimeNs = sleepTimeNs;;
        this.packetSize = MAXPACKETSIZE;
    }

    public TransmitterRunnable(OutputStream outStream) {
        this.netPackets = new LinkedList<NetworkPacket>();
        this.outStream = outStream;
        this.lock = new ReentrantLock();
        this.running = true;
        this.sleepTimeMs = 0;
        this.sleepTimeNs = 100;
        this.packetSize = MAXPACKETSIZE;
    }

    private boolean DEBUG = false;
    private LinkedList<NetworkPacket> netPackets;
    private OutputStream outStream;
    private Thread transmitThread;
    private ReentrantLock lock;
    private boolean running;
    private int sleepTimeMs;
    private int sleepTimeNs;
    private int packetSize;

    public void setDEBUG(boolean value) {
        DEBUG = value;
    }
    // Method for setting packet size
    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }

    // Method for adding packets to queue for transmission
    public void queuePackets(LinkedList<NetworkPacket> newPackets) {
        lock.lock();
        for (NetworkPacket netPacket : newPackets) {
            //System.err.println(netPacket.print());
            netPackets.addLast(netPacket);
        }
        lock.unlock();
    }

    // Method for retrieving network packets queued for transmission
    public LinkedList<NetworkPacket> getQueuedPackets() {
        return netPackets;
    }

    // Method to start the thread
    public void start() {
        running = true;
        if (transmitThread == null) {
            transmitThread = new Thread(this, "TransmitThread");
            transmitThread.start();
        }
    }

    // Method for stopping thread, breaks run loop
    public void stop() {
        running = false;
    }

    // Method containing transmitter thread logic
    @Override
    public void run() {
        try {
            while (running) {
                lock.lock();
                while (netPackets.size() > 0 && running) {
                    // Transmit packet when packets are queued
                    outStream.write(netPackets.removeLast().toByte());
                }
                lock.unlock();
            }
        } catch (IOException ex) {
            System.err.println("IOException while writing to radio");
            if (DEBUG) {
                ex.printStackTrace();
            }
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }
}
