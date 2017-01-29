package com.twojr.toolkit.test;
import static org.junit.Assert.*;

import com.twojr.toolkit.JBit;
import org.junit.Test;
/**
 * Created by Jason on 1/17/2017.
 */
public class JBitTest {
    @Test
    public void evaluateToByte1() {
        boolean value = true;
        int size = 1;
        int id = 1;
        String name = "name";

        JBit bit1 = new JBit(id,name,size,value);

        assertEquals(size,bit1.toByte().length);
        assertEquals(0x01, bit1.toByte()[0]);
    }

    @Test
    public void evaluateToByte0() {

        boolean value = false;
        int size = 1;
        int id = 1;
        String name = "name";

        JBit bit0 = new JBit(id,name,size,value);

        assertEquals(size,bit0.toByte().length);
        assertEquals((byte) 0x00, bit0.toByte()[0]);
    }

    @Test
    public void evaluateGetSize() {

        boolean value = false;
        int size = 1;
        int id = 1;
        String name = "name";

        JBit bit = new JBit(id,name,size,value);
        assertEquals(size, bit.getSize());
    }

    @Test
    public void evaluateGetValue() {

        boolean value = false;
        int size = 1;
        int id = 1;
        String name = "name";

        JBit bit = new JBit(id,name,size,value);

        assertEquals(value,bit.getValue());
    }

    @Test
    public void evalueateGetId() {

        boolean value = false;
        int size = 1;
        int id = 1;
        String name = "name";

        JBit bit = new JBit(id,name,size,value);
        assertEquals(id, bit.getId());
    }

    @Test
    public void evaluateGetName() {

        boolean value = false;
        int size = 1;
        int id = 1;
        String name = "name";

        JBit bit = new JBit(id,name,size,value);
        assertEquals(name, bit.getName());
    }

    @Test
    public void evaluatePrint1() {

        boolean value = true;
        int size = 1;
        int id = 1;
        String name = "name";

        JBit bit1 = new JBit(id,name,size,value);
        assertEquals("1", bit1.print());
    }

    @Test
    public void evaluatePrint0() {

        boolean value = false;
        int size = 1;
        int id = 1;
        String name = "name";

        JBit bit1 = new JBit(id,name,size,value);
        assertEquals("0", bit1.print());
    }

    @Test
    public void evaluateSetValue() {
        JBit jBit = new JBit();

        assertEquals(false, jBit.getValue());
        jBit.setValue(true);
        assertEquals(true, jBit.getValue());

        jBit.setValue(false);
        assertEquals(false,jBit.getValue());

    }

    @Test
    public void evaluateSetId() {
        JBit jBit = new JBit();


        assertEquals(0, jBit.getId());
        jBit.setId(12);
        assertEquals(12, jBit.getId());

    }

    @Test
    public void evaluateSetSize() {
        JBit jBit = new JBit();

        assertEquals(0, jBit.getSize());
        jBit.setSize(1);
        assertEquals(1, jBit.getSize());

    }

    @Test
    public void evaluateSetName() {
        JBit jBit = new JBit();

        assertEquals(null, jBit.getName());
        jBit.setName("name");
        assertEquals("name", jBit.getName());
    }

    // TODO: implement and write tests for compress and savings methods

}
