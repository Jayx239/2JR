package com.twojr.protocol.devices;

import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.aps.ApsPacket;
import com.twojr.protocol.aps.AttributeControl;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.protocol.aps.IApsPacket;
import com.twojr.protocol.network.INetPacket;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.integer.JUnsignedInteger;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Jason on 4/17/2017.
 */
public class TwoJRNetworkPacketHandler extends TwoJrPacketHandler {


    public TwoJRNetworkPacketHandler(TwoJRDevice device) {
        super(device);
        this.device = device;
    }

    TwoJRDevice device;

    @Override
    public void handle(TwoJrDataGram dataGram) {
        NetworkPacket inboundNetworkPacket = dataGram.getPacket();
        NetworkPacket responsePacket;
        responsePacket = new NetworkPacket(inboundNetworkPacket.toByte());
        TwoJrDataGram responseDataGram = null;

        INetPacket.networkLayerCommands cmd = INetPacket.networkLayerCommands.values()[inboundNetworkPacket.getCommandFrame().getValue()];
        // Check for network layer command
        switch (INetPacket.networkLayerCommands.values()[inboundNetworkPacket.getCommandFrame().getValue()]) {
            case REJOIN :
                HashMap<XBee64BitAddress, LinkedList<EndPoint>> endPoints = device.getEndPoints();

                ApsPacket applicationPacket = new ApsPacket(inboundNetworkPacket.getPayload());
                LinkedList<EndPoint> newEndPoint = new LinkedList<EndPoint>();

                // Insert new endpoint into list
                newEndPoint.push(applicationPacket.getEndPoint());

                EndPoint thisEndPoint = device.getEndPoints().get(device.get64BitAddress()).getFirst();
                AttributeControl attributeControl = new AttributeControl(new byte[1],thisEndPoint);

                ApsPacket apsResponsePacket = new ApsPacket(new JUnsignedInteger(JDataSizes.EIGHT_BIT,1),
                        new byte[0],
                        IApsPacket.apsCommands.CHECK_IN,thisEndPoint,
                        attributeControl);

                endPoints.putIfAbsent(new XBee64BitAddress(inboundNetworkPacket.getMacAddress().toByte()), newEndPoint);

                responsePacket.setPayload(apsResponsePacket.toByte());
                responsePacket.setCommandFrame(new JUnsignedInteger(JDataSizes.EIGHT_BIT,INetPacket.networkLayerCommands.REJOIN_RESPONSE.ordinal()));
                responseDataGram = new TwoJrDataGram(new XBee64BitAddress(responsePacket.getMacAddress().toByte()),responsePacket);

            case REJOIN_RESPONSE :

            case LEAVE_NETWORK :

            case FACTORY_RESET :
            case ROUTE_REQUEST:

            case ROUTE_RESPONSE:
            case ROUTE_TABLE_REQUEST:
            case ROUTE_TABLE_RESPONSE:
            case ADDRESS_REQUEST:
            case ADDRESS_RESPONSE:


        }

        if(responseDataGram != null)
            device.queueMessageToSend(responseDataGram);
        try {
            device.send();
        } catch (XBeeException e) {
            System.err.println("Error on packet handle send");
            e.printStackTrace();
        }
        // Check attributes

    }
}
