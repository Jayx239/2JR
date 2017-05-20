package com.twojr.protocol.devices.coordinator;

import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBeeMessage;
import com.digi.xbee.api.utils.HexUtils;
import com.twojr.protocol.network.NetworkPacket;

/**
 * Created by rcunni202 on 5/20/2017.
 */
public class CoordinatorDataListenThread implements Runnable {

    private Coordinator coordinator;
    private XBeeMessage xBeeMessage;

    public CoordinatorDataListenThread(Coordinator coordinator, XBeeMessage xBeeMessage) {
        this.coordinator = coordinator;
        this.xBeeMessage = xBeeMessage;
    }

    @Override
    public void run() {


        System.out.println("Packet Received-------------------");
        System.out.println(printPretty(xBeeMessage));

        NetworkPacket packet = new NetworkPacket(xBeeMessage.getData());

        System.out.println(packet.print());

    }

    private String printPretty(XBeeMessage xBeeMessage){

        String message = "";



        try {
            message += "SHORT MAC: 0x" + xBeeMessage.getDevice().get16BitAddress().toString() + "\n";
            message += "LONG MAC: 0x" + xBeeMessage.getDevice().get64BitAddress().toString() + "\n";

            byte[] panId = xBeeMessage.getDevice().getPANID();
            message += "PAN ID: 0x" + HexUtils.byteArrayToHexString(panId) + "\n";
            message += "Data: "+ HexUtils.byteArrayToHexString(xBeeMessage.getData()) + "\n";

        }catch (XBeeException e){

        }

        return message;
    }
}
