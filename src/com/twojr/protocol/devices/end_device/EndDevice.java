package com.twojr.protocol.devices.end_device;

import com.digi.xbee.api.XBeeNetwork;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.listeners.IExplicitDataReceiveListener;
import com.digi.xbee.api.models.DiscoveryOptions;
import com.digi.xbee.api.models.ExplicitXBeeMessage;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.models.XBeeMessage;
import com.twojr.protocol.Attribute;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.protocol.devices.TwoJRDevice;
import com.twojr.protocol.devices.coordinator.CoordinatorDataListener;
import com.twojr.protocol.devices.coordinator.CoordinatorDiscoveryListener;
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
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class EndDevice extends TwoJRDevice {


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
            if (!isOpen()) {


                open();
                reset();

                addDataListener(new EndDeviceDataListener(this));
                getNetwork().addDiscoveryListener(new EndDeviceDiscoveryListener(this));
                setReceiveTimeout(5000);
                applyChanges();

                System.out.println("Connection is Open");


            } else {

                System.out.println("Connection is already open");

            }

        }catch (XBeeException e){


        }


    }

    @Override
    public void stop() {

        if(isOpen()){

            System.out.println("Closing Serial Connection");


            getNetwork().clearDeviceList();

            removeDataListener(getRadioListener());

            if(getNetwork().isDiscoveryRunning()){

                getNetwork().stopDiscoveryProcess();


            }

            close();

            System.out.println("Serial connection closed");


        }
    }

    @Override
    public void send() throws XBeeException {
        // Get next queued message
        //TwoJrDataGram nextMessage = getOutMessageQueue().getNext();
        //NetworkPacket networkPacket = nextMessage.getPacket();
        // Send message
        //super.sendExplicitData(nextMessage.getDestinationLong(),apsPacket.getEndPoint().getId(),apsPacket.getEndPoint().getId(),);  //Data(nextMessage.getDestinationLong(),nextMessage.toByte());
        //super.sendData(nextMessage.getDestinationLong(),nextMessage.toByte());

    }

    @Override
    public void read() {

    }

    @Override
    public void discover() {

        System.out.println("Starting Discovery");
        XBeeNetwork network = getNetwork();
        try {
            network.setDiscoveryTimeout(10000);

            // Append the device type identifier and the local device.
            network.setDiscoveryOptions(EnumSet.of(DiscoveryOptions.APPEND_DD));

            if (isOpen()) {

                if (!network.isDiscoveryRunning()) {


                    network.startDiscoveryProcess();

                    while (getNetwork().isDiscoveryRunning()) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                } else {

                    System.out.println("Discovery is already running");

                }
            }

        } catch (TimeoutException e1){

            System.out.println("Discovery Timeout");
            network.stopDiscoveryProcess();

        } catch (XBeeException e) {
            e.printStackTrace();
        }


    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

