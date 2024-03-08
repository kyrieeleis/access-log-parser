import java.io.File;
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
            if (fileExists == true) {
                System.out.println("Gotcha! " + "This is valid file number " + counter + ".");
                counter++;
            }
        }
    }
}

