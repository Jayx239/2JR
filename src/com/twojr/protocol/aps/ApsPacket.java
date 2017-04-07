package com.twojr.protocol.aps;

import com.twojr.protocol.Command;
import com.twojr.protocol.Packet;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;

import java.lang.reflect.Array;
import java.util.Arrays;

import static com.twojr.protocol.aps.IApsPacket.apsCommands.*;


/**
 * Created by rcunni002c on 11/17/2016.
 */
public class ApsPacket extends Packet implements IApsPacket {

    private apsCommands commandFrame;
    private EndPoint endPoint;
    private int attributeCtrlLength;
    private AttributeControl attrCtrl;
    private LengthControl lengthControl;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public ApsPacket() {
    }

    public ApsPacket(JInteger sequenceNumber, byte[] payload, apsCommands commandFrame, EndPoint endPoint, AttributeControl attrCtrl, LengthControl lengthControl) {

        super(sequenceNumber, payload);

        this.commandFrame = commandFrame;
        this.endPoint = endPoint;
        this.attrCtrl = attrCtrl;
        this.lengthControl = lengthControl;
        this.attributeCtrlLength = attrCtrl.getSize();

    }



    public ApsPacket(JInteger sequenceNumber, byte[] payload, apsCommands commandFrame, EndPoint endPoint, AttributeControl attrCtrl) {
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

        this.commandFrame = getApsCommand(data[count]);
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

    public apsCommands getCommandFrame() {
        return commandFrame;
    }

    public void setCommandFrame(apsCommands commandFrame) {
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
        int count = 0;

        if(attrCtrl.isLengthControl()){

            data = new byte[getPayload().length + attributeCtrlLength*2 +4];

        }else{

            data = new byte[getPayload().length + attributeCtrlLength + 4];

        }

        data[count++] = getSequenceNumber().toByte()[0];

        data[count++] = commandFrame.getId();

        data[count++] = endPoint.toByte()[0];

        data[count++] = (byte)attributeCtrlLength;


        for(int i  = count; i < 4 + attributeCtrlLength; i++){

            data[count++] = attrCtrl.toByte()[i-4];

        }

        if(attrCtrl.isLengthControl()){

            for(int i  = count; i < 4 + attributeCtrlLength *2; i++){

                data[count++] = lengthControl.toByte()[i- (4 + attributeCtrlLength)];

            }

        }

        return data;

    }

    @Override
    public int getSize() {

        if(attrCtrl.isLengthControl()){

            return getPayload().length + getAttrCtrl().getSize()*2+ 4;

        }else {

            return getPayload().length + getAttrCtrl().getSize() + 4;

        }

    }

    @Override
    public String print() {

        String str = "";

        str += commandFrame.getId();
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

    public apsCommands getApsCommand(byte id){

        switch (id){

            case 0x00:
                return READ;
            case 0x01:
                return READ_RESPONSE;
            case 0x02:
                return WRITE;
            case 0x03:
                return WRITE_RESPONSE;
            case 0x04:
                return DISCOVER;
            case 0x05:
                return  DISCOVER_RESPONSE;
            case 0x06:
                return CHECK_IN;
            case 0x07:
                return  CHECK_IN_RESPONSE;
            case 0x08:
                return WAKE_DEVICE;
            case 0x09:
                return WAKE_DEVICE_RESPONSE;
            case 0x0A:
                return SLEEP_DEVICE;
            case 0x0B:
                return SLEEP_DEVICE_RESPONSE;
            default:
                return MANF;


        }

    }

}/*********************************************END OF FILE*************************************************************/

