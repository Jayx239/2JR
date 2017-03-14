package Demo;
import com.twojr.protocol.network.IPacket;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.*;
import com.twojr.toolkit.integer.JSignedInteger;
import com.twojr.toolkit.integer.JUnsignedInteger;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * Created by Jason on 3/6/2017.
 */
public class SpringDemo {

    public SpringDemo() {

    }

    Scanner reader = new Scanner(System.in);

    public void printHelpMenu() {
        System.out.println("Commands: ");
        System.out.println("-h: Help");
        System.out.println("-q: quit");
        System.out.println("-dt: Lists supported data types that can be built");
        System.out.println("build: Begins object build prompt");
        System.out.println();
    }

    public void printDataTypes() {
        System.out.println("0: Address");
        System.out.println("1: Array");
        System.out.println("2: Bit");
        System.out.println("3: Bitmap");
        System.out.println("4: Boolean");
        System.out.println("5: Double");
        System.out.println("6: Enumeration");
        System.out.println("7: Integer");
        System.out.println("8: String");
        System.out.println("9: Network Packet");
        System.out.println("-1: Cancel");
        System.out.println();

    }

    public void printArrayDataTypes() {
        System.out.println("0: Address");
        System.out.println("3: Bitmap");
        System.out.println("5: Double");
        System.out.println("7: Integer");
        System.out.println("8: String");
        System.out.println("-1: Cancel");
        System.out.println();
    }

    private void printByteB(byte[] bArray) {
        int bInd = 0;
        for(byte b : bArray) {

            System.out.print("Byte " + bInd + ": ");
            System.out.print(b>>7 & 0x01);
            System.out.print(b>>6 & 0x01);
            System.out.print(b>>5 & 0x01);
            System.out.print(b>>4 & 0x01);
            System.out.print(b>>3 & 0x01);
            System.out.print(b>>2 & 0x01);
            System.out.print(b>>1 & 0x01);
            System.out.print(b & 0x01);
            System.out.println();
            bInd++;
        }

        System.out.println();
    }

    public void printNetworkControlTypes() {
        System.out.println("Network Control Types");
        for(int i=0; i< IPacket.networkControlFlags.values().length; i++) {
            System.out.println( i + ": " + IPacket.networkControlFlags.values()[i]);
        }
    }
    public void printNetworkCommandFrameTypes() {
        System.out.println("Network Command Frame Types");
        for(int i=0; i< IPacket.networkControlFlags.values().length; i++) {
            System.out.println( i + ": " + IPacket.networkLayerCommands.values()[i]);
        }
    }

    public JData build() {
        return build(-1);
    }

    public JData build(int dataTypeIn) {
        boolean invalidDatatype = true;
        int dataType;
        boolean suppressTestAttrPrompt = false;

        while (invalidDatatype) {
            try {
                if (dataTypeIn >= 0 && dataTypeIn < 10) {
                    dataType = dataTypeIn;
                    suppressTestAttrPrompt = true;
                }
                else {
                    printDataTypes();
                    System.out.println("Enter the data type you would like to build: ");
                    dataType = Integer.parseInt(reader.nextLine());
                }
                switch (dataType) {
                    case 0:
                        // Address
                        System.out.print("Enter a IEEE Address (64 bit value): ");
                        long addr = Long.parseLong(reader.nextLine());

                        // Initialize address
                        byte[] bValue = new byte[8];
                        ByteBuffer.wrap(bValue).putLong(addr);
                        JAddress jAddr = new JAddress(addr);

                        // Encoding
                        System.out.print("Encoding Address : " + jAddr.print());
                        printByteB(jAddr.toByte());

                        // Decoding
                        System.out.print("Decoding: ");
                        System.out.print(new JAddress(jAddr.toByte()).print());

                        // Test attributes
                        if(!suppressTestAttrPrompt)
                            testAttributes(jAddr,0);

                        return jAddr;

                    case 1:
                        // Array
                        System.out.print("Starting array builder, please enter array type (-dt to list types):  ");
                        JArray jArr;

                        // Get data type
                        int nextDataType = Integer.parseInt(reader.nextLine());

                        // Get array length
                        System.out.print("Enter the number of elements in the array: ");
                        int arrSize = Integer.parseInt(reader.nextLine());

                        // Declare array
                        JData nextElem;
                        JData[] inArr = null;
                        switch (nextDataType) {
                            case 0:
                                inArr = new JAddress[arrSize];
                                break;
                            case 1:
                                break;
                            case 2:
                                inArr = new JBit[arrSize];
                                break;
                            case 3:
                                inArr = new JBitmap[arrSize];
                                break;
                            case 4:
                                inArr = new JBoolean[arrSize];
                                break;
                            case 5:
                                inArr = new JDouble[arrSize];
                                break;
                            case 6:
                                inArr = new JEnumeration[arrSize];
                                break;
                            case 7:
                                inArr = new JInteger[arrSize];
                                break;
                            case 8:
                                inArr = new JString[arrSize];
                                break;
                            case 9:
                                // Network packet
                                break;
                            default:
                                break;
                        }

                        jArr = new JArray(arrSize);

                        // Initialize array
                        for (int i = 0; i < arrSize; i++) {
                            inArr[i] = build(nextDataType);
                            jArr.setElementAt(i,inArr[i]);
                        }
                        System.out.println(arrSize);
                        // Initialize JArray

                        // Encode array
                        System.out.println("Encoding: ");
                        for (int i = 0; i < arrSize; i++) {
                            //System.out.println("Element [" + i + "]: " + jArr.print());
                            printByteB(inArr[i].toByte());
                        }

                        suppressTestAttrPrompt = false;
                        // Test attributes
                        if(!suppressTestAttrPrompt)
                            testAttributes(jArr,1);

                        return jArr;

                    case 2:
                        // Bit
                        System.out.print("Enter a bit value to encode (0/1): ");
                        int bitVal = Integer.parseInt(reader.nextLine());

                        // Initialize
                        JBit bit = new JBit("Bit", JDataSizes.EIGHT_BIT, bitVal == 0 ? false : true);
                        printByteB(bit.toByte());

                        // Decode
                        System.out.print("Decoding: ");
                        System.out.println(new JBit(bit.toByte()).getValue());

                        // Test attributes
                        if(!suppressTestAttrPrompt)
                            testAttributes(bit,2);

                        return bit;

                    case 3:
                        // Bitmap
                        System.out.print("Enter the size of the bitmap: ");
                        int numBytes = Integer.parseInt(reader.nextLine());

                        // Declare bitmap
                        JBitmap jBitmap = new JBitmap(new byte[numBytes]);

                        // Initialize bitmap with user input
                        for (int i = numBytes - 1; i >= 0; i--) {
                            System.out.print("Enter the value of the next byte in the bitmap: ");
                            jBitmap.setValue(Integer.parseInt(reader.nextLine()), i);
                        }

                        // Encode
                        System.out.println("Encoding: ");
                        printByteB(jBitmap.toByte());

                        // Decode
                        System.out.println("Decoding: \n" + jBitmap.print());

                        // Test attributes
                        if(!suppressTestAttrPrompt)
                            testAttributes(jBitmap,3);

                        return jBitmap;
                    case 4:
                        // Boolean
                        System.out.print("Enter a boolean to encode(T/F): ");
                        String bValStr = reader.nextLine();

                        // Declare bool
                        JBoolean bool = null;

                        // Set true, false, or invalid
                        if (bValStr.toLowerCase().equals("t"))
                            bool = new JBoolean(true);
                        else if (bValStr.toLowerCase().equals("f"))
                            bool = new JBoolean(false);
                        else {
                            System.out.println("Invalid Entry!");
                        }

                        // Encode
                        System.out.println("Encoding: ");
                        printByteB(bool.toByte());

                        // Decoding
                        System.out.println("Decoding: " + new JBoolean(bool.toByte()).getValue());

                        // Test attributes
                        if(!suppressTestAttrPrompt)
                            testAttributes(bool,4);

                        return bool;
                    case 5:
                        // Double
                        System.out.print("Enter a double to encode: ");
                        double dValue = Double.parseDouble(reader.nextLine());

                        // Initialize double
                        JDouble jDouble = new JDouble(dValue);
                        printByteB(jDouble.toByte());

                        // Decode
                        System.out.print("Decoding: ");

                        // Encode
                        System.out.println(new JDouble(jDouble.toByte()).getValue());

                        // Test attributes
                        if(!suppressTestAttrPrompt)
                            testAttributes(jDouble,5);

                        return jDouble;

                    case 6:
                        // Enumeration
                    case 7:
                        // Integer
                        System.out.println("Signed(0) or Unsigned (1): ");
                        int sign = Integer.parseInt(reader.nextLine());

                        System.out.print("Enter an integer to encode: ");
                        int intVal = Integer.parseInt(reader.nextLine());


                        if (sign == 1) {
                            // Initialize Unsigned
                            JUnsignedInteger jInt = new JUnsignedInteger(new JUnsignedInteger(JDataSizes.THIRTY_TWO_BIT, intVal).compress());

                            // Encode
                            System.out.println("Encoding: ");
                            printByteB(jInt.toByte());

                            // Decode
                            System.out.println("Decoding: ");
                            System.out.println(new JUnsignedInteger(jInt.toByte()).getValue());

                            // Test attributes
                            if(!suppressTestAttrPrompt)
                                testAttributes(jInt,7,1);

                            return jInt;
                        } else if (sign == 0) {
                            // Initialize Signed
                            JSignedInteger jInt = new JSignedInteger(new JSignedInteger(JDataSizes.THIRTY_TWO_BIT, intVal).compress());

                            // Encode
                            System.out.println("Encoding: ");
                            printByteB(jInt.toByte());

                            // Decode
                            System.out.print("Decoding: ");
                            System.out.println(new JSignedInteger(jInt.toByte()).getValue());

                            // Test attributes
                            if(!suppressTestAttrPrompt)
                                testAttributes(jInt,7,0);

                            return jInt;
                        }

                        System.out.println("Invalid entry");
                        return null;

                    case 8:
                        // String
                        System.out.print("Enter a string to encode: ");
                        String str = reader.nextLine();

                        // Initialize jstring
                        JString jStr = new JString();
                        jStr.setValue(str);

                        // Encode
                        System.out.println("Encoded String: ");
                        byte[] encodedStr = jStr.toByte();
                        printByteB(encodedStr);

                        // Decode
                        System.out.println("Decoding: " + new JString(encodedStr).getValue());

                        // Test attributes
                        if(!suppressTestAttrPrompt)
                            testAttributes(jStr,8);
                        return jStr;

                    case 9:
                        // Network Packet
                        System.out.println("Network Packet Builder");

                        System.out.print("Enter sequence number: ");
                        JUnsignedInteger seqNumber = new JUnsignedInteger(JDataSizes.EIGHT_BIT,Integer.parseInt(reader.nextLine()));

                        printNetworkControlTypes();
                        System.out.print("Select network control: ");
                        JUnsignedInteger networkControl = new JUnsignedInteger(JDataSizes.EIGHT_BIT,Integer.parseInt(reader.nextLine()));

                        System.out.print("Enter mac address: ");
                        JAddress macAddress = new JAddress(Long.parseLong(reader.nextLine()));

                        printNetworkCommandFrameTypes();
                        System.out.print("Enter command frame: ");
                        JUnsignedInteger commandFrame = new JUnsignedInteger(JDataSizes.EIGHT_BIT,Integer.parseInt(reader.nextLine()));

                        System.out.print("Enter payload: ");
                        JBitmap payload = (JBitmap) build(3);

                        NetworkPacket netPacket = new NetworkPacket(seqNumber,networkControl,macAddress,commandFrame,payload.toByte());
                        System.out.println("Encoding network packet: ");
                        printByteB(netPacket.toByte());

                        System.out.println("Decoding: ");
                        System.out.print(new NetworkPacket(netPacket.toByte()).print());
                        invalidDatatype = false;

                        testNetworkPacketAttributes(netPacket);
                        break;

                    default:

                        if (dataType == -1) {
                            return null;
                        }

                        System.out.println("Please select a valid datatype");
                        printDataTypes();
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input\n Error message: " + ex.getMessage());
            }
        }
        return null;
    }

    public void printJDataMethods() {
        System.out.println("JData Methods");
        System.out.println("-cmd: print commands");
        System.out.println("0: getSize()");
        System.out.println("1: setSize()");
        System.out.println("2: getValue()");
        System.out.println("3: setValue()");
        System.out.println("4: print()");
        System.out.println("5: toByte()");
        System.out.println("-1: cancel");

    }

    public void printNetworkPacketMethods() {
        System.out.println("Network Packet Methods");
        System.out.println("-cmd: print commands");
        System.out.println("0: getSequenceNumber()");
        System.out.println("1: setSequenceNumber()");
        System.out.println("2: getNetworkControl()");
        System.out.println("3: setNetworkControl()");
        System.out.println("4: getMacAddress()");
        System.out.println("5: setMacAddress()");
        System.out.println("6: getCommandFrame()");
        System.out.println("7: setCommandFrame()");
        System.out.println("8: getPayload()");
        System.out.println("9: setPayload()");
        System.out.println("10: toByte()");
        System.out.println("11: print()");
        System.out.println("12: printPayload()");
        System.out.println("-1: Exit Network Packet Attribute Utility");

    }

    public void testNetworkPacketAttributes(NetworkPacket networkPacket) {
        boolean invalidDatatype = false;
        int command;
        String commandStr = "";
        boolean quit;
        System.out.println("Network Packet Attribute Utility\nEnter -cmd for options");

        while (!invalidDatatype){
            try {
                System.out.println("Network Packet Method: ");
                commandStr = reader.nextLine();

                if(commandStr.equals("-cmd")) {
                    printNetworkPacketMethods();
                    continue;
                }

                command = Integer.parseInt(commandStr);
                switch (command) {
                    case 0:
                        // Get sequence number
                        testAttributes(networkPacket.getSequenceNumber(),7,1);
                        break;
                    case 1:
                        // Set sequence number
                        networkPacket.setSequenceNumber((JUnsignedInteger) build(7));
                        break;
                    case 2:
                        // Get network control
                        testAttributes(networkPacket.getNetworkControl(),7,1);
                        break;
                    case 3:
                        // Set network control
                        networkPacket.setNetworkControl((JUnsignedInteger) build(7));
                        break;
                    case 4:
                        // Get mac address
                        testAttributes(networkPacket.getMacAddress(),7,1);
                        break;
                    case 5:
                        // Set mac address
                        networkPacket.setMacAddress((JAddress) build(0));
                        break;
                    case 6:
                        // Get command frame
                        testAttributes(networkPacket.getCommandFrame(),7,1);
                        break;
                    case 7:
                        // Set command frame
                        networkPacket.setCommandFrame((JUnsignedInteger) build(7));
                        break;
                    case 8:
                        // print payload
                        printByteB(networkPacket.getPayload());
                        break;
                    case 9:
                        // Set payload
                        networkPacket.setPayload(build(4).toByte());
                        break;
                    case 10:
                        // toByte method
                        printByteB(networkPacket.toByte());
                        break;
                    case 11:
                        // Print method
                        System.out.print(networkPacket.print());
                        break;
                    case 12:
                        // print payload
                        System.out.print(networkPacket.printPayload(true));
                        break;
                    case -1:
                        // Exit
                        invalidDatatype = true;
                        break;
                    default:
                        System.out.println("Unrecognized command");
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input\n Error message: " + ex.getMessage());
            }
        }
    }

    public void testAttributes(JData jObj, int objId) {
        testAttributes(jObj,objId,0);
    }

    public void testAttributes(JData jObj, int objId, int sign) {
        boolean invalidDatatype = false;
        int command;
        String commandStr = "";
        boolean quit;
        System.out.println("Object attribute tester\nEnter -cmd for options");

        while (!invalidDatatype){
            try {
                switch (objId) {
                    case 0:
                        // Address
                        JAddress jAddr= (JAddress) jObj;
                        quit = false;
                        while(!quit) {
                            // Prompt for input / get user input
                            System.out.print("Enter method command: ");
                            commandStr = reader.nextLine();

                            // Check for cmd command
                            if(commandStr.equals("-cmd")) {
                                printJDataMethods();
                                continue;
                            }

                            // Parse command
                            command = Integer.parseInt(commandStr);

                            // Perform command
                            switch (command) {
                                case 0:
                                    // Get size
                                    System.out.println(jAddr.getSize());
                                    break;
                                case 1:
                                    // Set size
                                    System.out.print("Enter size: ");
                                    jAddr.setSize(Integer.parseInt(reader.nextLine()));
                                    break;
                                case 2:
                                    // Get value
                                    System.out.println(jAddr.getAddress());
                                    break;
                                case 3:
                                    // Set value
                                    System.out.print("Enter a IEEE Address (64 bit value): ");
                                    jAddr.setAddress(Long.parseLong(reader.nextLine()));
                                    break;

                                case 4:
                                    // Print
                                    System.out.println(jAddr.print());
                                    break;
                                case 5:
                                    // to Byte
                                    printByteB(jAddr.toByte());
                                    break;
                                case -1:
                                    // Quit
                                    quit = true;
                                    break;
                                default:
                                    // Invalid entry
                                    System.out.println("Invalid Entry");
                                    break;
                            }
                        }
                        invalidDatatype = true;
                        break;

                    case 1:
                        // Array
                        JArray jArr= (JArray) jObj;
                        quit = false;
                        while(!quit) {
                            // Prompt for input / get user input
                            System.out.print("Enter method command: ");
                            commandStr = reader.nextLine();

                            // Check for cmd command
                            if(commandStr.equals("-cmd")) {
                                printJDataMethods();
                                continue;
                            }

                            // Parse command
                            command = Integer.parseInt(commandStr);

                            // Perform command
                            switch (command) {
                                case 0:
                                    // Get size
                                    System.out.println(jArr.getSize());
                                    break;
                                case 1:
                                    // Set size
                                    System.out.print("Enter size: ");
                                    jArr.setSize(Integer.parseInt(reader.nextLine()));
                                    break;
                                case 2:
                                    // Get value
                                    System.out.println(jArr.getValue());
                                    break;
                                case 3:
                                    // Set value
                                    System.out.println("Functionallity not yet implemented");
                                    break;

                                case 4:
                                    // Print
                                    System.out.println(jArr.print());
                                    break;
                                case 5:
                                    // to Byte
                                    printByteB(jArr.toByte());
                                    break;
                                case -1:
                                    // Quit
                                    quit = true;
                                    break;
                                default:
                                    // Invalid entry
                                    System.out.println("Invalid Entry");
                                    break;
                            }
                        }
                        invalidDatatype = true;
                        break;

                    case 2:
                        // Bit
                        JBit jBit= (JBit) jObj;
                        quit = false;
                        while(!quit) {
                            // Prompt for input / get user input
                            System.out.print("Enter method command: ");
                            commandStr = reader.nextLine();

                            // Check for cmd command
                            if(commandStr.equals("-cmd")) {
                                printJDataMethods();
                                continue;
                            }

                            // Parse command
                            command = Integer.parseInt(commandStr);

                            // Perform command
                            switch (command) {
                                case 0:
                                    // Get size
                                    System.out.println(jBit.getSize());
                                    break;
                                case 1:
                                    // Set size
                                    System.out.print("Enter size: ");
                                    jBit.setSize(Integer.parseInt(reader.nextLine()));
                                    break;
                                case 2:
                                    // Get value
                                    System.out.println(jBit.getValue());
                                    break;
                                case 3:
                                    // Set value
                                    System.out.print("Set value 1/0: ");
                                    String arg = (reader.nextLine());
                                    if(arg.toLowerCase().equals("1"))
                                        jBit.setValue(true);
                                    else if (arg.toLowerCase().equals("0"))
                                        jBit.setValue(false);
                                    else
                                        System.out.println("Invalid input");

                                    break;

                                case 4:
                                    // Print
                                    System.out.println(jBit.print());
                                    break;
                                case 5:
                                    // to Byte
                                    printByteB(jBit.toByte());
                                    break;
                                case -1:
                                    // Quit
                                    quit = true;
                                    break;
                                default:
                                    // Invalid entry
                                    System.out.println("Invalid Entry");
                                    break;
                            }
                        }
                        invalidDatatype = true;
                        break;

                    case 3:
                        // Bitmap
                        JBitmap jBitmap = (JBitmap) jObj;
                        quit = false;
                        while(!quit) {
                            // Prompt for input / get user input
                            System.out.print("Enter method command: ");
                            commandStr = reader.nextLine();

                            // Check for cmd command
                            if(commandStr.equals("-cmd")) {
                                printJDataMethods();
                                continue;
                            }

                            // Parse command
                            command = Integer.parseInt(commandStr);

                            // Perform command
                            switch (command) {
                                case 0:
                                    // Get size
                                    System.out.println(jBitmap.getSize());
                                    break;
                                case 1:
                                    // Set size
                                    System.out.print("Enter size: ");
                                    jBitmap.setSize(Integer.parseInt(reader.nextLine()));
                                    break;
                                case 2:
                                    // Get value
                                    System.out.println(jBitmap.getValue());
                                    break;
                                case 3:
                                    // Set value
                                    byte[] input = new byte[jBitmap.getSize()];
                                    for(int i=0; i < input.length; i++) {
                                        System.out.print("Enter next byte value for bitmap: ");
                                        byte nextB = (byte) (Integer.parseInt(reader.nextLine()));
                                        jBitmap.setValue(nextB,i);
                                        printByteB(new byte[]{nextB});
                                    }

                                    break;
                                case 4:
                                    // Print
                                    System.out.println(jBitmap.print());
                                    break;
                                case 5:
                                    // to Byte
                                    printByteB(jBitmap.toByte());
                                    break;
                                case -1:
                                    // Quit
                                    quit = true;
                                    break;
                                default:
                                    // Invalid entry
                                    System.out.println("Invalid Entry");
                                    break;
                            }
                        }
                        invalidDatatype = true;
                        break;
                    case 4:
                        // Boolean
                        JBoolean jBool = (JBoolean) jObj;
                        quit = false;
                        while(!quit) {
                            // Prompt for input / get user input
                            System.out.print("Enter method command: ");
                            commandStr = reader.nextLine();

                            // Check for cmd command
                            if(commandStr.equals("-cmd")) {
                                printJDataMethods();
                                continue;
                            }

                            // Parse command
                            command = Integer.parseInt(commandStr);

                            // Perform command
                            switch (command) {
                                case 0:
                                    // Get size
                                    System.out.println(jBool.getSize());
                                    break;
                                case 1:
                                    // Set size
                                    System.out.print("Enter size: ");
                                    jBool.setSize(Integer.parseInt(reader.nextLine()));
                                    break;
                                case 2:
                                    // Get value
                                    System.out.println(jBool.getValue());
                                    break;
                                case 3:
                                    // Set value
                                    System.out.print("Set value T/F: ");
                                    String arg = (reader.nextLine());
                                    if(arg.toLowerCase().equals("t"))
                                        jBool.setValue(true);
                                    else if (arg.toLowerCase().equals("f"))
                                        jBool.setValue(false);
                                    else
                                        System.out.println("Invalid input");

                                    break;
                                case 4:
                                    // Print
                                    System.out.println(jBool.print());
                                    break;
                                case 5:
                                    // to Byte
                                    printByteB(jBool.toByte());
                                    break;
                                case -1:
                                    // Quit
                                    quit = true;
                                    break;
                                default:
                                    // Invalid entry
                                    System.out.println("Invalid Entry");
                                    break;
                            }
                        }
                        invalidDatatype = true;
                        break;

                    case 5:
                        // Double
                        JDouble jDoub = (JDouble) jObj;
                        quit = false;
                        while(!quit) {
                            // Prompt for input / get user input
                            System.out.print("Enter method command: ");
                            commandStr = reader.nextLine();

                            // Check for cmd command
                            if(commandStr.equals("-cmd")) {
                                printJDataMethods();
                                continue;
                            }

                            // Parse command
                            command = Integer.parseInt(commandStr);

                            // Perform command
                            switch (command) {
                                case 0:
                                    // Get size
                                    System.out.println(jDoub.getSize());
                                    break;
                                case 1:
                                    // Set size
                                    System.out.print("Enter size: ");
                                    jDoub.setSize(Integer.parseInt(reader.nextLine()));
                                    break;
                                case 2:
                                    // Get value
                                    System.out.println(jDoub.getValue());
                                    break;
                                case 3:
                                    // Set value
                                    System.out.print("Enter value: ");
                                    jDoub.setValue(Double.parseDouble(reader.nextLine()));
                                    break;
                                case 4:
                                    // Print
                                    System.out.println(jDoub.print());
                                    break;
                                case 5:
                                    // to Byte
                                    printByteB(jDoub.toByte());
                                    break;
                                case -1:
                                    // Quit
                                    quit = true;
                                    break;
                                default:
                                    // Invalid entry
                                    System.out.println("Invalid Entry");
                                    break;
                            }
                        }
                        invalidDatatype = true;
                        break;

                    case 6:
                        // Enumeration
                    case 7:
                        // Integer
                        JInteger jInteger;
                        if(sign == 0)
                            jInteger = (JSignedInteger) jObj;
                        else
                            jInteger = (JUnsignedInteger) jObj;

                        quit = false;
                        while(!quit) {
                            // Prompt for input / get user input
                            System.out.print("Enter method command: ");
                            commandStr = reader.nextLine();

                            // Check for cmd command
                            if(commandStr.equals("-cmd")) {
                                printJDataMethods();
                                continue;
                            }

                            // Parse command
                            command = Integer.parseInt(commandStr);

                            // Perform command
                            switch (command) {
                                case 0:
                                    // Get size
                                    System.out.println(jInteger.getSize());
                                    break;
                                case 1:
                                    // Set size
                                    System.out.print("Enter size: ");
                                    jInteger.setSize(Integer.parseInt(reader.nextLine()));
                                    break;
                                case 2:
                                    // Get value
                                    System.out.println(jInteger.getValue());
                                    break;
                                case 3:
                                    // Set value
                                    System.out.print("Enter value: ");
                                    jInteger.setValue(Integer.parseInt(reader.nextLine()));
                                    break;
                                case 4:
                                    // Print
                                    System.out.println(jInteger.print());
                                    break;
                                case 5:
                                    // to Byte
                                    printByteB(jInteger.toByte());
                                    break;
                                case -1:
                                    // Quit
                                    quit = true;
                                    break;
                                default:
                                    // Invalid entry
                                    System.out.println("Invalid Entry");
                                    break;
                            }
                        }
                        invalidDatatype = true;
                        break;

                    case 8:
                        // String
                        JString jStr = (JString) jObj;
                        quit = false;
                        while(!quit) {
                            // Prompt for input / get user input
                            System.out.print("Enter method command: ");
                            commandStr = reader.nextLine();

                            // Check for cmd command
                            if(commandStr.equals("-cmd")) {
                                printJDataMethods();
                                continue;
                            }

                            // Parse command
                            command = Integer.parseInt(commandStr);

                            // Perform command
                            switch (command) {
                                case 0:
                                    // Get size
                                    System.out.println(jStr.getSize());
                                    break;
                                case 1:
                                    // Set size
                                    System.out.print("Enter size: ");
                                    jStr.setSize(Integer.parseInt(reader.nextLine()));
                                    break;
                                case 2:
                                    // Get value
                                    System.out.println(jStr.getValue());
                                    break;
                                case 3:
                                    // Set value
                                    System.out.print("Enter value: ");
                                    jStr.setValue(reader.nextLine());
                                    break;
                                case 4:
                                    // Print
                                    System.out.println(jStr.print());
                                    break;
                                case 5:
                                    // to Byte
                                    printByteB(jStr.toByte());
                                    break;
                                case -1:
                                    // Quit
                                    quit = true;
                                    break;
                                default:
                                    // Invalid entry
                                    System.out.println("Invalid Entry");
                                    break;
                            }
                        }
                        invalidDatatype = true;
                        break;
                    case 9:
                        // Network Packet
                        break;
                    default:
                        break;
                }
            }catch (NumberFormatException ex) {
                System.out.println("Invalid input\n Error message: " + ex.getMessage());
            }
        }
    }

    public static void main(String args[]) {
        SpringDemo demo = new SpringDemo();
        boolean run = true;
        Scanner reader = new Scanner(System.in);

        demo.printHelpMenu();
        while(run) {
            System.out.print("Enter a command: ");
            String command = reader.nextLine();

            switch (command) {
                case "-h":
                    // Print help menu
                    demo.printHelpMenu();
                    break;
                case "-q":
                    // Quit
                    run = false;
                    break;
                case "-dt":
                    // Print data types
                    demo.printDataTypes();
                    break;
                case "build":
                    // Run object build process
                    demo.build();
                    break;

            }
        }
    }
}
