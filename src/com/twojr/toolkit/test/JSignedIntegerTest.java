package com.twojr.toolkit.test;
import static com.twojr.toolkit.JDataSizes.*;
import static org.junit.Assert.*;

import com.twojr.toolkit.DataTypes;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.JString;
import com.twojr.toolkit.integer.JSignedInteger;
import org.junit.Test;
/**
 * Created by Joseph Haggerty on 1/20/2017.
 */
public class JSignedIntegerTest {

    @Test
    public void evaluateByteInitializer() {
        JSignedInteger jSigned = new JSignedInteger(JDataSizes.THIRTY_TWO_BIT,938273);

        JSignedInteger jSignedByte = new JSignedInteger(jSigned.toByte());
        assertEquals(jSigned.getSize(),jSignedByte.getSize());
        assertEquals(jSigned.getValue(),jSignedByte.getValue());

        assertEquals(jSigned.toByte()[0],jSignedByte.toByte()[0]);
        assertEquals(jSigned.toByte()[1],jSignedByte.toByte()[1]);
        assertEquals(jSigned.toByte()[2],jSignedByte.toByte()[2]);
        assertEquals(jSigned.toByte()[3],jSignedByte.toByte()[3]);
    }

    @Test
    public void evaluateToByte8bit() {
        int value = 256;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(EIGHT_BIT, value);

        assertEquals(size,sIntTest.toByte().length);
        assertEquals( value &0xff, sIntTest.toByte()[0]);
    }

    @Test
    public void evaluateToByte32bit() {
        int value = 637261237;
        int size = THIRTY_TWO_BIT.ordinal();
        int id = 1;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(THIRTY_TWO_BIT, value);

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

        JSignedInteger sIntTest = new JSignedInteger(EIGHT_BIT, value);
        assertEquals(size, sIntTest.getSize());
        JSignedInteger sIntTest2 = new JSignedInteger(THIRTY_TWO_BIT,10);
        assertEquals(THIRTY_TWO_BIT.ordinal(), sIntTest2.getSize());

    }

    @Test
    public void evaluateGetValue() {
        int value = 128;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(EIGHT_BIT, value);

        assertEquals(value,sIntTest.getValue());
    }

    @Test
    public void evalueateGetId() {
        int value = 0;
        int size = 1;
        int id = DataTypes.SIGNED_THIRTY_TWO_BIT_INT;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(JDataSizes.THIRTY_TWO_BIT, value);
        assertEquals(id, sIntTest.getId());
    }

    @Test
    public void evaluateGetName() {
        int value = 0;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(EIGHT_BIT, value);
        assertEquals("Signed Integer", sIntTest.getName());

        sIntTest.setName(name);
        assertEquals(name, sIntTest.getName());
    }

    @Test
    public void evaluatePrint1() {
        int value = 1;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(EIGHT_BIT, value);
        assertEquals("1", sIntTest.print());
    }

    @Test
    public void evaluatePrint0() {
        int value = 0;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(EIGHT_BIT, value);
        assertEquals("0", sIntTest.print());
    }

    @Test
    public void evaluateSetValue() {
        JSignedInteger JSignedInteger = new JSignedInteger();

        assertEquals(0, JSignedInteger.getValue());
        JSignedInteger.setValue(1);
        assertEquals(1, JSignedInteger.getValue());

        JSignedInteger.setValue(0);
        assertEquals(0,JSignedInteger.getValue());
    }

    @Test
    public void evaluateSetId() {
        JSignedInteger sIntTest = new JSignedInteger();

        assertEquals(DataTypes.SIGNED_EIGHT_BIT_INT, sIntTest.getId());
        sIntTest.setId(12);
        assertEquals(12, sIntTest.getId());
    }

    @Test
    public void evaluateSetSize() {
        JSignedInteger sIntTest = new JSignedInteger();

        assertEquals(1, sIntTest.getSize());
        sIntTest.setSize(0);
        assertEquals(0, sIntTest.getSize());
    }

    @Test
    public void evaluateSetName() {
        JSignedInteger sIntTest = new JSignedInteger();

        assertEquals("Signed Integer", sIntTest.getName());
        sIntTest.setName("test_name");
        assertEquals("test_name", sIntTest.getName());
    }

    // TODO: implement and write tests for compress and savings methods

    @Test
    public void evaluateCompress() {
        JSignedInteger jSigned = new JSignedInteger(THIRTY_TWO_BIT,1024);

        assertEquals(THIRTY_TWO_BIT.ordinal(),jSigned.getSize());
        assertEquals(2, jSigned.compress().length);

        assertEquals(jSigned.toByte()[0], jSigned.compress()[0]);
        assertEquals(jSigned.toByte()[1], jSigned.compress()[1]);

    }

    @Test
    public void evaluateGetSavings() {
        JSignedInteger jSigned = new JSignedInteger(THIRTY_TWO_BIT,1024);

        assertEquals(THIRTY_TWO_BIT.ordinal(),jSigned.getSize());
        assertEquals(2, jSigned.compress().length);

        assertEquals(jSigned.toByte()[0], jSigned.compress()[0]);
        assertEquals(jSigned.toByte()[1], jSigned.compress()[1]);

        assertEquals(2,jSigned.getSavings());
    }


}
