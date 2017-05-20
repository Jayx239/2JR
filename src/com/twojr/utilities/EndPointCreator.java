package com.twojr.utilities;

import com.twojr.protocol.Attribute;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.protocol.aps.IApsPacket;
import com.twojr.protocol.devices.TwoJRDevice;
import com.twojr.protocol.network.INetPacket;
import com.twojr.toolkit.DataFactory;
import com.twojr.toolkit.JData;
import com.twojr.toolkit.JDataSizes;
import com.twojr.toolkit.JDataTypes;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

/**
 * Created by rcunni202 on 5/13/2017.
 */
public class EndPointCreator extends JFrame{

    private JSpinner endPointIdSpinner;
    private JButton createButton;
    private JComboBox dataTypeCombo;
    private JTextField attributeNameTextField;
    private JComboBox dataSizeCombo;
    private JButton addButton;
    private JPanel mainPanel;
    private JButton closeButton;
    private JList attributeList;

    private TwoJRDevice device;
    private EndPoint endPoint;
    private DefaultListModel<String> model;

    public EndPointCreator(TwoJRDevice device){

        this.device = device;
        endPoint = new EndPoint();

        this.setTitle("2JR Device Controller");
        setResizable(true);

        model = new DefaultListModel<>();
        attributeList.setModel(model);
        setContentPane(mainPanel);

        populateCommandCombos();
        setListeners();

        pack();
        setVisible(true);

    }


    private void setListeners(){

        endPointIdSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                endPointIdChange();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButtonAction();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createButtonAction();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeButtonAction();
            }
        });

    }
    private void populateCommandCombos(){

        for(JDataTypes dataTypes : JDataTypes.values()){

            dataTypeCombo.addItem(dataTypes.toString());

        }
        for(JDataSizes dataSize : JDataSizes.values() ){

            dataSizeCombo.addItem(dataSize.toString());

        }
    }

    private void endPointIdChange(){

        if((int)endPointIdSpinner.getValue() > 255){
            endPointIdSpinner.setValue(255);
        }else if((int)endPointIdSpinner.getValue() < 0){
            endPointIdSpinner.setValue(0);
        }

    }

    private void populateAttributes(){

        model.removeAllElements();

        for(Attribute attribute : endPoint.getAttributes()) {
            model.addElement(attribute.print());
        }


    }
    private void addButtonAction(){


        byte[] defaultValue = {0x00};
        String enumValue = dataTypeCombo.getSelectedItem().toString();
        JDataTypes dataType = JDataTypes.valueOf(enumValue);
        JData data =  DataFactory.getData(dataType.getId(),defaultValue);

        Attribute attribute = new Attribute(data,attributeNameTextField.getText());

        endPoint.addAttribute(attribute);
        populateAttributes();

    }


    private void createButtonAction(){

        endPoint.setId((int)endPointIdSpinner.getValue());
        device.setLocalEndPoint(endPoint);
        endPoint = new EndPoint();
        populateAttributes();

    }

    private void closeButtonAction(){
        this.dispose();
    }

}
