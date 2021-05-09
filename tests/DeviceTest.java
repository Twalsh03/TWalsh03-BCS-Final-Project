import device.Device;
import org.testng.annotations.Test;
import scanTools.NetScan;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceTest {

    NetScan testScan = new NetScan();


    @Test
    public void A_DeviceCreationTestIPAddress(){
        testScan.scan();
        Device routerTEST = null;
        try {
            routerTEST = new Device(InetAddress.getByName("192.168.8.1"), "94:37:F7:E4:5C:24", "homerouter.cpe");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Device d = testScan.getFoundDevice(0);
        String actual = d.getIPString();
        String expected = routerTEST.getIPString();
        assertEquals(expected, actual);
    }

    @Test
    public void B_DeviceCreationTestMacAddress(){

        testScan.scan();

        Device routerTEST = null;
        try {
            routerTEST = new Device(InetAddress.getByName("192.168.8.1"), "94:37:F7:E4:5C:24", "homerouter.cpe");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Device d = testScan.getFoundDevice(0);
        String actual = d.getMacAddress();
        String expected = routerTEST.getMacAddress();
        assertEquals(expected, actual);
    }

    @Test
    public void C_DeviceCreationTestToString(){

        testScan.scan();

        Device routerTEST = null;
        try {
            routerTEST = new Device(InetAddress.getByName("192.168.8.1"), "94:37:F7:E4:5C:24", "homerouter.cpe");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Device d = testScan.getFoundDevice(0);
        String actual = d.toString();
        String expected = "homerouter.cpe";
        assertEquals(expected, actual);
    }

}
