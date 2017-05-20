package com.twojr.protocol.devices.coordinator;

import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.ExplicitXBeeMessage;
import com.digi.xbee.api.models.XBeeMessage;
import com.digi.xbee.api.utils.HexUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.twojr.protocol.devices.TwoJrDataListener;
import com.twojr.protocol.network.NetworkPacket;

/**
 * Created by rcunni002c on 4/5/2017.
 */
public class CoordinatorDataListener extends TwoJrDataListener {

    private Coordinator coordinator;


    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public CoordinatorDataListener(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    //==================================================================================================================
    // Getter(s) and Setter(s)
    //==================================================================================================================

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }


    //==================================================================================================================
    // Public Functions
    //==================================================================================================================

    @Override
    public void dataReceived(XBeeMessage xBeeMessage) {

        CoordinatorDataListenThread thread = new CoordinatorDataListenThread(coordinator,xBeeMessage);
        thread.run();

    }


    //==================================================================================================================
    // Private Function(s)
    //==================================================================================================================





}/*********************************************END OF FILE*************************************************************/
