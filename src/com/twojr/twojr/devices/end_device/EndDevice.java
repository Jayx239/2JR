package com.twojr.twojr.devices.end_device;

import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;
import com.twojr.twojr.devices.TwoJRDevice;
import com.twojr.twojr.devices.TwoJRRadioListener;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class EndDevice extends TwoJRDevice{


    public int sleepInterval;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public EndDevice() {
    }

    public EndDevice(int id, String name, TwoJRRadioListener radioListener, JString modelID, JString manufacturer, JInteger applicationVersion, int sleepInterval) {
        super(id, name, radioListener, modelID, manufacturer, applicationVersion);
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

