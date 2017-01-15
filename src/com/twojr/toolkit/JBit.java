package com.twojr.toolkit;

import java.nio.ByteBuffer;
import java.time.temporal.ValueRange;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JBit extends JData{

    private boolean value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JBit(){

    }

    public JBit(int id, String name, int size, boolean value) {
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
        char valueAsChar = (char) (value ? 0x01 : 0x00);
        ByteBuffer.wrap(byteArray).putChar(valueAsChar);
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
        return value ? "1" : "0";
    }



    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
