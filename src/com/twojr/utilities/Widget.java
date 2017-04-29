package com.twojr.utilities;

import com.twojr.protocol.network.NetworkPacket;

import java.util.Scanner;

/**
 * Created by Jason on 4/17/2017.
 */
public class Widget {

    static void main() {
        boolean run = true;
        Scanner reader = new Scanner(System.in);

        while (run) {
            System.out.print("Enter a command: ");
            String command = reader.nextLine();

            switch (command) {
                case "-h":
                    // Print help menu

                    break;
                case "-q":
                    // Quit

                    run = false;
                    break;
                case "-dt":
                    // Print data types

                    break;
                case "-ir":

                    break;
                case "-sd":
                    // Send data over radio

                    break;
                case "-rd":
                    // Read data from radio

                    break;
                case "build":
                    // Run object build process

                    break;

            }
        }
    }
}
