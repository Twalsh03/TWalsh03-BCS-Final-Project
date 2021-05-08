package scanTools;

import device.Device;
import utils.AddPortService;

import java.io.IOException;
import java.net.*;


public class PortScan {

    /**
     * The PortScan class will be used to discover open ports on a given device.
     * It will be able to conduct TCP and UDP port scans on the devices IP Address.
     */
    public PortScan() {
    }

    private int scanLimit;

    /**
     * The TCP Scan method will take the scan type of the GUI and a device to run
     * the scan on. Depending on what the Scan Type is will the determine the amount
     * of ports to connect to. Once a port is found to be open, the AddPortService object
     * will add the service of the open port.
     *
     * @param device   Device to run the scan on
     * @param scanType The type of scan to run. Can be 'quick' or 'full'.
     */
    public void scanTCP(Device device, String scanType) {
        AddPortService serviceAdd = new AddPortService();
        if (scanType.equals("quick")) {
            scanLimit = 1024;
        } else if (scanType.equals("full")) {
            scanLimit = 65325;
        } else {
            scanLimit = -1;
        }

        // The for loop will search the open ports within the scanLimit
        // Using sockets, the host device will attempt to connect to the device using TCP
        // protocol. if the connection fails, the try catch block will see the error
        // can move onto the next port within the scope.

        for (int port = 1; port < scanLimit; port++) {
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress(device.getIp(), port), 300);
                socket.close();
                device.setPort(port, "");
                serviceAdd.addService(port, device, scanType);


            } catch (ConnectException | SocketTimeoutException ignore) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(device);
    }

    /**
     * The UDP port scn will use Datagram Packets to check for open ports.
     * This can prove inconsistent due to Datagram packets being connectionless.
     * <p>
     * Other than the connection type, this method works much like TCP scans, however
     * This method may not be used due to issues with discovering open UDP ports.
     *
     * @param device   Device to run scans
     * @param scanType the type of scan -- can be 'full' or quick.
     */
    public void scanUDP(Device device, String scanType) {
        AddPortService serviceAdd = new AddPortService();
        if (scanType.equals("quick")) {
            scanLimit = 1024;
        } else if (scanType.equals("full")) {
            scanLimit = 65325;
        } else {
            scanLimit = -1;
        }


        for (int port = 1; port < scanLimit; port++) {
            try {
                DatagramSocket UDPSocket = new DatagramSocket(port);
                UDPSocket.close();
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }

        System.out.println(device);
    }


}
