package com.twojr.toolkit;

import java.nio.ByteBuffer;
import java.util.Enumeration;

import static com.twojr.toolkit.DataTypes.*;
import static com.twojr.toolkit.JDataSizes.*;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JEnumeration extends JData {

    public static final String ENUMERATION = "Enumeration";
    private byte[] value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JEnumeration() {

        super(EIGHT_BIT_ENUMERATION, ENUMERATION, 1);

    }

    public JEnumeration(JDataSizes size, byte[] value) {

        super(EIGHT_BIT_ENUMERATION, ENUMERATION, 1);

        int id;
        switch (size){

            case EIGHT_BIT:
                id = EIGHT_BIT_ENUMERATION;
                break;

            case SIXTEEN_BIT:
                id = SIXTEEN_BIT_ENUMERATION;
                break;

            default:
                id = EIGHT_BIT_ENUMERATION;
                setSize(1);
                break;

        }

        setId(id);

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