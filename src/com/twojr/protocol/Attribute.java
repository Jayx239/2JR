package com.twojr.protocol;

import com.twojr.toolkit.JData;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class Attribute extends JData{

    private int bitNo;
    private JData data;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public Attribute() {
    }

    public Attribute(String name, int bitNo, JData data) {
        super(data.getId(), name, data.getSize());
        this.bitNo = bitNo;
        this.data = data;
    }


    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public int getBitNo() {
        return bitNo;
    }

    public void setBitNo(int bitNo) {
        this.bitNo = bitNo;
    }

    public JData getData() {
        return data;
    }

    public void setData(JData data) {
        this.data = data;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] compress() {
        return data.compress();
    }

    @Override
    public int getSavings() {
        return 0;
    }

    @Override
    public String print() {
        return null;
    }

    @Override
    public byte[] toByte() {
        return new byte[0];
    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

