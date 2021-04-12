package utils;

import org.testng.annotations.Test;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.*;



public class gatewayTest {
     localHost testGateway = new localHost();

     @Test
     public void localIPTest(){
         testGateway.getLocalHost();
         String actual = testGateway.getLocalHost();
         String expected = "192.168.8.105";
         assertEquals(expected, actual, "Local IP Address not as expected");

     }

}
