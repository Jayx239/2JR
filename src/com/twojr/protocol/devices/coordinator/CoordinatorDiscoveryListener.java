package com.twojr.protocol.devices.coordinator;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.listeners.IDiscoveryListener;
import com.twojr.protocol.aps.ApsPacket;
import com.twojr.protocol.aps.AttributeControl;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.protocol.aps.IApsPacket;
import com.twojr.protocol.devices.TwoJRRemoteDevice;
import com.twojr.protocol.devices.end_device.EndDevice;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.integer.JSignedInteger;
import com.twojr.utilities.threading.DiscoverThread;

/**
 * Created by rcunni002c on 4/5/2017.
 */
public class CoordinatorDiscoveryListener implements IDiscoveryListener {


    private Coordinator coordinator;

    public CoordinatorDiscoveryListener(Coordinator coordinator){
        this.coordinator = coordinator;
    }


    //==================================================================================================================
    // Public Functions
    //==================================================================================================================

    @Override
    public void deviceDiscovered(RemoteXBeeDevice remoteXBeeDevice) {

        System.out.println(remoteXBeeDevice.get64BitAddress().toString() + " was added to the network");

        CoordinatorDiscoverThread discoverThread = new CoordinatorDiscoverThread(coordinator,remoteXBeeDevice);
        discoverThread.run();
        coordinator.getNetwork().stopDiscoveryProcess();

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
    // Private Function(s)
    //==================================================================================================================



}/*********************************************END OF FILE*************************************************************/



