package com.twojr.toolkit.test;
import static org.junit.Assert.*;

import com.twojr.toolkit.JDouble;
import org.junit.Test;
import java.nio.ByteBuffer;

/**
 * Created by Jason on 1/16/2017.
 */
public class JDoubleTest {
    @Test
    public void evaluateToByte() {
        double value = 201902;
        int size = 8;
        int id = 1;
        String name = "name";

        JDouble jDouble = new JDouble(id,name,size,value);
        assertEquals(ByteBuffer.wrap(jDouble.toByte()).getDouble(),value,0);
    }

    @Test
    public void evaluateGetSize() {
        double value = 201902;
        int size = 8;
        int id = 1;
        String name = "name";

        JDouble jDouble = new JDouble(id,name,size,value);
        assertEquals(jDouble.getSize(),size);
    }

    @Test
    public void evaluateGetValue() {
        double value = 201902;
        int size = 8;
        int id = 1;
        String name = "name";

        JDouble jDouble = new JDouble(id,name,size,value);
        assertEquals(Double.compare(jDouble.getValue(),value),0);
    }

    @Test
    public void evalueateGetId() {
        double value = 201902;
        int size = 8;
        int id = 1;
        String name = "name";

        JDouble jDouble = new JDouble(id,name,size,value);
        assertEquals(jDouble.getId(),id);
    }

    @Test
    public void evaluateGetName() {
        double value = 201902;
        int size = 8;
        int id = 1;
        String name = "name";

        JDouble jDouble = new JDouble(id,name,size,value);
        assertEquals(jDouble.getName(),name);
    }

    @Test
    public void evaluateToString() {

        double value = 201902;
        int size = 8;
        int id = 1;
        String name = "name";

        JDouble jDouble = new JDouble(id,name,size,value);
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


        assertEquals(0, jDouble.getId());
        jDouble.setId(12);
        assertEquals(12, jDouble.getId());

    }

    @Test
    public void evaluateSetSize() {
        JDouble jDouble = new JDouble();

        assertEquals(0, jDouble.getSize());
        jDouble.setSize(8);
        assertEquals(8, jDouble.getSize());

    }

    @Test
    public void evaluateSetName() {
        JDouble jDouble = new JDouble();

        assertEquals(null, jDouble.getName());
        jDouble.setName("name");
        assertEquals("name", jDouble.getName());
    }

    // TODO: implement and write tests for compress and savings methods

}
