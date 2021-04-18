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
    public void C_DeviceCreationTest(){

        testScan.scan();

        Device routerTEST = null;
        try {
            routerTEST = new Device(InetAddress.getByName("192.168.8.1"), "MAC-STUB", "homerouter.cpe");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Device actual = testScan.getFoundDevice(0);
        Device expected = routerTEST;
        assertEquals(actual, expected);
    }

}
