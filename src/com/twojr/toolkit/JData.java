package com.twojr.toolkit;

import static com.twojr.toolkit.JDataSizes.*;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public abstract class JData extends JIdentity implements ISendable{

    private int size;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JData() {
    }

    public JData(int id, String name, int size) {
        super(id, name);
        if(name == null){
            throw new IllegalArgumentException("JData input name cannot be null");
        }
        this.size = size;
    }

    public JData(int id, String name, JDataSizes size) {
        super(id, name);
        if(name == null){
            throw new IllegalArgumentException("JData input name cannot be null");
        }
        if(size == null){
            throw new IllegalArgumentException("JData input size cannot be null");
        }
        this.size = size.ordinal();
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSize(JDataSizes size){

        this.size = size.ordinal();
    }


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================


    public abstract byte[] compress();      //Compresses data into the smallest format possible
    public abstract int getSavings();       //Reports the numbers of bytes saved by compression

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/

