package com.twojr.protocol;

import com.digi.xbee.api.models.XBee16BitAddress;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.twojr.protocol.network.NetworkPacket;

/**
 * Created by rcunni002c on 4/4/2017.
 */
public class TwoJrDataGram {

    private XBee64BitAddress destinationLong;
    private XBee16BitAddress desintationShort;
    private NetworkPacket packet;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public TwoJrDataGram(XBee64BitAddress destinationLong, XBee16BitAddress desintationShort, NetworkPacket packet) {
        this.destinationLong = destinationLong;
        this.desintationShort = desintationShort;
        this.packet = packet;
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/


