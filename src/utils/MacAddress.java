package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;


public class MacAddress {

    public MacAddress(){

    }
    public static String getMacAddrHost(String host) throws IOException {
        //
        boolean win = isWindows();
        boolean osx = isOSX();

        //
        if (win) {
            InetAddress address = InetAddress.getByName(host);
            String ip = address.getHostAddress();
            return run_program_with_catching_output("arp -a " + ip);
        }
        //
        return null;
        //
    }


    public static boolean isWindows() {
        //get the system OS
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static boolean isOSX() {
        //get the system OS
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

    //get the arp cache command against the IP given.
    public static String run_program_with_catching_output(String param) throws IOException {
        Process p = Runtime.getRuntime().exec(param);
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = input.readLine()) != null) {
            if (!line.trim().equals("")) {
                // keep only the process name
                line = line.substring(1);
                String mac = extractMacAddr(line);
                if (!mac.isEmpty()) {
                    return mac;
                }
            }

        }
        return null;
    }

    //as the MAC address is always 17 chars long, find it, trim whitespace, convert all upper case
    public static String extractMacAddr(String str) {
        String[] arr = str.split("   ");
        for (String string : arr) {
            if (string.trim().length() == 17) {
                return string.trim().toUpperCase();
            }
        }
        return "";
    }
}