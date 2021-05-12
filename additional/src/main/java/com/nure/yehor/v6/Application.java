package com.nure.yehor.v6;

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
        app.process("additional/text.txt", "additional/newtext.txt");

    }

    public void process(String path, String resultFilename) {
        List<String> newLines = fileToStream(path)
                .map(s -> s.length() > 2 ? s.toUpperCase() : s)
                .collect(Collectors.toList());
        writeToFile(newLines, resultFilename);
    }

    private Stream<String> fileToStream(String path) {
        try {
            return Files.lines(Paths.get(path))
                    .flatMap(l -> Stream.of(l.split("\\s+")))
                    ;
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
