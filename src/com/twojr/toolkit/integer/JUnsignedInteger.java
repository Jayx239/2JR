package com.twojr.toolkit.integer;

import com.twojr.toolkit.DataTypes;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.JInteger;

import static com.twojr.toolkit.DataTypes.*;
import static com.twojr.toolkit.JDataSizes.*;


/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JUnsignedInteger extends JInteger{

    public static final String UNSIGNED_INTEGER = "Unsigned Integer";
    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JUnsignedInteger(){

        setId(UNSIGNED_EIGHT_BIT_INT);
        setName(UNSIGNED_INTEGER);
        setSize(EIGHT_BIT);

    }

    public JUnsignedInteger(JDataSizes size, int value) {

        super(UNSIGNED_EIGHT_BIT_INT, UNSIGNED_INTEGER, size, value);

        setId(computeId(size));
    }

    public JUnsignedInteger(byte[] value) {
        setValue(value);
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================
    @Override
    public int getValue() {
        return super.getValue() < 0 ? 0 : super.getValue();
    }

    @Override
    public void setValue(int value) {
        super.setValue(value);
        setName(UNSIGNED_INTEGER);
        setId(computeId(getSize()));

    }

    public void setValue(byte[] byteInt) {
        int value = 0;
        for(int i=0; i<byteInt.length; i++) {
            value+= (int)((byteInt[i]&0xFF) << (i*8));
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
    public String print() {
        String val = Integer.toString(getValue());
        System.out.println(val);
        return val;
    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

    private int computeId(JDataSizes size) {

        int id;

        switch (size) {

            case EIGHT_BIT:
                id = UNSIGNED_EIGHT_BIT_INT;
                break;

            case SIXTEEN_BIT:
                id = UNSIGNED_SIXTEEN_BIT_INT;
                break;

            case TWENTY_FOUR_BIT:
                id = UNSIGNED_TWENTY_FOUR_BIT_INT;
                break;

            case THIRTY_TWO_BIT:
                id = UNSIGNED_THIRTY_TWO_BIT_INT;
                break;

            case FORTY_BIT:
                id = UNSIGNED_FORTY_BIT_INT;
                break;

            case FORTY_EIGHT_BIT:
                id = UNSIGNED_FORTY_EIGHT_BIT_INT;
                break;

            case FIFTY_SIX_BIT:
                id = UNSIGNED_FIFTY_SIX_BIT_INT;
                break;

            case SIXTY_FOUR_BIT:
                id = UNSIGNED_SIXTY_FOUR_BIT_INT;
                break;

            default:
                id = UNSIGNED_EIGHT_BIT_INT;
                setSize(1);
                break;

        }

        return id;
    }

    private int computeId(int size) {

        int id;

        switch (size){

            case 1:
                id = UNSIGNED_EIGHT_BIT_INT;
                break;

            case 2:
                id = UNSIGNED_SIXTEEN_BIT_INT;
                break;

            case 3:
                id = UNSIGNED_TWENTY_FOUR_BIT_INT;
                break;

            case 4:
                id = UNSIGNED_THIRTY_TWO_BIT_INT;
                break;

            case 5:
                id = UNSIGNED_FORTY_BIT_INT;
                break;

            case 6:
                id = UNSIGNED_FORTY_EIGHT_BIT_INT;
                break;

            case 7:
                id = UNSIGNED_FIFTY_SIX_BIT_INT;
                break;

            case 8:
                id = UNSIGNED_SIXTY_FOUR_BIT_INT;
                break;

            default:
                id = UNSIGNED_EIGHT_BIT_INT;
                setSize(1);
                break;

        }

        return id;
    }
} /*********************************************END OF FILE*************************************************************/
