package utils;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class gateway {

    private  InetAddress localHost;
    private  InetAddress localGateway;

    public gateway() {
        localHost = getLocalHost();
    }

    public void setLocalHost() {
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println(localHost.getHostAddress().trim());

    }

    public InetAddress getLocalHost(){
        return localHost;
    }


    public InetAddress getGateway() {
        return localGateway;
    }

}