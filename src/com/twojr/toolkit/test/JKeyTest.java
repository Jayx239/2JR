package com.twojr.toolkit.test;

import com.twojr.toolkit.DataTypes;
import com.twojr.toolkit.JAddress;
import com.twojr.toolkit.JKey;
import org.junit.Test;

import static com.twojr.toolkit.DataTypes.*;
import static com.twojr.toolkit.DataTypes.IEEE_ADDRESS;
import static org.junit.Assert.assertEquals;

/**
 * Created by rcunni002c on 2/1/2017.
 */
public class JKeyTest {

    @Test
    public void evaluateToByte()
    {

        byte[] address = {00,00,00,00,00,00,00,00};

        JKey jKey = new JKey(address);

        for(int i=0; i<address.length; i++)
        assertEquals(address[i], jKey.toByte()[i]);

    }

    @Test
    public void evaluateGetAddress()
    {

        byte[] address = {00,00,00,00,00,00,00,00};

        JKey jKey = new JKey(address);

        assertEquals(address, jKey.getValue());
    }

    @Test
    public void evaluateSetAddress()
    {

        byte[] address1 = {00,00,00,00,00,00,00,00};
        byte[] address2 = {00,00,00,00,00,00,00,01};

        JKey jKey = new JKey(address1);

        jKey.setValue(address2);
        assertEquals(address2, jKey.getValue());

    }


    @Test
    public void evaluatePrint()
    {

        byte[] address = {00,00,00,00,00,00,00,00};

        JKey jAddress = new JKey(address);

        String output = "Key: ";

        for(byte bits : address){

            output += bits + " ";

        }

        output += "\n";



        assertEquals(output, jAddress.print());

    }


}
