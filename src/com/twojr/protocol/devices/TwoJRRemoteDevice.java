package com.twojr.protocol.devices;

import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBee16BitAddress;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.twojr.protocol.TwoJrDataGram;
import com.twojr.protocol.TwoJrDatagramQueue;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;

import java.util.HashMap;

/**
 * Created by rcunni202 on 5/13/2017.
 */
public class TwoJRRemoteDevice {

    private XBee64BitAddress address;
    private XBee16BitAddress shortAddress;
    private HashMap<Integer,EndPoint> endPoints;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public TwoJRRemoteDevice(XBee64BitAddress address) {
        this.address = address;
        endPoints = new HashMap<>();
    }


    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public XBee64BitAddress getAddress() {
        return address;
    }

    public void setAddress(XBee64BitAddress address) {
        this.address = address;
    }

    public XBee16BitAddress getShortAddress() {
        return shortAddress;
    }

    public void setShortAddress(XBee16BitAddress shortAddress) {
        this.shortAddress = shortAddress;
    }

    public HashMap<Integer, EndPoint> getEndPoints() {
        return endPoints;
    }

    public void setEndPoints(HashMap<Integer, EndPoint> endPoints) {
        this.endPoints = endPoints;
    }


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

