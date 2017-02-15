package com.twojr.protocol.network;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.twojr.toolkit.IPrintable;
import com.twojr.toolkit.JDouble;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;
import com.twojr.toolkit.integer.JSignedInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;

import java.util.Vector;

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
