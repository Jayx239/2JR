package com.twojr.toolkit;

import java.util.ArrayList;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JArray extends JData{

    private JData[] value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JArray() {
    }

    public JArray(int id, String name, JDataSizes size, JData[] value) {
        super(id, name, size);
        this.value = value;
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public JData[] getValue() {
        return value;
    }

    public void setValue(JData[] value) {
        this.value = value;
    }


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] compress() {
        return new byte[0];
    }

    @Override
    public int getSavings() {
        return 0;
    }

    @Override
    public String print() {

        String output = "";

        for(JData data : value){

            output += data.print();

        }

        return output;

    }

    @Override
    public byte[] toByte() {

        int size = 0;

        for(JData data : value){

            size += data.getSize();

        }

        byte bytes[] = new byte[size];
        int arrayIndex = 0;

        for(JData data : value){

            byte[] dataBytes = data.toByte();

            for(int i = 0; i < data.getSize(); i++){

                bytes[arrayIndex] = dataBytes[i];

                arrayIndex++;

            }

        }

        return bytes;
    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
