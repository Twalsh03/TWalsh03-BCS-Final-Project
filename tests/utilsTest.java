import com.google.common.net.InetAddresses;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.testng.annotations.Test;


import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//TEST NETWORK IP HARD CODED FOR TESTING
//TESTS CONDUCTED ON WITHIN VIRTUAL BOX VM LAB
public class utilsTest {

    LocalHost testLocalHost = new LocalHost();
    NetScan testScan;

    @Test
    public void setLocalHost() {
        testLocalHost.setLocalHost();
        String actual = testLocalHost.getLocalHost();
        String expected = "192.168.8.105";
        assertEquals(expected, actual, "Local IP Address not as expected");

    }

    Gateway testGateway = new Gateway();

    @Test
    public void setTestGateway() {
        testGateway.setGateway();
        String actual = testGateway.getGateway();
        String expected = "192.168.8.1";
        assertEquals(expected, actual, "gateway Address not as expected");
    }


    //this test will scan the network and test the found details of the first found device.
    //In this case it will be my router.
    @Test
    public void foundDeviceTest(){
        //Test the properties of first found device
        Device testDevice = new Device("192.168.8.1","MAC-STUB","homerouter.cpe");
        Device actual = testScan.getFoundDevice(0);
        String expected = testDevice.toString();
        assertEquals(expected, actual, "The objects are not equal");
    }

    @Test
   public void networkScanTest() throws IOException, NoSuchMethodException {
        //Scan network
        testScan = new NetScan();
        testScan.scan();
        try {
            testScan.scan();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean expected = false;
        boolean actual = testScan.getFoundDevices().isEmpty();
        assertEquals(expected, actual);
    }

}