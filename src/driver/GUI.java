package driver;

import device.Device;
import scanTools.NetScan;
import scanTools.PortScan;
import utils.Gateway;
import utils.LocalHost;

import javax.sound.sampled.Port;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class GUI extends JFrame {
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JRadioButton fullPortScanRadioButton;
    private JRadioButton quickPortScanRadioButton;
    private JList foundDevicesList;
    private JButton scanButton;
    private JProgressBar progressBar1;
    private JTabbedPane tabbedPane1;
    private JLabel hostLabel;
    private JLabel GatewayLabel;
    private JLabel hostAddressText;
    private JLabel GatewayAddressText;
    private JLabel MACLabel;
    private JLabel DeviceIPLabel;
    private JButton startTCPPortScanButton;

    public GUI() {
        DefaultListModel<Device> model = new DefaultListModel<>();
        ArrayList<Device> foundDevices = new ArrayList<>();
        foundDevicesList.setModel(model);

        NetScan netScan = new NetScan();
        PortScan portScan = new PortScan();
        ImageIcon img = new ImageIcon("src/network.png");


        //get local and set localHost to get Address
        LocalHost localHost = new LocalHost();
        localHost.setLocalHost();

        //get gateway and set gateway to get Address
        Gateway gateway = new Gateway();
        gateway.setGateway();

        //scan button
        scanButton.addActionListener(e -> {
            //set to false to activate once
            scanButton.setVisible(false);

            netScan.scan();

            //fill the foundlist
            for(int i= 0;i<netScan.getFoundDevices().size();i++) {
                 foundDevices.add(netScan.getFoundDevice(i));

                model.addElement(foundDevices.get(i));
            }
        });

        //listModel
        foundDevicesList.getSelectionModel().addListSelectionListener(e -> {
            Device d = (Device) foundDevicesList.getSelectedValue();
            DeviceIPLabel.setText(d.getIp().toString());
            MACLabel.setText(d.getMacAddress());
            startTCPPortScanButton.addActionListener(e1 -> {
                portScan.scanTCP(d, "quick");
                System.out.println(d.getPorts());
            });


        });

        //rootPanel
        setTitle("Network Scanner");
        add(rootPanel);
        hostAddressText.setText(localHost.getLocalHost());
        GatewayAddressText.setText(gateway.getGateway());


    }

}