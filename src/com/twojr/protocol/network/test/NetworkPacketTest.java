package com.twojr.protocol.network.test;

import com.twojr.protocol.network.IPacket;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.JDouble;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;
import com.twojr.toolkit.integer.JSignedInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Jason on 2/14/2017.
 */
public class NetworkPacketTest {

    @Test
    public void evaluateToByte() {
        JUnsignedInteger seqNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT,0);
        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, IPacket.networkControlFlags.ENCRYPTED.ordinal());
        JDouble macAddress = new JDouble(100938);
        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT,IPacket.networkLayerCommands.REJOIN.ordinal());
        byte payload[] = new byte[2];
        payload[0] = (byte) 0xFF;
        payload[1] = (byte) 0xCA;
        NetworkPacket netPack = new NetworkPacket(seqNumber, networkControl, macAddress, commandFrame, payload);

        // Packet = 0x000047c5250000FFCA
        // 0x00 0x00 0x47c52500 0x00 0xFFCA
        assertEquals((byte) 0x00,netPack.toByte()[0]);
        assertEquals((byte) 0x00,netPack.toByte()[1]);
        assertEquals((byte) 0x40,netPack.toByte()[2]);
        assertEquals((byte) 0xF8,netPack.toByte()[3]);
        assertEquals((byte) 0xA4,netPack.toByte()[4]);
        assertEquals((byte) 0xA0,netPack.toByte()[5]);
        assertEquals((byte) 0x00, netPack.toByte()[6]);
        assertEquals((byte) 0x00, netPack.toByte()[7]);
        assertEquals((byte) 0x00, netPack.toByte()[8]);
        assertEquals((byte) 0x00, netPack.toByte()[9]);
        assertEquals((byte) 0x00, netPack.toByte()[10]);
        assertEquals((byte) 0xFF, netPack.toByte()[11]);
        assertEquals((byte) 0xCA, netPack.toByte()[12]);
    }

    @Test
    public void evaluateSetSequenceNumber() {
        JUnsignedInteger seqNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT,5);
        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, IPacket.networkControlFlags.ENCRYPTED.ordinal());
        JDouble macAddress = new JDouble(100938);
        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT,IPacket.networkLayerCommands.REJOIN.ordinal());
        byte payload[] = new byte[2];
        payload[0] = (byte) 0xFF;
        payload[1] = (byte) 0xCA;
        NetworkPacket netPack = new NetworkPacket();

        assertEquals(null,netPack.getSequenceNumber());
        netPack.setSequenceNumber(seqNumber);
        assertEquals(seqNumber, netPack.getSequenceNumber());
    }

    @Test
    public void evaluateSetNetworkControl() {
        JUnsignedInteger seqNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT,5);
        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, IPacket.networkControlFlags.ENCRYPTED.ordinal());
        JDouble macAddress = new JDouble(100938);
        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT,IPacket.networkLayerCommands.REJOIN.ordinal());
        byte payload[] = new byte[2];
        payload[0] = (byte) 0xFF;
        payload[1] = (byte) 0xCA;
        NetworkPacket netPack = new NetworkPacket();

        assertEquals(null,netPack.getNetworkControl());
        netPack.setNetworkControl(networkControl);
        assertEquals(networkControl, netPack.getNetworkControl());
    }

    @Test
    public void evaluateSetMacAddress() {
        JUnsignedInteger seqNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT,5);
        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, IPacket.networkControlFlags.ENCRYPTED.ordinal());
        JDouble macAddress = new JDouble(100938);
        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT,IPacket.networkLayerCommands.REJOIN.ordinal());
        byte payload[] = new byte[2];
        payload[0] = (byte) 0xFF;
        payload[1] = (byte) 0xCA;
        NetworkPacket netPack = new NetworkPacket();

        assertEquals(null,netPack.getMacAddress());
        netPack.setMacAddress(macAddress);
        assertEquals(macAddress, netPack.getMacAddress());
    }

    @Test
    public void evaluateSetCommandFrame() {
        JUnsignedInteger seqNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT,5);
        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, IPacket.networkControlFlags.ENCRYPTED.ordinal());
        JDouble macAddress = new JDouble(100938);
        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT,IPacket.networkLayerCommands.REJOIN.ordinal());
        byte payload[] = new byte[2];
        payload[0] = (byte) 0xFF;
        payload[1] = (byte) 0xCA;
        NetworkPacket netPack = new NetworkPacket();

        assertEquals(null,netPack.getCommandFrame());
        netPack.setCommandFrame(commandFrame);
        assertEquals(commandFrame, netPack.getCommandFrame());
    }

    @Test
    public void evaluateSetPayload() {
        JUnsignedInteger seqNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT,5);
        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, IPacket.networkControlFlags.ENCRYPTED.ordinal());
        JDouble macAddress = new JDouble(100938);
        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT,IPacket.networkLayerCommands.REJOIN.ordinal());
        byte payload[] = new byte[2];
        payload[0] = (byte) 0xFF;
        payload[1] = (byte) 0xCA;
        NetworkPacket netPack = new NetworkPacket();

        assertEquals(null,netPack.getPayload());
        netPack.setPayload(payload);
        assertEquals(payload, netPack.getPayload());
    }




}
