package com.twojr.toolkit.test;

import com.twojr.toolkit.*;
import org.junit.Test;

import java.util.HashMap;

import static com.twojr.toolkit.DataTypes.EIGHT_BIT_MAP_DATA;
import static com.twojr.toolkit.JDataSizes.EIGHT_BIT;
import static org.junit.Assert.assertEquals;

/**
 * Created by rcunni002c on 2/12/2017.
 */
public class JArrayTest {

    @Test
    public void evaluateToByte()
    {

        JBit jBit = new JBit(0,"Bit",1,false);
        JData array[] = {jBit};
        JArray jArray = new JArray(DataTypes.ARRAY,"Array", JDataSizes.EIGHT_BIT,array);

        assertEquals(jArray.toByte(),0);

    }

    @Test
    public void evaluateGetValue()
    {

        JBit jBit = new JBit(0,"Bit",1,false);
        JData array[] = {jBit};
        JArray jArray = new JArray(DataTypes.ARRAY,"Array", JDataSizes.EIGHT_BIT,array);

        assertEquals(jArray.getValue(),array);

    }

    @Test
    public void evaluateSetValue()
    {

        HashMap<Integer,Integer> values = new HashMap<>();

        JBit jBit1 = new JBit(0,"Bit",1,false);
        JBit jBit2 = new JBit(0,"Bit",1,true);

        JData array1[] = {jBit1};
        JData array2[] = {jBit2};

        JArray jArray = new JArray(DataTypes.ARRAY,"Array", JDataSizes.EIGHT_BIT,array1);

        jArray.setValue(array2);
        assertEquals(jArray.getValue(),array2);

    }

    @Test
    public void evaluatePrint()
    {

        JBit jBit = new JBit(0,"Bit",1,false);
        JData array[] = {jBit};
        JArray jArray = new JArray(DataTypes.ARRAY,"Array", JDataSizes.EIGHT_BIT,array);

        String output = "";

        for(JData data : jArray.getValue()){

            output += data.print();

        }
        
        assertEquals(jArray.print(),output);

    }


}
