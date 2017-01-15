package com.twojr.toolkit;

import java.nio.ByteBuffer;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JBoolean extends JData{

    private boolean value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JBoolean(){

    }

    public JBoolean(int id, String name, int size, boolean value) {
        super(id, name, size);
        this.value = value;
    }


    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] toByte() {
        byte[] byteArray = new byte[getSize()];
        ByteBuffer.wrap(byteArray).putInt(value ? 1 : 0);
        return byteArray;
    }

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
        return value ? "true" : "false";
    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
