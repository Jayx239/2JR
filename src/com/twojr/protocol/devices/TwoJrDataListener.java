package com.twojr.protocol.devices;

import com.digi.xbee.api.listeners.IExplicitDataReceiveListener;
import com.digi.xbee.api.models.ExplicitXBeeMessage;

/**
 * Created by rcunni002c on 4/5/2017.
 */
public abstract class TwoJrDataListener implements IExplicitDataReceiveListener {

    //==================================================================================================================
    // Public Functions
    //==================================================================================================================

    @Override
    public abstract void explicitDataReceived(ExplicitXBeeMessage explicitXBeeMessage);


}/*********************************************END OF FILE*************************************************************/