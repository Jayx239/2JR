package com.twojr.toolkit;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JBit extends JData{
    public static final String BIT_NAME = "Bit";
    private boolean value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JBit(){
        //Is there a reason this is empty?
    }

    public JBit(String name, JDataSizes size, boolean value) {
        super(DataTypes.EIGHT_BIT_DATA, name, size);
        if(name == null){
            throw new IllegalArgumentException("JBit input name cannot be null");
        }
        if(size == null){
            throw new IllegalArgumentException("JBit input size cannot be null");
        }
        this.value = value;
    }

    public JBit(byte[] bitByte) {
        super(DataTypes.EIGHT_BIT_DATA,BIT_NAME,bitByte.length);
        if(bitByte == null){
            throw new IllegalArgumentException("JBit input byteArray cannot be null");
        }
        setValue(bitByte);
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public void setValue(byte[] bitByte) {
        if(bitByte[0] == (byte) 0)
            setValue(false);
        else
            setValue(true);
    }
    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] toByte() {
        byte[] byteArray = new byte[getSize()];
        int retval = (value ? 0x01 : 0x00);
        byteArray[0] = (byte) retval;
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
        return value ? "1" : "0";
    }



    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
