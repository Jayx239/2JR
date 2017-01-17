package com.twojr.toolkit;

import static com.twojr.toolkit.JDataSizes.*;

/**
 * Created by rcunni002c on 11/17/2016.
 */
public abstract class JData extends JIdentity implements ISendable{

    private int size;

    //==================================================================================================================
    // Constructor(s)
    //==================================================================================================================

    public JData() {
    }

    public JData(int id, String name, int size) {
        
        super(id, name);
        this.size = size;

    }

    public JData(int id, String name, JDataSizes size) {
        super(id, name);

        switch (size){

            case EIGHT_BIT:
                this.size = 1;
                break;

            case SIXTEEN_BIT:
                this.size = 2;
                break;

            case TWENTY_FOUR_BIT:
                this.size = 3;
                break;

            case THIRTY_TWO_BIT:
                this.size = 4;
                break;

            case FORTY_BIT:
                this.size = 5;
                break;

            case FORTY_EIGHT_BIT:
                this.size = 6;
                break;

            case FIFTY_SIX_BIT:
                this.size = 7;
                break;

            case SIXTY_FOUR_BIT:
                this.size = 8;
                break;

            case SEVENTY_TWO_BIT:
                this.size = 9;
                break;

            case EIGHTY_BIT:
                this.size = 10;
                break;

            case EIGHTY_EIGHT_BIT:
                this.size = 11;
                break;

            case NINETY_SIX_BIT:
                this.size = 12;
                break;

            case HUNDRED_FOUR_BIT:
                this.size = 13;
                break;

            case HUNDRED_TWELVE_BIT:
                this.size = 14;
                break;

            case HUNDRED_TWENTY_BIT:
                this.size = 15;
                break;

            case HUNDRED_TWENTY_EIGHT_BIT:
                this.size = 16;
                break;
        }
    }

    //==================================================================================================================
    // Getter(s) & Setter(s)
    //==================================================================================================================

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    //==================================================================================================================
    // Public Functions(s)
    //==================================================================================================================


    public abstract byte[] compress();      //Compresses data into the smallest format possible
    public abstract int getSavings();       //Reports the numbers of bytes saved by compression

    //==================================================================================================================
    // Private Functions(s)
    //==================================================================================================================

} /*********************************************END OF FILE*************************************************************/

