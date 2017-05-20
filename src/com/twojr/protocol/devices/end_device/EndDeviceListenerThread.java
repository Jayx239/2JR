package com.twojr.protocol.devices.end_device;

import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBeeMessage;
import com.digi.xbee.api.utils.HexUtils;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.devices.TwoJRDataGramHandler;
import com.twojr.protocol.network.NetworkPacket;

/**
 * Created by rcunni202 on 5/20/2017.
 */
public class EndDeviceListenerThread implements Runnable {

    private EndDevice endDevice;
    private XBeeMessage xBeeMessage;

    public EndDeviceListenerThread(EndDevice endDevice, XBeeMessage xBeeMessage){
        this.endDevice = endDevice;
        this.xBeeMessage = xBeeMessage;
    }

    @Override
    public void run() {

        System.out.println("Packet Received-------------------");
        System.out.println(printPretty(xBeeMessage));

        NetworkPacket packet = new NetworkPacket(xBeeMessage.getData());

        System.out.println(packet.print());

        System.out.println("Sending Response---------------");

        TwoJRDataGramHandler twoJRDataGramHandler = new TwoJRDataGramHandler();

        TwoJrDataGram dataGram = new TwoJrDataGram(xBeeMessage.getDevice().get64BitAddress(),packet);

        TwoJrDataGram response = twoJRDataGramHandler.handle(dataGram, endDevice);

        System.out.println(response.getPacket().print());
        try {
            endDevice.sendData(xBeeMessage.getDevice(),response.getPacket().toByte());
        } catch (XBeeException e) {
            e.printStackTrace();
        }

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
