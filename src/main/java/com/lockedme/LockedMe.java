package com.lockedme;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class LockedMe {
    static BufferedReader reader;
    static final File rootFolder;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        rootFolder = new File("./rootDir");
        rootFolder.mkdirs();
    }

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        while (true) {
            displayMainMenu();
            mainMenuOptionHandler(readOption(3));
        }
    }

    public static void displayMainMenu() {
        System.out.println("======================================");
        System.out.println("LockedMe.com");
        System.out.println("Developed by Nasser Al-Jbreen");
        try {
            System.out.println("working directory: " + rootFolder.getCanonicalPath());
        } catch (IOException e) {
            System.out.println("An error has occurred while displaying working directory path, please contact an administrator.");
        }
        System.out.println("1- List all files in root directory");
        System.out.println("2- Extra file operations");
        System.out.println("3- Exit");
    }

    public static void mainMenuOptionHandler(int option) {
        switch (option) {
            case 1:
                listFiles();
                break;
            case 2:
                businessMenu();
                break;
            case 3:
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Failed to close the input stream");
                }
                System.exit(0);
            default:
                System.out.printf("The option you chose is %d, which is not valid\n", option);
        }
    }

    public static void businessMenu() {
        do {
            displayBusinessMenu();
        } while (businessMenuHandler(readOption(4)));
    }

    public static void displayBusinessMenu() {
        System.out.println("1- Add a new file");
        System.out.println("2- Delete a file");
        System.out.println("3- Search for a file");
        System.out.println("4- return to menu");
    }

    public static boolean businessMenuHandler(int option) {
        switch (option) {
            case 1:
                addNewFile();
                return true;
            case 2:
                deleteFile();
                return true;
            case 3:
                searchForFile();
                return true;
            case 4:
                return false;
            default:
                System.out.printf("The option you chose is %d, which is not valid\n", option);
        }
        return true;
    }

    public static void listFiles() {
        File[] files = rootFolder.listFiles(File::isFile);
        if (files == null || files.length == 0) {
            System.out.println("No files in the directory");
            return;
        }
        Arrays.sort(files, Comparator.comparing(File::getName));
        System.out.println("Current file list:");
        for (File file: files) {
            System.out.println(" - " + file.getName());
        }
    }

    public static void addNewFile() {
        System.out.println("Making a new file");
        String fileName = readFileName();
        File file = new File(rootFolder, fileName);
        try {
            if (file.createNewFile()) {
                System.out.printf("File %s created successfully\n", fileName);
            } else {
                System.out.printf("File %s already exists\n", fileName);
            }
        } catch (IOException e) {
            System.out.println("Couldn't write to disk!, try again later");
        }
        System.out.println("======================================");
    }

    public static void deleteFile() {
        System.out.println("Deleting file");
        String fileName = readFileName();
        File file = new File(rootFolder, fileName);
        if (file.delete()) {
            System.out.printf("File %s deleted successfully\n", fileName);
        } else {
            System.out.printf("File %s doesn't exists\n", fileName);
        }
        System.out.println("======================================");
    }

    public static void searchForFile() {

    }

    public static int readOption(int max) {
        while (true) {
            try {
                System.out.print("Choose an option by writing its number: ");
                int number = Integer.parseInt(reader.readLine());
                System.out.println("======================================");
                if (number > max || number < 1) {
                    throw new Exception();
                }
                return number;
            } catch (Exception e) {
                System.out.println("Invalid number");
            }
        }
    }

    public static String readFileName() {
        while (true) {
            try {
                System.out.print("Write file name: ");
                String fileName = reader.readLine();
                System.out.println("======================================");
                if (fileName.isEmpty()) {
                    throw new Exception();
                }
                return fileName;
            } catch (Exception e) {
                System.out.println("File name is empty");
            }
        }
    }
}
