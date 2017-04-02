package com.twojr.toolkit.test;
import static com.twojr.toolkit.JDataSizes.*;
import static org.junit.Assert.*;

import com.twojr.toolkit.DataTypes;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.integer.JUnsignedInteger;
import org.junit.Test;

import javax.xml.crypto.Data;

/**
 * Created by Joseph Haggerty on 1/20/2017.
 */
public class JUnsignedIntegerTest {

    @Test
    public void evaluateByteInitializer() {
        JUnsignedInteger jUnsigned = new JUnsignedInteger(JDataSizes.THIRTY_TWO_BIT,938273);

        JUnsignedInteger jUnsignedByte = new JUnsignedInteger(jUnsigned.toByte());
        assertEquals(jUnsigned.getSize(),jUnsignedByte.getSize());
        assertEquals(jUnsigned.getValue(),jUnsignedByte.getValue());

        assertEquals(jUnsigned.toByte()[0],jUnsignedByte.toByte()[0]);
        assertEquals(jUnsigned.toByte()[1],jUnsignedByte.toByte()[1]);
        assertEquals(jUnsigned.toByte()[2],jUnsignedByte.toByte()[2]);
        assertEquals(jUnsigned.toByte()[3],jUnsignedByte.toByte()[3]);
    }

    @Test
    public void evaluateToByte8bit() {
        int value = 233;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JUnsignedInteger sIntTest = new JUnsignedInteger(EIGHT_BIT,value);

        assertEquals(size,sIntTest.toByte().length);
        assertEquals((byte) value, sIntTest.toByte()[0]);
    }

    @Test
    public void evaluateToByte32bit() {
        int value = 637261237;
        int size = THIRTY_TWO_BIT.ordinal();
        int id = 1;
        String name = "test_name";

        JUnsignedInteger sIntTest = new JUnsignedInteger(THIRTY_TWO_BIT,value);

        assertEquals(size,sIntTest.toByte().length);
        assertEquals((byte) 0x25, sIntTest.toByte()[3]);
        assertEquals((byte) 0xFB, sIntTest.toByte()[2]);
        assertEquals((byte) 0xD5, sIntTest.toByte()[1]);
        assertEquals((byte) 0xB5, sIntTest.toByte()[0]);
    }

    @Test
    public void evaluateGetSize() {
        int value = 0;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JUnsignedInteger sIntTest = new JUnsignedInteger(EIGHT_BIT,value);
        assertEquals(size, sIntTest.getSize());
    }

    @Test
    public void evaluateGetValue() {
        int value = 0;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JUnsignedInteger sIntTest = new JUnsignedInteger(EIGHT_BIT,value);

        assertEquals(value,sIntTest.getValue());
    }

    @Test
    public void evalueateGetId() {
        int value = 0;
        int size = 1;
        int id = DataTypes.UNSIGNED_EIGHT_BIT_INT;
        String name = "test_name";

        JUnsignedInteger sIntTest = new JUnsignedInteger(EIGHT_BIT,value);
        assertEquals(id, sIntTest.getId());
    }

    @Test
    public void evaluateGetName() {
        int value = 0;
        int size = 1;
        int id = 1;
        String name = "Unsigned Integer";

        JUnsignedInteger sIntTest = new JUnsignedInteger(EIGHT_BIT,value);
        assertEquals(name, sIntTest.getName());
    }

    @Test
    public void evaluatePrint1() {
        int value = 1;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JUnsignedInteger sIntTest = new JUnsignedInteger(EIGHT_BIT,value);
        assertEquals("1", sIntTest.print());
    }

    @Test
    public void evaluatePrint0() {
        int value = 0;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JUnsignedInteger sIntTest = new JUnsignedInteger(EIGHT_BIT,value);
        assertEquals("0", sIntTest.print());
    }

    @Test
    public void evaluateSetValue() {
        JUnsignedInteger JUnsignedInteger = new JUnsignedInteger();

        assertEquals(0, JUnsignedInteger.getValue());
        JUnsignedInteger.setValue(1);
        assertEquals(1, JUnsignedInteger.getValue());

        JUnsignedInteger.setValue(0);
        assertEquals(0,JUnsignedInteger.getValue());
    }

    @Test
    public void evaluateSetValueNegative() {
        JUnsignedInteger JUnsignedInteger = new JUnsignedInteger();

        //Attempts to set negative value should actually set value to 0
        assertEquals(0, JUnsignedInteger.getValue());
        JUnsignedInteger.setValue(-1);
        assertEquals(0, JUnsignedInteger.getValue());
    }

    @Test
    public void evaluateSetId() {
        JUnsignedInteger sIntTest = new JUnsignedInteger();

        assertEquals(DataTypes.UNSIGNED_EIGHT_BIT_INT, sIntTest.getId());
        sIntTest.setId(12);
        assertEquals(12, sIntTest.getId());
    }

    @Test
    public void evaluateSetSize() {
        JUnsignedInteger sIntTest = new JUnsignedInteger();

        assertEquals(1, sIntTest.getSize());
        sIntTest.setSize(4);
        assertEquals(4, sIntTest.getSize());
    }

    @Test
    public void evaluateSetName() {
        JUnsignedInteger sIntTest = new JUnsignedInteger();

        assertEquals("Unsigned Integer", sIntTest.getName());
        sIntTest.setName("test_name");
        assertEquals("test_name", sIntTest.getName());
    }

    // TODO: implement and write tests for compress and savings methods
    @Test
    public void evaluateCompress() {
        JUnsignedInteger jUnsigned = new JUnsignedInteger(THIRTY_TWO_BIT,1024);

        assertEquals(THIRTY_TWO_BIT.ordinal(),jUnsigned.getSize());
        assertEquals(2, jUnsigned.compress().length);

        assertEquals(jUnsigned.toByte()[0], jUnsigned.compress()[0]);
        assertEquals(jUnsigned.toByte()[1], jUnsigned.compress()[1]);

    }

    @Test
    public void evaluateGetSavings() {
        JUnsignedInteger jUnsigned = new JUnsignedInteger(THIRTY_TWO_BIT,1024);

        assertEquals(THIRTY_TWO_BIT.ordinal(),jUnsigned.getSize());
        assertEquals(2, jUnsigned.compress().length);

        assertEquals(jUnsigned.toByte()[0], jUnsigned.compress()[0]);
        assertEquals(jUnsigned.toByte()[1], jUnsigned.compress()[1]);

        assertEquals(2,jUnsigned.getSavings());
    }
}