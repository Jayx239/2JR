package com.twojr.protocol.devices.coordinator;

import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;
import com.twojr.protocol.devices.TwoJRDevice;
import com.twojr.protocol.devices.TwoJRRadioListener;
import com.twojr.protocol.devices.end_device.EndDevice;
import com.twojr.protocol.devices.router.Router;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class Coordinator extends TwoJRDevice{

    private HashMap<String,EndDevice> endDevices;
    private HashMap<String,Router> routers;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public Coordinator(){

    }

    public Coordinator(int id, String name, TwoJRRadioListener radioListener, JString modelID, JString manufacturer, JInteger applicationVersion, HashMap<String, EndDevice> endDevices, HashMap<String, Router> routers) {
        super(id, name, radioListener, modelID, manufacturer, applicationVersion);
        this.endDevices = endDevices;
        this.routers = routers;
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

