package utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/***
 * The purpose of this class is to get the local Host's IP in a usable format, Which will be
 * used for gathering the network subnet.
 *
 */
public class LocalHost {

    private String localHost;

    public LocalHost() {

    }

    /***
     *Gathers the local IP Address of host machine. This will be used in order
     * to gather the network subnet in which to start scanning.
     *
     * Trims Local Host's device name.
     */
    public  void setLocalHost() {
        try {
            localHost = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        localHost = localHost.substring(localHost.indexOf("/"+1)).replace("/", "");
    }

    /***
     * Get the local host's IP
     * @return  - local host IP
     */
    public String getLocalHost(){
        return localHost;
    }
}