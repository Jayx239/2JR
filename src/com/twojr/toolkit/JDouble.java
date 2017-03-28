package com.twojr.toolkit;

import java.nio.ByteBuffer;

import static com.twojr.toolkit.DataTypes.*;
import static com.twojr.toolkit.JDataSizes.*;

/**
 * Created by rcunni002c on 11/17/2016.
 * Updated 1/15/2016 by Jason Gallagher
 */
public class JDouble extends JData {

    public static final String DOUBLE = "Double";

    private double value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JDouble() {
        super(DOUBLE_PRECISION, DOUBLE, SIXTY_FOUR_BIT);
    }

    public JDouble(double value) {
        super(DOUBLE_PRECISION, DOUBLE, SIXTY_FOUR_BIT);
        this.value = value;
    }

    public JDouble(byte[] byteDouble) {
        super(DOUBLE_PRECISION, DOUBLE, SIXTY_FOUR_BIT);
        if(byteDouble == null){
            throw new IllegalArgumentException("JData input byteDouble cannot be null");
        }
        setValue(byteDouble);
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public double getValue() {
        return value;
    }

    public void setValue(double value) {

        this.value = value;
    }

    public void setValue(byte[] value) {
        setValue(ByteBuffer.wrap(value).getDouble());
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] toByte() {
        byte[] byteArray = new byte[getSize()];
        ByteBuffer.wrap(byteArray).putDouble(value);
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
        return Double.toString(value);
    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
