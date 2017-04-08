package com.twojr.protocol.test;

import com.digi.xbee.api.models.XBee64BitAddress;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.network.INetPacket;
import com.twojr.protocol.network.NetworkPacket;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Jason on 4/7/2017.
 */
public class TwoJrDatagramTest {

    @Test
    public void evaluateTwoJrDataGramByteInitializer() {

        TwoJrDataGram dataGram = new TwoJrDataGram(new XBee64BitAddress(new byte[8]),new NetworkPacket(3, INetPacket.networkControlFlags.ENCRYPTED.ordinal(),381247123,INetPacket.networkLayerCommands.ADDRESS_REQUEST.ordinal(),new byte[]{0x02,0x3,0x12,0x5,0x23}));

        TwoJrDataGram decodedDataGram = new TwoJrDataGram(dataGram.toByte());

        byte[] expectedDatagramEncoded= dataGram.toByte();
        byte[] reEncodedDatagram = decodedDataGram.toByte();
        int expectedDatagramLen = expectedDatagramEncoded.length;

        assertEquals(expectedDatagramLen, reEncodedDatagram.length);

        for(int i=0; i< expectedDatagramLen; i++)
            assertEquals(dataGram.toByte()[i], decodedDataGram.toByte()[i]);
    }
}
