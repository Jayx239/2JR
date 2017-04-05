package com.twojr.protocol.devices.coordinator;

import com.digi.xbee.api.models.XBee64BitAddress;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;
import com.twojr.protocol.devices.TwoJRDevice;
import com.twojr.protocol.devices.TwoJRRadioListener;
import com.twojr.protocol.devices.end_device.EndDevice;
import com.twojr.protocol.devices.router.Router;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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

    public Coordinator(){

    }

    public Coordinator(int id, String name, TwoJRRadioListener radioListener, JString modelID, JString manufacturer,
                       JInteger applicationVersion, HashMap<XBee64BitAddress, EndDevice> endDevices,
                       HashMap<XBee64BitAddress, Router> routers, LinkedList<TwoJrDataGram> messageQueue) {

        super(id, name, radioListener, modelID, manufacturer, applicationVersion);
        this.endDevices = endDevices;
        this.routers = routers;
        this.messageQueue = messageQueue;

    }

    public Coordinator(int id, String name, TwoJRRadioListener radioListener, JString modelID, JString manufacturer, JInteger applicationVersion) {
        super(id, name, radioListener, modelID, manufacturer, applicationVersion);

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
    public void start() {

    }

    @Override
    public void close() {

    }

    @Override
    public void send() {

    }

    @Override
    public void read() {

    }

    @Override
    public void discover() {

    }

    @Override
    public String print() {
        return null;
    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

