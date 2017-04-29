package com.twojr.protocol.network;

import com.twojr.protocol.Packet;
import com.twojr.protocol.devices.TwoJrPacketHandler;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.integer.JUnsignedInteger;

/**
 * Created by Jason on 4/17/2017.
 */
public class TwoJRNetworkPacketHandler extends TwoJrPacketHandler {


    public TwoJRNetworkPacketHandler() {

    }


    @Override
    public NetworkPacket handle(Packet packet) {
        NetworkPacket inboundNetworkPacket = (NetworkPacket) packet;
        NetworkPacket responsePacket = inboundNetworkPacket;
        responsePacket.setPayload(new byte[0]);

        INetPacket.networkLayerCommands networkLayerCommand = INetPacket.networkLayerCommands.values()[inboundNetworkPacket.getCommandFrame().getValue()];

        // Check for network layer command
        switch (networkLayerCommand) {
            case REJOIN :
                responsePacket.setCommandFrame(new JUnsignedInteger(JDataSizes.EIGHT_BIT,INetPacket.networkLayerCommands.REJOIN_RESPONSE.ordinal()));
                break;
            case REJOIN_RESPONSE :

            case LEAVE_NETWORK :
                responsePacket.setCommandFrame(new JUnsignedInteger(JDataSizes.EIGHT_BIT,INetPacket.networkLayerCommands.LEAVE_NETWORK.ordinal()));
                break;
            case FACTORY_RESET :
                break;
            case ROUTE_REQUEST:
                responsePacket.setCommandFrame(new JUnsignedInteger(JDataSizes.EIGHT_BIT,INetPacket.networkLayerCommands.ROUTE_RESPONSE.ordinal()));
                break;
            case ROUTE_RESPONSE:
                break;
            case ROUTE_TABLE_REQUEST:
                responsePacket.setCommandFrame(new JUnsignedInteger(JDataSizes.EIGHT_BIT,INetPacket.networkLayerCommands.ROUTE_TABLE_RESPONSE.ordinal()));
                break;
            case ROUTE_TABLE_RESPONSE:
                break;
            case ADDRESS_REQUEST:
                responsePacket.setCommandFrame(new JUnsignedInteger(JDataSizes.EIGHT_BIT,INetPacket.networkLayerCommands.ADDRESS_RESPONSE.ordinal()));
                break;
            case ADDRESS_RESPONSE:
                break;

        }

        return responsePacket;
    }
}
