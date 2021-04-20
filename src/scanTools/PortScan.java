package scanTools;

import device.Device;
import utils.AddPortService;

import java.io.IOException;
import java.net.*;

public class PortScan {

    private int scanLimit;

    public void scan(Device device, String scanType) {
        AddPortService serviceAdd = new AddPortService();
        if (scanType.equals("quick")) {
            scanLimit = 1024;
        } else if (scanType.equals("full")) {
            scanLimit = 65325;
        } else {
            scanLimit = -1;
        }



        for (int port = 1; port < scanLimit; port++) {
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress(device.getIp(), port), 300);
                socket.close();
                serviceAdd.addService(port, device, scanType);


            } catch (ConnectException | SocketTimeoutException ignore) {
            } catch (BindException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(device);
    }
}