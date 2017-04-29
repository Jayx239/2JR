package com.twojr.protocol.devices;

import com.digi.xbee.api.Raw802Device;
import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBee16BitAddress;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.toolkit.JIdentity;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public abstract class TwoJRDevice extends Raw802Device {

    private TwoJrDataListener radioListener;
    private JString modelID;
    private JString manufacturer;
    private JInteger applicationVersion;
    private HashMap<XBee64BitAddress,HashMap<Integer,EndPoint>> remoteEndPoints;
    private HashMap<Integer,EndPoint> localEndpoints;


    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public TwoJRDevice(String port, int baudRate, TwoJrDataListener radioListener, JString modelID, JString manufacturer,
                       JInteger applicationVersion, HashMap<XBee64BitAddress, HashMap<Integer,EndPoint>> remoteEndPoints,
                       HashMap<Integer,EndPoint> localEndpoints) {
        super(port, baudRate);
        this.radioListener = radioListener;
        this.modelID = modelID;
        this.manufacturer = manufacturer;
        this.applicationVersion = applicationVersion;
        this.remoteEndPoints = remoteEndPoints;

    }

    public TwoJRDevice(String port, int baudRate, TwoJrDataListener radioListener, JString modelID, JString manufacturer, JInteger applicationVersion) {
        super(port, baudRate);
        this.radioListener = radioListener;
        this.modelID = modelID;
        this.manufacturer = manufacturer;
        this.applicationVersion = applicationVersion;
        this.remoteEndPoints = new HashMap<>();
        this.localEndpoints = new HashMap<>();
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public TwoJrDataListener getRadioListener() {
        return radioListener;
    }

    public void setRadioListener(TwoJrDataListener radioListener) {
        this.radioListener = radioListener;
    }

    public JString getModelID() {
        return modelID;
    }

    public void setModelID(JString modelID) {
        this.modelID = modelID;
    }

    public JString getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(JString manufacturer) {
        this.manufacturer = manufacturer;
    }

    public JInteger getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(JInteger applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public HashMap<XBee64BitAddress, HashMap<Integer, EndPoint>> getRemoteEndPoints() {
        return remoteEndPoints;
    }

    public void setRemoteEndPoints(HashMap<XBee64BitAddress, HashMap<Integer, EndPoint>> remoteEndPoints) {
        this.remoteEndPoints = remoteEndPoints;
    }

    public HashMap<Integer, EndPoint> getLocalEndpoints() {
        return localEndpoints;
    }

    public void setLocalEndpoints(HashMap<Integer, EndPoint> localEndpoints) {
        this.localEndpoints = localEndpoints;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    public EndPoint getRemoteEndPoint(XBee64BitAddress address, int id){

        return remoteEndPoints.get(address).get(id);

    }

    public EndPoint getLocalEndPoint(int id){

        return localEndpoints.get(id);
    }

    public abstract void start() throws XBeeException;
    public abstract void stop();
    public abstract void send() throws XBeeException;
    public abstract void read();
    public abstract void discover();


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

