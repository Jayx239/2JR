package com.twojr.protocol.devices.coordinator;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.exceptions.TimeoutException;
import com.digi.xbee.api.exceptions.XBeeException;
import com.twojr.protocol.aps.ApsPacket;
import com.twojr.protocol.aps.AttributeControl;
import com.twojr.protocol.aps.EndPoint;
import com.twojr.protocol.aps.IApsPacket;
import com.twojr.protocol.devices.TwoJRRemoteDevice;
import com.twojr.protocol.network.NetworkPacket;
import com.twojr.toolkit.integer.JSignedInteger;

/**
 * Created by rcunni202 on 5/20/2017.
 */
public class CoordinatorDiscoverThread implements Runnable {

    private Coordinator coordinator;
    private RemoteXBeeDevice remoteXBeeDevice;

    public CoordinatorDiscoverThread(Coordinator coordinator, RemoteXBeeDevice remoteXBeeDevice){

        this.coordinator = coordinator;
        this.remoteXBeeDevice = remoteXBeeDevice;

    }



    @Override
    public void run() {

        if(!remoteXBeeDevice.get64BitAddress().equals(coordinator.get64BitAddress())) {
            System.out.println(remoteXBeeDevice.toString() + "was added to the network");

            coordinator.addEndDevice(remoteXBeeDevice.get64BitAddress(), new TwoJRRemoteDevice(remoteXBeeDevice.get64BitAddress()));

            AttributeControl attributeControl = new AttributeControl(new byte[]{(byte) 0x03});

            ApsPacket apsPacket = new ApsPacket(new JSignedInteger(new byte[]{0}), new byte[]{}, IApsPacket.apsCommands.DISCOVER, new EndPoint((byte) 0x01), attributeControl);
            NetworkPacket networkPacket = new NetworkPacket(0, 0, 0, 0, apsPacket.toByte());

            System.out.println("Sending APS Discovery Request");
            System.out.println(networkPacket.print());
            sendAPSDiscoveryRequest(3,remoteXBeeDevice,networkPacket);




        }else {

            if(!coordinator.isNetworkDiscovered()) {

                System.out.println("Self Discovery");
                coordinator.setNetworkDiscovered(true);

            }

        }

    }

    private void sendAPSDiscoveryRequest(int transmissionCount,RemoteXBeeDevice remoteXBeeDevice, NetworkPacket networkPacket ){

        try {
            coordinator.sendData(remoteXBeeDevice, networkPacket.toByte());
        } catch (TimeoutException e1) {

            System.out.println("Packet Timeout");

            /*
            transmissionCount += transmissionCount + 1;
            if(transmissionCount < 4) {

                System.out.println("Retranmitting: " + transmissionCount);
                sendAPSDiscoveryRequest(transmissionCount, remoteXBeeDevice, networkPacket);

            }else {

                System.out.println("Transmission Failure");

            }
            */

        } catch (XBeeException e){
            e.printStackTrace();
        }

    }
}
