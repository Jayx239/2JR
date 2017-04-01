package com.twojr.toolkit.test;

import com.twojr.toolkit.DataTypes;
import com.twojr.toolkit.JAddress;
import com.twojr.toolkit.JArray;
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

        JAddress jAddress = new JAddress(address);

        assertEquals(jAddress.toByte(),address);

    }

    @Test
    public void evaluateLongInitializer() {
        JAddress jAddress = new JAddress(192832);
        long val = 192832;
        byte[] asByteArr = new byte[8];
        int offset = 0;
        for(byte b : asByteArr) {
            b = (byte)((val>>>(offset*8))&0xFF);
            assertEquals(b, jAddress.toByte()[offset++]);
        }
    }

    @Test
    public void evaluateStringInitializer() {
        String strAddress = "123:43:98:23:5:1:7:29";
        JAddress address = new JAddress(strAddress);
        assertEquals("Address: " + strAddress + "\n",address.print());
    }

    @Test
    public void evaluateGetAddress()
    {

        byte[] address = {00,00,00,00,00,00,00,00};

        JAddress jAddress = new JAddress(address);

        assertEquals(jAddress.getAddress(),address);
    }

    @Test
    public void evaluateSetAddress()
    {

        byte[] address1 = {00,00,00,00,00,00,00,00};
        byte[] address2 = {00,00,00,00,00,00,00,01};

        JAddress jAddress = new JAddress(address1);

        jAddress.setAddress(address2);
        assertEquals(jAddress.getAddress(),address2);

    }


    @Test
    public void evaluatePrint()
    {

        byte[] address = {00,00,00,00,00,00,00,00};

        JAddress jAddress = new JAddress(address);

        String output = "Address: ";

        int index = 0;
        for(byte bits : address)
        {
            output  += bits;
            if(index != 7)
                output += ":";
            index++;
        }
        output += '\n';


        assertEquals(jAddress.print(),output);

    }

}
