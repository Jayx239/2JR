package com.twojr.protocol.devices;

import com.twojr.protocol.Packet;
import com.twojr.protocol.TwoJrDataGram;

/**
 * Created by rcunni002c on 4/7/2017.
 */
public abstract class TwoJrPacketHandler {


    //==================================================================================================================
    // Constructors(s)
    //==================================================================================================================

    public TwoJrPacketHandler() {

    }

    //==================================================================================================================
    // Getter and Setters(s)
    //==================================================================================================================


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    public abstract Packet handle(Packet packet);

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/



