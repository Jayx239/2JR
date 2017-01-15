package com.twojr.toolkit;

import java.nio.ByteBuffer;

/**
 * Created by rcunni002c on 11/17/2016.
 * Updated 1/15/2016 by Jason Gallagher
 */
public class JDouble extends JData {

    private double value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JDouble() {
    }

    public JDouble(int id, String name, int size, double value) {
        super(id, name, size);
        this.value = value;
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


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] toByte() {
        byte[] byteArray = new byte[DataTypes.dataSizeMap.get(DataTypes.DOUBLE_PRECISION);
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
