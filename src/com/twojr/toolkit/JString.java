package com.twojr.toolkit;

import static com.twojr.toolkit.DataTypes.*;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JString extends JData {

    public static final String STRING_NAME = "String";

    private String value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JString(){

        super(CHARACTER_STRING, STRING_NAME, 1);

        if(getSize() > 8){
            setId(LONG_CHARARACTER_STRING);
        }

    }

    public JString(JDataSizes size, String value) {

        super(CHARACTER_STRING, STRING_NAME, size);

        if(getSize() > 8){
            setId(LONG_CHARARACTER_STRING);
        }

        this.value = value;
    }


    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] toByte() {

        return value.getBytes();

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

        String output = "String Value: " + value + "\n";

        System.out.print(output);

        return output;

    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
