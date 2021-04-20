import device.Device;
import org.testng.annotations.Test;
import scanTools.NetScan;
import scanTools.PortScan;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PortScanTest {

    NetScan testIPScan = new NetScan();
    PortScan portScan = new PortScan();
    Device TestDevice ;
    @Test
    public void D_OpenPortScanQuickTest() {
        testIPScan = new NetScan();
        portScan = new PortScan();
        testIPScan.scan();
        TestDevice = testIPScan.getFoundDevice(0);
        portScan.scan(TestDevice, "quick");
        System.out.println(TestDevice);
        boolean expected = false;
        boolean actual = TestDevice.getPorts().isEmpty();
        assertEquals(expected, actual);
    }

    @Test
    public void D_OpenPortScanFullTest() {
            testIPScan = new NetScan();
            portScan = new PortScan();
            testIPScan.scan();
            Device TestDevice = testIPScan.getFoundDevice(1);
            portScan.scan(TestDevice, "full");
            System.out.println(TestDevice);
            boolean expected = false;
            boolean actual = TestDevice.getPorts().isEmpty();
            assertEquals(expected, actual);
        }
}




