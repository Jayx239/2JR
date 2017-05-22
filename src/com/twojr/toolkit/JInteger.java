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
        if(name == null){
            throw new IllegalArgumentException("JInteger input name cannot be null");
        }
        this.value = value;
    }

    public JInteger(int id, String name, JDataSizes size, int value) {
        super(id, name, size);
        if(name == null){
            throw new IllegalArgumentException("JInteger input name cannot be null");
        }
        if(size == null){
            throw new IllegalArgumentException("JInteger input size cannot be null");
        }
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
    public byte[] compress() {
        byte[] byteVal = toByte();
        int len = byteVal.length;
        for(int i=byteVal.length-1; i>=0; i--) {
            if(byteVal[i] != (byte) 0x00)
                break;
            len--;
        }

        byte[] output = new byte[len];
        for(int i=0; i<len; i++) {
            output[i] = byteVal[i];
        }
        return output;
    }

    @Override
    public abstract int getSavings();       //Reports the numbers of bytes saved by compression
    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
