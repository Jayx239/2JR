package com.twojr.protocol.network;

import com.twojr.toolkit.*;
import com.twojr.toolkit.integer.JUnsignedInteger;

import java.nio.ByteBuffer;
import java.util.Vector;

import static com.twojr.toolkit.DataTypes.DOUBLE_PRECISION;
import static com.twojr.toolkit.DataTypes.UNSIGNED_EIGHT_BIT_INT;

/**
 * Created by Jason on 2/14/2017.
 */
public class NetworkPacket implements IPacket{

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
}
