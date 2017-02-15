package com.twojr.protocol.network;

/**
 * Created by Jason on 2/14/2017.
 */
public interface IPacket {

    byte[] toByte();

    // Enum used for indexing networkMasks and byteOffset arrays
    enum networkPacketMasks {
        SEQUENCE_NUMBER,
        NETWORK_CONTROL,
        MAC_ADDRESS,
        COMMAND_FRAME,
        PAYLOAD_ONE_BYTE,
        PAYLOAD_TWO_BYTE,
        PAYLOAD_FOUR_BYTE,
    }

    // Used for accessing segments of the packet.
    // Uses networkPacketMasks for indexing
    float[] networkMasks = {0xff,0xff >>> 8,0xffffffff >>> 16, 0xff >>> 80,
            0xffff >>> 88, 0xffffffff >>> 88};

    // The offsets for each segment of the packet
    // Uses networkPacketMasks as indexer
    int[] byteOffset = {0,8,16,80,88,88};

    // Network control
    enum networkControlFlags {
        ENCRYPTED,
        MESSAGE_INTEGRITY_CODE,
        END_DEVICE,
        ROUTER,
        MANUFACTURER_SPECIFIC;
    }

    // Masks for setting control bits, uses networkControlFlags as indexer
    int[] networkControlMask = {0x01, 0x02,0x04,0x08,0x10};

    // Network Layer commands
    enum networkLayerCommands {
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
