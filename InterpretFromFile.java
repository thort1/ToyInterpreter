import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class InterpretFromFile {
    public static void main(String[] args) throws Exception {
        // TODO - Replace with actual file path name to tester_program.txt
        Interpreter i = new Interpreter(readFile("yourFilePathGoesHERE"));
        i.evalProgram();
    }

    private static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.US_ASCII);
    }
}
