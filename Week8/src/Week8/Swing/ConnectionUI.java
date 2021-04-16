package Week8.Swing;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import Week8.Constants;
import Week8.Utils.Device;

public class ConnectionUI {
    JFrame frame = new JFrame("USER LOGIN");

    JLabel lblDeviceTwo, lblDeviceOne, lblDeviceOneID, lblDeviceTwoID;
    JComboBox<String> cbDeviceTwo, cbDeviceOne;
    JButton btnSave, btnCancel;

    Device deviceActions = new Device();

    public ConnectionUI(String data[][]) {
        String deviceNames[] = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            deviceNames[i] = data[i][Constants.deviceNameIndex];
        }
        lblDeviceOne = new JLabel("Device: ");
        lblDeviceOne.setBounds(75, 100, 200, 30);
        lblDeviceTwo = new JLabel("Connected to: ");
        lblDeviceTwo.setBounds(75, 150, 200, 30);
        lblDeviceOneID = new JLabel();
        lblDeviceOneID.setText("ID: " + data[0][Constants.deviceIDIndex]);
        lblDeviceOneID.setBounds(500, 100, 100, 30);
        lblDeviceTwoID = new JLabel();
        lblDeviceTwoID.setText("ID: " + data[0][Constants.deviceIDIndex]);
        lblDeviceTwoID.setBounds(500, 150, 100, 30);

        frame.add(lblDeviceOne);
        frame.add(lblDeviceTwo);
        frame.add(lblDeviceOneID);
        frame.add(lblDeviceTwoID);

        cbDeviceOne = new JComboBox<String>(deviceNames);
        cbDeviceOne.setEditable(false);
        cbDeviceOne.setBounds(300, 100, 150, 30);
        cbDeviceTwo = new JComboBox<String>(deviceNames);
        cbDeviceTwo.setEditable(false);
        cbDeviceTwo.setBounds(300, 150, 150, 30);

        frame.add(cbDeviceOne);
        frame.add(cbDeviceTwo);

        btnSave = new JButton("Save");
        btnSave.setBounds(170, 220, 120, 30);
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(310, 220, 120, 30);

        frame.add(btnSave);
        frame.add(btnCancel);

        cbDeviceOne.addItemListener(e -> {
            String deviceName = e.getItem().toString();
            String parentId = deviceActions.checkConnectedDevice(deviceName);
            if (parentId != null) {
                cbDeviceTwo.setSelectedItem(deviceActions.convertIdToName(parentId));
                lblDeviceOneID.setText("ID: " + deviceActions.convertNameToId(deviceName));
            }
        });

        cbDeviceTwo.addItemListener(e -> {
            String deviceName = e.getItem().toString();
            lblDeviceTwoID.setText("ID: " + deviceActions.convertNameToId(deviceName));
        });

        btnCancel.addActionListener(e -> {
            new DevicesTable();
            frame.dispose();
        });

        btnSave.addActionListener(e -> {
            String selectedDevice = cbDeviceOne.getSelectedItem().toString();
            String connectedDevice = cbDeviceTwo.getSelectedItem().toString();
            String changeData[] = deviceActions.findLine(deviceActions.convertNameToId(selectedDevice));
            changeData[Constants.deviceParentIdIndex] = deviceActions.convertNameToId(connectedDevice);
            String res = deviceActions.updateDevice(changeData);
            JOptionPane.showMessageDialog(frame, res);
            if (res != null) {
                new DevicesTable();
                frame.dispose();
            }
        });

        // SETTINGS OF THE FRAME
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
