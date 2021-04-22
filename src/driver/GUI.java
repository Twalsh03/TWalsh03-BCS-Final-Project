package driver;

import javax.swing.*;

public class GUI {
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
