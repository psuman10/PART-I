package Week8.Utils;

import Week8.AdjGraph;
import Week8.Constants;

import java.util.ArrayList;

import Week8.FileHandler;

public class Device {
    FileHandler fh = new FileHandler();
    public ArrayList<String> deviceList = fetchAllDeviceList();
    String deviceArray[][] = generateTableData(Constants.deviceDetailKeys.length);
    public int totalDevices = deviceList.size();
    int endId = 0;
    public AdjGraph deviceGraph = new AdjGraph(endId + 1);

    public Device() {
        if (this.totalDevices > 0) {
            this.endId = Integer.parseInt(
                this.deviceList.get(this.deviceList.size() - 1).split(Constants.spliter)[Constants.deviceIDIndex]);
            	this.deviceGraph = new AdjGraph(this.endId + 1);
        }
    }

    public static void main(String[] args) {
        Device d = new Device();
        d.plotGraphAndMatrix();
        d.fetchConnectedDevices();
        d.deviceGraph.shortestPathDij(0, 9);
    }

    public String[] findShortestPath(String from, String to) {
        int[] path = this.deviceGraph.shortestPathDij(Integer.parseInt(convertNameToId(from)),
                Integer.parseInt(convertNameToId(to)));
        String[] pathDevices = new String[path.length];
        for (int i = 0; i < path.length; i++) {
            pathDevices[i] = convertIdToName(Integer.toString(path[i]));
        }
        return pathDevices;
    }

    public ArrayList<String[]> fetchConnectedDevices() {
        String deviceIds[] = new String[this.endId + 1];
        for (int i = 0; i < deviceList.size(); i++) {
            deviceIds[i] = deviceList.get(i).split(Constants.spliter)[Constants.deviceIDIndex];
        }
        ArrayList<String[]> adjDevices = new ArrayList<String[]>();
        for (String di : deviceIds) {
            if (di != null) {
                String connected[] = this.deviceGraph.getConnected(Integer.parseInt(di));
                String names[] = new String[connected.length];
                for (int i = 0; i < names.length; i++) {
                    names[i] = convertIdToName(connected[i]);
                }
                adjDevices.add(names);
            }
        }

        return adjDevices;
    }

    public void plotGraphAndMatrix() {
        for (int i = 0; i < deviceList.size(); i++) {
            String each[] = deviceList.get(i).split(Constants.spliter);
            int cost = 1;
            if (each[2].equals(each[1])) {
                cost = 0;
            }
            this.deviceGraph.addEdge(Integer.parseInt(each[2]), Integer.parseInt(each[1]), cost);
        }
    }

    public ArrayList<String> fetchAllDeviceList() {
        return fh.ReadOrFetchAll(Constants.deviceDetailFileName);
    }

    public String[][] generateTableData(int columnLength) {
        if (deviceList.size() > 0) {
            String data[][] = new String[deviceList.size()][columnLength];
            for (int i = 0; i < deviceList.size(); i++) {
                for (int j = 0; j < columnLength; j++) {
                    data[i][j] = deviceList.get(i).split(Constants.spliter)[j];
                }
            }
            return data;
        } else {
            return null;
        }
    }

    public String checkConnectedDevice(String deviceName) {
        String parentId = null;
        for (int i = 0; i < this.totalDevices; i++) {
            if (deviceArray[i][Constants.deviceNameIndex].equals(deviceName)) {
                parentId = deviceArray[i][Constants.deviceParentIdIndex];
            }
        }
        return parentId;
    }

    public String convertIdToName(String ID) {
        String name = "";
        for (int i = 0; i < this.totalDevices; i++) {
            if (deviceArray[i][Constants.deviceIDIndex].equals(ID)) {
                name = deviceArray[i][Constants.deviceNameIndex];
            }
        }
        return name;
    }

    public String[] findLine(String ID) {
        String line[] = null;
        for (int i = 0; i < this.totalDevices; i++) {
            if (deviceArray[i][Constants.deviceIDIndex].equals(ID)) {
                line = deviceArray[i];
            }
        }
        return line;
    }

    public String convertNameToId(String name) {
        String ID = "";
        for (int i = 0; i < this.totalDevices; i++) {
            if (deviceArray[i][Constants.deviceNameIndex].equals(name)) {
                ID = deviceArray[i][Constants.deviceIDIndex];
            }
        }
        return ID;
    }

    public String addDevice(String[] deviceDetail) {
        FileHandler fh = new FileHandler();
        return fh.CreateOrAdd(Constants.deviceDetailFileName, deviceDetail);
    }

    public String deleteDevice(String ID) {
        FileHandler fh = new FileHandler();
        return fh.DeleteFromFile(Constants.deviceDetailFileName, ID);
    }

    public String updateDevice(String changeData[]) {
        return fh.UpdateFile(Constants.deviceDetailFileName, changeData);
    }

}
