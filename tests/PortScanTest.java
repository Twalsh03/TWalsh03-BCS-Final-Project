import device.Device;
import org.testng.annotations.Test;
import scanTools.NetScan;
import scanTools.PortScan;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PortScanTest {



    @Test
    public void D_OpenPortScanTest() {
        //Scan network
        NetScan testIPScan = new NetScan();
        PortScan portScan = new PortScan();

        testIPScan.scan(5);
        Device TestDevice = testIPScan.getFoundDevice(0);
        portScan.scan(TestDevice, "quick");

        boolean expected = false;
        boolean actual = TestDevice.getPorts().isEmpty();
        assertEquals(expected, actual);
    }

}
