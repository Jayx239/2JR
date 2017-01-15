package com.twojr.twojr.protocol.aps;

import com.twojr.toolkit.JInteger;
import com.twojr.twojr.protocol.Attribute;
import com.twojr.twojr.protocol.Command;
import com.twojr.twojr.protocol.Packet;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class ApsPacket extends Packet{

    private Command commandFrame;
    private EndPoint endPoint;
    private AttributeControl attrCtrl;


    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public ApsPacket() {
    }

    public ApsPacket(JInteger sequenceNumber, byte[] payload, Command commandFrame, EndPoint endPoint, AttributeControl attrCtrl) {
        super(sequenceNumber, payload);
        this.commandFrame = commandFrame;
        this.endPoint = endPoint;
        this.attrCtrl = attrCtrl;
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

