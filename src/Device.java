import java.net.InetAddress;

import java.util.List;


public class Device {
    String ip;
    String macAddress;
    String hostName;
    List<Integer>ports;

    public Device(String ip, String macAddress, String hostName){
         this.ip = ip;
         this.macAddress = macAddress;
         this.hostName = hostName;
    }

    public String getIp() {
        return ip;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public List<Integer> getPorts() {
        return ports;
    }

    @Override
    public String toString() {
        return "Device{" +
                "ip=" + ip +
                ", macAddress='" + macAddress + '\'' +
                ", hostName='" + hostName + '\'' +
                ", ports=" + ports +
                '}';
    }
}


