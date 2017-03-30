package com.twojr.protocol.devices;
import com.twojr.protocol.aps.commands.ApsCommandFactory;
import com.twojr.protocol.network.NetworkPacket;
import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class TwoJRRadioListener{
    private boolean DEBUG = false;

    public TwoJRRadioListener(String portName) {
        this.portName = portName;
        apsCommandFactory = new ApsCommandFactory();
        radioRunning = false;
    }

    private RXTXPort rxtxPort;
    private String portName;
    private InputStream inRadioStream;
    private OutputStream outRadioStream;
    private ApsCommandFactory apsCommandFactory;
    private boolean radioRunning;
    private TransmitterRunnable transmitter;
    private ReceiverRunnable receiver;
    private int packetSize;

    public void setPacketSize(int packetSize) {
        if(receiver == null || transmitter == null)
            return;
        this.packetSize = packetSize;
        receiver.setPacketSize(packetSize);
        transmitter.setPacketSize(packetSize);
    }

    public void setDEBUG(boolean val) {
        DEBUG = val;
    }

    public boolean isRadioRunning() {
            return radioRunning;
    }

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

    public void send(NetworkPacket netPacket) {
        LinkedList<NetworkPacket> netPacketWrapped = new LinkedList<>();
        netPacketWrapped.push(netPacket);
        send(netPacketWrapped);
    }

    public void send(LinkedList<NetworkPacket> networkPackets) {
        transmitter.queuePackets(networkPackets);
        transmitter.start();
    }

    public NetworkPacket tryGetNextPacket() {
        NetworkPacket nextPacket = receiver.getNextPacket();
        if(nextPacket == null)
            System.err.println("Error retrieving incoming packet, no packet available");
        return nextPacket;
    }

    public LinkedList<NetworkPacket> tryGetIncomingPackets() {
        LinkedList<NetworkPacket> packets = receiver.getAllPackets();
        if(packets == null)
            System.err.println("Error retrieving incoming packets, no packets available");
        return packets;
    }

    // Can return null
    public void listen() {
        receiver.start();
    }

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
}
