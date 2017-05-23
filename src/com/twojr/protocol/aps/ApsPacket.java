package com.twojr.protocol.aps;


import com.twojr.protocol.Attribute;
import com.twojr.protocol.Packet;
import com.twojr.toolkit.DataFactory;
import com.twojr.toolkit.DataTypes;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;
import org.w3c.dom.Attr;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static com.twojr.protocol.aps.IApsPacket.apsCommands.*;
import static com.twojr.toolkit.DataTypes.*;
import static com.twojr.toolkit.DataTypes.LONG_CHARACTER_STRING;
import static com.twojr.toolkit.DataTypes.LONG_OCTET_STRING;


/**
 * Created by rcunni002c on 11/17/2016.
 */
public class ApsPacket extends Packet implements IApsPacket {

    private apsCommands commandFrame;
    private EndPoint endPoint;
    private int attributeCtrlLength;
    private AttributeControl attrCtrl;
    private LengthControl lengthControl;
    private DataFactory dataFactory;
    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public ApsPacket() {
        dataFactory = new DataFactory();
    }

    public ApsPacket(JInteger sequenceNumber, byte[] payload, apsCommands commandFrame, EndPoint endPoint, AttributeControl attrCtrl, LengthControl lengthControl) {

        super(sequenceNumber, payload);

        this.commandFrame = commandFrame;
        this.endPoint = endPoint;
        this.attrCtrl = attrCtrl;
        this.lengthControl = lengthControl;
        this.attributeCtrlLength = attrCtrl.getSize();
        this.dataFactory = new DataFactory();
    }

    public ApsPacket(JInteger sequenceNumber, byte[] payload, apsCommands commandFrame, EndPoint endPoint, AttributeControl attrCtrl) {
        super(sequenceNumber, payload);
        this.commandFrame = commandFrame;
        this.endPoint = endPoint;
        this.attrCtrl = attrCtrl;
        this.attributeCtrlLength = attrCtrl.getSize();
        this.dataFactory = new DataFactory();
    }

    public ApsPacket(JInteger sequenceNumber, apsCommands commandFrame, EndPoint endPoint, AttributeControl attrCtrl) {
        super(sequenceNumber, new byte[0]);
        this.commandFrame = commandFrame;
        this.endPoint = endPoint;
        this.attrCtrl = attrCtrl;
        this.attributeCtrlLength = attrCtrl.getSize();
        this.dataFactory = new DataFactory();
        generatePayload();
    }

    public ApsPacket(JInteger sequenceNumber, apsCommands commandFrame, EndPoint endPoint, AttributeControl attrCtrl, LengthControl lengthControl) {

        super(sequenceNumber, new byte[0]);

        this.commandFrame = commandFrame;
        this.endPoint = endPoint;
        this.attrCtrl = attrCtrl;
        this.lengthControl = lengthControl;
        this.attributeCtrlLength = attrCtrl.getSize();
        this.dataFactory = new DataFactory();
        generatePayload();
    }

    public ApsPacket(byte[] data){
        this.dataFactory = new DataFactory();
        attributeCtrlLength = 0;

        if(data == null) {
            System.err.println("Invalid byte data for ApsPacket initialization");
            return;
        }
        int payloadSize;
        int count = 0;

        if(data.length <= 1) {
            System.err.println("Invalid byte data for ApsPacket initialization");
            return;
        }

        JUnsignedInteger sequenceNumber = new JUnsignedInteger(new byte[]{data[count]});
        setSequenceNumber(sequenceNumber);
        count++;

        if(data.length <= count) {
            System.err.println("Invalid byte data for ApsPacket initialization");
            return;
        }
        this.commandFrame = getApsCommand(data[count]);
        count++;

        if(data.length <= count) {
            System.err.println("Invalid byte data for ApsPacket initialization");
            return;
        }
        this.endPoint = new EndPoint(data[count]);
        count++;

        if(data.length <= count) {
            System.err.println("Invalid byte data for ApsPacket initialization");
            return;
        }
        lengthControl = new LengthControl(new byte[]{(byte)data[count]},endPoint);
        //this.attributeCtrlLength = data[count];
        count++;

        if(data.length <= count) {
            System.err.println("Invalid byte data for ApsPacket initialization");
            return;
        }
        this.attrCtrl = new AttributeControl(Arrays.copyOfRange(data,4,1 + count),endPoint);
        count ++;

        if(data.length <= count) {
            System.err.println("Invalid byte data for ApsPacket initialization");
            return;
        }

        if(attrCtrl.isLengthControl()){
            this.lengthControl = new LengthControl(Arrays.copyOfRange(data, attributeCtrlLength + 5, attributeCtrlLength * 2 +5), endPoint);
            count += attributeCtrlLength;
        }

        payloadSize = data.length - count;

        if(payloadSize <= 0) {
            System.err.println("No payload for ApsPacket byte initialization");
            return;
        }

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

        if(getPayload() == null)
            setPayload(new byte[0]);

        if(!validData()) {
            System.err.println("ApsPacket invalid data");
            return null;
        }

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
        for(int i=0; i<getPayload().length;i++) {
            data[count++] = getPayload()[i];
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

        String str = "\n------------------------------\n" +
                     "Application Layer Packet\n";
        str += "------------------------------\n";

        str += "Command Frame: " + (commandFrame != null ? commandFrame : null) + "\n";
        str += "Endpoint: " + (endPoint != null ? endPoint.print() : "null") + "\n";
        str += "Attribute Control: " + (attrCtrl != null ? attrCtrl.print() : "null") + "\n";

        if(attrCtrl != null && attrCtrl.isLengthControl()){

            str += "Length Control: " + lengthControl.print() + "\n";

        }

        str += "\n------------------------------\nAttributes\n------------------------------\n";
        str += printPayloadContents();
        str += "\n------------------------------\nPayload as byte array\n------------------------------\n";
        str += printPayload(true);
        //System.out.println(str);

        return str;

    }

    public String printPayloadContents() {
        if (endPoint == null)
            return "null";
        ArrayList<Attribute> attributes = endPoint.getAttributes(attrCtrl);
        boolean lengthControl = attrCtrl != null && attrCtrl.isLengthControl();

        int payloadOffset = 0;
        String payloadString = "";
        byte[] payload = getPayload();

        for(Attribute attribute : attributes) {
            int dataId = attribute.getData().getId();
            //int length = attribute.getData().getSize();//;DataTypes.dataSizeMap.get(dataId);
            if(!attrCtrl.getAttributeMap().containsKey(dataId))
                continue;

            //if(lengthControl)
                //length = (int) payload[payloadOffset++];

            //byte[] stream = getByteSegment(payload,payloadOffset,attribute.getData().getSize());
            payloadString += attribute.print() + '\n';
            //payloadString += DataFactory.getData(dataId, stream).print() + '\n';
            //payloadOffset += length;
        }

        return payloadString;
    }

    byte[] getByteSegment(byte[] inputArray, int startIndex, int length) {
        if(length < 0) {
            System.err.println("Invalid parameters for byte segment");
            return null;
        }
        byte[] output = new byte[length];
        int outputIndex = 0;
        for(int i=0; i<length; i++) {
            output[i] = inputArray[i+startIndex];
        }

        return output;
    }

    public byte[] generatePayload() {
        if(attrCtrl == null)
            return new byte[0];

        ArrayList<Byte> payload = new ArrayList<Byte>();


        LengthControl lengthControl = getLengthControl();
        if(lengthControl == null) {
            byte[] lengthControlByte = new byte[1];
            lengthControlByte[0] = (byte) 0x00;
            int i=0;
            for(Attribute attr : attrCtrl.getAttributeMap().values()){//for (int i = 0; i < 8; i++) {
                if (attrCtrl.getBitValue(i) && isDataVariableSize(attr.getData().getId()))
                    lengthControlByte[0] += (0x01 << i);
                i++;
            }

            lengthControl = new LengthControl(lengthControlByte, endPoint);
            setLengthControl(lengthControl);
        }

        //for(int i=0; i<endPoint.getAttributes().size(); i++) {
            //if(!attrCtrl.getAttributeMap().containsKey(i))
                //continue;
        int bitIndex = 0;
        for(Attribute attribute : attrCtrl.getAttributeMap().values()) {
            if(attrCtrl.getBitValue(bitIndex)) {
                if (lengthControl.getBitValue(bitIndex))
                    payload.add((byte) attribute.getData().getSize());
                for (byte payloadByte : attribute.getData().toByte())
                    payload.add(payloadByte);
            }
            bitIndex = bitIndex == 0 ? 1 : bitIndex*2;

        }
        byte[] payloadArray = new byte[payload.size()];
        int i = 0;

        for(Object b : payload.toArray()) {
            payloadArray[i++] = (byte) b;
        }
        this.setPayload(payloadArray);
        return payloadArray;
    }

    public byte[] generatePayload(ArrayList<byte[]> bytes){

        int length = 0;

        for(byte[] array : bytes){

            length+= array.length;

        }

        byte[] payload = new byte[length];
        int count = 0;

        for(int i = 0; i < bytes.size(); i++){
            for(int j = 0; j < bytes.get(i).length; j++){

                payload[count] = bytes.get(i)[j];
                count++;
            }
        }

        return payload;
    }



    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

    private boolean isDataVariableSize(int dataType) {
        switch (dataType) {
            // bit map
            case EIGHT_BIT_MAP_DATA :
            case SIXTEEN_BIT_MAP_DATA:
            case TWENTY_FOUR_BIT_MAP_DATA:
            case THIRTY_TWO_BIT_MAP_DATA:
            case FORTY_BIT_MAP_DATA:
            case FORTY_EIGHT_BIT_MAP_DATA:
            case FIFTY_SIX_BIT_MAP_DATA:
            case SIXTY_FOUR_BIT_MAP_DATA:

                // Strings
            case OCTET_STRING :
            case CHARACTER_STRING :
            case LONG_OCTET_STRING :
            case LONG_CHARACTER_STRING :

                return true;
        }
        return false;
    }

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

    private boolean validData() {

        if(commandFrame == null)
            return false;

        if(endPoint == null)
            return false;

        if(commandFrame == null)
            return false;

        if(endPoint == null)
            return false;

        if(attrCtrl == null) {
            return false;
        }
        if(lengthControl == null ) {//&& attrCtrl.isLengthControl()) {
            return false;
        }
        return true;
    }

}/*********************************************END OF FILE*************************************************************/

