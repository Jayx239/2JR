package com.twojr.toolkit.test;

import com.twojr.toolkit.JAddress;
import com.twojr.toolkit.JData;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.JString;
import org.junit.Test;

import static com.twojr.toolkit.DataTypes.IEEE_ADDRESS;
import static com.twojr.toolkit.JDataSizes.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by rcunni002c on 2/1/2017.
 */
public class JDataTest {


    @Test
    public void evaluateSetSize()
    {
        int size = 1;

        JData data = new JString(EIGHT_BIT,"");

        assertEquals(data.getSize(),size);

    }

    @Test
    public void evaluateGetSize()
    {

        int size1 = 1;
        int size2 = 2;

        JData data = new JString(EIGHT_BIT,"");

        data.setSize(size2);
        assertEquals(data.getSize(),size2);

    }

}
