package com.twojr.protocol.devices.router;

import com.twojr.protocol.devices.TwoJRDevice;
import com.twojr.protocol.devices.TwoJrDataListener;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.JString;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class Router extends TwoJRDevice{


//==================================================================================================================
// Constructor(s)
//==================================================================================================================

    public Router(String port, int baudRate, TwoJrDataListener radioListener, JString modelID, JString manufacturer, JInteger applicationVersion) {
        super(port, baudRate, radioListener, modelID, manufacturer, applicationVersion);
    }



//==================================================================================================================
// Getter(s) & Setter(s)
//==================================================================================================================


//==================================================================================================================
// Public Functions(s)
//==================================================================================================================

    public void route(){

    }


    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }


    @Override
    public void send() {

    }

    @Override
    public void read() {

    }

    @Override
    public void discover() {

    }

//==================================================================================================================
// Private Functions(s)
//==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

