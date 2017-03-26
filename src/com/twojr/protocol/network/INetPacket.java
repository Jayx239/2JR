package com.twojr.protocol.network;

import com.twojr.protocol.Packet;
import com.twojr.toolkit.IPrintable;

/**
 * Created by Jason on 2/14/2017.
 */
public interface INetPacket extends IPrintable {

    byte[] toByte();
    int getSize();
    //------------------------------------------------------------------------------------------------------------------
    // Enum used for indexing networkMasks and byteOffset arrays

    enum networkPacketMasks {
        SEQUENCE_NUMBER,
        NETWORK_CONTROL,
        MAC_ADDRESS,
        COMMAND_FRAME,
        PAYLOAD
    }

    // The offsets for each segment of the packet
    // Uses networkPacketMasks as indexer
    int[] networkPacketByteOffset = {0,1,2,10,11};

    //------------------------------------------------------------------------------------------------------------------
    // Network control -------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    enum networkControlFlags {
        ENCRYPTED,
        MESSAGE_INTEGRITY_CODE,
        END_DEVICE,
        ROUTER,
        MANUFACTURER_SPECIFIC,
        UNUSED_1,
        UNUSED_2,
        UNUSED_3
    }

    // Masks for setting control bits, uses networkControlFlags as indexer
    int[] networkControlMask = {0x01, 0x02,0x04,0x08,0x10,0x20,0x40,0x80};

    //------------------------------------------------------------------------------------------------------------------
    // Network Layer commands ------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
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
