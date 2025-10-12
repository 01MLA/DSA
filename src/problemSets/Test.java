package problemSets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        // 👇 Replace this with your target folder path
        String folderPath = "D:\\Android_Development (65GB)\\YouTube Videos";

        // 👇 Path where output will be saved
        String outputFilePath = "D:\\file_list_output.txt";

        File folder = new File(folderPath);

        // ✅ Check if path is a directory
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            try (FileWriter writer = new FileWriter(outputFilePath)) {
                writer.write("Files in folder: " + folderPath + "\n\n");

                if (files != null && files.length > 0) {
                    for (File file : files) {
                        if (file.isFile()) {
                            writer.write(file.getName() + "\n");
                        }
                    }
                    System.out.println("✅ File names have been written to: " + outputFilePath);
                } else {
                    writer.write("No files found in this folder.\n");
                    System.out.println("No files found in this folder.");
                }
            } catch (IOException e) {
                System.out.println("❌ Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("The path is not a valid directory.");
        }
    }
}
