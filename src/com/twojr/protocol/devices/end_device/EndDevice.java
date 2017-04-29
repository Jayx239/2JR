package com.twojr.protocol.devices.end_device;

import android.net.Network;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.listeners.IDataReceiveListener;
import com.digi.xbee.api.listeners.IExplicitDataReceiveListener;
import com.digi.xbee.api.listeners.IPacketReceiveListener;
import com.digi.xbee.api.models.ExplicitXBeeMessage;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.models.XBeeMessage;
import com.digi.xbee.api.packet.XBeePacket;
import com.twojr.protocol.Attribute;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.aps.ApsPacket;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.protocol.devices.TwoJRDevice;
import com.twojr.protocol.network.TwoJRNetworkPacketHandler;
import com.twojr.protocol.devices.TwoJrDataListener;
import com.twojr.protocol.network.INetPacket;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.JAddress;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;
import com.twojr.toolkit.integer.JUnsignedInteger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class EndDevice extends TwoJRDevice implements IExplicitDataReceiveListener {


    private int sleepInterval;
    private boolean running = false;
    TwoJRNetworkPacketHandler networkPacketHandler;
    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public EndDevice(String port, int baudRate, TwoJrDataListener radioListener, JString modelID, JString manufacturer, JInteger applicationVersion, int sleepInterval) {
        super(port, baudRate, radioListener, modelID, manufacturer, applicationVersion);
        this.sleepInterval = sleepInterval;
    }


    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public int getSleepInterval() {
        return sleepInterval;
    }

    public void setSleepInterval(int sleepInterval) {
        this.sleepInterval = sleepInterval;
    }

    public boolean isRunning() {
        return this.running;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public void start() {
        try {
            super.open();
            this.running = true;
            networkPacketHandler = new TwoJRNetworkPacketHandler();
            ArrayList<Attribute> attributes = new ArrayList<Attribute>();
            attributes.add(new Attribute(new JAddress(get64BitAddress().getValue()),toString()));

            EndPoint myEp = new EndPoint("Device",new JUnsignedInteger(JDataSizes.THIRTY_TWO_BIT,getModelID().getId()),attributes);
            LinkedList<EndPoint> endPoints = new LinkedList<>();
            endPoints.add(myEp);

            HashMap<XBee64BitAddress,LinkedList<EndPoint>> initEndPoints = new HashMap<>();
            initEndPoints.put(get64BitAddress(),endPoints);
            //setEndPoints(initEndPoints);

        }
        catch (XBeeException ex) {
            this.running = false;
            System.err.println("Error starting end device");
            ex.printStackTrace();
        }
    }

    @Override
    public void stop() {
        super.close();
    }

    @Override
    public void send() throws XBeeException {
        // Get next queued message
        TwoJrDataGram nextMessage = getOutMessageQueue().getNext();
        //NetworkPacket networkPacket = nextMessage.getPacket();
        // Send message
        //super.sendExplicitData(nextMessage.getDestinationLong(),apsPacket.getEndPoint().getId(),apsPacket.getEndPoint().getId(),);  //Data(nextMessage.getDestinationLong(),nextMessage.toByte());
        super.sendData(nextMessage.getDestinationLong(),nextMessage.toByte());

    }

    @Override
    public void read() {
        // Read encoded data
        XBeeMessage nextMessage = super.readData();

        // Strip datagram data
        TwoJrDataGram nextDatagram = new TwoJrDataGram(nextMessage.getData());

        // Store datagram in inbound queue
        //queueMessageToRead(nextDatagram);

        nextMessage.getDevice();
        super.queueMessageToSend(new TwoJrDataGram(nextMessage.getDevice().get64BitAddress(),new NetworkPacket(new JUnsignedInteger(JDataSizes.EIGHT_BIT,0), new JUnsignedInteger(JDataSizes.EIGHT_BIT,INetPacket.networkControlFlags.END_DEVICE.ordinal()),new JAddress(get64BitAddress().getValue()),new JUnsignedInteger(JDataSizes.EIGHT_BIT,INetPacket.networkLayerCommands.REJOIN_RESPONSE.ordinal()))));
        try {
            this.send();
        } catch (XBeeException ex) {

        }
    }

    @Override
    public void discover() {
        this.getNetwork();
    }

    @Override
    public void explicitDataReceived(ExplicitXBeeMessage explicitXBeeMessage) {
        System.err.println("Data received");
        //this.read();
        TwoJrDataGram nextDatagram = new TwoJrDataGram(explicitXBeeMessage.getData());

        // Store datagram in inbound queue
        //queueMessageToRead(nextDatagram);

        explicitXBeeMessage.getDevice();

        TwoJrDataGram responseDatagram;
        NetworkPacket responseNetworkPacket = networkPacketHandler.handle(nextDatagram.getPacket());
        //ApsPacket responseApsPacket = apsPacketHandler.handle(new ApsPacket(nextDatagram.getPacket.getPayload()));
        //responseNetworkPacket.setPayload(apsPacket.toByte());
        responseDatagram = new TwoJrDataGram(new XBee64BitAddress(responseNetworkPacket.getMacAddress().toByte()),responseNetworkPacket);
        super.queueMessageToSend(new TwoJrDataGram(explicitXBeeMessage.getDevice().get64BitAddress(),new NetworkPacket(new JUnsignedInteger(JDataSizes.EIGHT_BIT,0), new JUnsignedInteger(JDataSizes.EIGHT_BIT,INetPacket.networkControlFlags.END_DEVICE.ordinal()),new JAddress(get64BitAddress().toString()),new JUnsignedInteger(JDataSizes.EIGHT_BIT,INetPacket.networkLayerCommands.REJOIN_RESPONSE.ordinal()))));
        try {
            this.send();
        } catch (XBeeException ex) {

        }
        this.read();
    }
    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

