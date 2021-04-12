package tests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import utils.gateway;ggg

public class gatewayTest {
     gateway test = new gateway();

     @Test
     public void localIPTest(){
         String actual = test.getLocalHost().toString();
         String expected = "192.168.8.105";
          assertEquals(expected, actual, "Local IP Address not as expected");

     }

}
