package com.twojr.protocol.devices;

import com.twojr.protocol.network.NetworkPacket;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jason on 3/29/2017.
 */
public class ReceiverRunnable implements Runnable {

    public ReceiverRunnable(InputStream inputStream, int sleepTime) {
        inRadioStream = inputStream;
        lock = new ReentrantLock();
        incomingPackets = new LinkedList<NetworkPacket>();
        this.sleepTime = sleepTime;
    }

    public ReceiverRunnable(InputStream inputStream) {
        inRadioStream = inputStream;
        lock = new ReentrantLock();
        incomingPackets = new LinkedList<NetworkPacket>();
        sleepTime = 100;
    }

    private boolean DEBUG = true;
    private InputStream inRadioStream;
    private Thread receiverThread;
    private LinkedList<NetworkPacket> incomingPackets;
    private ReentrantLock lock;
    private boolean running;
    private int sleepTime;
    private int packetSize = 127;

    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }


    public LinkedList<NetworkPacket> getAllPackets() {
        try {
            lock.lock();
            LinkedList<NetworkPacket> out = incomingPackets;
            incomingPackets = new LinkedList<NetworkPacket>();
            return out;
        } catch (Exception ex) {
            System.err.println("Error getting all receiver packets");
            if (DEBUG)
                ex.printStackTrace();
        } finally {
            lock.unlock();
        }

        return null;
    }

    public NetworkPacket getNextPacket() {
        try {
            lock.lock();
            if (incomingPackets.size() > 0)
                return incomingPackets.pop();
        } catch (Exception ex) {
            System.err.println("Error getting all receiver packets");
            if (DEBUG)
                ex.printStackTrace();
        } finally {
            lock.unlock();
        }

        return null;
    }

    public boolean hasPackets() {
        try {
            lock.lock();
            boolean hasPackets = incomingPackets.size() > 0;
            return hasPackets;

        } catch (Exception ex) {
            System.err.println("Error getting all receiver packets");
        } finally {
            lock.unlock();
        }
        return false;
    }

    public void stop() {
        running = false;
    }

    public void start() {
        running = true;
        if (receiverThread == null) {
            receiverThread = new Thread(this, "ReceiveThread");
            receiverThread.start();
        }
    }

    @Override
    public void run() {
        running = true;
        byte networkPacketStream[] = new byte[packetSize];
        try {
            while (running) {
                byte firstByte[] = new byte[1];
                // Wait for start indicator
                while (inRadioStream.read(firstByte) == 0 && running) {
                    receiverThread.sleep(sleepTime);
                }
                networkPacketStream[0] = firstByte[0];
                // Start reading stream
                inRadioStream.read(networkPacketStream);

                // Encapsulate data in NetworkPacket
                NetworkPacket networkPacketHeader = new NetworkPacket(networkPacketStream);

                // Add networkPacket to incoming packets list
                lock.lock();
                incomingPackets.addLast(networkPacketHeader);
                lock.unlock();
            }
        } catch (IOException ex) {
            System.err.println("Radio Listener Exception on listen");
            if (DEBUG)
                ex.printStackTrace();
        } catch (InterruptedException iEx) {
            System.err.println("Receiver Interrupted Exception");
            if (DEBUG)
                iEx.printStackTrace();
        }
    }
}
