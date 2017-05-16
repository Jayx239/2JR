package com.twojr.protocol.aps;

import com.twojr.protocol.Attribute;
import com.twojr.toolkit.DataFactory;
import com.twojr.toolkit.JData;
import com.twojr.toolkit.JIdentity;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;


import java.util.ArrayList;
import java.util.Arrays;

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

    public EndPoint(byte id){

        this.jId = new JUnsignedInteger(new byte[] {id});
        this.attributes = new ArrayList<>();
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

        String str = "";

        str += "ID: " + getId() + '\n';

        for(Attribute attribute : attributes){

            str += attribute.getName() + ": " + attribute.getData().print() + '\n';

        }

        return str;

    }

    @Override
    public byte[] toByte() {

        byte[] output = new byte[1 + attributes.size()];
        output[0] = jId.toByte()[0];
        for(int i=0; i<attributes.size(); i++ ) {
            output[i+1] = (byte) attributes.get(i).getData().getId();
        }

        return jId.toByte();
    }

    @Override
    public byte[] compress() {
        return new byte[0];
    }

    @Override
    public int getSavings() {
        return 0;
    }

    public ArrayList<Attribute> getAttributes(AttributeControl attributeControl){

        ArrayList<Attribute> attributes = new ArrayList<>();

        for(int i = 0; i < attributeControl.getSize() * 8; i++){

            if(attributeControl.getBitValue(i)){

                attributes.add(this.attributes.get(i));
            }

        }

        return attributes;

    }

    public void setAttributes(AttributeControl attributeControl, byte[] payload){

        int count = 0;
        for(int i = 0; i < attributeControl.getSize() * 8; i++){

            if(attributeControl.getBitValue(i)){

                Attribute attribute = this.attributes.get(i);

                JData data = DataFactory.getData(attribute.getData().getId(),Arrays.copyOfRange(payload,count,count + attribute.getData().getSize()));

                this.attributes.get(i).setData(data);

                count += attribute.getData().getSize();

            }

        }



    }
    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

