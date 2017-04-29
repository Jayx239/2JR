package com.twojr.protocol.devices.end_device.test;

import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.ExplicitXBeeMessage;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.models.XBeeMessage;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.aps.ApsPacket;
import com.twojr.protocol.aps.AttributeControl;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.protocol.aps.IApsPacket;
import com.twojr.protocol.devices.TwoJRRadioListener;
import com.twojr.protocol.devices.end_device.EndDevice;
import com.twojr.protocol.network.INetPacket;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.JString;
import com.twojr.toolkit.integer.JUnsignedInteger;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Jason on 4/8/2017.
 * Requires Xbee radios to be attached via serial.
 * Set the com ports for each of the devices manually
 */
public class EndDeviceTest {

    /*
    * evaluateDeviceReceiver - This test opens two radio devices and transmits data between them.
    */
    @Test
    public void evaluateDeviceReceiver() {

        // Device specific configuration
        XBee64BitAddress endDeviceAddress = new XBee64BitAddress("0x0013A20040D661AA"); //0x0013A20040D661AA
        String endDevicePort = "COM7";
        String endDevice2Port = "COM8";


        TwoJRRadioListener twoJRRadioListener = new TwoJRRadioListener("COM3");
        EndDevice endDevice = new EndDevice(endDevicePort,9600,twoJRRadioListener, new JString(new byte[]{0x00,0x3,0x4}),new JString(new byte[] {0x3,0x32}),new JUnsignedInteger(JDataSizes.EIGHT_BIT,23),10);
        EndDevice endDevice2 = new EndDevice(endDevice2Port,9600,twoJRRadioListener, new JString(new byte[]{0x00,0x2,0x1}),new JString(new byte[] {0x3,0x32}),new JUnsignedInteger(JDataSizes.EIGHT_BIT,23),10);

        endDevice.start();
        endDevice2.start();

        ExplicitXBeeMessage xBeeMessage;
        TwoJrDataGram twoJrDataGram;
        //endDevice.addDataListener(endDevice);
        //endDevice.addPacketListener(endDevice);

        //endDevice.readData();
        TwoJrDataGram sendDataGram = new TwoJrDataGram(endDeviceAddress,new NetworkPacket(5, INetPacket.networkControlFlags.ENCRYPTED.ordinal(),0xffffffff,INetPacket.networkLayerCommands.ROUTE_REQUEST.ordinal(),new byte[] {0x23,0x4,0x1,0x5,0x2,0x5,74,97,115,111,110,33}));
        try {
            endDevice2.sendData(endDeviceAddress, sendDataGram.toByte());
            //endDevice2.send();
        } catch (XBeeException ex) {
            ex.printStackTrace();
        }

        while((xBeeMessage = endDevice.readData(10000)) == null) {
            if(xBeeMessage != null) {
                System.out.println("non-null");
            }

        }

        twoJrDataGram = new TwoJrDataGram(xBeeMessage.getData());

        System.err.print(twoJrDataGram.getPacket().print());
        //System.err.print(twoJrDataGram.getNext().getPacket().print());

        byte[] packet = twoJrDataGram.toByte();


        for(int i=0; i<packet.length; i++) {
            assertEquals(sendDataGram.toByte()[i], packet[i]);
            System.err.print((char)packet[i]);
        }
        endDevice.stop();

    }

    /*
    * evaluateHandlerTransmitter - This tests the TwoJRNetworkPacketHandler
    * Not complete
     */
    @Test
    public void evaluateHandlerTransmitter() {
        TwoJRRadioListener twoJRRadioListener = new TwoJRRadioListener("COM3");

        NetworkPacket netPacket = new NetworkPacket(new byte[]{0x01,0x01,0x00,0x13, (byte) 0xA2,0x00,0x40, (byte) 0xD6,0x61, (byte) 0xAA,(byte) INetPacket.networkLayerCommands.REJOIN.ordinal()});

        EndPoint endPoint = new EndPoint((byte) 1);

        AttributeControl attrControl = new AttributeControl(new byte[]{0x00},endPoint);
        ApsPacket applicationLayerPacket = new ApsPacket(new byte[]{0x01, (byte) IApsPacket.apsCommands.CHECK_IN.ordinal(),endPoint.toByte()[0],0x01,attrControl.toByte()[0]});
        netPacket.setPayload(applicationLayerPacket.toByte());

        EndDevice endDevice = new EndDevice("COM7",9600,twoJRRadioListener, new JString(new byte[]{0x00,0x3,0x4}),new JString(new byte[] {0x3,0x32}),new JUnsignedInteger(JDataSizes.EIGHT_BIT,23),10);
        EndDevice endDevice2 = new EndDevice("COM8",9600,twoJRRadioListener, new JString(new byte[]{0x00,0x2,0x1}),new JString(new byte[] {0x3,0x32}),new JUnsignedInteger(JDataSizes.EIGHT_BIT,23),10);

        endDevice.start();
        endDevice2.start();

        //endDevice.addDataListener(endDevice);
        //endDevice2.addDataListener(endDevice2);

        TwoJrDataGram sendDataGram = new TwoJrDataGram(new XBee64BitAddress("0x0013A20040D661AA"),netPacket);
        try {
            endDevice2.sendData(new XBee64BitAddress("0x0013A20040D661AA"), sendDataGram.toByte());
            //endDevice2.send();
        } catch (XBeeException ex) {
            ex.printStackTrace();
        }
        XBeeMessage xBeeMessage = endDevice.readData(1000);
        TwoJrDataGram twoJrDataGram = new TwoJrDataGram(xBeeMessage.getData());
        System.err.print(twoJrDataGram.getPacket().print());

        xBeeMessage = endDevice2.readData(10000);
        twoJrDataGram = new TwoJrDataGram(xBeeMessage.getData());

        System.err.print(twoJrDataGram.getPacket().print());


        System.err.print(twoJrDataGram.getPacket().print());

        endDevice2.stop();


    }

    @Test
    public void evaluateHandlerReceiver() {

    }
}
