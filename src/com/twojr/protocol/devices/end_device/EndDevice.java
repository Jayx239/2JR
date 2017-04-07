package com.twojr.protocol.devices.end_device;

import com.twojr.protocol.devices.TwoJrDataListener;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;
import com.twojr.protocol.devices.TwoJRDevice;
import com.twojr.protocol.devices.TwoJRRadioListener;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class EndDevice extends TwoJRDevice{


    public int sleepInterval;

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


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public void start() {

    }

    @Override
    public void stop() {

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


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

