package com.twojr.protocol.devices;

/**
 * Created by rcunni002c on 4/7/2017.
 */
public enum ATCommands {

    CHANNEL("CH"),
    PAN_ID("ID"),
    DEST_ADDRS_HIGH("DH"),
    DEST_ADDRS_LOW("DL"),
    SRC_ADDRS("MY"),
    DISC_OPTS("NO"),
    ACK_FAILS("EA"),
    CCA_FAILS("EC"),
    FW("VR"),
    HW("HV"),
    RSSI("DB");

    private String command;

     ATCommands(String command){
         this.command = command;
     }

    public String getCommand(){

        return command;

    }

}
