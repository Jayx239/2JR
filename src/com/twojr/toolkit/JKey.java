package com.twojr.toolkit;

import static com.twojr.toolkit.DataTypes.*;
import static com.twojr.toolkit.JDataSizes.*;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JKey extends JData{

    public static final String KEY = "Key";

    private byte[] value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JKey(){
        super(SECURITY_KEY, KEY, HUNDRED_TWENTY_EIGHT_BIT);
    }

    public JKey(byte[] value) {
        super(SECURITY_KEY, KEY, HUNDRED_TWENTY_EIGHT_BIT);
        this.value = value;
    }


    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] compress() {

        return value;

    }

    @Override
    public int getSavings() {
        return 0;
    }

    @Override
    public String print() {

        String output = "Key: ";

        for(byte bits : value){

            output += bits + " ";

        }

        output += "\n";

        return output;
        
    }

    @Override
    public byte[] toByte() {
        return new byte[0];
    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/