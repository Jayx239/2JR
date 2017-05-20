package com.twojr.protocol.devices;

import com.digi.xbee.api.XBee;
import com.digi.xbee.api.listeners.IDataReceiveListener;
import com.digi.xbee.api.listeners.IExplicitDataReceiveListener;
import com.digi.xbee.api.models.ExplicitXBeeMessage;
import com.digi.xbee.api.models.XBeeMessage;

/**
 * Created by rcunni002c on 4/5/2017.
 */
public abstract class TwoJrDataListener implements IDataReceiveListener{

    //==================================================================================================================
    // Public Functions
    //==================================================================================================================

    @Override
    public abstract void dataReceived(XBeeMessage xBeeMessage);

}/*********************************************END OF FILE*************************************************************/