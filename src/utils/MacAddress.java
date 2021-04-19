package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class MacAddress {

    public MacAddress(){
    }

    /***
     * Depending on HOST OS, get MAC Address via cached ARP TABLE.
     *
     *
     * @param host - IP of HOST Device - used to running local system commands from
     * @return MAC address of remote device (if found).
     */
    public static String getMacAddrHost(String host) throws UnknownHostException {
        //checks the HOST OS
        boolean win = isWindows();
        boolean osx = isOSX();
        InetAddress address = InetAddress.getByName(host);
        String ip = address.getHostAddress();
        //If HOST is Windows OS
        if (win) {
            return run_program_with_catching_output("arp -a " + ip);
        }
        //If HOST is MacOS based
        if (osx) {
         //   More research is needed in order to send a BASH command on MacOS
            return  "Not compatible";
            }
        return null;

    }

    /***
     *This method checks the host's OS as getting the MAC Address will vary depending on OS.
     *
     * @return  - Return true if host Operating System is Windows
     */
    private static boolean isWindows() {
        //get the system OS
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    /***
     * This method checks the host's OS as getting the MAC Address will vary depending on OS.
     *
     * @return -Will return true if host Operating System is MacOS
     */
    private static boolean isOSX() {
        //get the system OS
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }


    /***
     * If the host machine running this java program is windows based
     * the 'arp -a' command will be run using the runtime.exec() method.
     *
     * This will call the extractMacAddr() method to get just the MAC
     * address of the IP called.
     *
     * @param param  - Command to run on Windows CMD
     * @return mac - MAC ID of IP address's NIC
     */

    public static String run_program_with_catching_output(String param) {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        while (true) {
            try {
                if ((line = input.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!line.trim().equals("")) {
                // keep only the process name
                String mac = extractMacAddr(line.substring(1));
                if (!mac.isEmpty()) {
                    return mac;
                }
            }

        }
        return null;
    }

    /***
     * The String passed in the method is 'split' using regular expression and
     * moved into an array of Strings. The for loop checks the length of the elements in the
     * array for length of 17 as this will be the length of the MAC address.
     *
     * It is then moved to upper case for clarity and returned.
     *
     * @param str - Each line of the 'apr -a' output
     * @return - MAC address in uppercase
     */
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