package com.twojr.toolkit;

import com.twojr.toolkit.integer.JSignedInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;

import static com.twojr.toolkit.DataTypes.*;

/**
 * Created by Jason on 4/29/2017.
 */
public class DataFactory {

    public static JData getData(int id, byte[] dataStream) {

        switch (id) {
            case EIGHT_BIT_MAP_DATA:
            case SIXTEEN_BIT_MAP_DATA:
            case TWENTY_FOUR_BIT_MAP_DATA:
            case THIRTY_TWO_BIT_MAP_DATA:
            case FORTY_BIT_MAP_DATA:
            case FORTY_EIGHT_BIT_MAP_DATA:
            case FIFTY_SIX_BIT_MAP_DATA:
            case SIXTY_FOUR_BIT_MAP_DATA:
                return new JBitmap(dataStream);

            case UNSIGNED_EIGHT_BIT_INT:
            case UNSIGNED_SIXTEEN_BIT_INT:
            case UNSIGNED_TWENTY_FOUR_BIT_INT:
            case UNSIGNED_THIRTY_TWO_BIT_INT:
            case UNSIGNED_FORTY_BIT_INT:
            case UNSIGNED_FORTY_EIGHT_BIT_INT:
            case UNSIGNED_FIFTY_SIX_BIT_INT:
            case UNSIGNED_SIXTY_FOUR_BIT_INT:
                return new JUnsignedInteger(dataStream);

            case SIGNED_EIGHT_BIT_INT:
            case SIGNED_SIXTEEN_BIT_INT:
            case SIGNED_TWENTY_FOUR_BIT_INT:
            case SIGNED_THIRTY_TWO_BIT_INT:
            case SIGNED_FORTY_BIT_INT:
            case SIGNED_FORTY_EIGHT_BIT_INT:
            case SIGNED_FIFTY_SIX_BIT_INT:
            case SIGNED_SIXTY_FOUR_BIT_INT:
                return new JSignedInteger(dataStream);

            case EIGHT_BIT_ENUMERATION:
            case SIXTEEN_BIT_ENUMERATION:
                break;
            case SEMI_PRECISION:
            case SINGLE_PRECISION:
            case DOUBLE_PRECISION:
                return new JDouble(dataStream);
            case OCTET_STRING:
            case CHARACTER_STRING:
            case LONG_OCTET_STRING:
            case LONG_CHARACTER_STRING:
                return new JString(dataStream);

            case ARRAY:
                return new JArray();
            case IEEE_ADDRESS:
                return new JAddress(dataStream);
                

        }
    }
}
