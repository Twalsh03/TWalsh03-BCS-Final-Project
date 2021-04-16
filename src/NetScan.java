
import utils.Gateway;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.net.*;

/***
 *
 *
 */

public class NetScan{

    private static final Gateway gateway = new Gateway();
    private static String subnetGateway;
    private static ArrayList<Device> foundDevices = new ArrayList<>();
    private static String macAddress, hostname;

    public NetScan(){
        gateway.setGateway();

    }

    public static void scan() throws IOException, NoSuchMethodException, InterruptedException {


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
                String hostnameString = hostname.toString().substring(0, hostname.indexOf("/")+1);


                ///////MAC temp CODE //////
                macAddress = getMacAddrHost(currentIP.getHostAddress());





                Device newDevice = deviceFactory.getInstance(currentIP, macAddress, hostname);

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

    public static String getMacAddrHost(String host) throws IOException, InterruptedException {
        //
        boolean ok = ping3(host);
        //
        if (ok) {
            InetAddress address = InetAddress.getByName(host);
            String ip = address.getHostAddress();
            return run_program_with_catching_output("arp -a " + ip);
        }
        //
        return null;
        //
    }


    public static boolean ping3(String host) throws IOException, InterruptedException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");

        ProcessBuilder processBuilder = new ProcessBuilder("ping", isWindows ? "-n" : "-c", "1", host);
        Process proc = processBuilder.start();

        int returnVal = proc.waitFor();
        return returnVal == 0;
    }

    public static String run_program_with_catching_output(String param) throws IOException {
        Process p = Runtime.getRuntime().exec(param);
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = input.readLine()) != null) {
            if (!line.trim().equals("")) {
                // keep only the process name
                line = line.substring(1);
                String mac = extractMacAddr(line);
                if (mac.isEmpty() == false) {
                    return mac;
                }
            }

        }
        return null;
    }

    public static String extractMacAddr(String str) {
        String arr[] = str.split("   ");
        for (String string : arr) {
            if (string.trim().length() == 17) {
                return string.trim().toUpperCase();
            }
        }
        return "";
    }
}
