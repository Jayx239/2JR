package com.twojr.toolkit;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JAddress extends JData{

    public final static String ADDRESS = "Address";

    private byte[] address;


    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JAddress(){
        super(DataTypes.IEEE_ADDRESS, ADDRESS, JDataSizes.SIXTY_FOUR_BIT);
    }

    public JAddress(byte[] address) {
        super(DataTypes.IEEE_ADDRESS, ADDRESS, JDataSizes.SIXTY_FOUR_BIT);
        this.address = address;
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public byte[] getAddress() {
        return address;
    }

    public void setAddress(byte[] address) {
        this.address = address;
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

        String output = "Address: ";

        for(byte bits : address)
        {
            output  += bits + ":";

        }

        output += "\n";

        return output;

    }

    @Override
    public byte[] toByte() {

        return address;
        
    }


    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
