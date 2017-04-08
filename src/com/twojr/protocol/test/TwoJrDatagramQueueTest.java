package com.twojr.protocol.test;

import com.digi.xbee.api.models.XBee64BitAddress;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.TwoJrDatagramQueue;
import com.twojr.protocol.network.INetPacket;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.JAddress;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Jason on 4/7/2017.
 */
public class TwoJrDatagramQueueTest {

    @Test
    public void datagramQueueTest() {
        TwoJrDatagramQueue twoJrDatagramQueue = new TwoJrDatagramQueue();

        for(int i=0; i<50; i++) {
            twoJrDatagramQueue.insert(new TwoJrDataGram(new XBee64BitAddress(new JAddress("123:32:44:32:53:76:45:32").toByte()),new NetworkPacket(i, INetPacket.networkControlFlags.ROUTER.ordinal(),38127371,INetPacket.networkLayerCommands.ROUTE_RESPONSE.ordinal(),new byte[]{0x00,0x32,0x32,0x43,0x12})));
        }

        assertEquals(50,twoJrDatagramQueue.getSize());

        for(int i=0; i<50; i++) {
            assertEquals(i,twoJrDatagramQueue.getNext().getPacket().getSequenceNumber().getValue());
        }

        assertEquals(0,twoJrDatagramQueue.getSize());

        for(int i=0; i<50; i++) {
            twoJrDatagramQueue.insertHead(new TwoJrDataGram(new XBee64BitAddress(new JAddress("123:32:44:32:53:76:45:32").toByte()),new NetworkPacket(i, INetPacket.networkControlFlags.ROUTER.ordinal(),38127371,INetPacket.networkLayerCommands.ROUTE_RESPONSE.ordinal(),new byte[]{0x00,0x32,0x32,0x43,0x12})));
        }

        assertEquals(50,twoJrDatagramQueue.getSize());

        for (int i=49; i>=0; i--) {
            assertEquals(i,twoJrDatagramQueue.getNext().getPacket().getSequenceNumber().getValue());
        }

        assertEquals(0,twoJrDatagramQueue.getSize());

        twoJrDatagramQueue.insert(new TwoJrDataGram(new XBee64BitAddress(new JAddress("123:32:44:32:53:76:45:32").toByte()),new NetworkPacket(10, INetPacket.networkControlFlags.ROUTER.ordinal(),38127371,INetPacket.networkLayerCommands.ROUTE_RESPONSE.ordinal(),new byte[]{0x00,0x32,0x32,0x43,0x12})));
        twoJrDatagramQueue.insert(new TwoJrDataGram(new XBee64BitAddress(new JAddress("123:32:44:32:53:76:45:32").toByte()),new NetworkPacket(5, INetPacket.networkControlFlags.ROUTER.ordinal(),38127371,INetPacket.networkLayerCommands.ROUTE_RESPONSE.ordinal(),new byte[]{0x00,0x32,0x32,0x43,0x12})));

        assertEquals(2,twoJrDatagramQueue.getSize());
        assertEquals(10,twoJrDatagramQueue.peekNext().getPacket().getSequenceNumber().getValue());
        assertEquals(2,twoJrDatagramQueue.getSize());
        assertEquals(5,twoJrDatagramQueue.peekLast().getPacket().getSequenceNumber().getValue());
        assertEquals(2,twoJrDatagramQueue.getSize());
    }
}
