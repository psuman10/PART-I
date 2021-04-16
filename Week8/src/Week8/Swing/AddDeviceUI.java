package Week8.Swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Week8.Constants;
import Week8.FileHandler;
import Week8.Utils.Device;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class AddDeviceUI {
    int baseX = 125;
    int baseY = 100;
    int baseWidth = 100;
    int baseHeight = 30;
    int baseInputWidth = baseWidth + 150;
    int baseInputHeight = baseHeight;
    /**
     *
     */
    JFrame frame = new JFrame("ADD DEVICES");
    JLabel lblName, lblPort, lblType, lblDeviceID;
    JTextField tfName, tfPort;
    JComboBox<String> cbType;
    JButton btnSave, btnCancel;
    FileHandler fh = new FileHandler();

    /**
     * Create the frame.
     */
    public AddDeviceUI(String[] oldData) {

        Device deviceActions = new Device();
        String deviceTypes[] = { "Server", "Switch", "Hub", "Computer", "Mobile" };

        // LABELs
        if (oldData != null) {
            lblDeviceID = new JLabel("Device ID: " + oldData[Constants.deviceIDIndex]);
            lblDeviceID.setBounds(baseX + 150, (int) (baseY * 0.5), baseWidth, baseHeight);
            frame.add(lblDeviceID);
        }
        lblName = new JLabel("Device Name: ");
        lblName.setBounds(baseX, (int) (baseY * 1), baseWidth, baseHeight);
        frame.add(lblName);
        lblPort = new JLabel("Device Port: ");
        lblPort.setBounds(baseX, (int) (baseY * 1.5), baseWidth, baseHeight);
        frame.add(lblPort);
        lblType = new JLabel("Device Type: ");
        lblType.setBounds(baseX, (int) (baseY * 2), baseWidth, baseHeight);
        frame.add(lblType);

        // FIELDs
        tfName = new JTextField();
        tfName.setBounds(baseX + 125, (int) (baseY * 1), baseInputWidth, baseInputHeight);
        tfPort = new JTextField();
        tfPort.setBounds(baseX + 125, (int) (baseY * 1.5), baseInputWidth, baseInputHeight);
        cbType = new JComboBox<String>(deviceTypes);
        cbType.setEditable(false);
        cbType.setBounds(baseX + 125, (int) (baseY * 2), baseInputWidth, baseInputHeight);

        if (oldData != null) {
            tfName.setText(oldData[Constants.deviceNameIndex]);
            tfPort.setText(oldData[Constants.devicePortIndex]);
            cbType.setSelectedItem(oldData[Constants.deviceTypeIndex]);
        }
        frame.add(tfName);
        frame.add(tfPort);
        frame.add(cbType);

        btnSave = new JButton("Save");
        btnSave.setBounds(baseX + 70, (int) (baseY * 2.5), baseWidth + 20, baseHeight);
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(baseX + 210, (int) (baseY * 2.5), baseWidth + 20, baseHeight);

        frame.add(btnSave);
        frame.add(btnCancel);

        btnCancel.addActionListener(e -> {
            new DevicesTable();
            frame.dispose();
        });

        btnSave.addActionListener(e -> {
            String name = tfName.getText();
            String port = tfPort.getText();
            String type = cbType.getSelectedItem().toString();
            String ID = null;
            String parentID = null;

            if (oldData != null) {
                ID = oldData[Constants.deviceIDIndex];
                parentID = oldData[Constants.deviceParentIdIndex];
            } else {
                ID = getDeviceId();
                parentID = getDeviceId();
            }

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Device name is required.");
            } else if (checkDeviceName(name)) {
                JOptionPane.showMessageDialog(frame, "Device name should be unique.");
            } else if (port.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Device port is required.");
            } else if (type.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Device Type is required.");
            } else {
                String deviceDetail[] = { name, ID, parentID, port, type };
                String message = null;
                if (oldData != null) {
                    message = deviceActions.updateDevice(deviceDetail);
                } else {
                    message = deviceActions.addDevice(deviceDetail);
                }
                JOptionPane.showMessageDialog(frame, message);
                if (message.startsWith("Success")) {
                    new DevicesTable();
                    frame.dispose();
                }
            }
        });

        // SETTINGS OF THE FRAME
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    String getDeviceId() {
        ArrayList<String> data = fh.ReadOrFetchAll(Constants.deviceDetailFileName);
        if (data.size() > 0) {
            int prevId = Integer.parseInt(data.get(data.size() - 1).split(Constants.spliter)[Constants.deviceIDIndex]);
            return Integer.toString(prevId + 1);
        } else {
            return "0";
        }
    }

    boolean checkDeviceName(String newName) {
        String data[] = fh.ReadOrFetch(Constants.deviceDetailFileName, newName, Constants.deviceNameIndex);
        return data != null;
    }
}