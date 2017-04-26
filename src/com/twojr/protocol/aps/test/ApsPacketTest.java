package com.twojr.protocol.aps.test;

import com.twojr.protocol.Attribute;
import com.twojr.protocol.aps.ApsPacket;
import com.twojr.protocol.aps.AttributeControl;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.protocol.aps.IApsPacket;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.integer.JUnsignedInteger;
import junit.framework.Assert;
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

        //for(int i=0; i<commandFrame.toByte().length; i++)
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
        //CheckInCommand commandFrame = new CheckInCommand();
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

}
