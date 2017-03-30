package com.twojr.protocol.devices.test;

import com.twojr.protocol.devices.TwoJRRadioListener;
import com.twojr.protocol.network.INetPacket;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.*;
import com.twojr.toolkit.integer.JUnsignedInteger;
import org.junit.Test;

import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Jason on 3/29/2017.
 */
public class TwoJRRadioListenerTest {

    @Test
    public void singlePacketPingPongTest() {
        TwoJRRadioListener sender = new TwoJRRadioListener("COM3");
        TwoJRRadioListener receiver = new TwoJRRadioListener("COM4");

        sender.start();
        receiver.start();

        NetworkPacket netPacket = new NetworkPacket(new JUnsignedInteger(JDataSizes.EIGHT_BIT, 102), new JUnsignedInteger(JDataSizes.EIGHT_BIT, 2), new JAddress(2839283), new JUnsignedInteger(JDataSizes.EIGHT_BIT, 2));
        sender.send(netPacket);

        receiver.listen();
        sender.close();
        receiver.close();
    }

    @Test
    public void queuePacketPingPongTest() {
        TwoJRRadioListener sender = new TwoJRRadioListener("COM3");
        TwoJRRadioListener receiver = new TwoJRRadioListener("COM4");
        sender.start();
        receiver.start();
        receiver.setPacketSize(32);
        LinkedList<NetworkPacket> networkPackets = new LinkedList<>();
        NetworkPacket testArray[] = new NetworkPacket[3];
        for (int i = 1; i < 4; i++) {
            JString nextString = new JString(JDataSizes.SIXTY_FOUR_BIT, "hello there packet #" + Integer.toString(i));
            networkPackets.push(new NetworkPacket(i, INetPacket.networkControlFlags.ROUTER.ordinal(), 123, INetPacket.networkLayerCommands.ADDRESS_REQUEST.ordinal(), nextString.toByte()));
            testArray[i - 1] = new NetworkPacket(i, INetPacket.networkControlFlags.ROUTER.ordinal(), 123, INetPacket.networkLayerCommands.ADDRESS_REQUEST.ordinal(), nextString.toByte());
        }

        sender.send(networkPackets);
        receiver.listen();

        NetworkPacket nextPacket;
        for (int i = 0; i < 3; i++) {
            while ((nextPacket = receiver.tryGetNextPacket()) == null) ;
            assertEquals(testArray[i].print(), nextPacket.print());
        }

        sender.close();
        receiver.close();
    }
}
