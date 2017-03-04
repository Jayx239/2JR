package com.twojr.toolkit.test;
import static com.twojr.toolkit.DataTypes.DOUBLE_PRECISION;
import static com.twojr.toolkit.JDataSizes.SIXTY_FOUR_BIT;
import static org.junit.Assert.*;

import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.JDouble;
import org.junit.Test;
import java.nio.ByteBuffer;

/**
 * Created by Jason on 1/16/2017.
 */
public class JDoubleTest {

    @Test
    public void evaluateByteInitializer() {
        byte[] expectedValue = {(byte) 0x41, (byte)0x08, (byte) 0xA5, (byte) 0x70,
                (byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00};

        JDouble jDouble = new JDouble(expectedValue);
        for(int i=0; i<jDouble.getSize(); i++)
            assertEquals(expectedValue[i],jDouble.toByte()[i]);

    }

    @Test
    public void evaluateToByte() {
        double value = 201902;

        byte[] expectedValue = {(byte) 0x41, (byte)0x08, (byte) 0xA5, (byte) 0x70,
                (byte) 0x00,(byte) 0x00,(byte) 0x00,(byte) 0x00};
        int size = 8;
        int id = 1;
        String name = "name";

        JDouble jDouble = new JDouble(value);
        for(int i=0; i<size; i++)
            assertEquals(expectedValue[i],jDouble.toByte()[i]);

    }

    @Test
    public void evaluateToByteDecimal() {

        double value = 10.39284;

        byte[] expectedValue = {(byte) 0x40, (byte) 0x24, (byte) 0xC9, (byte) 0x22,
                (byte) 0x53, (byte) 0x11, (byte) 0x1F, (byte) 0x0C};
        int size = 8;
        int id = 1;
        String name = "name";

        JDouble jDouble = new JDouble(value);
        for (int i = 0; i < size; i++)
            assertEquals(expectedValue[i], jDouble.toByte()[i]);

    }

        @Test
    public void evaluateGetSize() {
        double value = 201902;
        int size = 8;
        int id = 1;
        String name = "name";

        JDouble jDouble = new JDouble(value);
        assertEquals(jDouble.getSize(),size);
    }

    @Test
    public void evaluateGetValue() {
        double value = 201902;
        int size = 8;
        int id = 1;
        String name = "name";

        JDouble jDouble = new JDouble(value);
        assertEquals(Double.compare(jDouble.getValue(),value),0);
    }

    @Test
    public void evalueateGetId() {
        double value = 201902;
        int size = 8;
        int id = 1;
        String name = "name";

        JDouble jDouble = new JDouble(value);
        assertEquals(jDouble.getId(),DOUBLE_PRECISION);
    }

    @Test
    public void evaluateGetName() {
        double value = 201902;
        int size = 8;
        int id = 1;
        String name = "name";

        JDouble jDouble = new JDouble(value);

        assertEquals(jDouble.getName(),"Double");
    }

    @Test
    public void evaluatePrint() {

        double value = 201902;
        int size = 8;
        int id = 1;
        String name = "name";

        JDouble jDouble = new JDouble(value);

        assertEquals(jDouble.print(),"201902.0");
    }

    @Test
    public void evaluateSetValue() {
        JDouble jDouble = new JDouble();

        assertEquals(0.0, jDouble.getValue(),0);
        jDouble.setValue(20.0);
        assertEquals(20.0, jDouble.getValue(),0);

    }

    @Test
    public void evaluateSetId() {
        JDouble jDouble = new JDouble();


        assertEquals(DOUBLE_PRECISION, jDouble.getId());
        jDouble.setId(12);
        assertEquals(12, jDouble.getId());

    }

    @Test
    public void evaluateSetSize() {
        JDouble jDouble = new JDouble();

        assertEquals(SIXTY_FOUR_BIT.ordinal(), jDouble.getSize());
        jDouble.setSize(SIXTY_FOUR_BIT.ordinal());
        assertEquals(SIXTY_FOUR_BIT.ordinal(), jDouble.getSize());

    }

    @Test
    public void evaluateSetName() {
        JDouble jDouble = new JDouble();

        assertEquals("Double", jDouble.getName());
        jDouble.setName("name");
        assertEquals("name", jDouble.getName());
    }

    // TODO: implement and write tests for compress and savings methods

}
