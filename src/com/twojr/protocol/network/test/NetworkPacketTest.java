package com.twojr.protocol.network.test;

import com.twojr.protocol.network.INetPacket;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.*;
import com.twojr.toolkit.integer.JUnsignedInteger;
import org.junit.Test;

import static com.twojr.protocol.network.INetPacket.MAXPACKETSIZE;
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
        assertEquals(MAXPACKETSIZE-INetPacket.networkPacketByteOffset[INetPacket.networkPacketMasks.PAYLOAD.ordinal()],testPacket.getPayload().length);

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
        int index = 0;
        for(byte pByte : payload)
            assertEquals(pByte, netPack.getPayload()[index++]);
    }

    @Test
    public void evaluatePrint() {
        byte[] byteInput = {0x32,(byte) INetPacket.networkControlFlags.ENCRYPTED.ordinal(),0x41, 0x0F, 0x42, (byte) 0xE9, (byte) 0xB2, 0x2D, 0x0E, 0x56, (byte) (INetPacket.networkLayerCommands.ROUTE_RESPONSE.ordinal()&0xff), 0x39,0x42};
        NetworkPacket testPacket = new NetworkPacket(byteInput);
        assertEquals("Sequence number: 50\n" +
                "Network control: ENCRYPTED\n" +
                "Mac address: Address: 86:14:45:-78:-23:66:15:65\n" +
                "Command frame: ROUTE_RESPONSE\n" +
                "Payload: \n" +
                "Payload [0]: 57\n" +
                "Payload [1]: 66\nPayload [2]: 0\n" +
                "Payload [3]: 0\n" +
                "Payload [4]: 0\n" +
                "Payload [5]: 0\n" +
                "Payload [6]: 0\n" +
                "Payload [7]: 0\n" +
                "Payload [8]: 0\n" +
                "Payload [9]: 0\n" +
                "Payload [10]: 0\n" +
                "Payload [11]: 0\n" +
                "Payload [12]: 0\n" +
                "Payload [13]: 0\n" +
                "Payload [14]: 0\n" +
                "Payload [15]: 0\n" +
                "Payload [16]: 0\n" +
                "Payload [17]: 0\n" +
                "Payload [18]: 0\n" +
                "Payload [19]: 0\n" +
                "Payload [20]: 0\n" +
                "Payload [21]: 0\n" +
                "Payload [22]: 0\n" +
                "Payload [23]: 0\n" +
                "Payload [24]: 0\n" +
                "Payload [25]: 0\n" +
                "Payload [26]: 0\n" +
                "Payload [27]: 0\n" +
                "Payload [28]: 0\n" +
                "Payload [29]: 0\n" +
                "Payload [30]: 0\n" +
                "Payload [31]: 0\n" +
                "Payload [32]: 0\n" +
                "Payload [33]: 0\n" +
                "Payload [34]: 0\n" +
                "Payload [35]: 0\n" +
                "Payload [36]: 0\n" +
                "Payload [37]: 0\n" +
                "Payload [38]: 0\n" +
                "Payload [39]: 0\n" +
                "Payload [40]: 0\n" +
                "Payload [41]: 0\n" +
                "Payload [42]: 0\n" +
                "Payload [43]: 0\n" +
                "Payload [44]: 0\n" +
                "Payload [45]: 0\n" +
                "Payload [46]: 0\n" +
                "Payload [47]: 0\n" +
                "Payload [48]: 0\n" +
                "Payload [49]: 0\n" +
                "Payload [50]: 0\n" +
                "Payload [51]: 0\n" +
                "Payload [52]: 0\n" +
                "Payload [53]: 0\n" +
                "Payload [54]: 0\n" +
                "Payload [55]: 0\n" +
                "Payload [56]: 0\n" +
                "Payload [57]: 0\n" +
                "Payload [58]: 0\n" +
                "Payload [59]: 0\n" +
                "Payload [60]: 0\n" +
                "Payload [61]: 0\n" +
                "Payload [62]: 0\n" +
                "Payload [63]: 0\n" +
                "Payload [64]: 0\n" +
                "Payload [65]: 0\n" +
                "Payload [66]: 0\n" +
                "Payload [67]: 0\n" +
                "Payload [68]: 0\n" +
                "Payload [69]: 0\n" +
                "Payload [70]: 0\n" +
                "Payload [71]: 0\n" +
                "Payload [72]: 0\n" +
                "Payload [73]: 0\n" +
                "Payload [74]: 0\n" +
                "Payload [75]: 0\n" +
                "Payload [76]: 0\n" +
                "Payload [77]: 0\n" +
                "Payload [78]: 0\n" +
                "Payload [79]: 0\n" +
                "Payload [80]: 0\n" +
                "Payload [81]: 0\n" +
                "Payload [82]: 0\n" +
                "Payload [83]: 0\n" +
                "Payload [84]: 0\n" +
                "Payload [85]: 0\n" +
                "Payload [86]: 0\n" +
                "Payload [87]: 0\n" +
                "Payload [88]: 0\n" +
                "Payload [89]: 0\n" +
                "Payload [90]: 0\n" +
                "Payload [91]: 0\n" +
                "Payload [92]: 0\n" +
                "Payload [93]: 0\n" +
                "Payload [94]: 0\n" +
                "Payload [95]: 0\n" +
                "Payload [96]: 0\n" +
                "Payload [97]: 0\n" +
                "Payload [98]: 0\n" +
                "Payload [99]: 0\n" +
                "Payload [100]: 0\n" +
                "Payload [101]: 0\n" +
                "Payload [102]: 0\n" +
                "Payload [103]: 0\n" +
                "Payload [104]: 0\n" +
                "Payload [105]: 0\n" +
                "Payload [106]: 0\n" +
                "Payload [107]: 0\n" +
                "Payload [108]: 0\n" +
                "Payload [109]: 0\n" +
                "Payload [110]: 0\n" +
                "Payload [111]: 0\n" +
                "Payload [112]: 0\n" +
                "Payload [113]: 0\n" +
                "Payload [114]: 0\n" +
                "Payload [115]: 0\n",testPacket.print(false));

        assertEquals("Sequence number: 50\n" +
                "Network control: ENCRYPTED\n" +
                "Mac address: Address: 86:14:45:-78:-23:66:15:65\n" +
                "Command frame: ROUTE_RESPONSE\n" +
                "Payload: \n" +
                "Payload [0]: 00111001\n" +
                "Payload [1]: 01000010\nPayload [2]: 00000000\n" +
                "Payload [3]: 00000000\n" +
                "Payload [4]: 00000000\n" +
                "Payload [5]: 00000000\n" +
                "Payload [6]: 00000000\n" +
                "Payload [7]: 00000000\n" +
                "Payload [8]: 00000000\n" +
                "Payload [9]: 00000000\n" +
                "Payload [10]: 00000000\n" +
                "Payload [11]: 00000000\n" +
                "Payload [12]: 00000000\n" +
                "Payload [13]: 00000000\n" +
                "Payload [14]: 00000000\n" +
                "Payload [15]: 00000000\n" +
                "Payload [16]: 00000000\n" +
                "Payload [17]: 00000000\n" +
                "Payload [18]: 00000000\n" +
                "Payload [19]: 00000000\n" +
                "Payload [20]: 00000000\n" +
                "Payload [21]: 00000000\n" +
                "Payload [22]: 00000000\n" +
                "Payload [23]: 00000000\n" +
                "Payload [24]: 00000000\n" +
                "Payload [25]: 00000000\n" +
                "Payload [26]: 00000000\n" +
                "Payload [27]: 00000000\n" +
                "Payload [28]: 00000000\n" +
                "Payload [29]: 00000000\n" +
                "Payload [30]: 00000000\n" +
                "Payload [31]: 00000000\n" +
                "Payload [32]: 00000000\n" +
                "Payload [33]: 00000000\n" +
                "Payload [34]: 00000000\n" +
                "Payload [35]: 00000000\n" +
                "Payload [36]: 00000000\n" +
                "Payload [37]: 00000000\n" +
                "Payload [38]: 00000000\n" +
                "Payload [39]: 00000000\n" +
                "Payload [40]: 00000000\n" +
                "Payload [41]: 00000000\n" +
                "Payload [42]: 00000000\n" +
                "Payload [43]: 00000000\n" +
                "Payload [44]: 00000000\n" +
                "Payload [45]: 00000000\n" +
                "Payload [46]: 00000000\n" +
                "Payload [47]: 00000000\n" +
                "Payload [48]: 00000000\n" +
                "Payload [49]: 00000000\n" +
                "Payload [50]: 00000000\n" +
                "Payload [51]: 00000000\n" +
                "Payload [52]: 00000000\n" +
                "Payload [53]: 00000000\n" +
                "Payload [54]: 00000000\n" +
                "Payload [55]: 00000000\n" +
                "Payload [56]: 00000000\n" +
                "Payload [57]: 00000000\n" +
                "Payload [58]: 00000000\n" +
                "Payload [59]: 00000000\n" +
                "Payload [60]: 00000000\n" +
                "Payload [61]: 00000000\n" +
                "Payload [62]: 00000000\n" +
                "Payload [63]: 00000000\n" +
                "Payload [64]: 00000000\n" +
                "Payload [65]: 00000000\n" +
                "Payload [66]: 00000000\n" +
                "Payload [67]: 00000000\n" +
                "Payload [68]: 00000000\n" +
                "Payload [69]: 00000000\n" +
                "Payload [70]: 00000000\n" +
                "Payload [71]: 00000000\n" +
                "Payload [72]: 00000000\n" +
                "Payload [73]: 00000000\n" +
                "Payload [74]: 00000000\n" +
                "Payload [75]: 00000000\n" +
                "Payload [76]: 00000000\n" +
                "Payload [77]: 00000000\n" +
                "Payload [78]: 00000000\n" +
                "Payload [79]: 00000000\n" +
                "Payload [80]: 00000000\n" +
                "Payload [81]: 00000000\n" +
                "Payload [82]: 00000000\n" +
                "Payload [83]: 00000000\n" +
                "Payload [84]: 00000000\n" +
                "Payload [85]: 00000000\n" +
                "Payload [86]: 00000000\n" +
                "Payload [87]: 00000000\n" +
                "Payload [88]: 00000000\n" +
                "Payload [89]: 00000000\n" +
                "Payload [90]: 00000000\n" +
                "Payload [91]: 00000000\n" +
                "Payload [92]: 00000000\n" +
                "Payload [93]: 00000000\n" +
                "Payload [94]: 00000000\n" +
                "Payload [95]: 00000000\n" +
                "Payload [96]: 00000000\n" +
                "Payload [97]: 00000000\n" +
                "Payload [98]: 00000000\n" +
                "Payload [99]: 00000000\n" +
                "Payload [100]: 00000000\n" +
                "Payload [101]: 00000000\n" +
                "Payload [102]: 00000000\n" +
                "Payload [103]: 00000000\n" +
                "Payload [104]: 00000000\n" +
                "Payload [105]: 00000000\n" +
                "Payload [106]: 00000000\n" +
                "Payload [107]: 00000000\n" +
                "Payload [108]: 00000000\n" +
                "Payload [109]: 00000000\n" +
                "Payload [110]: 00000000\n" +
                "Payload [111]: 00000000\n" +
                "Payload [112]: 00000000\n" +
                "Payload [113]: 00000000\n" +
                "Payload [114]: 00000000\n" +
                "Payload [115]: 00000000\n",testPacket.print());

    }

    @Test
    public void evaluateNullChecks() {
        NetworkPacket newPacket = new NetworkPacket();
        assertEquals("Sequence number: null\n" +
                "Network control: null\n" +
                "Mac address: null\n" +
                "Command frame: null\n" +
                "Payload: \n" +
                "null",newPacket.print());
    }
}
