package com.twojr.protocol.network.test;

import com.twojr.protocol.network.INetPacket;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.*;
import com.twojr.toolkit.integer.JUnsignedInteger;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Jason on 2/14/2017.
 */
public class NetworkPacketTest {

    @Test
    public void evaluateByteInitializer() {
        //         seqNumber(50)| networkControlFlag (ENCRYPTED)                      | mac address (8byte double) 256093.212b10                    | Command Frame                                                      | payload
        byte[] byteInput = {0x32,(byte) INetPacket.networkControlFlags.ENCRYPTED.ordinal(),0x41, 0x0F, 0x42, (byte) 0xE9, (byte) 0xB2, 0x2D, 0x0E, 0x56, (byte) (INetPacket.networkLayerCommands.ROUTE_RESPONSE.ordinal()&0xff), 0x39,0x42};

        NetworkPacket testPacket = new NetworkPacket(byteInput);

        // Sequence number
        assertEquals(0x32,testPacket.toByte()[0]);
        // Network control flag
        assertEquals(INetPacket.networkControlFlags.ENCRYPTED.ordinal(),testPacket.toByte()[INetPacket.networkControlMask[INetPacket.networkControlFlags.ENCRYPTED.ordinal()]]);
        // Mac address
        assertEquals(0x41,testPacket.toByte()[2]);
        assertEquals(0x0F,testPacket.toByte()[3]);
        assertEquals(0x42,testPacket.toByte()[4]);
        assertEquals((byte) 0xE9,testPacket.toByte()[5]);
        assertEquals((byte) 0xB2,testPacket.toByte()[6]);
        assertEquals(0x2D,testPacket.toByte()[7]);
        assertEquals(0x0E,testPacket.toByte()[8]);
        assertEquals(0x56,testPacket.toByte()[9]);

        // Check data lengths
        assertEquals(1,testPacket.getSequenceNumber().getSize());
        assertEquals(1,testPacket.getNetworkControl().getSize());
        assertEquals(8,testPacket.getMacAddress().getSize());
        assertEquals(1,testPacket.getCommandFrame().getSize());
        assertEquals(2,testPacket.getPayload().length);

        // Check data objects
        assertEquals(0x32, testPacket.getSequenceNumber().getValue());
        assertEquals((byte) INetPacket.networkControlFlags.ENCRYPTED.ordinal(), testPacket.getNetworkControl().getValue());
        assertEquals(0x41,testPacket.getMacAddress().toByte()[0]);
        assertEquals(0x0F,testPacket.getMacAddress().toByte()[1]);
        assertEquals(0x42,testPacket.getMacAddress().toByte()[2]);
        assertEquals((byte)0xE9,testPacket.getMacAddress().toByte()[3]);
        assertEquals((byte)0xB2,testPacket.getMacAddress().toByte()[4]);
        assertEquals((byte)0x2D,testPacket.getMacAddress().toByte()[5]);
        assertEquals((byte)0x0E,testPacket.getMacAddress().toByte()[6]);
        assertEquals((byte)0x56,testPacket.getMacAddress().toByte()[7]);

        assertEquals(INetPacket.networkLayerCommands.ROUTE_RESPONSE.ordinal(),testPacket.getCommandFrame().getValue());
        assertEquals((byte) 0x39,testPacket.getPayload()[0]);
        assertEquals(0x42,testPacket.getPayload()[1]);


    }

    @Test
    public void evaluateToByte() {
        JUnsignedInteger seqNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT,0);
        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, INetPacket.networkControlFlags.ENCRYPTED.ordinal());
        JAddress macAddress = new JAddress(100938);
        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT, INetPacket.networkLayerCommands.REJOIN.ordinal());
        byte payload[] = new byte[2];
        payload[0] = (byte) 0xFF;
        payload[1] = (byte) 0xCA;
        NetworkPacket netPack = new NetworkPacket(seqNumber, networkControl, macAddress, commandFrame, payload);

        // Packet = 0x000047c5250000FFCA
        // 0x00 0x00 0x47c52500 0x00 0xFFCA
        assertEquals((byte) 0x00,netPack.toByte()[0]);
        assertEquals((byte) 0x00,netPack.toByte()[1]);
        assertEquals((byte) 0x4A,netPack.toByte()[2]);
        assertEquals((byte) 0x8A,netPack.toByte()[3]);
        assertEquals((byte) 0x01,netPack.toByte()[4]);
        assertEquals((byte) 0x00,netPack.toByte()[5]);
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
        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, INetPacket.networkControlFlags.ENCRYPTED.ordinal());
        JAddress macAddress = new JAddress(100938);
        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT, INetPacket.networkLayerCommands.REJOIN.ordinal());
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
        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, INetPacket.networkControlFlags.ENCRYPTED.ordinal());
        JAddress macAddress = new JAddress(100938);
        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT, INetPacket.networkLayerCommands.REJOIN.ordinal());
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
        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, INetPacket.networkControlFlags.ENCRYPTED.ordinal());
        JAddress macAddress = new JAddress(100938);
        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT, INetPacket.networkLayerCommands.REJOIN.ordinal());
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
        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, INetPacket.networkControlFlags.ENCRYPTED.ordinal());
        JAddress macAddress = new JAddress(100938);
        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT, INetPacket.networkLayerCommands.REJOIN.ordinal());
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
        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, INetPacket.networkControlFlags.ENCRYPTED.ordinal());
        JAddress macAddress = new JAddress(100938);
        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT, INetPacket.networkLayerCommands.REJOIN.ordinal());
        byte payload[] = new byte[2];
        payload[0] = (byte) 0xFF;
        payload[1] = (byte) 0xCA;
        NetworkPacket netPack = new NetworkPacket();

        assertEquals(null,netPack.getPayload());
        netPack.setPayload(payload);
        assertEquals(payload, netPack.getPayload());
    }

    @Test
    public void evaluatePrint() {
        byte[] byteInput = {0x32,(byte) INetPacket.networkControlFlags.ENCRYPTED.ordinal(),0x41, 0x0F, 0x42, (byte) 0xE9, (byte) 0xB2, 0x2D, 0x0E, 0x56, (byte) (INetPacket.networkLayerCommands.ROUTE_RESPONSE.ordinal()&0xff), 0x39,0x42};
        NetworkPacket testPacket = new NetworkPacket(byteInput);
        assertEquals("Sequence number: 50\n" +
                "Network control: ENCRYPTED\n" +
                "Mac address: Address: 86:14:45:-78:-23:66:15:65:\n" +
                "Command frame: ROUTE_RESPONSE\n" +
                "Payload: \n" +
                "Payload [0]: 57\n" +
                "Payload [1]: 66\n",testPacket.print(false));

        assertEquals("Sequence number: 50\n" +
                "Network control: ENCRYPTED\n" +
                "Mac address: Address: 86:14:45:-78:-23:66:15:65:\n" +
                "Command frame: ROUTE_RESPONSE\n" +
                "Payload: \n" +
                "Payload [0]: 00111001\n" +
                "Payload [1]: 01000010\n",testPacket.print());

    }
}
