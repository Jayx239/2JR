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
        if(address == null){
            throw new IllegalArgumentException("JAddress input byte array address cannot be null");
        }
        this.address = address;
    }

    public JAddress(long addr) {
        super(DataTypes.IEEE_ADDRESS, ADDRESS, JDataSizes.SIXTY_FOUR_BIT);
        setAddress(addr);
    }

    public JAddress(String address) {
        super(DataTypes.IEEE_ADDRESS, ADDRESS, JDataSizes.SIXTY_FOUR_BIT);
        setAddress(address);
    }

    private void setAddress(String address) {
        long tempAddress = 0;
        String tempValue = "";
        int offset = 7;
        byte byteAddr[] = new byte[8];

        for(int i=0; i<address.length(); i++) {
            if(address.charAt(i) == ':') {
                byteAddr[offset--] = (byte) (Integer.parseInt(tempValue) & 0xFF);
                tempValue = "";
            }
            else
                tempValue+=address.charAt(i);
        }

        byteAddr[offset--] = (byte) (Integer.parseInt(tempValue) & 0xFF);
        setAddress(byteAddr);

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

    public void setAddress(long addr) {

        byte[]  bArray = new byte[getSize()];

        for(int i=0; i<getSize(); i++) {
            bArray[i] = (byte) ((addr >> (i*8)) & 0xff);
        }

        this.address = bArray;
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

        for(int i=7; i>0; i--) {
            output  += address[i] + ":";
        }
        output+= address[0];
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
