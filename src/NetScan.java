
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.net.*;

/***
 *
 *
 */

public class NetScan implements DeviceScan{

    private ArrayList<Device> foundDevices = new ArrayList<>();

    private final Gateway gateway = new Gateway();
    private String subnetGateway;


    public NetScan(){
        gateway.setGateway();

    }


    @Override
    public void scan() throws IOException, NoSuchMethodException {
        String macAddress, hostname;
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
                hostname = currentIP.getHostName();
                Device newDevice = deviceFactory.getInstance(currentIP, "MAC-STUB", hostname);
                foundDevices.add(newDevice);
                System.out.println(currentIP);
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
