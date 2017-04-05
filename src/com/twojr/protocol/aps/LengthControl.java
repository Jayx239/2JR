package com.twojr.protocol.aps;

import com.twojr.protocol.Attribute;
import com.twojr.toolkit.JBitmap;
import com.twojr.toolkit.JDataSizes;

import java.util.HashMap;

/**
 * Created by rcunni002c on 2/12/2017.
 */
public class LengthControl extends JBitmap{

    private HashMap<Integer,Attribute> attributeMap;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public LengthControl(){

    }

    public LengthControl(JDataSizes dataSize, HashMap<Integer, Attribute> attributeMap) {
        super(dataSize);
        this.attributeMap = attributeMap;
    }

    public LengthControl(byte[] data){

        super(data);
        this.attributeMap = null;

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


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

