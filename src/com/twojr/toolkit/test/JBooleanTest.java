package com.twojr.toolkit.test;
import static com.twojr.toolkit.DataTypes.BOOLEAN;
import static com.twojr.toolkit.JDataSizes.EIGHT_BIT;
import static com.twojr.toolkit.JDataSizes.NULL;
import static org.junit.Assert.*;

import com.twojr.toolkit.JBoolean;
import org.junit.Test;


/**
 * Created by Jason on 1/17/2017.
 */
public class JBooleanTest {

    @Test
    public void evaluateByteInitializer() {
        byte[] byteBit = {0x01};
        JBoolean bitByte = new JBoolean(byteBit);

        assertEquals(0x01,bitByte.toByte()[0]);

        bitByte.setValue(false);
        assertEquals(0x00, bitByte.toByte()[0]);
    }

    @Test
    public void evaluateToByte1() {
        boolean value = true;
        int size = 1;
        int id = 1;
        String name = "name";

        JBoolean boolean1 = new JBoolean(value);

        assertEquals(size,boolean1.toByte().length);
        assertEquals((byte) 0x01, boolean1.toByte()[0]);
    }

    @Test
    public void evaluateToByte0() {

        boolean value = false;
        int size = 1;
        int id = 1;
        String name = "name";

        JBoolean boolean0 = new JBoolean(value);

        assertEquals(size,boolean0.toByte().length);
        assertEquals((byte) 0x00, boolean0.toByte()[0]);
    }

    @Test
    public void evaluateGetSize() {

        boolean value = false;
        int size = 1;
        int id = 1;
        String name = "name";

        JBoolean jBoolean = new JBoolean(value);
        assertEquals(size, jBoolean.getSize());
    }

    @Test
    public void evaluateGetValue() {

        boolean value = false;
        int size = 1;
        int id = 1;
        String name = "name";

        JBoolean jBoolean = new JBoolean(value);

        assertEquals(value,jBoolean.getValue());
    }

    @Test
    public void evalueateGetId() {

        boolean value = false;
        int size = 1;
        int id = 1;
        String name = "name";


        JBoolean jBoolean = new JBoolean(value);
        assertEquals(BOOLEAN, jBoolean.getId());
    }

    @Test
    public void evaluateGetName() {

        boolean value = false;
        int size = 1;
        int id = 1;
        String name = "name";


        JBoolean jBoolean = new JBoolean(value);

        assertEquals("Boolean", jBoolean.getName());
    }

    @Test
    public void evaluatePrint1() {

        boolean value = true;
        int size = 1;
        int id = 1;
        String name = "name";

        JBoolean jBoolean = new JBoolean(value);

        assertEquals("true", jBoolean.print());
    }

    @Test
    public void evaluatePrint0() {

        boolean value = false;
        int size = 1;
        int id = 1;
        String name = "name";


        JBoolean jBoolean = new JBoolean(value);

        assertEquals("false", jBoolean.print());
    }

    @Test
    public void evaluateSetValue() {
        JBoolean jBoolean = new JBoolean();

        assertEquals(false, jBoolean.getValue());
        jBoolean.setValue(true);
        assertEquals(true, jBoolean.getValue());

        jBoolean.setValue(false);
        assertEquals(false,jBoolean.getValue());

    }

    @Test
    public void evaluateSetId() {
        JBoolean jBoolean = new JBoolean();


        assertEquals(BOOLEAN, jBoolean.getId());
        jBoolean.setId(12);
        assertEquals(12, jBoolean.getId());

    }

    @Test
    public void evaluateSetSize() {
        JBoolean jBoolean = new JBoolean();

        assertEquals(EIGHT_BIT.ordinal(), jBoolean.getSize());
        jBoolean.setSize(NULL.ordinal());
        assertEquals(NULL.ordinal(), jBoolean.getSize());

    }

    @Test
    public void evaluateSetName() {
        JBoolean jBoolean = new JBoolean();

        assertEquals("Boolean", jBoolean.getName());
        jBoolean.setName("name");
        assertEquals("name", jBoolean.getName());
    }

    // TODO: implement and write tests for compress and savings methods

}
