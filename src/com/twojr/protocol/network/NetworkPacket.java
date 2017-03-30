package com.twojr.protocol.network;

import com.twojr.toolkit.*;
import com.twojr.toolkit.integer.JUnsignedInteger;

/**
 * Created by Jason on 2/14/2017.
 */
public class NetworkPacket extends JData implements INetPacket {

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================
    public NetworkPacket() {

    }

    public NetworkPacket(int sequenceNumber, int networkControl, long macAddress, int commandFrame, byte[] payload) {
        this.sequenceNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT, sequenceNumber);
        this.networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, networkControl);
        this.macAddress = new JAddress(macAddress);
        this.commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT, commandFrame);
        this.payload = payload;
    }

    public NetworkPacket(JUnsignedInteger sequenceNumber, JUnsignedInteger networkControl, JAddress macAddress, JUnsignedInteger commandFrame, byte[] payload) {
        this.sequenceNumber = sequenceNumber;
        this.networkControl = networkControl;
        this.macAddress = macAddress;
        this.commandFrame = commandFrame;
        this.payload = payload;
    }

    public NetworkPacket(JUnsignedInteger sequenceNumber, JUnsignedInteger networkControl, JAddress macAddress, JUnsignedInteger commandFrame) {
        this.sequenceNumber = sequenceNumber;
        this.networkControl = networkControl;
        this.macAddress = macAddress;
        this.commandFrame = commandFrame;
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

        int sizeOfPayload = encodedPacket.length - networkPacketByteOffset[networkPacketMasks.PAYLOAD.ordinal()];
        this.payload = new byte[sizeOfPayload];

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
    public byte[] compress() {
        return new byte[0];
    }

    @Override
    public int getSavings() {
        return 0;
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

    public String printPayload(boolean byteFormatted) {
        int bInd = 0;
        String output = "";
        if (payload == null)
            return null;
        if (byteFormatted) {
            for (byte b : payload) {

                output += "Payload [" + bInd + "]: " + (b >> 7 & 0x01) +
                        (b >> 6 & 0x01) + (b >> 5 & 0x01) + (b >> 4 & 0x01) +
                        (b >> 3 & 0x01) + (b >> 2 & 0x01) + (b >> 1 & 0x01) +
                        (b & 0x01) + "\n";
                bInd++;
            }
        } else {
            for (int i = 0; i < payload.length; i++) {
                output += "Payload [" + i + "]: " + payload[i] + "\n";
            }
        }

        return output;
    }
}
