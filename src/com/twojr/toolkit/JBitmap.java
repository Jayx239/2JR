package com.twojr.toolkit;

import java.util.HashMap;

import static com.twojr.toolkit.DataTypes.*;
import static com.twojr.toolkit.JDataSizes.*;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JBitmap extends JData {

    public static final String BITMAP = "Bitmap";
    byte[] value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JBitmap() {
        super(EIGHT_BIT_MAP_DATA, BITMAP, EIGHT_BIT);
        value = new byte[EIGHT_BIT.ordinal()];
    }

    public JBitmap(JDataSizes size) {
        super(EIGHT_BIT_MAP_DATA, BITMAP, size);
        if(size == null){
            throw new IllegalArgumentException("JBitmap input size cannot be null");
        }
        setId(computeId(size));
        value = new byte[size.ordinal()];
    }

    public JBitmap(byte[] value) {
        super(EIGHT_BIT_MAP_DATA, BITMAP, value.length);
        if(value == null){
            throw new IllegalArgumentException("JBitmap input byte array value cannot be null");
        }
        setValue(value);
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public byte[] getValue() {
        return value;
    }

    public void setValue(int mask, int byteOffset) {
        if(!isValidByteOffset(byteOffset))
            return;

        this.value[byteOffset] = (byte) mask;
    }

    public void setValue(byte[] value) {
        this.value = value;
        setName(BITMAP);
        setSize(value.length);
        setId(computeId(value.length));
    }

    public void setValue(int mask) {
        setValue(mask,0);
    }

    public void setBitValue(boolean value, int bitOffset, int byteOffset) {
        if(!isValidBitOffset(bitOffset) || !isValidByteOffset(byteOffset))
            return;

        int intVal =  this.value[byteOffset];
        int flag = 0x0001 << (bitOffset);
        if(value)
            this.value[byteOffset] = (byte) (intVal | flag);    // Set bit to 1
        else
            this.value[byteOffset] = (byte) (intVal & ~flag);    // Set bit to 0
    }

    public void setBitValue(boolean value, int bitOffset) {
        setBitValue(value,bitOffset,0);
    }

    public boolean getBitValue(int byteOffset,int bitOffset){

        if(!isValidBitOffset(bitOffset) || !isValidByteOffset(byteOffset)) {
            return false;
        }

        byte data = this.value[byteOffset];

        return (data & (1 << bitOffset)) != 0;


    }


    public boolean getBitValue(int bitOffset){

        return getBitValue(0, bitOffset);

    }
    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] toByte() {
        return value;
    }

    @Override
    public String print() {

        String output = "";
        int row = 0;
        for(byte B : value) {
            output += "Row[" + row++ + "]: ";
            for(int i=7; i>=0; i--)
                output+= Integer.toString(((B >> i)&0x01));
            output+="\n";
        }

        return output;

    }

    @Override
    public byte[] compress() {
        return new byte[0];
    }

    @Override
    public int getSavings() {
        return 0;
    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

    private int computeId(JDataSizes size) {

        int id;
        switch(size)
        {
            case EIGHT_BIT:
                id = EIGHT_BIT_MAP_DATA;
                break;

            case SIXTEEN_BIT:
               id = SIXTEEN_BIT_MAP_DATA;
               break;

            case TWENTY_FOUR_BIT:
               id = TWENTY_FOUR_BIT_MAP_DATA;
               break;

            case THIRTY_TWO_BIT:
               id = THIRTY_TWO_BIT_MAP_DATA;
               break;

            case FORTY_BIT:
               id = FORTY_BIT_MAP_DATA;
               break;

            case FORTY_EIGHT_BIT:
               id = FORTY_EIGHT_BIT_MAP_DATA;
               break;

            case FIFTY_SIX_BIT:
                id = FIFTY_SIX_BIT_MAP_DATA;
                break;

            case SIXTY_FOUR_BIT:
                id = SIXTY_FOUR_BIT_MAP_DATA;
                break;

            default:
                id = EIGHT_BIT_MAP_DATA;
                break;

        }

        return id;
    }

    // Overload for computing id from byte array
    private int computeId(int size) {
    
        int id;
        switch(size)
        {
            case 1:
                id = EIGHT_BIT_MAP_DATA;
                break;

            case 2:
                id = SIXTEEN_BIT_MAP_DATA;
                break;

            case 3:
                id = TWENTY_FOUR_BIT_MAP_DATA;
                break;

            case 4:
                id = THIRTY_TWO_BIT_MAP_DATA;
                break;

            case 5:
                id = FORTY_BIT_MAP_DATA;
                break;

            case 6:
                id = FORTY_EIGHT_BIT_MAP_DATA;
                break;

            case 7:
                id = FIFTY_SIX_BIT_MAP_DATA;
                break;

            case 8:
                id = SIXTY_FOUR_BIT_MAP_DATA;
                break;

            default:
                id = EIGHT_BIT_MAP_DATA;
                break;
        }

        return id;
    }
    
    private boolean isValidByteOffset(int byteOffset) {
        // Check for invalid byte offset size
        if(byteOffset >= getSize()) {
            System.err.println("Invalid byte offset for bitmap setValue\nByte offset: " + byteOffset + "\nBitmap Size: " + getSize());
            return false;
        }

        return true;
    }

    private boolean isValidBitOffset(int bitOffset) {
        if(bitOffset > 7 || bitOffset < 0) {
            System.err.println("Invalid bit offset for bitmap setValue\nBit offset: " + bitOffset);
            return false;
        }
        return true;
    }

} /*********************************************END OF FILE*************************************************************/
