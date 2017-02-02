package com.twojr.toolkit.test;

import com.twojr.toolkit.DataTypes;
import com.twojr.toolkit.JAddress;
import com.twojr.toolkit.JString;
import org.junit.Test;

import static com.twojr.toolkit.DataTypes.*;
import static com.twojr.toolkit.DataTypes.CHARACTER_STRING;
import static org.junit.Assert.assertEquals;

/**
 * Created by rcunni002c on 2/1/2017.
 */
public class JAddressTest {

    @Test
    public void evaluateToByte()
    {

        byte[] address = {00,00,00,00,00,00,00,00};

        JAddress jAddress = new JAddress(IEEE_ADDRESS,"Address",8,address);

        assertEquals(jAddress.toByte(),jAddress);

    }

    @Test
    public void evaluateGetAddress()
    {

        byte[] address = {00,00,00,00,00,00,00,00};

        JAddress jAddress = new JAddress(IEEE_ADDRESS,"Address",8,address);

        assertEquals(jAddress.getAddress(),jAddress);
    }

    @Test
    public void evaluateSetAddress()
    {

        byte[] address1 = {00,00,00,00,00,00,00,00};
        byte[] address2 = {00,00,00,00,00,00,00,01};

        JAddress jAddress = new JAddress(IEEE_ADDRESS,"Address",8,address1);

        jAddress.setAddress(address2);
        assertEquals(jAddress.getAddress(),address2);

    }


    @Test
    public void evaluatePrint()
    {

        byte[] address = {00,00,00,00,00,00,00,00};

        JAddress jAddress = new JAddress(IEEE_ADDRESS,"Address",8,address);

        String output = "Address: ";

        for(byte bits : address)
        {
            output  += bits + ":";

        }

        output += "\n";


        assertEquals(jAddress.print(),output);

    }
}
