package com.twojr.protocol.devices;

import com.twojr.protocol.network.NetworkPacket;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jason on 3/29/2017.
 */
public class TransmitterRunnable implements Runnable {

    public TransmitterRunnable(OutputStream outStream, int sleepTime) {
        netPackets = new LinkedList<NetworkPacket>();
        this.outStream = outStream;
        lock = new ReentrantLock();
        running = true;
        this.sleepTime = sleepTime;
    }

    public TransmitterRunnable(OutputStream outStream) {
        netPackets = new LinkedList<NetworkPacket>();
        this.outStream = outStream;
        lock = new ReentrantLock();
        running = true;
    }

    private boolean DEBUG = false;
    private LinkedList<NetworkPacket> netPackets;
    private OutputStream outStream;
    private Thread transmitThread;
    private ReentrantLock lock;
    private boolean running;
    private int sleepTime;
    private int packetSize;

    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }

    public void queuePackets(LinkedList<NetworkPacket> newPackets) {
        lock.lock();
        for (NetworkPacket netPacket : newPackets) {
            //System.err.println(netPacket.print());
            netPackets.addLast(netPacket);
        }
        lock.unlock();
    }

    public LinkedList<NetworkPacket> getQueuedPackets() {
        return netPackets;
    }

    public void start() {
        running = true;
        if (transmitThread == null) {
            transmitThread = new Thread(this, "TransmitThread");
            transmitThread.start();
        }
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        try {
            while (running) {
                lock.lock();
                while (netPackets.size() > 0 && running) {
                    // Transmit
                    outStream.write(0x07);
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
