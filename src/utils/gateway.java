package utils;

public class gateway extends localHost {

    private String gateway;
    public gateway(){

    }
    //gets the local IP and sets the last subnet to .1
    //Will be used as the default starting point to of scans
    public void setGateway(){

        localHost localHost =new localHost();
        localHost.setLocalHost();
        String localHostIP = localHost.getLocalHost();

        int subIndex = localHostIP.lastIndexOf(".");
        gateway = localHostIP.substring(0,subIndex) + ".1";

    }
    public String getGateway() {
        return gateway;
    }
}
