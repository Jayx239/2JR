package com.twojr.utilities.threading;

import com.twojr.protocol.devices.TwoJRDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * Created by rcunni202 on 5/18/2017.
 */
public class DiscoverThread extends SwingWorker<Boolean, String> {

    private volatile boolean running = true;
    private TwoJRDevice device;

    public DiscoverThread(TwoJRDevice device){
        this.device = device;
    }

    public void terminate() {
        running = false;
    }

    @Override
    public Boolean doInBackground() throws Exception {

        device.discover();
        return true;

    }

    protected void done(){

    }

}
