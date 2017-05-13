package com.twojr.protocol.aps.test;

import com.twojr.protocol.Attribute;
import com.twojr.protocol.aps.ApsPacket;
import com.twojr.protocol.aps.AttributeControl;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.protocol.aps.IApsPacket;
import com.twojr.toolkit.JBoolean;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.integer.JSignedInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by rcunni002c on 4/7/2017.
 */
public class ApsPacketTest {


    @Test
    public void evaluateConstructor() {

        JUnsignedInteger sequenceNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT,34);
        byte[] payload = new byte[]{3,5,2,1,6,8,23};
        //CheckInCommand commandFrame = new CheckInCommand();
        IApsPacket.apsCommands commandFrame = IApsPacket.apsCommands.DISCOVER;
        EndPoint endPoint = new EndPoint("Endpoint 1", new JUnsignedInteger(JDataSizes.EIGHT_BIT,32),new ArrayList<Attribute>(){});
        AttributeControl attributeControl = new AttributeControl(JDataSizes.EIGHT_BIT, new HashMap<Integer,Attribute>(),false);
        ApsPacket apsPacket = new ApsPacket(sequenceNumber,payload,commandFrame,endPoint,attributeControl);

        assertEquals(sequenceNumber.getValue(), apsPacket.getSequenceNumber().getValue());

        for(int i=0; i<payload.length; i++)
            assertEquals(payload[i],apsPacket.getPayload()[i]);

        //for(int i=0; i<commandFrame.toByte().length; i++)
            assertEquals(commandFrame,apsPacket.getCommandFrame());

        for(int i=0; i<endPoint.toByte().length; i++)
            assertEquals(endPoint.toByte()[i],apsPacket.getEndPoint().toByte()[i]);

        for(int i=0; i<attributeControl.toByte().length; i++)
            assertEquals(attributeControl.toByte()[i], apsPacket.getAttrCtrl().toByte()[i]);
    }

    @Test
    public void evaluateByteInitializer() {

        JUnsignedInteger sequenceNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT,34);
        byte[] payload = new byte[]{3,5,2,1,6,8,23};
        //CheckInCommand commandFrame = new CheckInCommand()
        IApsPacket.apsCommands commandFrame = IApsPacket.apsCommands.DISCOVER;
        EndPoint endPoint = new EndPoint("Endpoint 1", new JUnsignedInteger(JDataSizes.EIGHT_BIT,32),new ArrayList<Attribute>(){});
        AttributeControl attributeControl = new AttributeControl(JDataSizes.EIGHT_BIT, new HashMap<Integer,Attribute>(),false);

        Vector<Byte> packetByteVector = new Vector<>();


        for(int i=0; i<sequenceNumber.toByte().length; i++)
            packetByteVector.add(sequenceNumber.toByte()[i]);

        for(int i=0; i<payload.length; i++)
            packetByteVector.add(payload[i]);

        packetByteVector.add((byte) commandFrame.ordinal());

        for(int i=0; i<endPoint.toByte().length; i++)
            packetByteVector.add(endPoint.toByte()[i]);

        for(int i=0; i<attributeControl.toByte().length; i++)
            packetByteVector.add(attributeControl.toByte()[i]);

        byte[] array = new byte[packetByteVector.size()];
        for(int i=0; i<array.length; i++)
            array[i] = packetByteVector.elementAt(i);

        ApsPacket byteConstructed = new ApsPacket(array);

        for(int i=0; i<array.length; i++) {
            assertEquals(array[i],byteConstructed.toByte()[i]);
        }

    }

    @Test
    public void evaluateToByte() {

        JUnsignedInteger sequenceNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT,34);
        byte[] payload = new byte[]{3,5,2,1,6,8,23};
        IApsPacket.apsCommands commandFrame = IApsPacket.apsCommands.DISCOVER;
        EndPoint endPoint = new EndPoint("Endpoint 1", new JUnsignedInteger(JDataSizes.EIGHT_BIT,32),new ArrayList<Attribute>(){});
        AttributeControl attributeControl = new AttributeControl(JDataSizes.EIGHT_BIT, new HashMap<Integer,Attribute>(),false);
        ApsPacket apsPacket = new ApsPacket(sequenceNumber,payload,commandFrame,endPoint,attributeControl);
        Vector<Byte> packetByteVector = new Vector<>();


        for(int i=0; i<sequenceNumber.getSize(); i++)
            packetByteVector.add(sequenceNumber.toByte()[i]);

        packetByteVector.add((byte) commandFrame.getId());

        for(int i=0; i<endPoint.getSize(); i++)
            packetByteVector.add(endPoint.toByte()[i]);

        packetByteVector.add((byte) attributeControl.getSize());

        for(int i=0; i<attributeControl.getSize(); i++)
            packetByteVector.add(attributeControl.toByte()[i]);

        for(int i=0; i<payload.length; i++)
            packetByteVector.add(payload[i]);

        byte[] array = new byte[packetByteVector.size()];
        for(int i=0; i<array.length; i++)
            array[i] = packetByteVector.elementAt(i);

        //System.err.println(Integer.toString(array.length));

        int index = 0;
        byte[] bytes = apsPacket.toByte();
        for(int i=0; i<bytes.length; i++) {
            assertEquals(array[i], bytes[i]);
        }


    }

    @Test
    public void evaluatePrint() {
        Attribute running = new Attribute(new JBoolean(true),"Running");
        Attribute groupMembers = new Attribute(new JSignedInteger(new byte[]{0x09}),"Group Members");
        Attribute projMembers = new Attribute(new JSignedInteger(new byte[]{0x08}),"Project Members");


        EndPoint endPoint = new EndPoint((byte) 0x00);
        endPoint.addAttribute(running);
        endPoint.addAttribute(groupMembers);
        endPoint.addAttribute(projMembers);

        HashMap<Integer,EndPoint> endpoints = new HashMap<>();

        endpoints.put(endPoint.getId(),endPoint);
        AttributeControl attributeControl = new AttributeControl(new byte[]{(byte)0x07});

        byte[] writeData = {0x00, 0x02, 0x03};
        ApsPacket apsPacket = new ApsPacket(new JSignedInteger(new byte[]{0}),writeData, IApsPacket.apsCommands.DISCOVER,endPoint,attributeControl);

        assertEquals("\n" +
                "------------------------------\n" +
                "Application Layer Packet\n" +
                "------------------------------\n" +
                "Command Frame: DISCOVER\n" +
                "Endpoint: ID: 0\n" +
                "Running: true\n" +
                "Group Members: 9\n" +
                "Project Members: 8\n" +
                "\n" +
                "Attribute Control: Row[0]: 00000111\n" +
                "\n" +
                "\n" +
                "------------------------------\n" +
                "Attributes\n" +
                "------------------------------\n" +
                "Name: Running Type: Boolean\n" +
                "Value: false\n" +
                "Name: Group Members Type: Signed Integer\n" +
                "Value: 2\n" +
                "Name: Project Members Type: Signed Integer\n" +
                "Value: 3\n" +
                "\n" +
                "------------------------------\n" +
                "Payload as byte array\n" +
                "------------------------------\n" +
                "Payload [0]: 00000000\n" +
                "Payload [1]: 00000010\n" +
                "Payload [2]: 00000011\n",apsPacket.print());

    }

}
