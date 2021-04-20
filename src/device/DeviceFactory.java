package device;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.HashMap;

/***
 *  This class is used to return a new device, which is created at runtime, if it is found within the network.
 *
 */
public class DeviceFactory {

    public DeviceFactory(){


    }

    /**
     * The getInstance method will return a newly created device object with the
     * parameters that have been passed into it.
     *
     *  Using reflection, a new device is created using Device Constructor with the found information passed into it.
     *
     * @param deviceIP  - IP of found device
     * @param macAddress - MAC Address of found device
     * @param hostname - name of found device
     * @return - Newly created object with parameters.
     */
    public Device getInstance(InetAddress deviceIP, String macAddress, String hostname) {


        Constructor<?> deviceConstructor = null;
        try {
            deviceConstructor = Device.class.getConstructor(InetAddress.class, String.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        try {
            if (deviceConstructor != null) {
                return (Device) deviceConstructor.newInstance(deviceIP, macAddress, hostname);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
