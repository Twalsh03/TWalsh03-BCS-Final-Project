package utils;

import device.Device;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class AddPortService {

    public AddPortService(){

    }

    public void AddService(String scanType, Device device) throws FileNotFoundException {
        try(
        BufferedReader br = new BufferedReader(new FileReader("PORT-TCP-QUICK.csv"))){
            String line;

            for (int i = 0; i< device.getPorts().size();i++) {

                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }







    }



}
