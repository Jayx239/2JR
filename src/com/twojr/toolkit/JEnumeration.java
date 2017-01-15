package com.twojr.toolkit;

import java.util.Enumeration;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JEnumeration extends JData {


    private Enumeration value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JEnumeration() {
    }

    public JEnumeration(int id, String name, int size, Enumeration value) {
        super(id, name, size);
        this.value = value;
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public Enumeration getValue() {
        return value;
    }

    public void setValue(Enumeration value) {
        this.value = value;
    }


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] toByte() {
        return new byte[0];
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
        return null;
    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/