package Week8.Swing;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import Week8.Constants;
import Week8.Utils.Device;

public class DevicesTable {
    JFrame frame = new JFrame("DEVICES TABLE");
    JButton btnUpdate, btnDelete, btnConnection, btnAddNew, btnShowConnection, btnFind;
    JLabel lblDeviceFrom, lblDeviceTo, lblPath, lblNoData;
    JComboBox<String> cbDeviceFrom, cbDeviceTo;
    JTable deviceTable;
    JScrollPane scrollPane;

    Device deviceActions = new Device();

    public DevicesTable() {
        String column[] = Constants.deviceDetailKeys;
        String data[][] = deviceActions.generateTableData(column.length);
        String deviceNames[] = { "No data" };
        if (data != null) {
            deviceNames = new String[data.length];
            for (int i = 0; i < data.length; i++) {
                deviceNames[i] = data[i][Constants.deviceNameIndex];
            }
            deviceTable = new JTable(data, column);
            scrollPane = new JScrollPane(deviceTable);
            scrollPane.setBounds(50, 100, 600, 400);
            frame.add(scrollPane);
        } else {
            lblNoData = new JLabel("No data in table.", SwingConstants.CENTER);
            lblNoData.setBounds(250, 100, 200, 30);
            frame.add(lblNoData);
        }

        btnConnection = new JButton("Connection");
        btnConnection.setBounds(50, 50, 150, 30);
        btnAddNew = new JButton("Add New");
        btnAddNew.setBounds(220, 50, 100, 30);
        btnShowConnection = new JButton("Show Connection");
        btnShowConnection.setBounds(340, 50, 200, 30);
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(400, 500, 100, 30);
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(550, 500, 100, 30);

        frame.add(btnAddNew);
        if (data != null) {
            frame.add(btnConnection);
            frame.add(btnShowConnection);
            frame.add(btnUpdate);
            frame.add(btnDelete);
        }

        lblDeviceFrom = new JLabel("From: ", SwingConstants.RIGHT);
        lblDeviceFrom.setBounds(100, 550, 50, 30);
        lblDeviceTo = new JLabel("To: ", SwingConstants.RIGHT);
        lblDeviceTo.setBounds(300, 550, 50, 30);
        lblPath = new JLabel();
        lblPath.setBounds(50, 600, 600, 30);
        if (data != null) {
            frame.add(lblDeviceFrom);
            frame.add(lblDeviceTo);
            frame.add(lblPath);
        }

        cbDeviceFrom = new JComboBox<String>(deviceNames);
        cbDeviceFrom.setEditable(false);
        cbDeviceFrom.setBounds(150, 550, 150, 30);
        cbDeviceTo = new JComboBox<String>(deviceNames);
        cbDeviceTo.setEditable(false);
        cbDeviceTo.setBounds(350, 550, 150, 30);

        btnFind = new JButton("Find");
        btnFind.setBounds(500, 550, 100, 30);

        if (data != null) {
            frame.add(cbDeviceFrom);
            frame.add(cbDeviceTo);
            frame.add(btnFind);
        }

        btnFind.addActionListener(e -> {
            deviceActions.plotGraphAndMatrix();
            String deviceFrom = cbDeviceFrom.getSelectedItem().toString();
            String deviceTo = cbDeviceTo.getSelectedItem().toString();
            String pathText = "";
            String[] path = deviceActions.findShortestPath(deviceFrom, deviceTo);
            for (int i = path.length - 1; i >= 0; i--) {
                if (i == 0) {
                    pathText += path[i];
                } else {
                    pathText += path[i] + " -> ";
                }
            }
            lblPath.setText(pathText);
        });

        btnConnection.addActionListener(e -> {
            new ConnectionUI(data);
            frame.dispose();
        });

        btnShowConnection.addActionListener(e -> {
            deviceActions.plotGraphAndMatrix();
            new ShowConnectionUI(deviceActions.totalDevices, deviceActions.fetchConnectedDevices());
            frame.dispose();
        });

        btnAddNew.addActionListener(e -> {
            new AddDeviceUI(null);
            frame.dispose();
        });

        btnUpdate.addActionListener(e -> {
            int selectedRow = deviceTable.getSelectedRow();
            if (selectedRow > -1) {
                new AddDeviceUI(deviceActions.deviceList.get(selectedRow).split(Constants.spliter));
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(scrollPane, "Please select row first.");
            }
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = deviceTable.getSelectedRow();
            if (selectedRow > -1) {
                int res = JOptionPane.showConfirmDialog(scrollPane, "Confirm to delete?");
                System.out.println(res);
                if (res == 0) {
                    deviceActions.deleteDevice(deviceActions.deviceList.get(selectedRow)
                            .split(Constants.spliter)[Constants.deviceIDIndex]);
                    new DevicesTable();
                    frame.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(scrollPane, "Please select row first.");
            }
        });

        frame.setLayout(null);
        frame.setSize(700, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
