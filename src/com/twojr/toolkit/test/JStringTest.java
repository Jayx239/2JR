package com.twojr.toolkit.test;

import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.twojr.toolkit.DataTypes;
import com.twojr.toolkit.JString;
import org.junit.Test;

import static com.twojr.toolkit.DataTypes.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by rcunni002c on 1/20/2017.
 */
public class JStringTest {



    @Test
    public void evaluateToByte()
    {

        String str = "Test";

        JString jString = new JString(CHARACTER_STRING,"String",-1,str);

        assertEquals(jString.toByte(),str.getBytes());

    }

    @Test
    public void evaluateGetValue()
    {

        String str = "Test";

        JString jString = new JString(CHARACTER_STRING,"String",-1,str);

        assertEquals(jString.getValue(),str);

    }

    @Test
    public void evaluateSetValue()
    {

        String str = "Test";
        String test  = "Test2";

        JString jString = new JString(CHARACTER_STRING,"String",-1,str);
        jString.setValue(test);
        assertEquals(jString.getValue(),test);

    }


    @Test
    public void evaluatePrint()
    {
        String str = "Test";

        JString jString = new JString(CHARACTER_STRING,"String",-1,str);

        String output = "String Value: " + str + "\n";

        assertEquals(jString.getValue(),output);

    }

}
