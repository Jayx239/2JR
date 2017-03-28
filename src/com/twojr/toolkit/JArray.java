package com.twojr.toolkit;

import com.twojr.toolkit.integer.JSignedInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;

import static com.twojr.toolkit.DataTypes.*;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JArray extends JData{

    public static final String ARRAY_NAME = "Array";

    private JData[] value;
    private int elementType;
    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JArray() {

        super(ARRAY, ARRAY_NAME, 0);

    }

    public JArray(JData[] value) {
        super(ARRAY, ARRAY_NAME, 0);
        if(value == null){
            throw new IllegalArgumentException("JArray input JData value cannot be null");
        }
        this.value = value;
        setSize(computeSize());
        if(value.length > 0)
            this.elementType = value[0].getId();
    }

    public JArray(int size) {
        super(ARRAY,ARRAY_NAME,size);
        this.value = new JData[size];
    }

    public JArray(byte[] byteArray, int dataType) {
        if(byteArray == null){
            throw new IllegalArgumentException("JArray input byteArray cannot be null");
        }
        setValue(byteArray,dataType);
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public JData[] getValue() {
        return value;
    }

    public JData getElementAt(int index) {
        return value[index];
    }

    public void setElementAt(int index, JData repVal) {
        value[index] = repVal;
    }

    public void setValue(JData[] value) {
        if (value == null)
            return;

        this.value = value;
        setSize(computeSize());
        if(computeSize() > 0)
            this.elementType = value[0].getId();

    }

    public void setValue(byte[] byteArray, int dataType) {
        if(byteArray == null || byteArray.length == 0)
            return;

        int dataSize = DataTypes.dataSizeMap.get(dataType);
        int arrayLen = byteArray.length/dataSize;
        JData[] jArray = new JData[arrayLen];

        for(int i = 0; i<arrayLen; i++) {

            byte[] nextIn = new byte[dataSize];
            for(int j=0; j< dataSize; j++)
                nextIn[j] = byteArray[(i*dataSize)+j];
            jArray[i] = initializeElement(dataType,nextIn);
            this.elementType = jArray[i].getId();
        }
        this.value = jArray;
        this.setSize(this.computeSize());
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] compress() {
        return new byte[0];
    }

    @Override
    public int getSavings() {
        return 0;
    }

    @Override
    public String print() {

        String output = "";
        int index = 0;
        for(JData data : value){
            output += "[" + index++ + "]: ";
            output += data.print();
            output += "\n";
        }

        return output;

    }

    @Override
    public byte[] toByte() {

        if(value == null || value.length == 0)
            return null;

        int size = this.computeSize();
        byte bytes[] = new byte[size];
        int arrayIndex = 0;

        for(JData data : value){

            byte[] dataBytes = data.toByte();
            for(int i = 0; i < dataBytes.length; i++){
                bytes[arrayIndex] = dataBytes[i];
                arrayIndex++;
            }
        }

        return bytes;
    }

    public int getElementId() {
        return elementType;
    }
    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

    private int computeSize() {

        int size = 0;
        for(JData data : value){
            size += data.toByte().length;
        }

        return size;
    }

    private JData initializeElement(int dataType,byte[] byteData) {

        JData retVal;
        switch(dataType) {

            // Bitmap Data
            case EIGHT_BIT_MAP_DATA:
            case SIXTEEN_BIT_MAP_DATA:
            case TWENTY_FOUR_BIT_MAP_DATA:
            case THIRTY_TWO_BIT_MAP_DATA:
            case FORTY_BIT_MAP_DATA:
            case FORTY_EIGHT_BIT_MAP_DATA:
            case FIFTY_SIX_BIT_MAP_DATA:
            case SIXTY_FOUR_BIT_MAP_DATA:
                return new JBitmap(byteData);

            // Unsigned integer data
            case UNSIGNED_EIGHT_BIT_INT:
            case UNSIGNED_SIXTEEN_BIT_INT:
            case UNSIGNED_TWENTY_FOUR_BIT_INT:
            case UNSIGNED_THIRTY_TWO_BIT_INT:
            case UNSIGNED_FORTY_BIT_INT:
            case UNSIGNED_FORTY_EIGHT_BIT_INT:
            case UNSIGNED_FIFTY_SIX_BIT_INT:
            case UNSIGNED_SIXTY_FOUR_BIT_INT:
                return new JUnsignedInteger(byteData);

            // Signed integer data
            case SIGNED_EIGHT_BIT_INT:
            case SIGNED_SIXTEEN_BIT_INT:
            case SIGNED_TWENTY_FOUR_BIT_INT:
            case SIGNED_THIRTY_TWO_BIT_INT:
            case SIGNED_FORTY_BIT_INT:
            case SIGNED_FORTY_EIGHT_BIT_INT:
            case SIGNED_FIFTY_SIX_BIT_INT:
            case SIGNED_SIXTY_FOUR_BIT_INT:
                return new JSignedInteger(byteData);

            // Floating point data
            case SEMI_PRECISION:
            case SINGLE_PRECISION:
            case DOUBLE_PRECISION:
                return new JDouble(byteData);

            // String data
            case OCTET_STRING:
            case CHARACTER_STRING:
            case LONG_OCTET_STRING:
            case LONG_CHARACTER_STRING:
                return new JString(byteData);
            default:
                return null;
        }
    }

} /*********************************************END OF FILE*************************************************************/
