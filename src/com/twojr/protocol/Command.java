package com.twojr.protocol;

import com.twojr.protocol.aps.commands.CheckInCommand;
import com.twojr.toolkit.JData;
import com.twojr.toolkit.JIdentity;
import com.twojr.toolkit.JInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public abstract class Command extends JData{


    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public Command() {
    }

    public Command(byte id, String name) {
        super(id, name, 1);
    }

    public Command(byte id){

        setId(id);

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

    public static Command createCommand(byte id){
        return new CheckInCommand();
    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

