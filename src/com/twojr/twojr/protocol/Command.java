package com.twojr.twojr.protocol;

import com.twojr.toolkit.JData;
import com.twojr.toolkit.JIdentity;
import com.twojr.toolkit.JInteger;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public abstract class Command extends JData{

    private JInteger jId;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public Command() {
    }

    public Command(int id, String name, int size, JInteger jId) {
        super(id, name, size);
        this.jId = jId;
    }


    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================


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

    @Override
    public byte[] toByte() {
        return new byte[0];
    }


    public abstract void execute();

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

