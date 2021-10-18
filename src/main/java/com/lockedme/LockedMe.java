package com.lockedme;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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
                break;
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

    }

    public static boolean businessMenuHandler(int option) {
        return true;
    }

    public static void listFiles() {

    }

    public static void addNewFile() {

    }

    public static void deleteFile() {

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
        return "";
    }
}
