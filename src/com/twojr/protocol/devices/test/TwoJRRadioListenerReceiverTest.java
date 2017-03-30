package com.twojr.protocol.devices.test;

import com.twojr.protocol.devices.TwoJRRadioListener;
import com.twojr.protocol.network.INetPacket;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.JAddress;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.integer.JUnsignedInteger;

import java.util.LinkedList;

/**
 * Created by Jason on 3/27/2017.
 */
public class TwoJRRadioListenerReceiverTest {

    public static void main(String args[]) {
        TwoJRRadioListener tjrRadio = new TwoJRRadioListener("COM4");
        tjrRadio.setDEBUG(true);
        tjrRadio.start();

        byte[] byteInput = {0x32, (byte) INetPacket.networkControlFlags.ENCRYPTED.ordinal(), 0x41, 0x0F, 0x42, (byte) 0xE9, (byte) 0xB2, 0x2D, 0x0E, 0x56, (byte) (INetPacket.networkLayerCommands.ROUTE_RESPONSE.ordinal() & 0xff), 0x39, 0x42};
        NetworkPacket testPacket;

        tjrRadio.listen();
        while ((testPacket = tjrRadio.tryGetNextPacket()) == null) ;

        System.out.println(testPacket.print());
        NetworkPacket responsePacket = new NetworkPacket(new JUnsignedInteger(JDataSizes.EIGHT_BIT, 0), new JUnsignedInteger(JDataSizes.EIGHT_BIT, 3), new JAddress(4712632), new JUnsignedInteger(JDataSizes.EIGHT_BIT, 8), new JUnsignedInteger(JDataSizes.EIGHT_BIT, 0x01).toByte());
        LinkedList<NetworkPacket> networkPackets = new LinkedList<NetworkPacket>();
        networkPackets.push(responsePacket);
        tjrRadio.send(networkPackets);
        tjrRadio.close();
        System.out.print("Done");
    }
}
