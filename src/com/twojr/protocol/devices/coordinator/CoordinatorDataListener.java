package com.twojr.protocol.devices.coordinator;

import com.digi.xbee.api.models.ExplicitXBeeMessage;
import com.digi.xbee.api.models.XBeeMessage;
import com.twojr.protocol.devices.TwoJrDataListener;

/**
 * Created by rcunni002c on 4/5/2017.
 */
public class CoordinatorDataListener extends TwoJrDataListener {

    private Coordinator coordinator;


    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public CoordinatorDataListener(){

    }
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
    public void explicitDataReceived(ExplicitXBeeMessage explicitXBeeMessage) {



    }

    @Override
    public void dataReceived(XBeeMessage xBeeMessage) {

    }


}/*********************************************END OF FILE*************************************************************/
