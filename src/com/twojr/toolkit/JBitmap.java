package com.twojr.toolkit;

import java.util.HashMap;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JBitmap extends JData {

    private HashMap<Integer,Integer> value;
    private HashMap<Integer,String> params;
    private int noBits;


    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JBitmap(){

    }

    public JBitmap(int id, String name, int noBits) {
        super(id, name, (int)noBits/8);
        this.params = new HashMap<>();

        for(int i = 0; i < noBits; i++){
            params.put(i,"");
        }


        this.value = new HashMap<>();

        for(int i = 0; i < noBits; i++){
            value.put(i,0);
        }
        this.noBits = noBits;
    }


    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public HashMap<Integer, String> getParams() {
        return params;
    }

    public void setParams(HashMap<Integer, String> params) {
        this.params = params;
    }

    public int getNoBits() {
        return noBits;
    }

    public void setNoBits(int noBits) {
        this.noBits = noBits;
    }


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    public void putValue(int i, boolean used){

        if(used) {
            value.put(i,1);
        }else {
            value.put(i,0);
        }

    }

    public int getValues(int i){

        return value.get(i);

    }

    @Override
    public byte[] toByte() {
        return new byte[0];
    }

    @Override
    public String print() {
        return null;
    }

    @Override
    public byte[] compress() {
        return new byte[0];
    }

    @Override
    public int getSavings() {
        return 0;
    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
