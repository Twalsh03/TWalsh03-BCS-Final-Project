package utils;

/***
 * This class will get the local IP address and convert that into the
 * network's gateway, (which is usually XXX.XXX.XXX.1). This will be used to
 * start scanning the local network for reachable devices.
 */
public class Gateway extends LocalHost {

    private String gateway;


    public Gateway() {

    }

    /***
     * Gets the local IP and sets the last subnet to .1.
     * Will be used as the default starting point to of scans
     *
     * changes the last octet to .1 by getting the last index of '.'
     */
    public void setGateway() {

        LocalHost localHost = new LocalHost();
        localHost.setLocalHost();
        String localHostIP = localHost.getLocalHost();

        gateway = localHostIP.substring(0, localHostIP.lastIndexOf(".")) + ".1";
    }

    /***
     * @return the subnet gateway.
     */
    public String getGateway() {
        return gateway;
    }

    /***
     * Get the subnet address of the network.
     * EXAMPLE:
     *
     * If local IP  is '192.168.0.44', then gateway will be set to  '192.168.0.1'
     *
     * The returned subnet will be '192.168.0.', which wil be used to start the scan.
     *
     * @return - subnet address of the network in a usable format.
     */
    public String getSubnet() {
    int subIndex = gateway.lastIndexOf(".");
    String subnet = gateway.substring(0,subIndex)+".";
    return subnet;
}
}