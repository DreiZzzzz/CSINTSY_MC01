import java.io.*;
import java.util.Scanner;

public class Checker {
    private static DataRecord recordHolder;

    // Assuming this is your custom file reader class
    private static FileReader readFile = new FileReader();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName;
        int userChoice;
        boolean reLoop = false;

        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        // Loops until the user enters an existing and valid file name.
        do {
            System.out.print("Enter filename: ");
            fileName = sc.nextLine();  // Use user input instead of hardcoded "sample.txt"

            System.out.println(); // prints a newline

            // Try reading the file, handle potential exceptions
            recordHolder = readFile.fileReader(fileName);
            if (recordHolder == null) {
                System.out.println("Error reading file. Please try again.");
            }
        } while(recordHolder == null);  // re-loop until the file is read correctly

        System.out.println("Neighbours of B: " + recordHolder.getActualCost().get(16).size()); // 4
        // If file is read successfully, print connections
        recordHolder.printConnections();
    }
}

