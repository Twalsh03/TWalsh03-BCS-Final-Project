public class Gateway extends LocalHost {

    private String gateway;
    public Gateway(){

    }
    //gets the local IP and sets the last subnet to .1
    //Will be used as the default starting point to of scans
    public void setGateway(){

        LocalHost localHost =new LocalHost();
        localHost.setLocalHost();
        String localHostIP = localHost.getLocalHost();

        int subIndex = localHostIP.lastIndexOf(".");
        gateway = localHostIP.substring(0,subIndex) + ".1";

    }
    public String getGateway() {
        return gateway;
    }
}