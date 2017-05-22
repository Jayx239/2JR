package com.twojr.protocol.devices.coordinator;

import com.digi.xbee.api.XBeeNetwork;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.APIOutputMode;
import com.digi.xbee.api.models.ATCommand;
import com.digi.xbee.api.models.DiscoveryOptions;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.aps.ApsPacket;
import com.twojr.protocol.aps.AttributeControl;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.protocol.aps.IApsPacket;
import com.twojr.protocol.devices.*;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;
import com.twojr.protocol.devices.end_device.EndDevice;
import com.twojr.protocol.devices.router.Router;
import com.twojr.toolkit.integer.JSignedInteger;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.digi.xbee.api.models.APIOutputMode.*;
import static com.digi.xbee.api.models.DiscoveryOptions.*;
import static com.twojr.protocol.devices.ATCommands.*;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class Coordinator extends TwoJRDevice{

    private HashMap<XBee64BitAddress,TwoJRRemoteDevice> endDevices;
    private HashMap<XBee64BitAddress,TwoJRRemoteDevice> routers;
    private LinkedList<TwoJrDataGram> messageQueue;
    private boolean networkDiscovered;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================


    public Coordinator(String port, int baudRate, JString modelID, JString manufacturer,
                       JInteger applicationVersion, HashMap<XBee64BitAddress, TwoJRRemoteDevice> endDevices,
                       HashMap<XBee64BitAddress, TwoJRRemoteDevice> routers, LinkedList<TwoJrDataGram> messageQueue) {

        super(port, baudRate, new CoordinatorDataListener(null), modelID, manufacturer, applicationVersion);
        CoordinatorDataListener dataListener = new CoordinatorDataListener(this);
        setRadioListener(dataListener);
        this.endDevices = endDevices;
        this.routers = routers;
        this.messageQueue = messageQueue;
        this.networkDiscovered = false;

        getNetwork().addDiscoveryListener(new CoordinatorDiscoveryListener(this));


    }

    public Coordinator(String port, int baudRate, JString modelID, JString manufacturer, JInteger applicationVersion) {
        super(port, baudRate, new CoordinatorDataListener(null), modelID, manufacturer, applicationVersion);

        CoordinatorDataListener dataListener = new CoordinatorDataListener(this);
        setRadioListener(dataListener);
        this.endDevices = new HashMap<>();
        this.routers = new HashMap<>();
        this.messageQueue = new LinkedList<>();
        this.networkDiscovered = false;

    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public HashMap<XBee64BitAddress, TwoJRRemoteDevice> getRouters() {
        return routers;
    }

    public void setRouters(HashMap<XBee64BitAddress, TwoJRRemoteDevice> routers) {
        this.routers = routers;
    }

    public HashMap<XBee64BitAddress, TwoJRRemoteDevice> getEndDevices() {
        return endDevices;
    }

    public void setEndDevices(HashMap<XBee64BitAddress, TwoJRRemoteDevice> endDevices) {
        this.endDevices = endDevices;
    }

    public void addEndDevice(XBee64BitAddress address, TwoJRRemoteDevice endDevice){
        endDevices.put(address,endDevice);
    }

    public TwoJRRemoteDevice getEndDevice(XBee64BitAddress address){
        return endDevices.get(address);
    }

    public boolean isNetworkDiscovered() {
        return networkDiscovered;
    }

    public void setNetworkDiscovered(boolean networkDiscovered) {
        this.networkDiscovered = networkDiscovered;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public void start() throws XBeeException {

        if(!isOpen()){

            freeCOMPort("COM6");
            open();
            reset();

            addDataListener(new CoordinatorDataListener(this));
            getNetwork().addDiscoveryListener(new CoordinatorDiscoveryListener(this));
            //setAPIOutputMode(MODE_EXPLICIT_ZDO_PASSTHRU);
            setReceiveTimeout(5000);
            applyChanges();

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

        System.out.println("Sending Data");

        AttributeControl attributeControl = new AttributeControl(new byte[]{(byte) 0x03});

        ApsPacket apsPacket = new ApsPacket(new JSignedInteger(new byte[]{0}), new byte[]{}, IApsPacket.apsCommands.DISCOVER, new EndPoint((byte) 0x01), attributeControl);
        NetworkPacket networkPacket = new NetworkPacket(0, 0, 0, 0, apsPacket.toByte());

        System.out.println("Sending APS Discovery Request");
        System.out.println(networkPacket.print());

        sendData(new XBee64BitAddress("0013A20041249BBE"),networkPacket.toByte());

    }



    @Override
    public void read() {

    }

    @Override
    public void discover() {

        System.out.println("Starting Discovery");
        XBeeNetwork network = getNetwork();
        try {
            network.setDiscoveryTimeout(20000);

            // Append the device type identifier and the local device.
            network.setDiscoveryOptions(EnumSet.of(DiscoveryOptions.APPEND_DD, DiscoveryOptions.DISCOVER_MYSELF));

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

    private void freeCOMPort(String COM){

            SerialPort serialPort = null;
            Enumeration portList = CommPortIdentifier.getPortIdentifiers();

            while (portList.hasMoreElements()) {
                CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
                if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                    if (portId.getName().equals(COM)) {

                    }
                }
            }
    }


}/*********************************************END OF FILE*************************************************************/

