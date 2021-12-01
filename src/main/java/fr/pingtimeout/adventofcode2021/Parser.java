package fr.pingtimeout.adventofcode2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Parser {
    public List<String> readFileAsStrings(String filePath) throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getResource(filePath).toURI());
        return Files.readAllLines(path);
    }

    public int[] readFileAsInts(String filePath) throws URISyntaxException, IOException {
        List<String> lines = readFileAsStrings(filePath);
        int[] result = new int[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            result[i] = Integer.parseInt(lines.get(i));
        }
        return result;
    }
}
