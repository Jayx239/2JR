package com.twojr.protocol.network;

import com.twojr.toolkit.JString;
import com.twojr.toolkit.integer.JSignedInteger;

/**
 * Created by Jason on 2/14/2017.
 */
public abstract class TwoJRDevice implements IDevice {

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public TwoJRDevice() {

    }

    public TwoJRDevice(JSignedInteger applicationVersion, TwoJRRadioListener radioListener, JString moduleId, JString manufacturer) {

    }

    // =================================================================================================================
    // Instance variables
    // =================================================================================================================

    private JSignedInteger applicationVersion;
    private TwoJRRadioListener radioListener;
    private JString moduleId;
    private JString manufacturer;

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public JSignedInteger getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(JSignedInteger applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public TwoJRRadioListener getRadioListener() {
        return radioListener;
    }

    public void setRadioListener(TwoJRRadioListener radioListener) {
        this.radioListener = radioListener;
    }

    public JString getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(JString manufacturer) {
        this.manufacturer = manufacturer;
    }

    public JString getModuleId() {
        return moduleId;
    }

    public void setModuleId(JString moduleId) {
        this.moduleId = moduleId;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
