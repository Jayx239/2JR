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
public class TwoJRRadioListenerSenderTest {

    public static void main(String[] args) {
        TwoJRRadioListener tjrRadio = new TwoJRRadioListener("COM3");
        //tjrRadio.setDEBUG(true);
        tjrRadio.start();

        byte[] byteInput = {0x32, (byte) INetPacket.networkControlFlags.ENCRYPTED.ordinal(), 0x41, 0x0F, 0x42,
                (byte) 0xE9, (byte) 0xB2, 0x2D, 0x0E, 0x56,
                (byte) (INetPacket.networkLayerCommands.ROUTE_RESPONSE.ordinal() & 0xff), 0x39, 0x42};
        NetworkPacket testPacket = new NetworkPacket(byteInput);
        LinkedList<NetworkPacket> packets = new LinkedList<NetworkPacket>();
        packets.push(testPacket);
        tjrRadio.send(packets);

        System.out.print(testPacket.print());
        tjrRadio.close();
        System.out.print("Done");
    }
}
