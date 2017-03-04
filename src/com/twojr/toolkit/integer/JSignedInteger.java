package com.twojr.toolkit.integer;

import com.twojr.toolkit.DataTypes;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.JInteger;

import java.nio.ByteBuffer;

import static com.twojr.toolkit.DataTypes.*;
import static com.twojr.toolkit.JDataSizes.*;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JSignedInteger extends JInteger {

    public static final String SIGNED_INTEGER = "Signed Integer";

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JSignedInteger(){

        setId(SIGNED_EIGHT_BIT_INT);
        setName(SIGNED_INTEGER);
        setSize(EIGHT_BIT);

    }
    public JSignedInteger(JDataSizes size, int value) {

        super(SIGNED_EIGHT_BIT_INT, SIGNED_INTEGER, size, value);

        setId(computeId(size));

    }

    public JSignedInteger(byte[] value) {
        setValue(value);
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    @Override
    public void setValue(int value) {
        super.setValue(value);
        setName(SIGNED_INTEGER);
        setId(computeId(getSize()));

    }

    public void setValue(byte[] byteInt) {
        int value = 0;
        for(int i=0; i<byteInt.length; i++) {
            value+= byteInt[i] << (i*8);
        }
        setSize(byteInt.length);
        setValue(value);
    }


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================


    @Override
    public byte[] toByte() {
        byte[] byteArray = new byte[getSize()];
        for(int i=0; i<getSize(); i++)
            byteArray[i] = (byte) ((this.getValue()>>(i*8))&0xff);
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

    private int computeId(JDataSizes size) {
        int id;

        switch (size){

            case EIGHT_BIT:
                id = SIGNED_EIGHT_BIT_INT;
                break;

            case SIXTEEN_BIT:
                id = SIGNED_SIXTEEN_BIT_INT;
                break;

            case TWENTY_FOUR_BIT:
                id = SIGNED_TWENTY_FOUR_BIT_INT;
                break;

            case THIRTY_TWO_BIT:
                id = SIGNED_THIRTY_TWO_BIT_INT;
                break;

            case FORTY_BIT:
                id = SIGNED_FORTY_BIT_INT;
                break;

            case FORTY_EIGHT_BIT:
                id = SIGNED_FORTY_EIGHT_BIT_INT;
                break;

            case FIFTY_SIX_BIT:
                id = SIGNED_FIFTY_SIX_BIT_INT;
                break;

            case SIXTY_FOUR_BIT:
                id = SIGNED_SIXTY_FOUR_BIT_INT;
                break;

            default:
                id = SIGNED_EIGHT_BIT_INT;
                setSize(1);
                break;

        }
        return id;
    }

    private int computeId(int size) {
        int id;

        switch (size){

            case 1:
                id = SIGNED_EIGHT_BIT_INT;
                break;

            case 2:
                id = SIGNED_SIXTEEN_BIT_INT;
                break;

            case 3:
                id = SIGNED_TWENTY_FOUR_BIT_INT;
                break;

            case 4:
                id = SIGNED_THIRTY_TWO_BIT_INT;
                break;

            case 5:
                id = SIGNED_FORTY_BIT_INT;
                break;

            case 6:
                id = SIGNED_FORTY_EIGHT_BIT_INT;
                break;

            case 7:
                id = SIGNED_FIFTY_SIX_BIT_INT;
                break;

            case 8:
                id = SIGNED_SIXTY_FOUR_BIT_INT;
                break;

            default:
                id = SIGNED_EIGHT_BIT_INT;
                setSize(1);
                break;

        }
        return id;
    }
} /*********************************************END OF FILE*************************************************************/
