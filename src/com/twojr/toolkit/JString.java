package com.twojr.toolkit;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public class JString extends JData {

    private String value;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JString(){

    }

    public JString(int id, String name, int size, String value) {
        super(id, name, size);
        this.value = value;
    }


    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================

    @Override
    public byte[] toByte() {

        return value.getBytes();

    }

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

        String output = "String Value: " + value + "\n";

        System.out.print(output);

        return output;

    }

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/
