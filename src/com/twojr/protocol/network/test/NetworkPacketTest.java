package com.twojr.protocol.network.test;

import com.twojr.protocol.network.IPacket;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.JDouble;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;
import com.twojr.toolkit.integer.JSignedInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Jason on 2/14/2017.
 */
public class NetworkPacketTest {

    @Test
    public void evaluateToByte() {
        JUnsignedInteger seqNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT,1);
        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHTY_BIT, IPacket.networkControlFlags.ENCRYPTED.ordinal());
        JDouble macAddress = new JDouble(0x9A42F8);
        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHTY_BIT,IPacket.networkLayerCommands.REJOIN.ordinal());
        byte payload[] = new byte[2];
        payload[0] = (byte) 0xFF;
        payload[1] = (byte) 0xCA;
        NetworkPacket netPack = new NetworkPacket(seqNumber, networkControl, macAddress, commandFrame, payload);

        // Packet = 0x0101729A42F800FFCA
        // 0x01 0x01 0x729A4f8 0x00 0xFFCA
        assertEquals(0x01,netPack.toByte()[0]);
        assertEquals(0x00,netPack.toByte()[1]);
        //assertEquals(0x72,netPack.toByte()[2]);
        //assertEquals(0x9A,netPack.toByte()[3]);
        assertEquals(0x42,netPack.toByte()[4]);
        assertEquals(0xF8,netPack.toByte()[5]);
        assertEquals(0x00, netPack.toByte()[6]);
        assertEquals(0xFF, netPack.toByte()[7]);
        assertEquals(0xCA, netPack.toByte()[8]);
    }
}
