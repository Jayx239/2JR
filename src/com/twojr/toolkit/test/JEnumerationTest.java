package com.twojr.toolkit.test;

import com.twojr.toolkit.DataTypes;
import com.twojr.toolkit.JAddress;
import com.twojr.toolkit.JEnumeration;
import org.junit.Test;

import static com.twojr.toolkit.DataTypes.*;
import static com.twojr.toolkit.DataTypes.IEEE_ADDRESS;
import static org.junit.Assert.assertEquals;

/**
 * Created by rcunni002c on 2/12/2017.
 */
public class JEnumerationTest {

    @Test
    public void evaluateToByte()
    {

        byte[] value = {00, 00};

        JEnumeration jEnumeration = new JEnumeration(SIXTEEN_BIT_ENUMERATION, "Enumeration",2, value);

        assertEquals(value,jEnumeration.toByte());
        
    }

    @Test
    public void evaluateGetValue()
    {

        byte[] value = {00, 00};

        JEnumeration jEnumeration = new JEnumeration(SIXTEEN_BIT_ENUMERATION, "Enumeration",2, value);

        assertEquals(value,jEnumeration.getValue());

    }

    @Test
    public void evaluateSetValue()
    {

        byte[] value1 = {00, 00};
        byte[] value2 = {00, 00};

        JEnumeration jEnumeration = new JEnumeration(SIXTEEN_BIT_ENUMERATION, "Enumeration",2, value1);
        jEnumeration.setValue(value2);

        assertEquals(value2,jEnumeration.getValue());

    }


    @Test
    public void evaluatePrint()
    {

        byte[] value = {00, 00};

        JEnumeration jEnumeration = new JEnumeration(SIXTEEN_BIT_ENUMERATION, "Enumeration",2, value);

        String output = "Enumeration: ";

        output += value;

        output += "\n";

        assertEquals(output,jEnumeration.print());

    }

}
