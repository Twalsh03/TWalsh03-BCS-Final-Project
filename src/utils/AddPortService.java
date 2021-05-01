package utils;

import device.Device;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class AddPortService {
    /**
     * This method is used to attach the service of a port that is found to be open.
     * the services that is running on the ports are those that are registered via
     * the IANA (Internet Assigned Numbers Authority)
     *
     * Full list can be found on the following link:
     * https://www.iana.org/assignments/service-names-port-numbers/service-names-port-numbers.xhtml
     */
    public AddPortService() {

    }
    /**
     * This method receives an open port, a scanType to determine the amount of ports
     * to look for, speeding up the process. It will also receive the device to which
     * information will be added to.
     *
     * @param port port to add service to
     * @param device device to add the port details to
     * @param scanType can be quick (ports 1-1024) or full( 1- 65535)
     * @throws FileNotFoundException -  if CSV file not found
     */
    public void addService(int port, Device device, String scanType) throws FileNotFoundException {
        if (scanType.equals("quick")) {
            String fileName = "src/PORT-TCP-QUICK.csv";
            serviceHelper(port, device, fileName);
        }
        if (scanType.equals("full")) {
            String fileName = "src/PORT-TCP-FULL.csv";
            serviceHelper(port, device, fileName);
        }
    }


    /**
     * To avoid reusing code, this helper method will assist addService() method.
     *
     * The method reads the .csv file as per the path name pasted into it via fileName
     * and reads line by line with a BufferedReader. Once the port number has been found
     * the Service associated with that port will be added to the device using
     * device.setPort().
     *
     * @param port port to add service to
     * @param device device to add the port details to
     * @param fileName file path to the ports .csv file
     */
    private void serviceHelper(int port, Device device, String fileName) {
        try (
                // new reader to read the .csv
                BufferedReader br = new BufferedReader(new FileReader(fileName))){
                String line;
                //while the reader is not at the end of the file, red line by line.
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                //if the value in the second column matches the open port
                //add the service associated with that open port.
                if (values[1].equals(String.valueOf(port)))
                    device.setPort(port, values[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}