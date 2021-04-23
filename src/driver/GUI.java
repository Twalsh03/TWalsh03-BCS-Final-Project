package driver;

import utils.Gateway;
import utils.LocalHost;

import javax.swing.*;


public class GUI extends JFrame {
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JRadioButton fullPortScanRadioButton;
    private JRadioButton quickPortScanRadioButton;
    private JRadioButton noPortScanRadioButton;
    private JList foundDevices;
    private JButton scanButton;
    private JProgressBar progressBar1;
    private JTabbedPane tabbedPane1;
    private JLabel hostLabel;
    private JLabel GatewayLabel;
    private JLabel hostAddressText;
    private JLabel GatewayAddressText;

    public GUI() {
        //get local and set localHost to get Address
        LocalHost localHost = new LocalHost();
        localHost.setLocalHost();

        //get gateway and set gateway to get Address
        Gateway gateway = new Gateway();
        gateway.setGateway();

        //
        setTitle("Network Scanner");

        add(rootPanel);
        hostAddressText.setText(localHost.getLocalHost());
        GatewayAddressText.setText(gateway.getGateway());


    }


}
