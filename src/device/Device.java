package device;

import java.net.InetAddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  When device is found on the network, its information is stored within an object of this
 *  class object.

 *  macAddress -
 *  hostName -
 *  ports - List of found open ports
 */
public class Device {
    InetAddress ip;
    String macAddress;
    String hostName;
    HashMap<Integer, String>ports;

    /**
     * When a device is created, information on the device is stored.
     *
     * @param ip - IP of device
     * @param macAddress - MAC address of device
     * @param hostName - Name of the device (if found)
     */
    public Device(InetAddress ip, String macAddress, String hostName){
         this.ip = ip;
         this.macAddress = macAddress;
         this.hostName = hostName;
         ports = new HashMap<Integer, String>();
    }

    /**
     * @return IP of device
     */
    public InetAddress getIp() {
        return ip;
    }

    /**
     * @return MAC Address of device
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * @return name of device
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @return return the list of ports found open on the device
     */
    public HashMap<Integer, String> getPorts() {
        return ports;
    }

    /**
     * When a port is found to be open, add the TCP port to the ports List
     *
     * @param port open port to add to ports List
     */
    public void setPort(int port, String service){

         ports.put(port, service );
    }
    /**
     * @return  String representation of Device
     */
    @Override
    public String toString() {
        return  hostName;
    }
}


