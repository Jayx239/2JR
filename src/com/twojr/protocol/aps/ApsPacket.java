package com.twojr.protocol.aps;

import com.twojr.protocol.Command;
import com.twojr.protocol.Packet;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;


/**
 * Created by rcunni002c on 11/17/2016.
 */
public class ApsPacket extends Packet {

    private Command commandFrame;
    private EndPoint endPoint;
    private AttributeControl attrCtrl;
    private LengthControl lengthControl;



    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public ApsPacket() {
    }

    public ApsPacket(JInteger sequenceNumber, byte[] payload, Command commandFrame, EndPoint endPoint, AttributeControl attrCtrl, LengthControl lengthControl) {
        super(sequenceNumber, payload);
        this.commandFrame = commandFrame;
        this.endPoint = endPoint;
        this.attrCtrl = attrCtrl;
        this.lengthControl = lengthControl;
    }

    public ApsPacket(JInteger sequenceNumber, byte[] payload, Command commandFrame, EndPoint endPoint, AttributeControl attrCtrl) {
        super(sequenceNumber, payload);
        this.commandFrame = commandFrame;
        this.endPoint = endPoint;
        this.attrCtrl = attrCtrl;
    }

    public ApsPacket(byte[] data){

        int payloadSize;

        JUnsignedInteger sequenceNumber = new JUnsignedInteger(new byte[]{data[0]});
        this.commandFrame = Command.createCommand(data[1]);
        this.endPoint = new EndPoint(data[2]);
        this.attrCtrl = new AttributeControl(new byte[]{data[3]});

        if(attrCtrl.isLengthControl()){

            this.lengthControl = new LengthControl(new byte[]{data[4]});
            payloadSize = 4 - data.length;


        }else {

            payloadSize = 3 - data.length;

        }

        byte[] payload = new byte[4 - data.length];

        for(int i  = 0; i < payloadSize + 1; i++){

            payload[i] = data[i+ data.length - payloadSize];

        }

    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public Command getCommandFrame() {
        return commandFrame;
    }

    public void setCommandFrame(Command commandFrame) {
        this.commandFrame = commandFrame;
    }

    public AttributeControl getAttrCtrl() {
        return attrCtrl;
    }

    public void setAttrCtrl(AttributeControl attrCtrl) {
        this.attrCtrl = attrCtrl;
    }

    public EndPoint getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(EndPoint endPoint) {
        this.endPoint = endPoint;
    }


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] getPayload(){

        return new byte[0];

    }

    @Override
    public String print() {
        return null;
    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

