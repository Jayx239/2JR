package com.twojr.toolkit;

import java.util.HashMap;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JBitmap extends JData {

    private HashMap<Integer,Integer> value;
    private HashMap<Integer,String> params;


    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JBitmap(){

    }

    public JBitmap(int id, String name, JDataSizes size) {

        super(id, name, size);
        this.params = new HashMap<>();

        for(int i = 0; i < getSize(); i++){
            params.put(i,"");
        }


        this.value = new HashMap<>();

        for(int i = 0; i < getSize(); i++){
            value.put(i,0);
        }

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


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    public void putValue(int i, boolean used) {

        if (i > 0 && i <= getSize() * 8) {

            if (used) {

                value.put(i, 1);

            } else {

                value.put(i, 0);

            }

        }

    }

    public int getValues(int i){

        if (i > 0 && i <= getSize() * 8) {

            return value.get(i);

        }else {


            return  -1;

        }

    }

    @Override
    public byte[] toByte() {

        byte[] bytes = new byte[getSize()];

        for(int i = 0; i < getSize(); i++){

            String byteStr = "";

            for(int j = 0; j < 8; j++)
            {

                byteStr += value.get(j + i*8);

            }

            bytes[i] = Byte.parseByte(byteStr);
        }

        return bytes;

    }

    @Override
    public String print() {

        String output = "";

        for(int key : value.keySet()){

            output +=  key + ". value: " + value.get(key) + ", param: " + params.get(key) + "\n";

        }

        System.out.print(output);

        return output;
        
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
