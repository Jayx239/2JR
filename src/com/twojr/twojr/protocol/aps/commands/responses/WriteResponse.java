package com.twojr.twojr.protocol.aps.commands.responses;

import com.twojr.toolkit.JInteger;
import com.twojr.twojr.protocol.Command;
import com.twojr.twojr.protocol.Response;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class WriteResponse  extends Response {

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public WriteResponse(int id, String name, int size, JInteger jId, Command command) {
        super(id, name, size, jId, command);
    }


    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public void execute() {

    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

}/*********************************************END OF FILE*************************************************************/

