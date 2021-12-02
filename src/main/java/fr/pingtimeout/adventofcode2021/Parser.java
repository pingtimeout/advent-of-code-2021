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
}
