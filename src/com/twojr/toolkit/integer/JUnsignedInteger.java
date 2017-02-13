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

        super(UNSIGNED_EIGHT_BIT_INT, UNSIGNED_INTEGER, EIGHT_BIT, value);

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

        setId(id);
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
