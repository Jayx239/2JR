package com.twojr.protocol.network;

import com.twojr.toolkit.*;
import com.twojr.toolkit.integer.JSignedInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;

import java.util.Map;
import java.util.Vector;

/**
 * Created by Jason on 2/14/2017.
 */
public class Router extends TwoJRDevice {

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public Router() {
        listeners = new Vector<TwoJRRadioListener>();
    }

    public Router(JSignedInteger applicationVersion, TwoJRRadioListener radioListener, JString moduleId, JString manufacturer) {
        super(applicationVersion,radioListener,moduleId,manufacturer);
        listeners = new Vector<TwoJRRadioListener>();
    }

    //==================================================================================================================
    // Instance variables
    //==================================================================================================================

    private NetworkPacket currentPacket;
    private Vector<TwoJRRadioListener> listeners;

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    @Override
    public JSignedInteger getApplicationVersion() {
        return super.getApplicationVersion();
    }

    @Override
    public void setApplicationVersion(JSignedInteger applicationVersion) {
        super.setApplicationVersion(applicationVersion);
    }

    @Override
    public TwoJRRadioListener getRadioListener() {
        return super.getRadioListener();
    }

    @Override
    public void setRadioListener(TwoJRRadioListener radioListener) {
        super.setRadioListener(radioListener);
    }

    @Override
    public JString getManufacturer() {
        return super.getManufacturer();
    }

    @Override
    public void setManufacturer(JString manufacturer) {
        super.setManufacturer(manufacturer);
    }

    @Override
    public JString getModuleId() {
        return super.getModuleId();
    }

    @Override
    public void setModuleId(JString moduleId) {
        super.setModuleId(moduleId);
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public void start() {
     //   radioListener
    }

    @Override
    public void close() {

    }

    @Override
    public void send() {

    }

    @Override
    public void read() {

    }

    @Override
    public void discover() {
        JUnsignedInteger sequenceNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT, 1);
        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT, IPacket.networkControlFlags.ROUTER.ordinal());
        JAddress macAddress = new JAddress(100202);
        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT, IPacket.networkLayerCommands.REJOIN.ordinal());


        NetworkPacket discoverPacket = new NetworkPacket(sequenceNumber,networkControl,macAddress,commandFrame);
        currentPacket = discoverPacket;
        send();
    }

    @Override
    public String print() {
        return "Router: ";
    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
