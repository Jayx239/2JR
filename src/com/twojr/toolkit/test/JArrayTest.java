package com.twojr.toolkit.test;

import com.twojr.toolkit.*;
import org.junit.Test;

import java.util.HashMap;

import static com.twojr.toolkit.DataTypes.DOUBLE_PRECISION;
import static com.twojr.toolkit.DataTypes.EIGHT_BIT_MAP_DATA;
import static com.twojr.toolkit.JDataSizes.EIGHT_BIT;
import static com.twojr.toolkit.JDataSizes.SIXTY_FOUR_BIT;
import static org.junit.Assert.assertEquals;

/**
 * Created by rcunni002c on 2/12/2017.
 */
public class JArrayTest {

    @Test
    public void evaluateByteInitializer() {
        JDouble jDouble = new JDouble(1000);
        JDouble jDouble1 = new JDouble(10392);

        JArray jArray = new JArray(new JData[]{jDouble,jDouble1});
        JArray jArray1 = new JArray(jArray.toByte(),DataTypes.DOUBLE_PRECISION);
        byte[] array = jArray.toByte();
        for(int i=0; i<array.length; i++) {
            assertEquals(array[i],jArray1.toByte()[i]);
        }
    }

    @Test
    public void evaluateToByte()
    {

        JBit jBit = new JBit("Bit",JDataSizes.EIGHT_BIT,false);
        JData array[] = {jBit};
        JArray jArray = new JArray(array);

        assertEquals(jArray.toByte()[0],0);


        JDouble array2[] = {new JDouble(10392),new JDouble(9382.392),new JDouble(83)};
        JArray doubArray = new JArray(array2);
        byte[] output = doubArray.toByte();

        // Test Array
        byte[] expected = {(byte) 0x40, (byte) 0xC4, (byte) 0x4C, (byte)0x00, (byte)0x00, (byte) 0x00,(byte) 0x00,(byte) 0x00,  //10392
                (byte) 0x40, (byte) 0xC2, (byte) 0x53, (byte) 0x32, (byte) 0x2D, (byte) 0x0E, (byte) 0x56, (byte) 0x04,     // 9382.392
                (byte) 0x40, (byte) 0x54, (byte) 0xC0, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };   // 83

        for(int i=0; i < (3*SIXTY_FOUR_BIT.ordinal()); i++)
                assertEquals(expected[i],output[i]);

    }

    @Test
    public void evaluateGetValue()
    {

        JBit jBit = new JBit("Bit",JDataSizes.EIGHT_BIT,false);
        JData array[] = {jBit};
        JArray jArray = new JArray(array);

        assertEquals(jArray.getValue(),array);

    }

    @Test
    public void evaluateSetValue()
    {

        HashMap<Integer,Integer> values = new HashMap<>();

        JBit jBit1 = new JBit("Bit",EIGHT_BIT,false);
        JBit jBit2 = new JBit("Bit",EIGHT_BIT,true);

        JData array1[] = {jBit1};
        JData array2[] = {jBit2};

        JArray jArray = new JArray(array1);

        jArray.setValue(array2);
        assertEquals(jArray.getValue(),array2);

    }

    @Test
    public void evaluatePrint()
    {

        JBit jBit = new JBit("Bit",EIGHT_BIT,false);
        JData array[] = {jBit};
        JArray jArray = new JArray(array);

        String output = "";
        int i = 0;
        for(JData data : jArray.getValue()){

            output += "["+ i++ +"]: " + data.print() + "\n";

        }
        
        assertEquals(output,jArray.print());

    }


}
