package com.twojr.protocol.devices;

import com.digi.xbee.api.models.XBee64BitAddress;
import com.twojr.protocol.Attribute;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.aps.*;
import com.twojr.protocol.devices.coordinator.Coordinator;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.protocol.network.TwoJRNetworkPacketHandler;

import java.util.ArrayList;

/**
 * Created by rcunni202 on 4/29/2017.
 */
public class TwoJRDataGramHandler {

    private TwoJRAPSPacketHandler twoJRAPSPacketHandler;
    private TwoJRNetworkPacketHandler twoJRNetworkPacketHandler;

    //==================================================================================================================
    // Constructors(s)
    //==================================================================================================================

    public TwoJRDataGramHandler() {
        twoJRAPSPacketHandler = new TwoJRAPSPacketHandler();
        twoJRNetworkPacketHandler = new TwoJRNetworkPacketHandler();
    }


    //==================================================================================================================
    // Getter and Setters(s)
    //==================================================================================================================

    public TwoJRAPSPacketHandler getTwoJRAPSPacketHandler() {
        return twoJRAPSPacketHandler;
    }

    public void setTwoJRAPSPacketHandler(TwoJRAPSPacketHandler twoJRAPSPacketHandler) {
        this.twoJRAPSPacketHandler = twoJRAPSPacketHandler;
    }

    public TwoJRNetworkPacketHandler getTwoJRNetworkPacketHandler() {
        return twoJRNetworkPacketHandler;
    }

    public void setTwoJRNetworkPacketHandler(TwoJRNetworkPacketHandler twoJRNetworkPacketHandler) {
        this.twoJRNetworkPacketHandler = twoJRNetworkPacketHandler;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    public TwoJrDataGram handle(TwoJrDataGram dataGram, TwoJRDevice device){

        NetworkPacket networkPacket = dataGram.getPacket();
        ApsPacket apsPacket = new ApsPacket(networkPacket.getPayload());
        XBee64BitAddress address = new XBee64BitAddress(networkPacket.getMacAddress().toByte());
        TwoJrDataGram response = dataGram;
        NetworkPacket networkResponse;
        ApsPacket apsResponse;

        networkResponse = twoJRNetworkPacketHandler.handle(networkPacket);
        apsResponse = twoJRAPSPacketHandler.handle(apsPacket);


        EndPoint endPoint = device.getLocalEndPoint(apsPacket.getEndPoint().getId());
        AttributeControl attributeControl = apsPacket.getAttrCtrl();
        LengthControl lengthControl = apsPacket.getLengthControl();
        byte[] apsPayload = new byte[0];

        switch (apsResponse.getCommandFrame()){

            case READ_RESPONSE:

                ArrayList<Attribute> attributes = endPoint.getAttributes(attributeControl);

                ArrayList<byte[]> bytes = new ArrayList<>();

                for (Attribute attribute : attributes){

                    bytes.add(attribute.getData().toByte());

                }

                apsPayload = apsPacket.generatePayload(bytes);

                break;

            case WRITE_RESPONSE:

                endPoint.setAttributes(attributeControl,apsPacket.getPayload());

                break;

            case DISCOVER_RESPONSE:

                break;

            case WAKE_DEVICE_RESPONSE:

                break;

            case CHECK_IN_RESPONSE:

                break;

            case SLEEP_DEVICE_RESPONSE:

                break;

            default:
                break;
        }


        apsResponse.setPayload(apsPayload);
        networkResponse.setPayload(apsResponse.toByte());
        response.setPacket(networkResponse);
        response.setDestinationLong(address);

        return dataGram;
    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

