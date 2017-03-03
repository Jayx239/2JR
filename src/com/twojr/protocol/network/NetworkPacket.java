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

    public NetworkPacket(JUnsignedInteger sequenceNumber, JUnsignedInteger networkControl, JDouble macAddress, JUnsignedInteger commandFrame, byte[] payload) {
        this.sequenceNumber = sequenceNumber;
        this.networkControl = networkControl;
        this.macAddress = macAddress;
        this.commandFrame = commandFrame;
        this.payload = payload;
    }

    public NetworkPacket(JUnsignedInteger sequenceNumber, JUnsignedInteger networkControl, JDouble macAddress, JUnsignedInteger commandFrame) {
        this.sequenceNumber = sequenceNumber;
        this.networkControl = networkControl;
        this.macAddress = macAddress;
        this.commandFrame = commandFrame;
    }

    public NetworkPacket(byte[] encodedPacket) {

        sequenceNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT, Byte.toUnsignedInt(encodedPacket[0]));
        networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT,Byte.toUnsignedInt(encodedPacket[1]));

        byte[] mByteAddr = new byte[]{encodedPacket[2],encodedPacket[3],encodedPacket[4],encodedPacket[5],encodedPacket[6],encodedPacket[7],encodedPacket[8],encodedPacket[9]};
        macAddress = new JDouble(ByteBuffer.wrap(mByteAddr).getDouble());

        int sizeOfPayload = networkControl.getValue();

        //for(int i=9; i<)
    }

    //==================================================================================================================
    // Instance variables
    //==================================================================================================================

    private JUnsignedInteger sequenceNumber;
    private JUnsignedInteger networkControl;
    private JDouble macAddress;

    private JUnsignedInteger commandFrame;
    byte[] payload;

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

    public JDouble getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(JDouble macAddress) {
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

    public byte[] toByte() {

        Vector<Byte> output = new Vector<Byte>();

        for(byte sequence : sequenceNumber.toByte()) {
            output.add(sequence);
        }

        for(byte control : networkControl.toByte()) {
            output.add(control);
        }

        for(byte mac : macAddress.toByte()) {
            output.add(mac);
        }

        for(byte command : commandFrame.toByte()) {
            output.add(command);
        }

        for(byte pay : payload) {
            output.add(pay);
        }

        byte[] outputArray = new byte[output.size()];
        for(int i=0; i<outputArray.length; i++) {
            outputArray[i] = output.elementAt(i);
        }

        return outputArray;
    }


}
