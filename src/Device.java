import java.net.InetAddress;

import java.util.List;


public class Device {
    InetAddress ip;
    String macAddress;
    String hostName;
    List<Integer>ports;

    public Device(){

    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setPorts(List<Integer> ports) {
        this.ports = ports;
    }

    public InetAddress getIp() {
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
}


