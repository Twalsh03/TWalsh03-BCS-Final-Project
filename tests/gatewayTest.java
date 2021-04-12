import org.testng.annotations.Test;
import utils.gateway;
import utils.localHost;


import static org.junit.jupiter.api.Assertions.*;


//TEST NETWORK IP HARD CODED
public class gatewayTest {
     localHost testLocalHost = new localHost();
     gateway testGateway = new gateway();
     @Test
     public void setLocalHost(){
         testLocalHost.setLocalHost();
         String actual =  testLocalHost.getLocalHost();
         String expected = "192.168.8.105";
         assertEquals(expected, actual, "Local IP Address not as expected");

     }

    @Test
    public void setTestGateway(){
         testGateway.setGateway();
        String actual = testGateway.getGateway();
        String expected = "192.168.8.1";
        assertEquals(expected, actual, "gateway Address not as expected");
    }



}
