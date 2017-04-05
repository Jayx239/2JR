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

    public AttributeControl(byte[] data){

        super(data);
        this.attributeMap = null;
        this.lengthControl = getBitValue(getSize()*8 -1);

    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public HashMap<Integer, Attribute> getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(HashMap<Integer, Attribute> attributeMap) {
        this.attributeMap = attributeMap;
    }

    public boolean isLengthControl() {
        return lengthControl;
    }

    public void setLengthControl(boolean lengthControl) {
        this.lengthControl = lengthControl;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

