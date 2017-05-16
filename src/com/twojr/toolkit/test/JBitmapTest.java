package com.twojr.toolkit.test;

import com.twojr.toolkit.*;
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
        JBitmap jBitmap = new JBitmap();
        jBitmap.setValue(0x13,0);
        assertEquals((byte)0x13, jBitmap.toByte()[0]);
    }

    @Test
    public void evaluateByteInitizilzer() {

        byte[] bitmap = {(byte)0x13,(byte)0x32};
        JBitmap constrTest = new JBitmap(bitmap);

        assertEquals(bitmap[0],constrTest.toByte()[0]);
        assertEquals(bitmap[1],constrTest.toByte()[1]);
    }

    @Test
    public void evaluateGetValue()
    {
        byte[] value = {(byte)0x80,(byte) 0x32};

        JBitmap jBitmap = new JBitmap(value);

        assertEquals(-128, jBitmap.getValue()[0]);
        assertEquals(0x32, jBitmap.getValue()[1]);

    }

    @Test
    public void evaluateSetValue()
    {

        JBitmap jBitmap = new JBitmap(JDataSizes.SIXTEEN_BIT);
        jBitmap.setBitValue(true,6,0);
        jBitmap.setBitValue(true,3,1);
        assertEquals(0x40, jBitmap.getValue()[0]);
        assertEquals(0x08,jBitmap.getValue()[1]);
    }

    @Test
    public void evaluateSetBit()
    {
        JBitmap jBitmap = new JBitmap(JDataSizes.THIRTY_TWO_BIT);
        jBitmap.setBitValue(true,3);    // Set first byte 4th bit => 8 base 10
        jBitmap.setBitValue(true,6,1);  // Set second byte 7th bit => 64 base 10
        jBitmap.setBitValue(true,1,2);  // Set third byte 3rd bit => 2 base 10
        jBitmap.setBitValue(true,4,3);  // Set fourth byte 5th bit -> 16 base 10

        assertEquals(8,jBitmap.toByte()[0]);
        assertEquals(64,jBitmap.toByte()[1]);
        assertEquals(2,jBitmap.toByte()[2]);
        assertEquals(16,jBitmap.toByte()[3]);

    }


    @Test
    public void evaluatePrint()
    {
        byte[] bitmap = {0x39,0x32,0x76};
        JBitmap jBitmap = new JBitmap(bitmap);
        String output = "Row[0]: 00111001\nRow[1]: 00110010\nRow[2]: 01110110\n";

        assertEquals(output,jBitmap.print());

    }


}
