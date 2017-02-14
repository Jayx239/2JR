package com.twojr.protocol.network;

import com.twojr.toolkit.JString;
import com.twojr.toolkit.integer.JSignedInteger;

/**
 * Created by Jason on 2/14/2017.
 */
public class EndDevice  extends TwoJRDevice {

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public EndDevice() {

    }

    public EndDevice(JSignedInteger applicationVersion, TwoJRRadioListener radioListener, JString moduleId, JString manufacturer) {
        super(applicationVersion,radioListener,moduleId,manufacturer);
    }

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
        super.start();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public void send() {
        super.send();
    }

    @Override
    public void read() {
        super.read();
    }

    @Override
    public void discover() {
        super.discover();
    }

    @Override
    public String print() {
        return super.print();
    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/

