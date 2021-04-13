import com.google.common.net.InetAddresses;
import org.testng.annotations.Test;


import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//TEST NETWORK IP HARD CODED FOR SIMPLICITY
//TEST CONDUCTED ON OSX 10.13.6
public class utilsTest {

    LocalHost testLocalHost = new LocalHost();

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

    NetScan testScan = new NetScan();
    @Test
    public void netScanTest(){
        try {
            testScan.scan();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> actual = testScan.getFoundDevices();
        String expected = "192.168.8.1";
        assertEquals(expected, actual, "gateway Address not as expected");
    }
}