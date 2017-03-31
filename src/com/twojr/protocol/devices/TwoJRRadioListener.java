package com.twojr.protocol.devices;
import com.twojr.protocol.network.NetworkPacket;
import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

import static com.twojr.protocol.network.INetPacket.*;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class TwoJRRadioListener{
    private boolean DEBUG = false;

    public TwoJRRadioListener(String portName) {
        this.portName = portName;
        this.radioRunning = false;
        this.packetSize = MAXPACKETSIZE;
    }

    private RXTXPort rxtxPort;
    private String portName;
    private InputStream inRadioStream;
    private OutputStream outRadioStream;
    private boolean radioRunning;
    private TransmitterRunnable transmitter;
    private ReceiverRunnable receiver;
    private int packetSize;

    public void setPacketSize(int packetSize) {
        if(receiver == null || transmitter == null)
            return;
        if(packetSize <= MAXPACKETSIZE && packetSize >= MINPACKETSIZE) {
            this.packetSize = packetSize;
            receiver.setPacketSize(packetSize);
            transmitter.setPacketSize(packetSize);
        }
    }

    public void setDEBUG(boolean val) {
        DEBUG = val;
    }

    public boolean isRadioRunning() {
            return radioRunning;
    }

    // Start method, Opens serial port, initializes transmitter and receiver threads
    public void start() {
        try{
            // Open rxtx port and initialize io streams
            rxtxPort = new RXTXPort(portName);
            inRadioStream = rxtxPort.getInputStream();
            outRadioStream = rxtxPort.getOutputStream();
            radioRunning = true;
            transmitter = new TransmitterRunnable(outRadioStream);
            receiver = new ReceiverRunnable(inRadioStream);
        }catch (PortInUseException ex) {
            System.err.println("Radio listener port in use\nPort name: " + portName );
            radioRunning = false;
            if(DEBUG)
                ex.printStackTrace();
        }
    }

    // Send method for transmitting single network packet
    public void send(NetworkPacket netPacket) {
        LinkedList<NetworkPacket> netPacketWrapped = new LinkedList<>();
        netPacketWrapped.push(netPacket);
        send(netPacketWrapped);
    }

    // Send method for transmitting stream of network packets
    public void send(LinkedList<NetworkPacket> networkPackets) {
        transmitter.queuePackets(networkPackets);
        transmitter.start();
    }

    // Method to start the radio listener
    public void listen() {
        receiver.start();
    }

    // Method for closing radioListener, stops receiver and transmitter threads
    public void close() {
        try {
            transmitter.stop();
            receiver.stop();
            inRadioStream.close();
            outRadioStream.close();
            rxtxPort.close();
            radioRunning = false;

        } catch(IOException ex) {
            System.err.println("Radio Listener Exception on close\nPort name: ");
            radioRunning = false;
            if(DEBUG)
                ex.printStackTrace();
        }
    }

    // Method for retrieving next received packet from receiver
    // May return null
    public NetworkPacket tryGetNextPacket() {
        NetworkPacket nextPacket = receiver.getNextPacket();
        if(nextPacket == null)
            if(DEBUG)
                System.err.println("Error retrieving incoming packet, no packet available");
        return nextPacket;
    }

    // Method for trying to retrieve all packets from radio receiver
    // May return null
    public LinkedList<NetworkPacket> tryGetIncomingPackets() {
        LinkedList<NetworkPacket> packets = receiver.getAllPackets();
        if(packets == null)
            if(DEBUG)
                System.err.println("Error retrieving incoming packets, no packets available");
        return packets;
    }

}
