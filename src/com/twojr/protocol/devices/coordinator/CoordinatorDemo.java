package com.twojr.protocol.devices.coordinator;

import com.digi.xbee.api.exceptions.XBeeException;
import com.twojr.protocol.Attribute;
import com.twojr.protocol.aps.ApsPacket;
import com.twojr.protocol.aps.AttributeControl;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.protocol.aps.IApsPacket;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.integer.JUnsignedInteger;

import java.util.HashMap;

/**
 * Created by rcunni002c on 4/7/2017.
 */
public class CoordinatorDemo {

    public static void main(String[] args){


        Coordinator coordinator = new Coordinator("COM25",115200,null,null,null);


        byte[] data = {0x00, 0x01,0x02,0x11};
        byte[] num = {0x01};
        JUnsignedInteger integer = new JUnsignedInteger(num);
        //ReadCommand command = new ReadCommand();
        IApsPacket.apsCommands command = IApsPacket.apsCommands.CHECK_IN;
        EndPoint endPoint = new EndPoint((byte)0x00);
        AttributeControl attributeControl = new AttributeControl(JDataSizes.EIGHT_BIT,new HashMap<Integer, Attribute>(),false);

        ApsPacket apsPacket = new ApsPacket(integer,data,command,endPoint,attributeControl);

        NetworkPacket packet = new NetworkPacket(1,0,0x0013A200E,0x00,apsPacket.toByte());

        try {
            coordinator.start();

            coordinator.discover();

            coordinator.sendBroadcastData(packet.toByte());


        } catch (XBeeException e) {
            e.printStackTrace();
        }

        coordinator.stop();

    }


}
