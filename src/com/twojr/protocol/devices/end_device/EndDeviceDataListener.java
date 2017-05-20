package com.twojr.protocol.devices.end_device;

import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.listeners.IDataReceiveListener;
import com.digi.xbee.api.models.XBeeMessage;
import com.digi.xbee.api.utils.HexUtils;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.devices.TwoJRDataGramHandler;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.protocol.network.TwoJRNetworkPacketHandler;

/**
 * Created by rcunni202 on 5/20/2017.
 */
public class EndDeviceDataListener implements IDataReceiveListener {

    private EndDevice endDevice;

    //==================================================================================================================
    // Constructors
    //==================================================================================================================

    public EndDeviceDataListener(){

    }

    public EndDeviceDataListener(EndDevice endDevice) {
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
    public void dataReceived(XBeeMessage xBeeMessage) {

        EndDeviceListenerThread thread = new EndDeviceListenerThread(endDevice,xBeeMessage);
        thread.run();


    }

    //==================================================================================================================
    // Private Functions
    //==================================================================================================================



}
