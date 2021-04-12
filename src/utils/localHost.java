package utils;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class localHost {

    private String localHost;

    public localHost() {

    }

    //receives the local IP Address of host machine
    //Will be used in order to gather the network IP in which to start scanning
    public  void setLocalHost() {
        try {
            localHost = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        localHost = localHost.substring(localHost.indexOf("/"+1)).
                replace("/", "").
                trim();
    }

    public String getLocalHost(){
        return localHost;
    }
}