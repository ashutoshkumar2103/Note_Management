import java.util.Scanner;
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.*;
public class Notes_Manager {

    // This class manages notes using FileReader and FileWriter for file operations

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String filePath = "notes.txt";

        while(true) {
            System.out.println("---Notes Manager---");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Delete Notes");
            System.out.println("4. Exit");
            System.out.println("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 1) {
                System.out.println("Enter your note: ");
                String note = scanner.nextLine();
                writeNoteToFile(filePath, note);
                System.out.println("Note added successfully!\n");
            }
            else if(choice == 2) {
                readNotesFromFile(filePath);
                System.out.println();
            }
            else if(choice == 3) {
                deleteFile(filePath);
                System.out.println("All notes deleted successfully!");
            }
            else if(choice == 4) {
                System.out.println("Exiting Notes Manager. Goodbye!");
                break;
            }
            else {
                System.out.println("Invalid choice. Please try again.");
            }

        }
        scanner.close();    
    }

    public static void writeNoteToFile(String filePath, String note) {
        
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(note + System.lineSeparator());
        }
        catch (IOException e) {
            System.out.println("An error occurred while writing the note: " + e.getMessage());
        }
    }

    public static void readNotesFromFile(String filePath) {
        boolean fileExists = false;
        StringBuilder notes = new StringBuilder();
        try (FileReader reader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            System.out.println("Your Notes: ");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                fileExists = true;
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred while reading the notes: " + e.getMessage());
        }
        if(!fileExists) {
            System.out.println("No notes found. Add some notes first in order to view them \n");
        }
    }


    //Optional method just to check for deletion of file
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file. File does not exist.");
        }
    }   

}