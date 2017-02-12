package com.twojr.toolkit.test;

import com.twojr.toolkit.DataTypes;
import com.twojr.toolkit.JBitmap;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.JString;
import org.junit.Test;

import java.util.HashMap;

import static com.twojr.toolkit.DataTypes.*;
import static com.twojr.toolkit.JDataSizes.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by rcunni002c on 2/12/2017.
 */
public class JBitmapTest {

    @Test
    public void evaluateToByte()
    {

        JBitmap jBitmap = new JBitmap(EIGHT_BIT_MAP_DATA,"Bitmap", EIGHT_BIT);

        assertEquals(jBitmap.toByte(),0);

    }

    @Test
    public void evaluateGetValue()
    {

        JBitmap jBitmap = new JBitmap(EIGHT_BIT_MAP_DATA,"Bitmap", EIGHT_BIT);

        assertEquals(jBitmap.getValue(),0);

    }

    @Test
    public void evaluateSetValue()
    {

        HashMap<Integer,Integer> values = new HashMap<>();
        values.put(0,0);
        values.put(1,0);
        values.put(2,0);
        values.put(3,0);
        values.put(4,0);
        values.put(5,0);
        values.put(6,0);
        values.put(7,1);

        JBitmap jBitmap = new JBitmap(EIGHT_BIT_MAP_DATA,"Bitmap", EIGHT_BIT);

        jBitmap.setValue(values);

        assertEquals(jBitmap.getValue(),1);

    }

    @Test
    public void evaluateGetValues()
    {

        JBitmap jBitmap = new JBitmap(EIGHT_BIT_MAP_DATA,"Bitmap", EIGHT_BIT);

        assertEquals(jBitmap.getValues(0),0);


    }

    @Test
    public void evaluatePutValue()
    {


        JBitmap jBitmap = new JBitmap(EIGHT_BIT_MAP_DATA,"Bitmap", EIGHT_BIT);

        jBitmap.putValue(0,true);

        assertEquals(jBitmap.getValues(0),1);

    }


    @Test
    public void evaluatePrint()
    {

        JBitmap jBitmap = new JBitmap(EIGHT_BIT_MAP_DATA,"Bitmap", EIGHT_BIT);

        HashMap<Integer,Integer> value = jBitmap.getValue();
        HashMap<Integer,String> params = jBitmap.getParams();

        String output = "";

        for(int key :value.keySet()){

            output +=  key + ". value: " + value.get(key) + ", param: " + params.get(key) + "\n";

        }
        
        assertEquals(jBitmap.getValue(),output);

    }


}
