package Engine3D.engine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {

    private Utils() {
        // Utility class
    }

    public static String readFile(String filePath) throws IOException {
        String str;
        try {
            str = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException excp) {
            throw new RuntimeException("Error reading file [" + filePath + "]", excp);
        }
        return str;
    }
}
