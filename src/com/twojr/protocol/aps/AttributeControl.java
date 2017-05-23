package com.twojr.protocol.aps;

import com.twojr.protocol.Attribute;
import com.twojr.toolkit.JBitmap;
import com.twojr.toolkit.JDataSizes;

import java.util.HashMap;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class AttributeControl extends JBitmap{

    private HashMap<Integer,Attribute> attributeMap;
    private boolean lengthControl;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public AttributeControl(){

    }

    public AttributeControl(JDataSizes dataSize, HashMap<Integer, Attribute> attributeMap, boolean lengthControl) {
        super(dataSize);
        this.attributeMap = attributeMap;
        this.lengthControl = lengthControl;
    }

    public AttributeControl(byte[] data,EndPoint endPoint){

        super(data);


        attributeMap = new HashMap<>();

        for(int i = 0; i < getSize(); i++){

            attributeMap.put(i,endPoint.getAttribute(i));

        }

        this.lengthControl = this.getBitValue(getSize()-1,7);

    }

    public AttributeControl(byte[] data){
        super(data);
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public HashMap<Integer, Attribute> getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(HashMap<Integer, Attribute> attributeMap) {
        this.attributeMap = attributeMap;
        toByte();
    }

    public boolean isLengthControl() {
        return lengthControl;
    }

    public void setLengthControl(boolean lengthControl) {
        this.lengthControl = lengthControl;
    }

    @Override
    public byte[] toByte() {
        /*for(int i=0; i<getSize()*8; i++) {
            if (attributeMap.containsKey(i));
                super.setBitValue(true, i%8, i > 0 ? i/8 : 0);
        }*/
        return super.toByte();
    }
    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

