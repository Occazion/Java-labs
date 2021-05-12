package com.nure.yehor.v13;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) {
        Application app = new Application();
        app.process("additional/v13.txt", "additional/newv13.txt", "+380", "+390");
    }

    public void process(String path, String resultFilename, String firstSeq, String secondSeq) {
        List<String> newLines = fileToStream(path)
                .filter(l -> matches(l.split(" ")[1], firstSeq, secondSeq))
                .collect(Collectors.toList());
        writeToFile(newLines, resultFilename);
    }

    private boolean matches(String str, String... regex) {
        for (String reg : regex) {
            if (str.startsWith(reg)) {
                return true;
            }
        }
        return false;
    }

    private Stream<String> fileToStream(String path) {
        try {
            return Files.lines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.empty();
    }

    private void writeToFile(List<String> lines, String filepath) {
        try (FileWriter writer = new FileWriter(filepath, false)) {
            for (String str : lines) {
                writer.write(str);
                writer.append(System.lineSeparator());
                writer.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
