package com.twojr.protocol.aps;

/**
 * Created by rcunni002c on 4/4/2017.
 */
public interface IApsPacket {

    // Network Packet Size Limits
    int MAXPACKETSIZE = 110;    // Maximum packet size supported by IEEE 802.15.4 protocol
    int MINPACKETSIZE = 3;     // Minimum packet size for 2JR 11 Byte Network header + 6 Byte Application header

    //------------------------------------------------------------------------------------------------------------------
    // Enum used for indexing networkMasks and byteOffset arrays
    //------------------------------------------------------------------------------------------------------------------
    enum applicationPacketMasks {
        SEQUENCE_NUMBER,
        NETWORK_CONTROL,
        MAC_ADDRESS,
        COMMAND_FRAME,
        PAYLOAD
    }

    //------------------------------------------------------------------------------------------------------------------
    // Application Layer commands ------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    enum apsCommands {
        REJOIN,
        REJOIN_RESPONSE,
        LEAVE_NETWORK,
        FACTORY_RESET,
        ROUTE_REQUEST,
        ROUTE_RESPONSE,
        ROUTE_TABLE_REQUEST,
        ROUTE_TABLE_RESPONSE,
        ADDRESS_REQUEST,
        ADDRESS_RESPONSE
    }
}

