package com.twojr.twojr.protocol;

import com.twojr.toolkit.IPrintable;
import com.twojr.toolkit.JInteger;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public abstract class Packet implements IPrintable {

    private JInteger sequenceNumber;
    private byte[] payload;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public Packet(){

    }

    public Packet(JInteger sequenceNumber, byte[] payload) {
        this.sequenceNumber = sequenceNumber;
        this.payload = payload;
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public JInteger getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(JInteger sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
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

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

