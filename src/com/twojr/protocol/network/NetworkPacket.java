package com.twojr.protocol.network;

import com.twojr.toolkit.*;
import com.twojr.toolkit.integer.JUnsignedInteger;

/**
 * Created by Jason on 2/14/2017.
 */
public class NetworkPacket implements INetPacket {

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public NetworkPacket() {

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

        byte[] mByteAddr = new byte[]{encodedPacket[2],encodedPacket[3],encodedPacket[4],encodedPacket[5],encodedPacket[6],encodedPacket[7],encodedPacket[8],encodedPacket[9]};
        this.macAddress = new JAddress(mByteAddr);

        singleTemp[0] = encodedPacket[networkPacketByteOffset[networkPacketMasks.COMMAND_FRAME.ordinal()]];
        this.commandFrame = (new JUnsignedInteger(singleTemp));

        int sizeOfPayload = encodedPacket.length- networkPacketByteOffset[networkPacketMasks.PAYLOAD.ordinal()];
        this.payload = new byte[sizeOfPayload];

        for(int i=0; i<sizeOfPayload;i++)
            this.payload[i] = encodedPacket[i+networkPacketByteOffset[networkPacketMasks.PAYLOAD.ordinal()]];

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
        for(byte sequence : sequenceNumber.toByte()) {
            output[index++] = sequence;
        }

        for(byte control : networkControl.toByte()) {
            output[index++] = control;
        }

        for(byte mac : macAddress.toByte()) {
            output[index++] = mac;
        }

        for(byte command : commandFrame.toByte()) {
            output[index++] = command;
        }

        for(byte pay : payload) {
            output[index++] = pay;
        }

        return output;
    }

    @Override
    public int getSize() {
        int size = sequenceNumber.getSize() + networkControl.getSize() + macAddress.getSize() + getCommandFrame().getSize() + payload.length;
        return size;
    }

    @Override
    public String print() {
        return print(true);
    }

    public String print(boolean byteFormatted) {
        String output = "";
        output += "Sequence number: " + sequenceNumber.getValue() + "\n";
        output += "Network control: " + networkControlFlags.values()[networkControl.getValue()] + "\n";
        output += "Mac address: " + macAddress.print();
        output += "Command frame: " + networkLayerCommands.values()[commandFrame.getValue()] + "\n";
        output += "Payload: \n";
        output += printPayload(byteFormatted);

        return output;
    }

    public String printPayload(boolean byteFormatted) {
        int bInd = 0;
        String output = "";

        if(byteFormatted) {
            for(byte b : payload) {

                output += "Payload [" + bInd + "]: " + (b>>7 & 0x01) +
                        (b>>6 & 0x01) + (b>>5 & 0x01) + (b>>4 & 0x01) +
                        (b>>3 & 0x01) + (b>>2 & 0x01) + (b>>1 & 0x01) +
                        (b & 0x01) + "\n";
                bInd++;
            }
        }
        else {
            for(int i=0; i < payload.length; i++) {
                output +="Payload [" + i + "]: " + payload[i] + "\n";
            }
        }

        return output;
    }
}
