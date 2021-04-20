package utils;

import device.Device;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class AddPortService {
    /**
     *
     *
     *
     */
    public AddPortService() {

    }

    /**
     *
     *
     *
     * @param port
     * @param device
     * @param scanType
     * @throws FileNotFoundException
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
     *
     *
     * @param port
     * @param device
     * @param fileName
     */
    private void serviceHelper(int port, Device device, String fileName) {
        try (
                //Buffer READER USED FOR READ SPEED - PUT IN REPORT
                BufferedReader br = new BufferedReader(new FileReader(fileName))){
                String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values[1].equals(String.valueOf(port)))
                    device.setPort(port, values[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}