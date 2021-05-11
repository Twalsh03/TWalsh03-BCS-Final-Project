import device.Device;
import org.testng.annotations.Test;
import scanTools.NetScan;
import scanTools.PortScan;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

public class PortScanTest {

    NetScan testIPScan = new NetScan();
    PortScan portScan = new PortScan();
    Device routerTEST;
    @Test
    public void  A_OpenPortScanTCPQuickTest() {
        testIPScan = new NetScan();
        portScan = new PortScan();
        testIPScan.scan();

        try {
            routerTEST = new Device(InetAddress.getByName("192.168.8.1"), "94:37:F7:E4:5C:24", "homerouter.cpe");
            routerTEST.setPort(443, "https");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        portScan.scanTCP(routerTEST, "quick");
        Device foundDevice = testIPScan.getFoundDevice(0);
        boolean expected = routerTEST.getPorts().containsKey(443);
        boolean actual = foundDevice.getPorts().containsKey(443);
        assertEquals(actual, expected);
    }

    @Test
    public void B_OpenPorTCPtScanFullTest() {
            testIPScan = new NetScan();
            portScan = new PortScan();
            testIPScan.scan();
            Device TestDevice = testIPScan.getFoundDevice(0);
            portScan.scanTCP(TestDevice, "full");
            System.out.println(TestDevice);
            boolean expected = false;
            boolean actual = TestDevice.getPorts().isEmpty();
            assertEquals(expected, actual);
        }

    @Test
    public void C_OpenPortUDPScanFullTest() {
        testIPScan = new NetScan();
        portScan = new PortScan();
        testIPScan.scan();
        Device TestDevice = testIPScan.getFoundDevice(0);
        portScan.scanUDP(TestDevice, "quick");
        System.out.println(TestDevice);
        boolean actual = TestDevice.getPorts().isEmpty();
        assertFalse(actual);
    }
}




