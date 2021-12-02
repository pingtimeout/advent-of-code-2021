package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Test;

public class ParserTest {
  @Test
  public void should_read_input_file() throws URISyntaxException, IOException {
    List<String> strings = new Parser().readFileAsStrings("/day01/input.txt");
    assertThat(strings)
        .containsExactly("199", "200", "208", "210", "200", "207", "240", "269", "260", "263");
  }
}
