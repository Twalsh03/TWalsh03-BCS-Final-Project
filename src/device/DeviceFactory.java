package device;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;

public class DeviceFactory {

    public DeviceFactory(){


    }

    public Device getInstance(InetAddress deviceIP, String macAddress, String hostname) throws NoSuchMethodException {



        Constructor<?> deviceConstructor = Device.class.getConstructor(InetAddress.class, String.class, String.class);


        try {
            return (Device) deviceConstructor.newInstance(deviceIP, macAddress, hostname);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
