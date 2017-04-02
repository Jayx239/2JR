package com.twojr.protocol.devices;

import com.twojr.protocol.network.NetworkPacket;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

import static com.twojr.protocol.network.INetPacket.MAXPACKETSIZE;

/**
 * Created by Jason on 3/29/2017.
 */
public class ReceiverRunnable implements Runnable {

    public ReceiverRunnable(InputStream inputStream, long sleepTimeMs, int sleepTimeNs) {
        this.inRadioStream = inputStream;
        this.lock = new ReentrantLock();
        this.incomingPackets = new LinkedList<NetworkPacket>();
        this.sleepTimeMs = sleepTimeMs;
        this.sleepTimeNs = sleepTimeNs;
        this.packetSize = MAXPACKETSIZE;
    }

    public ReceiverRunnable(InputStream inputStream) {
        this.inRadioStream = inputStream;
        this.lock = new ReentrantLock();
        this.incomingPackets = new LinkedList<NetworkPacket>();
        this.sleepTimeMs = 0;
        this.sleepTimeNs = 100;
        this.packetSize = MAXPACKETSIZE;
    }

    private boolean DEBUG = false;
    private InputStream inRadioStream;
    private Thread receiverThread;
    private LinkedList<NetworkPacket> incomingPackets;
    private ReentrantLock lock;
    private boolean running;
    private long sleepTimeMs;
    private int sleepTimeNs;
    private int packetSize;

    // Method to set debug flag
    public void setDebug(boolean value) {
        DEBUG = value;
    }

    // Method for setting sleep time, milliseconds
    public void setSleepTime(long sleepTimeMilliseconds) {
        setSleepTime(sleepTimeMilliseconds,0);
    }

    // Method for setting sleep time, millisecons and nanoseconds
    public void setSleepTime(long sleepTimeMilliseconds, int sleepTimeNanoSeconds) {
        sleepTimeMs = sleepTimeMilliseconds;
        sleepTimeNs = sleepTimeNanoSeconds;
    }

    // Method for setting network packet size
    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }

    // Method for getting all available packets
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

    // Method for getting next received packet
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

    // Method for checking if receiver contains received packets
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

    // Method for stopping receiver thread
    public void stop() {
        running = false;
    }

    // Method for starting receiver thread
    public void start() {
        running = true;
        if (receiverThread == null) {
            receiverThread = new Thread(this, "ReceiveThread");
            receiverThread.start();
        }
    }

    // Method containing receiver thread logic
    @Override
    public void run() {
        running = true;
        byte networkPacketStream[] = new byte[packetSize];
        try {
            while (running) {
                byte firstByte[] = new byte[1];

                // Wait for start indicator
                while (inRadioStream.available() < packetSize && running) {
                    receiverThread.sleep(sleepTimeMs, sleepTimeNs);
                }

                // Store first byte
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
        finally {
            if(receiverThread.holdsLock(lock))
                lock.unlock();
        }
    }
}
