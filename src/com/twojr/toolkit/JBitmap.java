package com.twojr.toolkit;

import java.util.HashMap;

import static com.twojr.toolkit.DataTypes.*;
import static com.twojr.toolkit.JDataSizes.*;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JBitmap extends JData {

    public static final String BITMAP = "Bitmap";

    private HashMap<Integer, Integer> value;
    private HashMap<Integer, String> params;


    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JBitmap() {

        super(EIGHT_BIT_MAP_DATA, BITMAP, JDataSizes.EIGHT_BIT);

        this.params = new HashMap<>();

        for (int i = 0; i < getSize(); i++) {
            params.put(i, "");
        }


        this.value = new HashMap<>();

        for (int i = 0; i < getSize(); i++) {
            value.put(i, 0);
        }

    }

    public JBitmap(JDataSizes size) {

        super(EIGHT_BIT_MAP_DATA, BITMAP, size);
        setId(computeId(size));

        this.params = new HashMap<>();

        for (int i = 0; i < getSize(); i++) {
            params.put(i, "");
        }


        this.value = new HashMap<>();

        for (int i = 0; i < getSize(); i++) {
            value.put(i, 0);
        }

    }

    public JBitmap(JDataSizes size, HashMap<Integer, Integer> value, HashMap<Integer, String> params) {

        super(EIGHT_BIT_MAP_DATA, BITMAP, size);

        setId(computeId(size));
        this.value = value;
        this.params = params;

    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public HashMap<Integer, String> getParams() {
        return params;
    }

    public void setParams(HashMap<Integer, String> params) {
        this.params = params;
    }

    public HashMap<Integer, Integer> getValue() {
        return value;
    }

    public void setValue(HashMap<Integer, Integer> value) {
        this.value = value;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    public void putValue(int i, boolean used) {

        if (i > 0 && i <= getSize() * 8) {

            if (used) {

                value.put(i, 1);

            } else {

                value.put(i, 0);

            }

        }

    }

    public int getValues(int i) {

        if (i > 0 && i <= getSize() * 8) {

            return value.get(i);

        } else {


            return -1;

        }

    }


    @Override
    public byte[] toByte() {

        byte[] bytes = new byte[getSize()];

        for (int i = 0; i < getSize(); i++) {

            String byteStr = "";

            for (int j = 0; j < 8; j++) {

                byteStr += value.get(j + i * 8);

            }

            bytes[i] = Byte.parseByte(byteStr);
        }

        return bytes;

    }

    @Override
    public String print() {

        String output = "";

        for (int key : value.keySet()) {

            output += key + ". value: " + value.get(key) + ", param: " + params.get(key) + "\n";

        }

        System.out.print(output);

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

} /*********************************************END OF FILE*************************************************************/
