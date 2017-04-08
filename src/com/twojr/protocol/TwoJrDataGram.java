package com.twojr.protocol;

import com.digi.xbee.api.models.XBee16BitAddress;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.JDataSizes;

/**
 * Created by rcunni002c on 4/4/2017.
 */
public class TwoJrDataGram {

    private XBee64BitAddress destinationLong;
    private XBee16BitAddress destinationShort;
    private NetworkPacket packet;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public TwoJrDataGram(XBee64BitAddress destinationLong, XBee16BitAddress desintationShort, NetworkPacket packet) {
        this.destinationLong = destinationLong;
        this.destinationShort = desintationShort;
        this.packet = packet;
    }

    public TwoJrDataGram(XBee64BitAddress destinationLong, NetworkPacket packet) {
        this.destinationLong = destinationLong;
        this.packet = packet;
    }

    // Method assumes that the datagram only contains the long destination address and encoded network packet
    public TwoJrDataGram(byte[] encodedDatagram) {

        // Check to make sure the encoded datagram is not null and atleast 64 bits
        if(encodedDatagram == null || encodedDatagram.length < JDataSizes.SIXTY_FOUR_BIT.ordinal()) {
            // throw exception
            return;
        }

        // Allocate byte array for decoding address
        byte[] byteAddress = new byte[JDataSizes.SIXTY_FOUR_BIT.ordinal()];
        for(int i = 0; i< JDataSizes.SIXTY_FOUR_BIT.ordinal(); i++) {
            byteAddress[i] = encodedDatagram[i];
        }

        // Set long address
        this.destinationLong = new XBee64BitAddress(byteAddress);

        // Decode network packet
        // Allocate network packet
        byte[] byteNetworkPacket = new byte[encodedDatagram.length-JDataSizes.SIXTY_FOUR_BIT.ordinal()];

        // Strip encoded network packet
        int networkByteindex = 0;
        for(int i=JDataSizes.SIXTY_FOUR_BIT.ordinal(); i < encodedDatagram.length; i++) {
            byteNetworkPacket[networkByteindex++] = encodedDatagram[i];
        }

        // Initialize packet with byte encoded packet
        this.packet = new NetworkPacket(byteNetworkPacket);


    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public XBee64BitAddress getDestinationLong() {
        return destinationLong;
    }

    public void setDestinationLong(XBee64BitAddress destinationLong) {
        this.destinationLong = destinationLong;
    }

    public XBee16BitAddress getDestinationShort() {
        return destinationShort;
    }

    public void setDestinationShort(XBee16BitAddress destinationShort) {
        this.destinationShort = destinationShort;
    }

    public NetworkPacket getPacket() {
        return packet;
    }

    public void setPacket(NetworkPacket packet) {
        this.packet = packet;
    }


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================
    public byte[] toByte() {

        // Allocate encoded datagram
        byte[] encodedDatagram = new byte[JDataSizes.SIXTY_FOUR_BIT.ordinal() + packet.getSize()];

        // Encode address
        byte[] longAddressEncoded = destinationLong.getValue();
        for(int i=0; i < longAddressEncoded.length; i++) {
            encodedDatagram[i] = longAddressEncoded[i];
        }

        // Encode packet
        byte[] encodedNetworkPacket = packet.toByte();
        int index = 0;
        for(int i=JDataSizes.SIXTY_FOUR_BIT.ordinal(); i < encodedDatagram.length; i++) {
            encodedDatagram[i] = encodedNetworkPacket[index++];
        }

        // return the encoded datagram
        return encodedDatagram;
    }
    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/


