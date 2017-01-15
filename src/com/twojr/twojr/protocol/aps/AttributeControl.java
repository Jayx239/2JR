package com.twojr.twojr.protocol.aps;

import com.twojr.toolkit.JBitmap;
import com.twojr.twojr.protocol.Attribute;

import java.util.HashMap;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class AttributeControl extends JBitmap{

    private HashMap<Integer,Attribute> attributeMap;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public AttributeControl(){

    }

    public AttributeControl(int id, String name, int noBits, HashMap<Integer, Attribute> attributeMap) {
        super(id, name, noBits);
        this.attributeMap = attributeMap;
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

