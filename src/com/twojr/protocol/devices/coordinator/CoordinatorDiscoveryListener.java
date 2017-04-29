package com.twojr.protocol.devices.coordinator;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.listeners.IDiscoveryListener;

/**
 * Created by rcunni002c on 4/5/2017.
 */
public class CoordinatorDiscoveryListener implements IDiscoveryListener {


    //==================================================================================================================
    // Public Functions
    //==================================================================================================================

    @Override
    public void deviceDiscovered(RemoteXBeeDevice remoteXBeeDevice) {

        System.out.println(remoteXBeeDevice.toString() + "was added to the network");

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

            System.out.println("Discovery was not successful");

        }

    }

}/*********************************************END OF FILE*************************************************************/



