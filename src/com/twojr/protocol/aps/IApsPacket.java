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
        COMMAND_FRAME,
        END_POINT,
        ATTRIBUTE_CTRL_LENGTH,
        ATTRIBUTE_CTRL,
        LENGTH_CTRL,
        PAYLOAD
    }

    //------------------------------------------------------------------------------------------------------------------
    // Application Layer commands ------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    enum apsCommands {

        READ((byte)0x00),
        READ_RESPONSE((byte)0x01),
        WRITE((byte)0x02),
        WRITE_RESPONSE((byte)0x03),
        DISCOVER((byte)0x04),
        DISCOVER_RESPONSE((byte)0x05),
        CHECK_IN((byte)0x06),
        CHECK_IN_RESPONSE((byte)0x07),
        WAKE_DEVICE((byte)0x08),
        WAKE_DEVICE_RESPONSE((byte)0x09),
        SLEEP_DEVICE((byte)0x0A),
        SLEEP_DEVICE_RESPONSE((byte)0x0B),
        MANF((byte)0xFF);

        private byte id;

        apsCommands(byte id){
            this.id = id;
        }

        public byte getId(){
            return id;
        }
    }
}

