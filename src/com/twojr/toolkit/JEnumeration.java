package com.twojr.toolkit;

import java.nio.ByteBuffer;
import java.util.Enumeration;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JEnumeration extends JData {


    private byte[] value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JEnumeration() {
    }

    public JEnumeration(int id, String name, int size, byte[] value) {
        super(id, name, size);
        this.value = value;
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] toByte() {

        return value;

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

        String output = "Enumeration: ";

        output += value;

        output += "\n";

        return output;

    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/