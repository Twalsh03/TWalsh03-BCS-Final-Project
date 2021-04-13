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

    private ArrayList<String> foundDevices = new ArrayList<>();
    private int SCAN_LIMIT  = 255;
    private Gateway gateway = new Gateway();
    private String subnetGateway;
    private String subnet;

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
        Constructor<Device> deviceConstructor;


        for(int i = 1; i<SCAN_LIMIT; i++){

            InetAddress currentIP = InetAddress.getByName(subnet+ i);
            if(currentIP.isReachable(500)){
               foundDevices.add(currentIP.toString());
                System.out.println(currentIP);
           }
        }

    }

    public List<String> getFoundDevices(){
        return foundDevices;
    }

    public String getSubnetGateway(){
     return subnetGateway;
    }

}
