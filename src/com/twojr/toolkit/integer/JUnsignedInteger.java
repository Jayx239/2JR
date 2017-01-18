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
    public String print() {
        String val = Integer.toString(getValue());
        System.out.println(val);
        return val;
    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
