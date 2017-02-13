package com.twojr.toolkit;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public abstract class JInteger extends JData {

    private int value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JInteger(){

    }

    public JInteger(int id, String name, int size, int value) {
        super(id, name, size);
        this.value = value;
    }

    public JInteger(int id, String name, JDataSizes size, int value) {
        super(id, name, size);
        this.value = value;
    }


    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================


    @Override
    public abstract byte[] toByte();
    @Override
    public abstract String print();
    @Override
    public abstract byte[] compress();      //Compresses data into the smallest format possible
    @Override
    public abstract int getSavings();       //Reports the numbers of bytes saved by compression
    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
