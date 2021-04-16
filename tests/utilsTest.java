import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;


//TEST NETWORK IP HARD CODED FOR TESTING
//TESTS CONDUCTED ON WITHIN VIRTUAL BOX VM LAB
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class utilsTest {

    LocalHost testLocalHost = new LocalHost();
    NetScan testScan;

    @Test
    public void A_setLocalHost() {
        testLocalHost.setLocalHost();
        String actual = testLocalHost.getLocalHost();
        String expected = "192.168.8.105";
        assertEquals(expected, actual, "Local IP Address not as expected");

    }

    Gateway testGateway = new Gateway();

    @Test
    public void B_setTestGateway() {
        testGateway.setGateway();
        String actual = testGateway.getGateway();
        String expected = "192.168.8.1";
        assertEquals(expected, actual, "gateway Address not as expected");
    }

    @Test
   public void C_networkScanTest() throws IOException, NoSuchMethodException {
        //Scan network
        testScan = new NetScan();
        try {
            try {
                testScan.scan();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean expected = false;
        boolean actual = testScan.getFoundDevices().isEmpty();
        assertEquals(expected, actual);
    }

    @Test
    public void D_DeviceCreationTest(){

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