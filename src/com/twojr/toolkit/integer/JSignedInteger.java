package com.twojr.toolkit.integer;

import com.twojr.toolkit.JInteger;

import java.nio.ByteBuffer;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JSignedInteger extends JInteger {

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JSignedInteger(){};
    public JSignedInteger(int id, String name, int size, int value) {
        super(id, name, size, value);
    }


    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================


    @Override
    public byte[] toByte() {
        byte[] byteArray = new byte[getSize()];
        ByteBuffer.wrap(byteArray).putInt(getValue());
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
    public String print() { return Integer.toString(getValue()); }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
