import org.testng.annotations.Test;
import scanTools.NetScan;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScanToolsTest {

    NetScan testScan = new NetScan();

    @Test
    public void C_networkScanTest() {
        //Scan network
        testScan = new NetScan();
        testScan.scan();

        boolean expected = false;
        boolean actual = testScan.getFoundDevices().isEmpty();
        assertEquals(expected, actual);
    }


}
