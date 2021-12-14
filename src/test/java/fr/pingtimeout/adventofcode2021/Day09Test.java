package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Test;

public class Day09Test extends DayTest {
  @Test
  public void should_calculate_risk_level() throws URISyntaxException, IOException {
    Day09 day = new Day09();
    List<String> lines = readInput(day);
    assertThat(day.partOne(lines)).isEqualTo(15);
  }

  @Test
  public void should_locate_basins() throws URISyntaxException, IOException {
    Day09 day = new Day09();
    List<String> lines = readInput(day);
    assertThat(day.partTwo(lines)).isEqualTo(1134);
  }
}
