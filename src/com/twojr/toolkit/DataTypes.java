package com.twojr.toolkit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rcunni002c on 11/7/2016.
 */
public class DataTypes {

    //General Data
    public static final int EIGHT_BIT_DATA = 0x08;
    public static final int SIXTEEN_BIT_DATA = 0x09;
    public static final int TWENTY_FOUR_BIT_DATA = 0x0A;
    public static final int THIRTY_TWO_BIT_DATA = 0x0B;
    public static final int FORTY_BIT_DATA = 0x0C;
    public static final int FORTY_EIGHT_BIT_DATA = 0x0D;
    public static final int FIFTY_SIX_BIT_DATA = 0x0E;
    public static final int SIXTY_FOUR_BIT_DATA = 0x0F;

    //logical
    public static final int BOOLEAN = 0x10;

    //Bitmap
    public static final int EIGHT_BIT_MAP_DATA = 0x18;
    public static final int SIXTEEN_BIT_MAP_DATA = 0x19;
    public static final int TWENTY_FOUR_BIT_MAP_DATA = 0x1A;
    public static final int THIRTY_TWO_BIT_MAP_DATA = 0x1B;
    public static final int FORTY_BIT_MAP_DATA = 0x1C;
    public static final int FORTY_EIGHT_BIT_MAP_DATA = 0x1D;
    public static final int FIFTY_SIX_BIT_MAP_DATA = 0x1E;
    public static final int SIXTY_FOUR_BIT_MAP_DATA = 0x1F;

    //unsigned
    public static final int UNSIGNED_EIGHT_BIT_INT = 0x20;
    public static final int UNSIGNED_SIXTEEN_BIT_INT = 0x21;
    public static final int UNSIGNED_TWENTY_FOUR_BIT_INT = 0x22;
    public static final int UNSIGNED_THIRTY_TWO_BIT_INT = 0x23;
    public static final int UNSIGNED_FORTY_BIT_INT = 0x24;
    public static final int UNSIGNED_FORTY_EIGHT_BIT_INT = 0x25;
    public static final int UNSIGNED_FIFTY_SIX_BIT_INT = 026;
    public static final int UNSIGNED_SIXTY_FOUR_BIT_INT = 0x27;

    //signed
    public static final int SIGNED_EIGHT_BIT_INT = 0x28;
    public static final int SIGNED_SIXTEEN_BIT_INT = 0x29;
    public static final int SIGNED_TWENTY_FOUR_BIT_INT = 0x2A;
    public static final int SIGNED_THIRTY_TWO_BIT_INT = 0x2B;
    public static final int SIGNED_FORTY_BIT_INT = 0x2C;
    public static final int SIGNED_FORTY_EIGHT_BIT_INT = 0x2D;
    public static final int SIGNED_FIFTY_SIX_BIT_INT = 0x2E;
    public static final int SIGNED_SIXTY_FOUR_BIT_INT = 0x2F;

    //Enumeration
    public static final int EIGHT_BIT_ENUMERATION = 0x30;
    public static final int SIXTEEN_BIT_ENUMERATION = 0x31;

    //Floating Point
    public static final int SEMI_PRECISION = 0x38;
    public static final int SINGLE_PRECISION = 0x39;
    public static final int DOUBLE_PRECISION = 0x3A;

    //String
    public static final int OCTET_STRING = 0x41;
    public static final int CHARACTER_STRING = 0x42;
    public static final int LONG_OCTET_STRING = 0x43;
    public static final int LONG_CHARARACTER_STRING = 0x44;

    //Ordered Seqeunce
    public static final int ARRAY = 0x48;
    public static final int STRUCTURE = 0x4C;

    //Collection
    public static final int SET = 0x50;
    public static final int BAG = 0x51;

    //Time
    public static final int TIME_OF_DAY = 0xE0;
    public static final int DATE = 0xE1;
    public static final int UTC_TIME = 0xE2;

    //Identifier
    public static final int CLUSTER_ID = 0xE8;
    public static final int ATTRIBUTE_ID = 0xE9;
    public static final int BACNET_OID = 0xEA;

    //Miscelleneous
    public static final int IEEE_ADDRESS = 0xF0;
    public static final int SECURITY_KEY = 0xF1;

    //================================================================
    // Data Size
    //================================================================
    public static final Map<Integer, Integer> dataSizeMap = new HashMap<Integer, Integer>();

    static {
        dataSizeMap.put(EIGHT_BIT_DATA, 1);
        dataSizeMap.put(SIXTEEN_BIT_DATA, 2);
        dataSizeMap.put(TWENTY_FOUR_BIT_DATA, 3);
        dataSizeMap.put(THIRTY_TWO_BIT_DATA, 4);
        dataSizeMap.put(FORTY_BIT_DATA, 5);
        dataSizeMap.put(FORTY_EIGHT_BIT_DATA, 6);
        dataSizeMap.put(FIFTY_SIX_BIT_DATA, 7);
        dataSizeMap.put(SIXTY_FOUR_BIT_DATA, 8);

        dataSizeMap.put(BOOLEAN, 1);

        dataSizeMap.put(EIGHT_BIT_MAP_DATA, 1);
        dataSizeMap.put(SIXTEEN_BIT_MAP_DATA, 2);
        dataSizeMap.put(TWENTY_FOUR_BIT_MAP_DATA, 3);
        dataSizeMap.put(THIRTY_TWO_BIT_MAP_DATA, 4);
        dataSizeMap.put(FORTY_BIT_MAP_DATA, 5);
        dataSizeMap.put(FORTY_EIGHT_BIT_MAP_DATA, 6);
        dataSizeMap.put(FIFTY_SIX_BIT_MAP_DATA, 7);
        dataSizeMap.put(SIXTY_FOUR_BIT_MAP_DATA, 8);

        dataSizeMap.put(UNSIGNED_EIGHT_BIT_INT, 1);
        dataSizeMap.put(UNSIGNED_SIXTEEN_BIT_INT, 2);
        dataSizeMap.put(UNSIGNED_TWENTY_FOUR_BIT_INT, 3);
        dataSizeMap.put(UNSIGNED_THIRTY_TWO_BIT_INT, 4);
        dataSizeMap.put(UNSIGNED_FORTY_BIT_INT, 5);
        dataSizeMap.put(UNSIGNED_FORTY_EIGHT_BIT_INT, 6);
        dataSizeMap.put(UNSIGNED_FIFTY_SIX_BIT_INT, 7);
        dataSizeMap.put(UNSIGNED_SIXTY_FOUR_BIT_INT, 8);

        dataSizeMap.put(SIGNED_EIGHT_BIT_INT, 1);
        dataSizeMap.put(SIGNED_SIXTEEN_BIT_INT, 2);
        dataSizeMap.put(SIGNED_TWENTY_FOUR_BIT_INT, 3);
        dataSizeMap.put(SIGNED_THIRTY_TWO_BIT_INT, 4);
        dataSizeMap.put(SIGNED_FORTY_BIT_INT, 5);
        dataSizeMap.put(SIGNED_FORTY_EIGHT_BIT_INT, 6);
        dataSizeMap.put(SIGNED_FIFTY_SIX_BIT_INT, 7);
        dataSizeMap.put(SIGNED_SIXTY_FOUR_BIT_INT, 8);

        dataSizeMap.put(EIGHT_BIT_ENUMERATION, 1);
        dataSizeMap.put(SIXTEEN_BIT_ENUMERATION, 2);

        dataSizeMap.put(SINGLE_PRECISION, 4);
        dataSizeMap.put(DOUBLE_PRECISION, 8);
        dataSizeMap.put(SEMI_PRECISION, 2);


        dataSizeMap.put(IEEE_ADDRESS, 8);
        dataSizeMap.put(CHARACTER_STRING, -1);

    }


    //================================================================
    // Defaults
    //================================================================
    public static final byte DEFAULT_COMMAND = (byte) 0xDE;

}
