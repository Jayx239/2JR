package com.twojr.toolkit;

/**
 * Created by rcunni202 on 5/13/2017.
 */
public enum JDataTypes {

    //General Data
     EIGHT_BIT_DATA(0x08),
     SIXTEEN_BIT_DATA(0x09),
     TWENTY_FOUR_BIT_DATA(0x0A),
     THIRTY_TWO_BIT_DATA(0x0B),
     FORTY_BIT_DATA(0x0C),
     FORTY_EIGHT_BIT_DATA(0x0D),
     FIFTY_SIX_BIT_DATA(0x0E),
     SIXTY_FOUR_BIT_DATA(0x0F),

    //logical
     BOOLEAN(0x10),

    //Bitmap
     EIGHT_BIT_MAP_DATA(0x18),
     SIXTEEN_BIT_MAP_DATA(0x19),
     TWENTY_FOUR_BIT_MAP_DATA(0x1A),
     THIRTY_TWO_BIT_MAP_DATA(0x1B),
     FORTY_BIT_MAP_DATA(0x1C),
     FORTY_EIGHT_BIT_MAP_DATA(0x1D),
     FIFTY_SIX_BIT_MAP_DATA(0x1E),
     SIXTY_FOUR_BIT_MAP_DATA(0x1F),

    //unsigned
     UNSIGNED_EIGHT_BIT_INT(0x20),
     UNSIGNED_SIXTEEN_BIT_INT(0x21),
     UNSIGNED_TWENTY_FOUR_BIT_INT(0x22),
     UNSIGNED_THIRTY_TWO_BIT_INT(0x23),
     UNSIGNED_FORTY_BIT_INT(0x24),
     UNSIGNED_FORTY_EIGHT_BIT_INT(0x25),
     UNSIGNED_FIFTY_SIX_BIT_INT(0x26),
     UNSIGNED_SIXTY_FOUR_BIT_INT(0x27),

    //signed
     SIGNED_EIGHT_BIT_INT(0x28),
     SIGNED_SIXTEEN_BIT_INT(0x29),
     SIGNED_TWENTY_FOUR_BIT_INT(0x2A),
     SIGNED_THIRTY_TWO_BIT_INT(0x2B),
     SIGNED_FORTY_BIT_INT(0x2C),
     SIGNED_FORTY_EIGHT_BIT_INT(0x2D),
     SIGNED_FIFTY_SIX_BIT_INT(0x2E),
     SIGNED_SIXTY_FOUR_BIT_INT(0x2F),

    //Enumeration
     EIGHT_BIT_ENUMERATION(0x30),
     SIXTEEN_BIT_ENUMERATION(0x31),

    //Floating Point
     SEMI_PRECISION(0x38),
     SINGLE_PRECISION(0x39),
     DOUBLE_PRECISION(0x3A),

    //String
     OCTET_STRING(0x41),
     CHARACTER_STRING(0x42),
     LONG_OCTET_STRING(0x43),
     LONG_CHARACTER_STRING(0x44),

    //Ordered Sequence
     ARRAY(0x48),
     STRUCTURE(0x4C),

    //Collection
     SET(0x50),
     BAG(0x51),

    //Time
     TIME_OF_DAY(0xE0),
     DATE(0xE1),
     UTC_TIME(0xE2),

    //Identifier
     CLUSTER_ID(0xE8),
     ATTRIBUTE_ID(0xE9),
     BACNET_OID(0xEA),

    //Miscellaneous
     IEEE_ADDRESS(0xF0),
     SECURITY_KEY(0xF1);
    
    private int id;
    
    JDataTypes(int id){
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
