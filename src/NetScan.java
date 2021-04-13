import java.util.List;
import java.io.*;
import java.net.*;

/***
 *
 *
 */

public class NetScan implements DeviceScan{
    List<Device>foundDevices;


    public NetScan(){ }
    @Override
    public void scan() {


    }

    public List<Device> getFoundDevices(){
        return foundDevices;
    }

}
