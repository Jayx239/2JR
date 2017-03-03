package com.twojr.protocol.network;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

/**
 * Created by Jason on 2/14/2017.
 */
public class TwoJRRadioListener implements Runnable{

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public TwoJRRadioListener(TwoJRDevice device) {
        this.device = device;
        runTerminate = false;
    }


    // =================================================================================================================
    // Instance variables
    // =================================================================================================================

    private TwoJRDevice device;
    private boolean runTerminate;
    private boolean discoveryMode;
    private boolean listeningMode;
    private boolean sendMode;
    private boolean readMode;
    private int timeout = 100;
    ByteInputStream streamListener;
    ByteOutputStream streamWriter;

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public TwoJRDevice getDevice() {
        return device;
    }

    public boolean isRunTerminate() {
        return runTerminate;
    }

    public boolean isDiscoveryMode() {
        return discoveryMode;
    }

    public boolean isSendMode() {
        return sendMode;
    }

    public boolean isReadMode() {
        return readMode;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public void run() {
        runTerminate = false;
        while(!runTerminate){
            if(listeningMode) {
                sendMode = true;


            }
            if(sendMode) {

                sendMode = false;
                readMode = true;
            }
            if(readMode) {
                //byte


                }
            }

        try{

            Thread.sleep(1);
        }
        catch(InterruptedException ex){

        }
    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================
    public void read() {

    }
    //private sendPacket()
} /*********************************************END OF FILE*************************************************************/
