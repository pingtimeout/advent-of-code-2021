package fr.pingtimeout.adventofcode2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Parser {
    public List<String> readFileAsStrings(String filePath) throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getResource(filePath).toURI());
        return Files.readAllLines(path);
    }

    public <E> List<E> readFileAs(String filePath, Function<String, E> transformer) throws URISyntaxException, IOException {
        return readFileAsStrings(filePath)
                .stream()
                .map(transformer)
                .collect(Collectors.toList());
    }

    public List<Integer> readFileAsInts(String filePath) throws URISyntaxException, IOException {
        return readFileAs(filePath, Integer::parseInt);
    }
}
