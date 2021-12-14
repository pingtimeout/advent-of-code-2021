package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Test;

public class Day10Test extends DayTest {
  @Test
  public void should_calculate_syntax_error_score() throws URISyntaxException, IOException {
    Day10 day = new Day10();
    List<String> lines = readInput(day);
    assertThat(day.partOne(lines)).isEqualTo(26397);
  }

  @Test
  public void should_calculate_completion_score() throws URISyntaxException, IOException {
    Day10 day = new Day10();
    List<String> lines = readInput(day);
    assertThat(day.partTwo(lines)).isEqualTo(288957);
  }
}
