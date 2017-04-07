package com.twojr.protocol.devices.coordinator;

import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.ATCommand;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.devices.ATCommands;
import com.twojr.protocol.devices.TwoJrDataListener;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;
import com.twojr.protocol.devices.TwoJRDevice;
import com.twojr.protocol.devices.TwoJRRadioListener;
import com.twojr.protocol.devices.end_device.EndDevice;
import com.twojr.protocol.devices.router.Router;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static com.twojr.protocol.devices.ATCommands.*;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class Coordinator extends TwoJRDevice{

    private HashMap<XBee64BitAddress,EndDevice> endDevices;
    private HashMap<XBee64BitAddress,Router> routers;
    private LinkedList<TwoJrDataGram> messageQueue;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================


    public Coordinator(String port, int baudRate, JString modelID, JString manufacturer,
                       JInteger applicationVersion, HashMap<XBee64BitAddress, EndDevice> endDevices,
                       HashMap<XBee64BitAddress, Router> routers, LinkedList<TwoJrDataGram> messageQueue) {

        super(port, baudRate, new CoordinatorDataListener(), modelID, manufacturer, applicationVersion);
        this.endDevices = endDevices;
        this.routers = routers;
        this.messageQueue = messageQueue;

        getNetwork().addDiscoveryListener(new CoordinatorDiscoveryListener());


    }

    public Coordinator(String port, int baudRate, JString modelID, JString manufacturer, JInteger applicationVersion) {
        super(port, baudRate, new CoordinatorDataListener(), modelID, manufacturer, applicationVersion);

        this.endDevices = new HashMap<>();
        this.routers = new HashMap<>();
        this.messageQueue = new LinkedList<>();

    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public void start() throws XBeeException {

        if(!isOpen()){

            open();

            addExplicitDataListener(getRadioListener());
            getNetwork().addDiscoveryListener(new CoordinatorDiscoveryListener());
            setReceiveTimeout(5000);

            System.out.println("Connection is Open");

            printInfo();


        }else {

            System.out.println("Connection is already open");

        }



    }

    @Override
    public void stop() {

        if(isOpen()){

            System.out.println("Closing Serial Connection");


            getNetwork().clearDeviceList();

            removeExplicitDataListener(getRadioListener());

            if(getNetwork().isDiscoveryRunning()){

                getNetwork().stopDiscoveryProcess();

            }

            close();

            System.out.println("Serial connection closed");


        }

    }

    @Override
    public void send() throws XBeeException {

        TwoJrDataGram dataGram = messageQueue.remove();

        System.out.println("Sending Data");

        sendData(dataGram.getDestinationLong(),dataGram.getPacket().toByte());

    }

    @Override
    public void read() {

    }

    @Override
    public void discover() {

        System.out.println("Starting Discovery");

        if(isOpen()) {

            if (!getNetwork().isDiscoveryRunning()) {

                getNetwork().startDiscoveryProcess();

            } else {

                System.out.println("Discovery is already running");

            }
        }

    }

    public void changeChannel(int channel) throws XBeeException {

        if(channel > 10 & channel < 27){


            byte data[] = new byte[0];
            data[0] = (byte)channel;


            setParameter(CHANNEL.getCommand(),data);


        }else {

            System.out.println("Invalid Channel Selection");

        }


    }

    public void printInfo(){


        try {
            System.out.println("\n\nCoordinator Information");

            byte[] pan = getParameter(PAN_ID.getCommand());
            System.out.print("\nPAN ID: 0x");

            for (byte bits : pan) {

                System.out.printf("%02X", bits);

            }

            System.out.println("\nDEST: 0x" + getDestinationAddress());
            System.out.println("NODE ID: " + getNodeID());
            System.out.println("SHORT MAC: 0x" + get16BitAddress());
            System.out.println("LONG MAC: 0x" + get64BitAddress());
            System.out.println("Firmware Version: v." + getFirmwareVersion());
            System.out.println("Hardware Version: v." + getHardwareVersion());
            System.out.println("Protocol: " + getXBeeProtocol());
            System.out.println("\n");


        } catch (XBeeException e) {

        e.printStackTrace();

        }

    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

