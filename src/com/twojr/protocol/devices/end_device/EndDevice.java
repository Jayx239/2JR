package com.twojr.protocol.devices.end_device;

import com.digi.xbee.api.exceptions.XBeeDeviceException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.devices.TwoJrDataListener;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;
import com.twojr.protocol.devices.TwoJRDevice;
import com.twojr.protocol.devices.TwoJRRadioListener;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class EndDevice extends TwoJRDevice{


    private int sleepInterval;
    private boolean running = false;
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
            this.open();
            this.running = true;
        }
        catch (XBeeException ex) {
            this.running = false;
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void send() throws XBeeException {

    }

    @Override
    public void read() {

    }

    @Override
    public void discover() {
        this.getNetwork();
    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

