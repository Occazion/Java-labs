package com.nure.yehor.v12;

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
        app.process("additional/v12.txt", "additional/newv12.txt", "word");
    }

    public void process(String path, String resultFilename, String regex) {
        List<String> newLines = fileToStream(path)
                .flatMap(s -> Stream.of(s.split(" ")))
                .filter(l -> l.matches(regex))
                .collect(Collectors.toList());
        writeToFile(newLines, resultFilename);
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
