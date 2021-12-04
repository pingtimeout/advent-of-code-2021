package fr.pingtimeout.adventofcode2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public abstract class DayTest {
  protected List<String> readInput(AdventDay day) throws URISyntaxException, IOException {
    String path = "/" + day.getClass().getSimpleName().toLowerCase() + "/input.txt";
    return new Parser().readFileAsStrings(path);
  }
}
