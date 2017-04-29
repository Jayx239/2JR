package com.twojr.protocol.aps;
import com.twojr.protocol.devices.TwoJrPacketHandler;
import com.twojr.protocol.Packet;

import static com.twojr.protocol.aps.IApsPacket.apsCommands.*;


/**
 * Created by rcunni202 on 4/29/2017.
 */
public class TwoJRAPSPacketHandler extends TwoJrPacketHandler {

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public TwoJRAPSPacketHandler(){}

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================
    @Override
    public ApsPacket handle(Packet packet){

        ApsPacket apsPacket = (ApsPacket) packet;

        switch(apsPacket.getCommandFrame()) {

            case READ:

                apsPacket.setCommandFrame(READ_RESPONSE);
                apsPacket.setPayload(new byte[0]);
                break;

            case WRITE:

                apsPacket.setCommandFrame(WRITE_RESPONSE);
                break;

            case DISCOVER:

                apsPacket.setCommandFrame(DISCOVER_RESPONSE);
                break;

            case CHECK_IN:

                apsPacket.setCommandFrame(CHECK_IN_RESPONSE);
                apsPacket.setPayload(new byte[0]);
                break;

            case WAKE_DEVICE:

                apsPacket.setCommandFrame(WAKE_DEVICE_RESPONSE);
                apsPacket.setPayload(new byte[0]);
                break;

            case SLEEP_DEVICE:
                apsPacket.setCommandFrame(SLEEP_DEVICE_RESPONSE);
                apsPacket.setPayload(new byte[0]);
                break;


            default:
                break;

        }

        return apsPacket;
    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================



}/*********************************************END OF FILE*************************************************************/

