package com.twojr.twojr.devices;

import com.twojr.toolkit.JIdentity;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public abstract class TwoJRDevice extends JIdentity {

    private TwoJRRadioListener radioListener;
    private JString modelID;
    private JString manufacturer;
    private JInteger applicationVersion;


    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public TwoJRDevice(){

    }

    public TwoJRDevice(int id, String name, TwoJRRadioListener radioListener, JString modelID, JString manufacturer, JInteger applicationVersion) {
        super(id, name);
        this.radioListener = radioListener;
        this.modelID = modelID;
        this.manufacturer = manufacturer;
        this.applicationVersion = applicationVersion;
    }


    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public TwoJRRadioListener getRadioListener() {
        return radioListener;
    }

    public void setRadioListener(TwoJRRadioListener radioListener) {
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


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    public abstract void start();
    public abstract void close();
    public abstract void send();
    public abstract void read();
    public abstract void discover();


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

