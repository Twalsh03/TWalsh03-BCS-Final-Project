package scanTools;

import utils.*;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.net.*;
import device.*;

/***
 *The purpose of this method is to scan the local network for online/reachable devices.
 *It will then gather gather information from the device and create a device object, which is then
 *held in a List of found devices.
 *
 */
public class NetScan{

    private static final Gateway gateway = new Gateway();
    private  static ArrayList<Device> foundDevices = new ArrayList<>();

    /***
     *When creating a NetScan object, set the gateway of the network.
     * This is used to start the scan.
     *
     */
    public NetScan(){
        gateway.setGateway();

    }

    /**
     * This method will scan the local network for reachable devices.
     * In order to crete the device objects of found devices during runtime, a DeviceFactory
     * is used. When a device is reachable, get the MAC Address of the device(using HOST's cached Arp Table),
     * the device name and store this with in a device object which is then held in a List of found devices.
     *
     *
     * SCAN_LIMIT - set to 255 as no device can have a higher IP.
     */
    public void scan() {
        final String subnet;
        final int SCAN_LIMIT  = 255;


        //get subnet to start scan on
        subnet = gateway.getSubnet();

        //device factory
        DeviceFactory deviceFactory = new DeviceFactory();


        for(int i = 1; i<SCAN_LIMIT; i++){

            InetAddress currentIP = null;
            try {
                currentIP = InetAddress.getByName(subnet + i);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            try {
                if(currentIP.isReachable(30)){

                    //convert Inet Address to String to manipulate
                    String hostname = currentIP.getHostName();

                    //use utils.MacAddress to get the MAC address of machine.
                    //functional on Win 10
                    String macString = MacAddress.getMacAddrHost(currentIP.getHostAddress());

                    Device newDevice = deviceFactory.getInstance(currentIP, macString, hostname);

                    foundDevices.add(newDevice);
                    System.out.println(newDevice);
               }
            } catch (IOException|NullPointerException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * This method will scan the local network for reachable devices.
     * In order to crete the device objects of found devices during runtime, a DeviceFactory
     * is used. When a device is reachable, get the MAC Address of the device(using HOST's cached Arp Table),
     * the device name and store this with in a device object which is then held in a List of found devices.
     *
     * @param limit number of total devices to scan
     */
    public void scan(int limit) {
        final String subnet;


        //get subnet to start scan on
        subnet = gateway.getSubnet();

        //device factory
        DeviceFactory deviceFactory = new DeviceFactory();


        for(int i = 1; i<limit; i++){

            InetAddress currentIP = null;
            try {
                currentIP = InetAddress.getByName(subnet + i);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            try {
                if(currentIP.isReachable(30)){

                    //convert Inet Address to String to manipulate
                    String hostname = currentIP.getHostName();

                    //use utils.MacAddress to get the MAC address of machine.
                    //functional on Win 10
                    String macString = MacAddress.getMacAddrHost(currentIP.getHostAddress());

                    Device newDevice = deviceFactory.getInstance(currentIP, macString, hostname);

                    foundDevices.add(newDevice);
                }
            } catch (IOException|NullPointerException e) {
                e.printStackTrace();
            }
        }

    }

    /***
     *List of Device objects that are found on the network.
     *
     * @return - List of found device objects
     */
    public List<Device> getFoundDevices(){
        return foundDevices;
    }

    /***
     * When called, will return the device object of a given index from foundDevices List.
     *
     * @param i - index of device to return
     * @return Device within foundDevice List with given index.
     */
    public Device getFoundDevice(int i){
        return foundDevices.get(i);
    }


}
