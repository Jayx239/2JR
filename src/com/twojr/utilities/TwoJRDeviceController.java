package com.twojr.utilities;

import com.digi.xbee.api.exceptions.XBeeException;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.protocol.aps.IApsPacket;
import com.twojr.protocol.devices.TwoJRDevice;
import com.twojr.protocol.devices.coordinator.Coordinator;
import com.twojr.protocol.devices.end_device.EndDevice;
import com.twojr.protocol.devices.router.Router;
import com.twojr.protocol.network.INetPacket;
import com.twojr.utilities.threading.DiscoverThread;
import gnu.io.CommPortIdentifier;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created by rcunni202 on 5/13/2017.
 */
public class TwoJRDeviceController extends JFrame {

    private JButton startButton;
    private JButton stopButton;
    private JComboBox comPortComboBox;
    private JComboBox apsCommandComboBox;
    private JComboBox baudRateComboBox;
    private JComboBox networkCommandComboBox;
    private JButton sendButton;
    private JButton discoverButton;
    private JPanel mainPanel;
    private JButton refreshButton;
    private JComboBox deviceTypeComboBox;
    private JLabel statusLabel;
    private JComboBox endPointComboBox;
    private JButton createEndPointButton;
    private JList packetsRecieved;
    private JTextArea localEndpointTextArea;
    private JTextArea remoteEndpointTextArea;


    private TwoJRDevice twoJRDevice;


    private static final String STATUS = "Status: ";

    public TwoJRDeviceController(){

        this.setTitle("2JR Device Controller");
        setResizable(true);

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AddCOMs();
        setListeners();
        populateCommandCombos();

        pack();
        setVisible(true);

    }

    private void populateCommandCombos(){

        for(IApsPacket.apsCommands apsCommand : IApsPacket.apsCommands.values() ){

            apsCommandComboBox.addItem(apsCommand.toString());

        }

        for(INetPacket.networkLayerCommands networkLayerCommands : INetPacket.networkLayerCommands.values() ){

            networkCommandComboBox.addItem(networkLayerCommands.toString());

        }
    }

    private void setListeners(){

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                refresh();
            }
        });


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendButtonAction();
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButtonAction();
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopButtonAction();
            }
        });

        discoverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                discoverButtonAction();
            }
        });

        createEndPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEndPointAction();
            }
        });

    }

    private TwoJRDevice createDevice(int baud, String comPort){

        String deviceType  = deviceTypeComboBox.getSelectedItem().toString();

        switch (deviceType){

            case "Coordinator":
                return  new Coordinator(comPort,baud,null,null,null);

            case "End Device":
                return  new EndDevice(comPort,baud,null,null,null,null,-1);

            case "Router":
                return  new Router(comPort,baud,null,null,null,null);

            default:
                return null;

        }

    }


    private String getCOMPort(){

        return  comPortComboBox.getSelectedItem().toString();

    }


    private int getBaudRate(){

        return  Integer.parseInt(baudRateComboBox.getSelectedItem().toString());

    }

    private void sendButtonAction(){

        IApsPacket.apsCommands command = IApsPacket.apsCommands.valueOf(apsCommandComboBox.getSelectedItem().toString());
        try {
            twoJRDevice.send(command);
        } catch (XBeeException e) {
            e.printStackTrace();
        }
    }
    private void startButtonAction(){

        try {

            statusLabel.setText(STATUS + "Starting...");

            this.twoJRDevice = createDevice(getBaudRate(),getCOMPort());

            statusLabel.setText(STATUS + "Connection Established!");

            twoJRDevice.start();

        } catch (XBeeException e) {

            statusLabel.setText(STATUS + "Connection Not Established!");
            e.printStackTrace();
        }

    }

    private void stopButtonAction(){

        statusLabel.setText(STATUS + "Disconnecting...");

        twoJRDevice.stop();

        statusLabel.setText(STATUS + "Disconnected!");

    }

    private void discoverButtonAction(){

        DiscoverThread discoverThread = new DiscoverThread(twoJRDevice);
        discoverThread.run();

    }

    private void createEndPointAction(){


        EndPointCreator endPointCreator = new EndPointCreator(twoJRDevice);

    }

    private void refresh(){

        comPortComboBox.removeAllItems();
        AddCOMs();

        String endpointStr = "";

       for(HashMap<Integer,EndPoint> map : twoJRDevice.getRemoteEndPoints().values()){

           for(EndPoint endPoint : map.values()){

               endpointStr += endPoint.print();
           }

       }



        remoteEndpointTextArea.setText(endpointStr);

        endpointStr = "";

        for(EndPoint endPoint : twoJRDevice.getLocalEndpoints().values()) {

            endpointStr += endPoint.print();
        }

        localEndpointTextArea.setText(endpointStr);


    }


    private void AddCOMs(){

        Enumeration portList = CommPortIdentifier.getPortIdentifiers();

        while(portList.hasMoreElements()){
            CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();

            comPortComboBox.addItem(portId.getName());

        }
    }

}
