package Week8.Swing;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class ShowConnectionUI {

    int totalDevices = 0;
    JLabel lblDevice[] = new JLabel[totalDevices];
    JLabel lblConnection[] = new JLabel[totalDevices];
    JFrame frame = new JFrame("CONNECTION LIST");
    JButton btnBack;
    JList<String> connectionList = new JList<String>();

    int baseX = 50;
    int baseY = 40;
    int baseHeight = 30;
    int baseWidth = 100;

    int frameWidth = 800;
    int frameHeight = 800;

    public ShowConnectionUI(int totalDevices, ArrayList<String[]> connectionArray) {
        this.totalDevices = totalDevices;
        lblDevice = new JLabel[totalDevices];
        lblConnection = new JLabel[totalDevices];

        btnBack = new JButton("Back");
        btnBack.setBounds(baseX, baseY, baseWidth, baseHeight);

        btnBack.addActionListener(e -> {
            new DevicesTable();
            frame.dispose();
        });

        for (int i = 0; i < totalDevices; i++) {
            String[] deviceConnection = connectionArray.get(i);
            String deviceString = deviceConnection[0] + " is connected to: ";
            if(deviceConnection.length <= 1){
                deviceString = deviceConnection[0] + " is not connected.";
            }
            lblDevice[i] = new JLabel(deviceString, SwingConstants.RIGHT);
            lblDevice[i].setBounds(baseX, baseY * (i + 2), baseWidth + 150, baseHeight);
            String connectionStr = "";
            for (int j = 1; j < deviceConnection.length; j++) {
                if (j == 1) {
                    connectionStr += deviceConnection[j];
                } else {
                    connectionStr += " | " + deviceConnection[j];
                }
            }
            lblConnection[i] = new JLabel(connectionStr);
            lblConnection[i].setBounds(baseX + 270, baseY * (i + 2), baseWidth + 330, baseHeight);

            frame.add(lblDevice[i]);
            frame.add(lblConnection[i]);

        }

        frame.add(btnBack);

        frame.setLayout(null);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
