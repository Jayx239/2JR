package com.twojr.protocol.devices.coordinator;

import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBeeMessage;
import com.digi.xbee.api.utils.HexUtils;
import com.twojr.protocol.Attribute;
import com.twojr.protocol.aps.ApsPacket;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.protocol.aps.IApsPacket;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.DataFactory;
import com.twojr.toolkit.DataTypes;
import com.twojr.toolkit.JData;
import com.twojr.toolkit.JString;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
        ApsPacket apsPacket = new ApsPacket(packet.getPayload());

        System.out.println(packet.print());

        switch(apsPacket.getCommandFrame()){

            case DISCOVER_RESPONSE:

                byte[] payload = apsPacket.getPayload();
                EndPoint endPoint = new EndPoint((byte)apsPacket.getEndPoint().getId());


                for(int i = 0; i < payload.length; i++) {

                    if (payload[i] == (byte) DataTypes.DISCOVER_START) {

                        Attribute attribute =  parseAtrribute(Arrays.copyOfRange(payload,i+1,i+4 + payload[i+1]));
                        endPoint.addAttribute(attribute);

                    }

                }

                coordinator.setRemotelEndPoint(xBeeMessage.getDevice().get64BitAddress(),endPoint);


                break;

            case READ_RESPONSE:

                EndPoint endPoint1 = coordinator.getRemoteEndPoint(xBeeMessage.getDevice().get64BitAddress(),apsPacket.getEndPoint().getId());
                ArrayList<Attribute> attributes = endPoint1.getAttributes(apsPacket.getAttrCtrl());

                int count = 0;

                for(Attribute attribute : attributes){

                    byte data[] = Arrays.copyOfRange(apsPacket.getPayload(),count,count + attribute.getData().getSize());
                    JData updated = DataFactory.getData(attribute.getData().getId(),data);
                    attribute.setData(updated);

                }

                break;

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

    private Attribute parseAtrribute(byte[] data){

        Attribute attribute;
        byte temp[] = {00};

        String string = new String(Arrays.copyOfRange(data,1,data[1]- 1));
        string = string.replace("\u0000", "");
        attribute = new Attribute(DataFactory.getData(data[data.length-2], temp),string);

        return attribute;
    }
}
