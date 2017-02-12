package com.twojr.toolkit.test;
import static org.junit.Assert.*;

import com.twojr.toolkit.integer.JSignedInteger;
import org.junit.Test;
/**
 * Created by Joseph Haggerty on 1/20/2017.
 */
public class JSignedIntegerTest {
    @Test
    public void evaluateToByte1() {
        int value = 1;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(id,name,size,value);

        assertEquals(size,sIntTest.toByte().length);
        assertEquals( 0x01, sIntTest.toByte()[0]);
    }

    @Test
    public void evaluateToByte0() {
        int value = 0;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(id,name,size,value);

        assertEquals(size,sIntTest.toByte().length);
        assertEquals(0x00, sIntTest.toByte()[0]);
    }

    @Test
    public void evaluateGetSize() {
        int value = 0;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(id,name,size,value);
        assertEquals(size, sIntTest.getSize());
    }

    @Test
    public void evaluateGetValue() {
        int value = 0;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(id,name,size,value);

        assertEquals(value,sIntTest.getValue());
    }

    @Test
    public void evalueateGetId() {
        int value = 0;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(id,name,size,value);
        assertEquals(id, sIntTest.getId());
    }

    @Test
    public void evaluateGetName() {
        int value = 0;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(id,name,size,value);
        assertEquals(name, sIntTest.getName());
    }

    @Test
    public void evaluatePrint1() {
        int value = 1;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(id,name,size,value);
        assertEquals("1", sIntTest.print());
    }

    @Test
    public void evaluatePrint0() {
        int value = 0;
        int size = 1;
        int id = 1;
        String name = "test_name";

        JSignedInteger sIntTest = new JSignedInteger(id,name,size,value);
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

        assertEquals(0, sIntTest.getId());
        sIntTest.setId(12);
        assertEquals(12, sIntTest.getId());
    }

    @Test
    public void evaluateSetSize() {
        JSignedInteger sIntTest = new JSignedInteger();

        assertEquals(0, sIntTest.getSize());
        sIntTest.setSize(1);
        assertEquals(1, sIntTest.getSize());
    }

    @Test
    public void evaluateSetName() {
        JSignedInteger sIntTest = new JSignedInteger();

        assertEquals(null, sIntTest.getName());
        sIntTest.setName("test_name");
        assertEquals("test_name", sIntTest.getName());
    }

    // TODO: implement and write tests for compress and savings methods
}