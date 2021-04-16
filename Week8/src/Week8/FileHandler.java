package Week8;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public String CreateOrAdd(String fileName, String[] data) {
        String message = "";
        try {
            File fl = new File(fileName);
            if (!fl.exists()) {
                fl.createNewFile();
            }
            if (fl.canWrite()) {
                String newData = "";
                for (int i = 0; i < data.length; i++) {
                    if (i != (data.length - 1)) {
                        newData += data[i] + Constants.spliter;
                    } else {
                        newData += data[i];
                    }
                }
                FileWriter flw = new FileWriter(fl, true);
                BufferedWriter buw = new BufferedWriter(flw);
                buw.write(newData + "\n");
                buw.close();
                message = "Success (Added).";
            } else {
                message = "File is writeable.";
            }
        } catch (Exception e) {
            message = "An error occured.";
            e.printStackTrace();
        }
        return message;
    }

    public String UpdateFile(String fileName, String changeData[]) {
        String message = "";
        try {
            File fl = new File(fileName);
            if (!fl.exists()) {
                fl.createNewFile();
            }
            if (fl.canWrite()) {
                ArrayList<String> oldData = ReadOrFetchAll(fileName);
                String newData = "";
                for (int i = 0; i < changeData.length; i++) {
                    if (i != (changeData.length - 1)) {
                        newData += changeData[i] + Constants.spliter;
                    } else {
                        newData += changeData[i];
                    }
                }
                FileWriter flw = new FileWriter(fl);
                BufferedWriter buw = new BufferedWriter(flw);
                for (String d : oldData) {
                    if (d.split(Constants.spliter)[Constants.deviceIDIndex].equals(changeData[Constants.deviceIDIndex])) {
                        buw.write(newData + "\n");
                    } else {
                        buw.write(d + "\n");
                    }
                }
                buw.close();
                message = "Success (Updated).";
            }else {
                message = "File is not writeable.";
            }
        } catch (Exception e) {
            message = "An error occured.";
            e.printStackTrace();
        }
        return message;
    }

    public String DeleteFromFile(String fileName, String ID) {
        String message = "";
        try {
            File fl = new File(fileName);
            if (!fl.exists()) {
                fl.createNewFile();
            }
            if (fl.canWrite()) {
                ArrayList<String> oldData = ReadOrFetchAll(fileName);
                FileWriter flw = new FileWriter(fl);
                BufferedWriter buw = new BufferedWriter(flw);
                for (String d : oldData) {
                    if (!d.split(Constants.spliter)[Constants.deviceIDIndex].equals(ID)) {
                        buw.write(d + "\n");
                    }
                }
                buw.close();
                message = "Success (Deleted).";
            }else {
                message = "File is not editable.";
            }
        } catch (Exception e) {
            message = "An error occured.";
            e.printStackTrace();
        }
        return message;
    }

    public String[] ReadOrFetch(String fileName, String findBy, int findIndex) {
        String[] fileData = null;
        try {
            File fl = new File(Constants.userDetailFileName);
            Scanner fileReader = new Scanner(fl);
            while (fileReader.hasNext()) {
                String data[] = fileReader.nextLine().split(Constants.spliter);
                if (findBy.toLowerCase().equals(data[findIndex].toLowerCase())) {
                    fileData = data;
                    break;
                }
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
        return fileData;
    }

    public ArrayList<String> ReadOrFetchAll(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File fl = new File(fileName);
            if (fl.exists()) {
                Scanner fileReader = new Scanner(fl);
                while (fileReader.hasNext()) {
                    fileData.add(fileReader.nextLine());
                }
                fileReader.close();
            }
        } catch (Exception e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
        return fileData;
    }

}