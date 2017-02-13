package com.twojr.protocol.aps;

import com.twojr.protocol.Attribute;
import com.twojr.toolkit.JData;
import com.twojr.toolkit.JIdentity;
import com.twojr.toolkit.JInteger;


import java.util.ArrayList;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class EndPoint extends JData{

    private JInteger jId;
    private ArrayList<Attribute> attributes;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public EndPoint(){

    }

    public EndPoint(String name, JInteger jId, ArrayList<Attribute> attributes) {
        super(jId.getValue(), name, 1);
        this.jId = jId;
        this.attributes = attributes;
    }

//==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public JInteger getjId() {
        return jId;
    }

    public void setjId(JInteger jId) {
        this.jId = jId;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================


    public Attribute getAttribute(int i){
        if(attributes != null) {
            if(attributes.size() > i && i > -1) {
                return attributes.get(i);
            }
        }

        return null;
    }

    public void addAttribute(Attribute attribute){
        if(attributes != null) {

            attributes.add(attribute);

        }

    }

    @Override
    public String print() {
        return null;
    }

    @Override
    public byte[] toByte() {
        return new byte[0];
    }

    @Override
    public byte[] compress() {
        return new byte[0];
    }

    @Override
    public int getSavings() {
        return 0;
    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

