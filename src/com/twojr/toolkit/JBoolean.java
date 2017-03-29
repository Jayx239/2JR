package com.twojr.toolkit;

import static com.twojr.toolkit.JDataSizes.*;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JBoolean extends JData{

    public static final String BOOLEAN_NAME = "Boolean";

    private boolean value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JBoolean(){
        super(DataTypes.BOOLEAN, BOOLEAN_NAME, EIGHT_BIT);
    }

    public JBoolean(boolean value) {
        super(DataTypes.BOOLEAN, BOOLEAN_NAME, EIGHT_BIT);
        this.value = value;
    }

    public JBoolean(byte[] boolByte) {
        super(DataTypes.BOOLEAN,BOOLEAN_NAME,EIGHT_BIT);
        if(boolByte == null){
            throw new IllegalArgumentException("JBoolean input byte array cannot be null");
        }
        setValue(boolByte);
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

    public void setValue(byte[] boolByte) {

        if(boolByte[0] == (byte) 0)
            value = false;
        else
            value = true;
    }
    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] toByte() {
        byte[] byteArray = new byte[getSize()];
        String valueAsChar = (char) (value ? 0x01 : 0x00) + "";
        byteArray = valueAsChar.getBytes();
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
        return value ? "true" : "false";
    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
