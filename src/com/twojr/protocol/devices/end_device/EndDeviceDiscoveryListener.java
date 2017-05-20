package com.twojr.protocol.devices.end_device;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.listeners.IDiscoveryListener;

/**
 * Created by rcunni202 on 5/20/2017.
 */
public class EndDeviceDiscoveryListener implements IDiscoveryListener {

    private EndDevice endDevice;

    //==================================================================================================================
    // Constructors
    //==================================================================================================================

    public EndDeviceDiscoveryListener(EndDevice endDevice) {
        this.endDevice = endDevice;
    }


    //==================================================================================================================
    // Getters & Setters
    //==================================================================================================================

    public EndDevice getEndDevice() {
        return endDevice;
    }

    public void setEndDevice(EndDevice endDevice) {
        this.endDevice = endDevice;
    }

    //==================================================================================================================
    // Public Functions
    //==================================================================================================================

    @Override
    public void deviceDiscovered(RemoteXBeeDevice remoteXBeeDevice) {

        System.out.println(remoteXBeeDevice.toString() + "was added to the network");
        endDevice.getNetwork().stopDiscoveryProcess();

    }

    @Override
    public void discoveryError(String s) {

        System.out.println("Discovery Error: " + s);


    }

    @Override
    public void discoveryFinished(String s) {

        if(s != null) {

            System.out.println("Discovery Sucessfully Finished" );


        }else {

            System.out.println("Discovery was completed");

        }

    }

    //==================================================================================================================
    // Private Functions
    //==================================================================================================================

}
