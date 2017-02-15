package com.twojr.toolkit;

import java.util.ArrayList;

import static com.twojr.toolkit.DataTypes.*;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JArray extends JData{

    public static final String ARRAY_NAME = "Array";

    private JData[] value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JArray() {

        super(ARRAY, ARRAY_NAME, 0);

    }

    public JArray(JData[] value) {

        super(ARRAY, ARRAY_NAME, 0);
        this.value = value;
        setSize(computeSize());
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public JData[] getValue() {
        return value;
    }

    public void setValue(JData[] value) {

        this.value = value;
        setSize(computeSize());

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

        int size = getSize();

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

    private int computeSize() {
        int size = 0;

        for(JData data : value){

            size += data.getSize();

        }

        return size;
    }

} /*********************************************END OF FILE*************************************************************/
