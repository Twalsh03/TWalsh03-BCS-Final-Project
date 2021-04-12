package utils;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class localHost {

    private String localHost;
    private String gateway;

    public localHost() {
        localHost = getLocalHost();
    }

    //receives the local IP Address of host machine
    //Will be used in order to gather the network IP in which to start scanning
    public  String getLocalHost() {
        try {
            localHost = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String StrippedLocalHost = localHost.substring(localHost.indexOf("/"+1)).
                replace("/", "").
                trim();
        return StrippedLocalHost;
    }

    public String setGateway(){



        return null;
    }

}