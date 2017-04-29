package com.twojr.protocol;

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
    public abstract  byte[] toByte();

    public abstract int getSize();

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

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

