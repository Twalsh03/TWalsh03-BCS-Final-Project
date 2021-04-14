import java.lang.reflect.Constructor;
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
    private final int SCAN_LIMIT  = 255;
    private Gateway gateway = new Gateway();
    private String subnetGateway, subnet, hostName, macAddress;
    private InetAddress currentIP;

    public NetScan(){
        gateway.setGateway();

    }


    @Override
    public void scan() throws IOException {
        //get network gateway to start scan
        subnetGateway = gateway.getGateway();
        //get subnet to start scan on
        int subIndex = subnetGateway.lastIndexOf(".");
        subnet = subnetGateway.substring(0,subIndex)+".";

        //device constructor
        DeviceFactory deviceFactory = new DeviceFactory();


        for(int i = 1; i<SCAN_LIMIT; i++){

            currentIP = InetAddress.getByName(subnet+ i);
            if(currentIP.isReachable(30)){
                Device  currentDevice = null;

                String currentIPString = currentIP.toString();
                currentIPString = currentIPString.substring(currentIPString.indexOf("/"+1)).
                        replace("/", "");


                try {
                    currentDevice = deviceFactory.getInstance(currentIPString, "MAC-STUB", currentIP.getHostName());
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

                foundDevices.add(currentDevice);

           }
        }

    }

    public List<Device> getFoundDevices(){
        return foundDevices;
    }

    public Device getFoundDevice( int i){
        return foundDevices.get(i);
    }

    public String getSubnetGateway(){
     return subnetGateway;
    }

}
