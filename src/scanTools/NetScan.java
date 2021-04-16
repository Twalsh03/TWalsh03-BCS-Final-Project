package scanTools;

import utils.*;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.net.*;
import device.*;


/***
 *
 *
 */

public class NetScan{

    private static final Gateway gateway = new Gateway();
    private static String subnetGateway, hostname;
    private static final ArrayList<Device> foundDevices = new ArrayList<>();
    private MacAddress macAddress;
    private String macString;


    public NetScan(){
        gateway.setGateway();

    }

    public void scan() throws IOException, NoSuchMethodException, InterruptedException {


        final String subnet;
        final int SCAN_LIMIT  = 255;
        InetAddress currentIP;


        //get network gateway to start scan
        subnetGateway = gateway.getGateway();
        //get subnet to start scan on
        int subIndex = subnetGateway.lastIndexOf(".");
        subnet = subnetGateway.substring(0,subIndex)+".";

        //device factory
        DeviceFactory deviceFactory = new DeviceFactory();


        for(int i = 1; i<SCAN_LIMIT; i++){

            currentIP = InetAddress.getByName(subnet+ i);

            if(currentIP.isReachable(30)){

                //convert Inet Address to String to manipulate
                hostname = currentIP.getHostName();
                //get just the hostname from the IP address gained
                String hostnameString = hostname.substring(0, hostname.indexOf("/")+1);

                //use utils.MacAddress to get the MAC address of machine.
                //functional on Win 10
                macString = MacAddress.getMacAddrHost(currentIP.getHostAddress());

                Device newDevice = deviceFactory.getInstance(currentIP, macString, hostname);

                foundDevices.add(newDevice);
                System.out.println(newDevice);
           }
        }

    }



    public List<Device> getFoundDevices(){
        return foundDevices;
    }

    public Device getFoundDevice(int i){
        return foundDevices.get(i);
    }

    public String getSubnetGateway(){
     return subnetGateway;
    }


}
