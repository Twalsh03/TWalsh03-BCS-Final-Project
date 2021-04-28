package driver;

import device.Device;
import scanTools.NetScan;
import scanTools.PortScan;
import utils.Gateway;
import utils.LocalHost;

import javax.swing.*;
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
    private JList portList;
    static DefaultListModel portModel;

    public GUI() {
        DefaultListModel<Device> model = new DefaultListModel<>();
        ArrayList<Device> foundDevices = new ArrayList<>();
        foundDevicesList.setModel(model);

        portModel = new DefaultListModel();



        NetScan netScan = new NetScan();
        PortScan portScan = new PortScan();

        //get local and set localHost to get Address
        LocalHost localHost = new LocalHost();
        localHost.setLocalHost();

        //get gateway and set gateway to get Address
        Gateway gateway = new Gateway();
        gateway.setGateway();




        //scan button
        scanButton.addActionListener(e -> {
            netScan.scan();

            //fill the foundList
            for(int i= 0;i<netScan.getFoundDevices().size();i++) {
                foundDevices.add(netScan.getFoundDevice(i));

                model.addElement(foundDevices.get(i));

            }
        });



        //listModel
        foundDevicesList.getSelectionModel().addListSelectionListener(e -> {
            Device d = (Device) foundDevicesList.getSelectedValue();

            DeviceIPLabel.setText(d.getIPString());
            MACLabel.setText(d.getMacAddress());


        });

        foundDevicesList.getSelectionModel().addListSelectionListener(e -> {
            Device d = (Device) foundDevicesList.getSelectedValue();
            Object[] objectArray = null;
            if(d.getPorts()!= null) {
                objectArray = d.getPorts().entrySet().toArray();
                portModel.clear();
                for (int i = 0; i < d.getPorts().size(); i++) {
                    portModel.addElement(objectArray[i]);
                    portList.setModel(portModel);
                    System.out.println("portModel:" + portModel);
                }
            }

        });
        //TCP Scan will present error box if no device is selected
        //otherwise check which RadioButton is selected and use that value in the ScanType

        startTCPPortScanButton.addActionListener(e ->{
            Device d = (Device) foundDevicesList.getSelectedValue();
            if (d == null) {
                JOptionPane.showMessageDialog(rootPanel, "Scan the network and select a device to port scan", "Oops!", JOptionPane.WARNING_MESSAGE);
            } else {
                //radio buttons
                String radioSelection = null;

                boolean quickSelect = quickPortScanRadioButton.isSelected();
                boolean fullSelect = fullPortScanRadioButton.isSelected();

                if(quickSelect){
                    radioSelection = "quick";
                }else if(fullSelect){
                    radioSelection = "full";
                }else{
                    JOptionPane.showMessageDialog(rootPanel, "Select a device to port scan", "Oops!", JOptionPane.WARNING_MESSAGE);
                    return;
                }


                System.out.println("selected RadioButton : "+radioSelection);
                portScan.scanTCP(d, radioSelection);


                System.out.println(d.getPorts());
             }
        });

        //rootPanel
        setTitle("Network Scanner");
        add(rootPanel);
        hostAddressText.setText(localHost.getLocalHost());
        GatewayAddressText.setText(gateway.getGateway());


    }


    }


