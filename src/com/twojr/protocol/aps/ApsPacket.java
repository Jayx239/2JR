package com.twojr.protocol.aps;

import com.twojr.protocol.Command;
import com.twojr.protocol.Packet;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;

import java.lang.reflect.Array;
import java.util.Arrays;


/**
 * Created by rcunni002c on 11/17/2016.
 */
public class ApsPacket extends Packet implements  IApsPacket {

    private Command commandFrame;
    private EndPoint endPoint;
    private int attributeCtrlLength;
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
        this.attributeCtrlLength = attrCtrl.getSize();
    }

    public ApsPacket(JInteger sequenceNumber, byte[] payload, Command commandFrame, EndPoint endPoint, AttributeControl attrCtrl) {
        super(sequenceNumber, payload);
        this.commandFrame = commandFrame;
        this.endPoint = endPoint;
        this.attrCtrl = attrCtrl;
        this.attributeCtrlLength = attrCtrl.getSize();
    }

    public ApsPacket(byte[] data){

        int payloadSize;
        int count = 0;

        JUnsignedInteger sequenceNumber = new JUnsignedInteger(new byte[]{data[count]});
        setSequenceNumber(sequenceNumber);
        count++;

        this.commandFrame = Command.createCommand(data[count]);
        count++;

        this.endPoint = new EndPoint(data[count]);
        count++;

        this.attributeCtrlLength = data[count];
        count++;

        this.attrCtrl = new AttributeControl(Arrays.copyOfRange(data,4,attributeCtrlLength + count),endPoint);
        count += attributeCtrlLength;

        if(attrCtrl.isLengthControl()){

            this.lengthControl = new LengthControl(Arrays.copyOfRange(data, attributeCtrlLength + 5, attributeCtrlLength * 2 +5), endPoint);
            count += attributeCtrlLength;


        }

        payloadSize = data.length - count;

        byte[] payload = Arrays.copyOfRange(data,count, data.length);

        setPayload(payload);


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

    public LengthControl getLengthControl() {
        return lengthControl;
    }

    public void setLengthControl(LengthControl lengthControl) {
        this.lengthControl = lengthControl;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================


    @Override
    public byte[] toByte() {

        byte[] data;

        if(attrCtrl.isLengthControl()){

            data = new byte[getPayload().length + attributeCtrlLength*2 +4];

        }else{

            data = new byte[getPayload().length + attributeCtrlLength + 4];

        }

        data[0] = commandFrame.toByte()[0];
        data[1] = endPoint.toByte()[0];
        data[2] = (byte)attributeCtrlLength;


        if(attrCtrl.isLengthControl()){

            data[3] = lengthControl.toByte()[0];

            for(int i  = 4; i < data.length; i++){

                data[i] = getPayload()[i-4];

            }

        }else {

            for(int i  = 3; i < data.length; i++){

                data[i] = getPayload()[i-3];

            }

        }


        return data;

    }

    @Override
    public int getSize() {

        if(attrCtrl.isLengthControl()){

            return getPayload().length + 4;

        }else {

            return getPayload().length + 3;
        }

    }

    @Override
    public String print() {

        String str = "";

        str += commandFrame.print();
        str += endPoint.print();
        str += attrCtrl.print();

        if(attrCtrl.isLengthControl()){

            str += lengthControl.print();

        }

        str += getPayload();

        System.out.println(str);

        return str;

    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

