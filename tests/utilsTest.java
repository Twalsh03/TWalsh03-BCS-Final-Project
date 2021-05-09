
import org.testng.annotations.Test;
import utils.Gateway;
import utils.LocalHost;

import static org.junit.jupiter.api.Assertions.*;


public class utilsTest {

    LocalHost testLocalHost = new LocalHost();

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



}