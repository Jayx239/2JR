package com.twojr.protocol.devices.coordinator;

import com.digi.xbee.api.exceptions.XBeeException;
import com.twojr.protocol.Attribute;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.aps.ApsPacket;
import com.twojr.protocol.aps.AttributeControl;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.protocol.aps.IApsPacket;
import com.twojr.protocol.devices.TwoJRDataGramHandler;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.JBoolean;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.integer.JSignedInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;

import java.util.HashMap;

import static com.twojr.protocol.aps.IApsPacket.*;

/**
 * Created by rcunni002c on 4/7/2017.
 */
public class CoordinatorDemo {

    public static void main(String[] args) throws XBeeException {

        Coordinator coordinator = new Coordinator("COM6",9600,null,null,null);

        coordinator.start();

        /*
        Attribute running = new Attribute(new JBoolean(true),"Running");
        Attribute groupMembers = new Attribute(new JSignedInteger(new byte[]{0x09}),"Group Members");
        Attribute projMembers = new Attribute(new JSignedInteger(new byte[]{0x08}),"Project Members");


        EndPoint endPoint = new EndPoint((byte) 0x00);
        endPoint.addAttribute(running);
        endPoint.addAttribute(groupMembers);
        endPoint.addAttribute(projMembers);

        HashMap<Integer,EndPoint> endpoints = new HashMap<>();

        endpoints.put(endPoint.getId(),endPoint);
        coordinator.setLocalEndpoints(endpoints);


        AttributeControl attributeControl = new AttributeControl(new byte[]{(byte)0x07});

        byte[] writeData = {0x00, 0x02, 0x03};
        ApsPacket apsPacket = new ApsPacket(new JSignedInteger(new byte[]{0}),writeData, apsCommands.DISCOVER,endPoint,attributeControl);
        NetworkPacket networkPacket = new NetworkPacket(0,0,0,0,apsPacket.toByte());

        TwoJrDataGram dataGram = new TwoJrDataGram(null,networkPacket);

        System.out.println("Message Recieved----------------");
        System.out.print(dataGram.getPacket().print(true));
        ApsPacket aps = new ApsPacket(dataGram.getPacket().getPayload());
        aps.print();
        TwoJRDataGramHandler handler = new TwoJRDataGramHandler();
        handler.handle(dataGram, coordinator);

        System.out.println("Message Handled----------------");
        System.out.print(dataGram.getPacket().print(true));
        aps = new ApsPacket(dataGram.getPacket().getPayload());
        aps.print();

        */

    }





}
