package com.twojr.protocol.network;

import com.twojr.protocol.Packet;
import com.twojr.toolkit.*;
import com.twojr.toolkit.integer.JUnsignedInteger;

/**
 * Created by Jason on 2/14/2017.
 */
public class NetworkPacket extends Packet implements INetPacket {

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================
    public NetworkPacket() {
        packetSize = MAXPACKETSIZE;
    }

    public NetworkPacket(int sequenceNumber, int networkControl, long macAddress, int commandFrame, byte[] payload) {
        this.sequenceNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT, sequenceNumber);
        this.networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, networkControl);
        this.macAddress = new JAddress(macAddress);
        this.commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT, commandFrame);
        this.packetSize = MAXPACKETSIZE;
        this.setPayload(payload);
    }

    public NetworkPacket(JUnsignedInteger sequenceNumber, JUnsignedInteger networkControl, JAddress macAddress, JUnsignedInteger commandFrame, byte[] payload) {
        this.sequenceNumber = sequenceNumber;
        this.networkControl = networkControl;
        this.macAddress = macAddress;
        this.commandFrame = commandFrame;
        this.packetSize = MAXPACKETSIZE;
        this.setPayload(payload);
    }

    public NetworkPacket(JUnsignedInteger sequenceNumber, JUnsignedInteger networkControl, JAddress macAddress, JUnsignedInteger commandFrame) {
        this.sequenceNumber = sequenceNumber;
        this.networkControl = networkControl;
        this.macAddress = macAddress;
        this.commandFrame = commandFrame;
        this.packetSize = MAXPACKETSIZE;
        byte[] defaultPayload = {0x00};
        this.setPayload(defaultPayload);
    }

    public NetworkPacket(byte[] encodedPacket) {

        byte[] singleTemp = {encodedPacket[networkPacketMasks.SEQUENCE_NUMBER.ordinal()]};
        this.sequenceNumber = new JUnsignedInteger(singleTemp);

        singleTemp[0] = encodedPacket[networkPacketMasks.NETWORK_CONTROL.ordinal()];
        this.networkControl = new JUnsignedInteger(singleTemp);

        byte[] mByteAddr = new byte[]{encodedPacket[2], encodedPacket[3], encodedPacket[4], encodedPacket[5], encodedPacket[6], encodedPacket[7], encodedPacket[8], encodedPacket[9]};
        this.macAddress = new JAddress(mByteAddr);

        singleTemp[0] = encodedPacket[networkPacketByteOffset[networkPacketMasks.COMMAND_FRAME.ordinal()]];
        this.commandFrame = (new JUnsignedInteger(singleTemp));

        this.packetSize = encodedPacket.length;

        int sizeOfPayload = encodedPacket.length - networkPacketByteOffset[networkPacketMasks.PAYLOAD.ordinal()];
        this.payload = new byte[this.packetSize - networkPacketByteOffset[networkPacketMasks.PAYLOAD.ordinal()]];

        if(sizeOfPayload < 0)
            sizeOfPayload = 0;

        for (int i = 0; i < sizeOfPayload; i++)
            this.payload[i] = encodedPacket[i + networkPacketByteOffset[networkPacketMasks.PAYLOAD.ordinal()]];

    }

    //==================================================================================================================
    // Instance variables
    //==================================================================================================================

    private JUnsignedInteger sequenceNumber;
    private JUnsignedInteger networkControl;
    private JAddress macAddress;

    private JUnsignedInteger commandFrame;
    private byte[] payload;
    private int packetSize;

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public JInteger getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(JUnsignedInteger sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public JInteger getNetworkControl() {
        return networkControl;
    }

    public void setNetworkControl(JUnsignedInteger networkControl) {
        this.networkControl = networkControl;
    }

    public JAddress getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(JAddress macAddress) {
        this.macAddress = macAddress;
    }

    public JUnsignedInteger getCommandFrame() {
        return commandFrame;
    }

    public void setCommandFrame(JUnsignedInteger commandFrame) {
        this.commandFrame = commandFrame;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] toByte() {

        byte[] output = new byte[getSize()];
        int index = 0;
        if (sequenceNumber != null)
            for (byte sequence : sequenceNumber.toByte()) {
                output[index++] = sequence;
            }
        if (networkControl != null)
            for (byte control : networkControl.toByte()) {
                output[index++] = control;
            }
        if (macAddress != null)
            for (byte mac : macAddress.toByte()) {
                output[index++] = mac;
            }
        if (commandFrame != null)
            for (byte command : commandFrame.toByte()) {
                output[index++] = command;
            }
        if (payload != null)
            for (byte pay : payload) {
                output[index++] = pay;
            }

        return output;
    }

    @Override
    public int getSize() {
        int size = 0;
        if (sequenceNumber != null)
            size += sequenceNumber.getSize();
        if (networkControl != null)
            size += networkControl.getSize();
        if (macAddress != null)
            size += macAddress.getSize();
        if (commandFrame != null)
            size += commandFrame.getSize();
        if (payload != null)
            size += payload.length;

        return size;
    }

    @Override
    public String print() {
        return print(true);
    }

    public String print(boolean byteFormatted) {
        String output = "";
        output += "Sequence number: " + (sequenceNumber != null ? sequenceNumber.getValue() : null) + "\n";
        output += "Network control: " + (networkControl != null ? networkControlFlags.values()[networkControl.getValue()] : null) + "\n";
        output += "Mac address: " + (macAddress != null ? macAddress.print() : null + "\n");
        output += "Command frame: " + (commandFrame != null ? networkLayerCommands.values()[commandFrame.getValue()] : null) + "\n";
        output += "Payload: \n";
        output += printPayload(byteFormatted);

        return output;
    }


}
