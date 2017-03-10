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

    public Command(byte id){

        JUnsignedInteger uint = new JUnsignedInteger(new byte[]{id});

        this.jId = uint;

    }


    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public JInteger getjId() {
        return jId;
    }

    public void setjId(JInteger jId) {
        this.jId = jId;
    }


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

