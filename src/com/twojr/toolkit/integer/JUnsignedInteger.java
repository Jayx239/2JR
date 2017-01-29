package com.twojr.toolkit.integer;

import com.twojr.toolkit.JInteger;
import java.nio.ByteBuffer;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JUnsignedInteger extends JInteger{

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JUnsignedInteger(){};
    public JUnsignedInteger(int id, String name, int size, int value) {
        super(id, name, size, value);
        //Any negative input for value is set to zero to keep this type unsigned
        if (value < 0) {
            super.setValue(0);
        }
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================
    @Override
    public int getValue() {
        return super.getValue() < 0 ? 0 : super.getValue();
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================


    @Override
    public byte[] toByte() {
        byte[] byteArray = new byte[getSize()];
        byteArray[0] = (byte) getValue();
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
        String val = Integer.toString(getValue());
        System.out.println(val);
        return val;
    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
