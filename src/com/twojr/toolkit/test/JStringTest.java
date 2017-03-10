package com.twojr.toolkit.test;

import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.twojr.toolkit.DataTypes;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.JString;
import org.junit.Test;

import static com.twojr.toolkit.DataTypes.*;
import static com.twojr.toolkit.JDataSizes.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by rcunni002c on 1/20/2017.
 */
public class JStringTest {



    @Test
    public void evaluateToByte()
    {

        String str = "Test";

        byte[] strAsByte= {(byte) 0x54, (byte) 0x65, (byte) 0x73, (byte) 0x74};     // Test
        JString jString = new JString(EIGHT_BIT,str);

        for(int i=0; i<strAsByte.length; i++)
            assertEquals(strAsByte[i],jString.toByte()[i]);

    }

    @Test
    public void evaluateByteInitializer() {
        String value = "This is the string value";
        JString jString = new JString(value.getBytes());

        assertEquals(value,jString.getValue());
    }

    @Test
    public void evaluateGetValue()
    {

        String str = "Test";

        JString jString = new JString(EIGHT_BIT,str);

        assertEquals(str,jString.getValue());

    }

    @Test
    public void evaluateSetValue()
    {

        String str = "Test";
        String test  = "Test2";

        JString jString = new JString(EIGHT_BIT,str);

        jString.setValue(test);
        assertEquals(test, jString.getValue());

    }


    @Test
    public void evaluatePrint()
    {
        String str = "Test";

        JString jString = new JString(EIGHT_BIT,str);

        String output = "String Value: " + str + "\n";

        assertEquals(output, jString.print());

    }

}
