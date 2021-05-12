package com.nure.yehor.v11;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    public static void main(String[] args) {
        Application app = new Application();
        app.process("additional/v11.txt", "additional/newv11.txt", 3, 1);
    }

    public void process(String path, String resultFilename, int lines, int words) {
        Stream<String> linesStream = fileToStream(path);
        long size = linesStream.count();

        List<String> newLines = fileToStream(path)
                .skip(size - lines)
                .map(s -> processString(s, words))
                .collect(Collectors.toList());
        writeToFile(newLines, resultFilename);
    }

    private String processString(String str, int words) {
        String[] wordsArr = str.split(" ");
        return Arrays.stream(wordsArr)
                .skip((long) wordsArr.length - words)
                .collect(Collectors.joining(" "));
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
