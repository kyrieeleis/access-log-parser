import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int counter = 1;
        for (; ; ) {
            System.out.println("Enter path to file: ");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (fileExists == false || isDirectory == true) {
                System.out.println("Chosen just directory (not file) or selected file doesn't exists. Please select a valid file or path.");
                continue;
            }
            if (fileExists) {
                System.out.println("Correct. " + "This is valid file number " + counter + ".");
                counter++;
                int lineCounter = 0;
                int yandexCounter = 0;
                int googleCounter = 0;
                String line;
                // String[] parts = new String[0];
                try {
                    FileReader fileReader = new FileReader(path);
                    BufferedReader reader = new BufferedReader(fileReader);
                    while ((line = reader.readLine()) != null) {
                        int length = line.length();
                        if (line.length() > 1024) {
                            throw new LineTooLongException("Line too long (1024 characters max).");
                        }
                        String[] firstBrackets = line.split("\"");
                        String parts[] = firstBrackets[5].split(";");
                        String fragment = null;
                        if (parts.length >= 2) {
                            fragment = parts[1];
                            fragment.split("/");
                            for (String part : parts) {
                                if (part.contains("YandexBot")) {
                                    part.trim();
                                    yandexCounter++;
                                }

                                if (part.contains("Googlebot")) {
                                    part.trim();
                                    googleCounter++;
                                }
                            }
                        }
                        lineCounter++;
                    }
                    System.out.println("Total number of lines: " + lineCounter);
                    System.out.println("YandexBot: " + yandexCounter + " or " + Math.round(percentage(yandexCounter, lineCounter)) + "% of total.");
                    System.out.println("Googlebot: " + googleCounter + " or " + Math.round(percentage(googleCounter, lineCounter)) + "% of total.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    private static double percentage(int a, int b) {
        return (double) a / (double) b * 100;
    }
}


