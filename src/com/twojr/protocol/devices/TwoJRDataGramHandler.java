package com.twojr.protocol.devices;

import com.digi.xbee.api.models.XBee64BitAddress;
import com.twojr.protocol.Attribute;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.aps.*;
import com.twojr.protocol.devices.coordinator.Coordinator;
import com.twojr.protocol.network.NetworkPacket;

import java.util.ArrayList;

/**
 * Created by rcunni202 on 4/29/2017.
 */
public class TwoJRDataGramHandler {

    private TwoJRDevice device;
    private TwoJRAPSPacketHandler twoJRAPSPacketHandler;
    //private TwoJRNetworkPAcketHandler twoJRNetworkPAcketHandler;

    //==================================================================================================================
    // Constructors(s)
    //==================================================================================================================

    public TwoJRDataGramHandler(TwoJRDevice device) {
        this.device = device;
        twoJRAPSPacketHandler = new TwoJRAPSPacketHandler();
    }


    //==================================================================================================================
    // Getter and Setters(s)
    //==================================================================================================================

    public TwoJRDevice getDevice() {
        return device;
    }

    public void setDevice(TwoJRDevice device) {
        this.device = device;
    }

    public TwoJRAPSPacketHandler getTwoJRAPSPacketHandler() {
        return twoJRAPSPacketHandler;
    }

    public void setTwoJRAPSPacketHandler(TwoJRAPSPacketHandler twoJRAPSPacketHandler) {
        this.twoJRAPSPacketHandler = twoJRAPSPacketHandler;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    public TwoJrDataGram handle(TwoJrDataGram dataGram){

        NetworkPacket networkPacket = dataGram.getPacket();
        ApsPacket apsPacket = new ApsPacket(networkPacket.getPayload());
        XBee64BitAddress address = new XBee64BitAddress(networkPacket.getMacAddress().toByte());
        TwoJrDataGram reponse;
        NetworkPacket networkResponse;
        ApsPacket apsResponse;


        apsResponse = twoJRAPSPacketHandler.handle(apsPacket);


        EndPoint endPoint = device.getLocalEndPoint(apsPacket.getEndPoint().getId());
        AttributeControl attributeControl = apsPacket.getAttrCtrl();
        LengthControl lengthControl = apsPacket.getLengthControl();
        ArrayList<Attribute> attributes = endPoint.getAttributes(attributeControl);
        byte[] apsPayload;

        switch (apsResponse.getCommandFrame()){

            case READ_RESPONSE:

                ArrayList<byte[]> bytes = new ArrayList<>();

                for (Attribute attribute : attributes){

                    bytes.add(attribute.getData().toByte());

                }

                apsPayload = apsPacket.generatePayload(bytes);

                break;

            case WRITE_RESPONSE:



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


        return dataGram;
    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

