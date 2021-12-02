package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Test;

public class Day01Test {
  @Test
  public void should_find_increases() throws URISyntaxException, IOException {
    List<String> ints = new Parser().readFileAsStrings("/day01/input.txt");
    int increases = new Day01().partOne(ints);
    assertThat(increases).isEqualTo(7);
  }

  @Test
  public void should_find_three_measurements_increases() throws URISyntaxException, IOException {
    List<String> ints = new Parser().readFileAsStrings("/day01/input.txt");
    int increases = new Day01().partTwo(ints);
    assertThat(increases).isEqualTo(5);
  }
}
