import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int counter = 1;
        for (; ; ) {
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (fileExists == false || isDirectory == true) {
                System.out.println("Chosen just directory (no file) or selected file doesn't exists. Please select a valid file or path.");
                continue;
            }
            if (fileExists) {
                System.out.println("Correct. " + "This is valid file number " + counter + ".");
                counter++;
                int minLength = Integer.MAX_VALUE;
                int maxLength = Integer.MIN_VALUE;
                int lineCounter = 0;
                String line;
                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader = new BufferedReader(fileReader);
                    while ((line = reader.readLine()) != null) {
                        int length = line.length();
                        if (line.length() > 1024) {
                            throw new LineTooLongException("Line too long (1024 characters max).");
                        }
                        if (line.length() < minLength) {
                            minLength = line.length();
                        } else if (line.length() >= maxLength) {
                            maxLength = line.length();
                        }
                        lineCounter++;
                    }
                    System.out.println("Total number of lines: " + lineCounter);
                    System.out.println("Min length: " + minLength);
                    System.out.println("Max length: " + maxLength);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
    }
}

