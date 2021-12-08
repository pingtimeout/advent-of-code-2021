package fr.pingtimeout.adventofcode2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.junit.Test;

public class Day03Test extends DayTest {
  @Test
  public void should_calculate_power_consumption() throws URISyntaxException, IOException {
    Day03 day = new Day03();
    List<String> lines = readInput(day);
    assertThat(day.partOne(lines)).isEqualTo(198);
  }

  @Test
  public void should_calculate_life_support_rating() throws URISyntaxException, IOException {
    Day03 day = new Day03();
    List<String> lines = readInput(day);
    assertThat(day.partTwo(lines)).isEqualTo(230);
  }
}
