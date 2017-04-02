package com.twojr.protocol.devices.test;

import com.twojr.protocol.devices.TransmitterRunnable;
import com.twojr.protocol.network.NetworkPacket;
import gnu.io.PortInUseException;
import gnu.io.RXTXPort;
import org.junit.Test;
import org.junit.Assert.*;

import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Jason on 3/30/2017.
 */
public class TransmitterRunnableTest {
    @Test
    public void evaluateQueuePackets() {
        try {
            RXTXPort port = new RXTXPort("COM4");
            TransmitterRunnable transmitter = new TransmitterRunnable(port.getOutputStream());
            LinkedList<NetworkPacket> netPackets1 = new LinkedList<>();
            byte data1[] = {0x01, 0x02};
            for (int i = 1; i < 4; i++) {
                netPackets1.add(new NetworkPacket(i, 2, 19838273, 3, data1));
            }

            transmitter.queuePackets(netPackets1);
            LinkedList<NetworkPacket> netPackets2 = new LinkedList<>();
            for (int i = 10; i < 15; i++) {
                netPackets2.add(new NetworkPacket(i, 2, 19838273, 3, data1));
            }

            transmitter.queuePackets(netPackets2);
            LinkedList<NetworkPacket> queue = transmitter.getQueuedPackets();
            int expectedSequenceNumber = 0;

            for (NetworkPacket packet : queue) {
                expectedSequenceNumber = expectedSequenceNumber == 3 ? 10 : expectedSequenceNumber+1;
                assertEquals(expectedSequenceNumber,packet.getSequenceNumber().getValue());
                System.err.println(packet.print());
            }


        } catch (PortInUseException ex) {
            System.err.println("Error opening COM4");
            ex.printStackTrace();
        }
    }
}
